package org.thuc.shoppe.exception;

import org.thuc.shoppe.constant.ApiCodes;
import org.thuc.shoppe.constant.ApiMessages;

public class NotFoundException extends AppException {
    public NotFoundException() {
        super(ApiCodes.NOT_FOUND, ApiMessages.NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(ApiCodes.NOT_FOUND, message);
    }
}
