-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 06, 2010 at 11:25
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
-- Table structure for table `accomodation`
--

CREATE TABLE IF NOT EXISTS `accomodation` (
  `product_id` varchar(6) NOT NULL,
  `product_type` varchar(25) NOT NULL,
  `description` text,
  `image` text COMMENT 'path ke file image',
  `max_pax` int(11) NOT NULL,
  `normal_entry` time NOT NULL,
  `normal_exit` time NOT NULL,
  `weekday_rate` double NOT NULL,
  `weekend_rate` double NOT NULL,
  `tolerance_early` time NOT NULL,
  `tolerance_late` time NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accomodation`
--

INSERT INTO `accomodation` (`product_id`, `product_type`, `description`, `image`, `max_pax`, `normal_entry`, `normal_exit`, `weekday_rate`, `weekend_rate`, `tolerance_early`, `tolerance_late`) VALUES
('AC001', 'Standard Room', 'Comfort in budget-friendly room', 'images/facilities/rooms/AC001.jpg', 2, '12:30:00', '11:30:00', 545000, 605000, '00:30:00', '00:30:00'),
('AC002', 'Deluxe Room', 'A good choice for more comfort and space', 'images/facilities/rooms/AC002.jpg', 2, '12:30:00', '11:30:00', 645000, 710000, '00:30:00', '00:30:00'),
('AC003', 'Queen Room', 'Superior Non-smoking, Sweet Sleeper Bed, Private Balcony, Combined Shower/bath', 'images/facilities/rooms/AC003.jpg', 2, '12:30:00', '11:30:00', 555000, 600000, '00:30:00', '00:30:00'),
('AC004', 'King Room', 'Club Floor Non-smoking, Sweet Sleeper Bed, Buffet Breakfast, Club Lounge-free Access, Internet For Free', 'images/facilities/rooms/AC004.gif', 0, '12:30:00', '11:30:00', 700000, 750000, '00:00:00', '00:00:00'),
('AC005', 'Princess Room', 'Junior Suite Lake View, Sweet Sleeper Bed, Spacious With Sitting Area, Combined Shower/bath, Coffee/tea Maker', 'images/facilities/rooms/AC005.jpg', 0, '00:00:00', '00:00:00', 400000, 450000, '00:00:00', '00:00:00'),
('AC006', 'Prince Room', 'Junior Suite Lake View, Sweet Sleeper Bed, Spacious With Sitting Area, Combined Shower/bath, Coffee/tea Maker', 'images/facilities/rooms/AC006.jpg', 0, '00:00:00', '00:00:00', 500000, 550000, '00:00:00', '00:00:00'),
('AC007', 'Minister Room', 'Superior Non-smoking, Sweet Sleeper Bed, Private Balcony, Combined Shower/bath', 'images/facilities/rooms/AC007.jpg', 0, '00:00:00', '00:00:00', 700000, 750000, '00:00:00', '00:00:00'),
('AC008', 'Ambassador Room', 'Superior Lake View Non-smoking, Sweet Sleeper Bed, Spacious With Sitting Area, Comfortable Work Area, Coffee/tea Maker', 'images/facilities/rooms/AC008.gif', 0, '00:00:00', '00:00:00', 780000, 800000, '00:00:00', '00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `username` varchar(25) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` text NOT NULL,
  `email` text NOT NULL,
  `identity_type` varchar(10) NOT NULL,
  `identity_number` varchar(50) NOT NULL,
  `address1` varchar(75) NOT NULL,
  `address2` varchar(75) DEFAULT NULL,
  `city` varchar(25) NOT NULL,
  `country` varchar(25) NOT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `fk_has_address_in` (`city`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`username`, `name`, `password`, `email`, `identity_type`, `identity_number`, `address1`, `address2`, `city`, `country`, `telephone`) VALUES
('christian.h6191', 'Christian Hadiwinoto', 'eef3834d2d4b2affd133ed9bb6106687', 'if17081@students.if.itb.ac.id', 'Passport', 'V608081', 'Jalan Sultan Tirtayasa 43', NULL, 'Bandung', 'Indonesia', '+6281806115607'),
('customer', 'Andara Livia', '91ec1f9324753048c0096d036a694f86', 'andara.livia@live.com', 'Passport', 'C567456', 'Jl. Terusan Dago Pojok', NULL, 'Bandung', 'Indonesia', NULL),
('harlili', 'Harlili', '8b9963cd552debb75f29e76fbb0eee4a', 'nch048@yahoo.com', 'Passport', 'J778889', 'Jalan Pendawa 15', NULL, 'Bandung', 'Indonesia', NULL),
('joy123', 'Joy Fernandez', '0d5425118d7aaa57e0f18a9e0d87104b', 'nch048@yahoo.com', 'Passport', 'B123456', 'The Bellezza Apartment, Permata Hijau', NULL, 'Jakarta', 'Indonesia', NULL),
('ponimin4070', 'Ponimin', '1f178c2dc14b2c94179846b0250d847e', 'nch048@yahoo.com', 'Passport', 'R22381A', 'Sukhumvit Soi 105', NULL, 'Bangkok', 'Thailand', '+66819222333');

-- --------------------------------------------------------

--
-- Table structure for table `hall`
--

CREATE TABLE IF NOT EXISTS `hall` (
  `product_id` varchar(6) NOT NULL,
  `product_type` varchar(25) NOT NULL,
  `description` text,
  `normal_rate` double NOT NULL,
  `normal_rate_unit` varchar(10) NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `overcharge_unit` varchar(10) NOT NULL,
  `overcharge_rate` double NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hall`
--

INSERT INTO `hall` (`product_id`, `product_type`, `description`, `normal_rate`, `normal_rate_unit`, `start_time`, `end_time`, `overcharge_unit`, `overcharge_rate`) VALUES
('HL001', 'Half Day Meeting', 'Half Day Meeting Package from 08.00 - 13.00. <br />Facilities: (Meeting Room and Meeting Room Amenities, Lunch or Dinner, 1x Coffe break) ', 300000, 'pax', '08:00:00', '13:00:00', 'venue', 85000),
('HL002', 'Full Day Meeting', 'Full Day Meeting Package from 08.00 - 18.00. <br />Facilities: (Meeting Room and Meeting Room Amenities, Lunch or Dinner, 2x Coffe break) ', 600000, 'pax', '08:00:00', '18:00:00', 'venue', 100000),
('HL003', 'Wedding Party', 'Wedding Party. We also provide wedding organizer. In cooperation with Bee Wedding & Party Organizer provide one stop wedding package, where all your wedding needs are included so the wedding couple have nothing to worry and can fully concentrate to their daily activities.', 700000, 'pax', '00:00:00', '00:00:00', 'venue', 50000),
('HL004', 'Party', 'Any Party but Wedding. We also supported by Party Organizer who provides the entertainment for our guest as well as for public. <br />\r\n\r\nThe Party Organizer has successfully held some huge events like : <br />\r\n\r\n    * music concert of Maliq d´essential, Rio Febrian, Marcell, Glenn Fredly, Syahrani, Wong Pitoe, Buitenzorg Jazz with Idang Rasyidi & other Jazz Musicians, etc. <br />\r\n    * Bogor Auction, <br />\r\n    * Product Launching, <br />\r\n    * Exhibition, <br />\r\n    * etc', 500000, 'pax', '00:00:00', '00:00:00', 'venue', 50000);

-- --------------------------------------------------------

--
-- Table structure for table `hall_reservation`
--

CREATE TABLE IF NOT EXISTS `hall_reservation` (
  `reservation_item_id` int(11) NOT NULL,
  `product_id` varchar(6) NOT NULL,
  `begin_time` time NOT NULL,
  `end_time` time NOT NULL,
  `use_date` date NOT NULL,
  `attendees` int(11) NOT NULL,
  `venue_no` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`reservation_item_id`),
  KEY `fk_hall_ordered_in` (`product_id`),
  KEY `fk_is_used_in` (`venue_no`),
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hall_reservation`
--

INSERT INTO `hall_reservation` (`reservation_item_id`, `product_id`, `begin_time`, `end_time`, `use_date`, `attendees`, `venue_no`) VALUES
(7, 'HL001', '08:00:00', '13:00:00', '2010-09-23', 15, '7603'),
(12, 'HL002', '08:00:00', '18:00:00', '2010-10-06', 75, '7603'),
(25, 'HL003', '10:00:00', '13:00:00', '2010-11-28', 300, '7603'),
(27, 'HL004', '18:00:00', '21:00:00', '2010-11-27', 100, '7601'),
(30, 'HL001', '08:00:00', '11:00:00', '2010-11-30', 50, '7602'),
(33, 'HL002', '08:00:00', '18:00:00', '2010-12-03', 100, '7603');

-- --------------------------------------------------------

--
-- Table structure for table `layout`
--

CREATE TABLE IF NOT EXISTS `layout` (
  `layout_no` int(11) NOT NULL AUTO_INCREMENT,
  `layout_name` varchar(20) NOT NULL,
  PRIMARY KEY (`layout_no`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `layout`
--

INSERT INTO `layout` (`layout_no`, `layout_name`) VALUES
(1, 'dining'),
(2, 'meeting'),
(3, 'classroom'),
(4, 'theatre'),
(5, 'standing');

-- --------------------------------------------------------

--
-- Table structure for table `other_services`
--

CREATE TABLE IF NOT EXISTS `other_services` (
  `product_id` varchar(6) NOT NULL,
  `product_type` varchar(30) NOT NULL,
  `description` text,
  `image` text,
  `pricing_unit` varchar(10) NOT NULL,
  `unit_price` double NOT NULL,
  `published` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `other_services`
--

INSERT INTO `other_services` (`product_id`, `product_type`, `description`, `image`, `pricing_unit`, `unit_price`, `published`) VALUES
('C0001', 'Catering', 'Catering for party/meeting.', NULL, 'pax', 30000, 0),
('F0001', 'Breakfast', 'Breakfast is served. Wake up slowly and enjoy a morning at a ready-laid breakfast table. Our popular breakfast buffet has everything from scrambled eggs to smoothies and our breakfast staff will serve you tea and coffee on demand. Served every day', NULL, 'person', 50000, 1),
('F0002', 'Lunch', 'Savour our delicious lunch buffet. The table is laid and the meal begins with a salad buffet, followed by soup, a fish course and a meat course with hot vegetables that vary from day to day. To finish, we have a splendid dessert table with ten or so different sweets. Served Monday to Friday.', NULL, 'person', 45000, 1),
('F0003', 'Dinner', 'The world is full of different tastes and at Spons threesixty° our aim is to enjoy them all. We serve tasty drinks and beautifully prepared food that finds its inspiration in every corner of the world, so we hope that you will feel at home, wherever you''re from. We are untiring in gathering ideas and flavours from different places, with the aim of exploring a whole world of taste.', NULL, 'person', 75000, 1),
('G0001', 'Aerobic Exercise Guide', 'Learn Poco Poco dance from the instructor. This facility is free to used by all our guests and the members of our fitness club. ', NULL, '', 0, 1),
('G0002', 'Gym Exercise Guide', 'Enlarge your muscle instructed by professional trainer. This facility is free to used by all our guests and the members of our fitness club. ', 'images/facilities/others/G0002.jpg', '', 0, 1),
('M0001', 'Classic Upper Body Massage', 'Our professional massage therapists provide treatments that will increase your well-being, give you new energy and relieve any pain. We accept reservations seven days a week 08:00 to 21:00 and we are happy to help you choose the treatment that best suits your needs. You can choose if you want the massage in your hotel room, In-Room Spa, or in our massage room, which is adjacent to the Fitness Centre on the first floor of the hotel. Welcome to relax!', 'images/facilities/others/M0001.jpg', '', 100000, 1),
('M0002', 'Classic Full Body Massage', 'Classic Full Body Massage. Full Body!', 'images/facilities/others/M0002.jpg', '', 300000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `other_services_reservation`
--

CREATE TABLE IF NOT EXISTS `other_services_reservation` (
  `reservation_item_id` int(11) NOT NULL,
  `product_id` varchar(6) NOT NULL,
  `note` text NOT NULL,
  PRIMARY KEY (`reservation_item_id`),
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `other_services_reservation`
--

INSERT INTO `other_services_reservation` (`reservation_item_id`, `product_id`, `note`) VALUES
(3, 'F0002', 'Oxtail Soup + Rice. Mango Juice'),
(4, 'F0001', 'Fried Rice, Orange Juice. Deliver before 6am tomorrow'),
(5, 'M0001', ''),
(8, 'C0001', ''),
(13, 'F0001', ''),
(14, 'F0001', ''),
(15, 'F0001', ''),
(19, 'M0001', ''),
(20, 'G0001', ''),
(21, 'G0002', '');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE IF NOT EXISTS `payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `reservation_id` int(11) NOT NULL,
  `confirm_time` datetime NOT NULL,
  `username` varchar(25) DEFAULT NULL,
  `payment_date` date NOT NULL,
  `payment_method` varchar(15) NOT NULL,
  `payment_bank` varchar(20) NOT NULL,
  `account_number` varchar(45) NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`payment_id`),
  UNIQUE KEY `reservation_id` (`reservation_id`),
  KEY `fk_checks` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`payment_id`, `reservation_id`, `confirm_time`, `username`, `payment_date`, `payment_method`, `payment_bank`, `account_number`, `amount`) VALUES
(1, 1, '2010-09-07 11:54:59', 'receptionist', '2010-09-07', 'cash', 'none', 'none', 3600000),
(2, 2, '2010-09-18 12:53:12', 'receptionist', '2010-09-17', 'transfer', 'BNI', '0129903495', 5590000),
(3, 3, '2010-09-24 13:00:00', 'receptionist', '2010-09-24', 'cash', 'none', 'none', 450000),
(4, 4, '2010-10-06 10:50:55', 'receptionist', '2010-10-06', 'credit card', 'BCA', '17889890', 48420000),
(5, 5, '2010-10-08 10:51:45', 'receptionist', '2010-10-07', 'transfer', 'BCA', '111909090', 3520000),
(6, 6, '2010-10-19 10:54:24', 'receptionist', '2010-10-19', 'cash', 'none', 'none', 1520000),
(7, 7, '2010-10-15 10:55:05', 'receptionist', '2010-10-15', 'none', 'none', 'none', 0),
(8, 8, '2010-10-15 10:56:30', 'receptionist', '2010-10-15', 'none', 'none', 'none', 0),
(9, 9, '2010-10-18 10:57:32', 'receptionist', '2010-11-18', 'cash', 'none', 'none', 1755000),
(10, 10, '2010-10-22 10:59:54', 'receptionist', '2010-10-20', 'transfer', 'BNI', '0129903456', 2065000),
(11, 12, '2010-11-01 11:01:11', NULL, '2010-10-30', 'transfer', 'Mandiri', '12330788', 450000),
(12, 13, '2010-11-04 11:01:48', NULL, '2010-11-02', 'transfer', 'BRI', '1932832932', 15000000);

-- --------------------------------------------------------

--
-- Table structure for table `profile`
--

CREATE TABLE IF NOT EXISTS `profile` (
  `id` tinyint(1) NOT NULL,
  `hotel_name` varchar(50) NOT NULL,
  `hotel_address1` varchar(75) NOT NULL,
  `hotel_address2` varchar(75) DEFAULT NULL,
  `hotel_city` varchar(25) NOT NULL,
  `hotel_country` varchar(25) NOT NULL,
  `hotel_email` text,
  `hotel_description` text,
  `hotel_phone` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `profile`
--

INSERT INTO `profile` (`id`, `hotel_name`, `hotel_address1`, `hotel_address2`, `hotel_city`, `hotel_country`, `hotel_email`, `hotel_description`, `hotel_phone`) VALUES
(1, 'Hotel Graha', 'Jl. Ir. Hi. Djuanda 45', 'Kecamatan Coblong, Kelurahan Dago', 'Bandung', 'Indonesia', 'contact@hotelgraha.co.id', 'Welcome to Hotel Graha Bandung, a five star luxury hotel in Bandung, West Java. Located in the heart of Bandung, we provide a superior level of service to satisfy the needs of our global guests. As a member of the "Hotel Graha" group, the Hotel Graha Bandung offers an extraordinary level of service and coordinated interior designs. Restored to its original opulence, Hotel Graha Bandung sets the standard for elegance. From the magnificent columned lobby with its marble floors and stained-glass dome to the classic décor of the 250 guestrooms and suites, we ensures that every stay is a memorable one.', '+6222 786567');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE IF NOT EXISTS `reservation` (
  `reservation_id` int(11) NOT NULL AUTO_INCREMENT,
  `is_onspot` tinyint(1) NOT NULL,
  `username` varchar(25) NOT NULL,
  `note` text NOT NULL,
  PRIMARY KEY (`reservation_id`),
  KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`reservation_id`, `is_onspot`, `username`, `note`) VALUES
(1, 1, 'customer', ''),
(2, 0, 'customer', ''),
(3, 1, 'customer', 'reservation id: 2'),
(4, 1, 'christian.h6191', ''),
(5, 0, 'customer', ''),
(6, 1, 'christian.h6191', ''),
(7, 1, 'customer', 'reservation id: 5'),
(8, 1, 'customer', 'reservation id: 5'),
(9, 1, 'customer', ''),
(10, 0, 'christian.h6191', ''),
(11, 0, 'customer', '(payment invalid)'),
(12, 0, 'christian.h6191', ''),
(13, 0, 'joy123', ''),
(14, 0, 'joy123', ''),
(15, 0, 'customer', '');

-- --------------------------------------------------------

--
-- Table structure for table `reservation_item`
--

CREATE TABLE IF NOT EXISTS `reservation_item` (
  `reservation_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `reservation_id` int(11) NOT NULL,
  `reservation_time` datetime NOT NULL,
  `price` double NOT NULL DEFAULT '0',
  `DTYPE` char(1) NOT NULL,
  PRIMARY KEY (`reservation_item_id`),
  KEY `fk_validates` (`price`),
  KEY `reservation_id` (`reservation_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=36 ;

--
-- Dumping data for table `reservation_item`
--

INSERT INTO `reservation_item` (`reservation_item_id`, `reservation_id`, `reservation_time`, `price`, `DTYPE`) VALUES
(1, 1, '2010-09-05 08:46:19', 1710000, 'R'),
(2, 1, '2010-09-05 08:46:19', 1695000, 'R'),
(3, 1, '2010-09-05 12:46:19', 45000, 'O'),
(4, 1, '2010-09-05 20:46:19', 50000, 'O'),
(5, 1, '2010-09-06 11:29:12', 100000, 'O'),
(6, 2, '2010-09-17 11:48:21', 1090000, 'R'),
(7, 2, '2010-09-17 11:48:21', 4500000, 'H'),
(8, 3, '2010-09-22 12:46:38', 450000, 'O'),
(9, 4, '2010-10-05 08:31:21', 1090000, 'R'),
(10, 4, '2010-10-05 08:31:21', 1090000, 'R'),
(11, 4, '2010-10-05 08:31:21', 1090000, 'R'),
(12, 4, '2010-10-05 08:31:21', 45000000, 'H'),
(13, 4, '2010-10-05 08:31:21', 50000, 'O'),
(14, 4, '2010-10-05 08:31:21', 50000, 'O'),
(15, 4, '2010-10-05 08:31:21', 50000, 'O'),
(16, 5, '2010-10-06 08:40:04', 2310000, 'R'),
(17, 5, '2010-10-06 08:40:04', 1210000, 'R'),
(18, 6, '2010-10-15 08:41:53', 1420000, 'R'),
(19, 6, '2010-10-16 10:41:53', 100000, 'O'),
(20, 8, '2010-10-15 08:41:53', 0, 'O'),
(21, 8, '2010-10-15 08:41:53', 0, 'O'),
(22, 9, '2010-10-15 08:41:53', 1755000, 'R'),
(23, 10, '2010-10-18 19:41:53', 2065000, 'R'),
(24, 11, '2010-10-18 21:41:53', 1755000, 'R'),
(25, 11, '2010-10-18 21:41:53', 210000000, 'H'),
(26, 12, '2010-10-19 11:41:53', 600000, 'R'),
(27, 13, '2010-10-19 20:41:53', 50000000, 'H'),
(28, 13, '2010-10-19 20:41:53', 1635000, 'R'),
(29, 13, '2010-10-19 20:41:53', 1635000, 'R'),
(30, 13, '2010-10-19 20:41:53', 15000000, 'H'),
(31, 14, '2010-10-19 21:41:53', 545000, 'R'),
(32, 14, '2010-10-19 21:41:53', 545000, 'R'),
(33, 14, '2010-10-19 21:41:53', 60000000, 'H'),
(34, 15, '2010-10-19 22:41:53', 1695000, 'R'),
(35, 15, '2010-10-19 22:41:53', 1695000, 'R');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE IF NOT EXISTS `room` (
  `room_no` varchar(6) NOT NULL,
  `product_id` varchar(6) NOT NULL,
  `room_name` varchar(20) DEFAULT NULL,
  `floor` int(11) NOT NULL,
  PRIMARY KEY (`room_no`),
  KEY `fk_has` (`product_id`),
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`room_no`, `product_id`, `room_name`, `floor`) VALUES
('101', 'AC003', NULL, 1),
('102', 'AC001', NULL, 1),
('103', 'AC001', NULL, 1),
('104', 'AC001', NULL, 1),
('105', 'AC001', NULL, 1),
('106', 'AC001', NULL, 1),
('107', 'AC001', NULL, 1),
('108', 'AC001', NULL, 1),
('109', 'AC001', NULL, 1),
('110', 'AC001', NULL, 1),
('111', 'AC001', NULL, 1),
('112', 'AC002', NULL, 1),
('113', 'AC002', NULL, 1),
('201', 'AC003', NULL, 2),
('202', 'AC001', NULL, 2),
('203', 'AC001', NULL, 2);

-- --------------------------------------------------------

--
-- Table structure for table `room_reservation`
--

CREATE TABLE IF NOT EXISTS `room_reservation` (
  `reservation_item_id` int(11) NOT NULL,
  `room_no` varchar(6) NOT NULL,
  `entry_date` date NOT NULL,
  `exit_date` date NOT NULL,
  `actual_entry` datetime DEFAULT NULL,
  `actual_exit` datetime DEFAULT NULL,
  PRIMARY KEY (`reservation_item_id`),
  KEY `room_no` (`room_no`),
  KEY `reservation_item_id` (`reservation_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `room_reservation`
--

INSERT INTO `room_reservation` (`reservation_item_id`, `room_no`, `entry_date`, `exit_date`, `actual_entry`, `actual_exit`) VALUES
(1, '101', '2010-09-05', '2010-09-07', '2010-09-05 08:46:19', '2010-09-07 21:19:03'),
(2, '102', '2010-09-05', '2010-09-07', '2010-09-05 08:46:19', '2010-09-07 21:19:03'),
(6, '102', '2010-09-22', '2010-09-24', '2010-09-22 12:46:38', '2010-09-24 12:46:46'),
(9, '108', '2010-10-05', '2010-10-06', '2010-10-05 08:31:21', '2010-10-06 18:31:21'),
(10, '109', '2010-10-05', '2010-10-06', '2010-10-05 08:31:21', '2010-10-06 18:31:21'),
(11, '110', '2010-10-05', '2010-10-06', '2010-10-05 08:31:21', '2010-10-06 18:31:21'),
(16, '201', '2010-10-15', '2010-10-18', '2010-10-15 08:41:53', '2010-10-18 08:41:53'),
(17, '203', '2010-10-16', '2010-10-18', '2010-10-16 08:41:53', '2010-10-18 08:41:53'),
(18, '113', '2010-10-15', '2010-10-19', '2010-10-15 08:41:53', '2010-10-19 08:41:53'),
(22, '202', '2010-10-16', '2010-10-18', '2010-10-16 08:41:53', '2010-10-18 08:41:53'),
(23, '112', '2010-11-20', '2010-11-22', NULL, NULL),
(24, '111', '2010-11-27', '2010-11-29', NULL, NULL),
(26, '201', '2010-11-20', '2010-11-21', NULL, NULL),
(28, '111', '2010-11-29', '2010-12-01', NULL, NULL),
(29, '106', '2010-11-29', '2010-12-01', NULL, NULL),
(31, '109', '2010-12-02', '2010-12-03', NULL, NULL),
(32, '110', '2010-12-02', '2010-12-03', NULL, NULL),
(34, '203', '2010-12-30', '2011-01-01', NULL, NULL),
(35, '202', '2010-12-30', '2011-01-01', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE IF NOT EXISTS `staff` (
  `username` varchar(25) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` text NOT NULL,
  `email` text NOT NULL,
  `employment_id` varchar(20) NOT NULL,
  `position` smallint(6) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`username`, `name`, `password`, `email`, `employment_id`, `position`) VALUES
('admin', 'Admin', '21232f297a57a5a743894a0e4a801fc3', 'nch048@yahoo.com', '9087654312', 0),
('ahmaddo', 'Ahmad Dodolin', '8914a32c5cbcc14610a24eb8ca64a221', 'ahmaddo@graha.net', '7807098711', 1),
('bambangb', 'Bambang Burangrang', 'c77a113a13f489395b869491031e9b24', 'nch048@yahoo.com', '1234567890', 0),
('manager', 'Manager', '1d0258c2440a8d19e716292b231e3190', 'manager@man.com', '5678888888', 2),
('receptionist', 'Dewi Aryani', '0a9b3767c8b9b69cea129110e8daeda2', 'e.k_moe@yahoo.com', '1209902010', 1),
('Restya19', 'Restya Winda A.', 'defe1ef55f723634cc31f440ad6abeda', 'restya_wa@yahoo.co.id', '198806032010092001', 1),
('Zulfikar19', 'Zulfikar Hakim', '94167761e0943c3c8ffc8142698857df', 'zulfikar_165@students.itb.ac.id', '199002192010091001', 0);

-- --------------------------------------------------------

--
-- Table structure for table `telephone`
--

CREATE TABLE IF NOT EXISTS `telephone` (
  `phone_type` varchar(10) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  PRIMARY KEY (`phone_number`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `telephone`
--

INSERT INTO `telephone` (`phone_type`, `phone_number`) VALUES
('office', '+622270470470'),
('fax', '+622270470471');

-- --------------------------------------------------------

--
-- Table structure for table `venue`
--

CREATE TABLE IF NOT EXISTS `venue` (
  `venue_no` varchar(6) NOT NULL,
  `venue_name` varchar(25) NOT NULL,
  `description` text,
  `image` text COMMENT 'path ke file image',
  PRIMARY KEY (`venue_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `venue`
--

INSERT INTO `venue` (`venue_no`, `venue_name`, `description`, `image`) VALUES
('7601', 'Mahakarya Ballroom', 'The largest meeting room that we have. It is ideal for wedding receptions, conventions, incentives and exhibitions as well as company gathering. ', 'images/facilities/meetings/7601.jpg'),
('7602', 'Arjuna Lounge', 'As a pleasant place for lobbying & relaxing, Arjuna Lounge has a perfect atmosphere for you to relax after your busy day. Chatting with friends, enjoying soft music, or watching national and international TV channels while having a wide range of tasty beverages and delicate light meals in a comfortable couch.', 'images/facilities/meetings/7602.jpg'),
('7603', 'Brahmastra Hall', 'Ideal for wedding receptions, conventions, incentives and exhibitions as well as company gathering. ', 'images/facilities/meetings/7603.jpg');

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
('7601', 5, 160),
('7602', 1, 50),
('7602', 2, 75),
('7602', 3, 100),
('7602', 4, 150),
('7602', 5, 200),
('7603', 1, 75),
('7603', 2, 100),
('7603', 3, 140),
('7603', 4, 210),
('7603', 5, 300);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `hall_reservation`
--
ALTER TABLE `hall_reservation`
  ADD CONSTRAINT `hall_reservation_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `hall` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `hall_reservation_ibfk_3` FOREIGN KEY (`venue_no`) REFERENCES `venue` (`venue_no`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `hall_reservation_ibfk_4` FOREIGN KEY (`reservation_item_id`) REFERENCES `reservation_item` (`reservation_item_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `other_services_reservation`
--
ALTER TABLE `other_services_reservation`
  ADD CONSTRAINT `other_services_reservation_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `other_services` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `other_services_reservation_ibfk_2` FOREIGN KEY (`reservation_item_id`) REFERENCES `reservation_item` (`reservation_item_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `payment_ibfk_2` FOREIGN KEY (`username`) REFERENCES `staff` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`username`) REFERENCES `customer` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `reservation_item`
--
ALTER TABLE `reservation_item`
  ADD CONSTRAINT `reservation_item_ibfk_1` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `room_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `accomodation` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `room_reservation`
--
ALTER TABLE `room_reservation`
  ADD CONSTRAINT `room_reservation_ibfk_1` FOREIGN KEY (`room_no`) REFERENCES `room` (`room_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `room_reservation_ibfk_2` FOREIGN KEY (`reservation_item_id`) REFERENCES `reservation_item` (`reservation_item_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `venue_layout`
--
ALTER TABLE `venue_layout`
  ADD CONSTRAINT `venue_layout_ibfk_1` FOREIGN KEY (`venue_no`) REFERENCES `venue` (`venue_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `venue_layout_ibfk_2` FOREIGN KEY (`layout_no`) REFERENCES `layout` (`layout_no`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

