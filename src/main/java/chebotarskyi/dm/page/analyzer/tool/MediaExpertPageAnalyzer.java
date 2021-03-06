package chebotarskyi.dm.page.analyzer.tool;

import com.google.common.collect.Lists;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class MediaExpertPageAnalyzer implements PageAnalyzer {

    final static String MEDIAEXPERT_ROOT = "mediaexpert.pl";
    final static String SPEC = "laptopy";

    @Override
    public List<AnalyzerResult> analyze(Element body) {

        Elements productRows = body.getElementsByClass("m-product");

        List<AnalyzerResult> analyzerResults = Lists.newArrayList();

        if (productRows.isEmpty()) {
            return analyzerResults;
        }


        for (Element productRow : productRows) {

            Element productHeader = productRow.getElementsByClass("js-gtmEvent_click").first();
            if (productHeader == null)
                continue;
            String title = productHeader.text();
            String url = MEDIAEXPERT_ROOT + productHeader.attr("href");
            Element priceStringElement = productRow.getElementsByClass("price_txt").first();
            if (priceStringElement == null)
                continue;

            String priceString = priceStringElement.text();

            String availability = productRow.getElementsByClass("js-delivery_trigger").isEmpty() ?
                    "Nie dostępny" :
                    "Dostępny";

            try {
                int length = priceString.length();
                double price = Double.parseDouble(priceString.substring(0, length - 2) + "." + priceString.substring(length-2, priceString.length()));
                if (!title.isEmpty() && !url.isEmpty())
                    analyzerResults.add(ImmutableAnalyzerResult.of(title, url, price, availability));
            } catch (NumberFormatException ignored) {
            }
        }

        return analyzerResults;
    }

    @Override
    public String getRoot() {
        return MEDIAEXPERT_ROOT;
    }

    @Override
    public String getSpec() {
        return SPEC;
    }
}
