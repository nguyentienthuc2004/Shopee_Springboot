package org.thuc.shoppe.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.thuc.shoppe.model.response.ResponseErrorDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ResponseErrorDto> handleAppException(AppException ex) {
        HttpStatus status = ex.getStatus();
        String message = ex.getMessage();
        ResponseErrorDto body = ResponseErrorDto.of(status.value(), message, message);
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorDto> handleValidation(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.toList());
        String message = "Validation failed";
        ResponseErrorDto body = ResponseErrorDto.of(HttpStatus.BAD_REQUEST.value(), message, errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseErrorDto> handleNotReadable(HttpMessageNotReadableException ex) {
        String message = "Malformed JSON request";
        ResponseErrorDto body = ResponseErrorDto.of(HttpStatus.BAD_REQUEST.value(), message, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseErrorDto> handleOther(Exception ex) {
        String message = "Internal server error";
        ResponseErrorDto body = ResponseErrorDto.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
