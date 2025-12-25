CREATE TABLE productos (
    id_producto INT IDENTITY(1,1) PRIMARY KEY,

    -- IDENTIDAD DE NEGOCIO
    codigo VARCHAR(50) NOT NULL UNIQUE,

    -- DESCRIPCION
    descripcion VARCHAR(255) NOT NULL,

    -- CLASIFICACION
    categoria VARCHAR(100) NOT NULL,
    marca VARCHAR(100),
    unidad_medida VARCHAR(20) NOT NULL,

    -- STOCK
    stock_total INT NOT NULL DEFAULT 0,

    -- PRECIOS DE COMPRA
    precio_compra_soles DECIMAL(10,2) NULL,
    precio_compra_dolares DECIMAL(10,2) NULL,

    -- PRECIO DE VENTA (SOLO SOLES)
    precio_venta_soles DECIMAL(10,2) NOT NULL,

    -- CONTROL
    estado BIT NOT NULL DEFAULT 1,

    -- AUDITORIA TECNICA
    fecha_creacion DATETIME2 NOT NULL DEFAULT SYSDATETIME()
);
