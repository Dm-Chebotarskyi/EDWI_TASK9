package chebotarskyi.dm.page.analyzer.tool;

import com.google.common.collect.Lists;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class MediaMarktPageAnalyzer implements PageAnalyzer {

    final static String MEDIAMARKT_ROOT = "mediamarkt.pl";
    final static String SPEC = "laptopy-laptopy-2-w-1";

    @Override
    public List<AnalyzerResult> analyze(Element body) {

        Elements productRows = body.getElementsByClass("m-offerBox_content");

        List<AnalyzerResult> analyzerResults = Lists.newArrayList();

        if (productRows.isEmpty()) {
            return analyzerResults;
        }


        for (Element productRow : productRows) {

            Element productMain = productRow.getElementsByClass("m-offerBox_contentInner").first();

            Element productHeader = productMain.getElementsByClass("js-product-name").first();
            String title = productHeader.text();
            String url = MEDIAMARKT_ROOT + productHeader.attr("href");
            Elements priceStringElements = productRow.getElementsByClass("m-priceBox_price");
            if (priceStringElements == null)
                continue;

            String priceString = priceStringElements == null ? priceStringElements.first().text() : "error";

            Elements availabilityElements = productRow.getElementsByClass("m-offerBox_cell");
            String availability = "Nie dostępny";
            if (availabilityElements.size() > 1)
                availability = availabilityElements.get(1).text();

            try {
                double price = Double.parseDouble(priceString.replaceAll("[-,/ ]", ""));
                if (!title.isEmpty() && !url.isEmpty())
                    analyzerResults.add(ImmutableAnalyzerResult.of(title, url, price, availability));
            } catch (NumberFormatException e) {
            }
        }

        return analyzerResults;
    }

    @Override
    public String getRoot() {
        return MEDIAMARKT_ROOT;
    }

    @Override
    public String getSpec() {
        return SPEC;
    }
}
