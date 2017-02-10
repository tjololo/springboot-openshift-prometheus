package net.tjololo.openshift.prometheus.actuator;

import net.tjololo.openshift.prometheus.rest.MyEndpoint;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Created by veg on 26.10.2016.
 */
@Component
public class CustomHelthCheck implements HealthIndicator {

    @Override
    public Health health() {
        if(MyEndpoint.ok) {
            return Health.up().build();
        } else {
            return Health.down().build();
        }
    }
}
