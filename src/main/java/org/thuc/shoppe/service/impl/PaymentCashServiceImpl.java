package org.thuc.shoppe.service.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.thuc.shoppe.service.PaymentService;
@Service
@ConditionalOnProperty(name = "payment.method", havingValue = "cash")
public class PaymentCashServiceImpl implements PaymentService {
    @Override
    public String processPayment() {
        return "Processing payment with cash";
    }
}
