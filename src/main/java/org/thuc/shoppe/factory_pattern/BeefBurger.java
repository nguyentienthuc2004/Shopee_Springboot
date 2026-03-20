package org.thuc.shoppe.factory_pattern;

public class BeefBurger implements Burger{
    @Override
    public String prepare() {
        return "Preparing a delicious beef burger with lettuce, tomato, and cheese.";
    }
}
