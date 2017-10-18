package com.basaki.actuate.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * {@code HealthCheck} is an example of custom health check.
 * endpoint.
 * <p>
 *
 * @author Indra Basak
 * @since 10/18/17
 */
@Component
public class HealthCheck implements HealthIndicator {

    @Override
    public Health health() {
        return Health.up().withDetail("MyStatus", "is happy").build();
    }
}
