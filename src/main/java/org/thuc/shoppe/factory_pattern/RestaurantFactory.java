package org.thuc.shoppe.factory_pattern;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class RestaurantFactory {
    private final Map<String, Restaurant> restaurantMap;
    public Restaurant getRestaurant(String type){
       Restaurant restaurant = restaurantMap.get(type);
         if(restaurant == null){
             throw new UnsupportedOperationException("Restaurant type not supported: " + type);
         }
         return restaurant;
    }
}
