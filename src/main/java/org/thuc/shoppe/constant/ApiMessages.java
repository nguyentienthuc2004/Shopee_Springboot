package org.thuc.shoppe.constant;

public final class ApiMessages {
    private ApiMessages() {
    }

    // Product
    public static final String PRODUCT_NOT_FOUND = "Product not found";
    public static final String PRODUCT_ALREADY_EXISTS = "Product already exists";
    public static final String PRODUCT_OUT_OF_STOCK = "Product is out of stock";

    // Category
    public static final String CATEGORY_NOT_FOUND = "Category not found";
    public static final String CATEGORY_ALREADY_EXISTS = "Category already exists";

    // Cart
    public static final String CART_NOT_FOUND = "Cart not found";
    public static final String CART_ITEM_NOT_FOUND = "Cart item not found";
    public static final String CART_EMPTY = "Cart is empty";

    // Order
    public static final String ORDER_NOT_FOUND = "Order not found";
    public static final String ORDER_CANNOT_CANCEL = "Order cannot be cancelled";
    public static final String ORDER_INVALID_STATUS = "Invalid order status";

    // User / Auth
    public static final String USER_NOT_FOUND = "User not found";
    public static final String USER_ALREADY_EXISTS = "User already exists";
    public static final String INVALID_CREDENTIALS = "Invalid username or password";
    public static final String ACCESS_DENIED = "You do not have permission to perform this action";

    // Payment
    public static final String PAYMENT_FAILED = "Payment failed";
    public static final String PAYMENT_METHOD_NOT_SUPPORTED = "Payment method is not supported";
}

