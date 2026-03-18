-- Flyway migration: create initial schema for shoppe application

-- ================= USER =================
CREATE TABLE IF NOT EXISTS Users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    fullName VARCHAR(255),
    phone VARCHAR(20),
    addressId BIGINT,

    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    createdBy BIGINT,
    updatedBy BIGINT,
    isDeleted BOOLEAN DEFAULT FALSE
);

-- ================= ROLE =================
CREATE TABLE IF NOT EXISTS Roles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,

    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    createdBy BIGINT,
    updatedBy BIGINT,
    isDeleted BOOLEAN DEFAULT FALSE
);

-- ================= PERMISSION =================
CREATE TABLE IF NOT EXISTS Permissions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,

    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    createdBy BIGINT,
    updatedBy BIGINT,
    isDeleted BOOLEAN DEFAULT FALSE
);

-- ================= USER ROLE =================
CREATE TABLE IF NOT EXISTS UserRoles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    userId BIGINT,
    roleId BIGINT,

    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    createdBy BIGINT,
    updatedBy BIGINT,
    isDeleted BOOLEAN DEFAULT FALSE,

    CONSTRAINT fk_userroles_user FOREIGN KEY (userId) REFERENCES Users(id),
    CONSTRAINT fk_userroles_role FOREIGN KEY (roleId) REFERENCES Roles(id)
);

-- ================= ROLE PERMISSION =================
CREATE TABLE IF NOT EXISTS RolePermissions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    roleId BIGINT,
    permissionId BIGINT,

    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    createdBy BIGINT,
    updatedBy BIGINT,
    isDeleted BOOLEAN DEFAULT FALSE,

    CONSTRAINT fk_rolepermissions_role FOREIGN KEY (roleId) REFERENCES Roles(id),
    CONSTRAINT fk_rolepermissions_permission FOREIGN KEY (permissionId) REFERENCES Permissions(id)
);

-- ================= CART =================
CREATE TABLE IF NOT EXISTS Carts (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    userId BIGINT UNIQUE,

    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    createdBy BIGINT,
    updatedBy BIGINT,
    isDeleted BOOLEAN DEFAULT FALSE,

    CONSTRAINT fk_carts_user FOREIGN KEY (userId) REFERENCES Users(id)
);

-- ================= CATEGORY =================
CREATE TABLE IF NOT EXISTS Categories (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,

    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    createdBy BIGINT,
    updatedBy BIGINT,
    isDeleted BOOLEAN DEFAULT FALSE
);

-- ================= PRODUCT =================
CREATE TABLE IF NOT EXISTS Products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    stock INT DEFAULT 0,
    categoryId BIGINT,

    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    createdBy BIGINT,
    updatedBy BIGINT,
    isDeleted BOOLEAN DEFAULT FALSE,

    CONSTRAINT fk_products_category FOREIGN KEY (categoryId) REFERENCES Categories(id)
);

-- ================= SIZE =================
CREATE TABLE IF NOT EXISTS Sizes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),

    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    createdBy BIGINT,
    updatedBy BIGINT,
    isDeleted BOOLEAN DEFAULT FALSE
);

-- ================= COLOR =================
CREATE TABLE IF NOT EXISTS Colors (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),

    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    createdBy BIGINT,
    updatedBy BIGINT,
    isDeleted BOOLEAN DEFAULT FALSE
);

-- ================= PRODUCT IMAGE =================
CREATE TABLE IF NOT EXISTS ProductImages (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    url TEXT,
    productId BIGINT,

    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    createdBy BIGINT,
    updatedBy BIGINT,
    isDeleted BOOLEAN DEFAULT FALSE,

    CONSTRAINT fk_productimages_product FOREIGN KEY (productId) REFERENCES Products(id)
);

-- ================= PRODUCT VARIANT =================
CREATE TABLE IF NOT EXISTS ProductVariants (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    productId BIGINT,
    colorId BIGINT,
    sizeId BIGINT,
    price DECIMAL(10,2),
    stock INT,
    productImageId BIGINT,

    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    createdBy BIGINT,
    updatedBy BIGINT,
    isDeleted BOOLEAN DEFAULT FALSE,

    CONSTRAINT fk_productvariants_product FOREIGN KEY (productId) REFERENCES Products(id),
    CONSTRAINT fk_productvariants_color FOREIGN KEY (colorId) REFERENCES Colors(id),
    CONSTRAINT fk_productvariants_size FOREIGN KEY (sizeId) REFERENCES Sizes(id),
    CONSTRAINT fk_productvariants_image FOREIGN KEY (productImageId) REFERENCES ProductImages(id)
);

-- ================= ORDER =================
CREATE TABLE IF NOT EXISTS Orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    userId BIGINT,
    totalPrice DECIMAL(12,2),
    status VARCHAR(50),

    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    createdBy BIGINT,
    updatedBy BIGINT,
    isDeleted BOOLEAN DEFAULT FALSE,

    CONSTRAINT fk_orders_user FOREIGN KEY (userId) REFERENCES Users(id)
);

-- ================= ITEM =================
CREATE TABLE IF NOT EXISTS Items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    productVariantId BIGINT,
    orderId BIGINT,

    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    createdBy BIGINT,
    updatedBy BIGINT,
    isDeleted BOOLEAN DEFAULT FALSE,

    CONSTRAINT fk_items_variant FOREIGN KEY (productVariantId) REFERENCES ProductVariants(id),
    CONSTRAINT fk_items_order FOREIGN KEY (orderId) REFERENCES Orders(id)
);

-- ================= ORDER ITEM =================
CREATE TABLE IF NOT EXISTS OrderItems (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    orderId BIGINT,
    itemId BIGINT,
	quantity INT,

    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    createdBy BIGINT,
    updatedBy BIGINT,
    isDeleted BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_orderitems_order FOREIGN KEY (orderId) REFERENCES Orders(id),
    CONSTRAINT fk_orderitems_item FOREIGN KEY (itemId) REFERENCES Items(id)
);

-- ================= PAYMENT =================
CREATE TABLE IF NOT EXISTS Payments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    orderId BIGINT,
    amount DECIMAL(12,2),
    method VARCHAR(50),
    status VARCHAR(50),

    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    createdBy BIGINT,
    updatedBy BIGINT,
    isDeleted BOOLEAN DEFAULT FALSE,

    CONSTRAINT fk_payments_order FOREIGN KEY (orderId) REFERENCES Orders(id)
);
