INSERT INTO product_variants
(product_id, color_id, size_id, price, stock, product_image_id, created_at, updated_at, created_by, updated_by, is_deleted)
VALUES
    (1, 1, 1, 120000, 20, 1, NOW(), NOW(), 1, 1, FALSE),
    (1, 2, 2, 125000, 25, 1, NOW(), NOW(), 1, 1, FALSE),
    (1, 3, 3, 130000, 30, 1, NOW(), NOW(), 1, 1, FALSE),
    (2, 1, 2, 250000, 15, 2, NOW(), NOW(), 1, 1, FALSE),
    (2, 4, 3, 260000, 10, 2, NOW(), NOW(), 1, 1, FALSE);