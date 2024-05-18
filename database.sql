-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: booking_management
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Current Database: `booking_management`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `booking_management` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `booking_management`;

--
-- Table structure for table `bookingstate`
--

DROP TABLE IF EXISTS `bookingstate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookingstate` (
  `request_id` bigint unsigned NOT NULL,
  `step` int unsigned NOT NULL,
  `state` int NOT NULL,
  PRIMARY KEY (`request_id`,`step`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookingstate`
--

LOCK TABLES `bookingstate` WRITE;
/*!40000 ALTER TABLE `bookingstate` DISABLE KEYS */;
INSERT INTO `bookingstate` VALUES (1715243848732,1,-1),(1715243848732,2,-1),(1715243848732,3,-1),(1715243848732,4,-1),(1715243848732,5,-1),(1715243848732,6,-1),(1715243848732,7,-1),(1715243848732,8,-1);
/*!40000 ALTER TABLE `bookingstate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `ticket_management`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ticket_management` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ticket_management`;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `invoice_id` int unsigned NOT NULL,
  `price` decimal(19,2) NOT NULL,
  `seat_id` int unsigned NOT NULL,
  `showtime_id` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (135,42,70000.00,1,1),(136,42,70000.00,2,1);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `cinema_management`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `cinema_management` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `cinema_management`;

--
-- Table structure for table `cinema`
--

DROP TABLE IF EXISTS `cinema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cinema` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cinema`
--

LOCK TABLES `cinema` WRITE;
/*!40000 ALTER TABLE `cinema` DISABLE KEYS */;
INSERT INTO `cinema` VALUES (1,'Rạp chiếu phim Mega GS','123 Nguyễn Huệ, Quận 1, TP.HCM'),(2,'CGV Vincom Đồng Khởi','72 Lê Thánh Tôn, Quận 1, TP.HCM');
/*!40000 ALTER TABLE `cinema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `film`
--

DROP TABLE IF EXISTS `film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `film` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film`
--

LOCK TABLES `film` WRITE;
/*!40000 ALTER TABLE `film` DISABLE KEYS */;
INSERT INTO `film` VALUES (1,'Avengers: Endgame'),(2,'Parasite');
/*!40000 ALTER TABLE `film` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filmshowtime`
--

DROP TABLE IF EXISTS `filmshowtime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `filmshowtime` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `film_id` int unsigned NOT NULL,
  `room_id` int unsigned NOT NULL,
  `datetime` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `film_id` (`film_id`),
  KEY `room_id` (`room_id`),
  CONSTRAINT `filmshowtime_ibfk_1` FOREIGN KEY (`film_id`) REFERENCES `film` (`id`),
  CONSTRAINT `filmshowtime_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filmshowtime`
--

LOCK TABLES `filmshowtime` WRITE;
/*!40000 ALTER TABLE `filmshowtime` DISABLE KEYS */;
INSERT INTO `filmshowtime` VALUES (1,1,1,'2024-04-25 02:00:00');
/*!40000 ALTER TABLE `filmshowtime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (42,1,1),(43,1,1),(44,1,-1);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `cinema_id` int unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cinema_id` (`cinema_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,1,'Phòng 1'),(2,1,'Phòng 2'),(3,1,'Phòng 3'),(4,1,'Phòng 4'),(5,2,'Phòng 1'),(6,2,'Phòng 2'),(7,2,'Phòng 3'),(8,2,'Phòng 4');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `room_id` int unsigned NOT NULL,
  `seat_row` int NOT NULL,
  `seat_column` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `room_id` (`room_id`),
  CONSTRAINT `seat_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=401 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (1,1,1,1),(2,1,1,2),(3,1,1,3),(4,1,1,4),(5,1,1,5),(6,1,1,6),(7,1,1,7),(8,1,1,8),(9,1,1,9),(10,1,1,10),(11,1,2,1),(12,1,2,2),(13,1,2,3),(14,1,2,4),(15,1,2,5),(16,1,2,6),(17,1,2,7),(18,1,2,8),(19,1,2,9),(20,1,2,10),(21,1,3,1),(22,1,3,2),(23,1,3,3),(24,1,3,4),(25,1,3,5),(26,1,3,6),(27,1,3,7),(28,1,3,8),(29,1,3,9),(30,1,3,10),(31,1,4,1),(32,1,4,2),(33,1,4,3),(34,1,4,4),(35,1,4,5),(36,1,4,6),(37,1,4,7),(38,1,4,8),(39,1,4,9),(40,1,4,10),(41,2,1,1),(42,2,1,2),(43,2,1,3),(44,2,1,4),(45,2,1,5),(46,2,1,6),(47,2,1,7),(48,2,1,8),(49,2,1,9),(50,2,1,10),(51,2,2,1),(52,2,2,2),(53,2,2,3),(54,2,2,4),(55,2,2,5),(56,2,2,6),(57,2,2,7),(58,2,2,8),(59,2,2,9),(60,2,2,10),(61,2,3,1),(62,2,3,2),(63,2,3,3),(64,2,3,4),(65,2,3,5),(66,2,3,6),(67,2,3,7),(68,2,3,8),(69,2,3,9),(70,2,3,10),(71,2,4,1),(72,2,4,2),(73,2,4,3),(74,2,4,4),(75,2,4,5),(76,2,4,6),(77,2,4,7),(78,2,4,8),(79,2,4,9),(80,2,4,10),(81,3,1,1),(82,3,1,2),(83,3,1,3),(84,3,1,4),(85,3,1,5),(86,3,1,6),(87,3,1,7),(88,3,1,8),(89,3,1,9),(90,3,1,10),(91,3,2,1),(92,3,2,2),(93,3,2,3),(94,3,2,4),(95,3,2,5),(96,3,2,6),(97,3,2,7),(98,3,2,8),(99,3,2,9),(100,3,2,10),(101,3,3,1),(102,3,3,2),(103,3,3,3),(104,3,3,4),(105,3,3,5),(106,3,3,6),(107,3,3,7),(108,3,3,8),(109,3,3,9),(110,3,3,10),(111,3,4,1),(112,3,4,2),(113,3,4,3),(114,3,4,4),(115,3,4,5),(116,3,4,6),(117,3,4,7),(118,3,4,8),(119,3,4,9),(120,3,4,10),(121,4,1,1),(122,4,1,2),(123,4,1,3),(124,4,1,4),(125,4,1,5),(126,4,1,6),(127,4,1,7),(128,4,1,8),(129,4,1,9),(130,4,1,10),(131,4,2,1),(132,4,2,2),(133,4,2,3),(134,4,2,4),(135,4,2,5),(136,4,2,6),(137,4,2,7),(138,4,2,8),(139,4,2,9),(140,4,2,10),(141,4,3,1),(142,4,3,2),(143,4,3,3),(144,4,3,4),(145,4,3,5),(146,4,3,6),(147,4,3,7),(148,4,3,8),(149,4,3,9),(150,4,3,10),(151,4,4,1),(152,4,4,2),(153,4,4,3),(154,4,4,4),(155,4,4,5),(156,4,4,6),(157,4,4,7),(158,4,4,8),(159,4,4,9),(160,4,4,10),(161,5,1,1),(162,5,1,2),(163,5,1,3),(164,5,1,4),(165,5,1,5),(166,5,1,6),(167,5,1,7),(168,5,1,8),(169,5,1,9),(170,5,1,10),(171,5,2,1),(172,5,2,2),(173,5,2,3),(174,5,2,4),(175,5,2,5),(176,5,2,6),(177,5,2,7),(178,5,2,8),(179,5,2,9),(180,5,2,10),(181,5,3,1),(182,5,3,2),(183,5,3,3),(184,5,3,4),(185,5,3,5),(186,5,3,6),(187,5,3,7),(188,5,3,8),(189,5,3,9),(190,5,3,10),(191,5,4,1),(192,5,4,2),(193,5,4,3),(194,5,4,4),(195,5,4,5),(196,5,4,6),(197,5,4,7),(198,5,4,8),(199,5,4,9),(200,5,4,10),(201,6,1,1),(202,6,1,2),(203,6,1,3),(204,6,1,4),(205,6,1,5),(206,6,1,6),(207,6,1,7),(208,6,1,8),(209,6,1,9),(210,6,1,10),(211,6,2,1),(212,6,2,2),(213,6,2,3),(214,6,2,4),(215,6,2,5),(216,6,2,6),(217,6,2,7),(218,6,2,8),(219,6,2,9),(220,6,2,10),(221,6,3,1),(222,6,3,2),(223,6,3,3),(224,6,3,4),(225,6,3,5),(226,6,3,6),(227,6,3,7),(228,6,3,8),(229,6,3,9),(230,6,3,10),(231,6,4,1),(232,6,4,2),(233,6,4,3),(234,6,4,4),(235,6,4,5),(236,6,4,6),(237,6,4,7),(238,6,4,8),(239,6,4,9),(240,6,4,10),(241,7,1,1),(242,7,1,2),(243,7,1,3),(244,7,1,4),(245,7,1,5),(246,7,1,6),(247,7,1,7),(248,7,1,8),(249,7,1,9),(250,7,1,10),(251,7,2,1),(252,7,2,2),(253,7,2,3),(254,7,2,4),(255,7,2,5),(256,7,2,6),(257,7,2,7),(258,7,2,8),(259,7,2,9),(260,7,2,10),(261,7,3,1),(262,7,3,2),(263,7,3,3),(264,7,3,4),(265,7,3,5),(266,7,3,6),(267,7,3,7),(268,7,3,8),(269,7,3,9),(270,7,3,10),(271,7,4,1),(272,7,4,2),(273,7,4,3),(274,7,4,4),(275,7,4,5),(276,7,4,6),(277,7,4,7),(278,7,4,8),(279,7,4,9),(280,7,4,10),(281,8,1,1),(282,8,1,2),(283,8,1,3),(284,8,1,4),(285,8,1,5),(286,8,1,6),(287,8,1,7),(288,8,1,8),(289,8,1,9),(290,8,1,10),(291,8,2,1),(292,8,2,2),(293,8,2,3),(294,8,2,4),(295,8,2,5),(296,8,2,6),(297,8,2,7),(298,8,2,8),(299,8,2,9),(300,8,2,10),(301,8,3,1),(302,8,3,2),(303,8,3,3),(304,8,3,4),(305,8,3,5),(306,8,3,6),(307,8,3,7),(308,8,3,8),(309,8,3,9),(310,8,3,10),(311,8,4,1),(312,8,4,2),(313,8,4,3),(314,8,4,4),(315,8,4,5),(316,8,4,6),(317,8,4,7),(318,8,4,8),(319,8,4,9),(320,8,4,10);
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `amount` decimal(19,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ptbien264@gmail.com','user1','password1',150000.00),(2,'bbiieennww@gmail.com','user2','password2',1000000.00),(3,'gido@gmail.com','user3','password3',0.00);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-19  0:04:45
