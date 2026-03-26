package org.thuc.shoppe.factory_pattern;

public class VeggieRestaurant extends Restaurant{
    @Override
    public Burger createBurger() {
        return new VeggieBurger();
    }
}
