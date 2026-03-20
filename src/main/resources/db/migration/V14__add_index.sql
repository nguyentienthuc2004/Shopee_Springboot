CREATE INDEX idx_stock ON products(stock);
ALTER TABLE products ADD FULLTEXT idx_products_name(name);
ALTER TABLE categories ADD FULLTEXT idx_categories_name(name);