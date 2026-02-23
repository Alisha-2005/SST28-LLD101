public class NoOpValidator implements NotificationValidator {
    @Override
    public void validate(Notification n) { /* always valid */ }
}
