USE dbtallas;

DROP PROCEDURE IF EXISTS sp_listar_tallas;
DROP PROCEDURE IF EXISTS sp_detalle_talla;
DROP PROCEDURE IF EXISTS sp_insertar_talla;
DROP PROCEDURE IF EXISTS sp_editar_talla;
DROP PROCEDURE IF EXISTS sp_eliminar_talla;

DELIMITER $$

CREATE PROCEDURE sp_listar_tallas()
BEGIN
    SELECT id, nombre, relacion, prenda, talla, notas, activo
    FROM tallas
    ORDER BY id DESC;
END $$

CREATE PROCEDURE sp_detalle_talla(
    IN p_id INT
)
BEGIN
    SELECT id, nombre, relacion, prenda, talla, notas, activo
    FROM tallas
    WHERE id = p_id;
END $$

CREATE PROCEDURE sp_insertar_talla(
    IN p_nombre VARCHAR(150),
    IN p_relacion VARCHAR(150),
    IN p_prenda VARCHAR(100),
    IN p_talla VARCHAR(20),
    IN p_notas TEXT,
    IN p_activo BOOLEAN
)
BEGIN
    INSERT INTO tallas (nombre, relacion, prenda, talla, notas, activo)
    VALUES (p_nombre, p_relacion, p_prenda, p_talla, p_notas, p_activo);

    SELECT LAST_INSERT_ID() AS id_generado;
END $$

CREATE PROCEDURE sp_editar_talla(
    IN p_id INT,
    IN p_nombre VARCHAR(150),
    IN p_relacion VARCHAR(150),
    IN p_prenda VARCHAR(100),
    IN p_talla VARCHAR(20),
    IN p_notas TEXT,
    IN p_activo BOOLEAN
)
BEGIN
    UPDATE tallas
    SET nombre = p_nombre,
        relacion = p_relacion,
        prenda = p_prenda,
        talla = p_talla,
        notas = p_notas,
        activo = p_activo
    WHERE id = p_id;

    SELECT ROW_COUNT() AS filas_afectadas;
END $$

CREATE PROCEDURE sp_eliminar_talla(
    IN p_id INT
)
BEGIN
    DELETE FROM tallas
    WHERE id = p_id;

    SELECT ROW_COUNT() AS filas_afectadas;
END $$

DELIMITER ;
