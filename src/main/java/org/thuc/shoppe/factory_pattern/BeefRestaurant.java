package org.thuc.shoppe.factory_pattern;

import org.springframework.stereotype.Component;

@Component("beef")
public class BeefRestaurant extends Restaurant{
    @Override
    public Burger createBurger() {
        return new BeefBurger();
    }
}
