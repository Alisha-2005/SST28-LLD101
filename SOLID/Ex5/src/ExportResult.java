public class ExportResult {
    public final String contentType;
    public final byte[] bytes;
    public final String error; // null when export succeeded

    public ExportResult(String contentType, byte[] bytes) {
        this(contentType, bytes, null);
    }

    public ExportResult(String contentType, byte[] bytes, String error) {
        this.contentType = contentType;
        this.bytes = bytes == null ? new byte[0] : bytes;
        this.error = error;
    }
}
