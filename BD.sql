-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 10-08-2020 a las 22:02:27
-- Versión del servidor: 5.7.26
-- Versión de PHP: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tp-integrador-died-2020`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `camino`
--

DROP TABLE IF EXISTS `camino`;
CREATE TABLE IF NOT EXISTS `camino` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `planta_id` int(11) NOT NULL,
  `ordenPedido_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `camion`
--

DROP TABLE IF EXISTS `camion`;
CREATE TABLE IF NOT EXISTS `camion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patente` varchar(7) NOT NULL,
  `marca` varchar(10) NOT NULL,
  `modelo` varchar(10) NOT NULL,
  `kmRecorridos` float NOT NULL,
  `costoKm` float NOT NULL,
  `costoHora` float NOT NULL,
  `fechaCompra` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadopedido`
--

DROP TABLE IF EXISTS `estadopedido`;
CREATE TABLE IF NOT EXISTS `estadopedido` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `estadopedido`
--

INSERT INTO `estadopedido` (`id`, `descripcion`) VALUES
(0, 'CREADA'),
(1, 'PROCESADA'),
(2, 'ENTREGADA'),
(3, 'CANCELADA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grafo`
--

DROP TABLE IF EXISTS `grafo`;
CREATE TABLE IF NOT EXISTS `grafo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `insumogeneral`
--


DROP TABLE IF EXISTS `insumo`;
CREATE TABLE IF NOT EXISTS `insumo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) NOT NULL,
  `unidadMedida` varchar(50) NOT NULL,
  `costo` float NOT NULL,
  `peso` float NOT NULL,
  `densidad` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `item`
--

DROP TABLE IF EXISTS `item`;
CREATE TABLE IF NOT EXISTS `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) NOT NULL,
  `insumo_id` int(11) DEFAULT NULL,
  `ordenPedido_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordenpedido`
--

DROP TABLE IF EXISTS `ordenpedido`;
CREATE TABLE IF NOT EXISTS `ordenpedido` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fechaSolicitud` date NOT NULL,
  `fechaEntrega` date NOT NULL,
  `costoEnvio` float NOT NULL,
  `destino_id` int(11) NOT NULL,
  `origen_id` int(11) DEFAULT NULL,
  `camion_id` int(11) DEFAULT NULL,
  `estadoPedido_id` int(11) NOT NULL,
  `planta_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `planta`
--

DROP TABLE IF EXISTS `planta`;
CREATE TABLE IF NOT EXISTS `planta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `grafo_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ruta`
--

DROP TABLE IF EXISTS `ruta`;
CREATE TABLE IF NOT EXISTS `ruta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `distanciaKm` float NOT NULL,
  `duracionHora` float NOT NULL,
  `pesoMaximo` float NOT NULL,
  `grafo_id` int(11) NOT NULL,
  `plantaOrigen_id` int(11) NOT NULL,
  `plantaDestino_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `stock`
--

DROP TABLE IF EXISTS `stock`;
CREATE TABLE IF NOT EXISTS `stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) NOT NULL,
  `puntoPedido` int(11) NOT NULL,
  `insumo_id` int(11) DEFAULT NULL,
  `planta_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
