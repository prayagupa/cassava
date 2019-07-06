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

        var success = FileWriter.openStream("/tmp/abc.csv")
                .thenCompose($ -> $.append(encoded))
                .thenCompose($ -> $.flushStream())
                .thenCompose($ -> $.closeStream());
        success.get();

        //failure

        var failure = FileWriter.openStream("/dont_exist/abc.csv")
                .thenCompose($ -> $.append(encoded))
                .thenCompose($ -> $.flushStream())
                .thenCompose($ -> $.closeStream());

        failure.handle((s, f) -> {
            if (f != null)
                System.out.println("error: " + f);
            return s;
        });

    }
}
