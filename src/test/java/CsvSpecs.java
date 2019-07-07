import com.prayagupd.cassava.data.Csv;
import com.prayagupd.cassava.data.FileWriter;

import java.util.concurrent.ExecutionException;

public class CsvSpecs {

    public static class Audience {
        public String platform;
        public String location;
        public Integer deviceId;
        public Double dwell;

        public Audience() {

        }

        public Audience(String platform,
                        String location,
                        Integer deviceId,
                        Double dwell) {
            this.platform = platform;
            this.location = location;
            this.deviceId = deviceId;
            this.dwell = dwell;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //
        var encoded = Csv.encode(new Audience(
                "android",
                "SEA",
                1,
                10.0
        ));

        System.out.println(encoded);

        //encode with header
        var encodeWithHeader = Csv.encodeWithHeader(new Audience(
                "android",
                "Jumla",
                1,
                10.9
        ));

        System.out.println(encodeWithHeader);

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

        //failure.get();

        //decode
        System.out.println("decoding");
        var audience = Csv.decode("android,Jumla,1,10.9", Audience.class);
        System.out.println(audience.platform);
        System.out.println(audience.location);
        System.out.println(audience.deviceId);
        System.out.println(audience.dwell);
    }
}
