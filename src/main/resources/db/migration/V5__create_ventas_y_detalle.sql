/* ============================================================================
   V5 - TABLAS DE VENTAS (CABECERA + DETALLE)
   FERRESYS - FASE 6
   ============================================================================ */

-- =========================
-- TABLA: ventas (CABECERA)
-- =========================
CREATE TABLE ventas (
    id_venta            INT IDENTITY(1,1) PRIMARY KEY,
    fecha               DATETIME2       NOT NULL,
    id_usuario          INT             NOT NULL,
    total               DECIMAL(12,2)   NOT NULL,
    estado              BIT             NOT NULL DEFAULT 1
);

-- =========================
-- TABLA: venta_detalle
-- =========================
CREATE TABLE venta_detalle (
    id_detalle          INT IDENTITY(1,1) PRIMARY KEY,
    id_venta            INT             NOT NULL,
    id_producto         INT             NOT NULL,
    cantidad            INT             NOT NULL,
    precio_unitario     DECIMAL(12,2)   NOT NULL,
    subtotal            DECIMAL(12,2)   NOT NULL
);

-- =========================
-- FOREIGN KEYS
-- =========================
ALTER TABLE venta_detalle
ADD CONSTRAINT fk_venta_detalle_venta
FOREIGN KEY (id_venta) REFERENCES ventas(id_venta);

ALTER TABLE venta_detalle
ADD CONSTRAINT fk_venta_detalle_producto
FOREIGN KEY (id_producto) REFERENCES productos(id_producto);
