public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit, NotificationValidator validator) { super(audit, validator); }

    @Override
    protected void doSend(Notification n) {
        // Do not perform precondition tightening or silent truncation here.
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + n.body);
        audit.add("email sent");
    }
}
