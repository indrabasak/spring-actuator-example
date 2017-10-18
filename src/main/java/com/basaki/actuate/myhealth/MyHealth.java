package com.basaki.actuate.myhealth;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;
import lombok.Data;

/**
 * {@code MyHealth} is the model containing information exposed bt /myhealth
 * endpoint.
 * <p>
 *
 * @author Indra Basak
 * @since 10/17/17
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
