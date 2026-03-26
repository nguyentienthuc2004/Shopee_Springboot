package org.thuc.shoppe.model.request.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserLoginRequest {
    private String email;
    private String password;
    public String toString() {
        return "UserLoginRequest{" +
                "email='" + email + '\'' +
                ", password='[PROTECTED]'" +
                '}';
    }
}
