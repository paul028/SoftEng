-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 19, 2017 at 04:01 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `homecontrol`
--
CREATE DATABASE IF NOT EXISTS `homecontrol` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `homecontrol`;

-- --------------------------------------------------------

--
-- Table structure for table `admin_function`
--

DROP TABLE IF EXISTS `admin_function`;
CREATE TABLE `admin_function` (
  `function_id` int(7) NOT NULL,
  `function_name` varchar(50) NOT NULL,
  `function_description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin_function`
--

INSERT INTO `admin_function` (`function_id`, `function_name`, `function_description`) VALUES
(1, 'Added New User', 'Add User'),
(2, 'Removed User', 'Remove User'),
(3, 'Added Controls', 'Added Controls'),
(4, 'Removed Controls', 'Remove Controls'),
(5, 'Updated Roles', 'Update Roles');

-- --------------------------------------------------------

--
-- Table structure for table `admin_log`
--

DROP TABLE IF EXISTS `admin_log`;
CREATE TABLE `admin_log` (
  `adminlog_id` int(10) NOT NULL,
  `function_id` int(7) NOT NULL,
  `user_id` int(7) NOT NULL,
  `admin_id` int(7) NOT NULL,
  `date_performed` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `appliance`
--

DROP TABLE IF EXISTS `appliance`;
CREATE TABLE `appliance` (
  `app_id` int(7) NOT NULL,
  `appcat_id` int(7) NOT NULL,
  `room_id` int(7) NOT NULL,
  `app_name` varchar(50) NOT NULL,
  `app_description` text NOT NULL,
  `app_state` int(2) NOT NULL,
  `device_id` int(10) NOT NULL,
  `gpio` int(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appliance`
--

INSERT INTO `appliance` (`app_id`, `appcat_id`, `room_id`, `app_name`, `app_description`, `app_state`, `device_id`, `gpio`) VALUES
(1, 1, 1, 'Masters-Lights1', 'MasterBeadroom Light', 1, 1, 2),
(2, 1, 1, 'Masters-Lights2', 'Lights2', 0, 1, 3),
(3, 1, 1, 'Masters-Lights3', 'Lights3', 0, 1, 4),
(4, 1, 2, 'Room1-Lights1', 'Lights1', 1, 1, 17),
(5, 1, 2, 'Room1-Lights2', 'Lights2', 0, 1, 27),
(7, 1, 3, 'Room2-Lights1', 'Lights1', 0, 1, 22),
(13, 1, 5, 'LivingRoomLights1', 'Lights1', 0, 1, 10),
(14, 1, 5, 'LivingRoomLights2', 'Lights2', 0, 1, 9),
(16, 1, 6, 'KitchenLights1', 'Lights1', 0, 1, 11),
(19, 1, 7, 'GatesLight1', 'Lights1', 0, 1, 5),
(22, 2, 3, 'Room2-Socket1', 'Socket1', 1, 1, 0),
(23, 2, 3, 'Room2-Socket2', 'Socket2', 1, 1, 0),
(24, 2, 3, 'Room2-Socket3', 'Socket3', 0, 1, 0),
(28, 2, 5, 'LivingRoomSocket1', 'Socket1', 0, 1, 0),
(29, 2, 5, 'LivingRoomSocket2', 'Socket2', 0, 1, 0),
(30, 2, 5, 'LivingRoomSocket3', 'Socket3', 0, 1, 0),
(31, 2, 6, 'KitchenSocket1', 'Socket1', 0, 1, 0),
(32, 2, 6, 'KitchenSocket2', 'Socket2', 0, 1, 0),
(33, 2, 6, 'KitchenSocket3', 'Socket3', 0, 1, 0),
(34, 3, 7, 'GateAlarm', 'GateAlarm', 0, 1, 0),
(35, 2, 1, 'Masters-Socket1', 'Socket1', 0, 1, 0),
(36, 2, 1, 'Masters-Socket2', 'Socket2', 1, 1, 0),
(37, 2, 1, 'Masters-Socket3', 'Socket3', 0, 1, 0),
(38, 2, 2, 'Room1-Socket1', 'Socket1', 0, 1, 0),
(39, 2, 2, 'Room1-Socket2', 'Socket2', 0, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `appliance_log`
--

DROP TABLE IF EXISTS `appliance_log`;
CREATE TABLE `appliance_log` (
  `applog_id` int(7) NOT NULL,
  `app_id` int(7) NOT NULL,
  `user_id` int(7) NOT NULL,
  `log_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `state` enum('ON','OFF') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appliance_log`
--

INSERT INTO `appliance_log` (`applog_id`, `app_id`, `user_id`, `log_time`, `state`) VALUES
(9, 36, 1, '2017-09-13 16:56:43', 'OFF'),
(10, 36, 1, '2017-09-13 16:56:45', 'ON'),
(11, 37, 1, '2017-09-13 16:56:48', 'OFF'),
(12, 2, 1, '2017-09-14 14:46:10', 'OFF'),
(13, 1, 1, '2017-09-14 14:46:16', 'OFF'),
(14, 1, 1, '2017-09-14 14:46:34', 'ON'),
(15, 1, 1, '2017-09-14 14:46:51', 'ON'),
(16, 1, 1, '2017-09-14 14:46:54', 'OFF'),
(17, 1, 1, '2017-09-14 14:48:22', 'ON'),
(18, 1, 1, '2017-09-14 14:48:23', 'ON'),
(19, 1, 1, '2017-09-14 14:48:30', 'ON'),
(20, 1, 1, '2017-09-14 14:48:31', 'ON'),
(21, 1, 1, '2017-09-14 14:48:32', 'ON'),
(22, 1, 1, '2017-09-14 14:57:05', 'ON'),
(23, 1, 1, '2017-09-14 14:57:42', 'ON'),
(24, 1, 1, '2017-09-14 14:59:02', 'ON'),
(25, 1, 1, '2017-09-14 15:29:48', 'ON'),
(26, 1, 1, '2017-09-14 15:31:26', 'ON'),
(27, 1, 1, '2017-09-14 15:32:54', 'ON'),
(28, 1, 1, '2017-09-14 15:33:05', 'OFF'),
(29, 1, 1, '2017-09-17 18:43:26', 'ON'),
(30, 23, 1, '2017-09-19 11:08:30', 'ON'),
(31, 35, 1, '2017-09-19 11:08:50', 'OFF'),
(32, 23, 1, '2017-09-19 11:08:58', 'OFF'),
(33, 23, 1, '2017-09-19 11:09:28', 'ON'),
(34, 23, 1, '2017-09-19 11:10:08', 'OFF'),
(35, 23, 3, '2017-09-19 13:50:00', 'ON'),
(36, 22, 3, '2017-09-19 13:50:02', 'ON');

-- --------------------------------------------------------

--
-- Table structure for table `application_category`
--

DROP TABLE IF EXISTS `application_category`;
CREATE TABLE `application_category` (
  `appcat_id` int(7) NOT NULL,
  `appcat_name` varchar(50) NOT NULL,
  `appcat_description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `application_category`
--

INSERT INTO `application_category` (`appcat_id`, `appcat_name`, `appcat_description`) VALUES
(1, 'Lights', 'Lights'),
(2, 'Alarms', 'Alarms'),
(3, 'Socket', 'Socket');

-- --------------------------------------------------------

--
-- Table structure for table `controllers`
--

DROP TABLE IF EXISTS `controllers`;
CREATE TABLE `controllers` (
  `control_id` int(7) NOT NULL,
  `app_id` int(7) NOT NULL,
  `user_id` int(7) NOT NULL,
  `grant_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `controllers`
--

INSERT INTO `controllers` (`control_id`, `app_id`, `user_id`, `grant_date`) VALUES
(37, 1, 1, '2017-09-15 04:30:47'),
(38, 2, 1, '2017-09-15 04:30:47'),
(39, 3, 1, '2017-09-15 04:30:47'),
(40, 35, 1, '2017-09-15 04:30:47'),
(41, 36, 1, '2017-09-15 04:30:47'),
(42, 37, 1, '2017-09-15 04:30:47'),
(43, 19, 1, '2017-09-15 04:30:47'),
(44, 34, 1, '2017-09-15 04:30:47'),
(45, 4, 1, '2017-09-15 04:30:47'),
(46, 5, 1, '2017-09-15 04:30:47'),
(47, 38, 1, '2017-09-15 04:30:47'),
(48, 39, 1, '2017-09-15 04:30:47'),
(49, 7, 1, '2017-09-15 04:30:47'),
(50, 22, 1, '2017-09-15 04:30:47'),
(51, 23, 1, '2017-09-15 04:30:47'),
(52, 24, 1, '2017-09-15 04:30:47'),
(53, 13, 1, '2017-09-15 04:30:47'),
(54, 14, 1, '2017-09-15 04:30:47'),
(55, 28, 1, '2017-09-15 04:30:47'),
(56, 29, 1, '2017-09-15 04:30:47'),
(57, 30, 1, '2017-09-15 04:30:47'),
(58, 16, 1, '2017-09-15 04:30:47'),
(59, 31, 1, '2017-09-15 04:30:47'),
(60, 32, 1, '2017-09-15 04:30:47'),
(61, 33, 1, '2017-09-15 04:30:47'),
(79, 23, 4, '2017-09-15 04:34:44'),
(80, 24, 4, '2017-09-15 04:34:44'),
(81, 19, 4, '2017-09-15 04:34:44'),
(82, 34, 4, '2017-09-15 04:34:44'),
(85, 13, 5, '2017-09-15 04:35:38'),
(97, 1, 2, '2017-09-19 12:34:09'),
(98, 2, 2, '2017-09-19 12:34:35'),
(101, 2, 3, '2017-09-19 13:00:44'),
(102, 1, 3, '2017-09-19 13:01:06'),
(103, 3, 3, '2017-09-19 13:01:08'),
(104, 5, 3, '2017-09-19 13:05:24'),
(105, 7, 3, '2017-09-19 13:05:27'),
(106, 13, 3, '2017-09-19 13:05:29'),
(107, 14, 3, '2017-09-19 13:05:30'),
(108, 19, 3, '2017-09-19 13:05:32'),
(109, 16, 3, '2017-09-19 13:05:33'),
(110, 23, 3, '2017-09-19 13:05:34'),
(111, 22, 3, '2017-09-19 13:05:36'),
(112, 38, 3, '2017-09-19 13:05:38'),
(113, 39, 3, '2017-09-19 13:05:40'),
(114, 37, 3, '2017-09-19 14:00:43');

-- --------------------------------------------------------

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `device_id` int(10) NOT NULL,
  `device_name` varchar(20) NOT NULL,
  `mac_address` varchar(60) NOT NULL,
  `location` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `device`
--

INSERT INTO `device` (`device_id`, `device_name`, `mac_address`, `location`) VALUES
(1, 'Controller1', 'b8:27:eb:96:5d:46', 'Home');

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
CREATE TABLE `rooms` (
  `room_id` int(7) NOT NULL,
  `room_name` varchar(50) NOT NULL,
  `room_description` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`room_id`, `room_name`, `room_description`) VALUES
(1, 'Masters Beadroom', 'Masters Beadroom'),
(2, 'Room1', 'Room1'),
(3, 'Room2', 'Room2'),
(5, 'LivingRoom', 'LivingRoom'),
(6, 'Kitchen', 'Kitchen'),
(7, 'Gate', 'Gates');

-- --------------------------------------------------------

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `user_id` int(7) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `email_address` varchar(50) NOT NULL,
  `role` enum('user','admin') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_account`
--

INSERT INTO `user_account` (`user_id`, `firstname`, `lastname`, `username`, `password`, `email_address`, `role`) VALUES
(1, 'Paul', 'Nonat', 'user', 'pass', 'paulvincentnonat@gmail.com', 'admin'),
(2, 'Nalds', 'Noceja', 'nalds', 'password', 'naldsnocejafeb13@gmail.com', 'user'),
(3, 'Nathalie', 'Moron', 'nath', 'password', 'aliehuqueriza@gmail.com', 'user'),
(4, 'Jeffrey', 'Pabilonia', 'jeff', 'password', 'jeffreypabilonia13@gmail.com', 'user'),
(5, 'Eduardo', 'Narciso', 'nars', 'password', 'eduardojr@gmail.com', 'user'),
(6, 'pbil', 'fshsjd', 'pabilon', 'password', 'pabilon@gmail.com', 'user'),
(7, 'bdhshsja', 'vdhsjaka', 'pabinathnath', 'password', 'shdjakfbridjs@gmail.com', 'user'),
(8, 'dbdjsksmfbf', 'hdjskakdbdv', 'useruseruseruse', 'passwotd', 'fhsknfbbdbbsksoeue@gmail.com', 'user'),
(9, 'gxjxgxgx', 'bhxhdhxhxh', 'hogogmvmytv', 'password', 'vnvfjfjcnf@gmail.com', 'user'),
(10, 'ncjcjvvvvv', 'gvvbhgf', 'gyhggft', 'password', 'djvvfgvvcvbn@gmail.com', 'user'),
(11, 'fjfjfjcjr', 'dhchcurhrc', 'nonatnath', 'password', 'nonatnath@gmail.com', 'user'),
(12, 'nathalie', 'pabilonia', 'nathpabi', 'password', 'nathpabi@gmail.com', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_function`
--
ALTER TABLE `admin_function`
  ADD PRIMARY KEY (`function_id`);

--
-- Indexes for table `admin_log`
--
ALTER TABLE `admin_log`
  ADD PRIMARY KEY (`adminlog_id`),
  ADD KEY `function_id` (`function_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `appliance`
--
ALTER TABLE `appliance`
  ADD PRIMARY KEY (`app_id`),
  ADD KEY `appcat_id` (`appcat_id`),
  ADD KEY `room_id` (`room_id`);

--
-- Indexes for table `appliance_log`
--
ALTER TABLE `appliance_log`
  ADD PRIMARY KEY (`applog_id`),
  ADD KEY `app_id` (`app_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `application_category`
--
ALTER TABLE `application_category`
  ADD PRIMARY KEY (`appcat_id`);

--
-- Indexes for table `controllers`
--
ALTER TABLE `controllers`
  ADD PRIMARY KEY (`control_id`),
  ADD KEY `app_id` (`app_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `device`
--
ALTER TABLE `device`
  ADD PRIMARY KEY (`device_id`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`room_id`);

--
-- Indexes for table `user_account`
--
ALTER TABLE `user_account`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_function`
--
ALTER TABLE `admin_function`
  MODIFY `function_id` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `admin_log`
--
ALTER TABLE `admin_log`
  MODIFY `adminlog_id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `appliance`
--
ALTER TABLE `appliance`
  MODIFY `app_id` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;
--
-- AUTO_INCREMENT for table `appliance_log`
--
ALTER TABLE `appliance_log`
  MODIFY `applog_id` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
--
-- AUTO_INCREMENT for table `application_category`
--
ALTER TABLE `application_category`
  MODIFY `appcat_id` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `controllers`
--
ALTER TABLE `controllers`
  MODIFY `control_id` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=115;
--
-- AUTO_INCREMENT for table `device`
--
ALTER TABLE `device`
  MODIFY `device_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `room_id` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `user_account`
--
ALTER TABLE `user_account`
  MODIFY `user_id` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin_log`
--
ALTER TABLE `admin_log`
  ADD CONSTRAINT `admin_log_ibfk_1` FOREIGN KEY (`function_id`) REFERENCES `admin_function` (`function_id`),
  ADD CONSTRAINT `admin_log_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`user_id`);

--
-- Constraints for table `appliance`
--
ALTER TABLE `appliance`
  ADD CONSTRAINT `appliance_ibfk_1` FOREIGN KEY (`appcat_id`) REFERENCES `application_category` (`appcat_id`),
  ADD CONSTRAINT `appliance_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`);

--
-- Constraints for table `appliance_log`
--
ALTER TABLE `appliance_log`
  ADD CONSTRAINT `appliance_log_ibfk_1` FOREIGN KEY (`app_id`) REFERENCES `appliance` (`app_id`),
  ADD CONSTRAINT `appliance_log_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`user_id`);

--
-- Constraints for table `controllers`
--
ALTER TABLE `controllers`
  ADD CONSTRAINT `controllers_ibfk_1` FOREIGN KEY (`app_id`) REFERENCES `appliance` (`app_id`),
  ADD CONSTRAINT `controllers_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`user_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
