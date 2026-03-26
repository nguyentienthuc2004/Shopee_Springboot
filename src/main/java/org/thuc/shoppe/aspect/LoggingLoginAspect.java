package org.thuc.shoppe.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LoggingLoginAspect {
    @Before("execution(* org.thuc.shoppe.controller.AuthController.login(..))")
    public void logBeforeControllerMethods(JoinPoint joinPoint) {
        log.info("Method called: " + joinPoint.getSignature().getName());
        Object object[]= joinPoint.getArgs();
        log.info("Arguments: {}", Arrays.toString(object));
    }
    @After("execution(* org.thuc.shoppe.controller.AuthController.login(..))")
    public void logAfterControllerMethods(JoinPoint joinPoint) {
        log.info("Method finished: " + joinPoint.getSignature().getName());
    }
    @AfterReturning("execution(* org.thuc.shoppe.controller.AuthController.login(..))")
    public void logAfterReturningControllerMethods(JoinPoint joinPoint) {
        log.info("Method executed successfully: " + joinPoint.getSignature().getName());
    }
    @AfterThrowing("execution(* org.thuc.shoppe.controller.AuthController.login(..))")
    public void logAfterThrowingControllerMethods(JoinPoint joinPoint) {
        log.error("Method threw an exception: " + joinPoint.getSignature().getName());
    }

}
