package org.thuc.shoppe.model.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.thuc.shoppe.constant.ApiCodes;
import org.thuc.shoppe.constant.ApiMessages;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseSuccessDto<T> {
    private int code;
    private String message;
    private T data;
    private LocalDateTime timestamp = LocalDateTime.now();

    public static <T> ResponseSuccessDto<T> of(int code, String message, T data) {
        return new ResponseSuccessDto<>(code, message, data, LocalDateTime.now());
    }

    public static <T> ResponseSuccessDto<T> success(T data) {
        return of(ApiCodes.SUCCESS, ApiMessages.SUCCESS, data);
    }
}
