package com.gmail.solovyov.daniil.monitoring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MonitoringAspect {

    private static final String START = "_START";
    private static final String END = "_END";
    private static final String DURATION = "_DURATION";

    private MonitoringService monitoringService;

    @Autowired
    public MonitoringAspect(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @Around("@annotation(com.gmail.solovyov.daniil.monitoring.Monitoring)")
    public Object monitor(ProceedingJoinPoint joinPoint) throws Throwable {

        String eventName = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Monitoring.class).value();
        Object[] args = joinPoint.getArgs();

        long start = System.currentTimeMillis();
        monitoringService.monitor(eventName.concat(START), 1, args, start);

        Object proceed = joinPoint.proceed();

        long end = System.currentTimeMillis();
        monitoringService.monitor(eventName.concat(END), 1, args, end);

        long duration = end - start;
        monitoringService.monitor(eventName.concat(DURATION), duration, args, end);

        return proceed;
    }
}
