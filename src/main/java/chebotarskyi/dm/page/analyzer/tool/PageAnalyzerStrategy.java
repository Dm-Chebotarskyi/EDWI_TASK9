package chebotarskyi.dm.page.analyzer.tool;

import org.jsoup.nodes.Element;

import java.util.List;

import static chebotarskyi.dm.page.analyzer.tool.EuroPageAnalyzer.EURO_AGD_ROOT;
import static chebotarskyi.dm.page.analyzer.tool.MediaExpertPageAnalyzer.MEDIAEXPERT_ROOT;
import static chebotarskyi.dm.page.analyzer.tool.MediaMarktPageAnalyzer.MEDIAMARKT_ROOT;

public class PageAnalyzerStrategy implements PageAnalyzer{
    private final PageAnalyzer analyzer;

    public PageAnalyzerStrategy(String url) {
        if (url.contains(EURO_AGD_ROOT)) {
            analyzer = new EuroPageAnalyzer();
        } else if (url.contains(MEDIAMARKT_ROOT)) {
            analyzer = new MediaMarktPageAnalyzer();
        } else if (url.contains(MEDIAEXPERT_ROOT)) {
            analyzer = new MediaExpertPageAnalyzer();
        } else{
            throw new RuntimeException("Unsupported internet shop");
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
