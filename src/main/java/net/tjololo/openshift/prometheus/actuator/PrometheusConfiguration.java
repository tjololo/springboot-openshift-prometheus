package net.tjololo.openshift.prometheus.actuator;

import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.MemoryPoolsExports;
import io.prometheus.client.hotspot.StandardExports;
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
    @Bean
    @ConditionalOnMissingBean
    CollectorRegistry metricRegistry() {
        return CollectorRegistry.defaultRegistry;
    }

    @Bean
    ExporterRegister exporterRegister() {
        List<Collector> collectors = new ArrayList<>();
        collectors.add(new StandardExports());
        collectors.add(new MemoryPoolsExports());
        ExporterRegister register = new ExporterRegister(collectors);
        return register;
    }

    @Bean
    ServletRegistrationBean registerPrometheusExporterServlet(CollectorRegistry metricRegistry) {
        return new ServletRegistrationBean(new MetricsServlet(metricRegistry), "/prometheus");
    }

}
