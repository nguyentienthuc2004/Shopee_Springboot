package org.thuc.shoppe.factory_pattern;

public class BeefRestaurant extends Restaurant{
    @Override
    public Burger createBurger() {
        return new BeefBurger();
    }
}
