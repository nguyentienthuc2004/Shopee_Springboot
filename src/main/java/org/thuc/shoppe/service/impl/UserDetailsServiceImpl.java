package org.thuc.shoppe.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thuc.shoppe.entity.User;
import org.thuc.shoppe.entity.UserRole;
import org.thuc.shoppe.repo.UserRoleRepository;
import org.thuc.shoppe.security.UserPrincipal;
import org.thuc.shoppe.service.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());
        for (UserRole userRole : userRoles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+userRole.getRole().getName()));
        }
        return UserPrincipal.from(user, authorities);
    }
}
