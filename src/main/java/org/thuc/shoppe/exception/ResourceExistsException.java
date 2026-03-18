package org.thuc.shoppe.exception;

import org.springframework.http.HttpStatus;

public class ResourceExistsException extends AppException {
    public ResourceExistsException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
