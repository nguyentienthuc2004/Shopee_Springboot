package org.thuc.shoppe.factory_pattern;

public class VeggieBurger implements Burger{
    @Override
    public String prepare() {
        return "Preparing a delicious veggie burger with lettuce, tomato, and cheese.";
    }
}
