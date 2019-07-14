package integration;

import com.gmail.solovyov.daniil.domain.Metric;
import com.gmail.solovyov.daniil.domain.Monitoring;
import com.gmail.solovyov.daniil.repository.MonitoringRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

public class MonitoringRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private MonitoringRepository monitoringRepository;

    @Test
    public void shouldReturnOneEntityWithoutEmptyFields() {
        String eventName = "Name";
        Integer eventId = eventRepository.create(eventName);

        Metric metric = new Metric();
        metric.setEventId(eventId);
        metric.setValue(300L);
        metric.setParameters("Parameters");
        metric.setEventTimestamp(new Timestamp(System.currentTimeMillis()));

        metricRepository.save(metric);

        List<Monitoring> result = monitoringRepository.findAll();
        Assert.assertEquals(1, result.size());

        Monitoring monitoring = result.iterator().next();
        Assert.assertNotNull(monitoring.getId());
        Assert.assertEquals(eventName, monitoring.getEventName());
        Assert.assertEquals(metric.getValue(), monitoring.getValue());
        Assert.assertEquals(metric.getParameters(), monitoring.getParameters());
        Assert.assertEquals(metric.getEventTimestamp(), monitoring.getEventTimestamp());
    }
}
