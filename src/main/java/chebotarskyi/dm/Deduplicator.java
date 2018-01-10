package chebotarskyi.dm;

import java.util.HashSet;
import java.util.Set;

public class Deduplicator {

    private final static Set<String> titles = new HashSet<>();

    public static boolean isOk(String title) {
        return titles.add(title);
    }
}
