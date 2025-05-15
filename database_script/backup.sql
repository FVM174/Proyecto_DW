-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: crossover.proxy.rlwy.net    Database: DB_DW
-- ------------------------------------------------------
-- Server version	9.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `CATEGORIA`
--

DROP TABLE IF EXISTS `CATEGORIA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CATEGORIA` (
  `id_categoria` int NOT NULL,
  `nombre_categoria` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CATEGORIA`
--

LOCK TABLES `CATEGORIA` WRITE;
/*!40000 ALTER TABLE `CATEGORIA` DISABLE KEYS */;
/*!40000 ALTER TABLE `CATEGORIA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CLIENTE`
--

DROP TABLE IF EXISTS `CLIENTE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CLIENTE` (
  `id_cliente` int NOT NULL,
  `nombre_cliente` varchar(100) DEFAULT NULL,
  `direccion_cliente` varchar(200) DEFAULT NULL,
  `telefono_cliente` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CLIENTE`
--

LOCK TABLES `CLIENTE` WRITE;
/*!40000 ALTER TABLE `CLIENTE` DISABLE KEYS */;
/*!40000 ALTER TABLE `CLIENTE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DESCUENTO`
--

DROP TABLE IF EXISTS `DESCUENTO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DESCUENTO` (
  `id_descuento` int NOT NULL,
  `porcentaje` float DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  PRIMARY KEY (`id_descuento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DESCUENTO`
--

LOCK TABLES `DESCUENTO` WRITE;
/*!40000 ALTER TABLE `DESCUENTO` DISABLE KEYS */;
/*!40000 ALTER TABLE `DESCUENTO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DESCUENTO_PRODUCTO`
--

DROP TABLE IF EXISTS `DESCUENTO_PRODUCTO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DESCUENTO_PRODUCTO` (
  `id_producto` int NOT NULL,
  `id_descuento` int NOT NULL,
  PRIMARY KEY (`id_producto`,`id_descuento`),
  KEY `id_descuento` (`id_descuento`),
  CONSTRAINT `DESCUENTO_PRODUCTO_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `PRODUCTO` (`id_producto`),
  CONSTRAINT `DESCUENTO_PRODUCTO_ibfk_2` FOREIGN KEY (`id_descuento`) REFERENCES `DESCUENTO` (`id_descuento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DESCUENTO_PRODUCTO`
--

LOCK TABLES `DESCUENTO_PRODUCTO` WRITE;
/*!40000 ALTER TABLE `DESCUENTO_PRODUCTO` DISABLE KEYS */;
/*!40000 ALTER TABLE `DESCUENTO_PRODUCTO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DETALLE_VENTA`
--

DROP TABLE IF EXISTS `DETALLE_VENTA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DETALLE_VENTA` (
  `id_detalle_venta` int NOT NULL,
  `id_venta` int DEFAULT NULL,
  `id_producto` int DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `precio_unitario_venta` float DEFAULT NULL,
  `subtotal` float DEFAULT NULL,
  PRIMARY KEY (`id_detalle_venta`),
  KEY `id_venta` (`id_venta`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `DETALLE_VENTA_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `VENTA` (`id_venta`),
  CONSTRAINT `DETALLE_VENTA_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `PRODUCTO` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DETALLE_VENTA`
--

LOCK TABLES `DETALLE_VENTA` WRITE;
/*!40000 ALTER TABLE `DETALLE_VENTA` DISABLE KEYS */;
/*!40000 ALTER TABLE `DETALLE_VENTA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EMPLEADO`
--

DROP TABLE IF EXISTS `EMPLEADO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `EMPLEADO` (
  `id_empleado` int NOT NULL,
  `nombre_empleado` varchar(100) DEFAULT NULL,
  `puesto` varchar(50) DEFAULT NULL,
  `telefono_empleado` varchar(12) DEFAULT NULL,
  `correo_empleado` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EMPLEADO`
--

LOCK TABLES `EMPLEADO` WRITE;
/*!40000 ALTER TABLE `EMPLEADO` DISABLE KEYS */;
/*!40000 ALTER TABLE `EMPLEADO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MARCA`
--

DROP TABLE IF EXISTS `MARCA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `MARCA` (
  `id_marca` int NOT NULL,
  `nombre_marca` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_marca`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MARCA`
--

LOCK TABLES `MARCA` WRITE;
/*!40000 ALTER TABLE `MARCA` DISABLE KEYS */;
/*!40000 ALTER TABLE `MARCA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `METODO_PAGO`
--

DROP TABLE IF EXISTS `METODO_PAGO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `METODO_PAGO` (
  `id_metodo` int NOT NULL,
  `nombre_metodo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_metodo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `METODO_PAGO`
--

LOCK TABLES `METODO_PAGO` WRITE;
/*!40000 ALTER TABLE `METODO_PAGO` DISABLE KEYS */;
/*!40000 ALTER TABLE `METODO_PAGO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MOVIMIENTO_INVENTARIO`
--

DROP TABLE IF EXISTS `MOVIMIENTO_INVENTARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `MOVIMIENTO_INVENTARIO` (
  `id_movimiento` int NOT NULL,
  `id_producto` int DEFAULT NULL,
  `tipo_movimiento` enum('entrada','salida') DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `fecha_movimiento` date DEFAULT NULL,
  `motivo` text,
  PRIMARY KEY (`id_movimiento`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `MOVIMIENTO_INVENTARIO_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `PRODUCTO` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MOVIMIENTO_INVENTARIO`
--

LOCK TABLES `MOVIMIENTO_INVENTARIO` WRITE;
/*!40000 ALTER TABLE `MOVIMIENTO_INVENTARIO` DISABLE KEYS */;
/*!40000 ALTER TABLE `MOVIMIENTO_INVENTARIO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRODUCTO`
--

DROP TABLE IF EXISTS `PRODUCTO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PRODUCTO` (
  `id_producto` int NOT NULL,
  `nombre_producto` varchar(45) DEFAULT NULL,
  `descripcion_producto` text,
  `precio_unitario` float DEFAULT NULL,
  `id_marca` int DEFAULT NULL,
  `id_categoria` int DEFAULT NULL,
  `color` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_producto`),
  KEY `id_marca` (`id_marca`),
  KEY `id_categoria` (`id_categoria`),
  CONSTRAINT `PRODUCTO_ibfk_1` FOREIGN KEY (`id_marca`) REFERENCES `MARCA` (`id_marca`),
  CONSTRAINT `PRODUCTO_ibfk_2` FOREIGN KEY (`id_categoria`) REFERENCES `CATEGORIA` (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRODUCTO`
--

LOCK TABLES `PRODUCTO` WRITE;
/*!40000 ALTER TABLE `PRODUCTO` DISABLE KEYS */;
/*!40000 ALTER TABLE `PRODUCTO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RECLAMO`
--

DROP TABLE IF EXISTS `RECLAMO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RECLAMO` (
  `id_reclamo` int NOT NULL,
  `id_venta` int DEFAULT NULL,
  `fecha_reclamo` date DEFAULT NULL,
  `descripcion` text,
  `estado` enum('pendiente','resuelto','rechazado') DEFAULT NULL,
  PRIMARY KEY (`id_reclamo`),
  KEY `id_venta` (`id_venta`),
  CONSTRAINT `RECLAMO_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `VENTA` (`id_venta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RECLAMO`
--

LOCK TABLES `RECLAMO` WRITE;
/*!40000 ALTER TABLE `RECLAMO` DISABLE KEYS */;
/*!40000 ALTER TABLE `RECLAMO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VENTA`
--

DROP TABLE IF EXISTS `VENTA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `VENTA` (
  `id_venta` int NOT NULL,
  `fecha_venta` date DEFAULT NULL,
  `total_venta` float DEFAULT NULL,
  `id_cliente` int DEFAULT NULL,
  `id_empleado` int DEFAULT NULL,
  `id_metodo` int DEFAULT NULL,
  PRIMARY KEY (`id_venta`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_empleado` (`id_empleado`),
  KEY `id_metodo` (`id_metodo`),
  CONSTRAINT `VENTA_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `CLIENTE` (`id_cliente`),
  CONSTRAINT `VENTA_ibfk_2` FOREIGN KEY (`id_empleado`) REFERENCES `EMPLEADO` (`id_empleado`),
  CONSTRAINT `VENTA_ibfk_3` FOREIGN KEY (`id_metodo`) REFERENCES `METODO_PAGO` (`id_metodo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VENTA`
--

LOCK TABLES `VENTA` WRITE;
/*!40000 ALTER TABLE `VENTA` DISABLE KEYS */;
/*!40000 ALTER TABLE `VENTA` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-14 13:19:01
