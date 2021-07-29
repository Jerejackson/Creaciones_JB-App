-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-07-2021 a las 15:40:33
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `creacionesjb`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscarCliente` (IN `iddd` INT)  BEGIN
SELECT cedula INTO @idd FROM cliente WHERE cedula = iddd;
IF @idd is NOT NULL THEN
SELECT * FROM cliente c JOIN referencia r on c.codCliente = r.codCliente WHERE c.cedula = iddd;
ELSE
SELECT 0,0,0,0,0;
END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `buscarClienteCredito` (`cc` INT)  BEGIN
SELECT codCliente INTO @cod FROM cliente WHERE cedula = cc;
IF @cod IS NOT null THEN
	SELECT @cod;
ELSE
	SELECT 0;
END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `buscarClienteTabla` (IN `cc` INT)  BEGIN
SELECT codCliente INTO @cod FROM cliente WHERE cedula = cc;
IF @cod is NOT NULL THEN
SELECT c.codCredito, c.nombreProducto, c.valorProducto, d.quincenas, d.porcentInteres, c.fechaInicio FROM credito c join diferido d on c.diferido = d.codigo WHERE codCliente = @cod;
ELSE
SELECT 0,0,0,0,0;
END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `buscarCredito` (`codigo` INT)  BEGIN
SELECT codCredito INTO @cod FROM credito WHERE codCredito = codigo;
IF @cod is NOT NULL THEN
SELECT * FROM credito cre JOIN cliente cli on cre.codCliente = cli.codCliente WHERE cre.codCredito = codigo;
ELSE
SELECT 0,0,0,0,0;
END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `buscarEmpleado` (IN `iddd` INT)  BEGIN
SELECT id INTO @idd FROM empleado WHERE id = iddd;
IF @idd is NOT NULL THEN
SELECT * FROM empleado WHERE id = iddd;
ELSE
SELECT 0,0,0,0,0;
END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `editarCliente` (`codC` INT, `ccC` INT, `nomC` VARCHAR(30), `dirCasaC` VARCHAR(50), `dirTraC` VARCHAR(50), `telCasaC` VARCHAR(40), `cargoC` VARCHAR(30), `empresaC` VARCHAR(20), `telTraC` VARCHAR(15), `codP` INT, `ccP` INT, `nomP` VARCHAR(30), `dirCasaP` VARCHAR(50), `dirTraP` VARCHAR(50), `telCasaP` VARCHAR(40), `cargoP` VARCHAR(30), `empresaP` VARCHAR(20), `telTraP` VARCHAR(15), `codF` INT, `ccF` INT, `nomF` VARCHAR(30), `dirCasaF` VARCHAR(50), `dirTraF` VARCHAR(50), `telCasaF` VARCHAR(40), `cargoF` VARCHAR(30), `empresaF` VARCHAR(20), `telTraF` VARCHAR(15))  BEGIN
UPDATE cliente SET cedula = ccC, nombre = nomC, direccionCasa = dirCasaC, direccionTrabajo = dirTraC, telefono = telCasaC, cargoTrabajo = cargoC, empresa = empresaC, telTrabajo = telTraC WHERE codCliente = codC;

UPDATE referencia SET cedula = ccP, nombre = nomP, direccionCasa = dirCasaP, direccionTrabajo = dirTraP, telefono = telCasaP, cargoTrabajo = cargoP, empresa = empresaP, telTrabajo = telTraP WHERE codReferencia = codP;

UPDATE referencia SET cedula = ccF, nombre = nomF, direccionCasa = dirCasaF, direccionTrabajo = dirTraF, telefono = telCasaF, cargoTrabajo = cargoF, empresa = empresaF, telTrabajo = telTraF WHERE codReferencia = codF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `editarCredito` (IN `codCredit` INT, IN `ccCliente` INT, IN `nDiferido` INT, IN `valor` INT, IN `nomProducto` VARCHAR(40))  BEGIN
SELECT codCliente INTO @cod from cliente WHERE cedula = ccCliente;
UPDATE credito SET codCliente = @cod, diferido = nDiferido, valorProducto = valor, nombreProducto = nomProducto WHERE codCredito =codCredit;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `registrarCredito` (`ccCliente` INT, `nDiferido` INT, `valor` INT, `nomProducto` VARCHAR(40))  BEGIN
SELECT codCliente INTO @cod FROM cliente WHERE cedula = ccCliente; 
INSERT INTO credito value(null,@cod,nDiferido,valor,nomProducto,NOW());
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `registrarCuotas` (IN `codigoCuo` INT, IN `abono` INT, IN `fecha` DATE)  BEGIN
SELECT codCuota INTO @cod from cuota WHERE codCuota = codigoCuo;
IF @cod > 0 THEN
UPDATE cuota SET fechaPago = fecha, valorPago = abono WHERE codCuota = codigoCuo;
END IF;
END$$

--
-- Funciones
--
CREATE DEFINER=`root`@`localhost` FUNCTION `registrarCliente` (`ccC` INT, `nomC` VARCHAR(30), `dirCasaC` VARCHAR(50), `dirTraC` VARCHAR(50), `telCasaC` VARCHAR(40), `cargoC` VARCHAR(30), `empresaC` VARCHAR(20), `telTraC` VARCHAR(15), `ccP` INT, `nomP` VARCHAR(30), `dirCasaP` VARCHAR(50), `dirTraP` VARCHAR(50), `telCasaP` VARCHAR(40), `cargoP` VARCHAR(30), `empresaP` VARCHAR(20), `telTraP` VARCHAR(15), `ccF` INT, `nomF` VARCHAR(30), `dirCasaF` VARCHAR(50), `dirTraF` VARCHAR(50), `telCasaF` VARCHAR(40), `cargoF` VARCHAR(30), `empresaF` VARCHAR(20), `telTraF` VARCHAR(15)) RETURNS INT(11) BEGIN
SELECT cedula INTO @idC FROM cliente WHERE cedula = ccC;
SELECT cedula INTO @idP FROM referencia WHERE cedula = ccP;
SELECT cedula INTO @idF FROM referencia WHERE cedula = ccF;
IF @idC IS null THEN
	IF @idP IS null AND @idF IS null THEN
		INSERT INTO cliente VALUES(null, ccC, nomC, dirCasaC, dirTraC, telCasaC, cargoC, empresaC, telTraC);
        SELECT MAX(codCliente) INTO @codigoC FROM cliente;
        INSERT INTO referencia VALUES (null,ccP, nomP, @codigoC, 1, telCasaP, dirCasaP, dirTraP, cargoP, empresaP,telTraP), (null,ccF, nomF, @codigoC, 2, telCasaF, dirCasaF, dirTraF, cargoF, empresaF,telTraF);
    ELSE
    select 1 INTO @idc;
    END IF;
END IF;
RETURN @idC;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `registrarEmpleado` (`idd` INT, `nom` VARCHAR(30), `pass` VARCHAR(30)) RETURNS INT(11) BEGIN
SELECT id INTO @idd FROM empleado WHERE id = idd;
IF @idd IS null THEN
INSERT INTO empleado VALUE(idd, nom, pass);
END IF;
RETURN @idd;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `codCliente` int(11) NOT NULL,
  `cedula` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `direccionCasa` varchar(50) NOT NULL,
  `direccionTrabajo` varchar(50) DEFAULT NULL,
  `telefono` varchar(40) NOT NULL,
  `cargoTrabajo` varchar(30) NOT NULL,
  `empresa` varchar(20) DEFAULT NULL,
  `telTrabajo` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`codCliente`, `cedula`, `nombre`, `direccionCasa`, `direccionTrabajo`, `telefono`, `cargoTrabajo`, `empresa`, `telTrabajo`) VALUES
(9, 654, 'jvjhvj', 'jhgjg', 'rtyrt', 'werwer', 'cvbdf', 'gdfgdfg', 'dfgfdbv'),
(10, 1110573599, 'jacon', 'jackson dir c', 'jackson dir t', 'jackson tel c', 'jackson cargo', 'jackson empresa', 'jackson tel t'),
(11, 45, 'kjbkbj', 'kjbkjb', 'kjbkjb', 'kjbjk', 'jb', 'bk', 'kjbk'),
(12, 12345678, 'jbkjbk', 'vjbk', 'jbk', 'vjbjk', 'jbk', 'bk', 'jb');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `credito`
--

CREATE TABLE `credito` (
  `codCredito` int(11) NOT NULL,
  `codCliente` int(11) NOT NULL,
  `diferido` int(11) NOT NULL,
  `valorProducto` int(11) NOT NULL,
  `nombreProducto` varchar(40) DEFAULT NULL,
  `fechaInicio` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `credito`
--

INSERT INTO `credito` (`codCredito`, `codCliente`, `diferido`, `valorProducto`, `nombreProducto`, `fechaInicio`) VALUES
(2, 10, 3, 200000, 'dfghj', '2021-03-16'),
(3, 9, 2, 4567, 'dfghj', '2021-02-16'),
(4, 10, 3, 3000000, 'casa', '2021-03-17'),
(5, 10, 12, 300000, 'mesa2', '2021-03-19');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuota`
--

