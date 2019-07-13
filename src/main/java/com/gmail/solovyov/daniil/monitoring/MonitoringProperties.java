package com.gmail.solovyov.daniil.monitoring;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "monitoring.database")
@Getter
@Setter
public class MonitoringProperties {
    private String url;
    private String username;
    private String password;
}
