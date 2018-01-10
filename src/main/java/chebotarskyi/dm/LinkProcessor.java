package chebotarskyi.dm;

import chebotarskyi.dm.page.analyzer.tool.AnalyzerResult;
import chebotarskyi.dm.page.analyzer.tool.PageAnalyzerStrategy;
import com.google.common.collect.ImmutableSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class LinkProcessor {

    private final String url;
    private int count = 1;
    private IndexUtils indexUtils;
    private final PageAnalyzerStrategy analyzerStrategy;
    private final int threshold;

    public LinkProcessor(String url, IndexUtils indexUtils, int threshold) {
        this.url = url;
        this.indexUtils = indexUtils;
        this.threshold = threshold;
        analyzerStrategy = new PageAnalyzerStrategy(url);
    }

    public void startProcessing() {

        long startTime = System.currentTimeMillis();

        Set<String> links = new TreeSet<>();

        Set<String> innerLinks = process(url);

        while (count < threshold) {
            if (innerLinks == null) {
                break;
                //TODO: Figure out what to do with null
                //Or leave it like this :D
            }

            Set<String> linksToProcess = new TreeSet<>(innerLinks);

            linksToProcess.removeAll(links);

            links.addAll(innerLinks);
            if (linksToProcess.size() == 0)
                break;
            for (String link : linksToProcess) {

                if (count >= threshold)
                    break;

                Set<String> newLinks = process(link);
                count++;
                if (newLinks != null)
                    innerLinks.addAll(newLinks);
            }
        }


        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.print("Processed " + count + " links in " +
                String.format("%02d min, %02d sec\n",
                        TimeUnit.MILLISECONDS.toMinutes(duration),
                        TimeUnit.MILLISECONDS.toSeconds(duration) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))
                ));
    }


    private Set<String> process(String link) {

        if (!link.contains(analyzerStrategy.getRoot()))
            return new HashSet<>();

        String rootDomain = getDomainName(link);

        try {
            Element body = getBodyFrom(link);

            if (body != null) {

                System.out.println(link);

                List<AnalyzerResult> results = analyzerStrategy.analyze(body);

                for (AnalyzerResult result : results) {
                    indexUtils.addDoc(result.title(), result.url(), result.price());
                }
                Set<String> innerLinks = getLinksFrom(body);
                Set<String> links = new TreeSet<>();
                for (String l : innerLinks) {

                    if (!l.contains(analyzerStrategy.getSpec()))
                        continue;

                    if (l.contains(".pdf") || l.contains(".mp4") || l.contains(".mpeg4"))
                        continue;

                    if (getDomainName(l).equals("")) {
                        try {
                            URL url = new URL(new URL("http://" + rootDomain + "/"), l);
                            links.add(url.toString());
                        } catch (Exception e) {
                        }
                    } else {
                        links.add(l);
                    }
                }

                return links;
            }

        } catch (IOException e) {
        }

        return null;
    }

    private Element getBodyFrom(String url) throws IOException {
        Document html;
        try {
            if (!url.contains("http"))
                url = "http://" + url;
            html = Jsoup.connect(url).get();
        } catch (IllegalArgumentException e) {
            System.out.println("Malformed url!");
            return null;
        }
        Elements elements = html.getElementsByTag("body");
        if (elements.size() > 0)
            return elements.get(0);
        else
            return null;
    }

    private Set<String> getLinksFrom(Element element) {
        Set<String> links = new TreeSet<>();

        Elements elements = element.getElementsByAttribute("href");
        for (Element e : elements) {
            links.add(e.attr("href"));
        }

        return links;
    }

    private String getDomainName(String url) {
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            return "";
        }
        String domain = uri.getHost();
        if (domain != null)
            return domain.startsWith("www.") ? domain.substring(4) : domain;
        return "";
    }

}
