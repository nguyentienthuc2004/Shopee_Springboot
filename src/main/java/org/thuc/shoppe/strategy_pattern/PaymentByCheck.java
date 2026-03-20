package org.thuc.shoppe.strategy_pattern;

public class PaymentByCheck implements Payment{
    @Override
    public String pay() {
        return "Paying by check";
    }
}
