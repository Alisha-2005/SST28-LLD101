package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * REFACTORED: Proper, thread-safe, lazy-initialized Singleton.
 *
 * - Private constructor prevents external instantiation
 * - Static holder pattern for thread-safe lazy initialization
 * - Reflection protection: constructor throws if instance already exists
 * - Serialization protection: readResolve() returns the singleton instance
 */
public final class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // Flag to detect reflection-based construction attempts
    private static volatile boolean instanceCreated = false;

    private final Map<String, Long> counters = new HashMap<>();

    /**
     * Private constructor.
     * Throws exception if called via reflection after singleton is already created.
     */
    private MetricsRegistry() {
        synchronized (MetricsRegistry.class) {
            if (instanceCreated) {
                throw new IllegalStateException("MetricsRegistry is a singleton. Use getInstance() instead.");
            }
            instanceCreated = true;
        }
    }

    /**
     * Thread-safe, lazy-initialized singleton using static holder pattern.
     */
    private static final class Holder {
        private static final MetricsRegistry INSTANCE = new MetricsRegistry();
    }

    public static MetricsRegistry getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Preserve singleton on serialization/deserialization.
     * When an instance is deserialized, return the singleton instead.
     */
    @Serial
    private Object readResolve() {
        return getInstance();
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }
}
