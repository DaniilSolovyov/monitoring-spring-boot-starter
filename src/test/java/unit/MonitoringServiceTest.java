package unit;

import com.gmail.solovyov.daniil.domain.Metric;
import com.gmail.solovyov.daniil.repository.EventRepository;
import com.gmail.solovyov.daniil.repository.MetricRepository;
import com.gmail.solovyov.daniil.service.MonitoringService;
import com.gmail.solovyov.daniil.service.MonitoringServiceImpl;
import org.junit.Test;

import java.sql.Timestamp;

import static org.mockito.Mockito.*;

public class MonitoringServiceTest {

    private EventRepository eventRepository = mock(EventRepository.class);
    private MetricRepository metricRepository = mock(MetricRepository.class);
    private MonitoringService monitoringService = new MonitoringServiceImpl(eventRepository, metricRepository);

    @Test
    public void shouldCreateNewEventAndMetric() {
        String eventName = "EventName";
        long value = 300L;
        long timestamp = 10000L;

        when(eventRepository.findIdByName(eventName)).thenReturn(null);
        when(eventRepository.create(eventName)).thenReturn(1);

        Metric metric = new Metric();
        metric.setEventId(1);
        metric.setValue(value);
        metric.setParameters("Name,27.1");
        metric.setEventTimestamp(new Timestamp(timestamp));

        monitoringService.monitor(eventName, value, new Object[]{"Name", 27.1}, timestamp);

        verify(metricRepository).save(eq(metric));
    }
}
