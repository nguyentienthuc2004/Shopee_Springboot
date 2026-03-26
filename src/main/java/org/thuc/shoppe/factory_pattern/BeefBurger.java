package org.thuc.shoppe.factory_pattern;

public class BeefBurger implements Burger{
    @Override
    public String create() {
        return "Create a delicious beef burger with lettuce, tomato, and cheese.";
    }
}
