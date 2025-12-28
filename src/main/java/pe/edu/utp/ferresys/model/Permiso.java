package pe.edu.utp.ferresys.model;

/*
=========================================================
 PERMISO
 - ACCIONES ATOMICAS DEL SISTEMA
 - BASE DE SEGURIDAD Y CONTROL DE ACCESO
=========================================================
*/
public enum Permiso {

    // USUARIOS
    USUARIO_VER,
    USUARIO_CREAR,
    USUARIO_EDITAR,
    USUARIO_ELIMINAR,

    // PRODUCTOS
    PRODUCTO_VER,
    PRODUCTO_CREAR,
    PRODUCTO_EDITAR,
    PRODUCTO_ELIMINAR,

    // VENTAS
    VENTA_CREAR,
    VENTA_VER,

    // REPORTES
    REPORTE_VER,

    // CONFIGURACION
    CONFIGURACION_ACCESO
}
