package integration;

import com.gmail.solovyov.daniil.monitoring.Monitoring;
import com.gmail.solovyov.daniil.monitoring.MonitoringAspect;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MonitoringAspectTest extends BaseSpringBootTest {

    @Autowired
    private MonitoringAspect monitoringAspect;

    @Test
    public void shouldCreateRows() {

        TestBean target = new TestBean();
        AspectJProxyFactory factory = new AspectJProxyFactory(target);
        factory.addAspect(monitoringAspect);
        TestBean testBean = factory.getProxy();

        for (int i = 0; i <= 40; i++) {
            testBean.methodUnderMonitoring("NAME", 1);
        }

        int size = metricRepository.findAll().size();

        Assert.assertTrue(size > 0);
    }

    @Test
    public void shouldNotCreateRows() {

        TestBean target = new TestBean();
        AspectJProxyFactory factory = new AspectJProxyFactory(target);
        factory.addAspect(monitoringAspect);
        TestBean testBean = factory.getProxy();

        for (int i = 0; i <= 40; i++) {
            testBean.justMethod("NAME", 1);
        }

        int size = metricRepository.findAll().size();

        Assert.assertEquals(0, size);
    }

    private class TestBean {
        @Monitoring("METHOD")
        public void methodUnderMonitoring(String name, double value) {
        }

        public void justMethod(String name, double value) {

        }
    }
}
