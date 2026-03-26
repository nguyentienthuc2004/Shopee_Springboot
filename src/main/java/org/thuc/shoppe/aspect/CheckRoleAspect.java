package org.thuc.shoppe.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.thuc.shoppe.annotation.CheckRole;

import java.util.List;

@Aspect
@Component
@Slf4j
public class CheckRoleAspect {
    @Before("@annotation(checkRole)")
    public void checkRoleBeforeCreateCart(CheckRole checkRole) {
        String value = checkRole.value();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = auth.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .toList();
        if (!roles.contains("ROLE_" + value)) {
            throw new BadCredentialsException("Forbidden: User does not have the required role to access this resource");
        }
    }
}
