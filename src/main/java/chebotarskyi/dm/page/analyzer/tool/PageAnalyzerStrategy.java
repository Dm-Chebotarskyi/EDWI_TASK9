package chebotarskyi.dm.page.analyzer.tool;

import chebotarskyi.dm.exception.NotSupportedShopException;
import org.jsoup.nodes.Element;

import java.util.List;

import static chebotarskyi.dm.page.analyzer.tool.EuroPageAnalyzer.EURO_AGD_ROOT;
import static chebotarskyi.dm.page.analyzer.tool.MediaExpertPageAnalyzer.MEDIAEXPERT_ROOT;
import static chebotarskyi.dm.page.analyzer.tool.MediaMarktPageAnalyzer.MEDIAMARKT_ROOT;
import static chebotarskyi.dm.page.analyzer.tool.SaturnPageAnalyzer.SATURN_ROOT;
import static chebotarskyi.dm.page.analyzer.tool.XKomPageAnalyzer.XKOM_ROOT;

public class PageAnalyzerStrategy implements PageAnalyzer{
    private final PageAnalyzer analyzer;

    public PageAnalyzerStrategy(String url) throws NotSupportedShopException {
        if (url.contains(EURO_AGD_ROOT)) {
            analyzer = new EuroPageAnalyzer();
        } else if (url.contains(MEDIAMARKT_ROOT)) {
            analyzer = new MediaMarktPageAnalyzer();
        } else if (url.contains(MEDIAEXPERT_ROOT)) {
            analyzer = new MediaExpertPageAnalyzer();
        } else if (url.contains(SATURN_ROOT)) {
            analyzer = new SaturnPageAnalyzer();
        } else if (url.contains(XKOM_ROOT)) {
            analyzer = new XKomPageAnalyzer();
        } else {
            throw new NotSupportedShopException(String.format("Shop under {} is not supported", url));
        }
    }

    @Override
    public List<AnalyzerResult> analyze(Element body) {
        return analyzer.analyze(body);
    }

    @Override
    public String getRoot() {
        return analyzer.getRoot();
    }

    @Override
    public String getSpec() {
        return analyzer.getSpec();
    }
}
