import com.prayagupd.cassava.data.Csv;
import com.prayagupd.cassava.data.FileWriter;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static com.prayagupd.cassava.data.Csv.encode;

public class CsvSpecs {
    public static void main(String[] args) throws FileNotFoundException, ExecutionException, InterruptedException {
        var encoded = encode(Arrays.asList(
                new Csv.Tuple<String, Integer>("upd", 1),
                new Csv.Tuple<String, Integer>("youpeed", 2)
        ));

        System.out.println(encoded);

        var f = FileWriter.openStream("/tmp/abc.csv")
                .append(encoded)
                .thenCompose($ -> $.flushStream())
                .thenCompose($ -> $.closeStream());

        f.get();
    }
}
