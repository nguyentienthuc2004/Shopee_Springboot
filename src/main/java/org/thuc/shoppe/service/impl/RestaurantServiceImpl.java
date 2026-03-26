package org.thuc.shoppe.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thuc.shoppe.factory_pattern.*;
import org.thuc.shoppe.service.RestaurantService;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantFactory restaurantFactory;
    @Override
    public String order(String typeFood) {
        Restaurant res = restaurantFactory.getRestaurant(typeFood);
        Burger burger = res.orderBurger();
        return burger.create();
    }
}
