package org.thuc.shoppe.factory_pattern;

public class Group1Restaurant extends Restaurant{
    @Override
    public Burger createBurger() {
        return new BeefBurger();
    }
}
