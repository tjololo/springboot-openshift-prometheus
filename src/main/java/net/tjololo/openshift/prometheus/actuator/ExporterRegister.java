package net.tjololo.openshift.prometheus.actuator;

import io.prometheus.client.Collector;

import java.util.List;

/**
 * Created by veg on 10.02.2017.
 */
public class ExporterRegister {
    private List<Collector> collectors;

    public ExporterRegister(List<Collector> collectors) {
        for (Collector collector : collectors) {
            collector.register();
        }
        this.collectors = collectors;
    }

    public List<Collector> getCollectors() {
        return collectors;
    }

}
