package com.gmail.solovyov.daniil.monitoring;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "monitoring")
@Getter
@Setter
public class MonitoringProperties {
    private Integer queueSize;
    private Integer batchSize;
    private Integer batchThreshold;
}
