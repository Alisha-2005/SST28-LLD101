public interface EligibilityRule {
    // returns a failure reason string if the rule fails, otherwise null
    String check(StudentProfile s);
}
