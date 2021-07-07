-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 21, 2021 at 02:03 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `taskit`
--

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `feedback_id` int(200) NOT NULL,
  `user_id` int(200) NOT NULL,
  `feedback_by` int(200) NOT NULL,
  `rate` double NOT NULL,
  `feedback_text` varchar(200) NOT NULL,
  `date_posted` timestamp(6) NOT NULL DEFAULT current_timestamp(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`feedback_id`, `user_id`, `feedback_by`, `rate`, `feedback_text`, `date_posted`) VALUES
(1, 2, 15, 3.5, 'yoh', '2021-05-21 11:15:02.931419'),
(2, 2, 15, 3.5, 'yoh', '2021-05-21 11:15:05.103496'),
(3, 6, 15, 4.5, '', '2021-05-21 11:17:00.940650'),
(4, 1, 15, 4, 'amazing!', '2021-05-21 11:43:43.715030'),
(5, 3, 15, 3.5, '', '2021-05-21 12:02:00.308469');

-- --------------------------------------------------------

--
-- Table structure for table `sys_meta`
--

CREATE TABLE `sys_meta` (
  `meta_id` int(200) NOT NULL,
  `meta_key` varchar(200) NOT NULL,
  `meta_value` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sys_meta`
--

INSERT INTO `sys_meta` (`meta_id`, `meta_key`, `meta_value`) VALUES
(1, 'service', 'laundry'),
(2, 'service', 'electrician'),
(3, 'service', 'plumber'),
(4, 'service', 'painting'),
(5, 'service', 'movers'),
(6, 'service', 'cobbler'),
(7, 'service', 'delivery'),
(8, 'service', 'mechanics'),
(9, 'location', 'Gate A'),
(10, 'location', 'Gate B'),
(11, 'location', 'Gate C'),
(12, 'location', 'Gate D'),
(13, 'location', 'Juja Stage'),
(15, 'location', 'Oasis');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(200) NOT NULL,
  `fullname` varchar(200) NOT NULL,
  `username` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `phone` varchar(200) NOT NULL,
  `service` varchar(200) NOT NULL,
  `location` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `role` int(12) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `fullname`, `username`, `email`, `phone`, `service`, `location`, `password`, `role`) VALUES
(1, 'Jacky Itha', 'jacky', 'jacky@gmail.com', '0701314414', '1', '10', '', 0),
(2, 'Arap Cheru', 'Arap', 'g@g.c', '0790780464', '3', '10', '', 0),
(3, 'Zac Mwangi', 'Zac', 'Zacmwangi14@gmail.com', '0730780464', '2', '12', '', 0),
(4, 'Vin Njeru', 'Vinn', 'VNjeru23@gmail.com', '0716631107', '4', '11', '', 0),
(5, 'Lewis Ng\'anga', 'Lewis', 'LewisN@gmail.com', '0714479133', '5', '13', '', 0),
(6, 'Josephat Ndungu', 'Jose', 'Jose123@gmail.com', '0718201905', '8', '9', '', 0),
(7, 'Victoria Nyaguthii', 'Vicky', 'Vtoria@gmail.com', '0711552145', '1', '12', '', 0),
(8, 'Tracy wakuraya', 'Tracy', 'Twakuraya@gmail.com', '07283013988', '2', '10', '', 0),
(9, 'Linet Njaguara', 'Njaguara', 'Njaguara4@gmail.com', '0720243803', '5', '12', '', 0),
(10, 'Eric Maina', 'Eric', 'Emaina@gmail.com', '0720813635', '6', '9', '', 0),
(11, 'Moffat Waweru', 'Moffat', 'MoffatW@gmail.com', '0712361222', '7', '11', '', 0),
(12, 'yoh', 'yoh', '', '0785012545', '', '', 'a113bd39fd0b16165b6dcb5cf7799a15', 0),
(13, 'yoh', 'yoh', '', '0785012545', '', '', 'a113bd39fd0b16165b6dcb5cf7799a15', 0),
(14, 'jakuom', 'jakuom', '', '+254708928903', '', '', '3b812175a3bbca97b079857d20ee12f1', 0),
(15, 'kaki', 'kaki', '', '0701314414', '', '', 'db788e8cbac6da8538ff37261c9ed197', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`feedback_id`);

--
-- Indexes for table `sys_meta`
--
ALTER TABLE `sys_meta`
  ADD PRIMARY KEY (`meta_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `feedback_id` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `sys_meta`
--
ALTER TABLE `sys_meta`
  MODIFY `meta_id` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
