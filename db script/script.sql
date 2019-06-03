-- Proyecto: Restaurant
-- Este archivo contiene el script de la base de datos
-- utilizado para el proyecto final de la materia de
-- "Desarrollo de Dispositivos Móviles"

drop database if exists RestaurantMovil;
Create Database RestaurantMovil default character set utf8;

drop database if exists pruebaservicioweb;
Create Database pruebaservicioweb default character set utf8;


USE RestaurantMovil ;

-- -----------------------------------------------------
-- Table `Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Categoria` (
  `idCategoria` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NULL,
  PRIMARY KEY (`idCategoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Alimento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Alimento` (
  `idAlimento` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `precio` FLOAT NOT NULL,
  `existencia` TINYINT(1) NOT NULL DEFAULT 0,
  `Categoria_idCategoria` INT NOT NULL,
  PRIMARY KEY (`idAlimento`),
  INDEX `fk_Alimento_Categoria_idx` (`Categoria_idCategoria` ASC),
  CONSTRAINT `fk_Alimento_Categoria`
    FOREIGN KEY (`Categoria_idCategoria`)
    REFERENCES `Categoria` (`idCategoria`)
    )
ENGINE = InnoDB
Default Character SET = utf8;


-- -----------------------------------------------------
-- Table `Empresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Empresa` (
  `rfc` VARCHAR(60) NOT NULL,
  `nombre` VARCHAR(60) NOT NULL,
  `tipo_empresa` VARCHAR(45) NULL,
  PRIMARY KEY (`rfc`))
ENGINE = InnoDB
Default Character SET = utf8;


-- -----------------------------------------------------
-- Table `Sucursal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Sucursal` (
  `idSucursal` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `telefono` INT NULL,
  `Empresa_rfc` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`idSucursal`),
  INDEX `fk_Sucursal_Empresa1_idx` (`Empresa_rfc` ASC),
  CONSTRAINT `fk_Sucursal_Empresa1`
    FOREIGN KEY (`Empresa_rfc`)
    REFERENCES `Empresa` (`rfc`)
    )
ENGINE = InnoDB
Default Character SET = utf8;
	

-- -----------------------------------------------------
-- Table `Empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Empleado` (
  `clave_empleado` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido_paterno` VARCHAR(45) NOT NULL,
  `apellido_materno` VARCHAR(45) NOT NULL,
  `puesto` VARCHAR(45) NOT NULL,
  `activo` TINYINT(1) NOT NULL DEFAULT 1,
  `fecha_registro` DATETIME NOT NULL DEFAULT now(),
  `Sucursal_idSucursal` INT NOT NULL,
  PRIMARY KEY (`clave_empleado`),
  INDEX `fk_Empleado_Sucursal1_idx` (`Sucursal_idSucursal` ASC),
  CONSTRAINT `fk_Empleado_Sucursal1`
    FOREIGN KEY (`Sucursal_idSucursal`)
    REFERENCES `Sucursal` (`idSucursal`)
    )
ENGINE = InnoDB
Default Character SET = utf8;


-- -----------------------------------------------------
-- Table `Orden`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Orden` (
  `id_orden` INT NOT NULL AUTO_INCREMENT,
  `fecha_orden` DATETIME NOT NULL default now(),
  `entregada` TINYINT(1) NOT NULL DEFAULT 0,
  `numero_mesa` INT NOT NULL,
  `finalizada` TINYINT(1) NOT NULL DEFAULT 0,
  `total` DOUBLE NULL,
  `propina` DOUBLE NULL,
  `Empleado_clave_empleado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_orden`),
  INDEX `fk_Orden_Empleado1_idx` (`Empleado_clave_empleado` ASC),
  CONSTRAINT `fk_Orden_Empleado1`
    FOREIGN KEY (`Empleado_clave_empleado`)
    REFERENCES `Empleado` (`clave_empleado`)
    )
ENGINE = InnoDB
Default Character SET = utf8;


-- -----------------------------------------------------
-- Table `DetalleOrden`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DetalleOrden` (
  `idDetalleOrden` INT NOT NULL AUTO_INCREMENT,
  `cantidad` INT NOT NULL default 1,
  `Alimento_idAlimento` INT NOT NULL,
  `Orden_id_orden` INT NOT NULL,
  PRIMARY KEY (`idDetalleOrden`),
  INDEX `fk_DetalleOrden_Alimento1_idx` (`Alimento_idAlimento` ASC),
  INDEX `fk_DetalleOrden_Orden1_idx` (`Orden_id_orden` ASC),
  CONSTRAINT `fk_DetalleOrden_Alimento1`
    FOREIGN KEY (`Alimento_idAlimento`)
    REFERENCES `Alimento` (`idAlimento`),
  CONSTRAINT `fk_DetalleOrden_Orden1`
    FOREIGN KEY (`Orden_id_orden`)
    REFERENCES `Orden` (`id_orden`)
    )
ENGINE = InnoDB
Default Character SET = utf8;


-- -----------------------------------------------------
-- Table `Mesa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Mesa` (
  `idMesa` INT NOT NULL AUTO_INCREMENT,
  `numero_sillas` INT NOT NULL,
  `disponible` TINYINT(1) NOT NULL DEFAULT 1,
  `Sucursal_idSucursal` INT NOT NULL,
  PRIMARY KEY (`idMesa`),
  INDEX `fk_Mesa_Sucursal1_idx` (`Sucursal_idSucursal` ASC),
  CONSTRAINT `fk_Mesa_Sucursal1`
    FOREIGN KEY (`Sucursal_idSucursal`)
    REFERENCES `Sucursal` (`idSucursal`)
    )
ENGINE = InnoDB
Default Character SET = utf8;

-- -----------------------------------------------------
-- Inserciones Iniciales
-- -----------------------------------------------------
Insert into Empresa (rfc, nombre, tipo_empresa) values 
	('BGEL960518000', 'DS3 Restaurant', 'privada');
    
Insert into Sucursal (nombre, direccion, telefono, Empresa_rfc) values 
	('DS3 Leon', 'Damasco #119, Col Lomas del Condado 37000', '7778899', 'BGEL960518000');
    
Insert into Empleado (clave_empleado, nombre, apellido_paterno, apellido_materno,
	password, puesto, Sucursal_idSucursal) values
	('MartinED', 'Martin', 'Hernández', 'Segura', 'pass', 'Mesero', 1),
    ('AldoCM', 'Aldo', 'Cardona', 'Medina', 'pass', 'Mesero', 1),
    ('Uli', 'Ulises', 'Hernández', 'Márquez', 'pass', 'Mesero', 1),
    ('Eder', 'Eder', 'Beade', 'Gómez', 'pass', 'Administrador', 1);
    
Insert into Mesa (numero_sillas, Sucursal_idSucursal) values
	('4', 1),('6', 1),('2', 1),('4', 1),('6', 1),('2', 1),
    ('4', 1),('6', 1),('2', 1),('4', 1),('6', 1),('2', 1),
    ('4', 1),('6', 1),('2', 1),('4', 1),('6', 1),('2', 1),
    ('4', 1),('6', 1),('2', 1),('4', 1),('6', 1),('2', 1),
    ('4', 1),('6', 1),('2', 1),('4', 1),('6', 1),('2', 1);
    
Insert into Categoria (idCategoria, nombre, descripcion) values
	(1, 'Comida', 'N/A'),
    (2, 'Bebida', 'N/A'),
    (3, 'Postre', 'N/A');
    
Insert into Alimento (nombre, precio, Categoria_idCategoria) values
	# ## -- Postres -- ## #
    ('Milanesa de res', 30, 1),
    ('Corte Rib eye Roll', 80, 1),
    ('Tacos de chorizo', 10, 1),
    ('Tacos de bisteck', 10, 1),
    ('Tacos de constilla', 10, 1),
    ('Tacos de pastor', 10, 1),
    ('Birria', 40, 1),
    ('Chistorra', 35, 1),
    ('Sandwich club', 30, 1),
    ('Parrilla simple', 50, 1),
    # ## -- Bebidass -- ## #
    ('Refresco', 45, 2),
    ('Jugo de Manzana', 40, 2),
    ('Jugo de naranja', 40, 2),
    ('Limonada', 50, 2),
    ('Naranjada', 50, 2),
    ('agua', 20, 2),
    ('Café expresso', 20, 2),
    ('Café americano', 20, 2),
    ('Malteada de fresa', 60, 2),
    ('Malteada de chocolate', 60, 2),
    # ## -- Postres -- ## #
    ('Helado napolitano', 25, 3),
    ('Flan', 25, 3),
    ('Crepas', 30, 3),
    ('Arroz con leche', 25, 3),
    ('Fresas con crema', 25, 3),
    ('Pay de queso', 30, 3),
    ('Mousse de chocolate', 20, 3),
    ('Pastel de fresa', 45, 3),
    ('Pastel de vainilla', 45, 3),
    ('Pastel de chocolate', 45, 3);
    
Insert into Orden (numero_mesa, finalizada, Empleado_Clave_empleado) values
	(10, 1, 'uli'),
    (15, 0, 'uli');
    
Insert into detalleorden (Alimento_idAlimento, Orden_id_orden) values
	(4, 1),
    (14, 1),
    (5, 2),
    (12, 2);
    
update Mesa set disponible = false where idMesa = 10;
update Mesa set disponible = false where idMesa = 15;

# Con la orden 1
Update orden set total = 60, propina = 6, entregada = true, finalizada = true where id_orden = 1;
Update mesa set disponible = true where idMesa = 10;

# Con la orden 2
Update orden set total = 50, entregada = true where id_orden = 2;
