package org.thuc.shoppe.model.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateUserRequest {

    @Email(message = "Email  not valid")
    @NotEmpty(message = "Email  not be empty")
    private String email;

    @NotEmpty(message = "Password not be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotEmpty(message = "fullName not be empty")
    private String fullName;

    @NotEmpty(message = "Phone number must not be empty")
    @Pattern(regexp = "^(\\+84|0)\\d{9}$",
            message = "Phone number must be valid and start with +84 or 0 followed by 9 digits")
    private String phone;

    @Override
    public String toString() {
        return "CreateUserRequest{" +
                "email='" + email + '\'' +
                ", password='[PROTECTED]'" +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}