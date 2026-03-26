package org.thuc.shoppe.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserLoginResponseDto {
    private UserDto user;
    private String accessToken;
    @Override
    public String toString() {
        return "UserLoginResponseDto{" +
                "user=" + user +
                ", accessToken='[PROTECTED]'" +
                '}';
    }
}
