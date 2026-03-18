package org.thuc.shoppe.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thuc.shoppe.entity.Role;
import org.thuc.shoppe.entity.User;
import org.thuc.shoppe.entity.UserRole;
import org.thuc.shoppe.exception.NotFoundException;
import org.thuc.shoppe.exception.ResourceExistsException;
import org.thuc.shoppe.mapper.UserMapper;
import org.thuc.shoppe.model.dto.UserLoginResponseDto;
import org.thuc.shoppe.model.dto.UserResponseDto;
import org.thuc.shoppe.model.enums.RoleEnum;
import org.thuc.shoppe.model.request.user.CreateUserRequest;
import org.thuc.shoppe.model.request.user.UserLoginRequest;
import org.thuc.shoppe.repo.RoleRepository;
import org.thuc.shoppe.repo.UserRoleRepository;
import org.thuc.shoppe.service.AuthService;
import org.thuc.shoppe.service.UserRepository;
import org.thuc.shoppe.util.JwtTokenUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserResponseDto register(CreateUserRequest createUserRequest) {
        // Check if user already exists by email
        User existingUser = userRepository.findByEmail(createUserRequest.getEmail());
        if (existingUser != null) {
            throw new ResourceExistsException("User already exists with email: " + createUserRequest.getEmail());
        }
        // Map request to entity and persist
        User newUserEntity = userMapper.toUser(createUserRequest);
        Role roleUser = roleRepository.findByName(RoleEnum.ROLE_USER);
        if(roleUser==null){
            throw new NotFoundException("Role not found: " + RoleEnum.ROLE_USER);
        }
        newUserEntity.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        User savedUser = userRepository.save(newUserEntity);
        UserRole userRole = new UserRole();
        userRole.setUser(savedUser);
        userRole.setRole(roleUser);
        userRoleRepository.save(userRole);
        return userMapper.toUserResponseDto(savedUser);
    }

    @Override
    public UserLoginResponseDto login(UserLoginRequest userLogin) {
        // Authenticate credentials
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token using email as subject
        User user = userRepository.findByEmail(userLogin.getEmail());
        Map<String,Object> claims = new HashMap<>();
        List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());
        claims.put("roles", userRoles.stream().map(ur -> ur.getRole().getName()).toList());
        claims.put("fullName", user.getFullName());
        claims.put("phone", user.getPhone());
        String accessToken = jwtTokenUtil.generateAccessToken(claims,userLogin.getEmail());
        UserResponseDto userDto = userMapper.toUserResponseDto(user);
        UserLoginResponseDto response = new UserLoginResponseDto();
        response.setUser(userDto);
        response.setAccessToken(accessToken);
        return response;
    }
}
