package org.thuc.shoppe.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final Logger log = LoggerFactory.getLogger(AuthController.class);
//    private final AuthService authService;
//
//    @PostMapping("/register")
//    public ResponseEntity<ResponseSuccessDto<UserDto>> register(@RequestBody RegisterRequestDto request) {
//        log.debug("Request to register user with email: {}", request.getEmail());
//        UserDto user = authService.register(request);
//        return ResponseEntity.ok(ResponseSuccessDto.success(user));
//    }
}
