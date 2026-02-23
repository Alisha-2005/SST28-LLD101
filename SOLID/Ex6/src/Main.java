public class Main {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");
        AuditLog audit = new AuditLog();

        Notification n = new Notification("Welcome", "Hello and welcome to SST!", "riya@sst.edu", "9876543210");

        NotificationValidator noop = new NoOpValidator();
        NotificationValidator waValidator = new WhatsAppValidator();

        NotificationSender email = new EmailSender(audit, noop);
        NotificationSender sms = new SmsSender(audit, noop);
        NotificationSender wa = new WhatsAppSender(audit, waValidator);

        email.send(n);
        sms.send(n);
        try {
            wa.send(n);
        } catch (RuntimeException ex) {
            System.out.println("WA ERROR: " + ex.getMessage());
            audit.add("WA failed");
        }

        System.out.println("AUDIT entries=" + audit.size());
    }
}
