package com.example.aop.sla;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class SLOAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(SLOAspect.class);

    private final SLOsConfig slosConfig;

    @Autowired
    public SLOAspect(SLOsConfig slosConfig) {
        this.slosConfig = slosConfig;
    }

    @Around(value = "@annotation(trackLatency)")
    public void trackLatency(ProceedingJoinPoint pjp, TrackLatency trackLatency) throws Throwable {
        String latencyId = trackLatency.latency();
        SLOsConfig.SLOConfig config = slosConfig
                .getSlos()
                .stream()
                .filter(s -> s.getId().equals(latencyId))
                .findAny()
                .orElseThrow(
                        () -> new IllegalStateException(
                                "SLO with ID " + latencyId + " is not configured."
                        )
                );

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        pjp.proceed();
        stopWatch.stop();

        long actualExecutionTime = stopWatch.getLastTaskTimeMillis();
        if (actualExecutionTime > config.getThreshold()) {
            // Usually we have to calculate percentages and so on,
            // but I don't know how this is working, so I'm just going to log something.
            LOGGER.warn("Method {} was too slow. Execution time {} millis", latencyId, actualExecutionTime);
        }
    }
}
