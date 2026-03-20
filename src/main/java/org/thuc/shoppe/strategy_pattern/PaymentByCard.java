package org.thuc.shoppe.strategy_pattern;

public class PaymentByCard implements Payment{
    @Override
    public String pay() {
        return "Paying by card";
    }
}
