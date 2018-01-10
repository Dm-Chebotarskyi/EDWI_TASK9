package chebotarskyi.dm.page.analyzer.tool;

import com.google.common.collect.Lists;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class EuroPageAnalyzer implements PageAnalyzer {

    final static String EURO_AGD_ROOT = "euro.com.pl";
    final static String SPEC = "laptopy-i-netbooki";

    @Override
    public List<AnalyzerResult> analyze(Element body) {

        Elements productRows = body.getElementsByClass("product-row");

        List<AnalyzerResult> analyzerResults = Lists.newArrayList();

        if (productRows.isEmpty()) {
            return analyzerResults;
        }


        for (Element productRow : productRows) {

            Element productMain = productRow.getElementsByClass("product-main").first();
            Element productSales = productRow.getElementsByClass("product-sales").first();

            Element productHeader = productMain.getElementsByClass("js-save-keyword").first();
            String title = productHeader.text();
            String url = EURO_AGD_ROOT + productHeader.attr("href");
            Element priceStringElement = productSales.getElementsByClass("price-normal").first();
            if (priceStringElement == null)
                continue;

            String priceString = priceStringElement.text();

            try {
                double price = Double.parseDouble(priceString.replaceAll("[zł/ ]", ""));
                if (!title.isEmpty() && !url.isEmpty())
                    analyzerResults.add(ImmutableAnalyzerResult.of(title, url, price));
            } catch (NumberFormatException e) {
            }
        }

        return analyzerResults;
    }

    @Override
    public String getRoot() {
        return EURO_AGD_ROOT;
    }

    @Override
    public String getSpec() {
        return SPEC;
    }
}
