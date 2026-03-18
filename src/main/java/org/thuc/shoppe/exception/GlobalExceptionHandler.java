package org.thuc.shoppe.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.thuc.shoppe.constant.ApiCodes;
import org.thuc.shoppe.constant.ApiMessages;
import org.thuc.shoppe.model.response.ResponseErrorDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ResponseErrorDto> handleAppException(AppException ex) {
        int code = ex.getCode();
        String message = ex.getMessage();
        ResponseErrorDto body = ResponseErrorDto.of(code, message, message);
        HttpStatus status = mapCodeToStatus(code);
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorDto> handleValidation(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.toList());
        ResponseErrorDto body = ResponseErrorDto.of(ApiCodes.BAD_REQUEST, ApiMessages.BAD_REQUEST, errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseErrorDto> handleNotReadable(HttpMessageNotReadableException ex) {
        ResponseErrorDto body = ResponseErrorDto.of(ApiCodes.BAD_REQUEST, ApiMessages.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseErrorDto> handleOther(Exception ex) {
        ResponseErrorDto body = ResponseErrorDto.of(ApiCodes.INTERNAL_SERVER_ERROR, ApiMessages.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private HttpStatus mapCodeToStatus(int code) {
        return switch (code) {
            case ApiCodes.NOT_FOUND -> HttpStatus.NOT_FOUND;
            case ApiCodes.BAD_REQUEST -> HttpStatus.BAD_REQUEST;
            case ApiCodes.UNAUTHORIZED -> HttpStatus.UNAUTHORIZED;
            case ApiCodes.INTERNAL_SERVER_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR;
            default -> HttpStatus.OK;
        };
    }
}
