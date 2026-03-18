package org.thuc.shoppe.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.thuc.shoppe.entity.Cart;
import org.thuc.shoppe.entity.CartItem;
import org.thuc.shoppe.entity.ProductVariant;
import org.thuc.shoppe.entity.User;
import org.thuc.shoppe.exception.NotFoundException;
import org.thuc.shoppe.mapper.CartMapper;
import org.thuc.shoppe.model.dto.CartResponseDto;
import org.thuc.shoppe.model.request.cart.CartItemRequest;
import org.thuc.shoppe.repo.CartItemRepository;
import org.thuc.shoppe.repo.CartRepository;
import org.thuc.shoppe.repo.ProductVariantRepository;
import org.thuc.shoppe.security.UserPrincipal;
import org.thuc.shoppe.service.CartService;
import org.thuc.shoppe.service.UserRepository;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final UserRepository userRepository;
    private final ProductVariantRepository productVariantRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartResponseDto createCart() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        User user = userRepository.findByEmail(userPrincipal.getEmail());
        Cart newCart = new Cart();
        newCart.setUser(user);
        Cart savedCart = cartRepository.save(newCart);
        return cartMapper.toCartResponseDto(savedCart);
    }

    @Override
    public void addItem(CartItemRequest cartItemRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        int userId = userPrincipal.getId().intValue();
        Cart cart = cartRepository.findByUserId(userId);
        if(cart==null){
            throw new NotFoundException("Cart not found for user id: " + userId);
        }
        ProductVariant productVariant = productVariantRepository.findById(cartItemRequest.getProductVariantId())
                .orElseThrow(() -> new NotFoundException("Product variant not found with id: " + cartItemRequest.getProductVariantId()));
        CartItem existItem = cartItemRepository.findByCartIdAndProductVariantId(cart.getId(), productVariant.getId());

        if(existItem!=null){
            existItem.setQuantity(cartItemRequest.getQuantity()+existItem.getQuantity());
            cartItemRepository.save(existItem);
            return;
        }
        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProductVariant(productVariant);
        item.setQuantity(cartItemRequest.getQuantity());
        cartItemRepository.save(item);
    }
}
