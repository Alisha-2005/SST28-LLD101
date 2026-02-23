public class TestRawParser {
    public static void main(String[] args) {
        String raw = "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE";
        StudentInput in = RawParser.parse(raw);
        int failed = 0;
        if (!"Riya".equals(in.name)) { System.out.println("FAIL: name"); failed++; }
        if (!"riya@sst.edu".equals(in.email)) { System.out.println("FAIL: email"); failed++; }
        if (!"9876543210".equals(in.phone)) { System.out.println("FAIL: phone"); failed++; }
        if (!"CSE".equals(in.program)) { System.out.println("FAIL: program"); failed++; }

        if (failed == 0) System.out.println("TestRawParser: PASS"); else System.out.println("TestRawParser: FAIL (" + failed + ")");
    }
}
