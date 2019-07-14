package com.gmail.solovyov.daniil.monitoring;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"com.gmail.solovyov.daniil.*"})
@EnableConfigurationProperties(MonitoringProperties.class)
public class MonitoringConfig {
}
