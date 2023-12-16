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
-- Table structure for table `quest_answercurs`
--

DROP TABLE IF EXISTS `quest_answercurs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quest_answercurs` (
  `idquest_answerCurs` int NOT NULL AUTO_INCREMENT,
  `quest` varchar(256) NOT NULL,
  `answer` varchar(90) NOT NULL,
  `idcomplexity` int NOT NULL,
  `idcategory` int NOT NULL,
  PRIMARY KEY (`idquest_answerCurs`),
  KEY `category_idx` (`idcategory`),
  KEY `complexity_idx` (`idcomplexity`),
  CONSTRAINT `category` FOREIGN KEY (`idcategory`) REFERENCES `category` (`idcategory`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `complexity` FOREIGN KEY (`idcomplexity`) REFERENCES `complexity` (`idcomplexity`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quest_answercurs`
--

LOCK TABLES `quest_answercurs` WRITE;
/*!40000 ALTER TABLE `quest_answercurs` DISABLE KEYS */;
INSERT INTO `quest_answercurs` VALUES (52,'Как называется крупнейшая планета Солнечной системы?','Юпитер',23,17),(53,'Какой вид транспорта движется по рельсам и тянется лошадьми?','Трамвай',23,19),(54,'Какое животное известно своими полосами черного и белого цвета?','Зебра',23,17),(55,'Как называется единица измерения времени, равная 60 минутам?','Час',23,19),(56,'Как называется голубой камень, используемый для изготовления украшений?','Сапфир',23,17),(57,'Какие имя и фамилия у главного героя фильма \"Властелин колец\"?','Фродо Бэггинс',22,16),(58,'Кто исполнил главную роль в фильме \"Звездные войны: Эпизод IV - Новая надежда\"?','Марк Хэмилл',22,16),(59,'Как звали главного антагониста в фильме \"Темный рыцарь\" (2008)?','Джокер',23,16),(60,'Какой фильм получил премию Оскар за лучший фильм в 2019 году?','Зелёная книга',25,16),(62,'Какая столица считается самой высокогорной среди столиц мира?','Лхаса',25,19),(63,'Какой элемент в периодической таблице обозначается как Fe?','Железо',23,17),(64,'Кто написал роман \"Преступление и наказание\"?','Фёдор Достоевский',23,20),(65,'Какое слово означает страх перед открытыми пространствами?','Агорафобия',22,19),(66,'Как называется процесс превращения жидкости в газ?','Испарение',23,19),(67,'Какой инструмент изображён на логотипе музыкальной компании RCA?','Граммофон',25,18),(68,'Как называется главный вокалист группы \"The Beatles\"?','Джон Леннон',22,18),(69,'Какой музыкальный инструмент считается \"королём джаза\"?','Саксофон',23,18),(70,'Какое музыкальное направление представляет собой группа ABBA?','Поп',22,18),(71,'Как называется японский традиционный музыкальный инструмент, напоминающий кинжал?','Самисэн',25,18),(72,'Хочешь есть, попей водички...?','Вот девиз анорексички',23,19),(77,'1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111','111111111111111',23,17);
/*!40000 ALTER TABLE `quest_answercurs` ENABLE KEYS */;
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
