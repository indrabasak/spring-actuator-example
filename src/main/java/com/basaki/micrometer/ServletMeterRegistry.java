package com.basaki.micrometer;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.dropwizard.DropwizardMeterRegistry;
import io.micrometer.core.instrument.util.HierarchicalNameMapper;

public class ServletMeterRegistry extends DropwizardMeterRegistry {

    public ServletMeterRegistry() {
        this(HierarchicalNameMapper.DEFAULT, Clock.SYSTEM);
    }

    public ServletMeterRegistry(HierarchicalNameMapper nameMapper,
            Clock clock) {
        super(nameMapper, clock);
    }
}
