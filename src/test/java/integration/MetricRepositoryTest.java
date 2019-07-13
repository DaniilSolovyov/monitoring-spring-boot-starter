package integration;

import com.gmail.solovyov.daniil.domain.Metric;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.sql.Timestamp;

public class MetricRepositoryTest extends BaseRepositoryTest {

    @Test
    public void shouldCreateEntry() {
        Integer eventId = eventRepository.create("Name");

        Metric metric = new Metric();
        metric.setEventId(eventId);
        metric.setValue(300L);
        metric.setParameters("Parameters");
        metric.setEventTimestamp(new Timestamp(System.currentTimeMillis()));

        metricRepository.save(metric);

        int rowsCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "METRIC");
        Assert.assertEquals(1, rowsCount);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldThrowException() {
        Metric metric = new Metric();
        metric.setEventId(1);
        metric.setValue(300L);
        metric.setParameters("Parameters");
        metric.setEventTimestamp(new Timestamp(System.currentTimeMillis()));

        metricRepository.save(metric);
    }
}
