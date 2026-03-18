package org.thuc.shoppe.service;

import org.thuc.shoppe.model.dto.UserLoginResponseDto;
import org.thuc.shoppe.model.dto.UserResponseDto;
import org.thuc.shoppe.model.request.user.CreateUserRequest;
import org.thuc.shoppe.model.request.user.UserLoginRequest;

public interface AuthService {
    public UserResponseDto register(CreateUserRequest createUserRequest);

    UserLoginResponseDto login(UserLoginRequest userLogin);
}
