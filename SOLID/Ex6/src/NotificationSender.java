public abstract class NotificationSender {
    protected final AuditLog audit;
    private final NotificationValidator validator;

    protected NotificationSender(AuditLog audit, NotificationValidator validator) {
        this.audit = audit;
        this.validator = validator;
    }

    // Template method: validation happens in base, delivery in subclass.
    public final void send(Notification n) {
        validator.validate(n);
        doSend(n);
    }

    // Subclasses implement delivery without performing precondition checks.
    protected abstract void doSend(Notification n);
}

