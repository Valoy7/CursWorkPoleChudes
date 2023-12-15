-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `gamers`
--

DROP TABLE IF EXISTS `gamers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gamers` (
  `idgamers` int NOT NULL AUTO_INCREMENT,
  `idusers` int NOT NULL,
  `nickname` varchar(60) NOT NULL,
  `lastScore` int DEFAULT NULL,
  `maxScore` int DEFAULT NULL,
  PRIMARY KEY (`idgamers`),
  KEY `idusers_1_idx` (`idusers`),
  CONSTRAINT `idusers_1` FOREIGN KEY (`idusers`) REFERENCES `users2` (`idusers`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gamers`
--

LOCK TABLES `gamers` WRITE;
/*!40000 ALTER TABLE `gamers` DISABLE KEYS */;
INSERT INTO `gamers` VALUES (66,8,'perv',2400,2400),(67,8,'vtor',1550,1550),(68,8,'tret',300,300),(69,8,'chetv',300,300),(70,8,'123',350,700),(71,7,'perv',500,500),(72,7,'555',0,NULL),(73,8,'sddf',400,400),(74,8,'1234',600,600),(75,8,'ыфа',0,NULL),(76,8,'4314',NULL,NULL),(77,8,'23',0,500),(78,8,'22',350,500),(79,8,'12',NULL,NULL),(80,8,'1',0,6150),(81,7,'7',NULL,NULL),(82,8,'2',0,16950),(83,8,'3',NULL,NULL),(84,8,'4',NULL,NULL),(85,8,'5',NULL,NULL),(86,8,'п',NULL,NULL),(87,8,'ff',1450,1450),(88,8,'677',550,550),(89,8,'petya',900,900),(90,8,'11',400,400),(91,8,'456',2100,2100);
/*!40000 ALTER TABLE `gamers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-15 10:53:31
