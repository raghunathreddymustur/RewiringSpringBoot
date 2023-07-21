package com.raghu.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class AlwaysDownHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        return Health.down()
                .withDetail("system-available", false)
                .build();
    }
}
