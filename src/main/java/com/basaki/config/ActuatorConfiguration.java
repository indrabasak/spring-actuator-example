package com.basaki.config;

import com.basaki.actuator.MyHealthEndpoint;
import com.basaki.actuator.MyHealthWebEndpointExtension;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@code ActuatorConfiguration} is the configuration for setting up new
 * actuator endpoints.
 * <p/>
 *
 * @author Indra Basak
 * @since 10/17/17
 */
@Configuration
public class ActuatorConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnEnabledEndpoint
    public MyHealthEndpoint myHealthEndpoint() {
        return new MyHealthEndpoint();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnEnabledEndpoint
    @ConditionalOnBean({MyHealthEndpoint.class})
    public MyHealthWebEndpointExtension myHealthWebEndpointExtension(
            MyHealthEndpoint delegate) {
        return new MyHealthWebEndpointExtension(delegate);
    }
}
