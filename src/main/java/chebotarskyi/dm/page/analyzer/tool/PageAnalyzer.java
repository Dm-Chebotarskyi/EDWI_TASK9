package chebotarskyi.dm.page.analyzer.tool;

import org.jsoup.nodes.Element;

import java.util.List;

interface PageAnalyzer {

    List<AnalyzerResult> analyze(Element body);

    String getRoot();

    String getSpec();

}
