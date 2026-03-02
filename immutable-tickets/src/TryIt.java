import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * REFACTORED: Demo showing immutable ticket design.
 *
 * - Direct mutation does not compile (no setters)
 * - External modifications to tags do not affect the ticket
 * - Service "updates" return NEW ticket instances
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        // Create a ticket using the Builder
        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);
        System.out.println("  Identity hashcode: " + System.identityHashCode(t));

        // "Update" via service creates a NEW ticket
        IncidentTicket assigned = service.assign(t, "agent@example.com");
        System.out.println("\nAfter assigning to agent (new instance): " + assigned);
        System.out.println("  New identity hashcode: " + System.identityHashCode(assigned));
        System.out.println("  Original unchanged: " + t);

        // "Escalate" creates ANOTHER new instance
        IncidentTicket escalated = service.escalateToCritical(assigned);
        System.out.println("\nAfter escalation (new instance): " + escalated);
        System.out.println("  New identity hashcode: " + System.identityHashCode(escalated));
        System.out.println("  Original assigned unchanged: " + assigned);

        // Attempt to modify tags from outside - no effect
        System.out.println("\nTesting external tag list mutation:");
        List<String> tags = escalated.getTags();
        System.out.println("  Tags before: " + tags);
        try {
            tags.add("HACKED_FROM_OUTSIDE");
            System.out.println("  ERROR: Should not be able to modify tags list!");
        } catch (UnsupportedOperationException e) {
            System.out.println("  Good! Got exception: " + e.getClass().getSimpleName());
            System.out.println("  Tags after attempted modification: " + escalated.getTags());
        }

        // Building a fresh ticket with full options
        System.out.println("\n\nBuilding a fresh ticket with full options:");
        IncidentTicket custom = new IncidentTicket.Builder()
                .id("TCK-2002")
                .reporterEmail("customer@example.com")
                .title("Database connection timeout")
                .description("Connection pool exhausted")
                .priority("CRITICAL")
                .assigneeEmail("dba@example.com")
                .slaMinutes(60)
                .source("WEBHOOK")
                .customerVisible(true)
                .addTag("DATABASE")
                .addTag("URGENT")
                .build();
        System.out.println("  Created: " + custom);
    }
}
