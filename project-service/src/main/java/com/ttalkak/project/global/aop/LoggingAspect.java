package com.ttalkak.project.global.aop;

import jakarta.persistence.Column;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("within(com.ttalkak.project..application.inputport.*InputPort)")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 메서드 정보 가져오기
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();

        // 파라미터 정보 가져오기
        Object[] args = joinPoint.getArgs();
        String params = Arrays.toString(args);

        // 메서드 실행 전 로그
        log.info("Entering: {}.{} with parameters {}", className, methodName, params);

        long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = joinPoint.proceed();
            return result;
        } finally {
            long executionTime = System.currentTimeMillis() - start;
            log.info("Exiting: {}.{} with parameters {} {}", className, methodName, params, executionTime);
        }
    }
}
