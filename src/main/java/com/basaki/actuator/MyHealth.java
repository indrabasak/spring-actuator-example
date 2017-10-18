package com.basaki.actuator;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;

/**
 * {@code MyHealth} is the model containing information exposed bt /myhealth endpoint.
 * <p>
 *
 * @author Christian Dupuis
 * @author Phillip Webb
 * @since 1.1.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MyHealth {

    private Map<String, Object> details;

    @JsonAnyGetter
    public Map<String, Object> getDetails() {
        return this.details;
    }
}
