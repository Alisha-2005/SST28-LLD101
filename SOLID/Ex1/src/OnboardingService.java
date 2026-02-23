import java.util.*;

public class OnboardingService {
    private final StudentRepository repo;
    private final Validator validator;

    public OnboardingService(StudentRepository repo, Validator validator) {
        this.repo = repo;
        this.validator = validator;
    }

    // Processes a parsed input and returns a RegistrationResult; does not perform console IO.
    public RegistrationResult register(StudentInput input) {
        List<String> errors = validator.validate(input);

        if (!errors.isEmpty()) {
            return new RegistrationResult(false, null, errors, repo.count());
        }

        String id = IdUtil.nextStudentId(repo.count());
        StudentRecord rec = new StudentRecord(id, input.name, input.email, input.phone, input.program);
        repo.save(rec);
        return new RegistrationResult(true, rec, Collections.emptyList(), repo.count());
    }
}
