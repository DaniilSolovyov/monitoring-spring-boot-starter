package integration;

import com.gmail.solovyov.daniil.monitoring.MonitoringConfig;
import com.gmail.solovyov.daniil.repository.EventRepository;
import com.gmail.solovyov.daniil.repository.MetricRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MonitoringConfig.class})
public abstract class BaseRepositoryTest {

    @Autowired
    protected MetricRepository metricRepository;
    @Autowired
    protected EventRepository eventRepository;
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Before
    public void refreshBefore() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "METRIC", "EVENT");
    }

    @Before
    public void refreshAfter() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "METRIC", "EVENT");
    }
}
