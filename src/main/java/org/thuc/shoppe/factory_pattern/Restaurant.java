package org.thuc.shoppe.factory_pattern;

public abstract class Restaurant {
    public abstract Burger createBurger();
    public Burger orderBurger(){
        Burger burger = createBurger();
        String preparation = burger.prepare();
        System.out.println(preparation);
        return burger;
    }
}
