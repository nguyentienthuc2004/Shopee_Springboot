package org.thuc.shoppe.factory_pattern;

import org.springframework.stereotype.Component;

@Component
public class RestaurantFactory {
    public Restaurant getRestaurant(String type){
        if(type.equalsIgnoreCase("beef")){
            return new BeefRestaurant();
        } else if(type.equalsIgnoreCase("veggie")){
            return new VeggieRestaurant();
        }
        return null;
    }
}
