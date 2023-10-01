-- Indexes for primary keys have been explicitly created.

-- ---------- Table for validation queries from the connection pool. ----------

DROP TABLE PingTable;
CREATE TABLE PingTable (foo CHAR(1));


DROP TABLE LineaPedido;

DROP TABLE Comentario;
DROP TABLE Adjunto;

DROP TABLE StockTalla;
DROP TABLE Descuento;

DROP TABLE Etiqueta;

DROP TABLE Pedido;

DROP TABLE Recomendacion;


DROP TABLE Ropa;

DROP TABLE PuntosExtra;



DROP TABLE Novedad;
DROP TABLE Categoria;
DROP TABLE UserProfile;


-- ------------------------------ UserProfile ----------------------------------

CREATE TABLE UserProfile (
    usrId BIGINT NOT NULL AUTO_INCREMENT,
    loginName VARCHAR(30) COLLATE latin1_bin NOT NULL,
    enPassword VARCHAR(13) NOT NULL, 
    firstName VARCHAR(30) NOT NULL,
    lastName VARCHAR(40) NOT NULL, 
    email VARCHAR(60) NOT NULL,
    dni VARCHAR(9) NOT NULL,
    telefono BIGINT NOT NULL,
    fechaNacimiento VARCHAR(45) NOT  NULL,
    tipoUsuario VARCHAR(45) NULL,
    numeroPuntos INT  NULL,
    CONSTRAINT UserProfile_PK PRIMARY KEY (usrId),
    CONSTRAINT LoginNameUniqueKey UNIQUE (loginName)) 
    ENGINE = InnoDB;

CREATE INDEX UserProfileIndexByLoginName ON UserProfile (loginName);

-- ------------------------------ Categoria ----------------------------------

CREATE TABLE Categoria (
    idCategoria BIGINT NOT NULL AUTO_INCREMENT,
    nombreCategoria VARCHAR(30) COLLATE latin1_bin NOT NULL,
    idCategoriaPadre BIGINT NOT NULL,    
      
    CONSTRAINT Categoria_PK PRIMARY KEY (idCategoria))
    ENGINE = InnoDB;
CREATE INDEX CategoriaIndexByIdCategoria ON Categoria (idCategoria);





-- ------------------------------ Ropa ----------------------------------

CREATE TABLE Ropa (
    idRopa BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(30) COLLATE latin1_bin NOT NULL,
    precio INT NOT NULL, 
    marca VARCHAR(40) NOT NULL, 
    color VARCHAR(30) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,  
    idCategoria BIGINT NOT NULL,
    numPuntos INT NULL,
          
    
    CONSTRAINT Ropa_PK PRIMARY KEY (idRopa),
	CONSTRAINT idCategoriaFK FOREIGN KEY(idCategoria) REFERENCES Categoria (idCategoria))
    ENGINE = InnoDB;
CREATE INDEX RopaIndexByIdRopa ON Ropa (idRopa);



-- ------------------------------ StockTalla ----------------------------------

CREATE TABLE StockTalla (
    idStockTalla BIGINT NOT NULL AUTO_INCREMENT,
    talla VARCHAR(10) COLLATE latin1_bin NOT NULL,
	stock INT NOT NULL, 
    idRopa BIGINT NOT NULL,
    
    CONSTRAINT StockTalla_PK PRIMARY KEY (idStockTalla),
	CONSTRAINT idRopaFK FOREIGN KEY(idRopa) REFERENCES Ropa (idRopa))
    ENGINE = InnoDB;
CREATE INDEX StockTallaIndexByIdStockTalla ON StockTalla (idStockTalla);





-- ------------------------------ Adjunto ----------------------------------

CREATE TABLE Adjunto (
    idAdjunto BIGINT NOT NULL AUTO_INCREMENT,
    nombreAdjunto VARCHAR(190) COLLATE latin1_bin NOT NULL,
    imagen LONGBLOB,
    idRopa BIGINT NOT NULL,



    CONSTRAINT idRopa5FG FOREIGN KEY(idRopa) REFERENCES Ropa (idRopa),
    CONSTRAINT Adjunto_PK PRIMARY KEY (idAdjunto))
    ENGINE = InnoDB;
CREATE INDEX AdjuntoIndexByIdAdjunto ON Adjunto (idAdjunto);




-- ------------------------------ Comentario ----------------------------------

CREATE TABLE Comentario (
    idComentario BIGINT NOT NULL AUTO_INCREMENT,
    textoComentario VARCHAR(255) COLLATE latin1_bin NOT NULL,
    usrId BIGINT NOT NULL,
    idRopa BIGINT NOT NULL,
    
    CONSTRAINT idRopa2FK FOREIGN KEY(idRopa) REFERENCES Ropa (idRopa),
    CONSTRAINT usrIdFK FOREIGN KEY(usrId) REFERENCES UserProfile (usrId),
    CONSTRAINT Comentario_PK PRIMARY KEY (idComentario))
    
    ENGINE = InnoDB;
