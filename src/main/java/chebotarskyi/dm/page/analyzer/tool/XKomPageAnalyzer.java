package chebotarskyi.dm.page.analyzer.tool;

import com.google.common.collect.Lists;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class XKomPageAnalyzer implements PageAnalyzer {

    final static String XKOM_ROOT = "x-kom.pl";
    final static String SPEC = "laptopy-notebooki-ultrabooki";

    @Override
    public List<AnalyzerResult> analyze(Element body) {

        Elements productRows = body.getElementsByClass("product-item");

        List<AnalyzerResult> analyzerResults = Lists.newArrayList();

        if (productRows.isEmpty()) {
            return analyzerResults;
        }


        for (Element productRow : productRows) {
            Element productSales = productRow.getElementsByClass("product-item").first();

            Element productHeader = productRow.getElementsByClass("name").first();
            if (productHeader == null)
                continue;
            String title = productHeader.text();
            String url = XKOM_ROOT + productHeader.attr("href");
            Element priceStringElement = productSales.getElementsByClass("price").first();
            if (priceStringElement == null)
                continue;

            String priceString = priceStringElement.text();

            String availability = productSales.getElementsByClass("js-add-to-cart").isEmpty() ?
                    "Nie dostępny" :
                    "Dostępny";

            try {

                double price = Double.parseDouble(priceString.replace(",", ".").replaceAll("[zł/ ]", ""));
                if (!title.isEmpty() && !url.isEmpty())
                    analyzerResults.add(ImmutableAnalyzerResult.of(title, url, price, availability));
            } catch (NumberFormatException e) {
            }

        }

        return analyzerResults;
    }

    @Override
    public String getRoot() {
        return XKOM_ROOT;
    }

    @Override
    public String getSpec() {
        return SPEC;
    }
}
