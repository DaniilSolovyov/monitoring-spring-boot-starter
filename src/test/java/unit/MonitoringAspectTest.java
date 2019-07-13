package unit;

import com.gmail.solovyov.daniil.monitoring.Monitoring;
import com.gmail.solovyov.daniil.monitoring.MonitoringAspect;
import com.gmail.solovyov.daniil.service.MonitoringService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import java.util.concurrent.TimeUnit;

import static org.mockito.AdditionalMatchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class MonitoringAspectTest {

    private MonitoringService monitoringService;
    private TestBean testBean;
    private String name = "Name";
    private double value = 21.7;
    private Object[] args = new Object[]{name, value};

    @Before
    public void refresh() {
        monitoringService = mock(MonitoringService.class);
        TestBean target = new TestBean();
        AspectJProxyFactory factory = new AspectJProxyFactory(target);
        MonitoringAspect aspect = new MonitoringAspect(monitoringService);
        factory.addAspect(aspect);
        testBean = factory.getProxy();
    }


    @Test
    public void shouldBeInvokedOnMethod() {
        testBean.methodUnderMonitoring(name, value);
        verify(monitoringService).monitor(eq("METHOD_START"), eq(1L), aryEq(args), anyLong());
        verify(monitoringService).monitor(eq("METHOD_END"), eq(1L), aryEq(args), anyLong());
        verify(monitoringService).monitor(eq("METHOD_DURATION"), gt((long) value), aryEq(args), anyLong());
    }

    @Test
    public void shouldNotBeInvokedOnMethod() {
        testBean.justMethod(name, value);
        verifyZeroInteractions(monitoringService);
    }

    private class TestBean {
        @Monitoring("METHOD")
        public void methodUnderMonitoring(String name, double value) {
            try {
                TimeUnit.MILLISECONDS.sleep((long) value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void justMethod(String name, double value) {

        }
    }
}