import java.util.*;

public class RegistrationResult {
    public final boolean success;
    public final StudentRecord record; // null when failure
    public final List<String> errors; // empty when success
    public final int totalCount; // repository count after save (if success)

    public RegistrationResult(boolean success, StudentRecord record, List<String> errors, int totalCount) {
        this.success = success;
        this.record = record;
        this.errors = errors == null ? Collections.emptyList() : Collections.unmodifiableList(errors);
        this.totalCount = totalCount;
    }
}
