package com.gmail.solovyov.daniil.event;

import com.gmail.solovyov.daniil.monitoring.Monitoring;
import org.springframework.stereotype.Component;

@Component
public class TestBean {

    @Monitoring("SHORT_METHOD")
    public void shortMethod(){

    }

    @Monitoring("LONG_METHOD")
    public void longMethod(){

    }

    public void justMethod(){

    }
}
