package integration;

import collaborator.TestBean;
import com.gmail.solovyov.daniil.MonitoringConfig;
//import com.gmail.solovyov.daniil.monitoring.event.TestBean;
import config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;


//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = { TestConfig.class })
@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {MonitoringConfig.class})
@SpringBootTest(classes = {MonitoringConfig.class, TestConfig.class})
@EnableAspectJAutoProxy(proxyTargetClass=true)

public class MonitoringAspectTest {

    @Autowired
    private TestBean testBean;

//    @Autowired
//    private EventRepository eventRepository;


    @Test
    public void test(){
        System.out.println();


        testBean.justMethod();

        testBean.shortMethod();

        testBean.longMethod();
    }

}