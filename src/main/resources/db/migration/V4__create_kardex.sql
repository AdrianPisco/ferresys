-- =========================================================
-- V4: CREACION DE TABLA KARDEX
-- REGISTRO DE MOVIMIENTOS DE INVENTARIO
-- =========================================================

CREATE TABLE kardex (
    id_kardex INT IDENTITY(1,1) PRIMARY KEY,

    id_producto INT NOT NULL,

    tipo_movimiento VARCHAR(20) NOT NULL,
    cantidad INT NOT NULL,

    stock_anterior INT NOT NULL,
    stock_nuevo INT NOT NULL,

    motivo VARCHAR(255),
    usuario VARCHAR(100) NOT NULL,

    fecha DATETIME2 NOT NULL,

    CONSTRAINT fk_kardex_producto
        FOREIGN KEY (id_producto)
        REFERENCES productos(id_producto)
);
