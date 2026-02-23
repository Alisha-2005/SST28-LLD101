import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        // Return a graceful error result instead of throwing; preserves substitutability.
        if (req == null) return new ExportResult("application/pdf", new byte[0], "request is null");
        if (req.body != null && req.body.length() > 20) {
            return new ExportResult("application/pdf", new byte[0], "PDF cannot handle content > 20 chars");
        }
        String fakePdf = "PDF(" + req.title + "):" + req.body;
        return new ExportResult("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}
