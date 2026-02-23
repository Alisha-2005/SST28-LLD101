public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit, NotificationValidator validator) { super(audit, validator); }

    @Override
    protected void doSend(Notification n) {
        // SMS channel ignores subject by design; delivery happens here.
        System.out.println("SMS -> to=" + n.phone + " body=" + n.body);
        audit.add("sms sent");
    }
}
