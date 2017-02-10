package net.tjololo.openshift.prometheus.actuator;

import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.MemoryPoolsExports;
import io.prometheus.client.hotspot.StandardExports;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veg on 10.02.2017.
 */
@Configuration
@ConditionalOnClass(CollectorRegistry.class)
public class PrometheusConfiguration {
    Logger log = LoggerFactory.getLogger(PrometheusConfiguration.class);
    @Autowired
    private List<HealthIndicator> healthChecs;

    @Bean
    @ConditionalOnMissingBean
    CollectorRegistry metricRegistry() {
        CollectorRegistry defaultRegistry = CollectorRegistry.defaultRegistry;
        return defaultRegistry;
    }

    @Bean
    ExporterRegister exporterRegister() {
        List<Collector> collectors = new ArrayList<>();
        collectors.add(new StandardExports());
        collectors.add(new MemoryPoolsExports());
        collectors.add(new HeathGauge(healthChecs));
        ExporterRegister register = new ExporterRegister(collectors);
        return register;
    }

    @Bean
    ServletRegistrationBean registerPrometheusExporterServlet(CollectorRegistry metricRegistry) {
        return new ServletRegistrationBean(new MetricsServlet(metricRegistry), "/prometheus");
    }

}
