package org.thuc.shoppe.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thuc.shoppe.model.dto.UserLoginResponseDto;
import org.thuc.shoppe.model.dto.UserDto;
import org.thuc.shoppe.model.request.user.CreateUserRequest;
import org.thuc.shoppe.model.request.user.UserLoginRequest;
import org.thuc.shoppe.model.response.ResponseSuccessDto;
import org.thuc.shoppe.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ResponseSuccessDto<UserDto>> register(@RequestBody @Valid CreateUserRequest createUserRequest) {
        log.debug("Request to register user with email: {}", createUserRequest.getEmail());
        UserDto user = authService.register(createUserRequest);
        return new ResponseEntity<>(ResponseSuccessDto.success(user), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseSuccessDto<UserLoginResponseDto>> login(@RequestBody @Valid UserLoginRequest userLogin){
        log.debug("Request to login user with email: {}", userLogin.getEmail());
        UserLoginResponseDto response = authService.login(userLogin);
        return ResponseEntity.ok(ResponseSuccessDto.success(response));
    }
}

