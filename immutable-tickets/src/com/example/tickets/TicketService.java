package com.example.tickets;

import java.util.ArrayList;
import java.util.List;

/**
 * REFACTORED: Service layer that creates and "updates" immutable tickets.
 *
 * - Uses Builder for creation and updates
 * - No more mutations after creation
 * - Validation is centralized in Builder.build()
 * - "Updates" return new ticket instances
 */
public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        // All validation is now centralized in Builder.build()
        return new IncidentTicket.Builder()
                .id(id)
                .reporterEmail(reporterEmail)
                .title(title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .addTag("NEW")
                .build();
    }

    /**
     * Creates a new ticket instance with CRITICAL priority.
     * Does not mutate the original ticket.
     */
    public IncidentTicket escalateToCritical(IncidentTicket t) {
        // Create a new ticket with escalated priority
        return t.toBuilder()
                .priority("CRITICAL")
                .addTag("ESCALATED")
                .build();
    }

    /**
     * Creates a new ticket instance with the assignee set.
     * Does not mutate the original ticket.
     */
    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        // Validation happens in Builder.build()
        return t.toBuilder()
                .assigneeEmail(assigneeEmail)
                .build();
    }
}
