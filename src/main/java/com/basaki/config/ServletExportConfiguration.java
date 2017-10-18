package com.basaki.config;


import com.basaki.micrometer.ServletMeterRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.servlets.AdminServlet;
import com.codahale.metrics.servlets.HealthCheckServlet;
import com.codahale.metrics.servlets.MetricsServlet;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.util.HierarchicalNameMapper;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.export.MetricsExporter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConditionalOnProperty(name = {"metrics.report.http"})
@Slf4j
public class ServletExportConfiguration {

    @Value("${metrics.report.http.url:/metrics/*}")
    private String urlMappings;

    /**
     * Registers the admin servlet to make all the metrics available by HTTP at
     * the url http://&lt;host&gt;:&lt;port&gt;/metrics
     *
     * @return the admin servlet responsible for making the Dropwizard metrics
     * available
     */
    @Bean(name = "metricsServlet")
    public ServletRegistrationBean registerHttpReporter() {
        return new ServletRegistrationBean(new AdminServlet(), urlMappings) {
            @Override
            public String getServletName() {
                log.debug("Registering metrics admin servlet...");
                return "MetricsAdmin";
            }
        };
    }

    /**
     * Registers a health check registry which is needed by
     * <tt>HealthCheckServlet</tt>. The health check servlet is instantiated by
     * the admin servlet.
     *
     * @return a servlet listener which has the Dropwizard's health check
     * registry. The registry contains all the metrics.
     */
    @Bean
    public ServletListenerRegistrationBean<HealthCheckServlet.ContextListener> registerHealthCheckRegistry() {
        final HealthCheckRegistry healthCheckRegistry =
                new HealthCheckRegistry();
        final HealthCheckServlet.ContextListener listener =
                new HealthCheckServlet.ContextListener() {
                    @Override
                    protected HealthCheckRegistry getHealthCheckRegistry() {
                        log.debug("Registering Health Check Registry...");
                        return healthCheckRegistry;
                    }
                };
        return new ServletListenerRegistrationBean<>(listener);
    }

    @Bean
    public ServletContextListener getContextListener(
            @Qualifier("servlet-registry") MetricsExporter exporter) {
        return new ServletContextListener() {

            @Override
            public void contextInitialized(ServletContextEvent sce) {
                log.debug("Registering Metrics Registry...");
                sce.getServletContext().setAttribute(
                        MetricsServlet.METRICS_REGISTRY,
                        new ServletMeterRegistry().getDropwizardRegistry());
            }

            @Override
            public void contextDestroyed(ServletContextEvent sc) {
                // Do nothing
            }
        };
    }

    @Bean(name = "servlet-registry")
    //@ConditionalOnProperty(value = "spring.metrics.jmx.enabled", matchIfMissing = true)
    public MetricsExporter sevletExporter(HierarchicalNameMapper nameMapper,
            Clock clock) {
        return () -> new ServletMeterRegistry(nameMapper, clock);
    }

    @Bean
    @ConditionalOnMissingBean
    public Clock micrometerClock() {
        return Clock.SYSTEM;
    }

    @Bean
    @ConditionalOnMissingBean
    public HierarchicalNameMapper hierarchicalNameMapper() {
        return HierarchicalNameMapper.DEFAULT;
    }
}