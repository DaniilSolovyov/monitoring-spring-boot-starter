package com.gmail.solovyov.daniil.monitoring;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.gmail.solovyov.daniil.*"})
//@PropertySource("monitoring.properties")
@EnableConfigurationProperties(MonitoringProperties.class)
public class MonitoringConfig {

    public static final String MONITORING = "monitoring";

//    @Autowired
//    private Environment environment;

//    @Bean(name ="monitoring")
//    public DataSource getDataSource(MonitoringProperties monitoringProperties) {
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setUrl(monitoringProperties.getUrl());
//        ds.setUsername(monitoringProperties.getUsername());
//        ds.setPassword(monitoringProperties.getPassword());
//        return ds;
//    }

    @Bean(name = MONITORING)
    public JdbcTemplate getJdbcTemplate(MonitoringProperties monitoringProperties) {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(monitoringProperties.getUrl());
        hikariConfig.setUsername(monitoringProperties.getUsername());
        hikariConfig.setPassword(monitoringProperties.getPassword());

//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setUrl(monitoringProperties.getUrl());
//        ds.setUsername(monitoringProperties.getUsername());
//        ds.setPassword(monitoringProperties.getPassword());
        DataSource dataSource = new HikariDataSource(hikariConfig);
        return new JdbcTemplate(dataSource);
    }
}
