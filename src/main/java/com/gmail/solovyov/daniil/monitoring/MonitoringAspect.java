package com.gmail.solovyov.daniil.monitoring;

import com.gmail.solovyov.daniil.domain.Metric;
import com.gmail.solovyov.daniil.service.MonitoringService;
import com.gmail.solovyov.daniil.util.MetricUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Aspect
@Component
public class MonitoringAspect {

    private static final String START = "_START";
    private static final String END = "_END";
    private static final String DURATION = "_DURATION";

    private MonitoringService monitoringService;

    public MonitoringAspect(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @Around("@annotation(com.gmail.solovyov.daniil.monitoring.Monitoring)")
    public Object monitor(ProceedingJoinPoint joinPoint) throws Throwable {

        String eventName = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Monitoring.class).value();
        Object[] args = joinPoint.getArgs();

        long start = System.currentTimeMillis();
        monitoringService.monitor(
                new Metric(eventName.concat(START), 1L, MetricUtil.argsToString(args), new Timestamp(start)));

        Object proceed = joinPoint.proceed();

        long end = System.currentTimeMillis();
        monitoringService.monitor(
                new Metric(eventName.concat(END), 1L, MetricUtil.argsToString(args), new Timestamp(end)));


        long duration = end - start;
        monitoringService.monitor(
                new Metric(eventName.concat(DURATION), duration, MetricUtil.argsToString(args), new Timestamp(end)));

        return proceed;
    }
}
