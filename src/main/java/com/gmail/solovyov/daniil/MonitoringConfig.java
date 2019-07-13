package com.gmail.solovyov.daniil;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
//@ComponentScan(basePackages = {"com.gmail.solovyov.daniil.monitoring.*"})
//@PropertySource("monitoring.properties")
@EnableConfigurationProperties(MonitoringProperties.class)
public class MonitoringConfig {

//    @Autowired
//    private Environment environment;

    @Bean
    public DataSource getDataSource(MonitoringProperties monitoringProperties) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(monitoringProperties.getUrl());
        ds.setUsername(monitoringProperties.getUsername());
        ds.setPassword(monitoringProperties.getPassword());
        return ds;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
