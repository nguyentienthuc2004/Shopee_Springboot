-- Flyway migration: create initial schema for shoppe application (snake_case)

-- ================= USERS =================
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(255),
    phone VARCHAR(20),
    address_id BIGINT,

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    is_deleted BOOLEAN DEFAULT FALSE
);

-- ================= ROLES =================
CREATE TABLE IF NOT EXISTS roles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    is_deleted BOOLEAN DEFAULT FALSE
);

-- ================= PERMISSIONS =================
CREATE TABLE IF NOT EXISTS permissions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    is_deleted BOOLEAN DEFAULT FALSE
);

-- ================= USER ROLES =================
CREATE TABLE IF NOT EXISTS user_roles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    role_id BIGINT,

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    is_deleted BOOLEAN DEFAULT FALSE,

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- ================= ROLE PERMISSIONS =================
CREATE TABLE IF NOT EXISTS role_permissions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role_id BIGINT,
    permission_id BIGINT,

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    is_deleted BOOLEAN DEFAULT FALSE,

    FOREIGN KEY (role_id) REFERENCES roles(id),
    FOREIGN KEY (permission_id) REFERENCES permissions(id)
);

-- ================= CARTS =================
CREATE TABLE IF NOT EXISTS carts (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT UNIQUE,

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    is_deleted BOOLEAN DEFAULT FALSE,

    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- ================= CATEGORIES =================
CREATE TABLE IF NOT EXISTS categories (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    is_deleted BOOLEAN DEFAULT FALSE
);

-- ================= PRODUCTS =================
CREATE TABLE IF NOT EXISTS products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    stock INT DEFAULT 0,
    category_id BIGINT,

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    is_deleted BOOLEAN DEFAULT FALSE,

    FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- ================= SIZES =================
CREATE TABLE IF NOT EXISTS sizes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    is_deleted BOOLEAN DEFAULT FALSE
);

-- ================= COLORS =================
CREATE TABLE IF NOT EXISTS colors (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    is_deleted BOOLEAN DEFAULT FALSE
);

-- ================= PRODUCT IMAGES =================
CREATE TABLE IF NOT EXISTS product_images (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    url TEXT,
    product_id BIGINT,

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    is_deleted BOOLEAN DEFAULT FALSE,

    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- ================= PRODUCT VARIANTS =================
CREATE TABLE IF NOT EXISTS product_variants (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_id BIGINT,
    color_id BIGINT,
    size_id BIGINT,
    price DECIMAL(10,2),
    stock INT,
    product_image_id BIGINT,

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    is_deleted BOOLEAN DEFAULT FALSE,

    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (color_id) REFERENCES colors(id),
    FOREIGN KEY (size_id) REFERENCES sizes(id),
    FOREIGN KEY (product_image_id) REFERENCES product_images(id)
);

-- ================= ORDERS =================
CREATE TABLE IF NOT EXISTS orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    total_price DECIMAL(12,2),
    status VARCHAR(50),

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    is_deleted BOOLEAN DEFAULT FALSE,

    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- ================= ITEMS =================
CREATE TABLE IF NOT EXISTS items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_variant_id BIGINT,
    order_id BIGINT,

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    is_deleted BOOLEAN DEFAULT FALSE,

    FOREIGN KEY (product_variant_id) REFERENCES product_variants(id),
    FOREIGN KEY (order_id) REFERENCES orders(id)
);

-- ================= ORDER ITEMS =================
CREATE TABLE IF NOT EXISTS order_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT,
    item_id BIGINT,
    quantity INT,

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    is_deleted BOOLEAN DEFAULT FALSE,

    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (item_id) REFERENCES items(id)
);

-- ================= PAYMENTS =================
CREATE TABLE IF NOT EXISTS payments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT,
    amount DECIMAL(12,2),
    method VARCHAR(50),
    status VARCHAR(50),

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    is_deleted BOOLEAN DEFAULT FALSE,

    FOREIGN KEY (order_id) REFERENCES orders(id)
);

-- ================= INSERT DATA =================
INSERT INTO categories (name, created_at, updated_at, created_by, updated_by, is_deleted)
VALUES
('Thời trang nam', NOW(), NOW(), 1, 1, FALSE),
('Thời trang nữ', NOW(), NOW(), 1, 1, FALSE),
('Điện thoại & Phụ kiện', NOW(), NOW(), 1, 1, FALSE),
('Máy tính & Laptop', NOW(), NOW(), 1, 1, FALSE),
('Thiết bị điện tử', NOW(), NOW(), 1, 1, FALSE),
('Nhà cửa & Đời sống', NOW(), NOW(), 1, 1, FALSE),
('Sức khỏe & Làm đẹp', NOW(), NOW(), 1, 1, FALSE),
('Mẹ & Bé', NOW(), NOW(), 1, 1, FALSE),
('Thể thao & Du lịch', NOW(), NOW(), 1, 1, FALSE),
('Ô tô & Xe máy & Xe đạp', NOW(), NOW(), 1, 1, FALSE);

INSERT INTO sizes (name, created_at, updated_at, created_by, updated_by, is_deleted)
VALUES
('S', NOW(), NOW(), 1, 1, FALSE),
('M', NOW(), NOW(), 1, 1, FALSE),
('L', NOW(), NOW(), 1, 1, FALSE),
('XL', NOW(), NOW(), 1, 1, FALSE);

INSERT INTO colors (name, created_at, updated_at, created_by, updated_by, is_deleted)
VALUES
('Đỏ', NOW(), NOW(), 1, 1, FALSE),
('Xanh', NOW(), NOW(), 1, 1, FALSE),
('Đen', NOW(), NOW(), 1, 1, FALSE),
('Trắng', NOW(), NOW(), 1, 1, FALSE);

INSERT INTO products (name, description, stock, category_id, created_at, updated_at, created_by, updated_by, is_deleted)
VALUES
('Áo thun nam basic', 'Áo thun cotton 100%', 100, 1, NOW(), NOW(), 1, 1, FALSE),
('Áo hoodie nữ', 'Hoodie form rộng', 80, 2, NOW(), NOW(), 1, 1, FALSE);

INSERT INTO product_images (url, product_id, created_at, updated_at, created_by, updated_by, is_deleted)
VALUES
('https://example.com/aothun.jpg', 1, NOW(), NOW(), 1, 1, FALSE),
('https://example.com/hoodie.jpg', 2, NOW(), NOW(), 1, 1, FALSE);

INSERT INTO product_variants
(product_id, color_id, size_id, price, stock, product_image_id, created_at, updated_at, created_by, updated_by
, is_deleted)
VALUES
(1, 1, 1, 120000, 20, 1, NOW(), NOW(), 1, 1, FALSE),
(1, 2, 2, 125000, 25, 1, NOW(), NOW(), 1, 1, FALSE),
(1, 3, 3, 130000, 30, 1, NOW(), NOW(), 1, 1, FALSE),
(2, 1, 2, 250000, 15, 2, NOW(), NOW(), 1, 1, FALSE),
(2, 4, 3, 260000, 10, 2, NOW(), NOW(), 1, 1, FALSE);
