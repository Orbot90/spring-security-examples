package ru.orbot90.security.console.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

/**
 * Created by plevako on 06.05.2016.
 */
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class AccessDeniedHandler {

    @Pointcut("@annotation(org.springframework.security.access.annotation.Secured)")
    public void securedMethod() { }

    @Around("securedMethod()")
    public Object handleAccessDeniedException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (AccessDeniedException e) {
            System.out.println("Access to secured method denied!");
            return null;
        }
    }
}
