package org.thuc.shoppe.exception;

import org.thuc.shoppe.constant.ApiCodes;
import org.thuc.shoppe.constant.ApiMessages;

public class InternalServerException extends AppException {
    public InternalServerException() {
        super(ApiCodes.INTERNAL_SERVER_ERROR, ApiMessages.INTERNAL_SERVER_ERROR);
    }

    public InternalServerException(String message) {
        super(ApiCodes.INTERNAL_SERVER_ERROR, message);
    }

    public InternalServerException(String message, Throwable cause) {
        super(ApiCodes.INTERNAL_SERVER_ERROR, message, cause);
    }
}
