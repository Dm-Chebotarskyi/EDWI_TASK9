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

            Element productMain = productRow.getElementsByClass("m-product_content").first();
            Element productSales = productRow.getElementsByClass("m-product_options").first();

            Element productHeader = productMain.getElementsByClass("js-gtmEvent_click").first();
            String title = productHeader.text();
            String url = MEDIAEXPERT_ROOT + productHeader.attr("href");

            //TODO : Fix NullPointerException on getting price

            Element priceStringElement = productSales.getElementsByClass("price").first();
            if (priceStringElement == null)
                continue;

            String priceString = priceStringElement.text();

            try {
                double price = Double.parseDouble(priceString.replaceAll("[-,/ ]", ""));
                if (!title.isEmpty() && !url.isEmpty())
                    analyzerResults.add(ImmutableAnalyzerResult.of(title, url, price));
            } catch (NumberFormatException e) {
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
