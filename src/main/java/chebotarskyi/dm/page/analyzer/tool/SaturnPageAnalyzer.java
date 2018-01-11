package chebotarskyi.dm.page.analyzer.tool;

import com.google.common.collect.Lists;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class SaturnPageAnalyzer implements PageAnalyzer {

    final static String SATURN_ROOT = "saturn.pl";
    final static String SPEC = "laptopy-laptopy-2-w-1";

    @Override
    public List<AnalyzerResult> analyze(Element body) {

        Elements productRows = body.getElementsByClass("m-productItem_content");

        List<AnalyzerResult> analyzerResults = Lists.newArrayList();

        if (productRows.isEmpty()) {
            return analyzerResults;
        }


        for (Element productRow : productRows) {

            Element productHeader = productRow.getElementsByClass("js-product-name").first();
            if (productHeader == null)
                continue;
            String title = productHeader.text();
            String url = SATURN_ROOT + productHeader.attr("href");
            Elements priceStringElements = productRow.getElementsByClass("m-priceBox_price");

            if (priceStringElements == null || priceStringElements.size() < 1)
                continue;

            String priceString = priceStringElements.first().text();

            Elements productDelivery = productRow.getElementsByClass("m-productItem_delivery");
            String availability = productDelivery == null || productDelivery.size() < 1 ? "Nie dostępny" : productDelivery.first().text();

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
        return SATURN_ROOT;
    }

    @Override
    public String getSpec() {
        return SPEC;
    }
}
