-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-11-2020 a las 23:39:49
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
-- Base de datos: `tecnojava_tp1`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `NPedido` int(11) NOT NULL,
  `NCliente` int(11) NOT NULL,
  `Fecha` datetime NOT NULL DEFAULT current_timestamp(),
  `Estado` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`NPedido`, `NCliente`, `Fecha`, `Estado`) VALUES
(29, 4, '2020-11-16 12:25:22', 'Solicitado'),
(30, 4, '2020-11-16 14:43:51', 'Solicitado'),
(31, 1, '2020-11-19 10:32:25', 'Solicitado'),
(32, 1, '2020-11-19 11:06:27', 'Solicitado'),
(33, 1, '2020-11-19 12:07:08', 'Solicitado'),
(34, 1, '2020-11-19 12:07:22', 'Solicitado'),
(35, 1, '2020-11-19 12:13:18', 'Solicitado'),
(36, 1, '2020-11-19 12:15:26', 'Solicitado'),
(37, 1, '2020-11-19 12:16:53', 'Solicitado'),
(38, 1, '2020-11-19 12:37:06', 'Solicitado'),
(39, 7, '2020-11-19 12:57:44', 'Solicitado'),
(40, 7, '2020-11-20 11:29:31', 'Solicitado'),
(41, 7, '2020-11-20 12:22:33', 'Solicitado'),
(42, 7, '2020-11-20 13:03:36', 'Solicitado'),
(43, 1, '2020-11-28 18:40:01', 'Solicitado'),
(44, 1, '2020-11-28 18:43:14', 'Solicitado'),
(45, 7, '2020-11-28 18:44:24', 'Solicitado'),
(46, 1, '2020-11-28 18:44:53', 'Solicitado'),
(47, 1, '2020-11-28 20:50:39', 'Solicitado'),
(48, 1, '2020-11-28 21:01:51', 'Solicitado'),
(49, 1, '2020-11-28 21:15:07', 'Solicitado'),
(50, 1, '2020-11-28 21:30:26', 'Solicitado'),
(51, 1, '2020-11-28 21:30:36', 'Solicitado'),
(52, 1, '2020-11-28 21:50:16', 'Solicitado'),
(53, 1, '2020-11-29 19:14:58', 'Solicitado'),
(54, 1, '2020-11-29 19:14:59', 'Solicitado'),
(55, 1, '2020-11-29 19:15:00', 'Solicitado'),
(56, 1, '2020-11-29 19:15:04', 'Solicitado'),
(57, 1, '2020-11-29 19:16:11', 'Solicitado'),
(58, 1, '2020-11-29 19:16:21', 'Solicitado'),
(59, 1, '2020-11-29 19:16:28', 'Solicitado'),
(60, 1, '2020-11-29 19:16:31', 'Solicitado'),
(61, 1, '2020-11-29 19:16:36', 'Solicitado'),
(62, 1, '2020-11-29 19:16:43', 'Solicitado'),
(63, 7, '2020-11-29 19:28:02', 'Solicitado'),
(64, 7, '2020-11-29 19:28:40', 'Solicitado'),
(65, 10, '2020-11-29 19:29:40', 'Solicitado'),
(66, 10, '2020-11-29 19:29:42', 'Solicitado'),
(67, 10, '2020-11-29 19:29:43', 'Solicitado'),
(68, 10, '2020-11-29 19:29:44', 'Solicitado'),
(69, 10, '2020-11-29 19:32:18', 'Solicitado'),
(70, 7, '2020-11-29 19:33:06', 'Solicitado'),
(71, 10, '2020-11-29 19:33:13', 'Solicitado'),
(72, 7, '2020-11-29 19:38:37', 'Solicitado'),
(73, 1, '2020-11-29 19:38:57', 'Solicitado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos_detalles`
--

CREATE TABLE `pedidos_detalles` (
  `NItem` int(11) NOT NULL,
  `NPedido` int(11) NOT NULL,
  `Codigo` int(11) NOT NULL,
  `Producto` varchar(255) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `PrecioUnidad` decimal(10,0) NOT NULL,
  `Total` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pedidos_detalles`
--

INSERT INTO `pedidos_detalles` (`NItem`, `NPedido`, `Codigo`, `Producto`, `Cantidad`, `PrecioUnidad`, `Total`) VALUES
(17, 29, 1, 'martillo grande', 3, '0', '0'),
(18, 29, 3, 'tornillos x 1kg', 2, '0', '0'),
(19, 30, 1, 'martillo grande', 14, '0', '0'),
(20, 30, 2, 'martillo chico', 15, '0', '0'),
(21, 30, 3, 'tornillos x 1kg', 5, '0', '0'),
(22, 31, 2, 'martillo chico', 1, '0', '0'),
(23, 31, 3, 'tornillos x 1kg', 1, '0', '0'),
(24, 32, 2, 'martillo chico', 1, '0', '0'),
(26, 37, 16, 'bordeadora', 2, '3569', '7138'),
(27, 37, 2, 'martillo chico', 2, '700', '1400'),
(28, 37, 3, 'tornillos x 1kg', 2, '333', '666'),
(29, 37, 4, 'amoladora', 2, '5000', '10000'),
(30, 37, 11, 'lamparita 500wats', 2, '999', '1998'),
(31, 37, 13, 'tenaza', 2, '250', '500'),
(32, 37, 15, 'madera larga', 2, '500', '1000'),
(33, 38, 16, 'bordeadora', 1, '3569', '3569'),
(34, 38, 2, 'martillo chico', 1, '700', '700'),
(35, 38, 3, 'tornillos x 1kg', 1, '333', '333'),
(36, 38, 4, 'amoladora', 1, '5000', '5000'),
(37, 38, 11, 'lamparita 500wats', 1, '999', '999'),
(38, 38, 13, 'tenaza', 1, '250', '250'),
(39, 38, 15, 'madera larga', 1, '500', '500'),
(40, 39, 2, 'martillo chico', 3, '700', '2100'),
(41, 40, 2, 'martillo chico', 1, '700', '700'),
(42, 40, 3, 'tornillos x 1kg', 1, '333', '333'),
(43, 40, 4, 'amoladora', 1, '5000', '5000'),
(44, 40, 11, 'lamparita 500wats', 1, '999', '999'),
(45, 41, 3, 'tornillos x 1kg', 1, '333', '333'),
(46, 42, 3, 'tornillos x 1kg', 1, '333', '333'),
(47, 43, 2, 'martillo chico', 1, '700', '700'),
(48, 43, 3, 'tornillos x 1kg', 1, '333', '333'),
(49, 43, 4, 'amoladora', 1, '5000', '5000'),
(50, 43, 11, 'lamparita 500wats', 1, '999', '999'),
(51, 43, 13, 'tenaza', 1, '250', '250'),
(52, 44, 2, 'martillo chico', 1, '700', '700'),
(53, 44, 3, 'tornillos x 1kg', 1, '333', '333'),
(54, 44, 4, 'amoladora', 1, '5000', '5000'),
(55, 45, 2, 'martillo chico', 1, '700', '700'),
(56, 46, 2, 'martillo chico', 1, '700', '700'),
(57, 47, 2, 'martillo chico', 1, '700', '700'),
(58, 47, 3, 'tornillos x 1kg', 1, '333', '333'),
(59, 47, 4, 'amoladora', 1, '5000', '5000'),
(60, 48, 2, 'martillo chico', 1, '700', '700'),
(61, 48, 3, 'tornillos x 1kg', 1, '333', '333'),
(62, 48, 11, 'lamparita 500wats', 1, '999', '999'),
(63, 49, 11, 'lamparita 500wats', 1, '999', '999'),
(64, 50, 2, 'martillo chico', 1, '700', '700'),
(65, 51, 11, 'lamparita 500wats', 1, '999', '999'),
(66, 52, 2, 'martillo chico', 1, '700', '700'),
(67, 52, 3, 'tornillos x 1kg', 1, '333', '333'),
(68, 52, 11, 'lamparita 500wats', 1, '999', '999'),
(69, 52, 13, 'tenaza', 1, '250', '250'),
(70, 52, 15, 'madera larga', 1, '500', '500'),
(71, 63, 2, 'martillo chico', 2, '700', '1400'),
(72, 63, 3, 'tornillos x 1kg', 3, '333', '999'),
(73, 64, 3, 'tornillos x 1kg', 1, '333', '333'),
(74, 65, 2, 'martillo chico', 1, '700', '700'),
(75, 69, 2, 'martillo chico', 1, '700', '700'),
(76, 70, 3, 'tornillos x 1kg', 1, '333', '333'),
(77, 70, 13, 'tenaza', 1, '250', '250'),
(78, 71, 2, 'martillo chico', 6, '700', '4200'),
(79, 71, 3, 'tornillos x 1kg', 5, '333', '1665'),
(80, 71, 11, 'lamparita 500wats', 4, '999', '3996'),
(81, 71, 15, 'madera larga', 3, '500', '1500'),
(82, 72, 13, 'tenaza', 15, '250', '3750'),
(83, 73, 2, 'martillo chico', 33, '700', '23100');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `Codigo` int(11) NOT NULL,
  `Nombre` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `Precio` decimal(10,0) NOT NULL,
  `Cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`Codigo`, `Nombre`, `Precio`, `Cantidad`) VALUES
(2, 'martillo chico', '700', 172),
(3, 'tornillos x 1kg', '333', 299),
(4, 'amoladora', '5000', 0),
(11, 'lamparita 500wats', '999', 9278),
(13, 'tenaza', '250', 834),
(15, 'madera larga', '500', 992),
(16, 'bordeadora', '8999', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `ID` int(11) NOT NULL,
  `Usuario` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `Password` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `Nick` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `Email` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `Activo` tinyint(1) DEFAULT NULL,
  `Saldo` decimal(10,0) NOT NULL DEFAULT 0,
  `isAdmin` tinyint(1) DEFAULT 0,
  `FechaAlta` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`ID`, `Usuario`, `Password`, `Nick`, `Email`, `Activo`, `Saldo`, `isAdmin`, `FechaAlta`) VALUES
(1, 'Lucas', '123123', 'Lucas', 'lucasemilino24@hotmail.com', 1, '395712', 1, '2020-11-14 03:35:55'),
(3, 'Lucas1', '12345', 'Lucas1', 'LucasEmilino22@hotmail.com', 1, '0', 0, '2020-11-14 03:35:55'),
(4, 'Lucas3', '12345', 'Lucas3', 'LucasEmilino23@hotmail.com', 1, '17005', 0, '2020-11-14 03:35:55'),
(5, 'noadmin', 'noadmin', 'noadmin', 'noadmin@gmail.com', 0, '5000000', 0, '2020-11-14 03:35:55'),
(6, 'FABIANO', '12345', 'Caruana', 'fabianocaruana@holamundo.com', 0, '800', 0, '2020-11-14 03:35:55'),
(7, 'HIKARU', '123123', 'Nakamura', 'hikarunakamura@gmail.com', 1, '4857', 0, '2020-11-14 03:35:55'),
(8, 'LIREN', '123123', 'Ding1', 'dingliren@yahoo.com.ar', 0, '2110', 0, '2020-11-14 03:35:55'),
(9, 'NOACTIVE', 'noactivo', 'noactivo', 'noactive@noactive.com', 0, '654654654', 0, '2020-11-14 03:35:55'),
(10, 'FABIANO2', 'fabiano2', 'fabiano2', 'fabiano2@gmail.com', 1, '65139', 0, '2020-11-14 03:35:55'),
(11, 'FABIANO3', 'fabiano3', 'fabiano3', 'fabiano3@gmail.com', 1, '0', 0, '2020-11-14 03:35:55'),
(12, 'DATETIME', 'datetime', 'datetime', 'datetime@now.com', 1, '0', 0, '2020-11-14 03:36:48');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`NPedido`);

--
-- Indices de la tabla `pedidos_detalles`
--
ALTER TABLE `pedidos_detalles`
  ADD PRIMARY KEY (`NItem`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`Codigo`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Usuario` (`Usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `NPedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT de la tabla `pedidos_detalles`
--
ALTER TABLE `pedidos_detalles`
  MODIFY `NItem` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `Codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
