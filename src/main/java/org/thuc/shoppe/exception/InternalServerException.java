package org.thuc.shoppe.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends AppException {
    public InternalServerException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
    }

    public InternalServerException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    public InternalServerException(String message, Throwable cause) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, cause);
    }
}
