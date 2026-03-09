package com.example.payments;

import java.util.Objects;

/**
 * Adapter for SafeCash SDK exposing a {@link PaymentGateway} interface.
 */
public class SafeCashAdapter implements PaymentGateway {
    private final SafeCashClient client;

    public SafeCashAdapter(SafeCashClient client) {
        this.client = Objects.requireNonNull(client, "client");
    }

    @Override
    public String charge(String customerId, int amountCents) {
        Objects.requireNonNull(customerId, "customerId");
        SafeCashPayment p = client.createPayment(amountCents, customerId);
        return p.confirm();
    }
}
