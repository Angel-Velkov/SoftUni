package com.example.aop.basic;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnProperty(name ="examples.basic.enabled", havingValue = "true")
public class MyBasicAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyBasicAspect.class);

    @Pointcut("execution(* com.example.aop.Student.*(..))")
    public void track() {
    }

    @Before("track()")
    public void before(JoinPoint joinPoint) {
        LOGGER.info("Before calling: {}", joinPoint.getSignature());
    }

    @AfterThrowing(pointcut = "track()", throwing = "error")
    public void afterThrow(Throwable error) {
        LOGGER.info("Exception detected: ", error);
    }
}
