package integration;

import com.gmail.solovyov.daniil.domain.Metric;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;


public class MetricRepositoryTest extends BaseSpringBootTest{

    @Test
    public void shouldInsertAndAssignId(){
        Metric expectedMetric = new Metric("EVENT", 300L, "PARAMETERS", new Timestamp(12345));

        metricRepository.saveAll(Arrays.asList(expectedMetric));

        List<Metric> metrics = metricRepository.findAll();

        Assert.assertEquals(1, metrics.size());

        Metric actualMetric = metrics.iterator().next();

        Assert.assertNotNull(actualMetric.getId());
        Assert.assertEquals(expectedMetric.getEventName(), actualMetric.getEventName());
        Assert.assertEquals(expectedMetric.getValue(), actualMetric.getValue());
        Assert.assertEquals(expectedMetric.getParameters(), actualMetric.getParameters());
        Assert.assertEquals(expectedMetric.getEventTimestamp(), actualMetric.getEventTimestamp());
    }
}
