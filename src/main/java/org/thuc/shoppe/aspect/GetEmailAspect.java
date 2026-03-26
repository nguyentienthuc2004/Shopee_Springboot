package org.thuc.shoppe.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GetEmailAspect {

    @Around("@annotation(org.thuc.shoppe.annotation.CurrentUserEmail)")
    public Object getCurrentUserEmail(ProceedingJoinPoint joinPoint) throws Throwable {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Object[] args = joinPoint.getArgs();
        args[0] = email;
        return joinPoint.proceed(args);
    }
}
