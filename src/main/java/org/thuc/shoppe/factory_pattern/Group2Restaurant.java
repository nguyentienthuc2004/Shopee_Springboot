package org.thuc.shoppe.factory_pattern;

public class Group2Restaurant extends Restaurant{
    @Override
    public Burger createBurger() {
        return new VeggieBurger();
    }
}
