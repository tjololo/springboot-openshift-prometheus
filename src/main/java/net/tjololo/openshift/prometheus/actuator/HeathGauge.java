package net.tjololo.openshift.prometheus.actuator;

import io.prometheus.client.Collector;
import io.prometheus.client.GaugeMetricFamily;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by veg on 10.02.2017.
 */
public class HeathGauge extends Collector {
    private List<HealthIndicator> healthChecs;

    public HeathGauge(List<HealthIndicator> healthChecs) {
        this.healthChecs = healthChecs;
    }

    void addHealthUp(List<MetricFamilySamples> sampleFamilies) {
        GaugeMetricFamily health = new GaugeMetricFamily("spring_boot_up", "Health status from spring boot actuator", Collections.singletonList("health"));
        health.addMetric(Collections.singletonList("all"), getHealth());
        sampleFamilies.add(health);
    }

    @Override
    public List<MetricFamilySamples> collect() {
        List<MetricFamilySamples> metricFamilySamples = new ArrayList<>();
        addHealthUp(metricFamilySamples);
        return metricFamilySamples;
    }

    private double getHealth() {
        double health = 1;
        for (HealthIndicator healthChec : healthChecs) {
            if (healthChec.health().getStatus() != Status.UP) {
                health = 0;
                break;
            }
        }
        return health;
    }
}
