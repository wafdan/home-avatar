-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 14, 2010 at 08:09 
-- Server version: 5.1.37
-- PHP Version: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `avatar`
--

-- --------------------------------------------------------

--
-- Table structure for table `venue_layout`
--

CREATE TABLE IF NOT EXISTS `venue_layout` (
  `venue_no` varchar(6) NOT NULL,
  `layout_no` int(11) NOT NULL,
  `capacity` int(11) NOT NULL,
  PRIMARY KEY (`venue_no`,`layout_no`),
  KEY `fk_has_layout` (`venue_no`),
  KEY `fk_is_applied_in` (`layout_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `venue_layout`
--

INSERT INTO `venue_layout` (`venue_no`, `layout_no`, `capacity`) VALUES
('7601', 1, 80),
('7601', 2, 100),
('7601', 3, 120),
('7601', 4, 140),
('7601', 5, 200),
('7601', 6, 155),
('7602', 1, 50),
('7602', 2, 75),
('7602', 3, 100),
('7602', 4, 150),
('7602', 5, 200),
('7602', 6, 185),
('7603', 1, 80),
('7603', 2, 100),
('7603', 3, 140),
('7603', 4, 210),
('7603', 5, 300),
('7603', 6, 280);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `venue_layout`
--
ALTER TABLE `venue_layout`
  ADD CONSTRAINT `venue_layout_ibfk_1` FOREIGN KEY (`venue_no`) REFERENCES `venue` (`venue_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `venue_layout_ibfk_2` FOREIGN KEY (`layout_no`) REFERENCES `layout` (`layout_no`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
