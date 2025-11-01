package myParDo;
import org.apache.beam.sdk.transforms.DoFn;
// import org.apache.beam.sdk.transforms.DoFn.ProcessContext;

public class extractFirstCol extends DoFn<String, String> {
    @ProcessElement
    public void processElement(ProcessContext c) {
        String line = c.element();
        String[] columns = line.split(",");
        if (columns.length > 0) {
            c.output(columns[0]);
        }
    }
}
