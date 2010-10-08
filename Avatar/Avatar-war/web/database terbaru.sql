-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 08, 2010 at 02:41 PM
-- Server version: 5.1.41
-- PHP Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `avatar`
--

-- --------------------------------------------------------

--
-- Table structure for table `profile`
--

CREATE TABLE IF NOT EXISTS `profile` (
  `hotel_name` varchar(50) NOT NULL,
  `hotel_address1` varchar(75) NOT NULL,
  `hotel_address2` varchar(75) DEFAULT NULL,
  `hotel_city` varchar(25) NOT NULL,
  `hotel_country` varchar(25) NOT NULL,
  `hotel_email` text,
  `hotel_description` text,
  `hotel_phone` varchar(15) NOT NULL,
  PRIMARY KEY (`hotel_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `profile`
--

INSERT INTO `profile` (`hotel_name`, `hotel_address1`, `hotel_address2`, `hotel_city`, `hotel_country`, `hotel_email`, `hotel_description`, `hotel_phone`) VALUES
('Spons Hotel', 'Jalan Spons 112', 'Kelurahan Ceu Kecamatan Ceuceu', 'Jakarta', 'Indonesia', 'contact@sponshotel.co.id', 'Hotel ini berada di kawasan elit jakarta, terbebas dari semrawut dan kemacetan kota Jakarta', '085220280021');