CREATE INDEX ComentarioIndexByIdComentario ON Comentario (idComentario);


-- ------------------------------ Etiqueta ----------------------------------

CREATE TABLE Etiqueta (
    idEtiqueta BIGINT NOT NULL AUTO_INCREMENT,
    nombreEtiqueta VARCHAR(30) COLLATE latin1_bin NOT NULL,
    idRopa BIGINT NULL,
    
    CONSTRAINT idRopa3FK FOREIGN KEY(idRopa) REFERENCES Ropa (idRopa),
    CONSTRAINT Etiqueta_PK PRIMARY KEY (idEtiqueta))
    ENGINE = InnoDB;
CREATE INDEX EtiquetaIndexByIdEtiqueta ON Etiqueta (idEtiqueta);

-- ------------------------------ Descuento ----------------------------------

CREATE TABLE Descuento (

    idDescuento BIGINT NOT NULL AUTO_INCREMENT,
    porcentajeDescuento BIGINT NOT NULL,
    idEtiqueta BIGINT NOT NULL,


        CONSTRAINT idEtiquetaFK FOREIGN KEY(idEtiqueta) REFERENCES Etiqueta (idEtiqueta),
        CONSTRAINT Descuento_PK PRIMARY KEY (idDescuento))

	    ENGINE = InnoDB;
CREATE INDEX DescuentoIndexByIdDescuento ON Descuento (idDescuento);



-- ------------------------------ Pedido ----------------------------------

CREATE TABLE Pedido (
    idPedido BIGINT NOT NULL AUTO_INCREMENT,
    fechaPedido DATE,
    precioTotal INT NOT NULL,
    estado VARCHAR(100) COLLATE latin1_bin NOT NULL,
    usrId BIGINT,

    CONSTRAINT usrId2FK FOREIGN KEY (usrId) REFERENCES UserProfile (usrId),   
    CONSTRAINT Pedido_PK PRIMARY KEY (idPedido))
    ENGINE = InnoDB;
CREATE INDEX PedidoIndexByIdPedido ON Pedido (idPedido);



-- ------------------------------ LineaPedido ----------------------------------

CREATE TABLE LineaPedido (
    idLineaPedido BIGINT NOT NULL AUTO_INCREMENT,
    precioUnitario INT NOT NULL, 
    numeroUnidades INT NOT NULL, 
    idPedido BIGINT NOT NULL,
    idRopa BIGINT NOT NULL,
    idStockTalla BIGINT NOT NULL,

    CONSTRAINT idStockTallaFK FOREIGN KEY (idStockTalla) REFERENCES StockTalla(idStockTalla),
    CONSTRAINT idRopa4FK FOREIGN KEY (idRopa) REFERENCES Ropa(idRopa),
    CONSTRAINT idPedidoFK FOREIGN KEY (idPedido) REFERENCES Pedido (idPedido),
    CONSTRAINT LineaPedido_PK PRIMARY KEY (idLineaPedido))
    ENGINE = InnoDB;
CREATE INDEX LineaPedidoIndexByIdLineaPedido ON LineaPedido (idLineaPedido);




-- ------------------------------ Recomendacion ----------------------------------

CREATE TABLE Recomendacion (
    idRecomendacion BIGINT NOT NULL AUTO_INCREMENT,
    idRopa1 BIGINT NOT NULL,
    idRopa2 BIGINT NOT NULL,
    numVeces INT NOT NULL, 
   
    CONSTRAINT idRopa5FK FOREIGN KEY (idRopa1) REFERENCES Ropa(idRopa),
    CONSTRAINT idRopa6FK FOREIGN KEY (idRopa2) REFERENCES Ropa(idRopa),
    CONSTRAINT Recomendacion_PK PRIMARY KEY (idRecomendacion))
    ENGINE = InnoDB;
CREATE INDEX RecomendacionIndexByIdRecomendacion ON Recomendacion (idRecomendacion);


-- ------------------------------ Novedad ----------------------------------

CREATE TABLE Novedad (
    idNovedad BIGINT NOT NULL AUTO_INCREMENT,
    esNovedad VARCHAR(30) COLLATE latin1_bin NOT NULL,
    fechaNovedad	 VARCHAR(30) COLLATE latin1_bin NOT NULL,
    
    CONSTRAINT Novedad_PK PRIMARY KEY (idNovedad))
    ENGINE = InnoDB;
CREATE INDEX NovedadIndexByIdNovedad ON Novedad (idNovedad);




-- ------------------------------ PuntosExtra ----------------------------------

CREATE TABLE PuntosExtra (
    idPuntosExtra BIGINT NOT NULL AUTO_INCREMENT,
    numeroPuntosExtra INT NOT NULL, 
    
    
    CONSTRAINT PuntosExtra_PK PRIMARY KEY (idPuntosExtra))
    ENGINE = InnoDB;
CREATE INDEX PuntosExtraIndexByIdPuntosExtra ON PuntosExtra (idPuntosExtra);