CREATE TABLE `cuota` (
  `codCuota` int(11) NOT NULL,
  `fechaPago` date NOT NULL,
  `valorPago` int(11) NOT NULL,
  `codCredito` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cuota`
--

INSERT INTO `cuota` (`codCuota`, `fechaPago`, `valorPago`, `codCredito`) VALUES
(1, '2021-03-16', 3000, 2),
(2, '2021-03-05', 1000, 2),
(4, '2021-03-18', 500, 2),
(5, '2021-03-18', 500, 2),
(6, '2021-03-19', 1552, 3),
(10, '2021-03-19', 1000, 3),
(11, '2021-03-19', 0, 3),
(18, '2021-03-19', 770000, 4),
(19, '2021-03-19', 770000, 4),
(20, '2021-03-19', 770000, 4),
(21, '2021-03-19', 770000, 4),
(22, '2021-03-19', 770000, 4),
(23, '2021-03-19', 770000, 4),
(24, '2021-03-19', 1000, 2),
(25, '2021-03-19', 302000, 2),
(26, '2021-03-19', 345000, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `diferido`
--

CREATE TABLE `diferido` (
  `codigo` int(11) NOT NULL,
  `quincenas` int(11) NOT NULL,
  `porcentInteres` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `diferido`
--

INSERT INTO `diferido` (`codigo`, `quincenas`, `porcentInteres`) VALUES
(1, 2, 18),
(2, 4, 36),
(3, 6, 54),
(4, 8, 72),
(5, 10, 90),
(6, 12, 108),
(7, 2, 15),
(8, 4, 30),
(9, 6, 45),
(10, 8, 60),
(11, 1, 0),
(12, 1, 15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id` int(11) NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `contrasena` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id`, `nombre`, `contrasena`) VALUES
(1, 'jeremi', '1'),
(2, 'jackson', '2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `referencia`
--

CREATE TABLE `referencia` (
  `codReferencia` int(11) NOT NULL,
  `cedula` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `codCliente` int(11) NOT NULL,
  `tipoReferencia` int(11) NOT NULL,
  `telefono` varchar(40) NOT NULL,
  `direccionCasa` varchar(50) DEFAULT NULL,
  `direccionTrabajo` varchar(50) DEFAULT NULL,
  `cargoTrabajo` varchar(30) DEFAULT NULL,
  `empresa` varchar(20) DEFAULT NULL,
  `telTrabajo` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `referencia`
--

INSERT INTO `referencia` (`codReferencia`, `cedula`, `nombre`, `codCliente`, `tipoReferencia`, `telefono`, `direccionCasa`, `direccionTrabajo`, `cargoTrabajo`, `empresa`, `telTrabajo`) VALUES
(9, 123, '+nomP+', 9, 1, '+telCasaP+', '+dirCasaP+', '+dirTraP+', '+cargoP+', '+empresaP+', '+telTraP+'),
(10, 543, '+nomF+', 9, 2, '+telCasaF+', '+dirCasaF+', '+dirTraF+', '+cargoF+', '+empresaF+', '+telTraF+'),
(11, 5555, 'sanchez', 10, 1, 'sanchez tel c', 'sanchez dir c', 'sanchez dir t', 'sanchez cargo', 'sanchez empresa', 'sanchez tel t'),
(12, 456, 'jeremi', 10, 2, 'jeremi tel c', 'jeremi dir c', 'jeremi dir t', 'jeremi cargo', 'jeremi empresa', 'jeremi tel t'),
(13, 2345, 'hvjhvjh', 11, 1, 'jhv', 'vjhv', 'vjh', 'jh', 'jhv', 'vjhv'),
(14, 898, 'jhvjhv', 11, 2, 'hv', 'jhvj', 'jh', 'jhv', 'jhv', 'vjhv'),
(15, 0, 'kjbkjbjkb', 12, 1, '3456789', '', '', 'mhbjhbj', '', ''),
(16, 0, 'jhjhvjhv', 12, 2, '34567', '', '', 'vjhvjh', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tiporeferencia`
--

CREATE TABLE `tiporeferencia` (
  `codigo` int(11) NOT NULL,
  `etiqueta` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tiporeferencia`
--

INSERT INTO `tiporeferencia` (`codigo`, `etiqueta`) VALUES
(1, 'Personal'),
(2, 'Familiar');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`codCliente`);

--
-- Indices de la tabla `credito`
--
ALTER TABLE `credito`
  ADD PRIMARY KEY (`codCredito`),
  ADD KEY `fk_creditoCliente` (`codCliente`),
  ADD KEY `fk_creditoDiferido` (`diferido`);

--
-- Indices de la tabla `cuota`
--
ALTER TABLE `cuota`
  ADD PRIMARY KEY (`codCuota`),
  ADD KEY `fk_cuotaCredito` (`codCredito`);

--
-- Indices de la tabla `diferido`
--
ALTER TABLE `diferido`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `referencia`
--
ALTER TABLE `referencia`
  ADD PRIMARY KEY (`codReferencia`),
  ADD KEY `fk_referenciaCliente` (`codCliente`),
  ADD KEY `fk_referenciaTipoReferido` (`tipoReferencia`);

--
-- Indices de la tabla `tiporeferencia`
--
ALTER TABLE `tiporeferencia`
  ADD PRIMARY KEY (`codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `codCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `credito`
--
ALTER TABLE `credito`
  MODIFY `codCredito` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `cuota`
--
ALTER TABLE `cuota`
  MODIFY `codCuota` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `diferido`
--
ALTER TABLE `diferido`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `referencia`
--
ALTER TABLE `referencia`
  MODIFY `codReferencia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `tiporeferencia`
--
ALTER TABLE `tiporeferencia`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `credito`
--
ALTER TABLE `credito`
  ADD CONSTRAINT `fk_creditoCliente` FOREIGN KEY (`codCliente`) REFERENCES `cliente` (`codCliente`),
  ADD CONSTRAINT `fk_creditoDiferido` FOREIGN KEY (`diferido`) REFERENCES `diferido` (`codigo`);

--
-- Filtros para la tabla `cuota`
--
ALTER TABLE `cuota`
  ADD CONSTRAINT `fk_cuotaCredito` FOREIGN KEY (`codCredito`) REFERENCES `credito` (`codCredito`);

--
-- Filtros para la tabla `referencia`
--
ALTER TABLE `referencia`
  ADD CONSTRAINT `fk_referenciaCliente` FOREIGN KEY (`codCliente`) REFERENCES `cliente` (`codCliente`),
  ADD CONSTRAINT `fk_referenciaTipoReferido` FOREIGN KEY (`tipoReferencia`) REFERENCES `tiporeferencia` (`codigo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
