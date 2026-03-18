package org.thuc.shoppe.model.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseErrorDto {
    private int code;
    private String message;
    private List<String> errors;
    private LocalDateTime timestamp = LocalDateTime.now();

    public static ResponseErrorDto of(int code, String message, List<String> errors) {
        return new ResponseErrorDto(code, message, errors, LocalDateTime.now());
    }

    public static ResponseErrorDto of(int code, String message, String error) {
        return of(code, message, List.of(error));
    }
}
