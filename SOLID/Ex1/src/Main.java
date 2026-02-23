public class Main {
    public static void main(String[] args) {
        System.out.println("=== Student Onboarding ===");
        FakeDb db = new FakeDb();
        Validator validator = new Validator();
        OnboardingService svc = new OnboardingService(db, validator);

        String raw = "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE";
        System.out.println("INPUT: " + raw);

        StudentInput input = RawParser.parse(raw);
        RegistrationResult res = svc.register(input);

        if (!res.success) {
            System.out.println("ERROR: cannot register");
            for (String e : res.errors) System.out.println("- " + e);
        } else {
            System.out.println("OK: created student " + res.record.id);
            System.out.println("Saved. Total students: " + res.totalCount);
            System.out.println("CONFIRMATION:");
            System.out.println(res.record);
        }

        System.out.println();
        System.out.println("-- DB DUMP --");
        System.out.print(TextTable.render3(db));
    }
}
