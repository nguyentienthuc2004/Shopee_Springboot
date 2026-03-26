package org.thuc.shoppe.factory_pattern;

import org.springframework.stereotype.Component;

public abstract class Restaurant {
    public abstract Burger createBurger();
    public Burger orderBurger(){
        return createBurger();
    }

}
