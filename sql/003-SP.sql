USE tienda;

DROP PROCEDURE IF EXISTS sp_listar_productos;
DROP PROCEDURE IF EXISTS sp_insertar_producto;

DELIMITER $$

CREATE PROCEDURE sp_listar_productos()
BEGIN
    SELECT id, nombre, categoria, precio, stock, estado
    FROM producto
    ORDER BY id DESC;
END $$

CREATE PROCEDURE sp_insertar_producto(
    IN p_nombre VARCHAR(150),
    IN p_categoria VARCHAR(100),
    IN p_precio DECIMAL(10,2),
    IN p_stock INT,
    IN p_estado INT
)
BEGIN
    INSERT INTO producto (nombre, categoria, precio, stock, estado)
    VALUES (p_nombre, p_categoria, p_precio, p_stock, p_estado);

    SELECT LAST_INSERT_ID() AS id_generado;
END $$

DELIMITER ;
