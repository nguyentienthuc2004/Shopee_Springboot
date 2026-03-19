package org.thuc.shoppe.strategy_pattern;

public class PaymentService implements Payment{
    private Payment payment;

    public PaymentService(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String pay() {
        return payment.pay();
    }
}
