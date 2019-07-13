package app;

import app.event.EventRepository;
import app.metric.Metric;
import app.metric.MetricRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.Timestamp;
import java.util.List;

@SpringBootApplication
public class Starter {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Starter.class, args);
        MetricRepository bean = run.getBean(MetricRepository.class);

        Metric metric = new Metric();
        metric.setEventName("Test name");
        metric.setValue(3000);
        metric.setEventTimestamp(new Timestamp(System.currentTimeMillis()));
        metric.setParameters("MyParams");

        bean.save(metric);

        bean.findAll().forEach(System.out::println);

        EventRepository eventRepository = run.getBean(EventRepository.class);

        eventRepository.save("Test namengnffg");

        System.out.println();
    }
}
