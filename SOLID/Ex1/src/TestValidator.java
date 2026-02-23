public class TestValidator {
    public static void main(String[] args) {
        Validator v = new Validator();
        int failed = 0;

        StudentInput good = new StudentInput("Riya", "riya@sst.edu", "9876543210", "CSE");
        if (!v.validate(good).isEmpty()) { System.out.println("FAIL: good input reported errors"); failed++; }

        StudentInput noName = new StudentInput("", "a@b.com", "1234", "CSE");
        if (!v.validate(noName).contains("name is required")) { System.out.println("FAIL: missing name not detected"); failed++; }

        StudentInput badEmail = new StudentInput("X", "no-at", "1234", "CSE");
        if (!v.validate(badEmail).contains("email is invalid")) { System.out.println("FAIL: bad email not detected"); failed++; }

        StudentInput badPhone = new StudentInput("X", "a@b.com", "12x4", "CSE");
        if (!v.validate(badPhone).contains("phone is invalid")) { System.out.println("FAIL: bad phone not detected"); failed++; }

        StudentInput badProgram = new StudentInput("X", "a@b.com", "1234", "BIO");
        if (!v.validate(badProgram).contains("program is invalid")) { System.out.println("FAIL: bad program not detected"); failed++; }

        if (failed == 0) System.out.println("TestValidator: PASS"); else System.out.println("TestValidator: FAIL (" + failed + ")");
    }
}
