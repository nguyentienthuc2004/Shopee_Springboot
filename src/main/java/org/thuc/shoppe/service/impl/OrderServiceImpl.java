package org.thuc.shoppe.service.impl;

import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import org.hibernate.Transaction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thuc.shoppe.entity.*;
import org.thuc.shoppe.exception.NotFoundException;
import org.thuc.shoppe.mapper.OrderMapper;
import org.thuc.shoppe.model.dto.OrderDto;
import org.thuc.shoppe.model.enums.OrderStatus;
import org.thuc.shoppe.repo.*;
import org.thuc.shoppe.security.UserPrincipal;
import org.thuc.shoppe.service.OrderService;
import org.thuc.shoppe.service.UserRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductVariantRepository productVariantRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public OrderDto createOrder(List<Long> cartItems) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userRepository.findByEmail(userPrincipal.getEmail());
        Cart cart = cartRepository.findByUserId(user.getId());
        // Validate stock for all items first
        for (Long id : cartItems) {
            CartItem item = cartItemRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Cart item not found with id: " + id));
            ProductVariant productVariant = productVariantRepository.findById(item.getProductVariant().getId())
                    .orElseThrow(() -> new NotFoundException("Product variant not found with id: " + item.getProductVariant().getId()));
            if (item.getQuantity() > productVariant.getStock()) {
                throw new IllegalArgumentException("Not enough stock for product variant with id: " + productVariant.getId());
            }
        }

        // Create order entity
        Order order = new Order();
        order.setStatus(OrderStatus.PENDING);

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Long id : cartItems) {
            CartItem item = cartItemRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Cart item not found with id: " + id));
            ProductVariant productVariant = productVariantRepository.findById(item.getProductVariant().getId())
                    .orElseThrow(() -> new NotFoundException("Product variant not found with id: " + item.getProductVariant().getId()));
            if (item.getQuantity() > productVariant.getStock()) {
                throw new IllegalArgumentException("Not enough stock for product variant with id: " + item.getProductVariant().getId());
            }
            productVariant.setStock(productVariant.getStock() - item.getQuantity());
            productVariantRepository.save(productVariant);

            CartItem cartItem = cartItemRepository.findByCartIdAndProductVariantId(cart.getId(), productVariant.getId());
            if (cartItem != null) {
                cartItemRepository.delete(cartItem);
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProductVariant(productVariant);
            orderItem.setQuantity(item.getQuantity());
            orderItems.add(orderItem);

            BigDecimal lineTotal = productVariant.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            totalPrice = totalPrice.add(lineTotal);
        }

        order.setTotalPrice(totalPrice);
        order.setUser(user);
        Order savedOrder = orderRepository.save(order);
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(savedOrder);
            orderItemRepository.save(orderItem);
        }
        return orderMapper.toOrderDto(savedOrder);

    }
}
