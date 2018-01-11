package chebotarskyi.dm.page.analyzer.tool;

import org.immutables.value.Value;

@Value.Immutable
public interface AnalyzerResult {

    @Value.Parameter
    String title();

    @Value.Parameter
    String url();

    @Value.Parameter
    double price();

    @Value.Parameter
    String availibility();
}
