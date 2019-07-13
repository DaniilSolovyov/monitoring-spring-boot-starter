package config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"collaborator"})
//@PropertySource("monitoring-test.properties")
//@Import(MonitoringConfig.class)
public class TestConfig {

//    @Bean
//    public DataSource getDataSource() {
//
//        return null;
//    }

//    @Bean
//    public TestBean getTestBean(){
//        return new TestBean();
//    }

//    @Bean
//    public JdbcTemplate getJdbcTemplate(){
//        return new JdbcTemplate(getDataSource());
//    }
}
