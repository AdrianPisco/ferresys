/* FERRESYS - MIGRACION INICIAL */

CREATE TABLE roles (
    id_role INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);



CREATE TABLE usuarios (
	    id_usuario INT IDENTITY(1,1) PRIMARY KEY,
	    username VARCHAR(50) NOT NULL UNIQUE,
	    password_hash VARCHAR(255) NOT NULL,
        estado BIT NOT NULL DEFAULT 1,
        id_role INT NOT NULL,
        CONSTRAINT fk_usuarios_roles
       		FOREIGN KEY (id_role)
        	REFERENCES roles(id_role)
);

CREATE TABLE auditoria (
	    id_auditoria INT IDENTITY(1,1) PRIMARY KEY,
    	accion VARCHAR(100) NOT NULL,
  		usuario VARCHAR(50) NOT NULL,
    	fecha DATETIME NOT NULL DEFAULT GETDATE()
);


