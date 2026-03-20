INSERT INTO products (name, description, stock, category_id, created_at, updated_at, created_by, updated_by, is_deleted)
SELECT
    CONCAT('Product ', LPAD(seq,7,'0')) AS name,
    CONCAT('Description for product ', LPAD(seq,7,'0')) AS description,
    FLOOR(RAND() * 1000) AS stock,
    IF(RAND() < 0.5, 1, 2) AS category_id,
    NOW() AS created_at,
    NOW() AS updated_at,
    1 AS created_by,
    1 AS updated_by,
    0 AS is_deleted
FROM (
         SELECT @row := @row + 1 AS seq
         FROM (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3) t1,
             (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3) t2,
             (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3) t3,
             (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3) t4,
             (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3) t5,
             (SELECT @row := 0) r
     ) seqs
    LIMIT 1000000;