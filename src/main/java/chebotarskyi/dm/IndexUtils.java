package chebotarskyi.dm;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

public class IndexUtils {

    public static final String INDEX_PATH = "/home/dima/index_task8.lucene";
    private final Supplier<IndexWriter> w;

    IndexUtils() {
        w = Suppliers.memoize(this::getWriter);
    }

    private IndexWriter getWriter() {
        IndexWriter w = null;
        try {
            StandardAnalyzer analyzer = new StandardAnalyzer();
            FSDirectory index = FSDirectory.open(Paths.get(INDEX_PATH));
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            w = new IndexWriter(index, config);
        } catch (IOException e) {
            System.out.println("Cannot create writer: " + e.getMessage());
        }
        return w;
    }

    public void addDoc(String title, String url, double price, String availability) throws IOException {

        if (!Deduplicator.isOk(title))
            return;

        Document doc = new Document();
        doc.add(new TextField("title", title, Field.Store.YES));
        doc.add(new StringField("url", url, Field.Store.YES));
        doc.add(new StringField("price", String.valueOf(price), Field.Store.YES));
        doc.add(new StringField("availability", availability, Field.Store.YES));
        w.get().addDocument(doc);
    }

    public void closeWriter() {
        try {
            w.get().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 

}
