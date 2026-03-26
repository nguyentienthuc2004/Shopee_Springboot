package org.thuc.shoppe.factory_pattern;

import org.springframework.stereotype.Component;

@Component("veggie")
public class VeggieRestaurant extends Restaurant{
    @Override
    public Burger createBurger() {
        return new VeggieBurger();
    }
}
