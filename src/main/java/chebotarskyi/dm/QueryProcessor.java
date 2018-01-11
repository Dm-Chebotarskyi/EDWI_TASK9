package chebotarskyi.dm;

import chebotarskyi.dm.exception.NotFoundException;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class QueryProcessor {

    private final StandardAnalyzer analyzer;
    private FSDirectory index;
    private final int buffer = 200;

    public QueryProcessor() {
        analyzer = new StandardAnalyzer();
        try {
            index = FSDirectory.open(Paths.get(IndexUtils.INDEX_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int hitsPerPage = 10;

    public String processQuery(String querystr) throws ParseException, NotFoundException {

        querystr = querystr.replaceAll("[/ ]", " AND ");

        Query q = new QueryParser("title", analyzer).parse(querystr);

        StringBuilder builder = new StringBuilder();
        try {
            IndexReader reader = DirectoryReader.open(index);
            IndexSearcher searcher = new IndexSearcher(reader);
            TopDocs docs = searcher.search(q, hitsPerPage);
            ScoreDoc[] hits = docs.scoreDocs;

            System.out.println("Found " + hits.length + " hits.");
            if (hits.length <= 0) {
                throw new NotFoundException();
            }

            List<Document> documentList = Arrays.stream(hits).map(hit -> {
                try {
                    return searcher.doc(hit.doc);
                } catch (IOException e) {
                    return null;
                }
            }).collect(Collectors.toList());

            documentList.sort(new DocsComparator());

            for (int i=0; i<documentList.size() && i < 3; ++i) {
                builder.append(String.valueOf(i + 1))
                        .append(".\t")
                        .append(documentList.get(i).get("title"))
                        .append("\n\t")
                        .append(documentList.get(i).get("price"))
                        .append("\n\t")
                        .append(documentList.get(i).get("url"))
                        .append("\n\t")
                        .append(documentList.get(i).get("availability"))
                        .append("\n\n-----------------------------------\n");
            }
        } catch (IOException e){

        }
        return builder.toString();
    }

    public void closeIndex() {
        try {
            index.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class DocsComparator implements Comparator<Document> {
        @Override
        public int compare(Document d1, Document d2) {
            Double price1 = Double.parseDouble(d1.get("price"));
            Double price2 = Double.parseDouble(d2.get("price"));
            return price1.compareTo(price2);
        }
    }
}
