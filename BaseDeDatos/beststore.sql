-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-07-2024 a las 03:24:44
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `beststore`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id`, `created_at`, `direccion`, `email`, `nombre`, `telefono`) VALUES
(2, '2024-04-27 18:50:01.000000', 'Avenida 456, Ciudad', 'anita721@gmail.com', 'Ana García', '2147483647'),
(8, '2024-04-28 08:56:07.000000', 'calle10 #5-48 cofdfmuneros', 'ibarragomez77@gmail.com', 'Angel', '2147483647'),
(9, '2024-04-28 09:01:38.000000', 'calle10 #6-77 motilones', 'Roller69@gmail.com', 'Roller Danielito', '2147483634'),
(10, '2024-05-22 12:04:51.000000', 'Calle del Sol 45', 'ana.torres@example.com', 'Ana Torres', '111222333'),
(11, '2024-05-22 12:04:51.000000', 'Calle Luna 89', 'luis.martinez@example.com', 'Luis Martinez', '444555666'),
(12, '2024-05-22 12:04:51.000000', 'Avenida Estrellas 12', 'elena.gomez@example.com', 'Elena Gomez', '777888999'),
(13, '2024-05-22 12:05:59.000000', 'Calle del Sol 45', 'ana.torres@example.com', 'Ana Torres', '111222333'),
(14, '2024-05-22 12:05:59.000000', 'Calle Luna 89', 'luis.martinez@example.com', 'Luis Martinez', '444555666'),
(15, '2024-05-22 12:05:59.000000', 'Avenida Estrellas 12', 'elena.gomez@example.com', 'Elena Gomez', '777888999'),
(16, '2024-07-06 16:14:36.000000', 'calle10 #5-48 ', 'angelgabriel777@gmail.com', 'Angel', '3142462357');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mascotas`
--

CREATE TABLE `mascotas` (
  `id` int(11) NOT NULL,
  `color` varchar(255) DEFAULT NULL,
  `edad` int(11) NOT NULL,
  `especie` varchar(255) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `raza` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT NULL,
  `cliente_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `image_file_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `products`
--

INSERT INTO `products` (`id`, `category`, `created_at`, `brand`, `description`, `image_file_name`, `name`, `price`) VALUES
(1, 'Accessories', '2024-04-27 12:04:26.000000', 'PetPrime', 'correa para perros', '1714237466163_correa para perros.jpg', 'CORREAS PARA PERROS RETRÁCTIL TALLA M – COLOR AZÚL', 50000),
(2, 'Alimentos', '2024-04-27 14:02:19.000000', 'DOG CHOW', 'El concentrado Purina Dog Chow Edad Madura es un concentrado especialmente creado para proporcionar una nutrición óptima y adecuada a perros en etapa senior de todas las razas. Es un concentrado formulado para el cuidado y fortalecimiento articular gracias a su contenido de glucosamina. También, aportará vitaminas C y E, minerales, prebióticos, fibra natural y otros nutrientes que además de favorecer su digestión y sistema inmunológico, favorecerá su salud en general, su vitalidad y su energía para mantenerse activo por más tiempo. Es un concentrado ideal para perros adultos mayores de 7 años, que aportará una nutrición sana, completa, balanceada y adecuada para tu perro durante esta importante etapa.', '1714244539450_Alimento para perro.jpg', 'Dog Chow Perros Senior Vida Sana 17 Kg', 20000),
(11, 'Higiene', '2024-04-27 14:13:15.000000', 'Chunky', 'DeliDog Dent son ricos snacks para perros que además de brindarles una rica experiencia de sabor, también se encargará de cuidar su salud dental. Su especial fórmula y textura favorecerá la limpieza de los dientes, retirando el sarro y la placa acumulada. Además, tiene un rico sabor a menta que proporcionará un aliento fresco. ', '1714245195852_Hiiegiene para mascotas.jpg', 'Chunky DeliDog Dent Perros 150 g', 17000),
(12, 'Medicamentos', '2024-04-27 14:20:36.000000', 'perritos<3', 'Suplemento Vitaminico Perritos Omegas + Vitaminas + Zinc por 60 tabletas', '1714245636076_suplemento para perros.jpg', 'Suplemento Vitaminico Perritos Omegas + Vitaminas + Zinc por 60 tabletas', 50000),
(14, 'Snacks', '2024-04-27 15:22:05.000000', 'pedigree', 'Los mini snacks Pedigree Tasty Minis con queso y carne de vacuno son un alimento complementario adecuados para perros de cualquier tamaño. En Pedigree, creemos firmemente que los perros nos hacen mejores personas. Su adorable candidez saca a diario lo mejor de nosotros.', '1714249325828_snacks.jpeg', 'Pedigree Tasty Mini Snacks para Perros Sabor Queso y Buey', 10000),
(15, 'Other', '2024-04-27 15:25:43.000000', 'TRIXIE', 'Acompaña a tu perro en paseos o visitas al veterinario con diversión asegurada gracias al juguete de felpa en forma de perro de Trixie con cuerda para perros activos, hecho de poliéster suave y resistente, perfecto para morder y lanzar.', '1714249543574_jueguete.jpg', 'Juguete de Peluche en Forma de Perro', 23000);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `mascotas`
--
ALTER TABLE `mascotas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2an8whhtepxb34mq1khlgsum4` (`cliente_id`);

--
-- Indices de la tabla `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `mascotas`
--
ALTER TABLE `mascotas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `mascotas`
--
ALTER TABLE `mascotas`
  ADD CONSTRAINT `FK2an8whhtepxb34mq1khlgsum4` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
