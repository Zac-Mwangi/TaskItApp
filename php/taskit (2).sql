-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 21, 2021 at 09:40 AM
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
(1, 2, 0, 1, 'oyaa', '2021-05-21 02:52:57.730683'),
(2, 2, 0, 1, 'oyaa', '2021-05-21 02:53:07.521815'),
(3, 2, 0, 2, 'oyaa', '2021-05-21 02:53:18.558178'),
(4, 2, 0, 5, '', '2021-05-21 03:32:38.619581'),
(5, 2, 0, 4.5, '', '2021-05-21 03:32:44.383111'),
(6, 2, 0, 5, '', '2021-05-21 03:32:49.999043'),
(7, 1, 0, 4.5, '', '2021-05-21 03:33:02.605897'),
(8, 2, 0, 5, '', '2021-05-21 03:42:02.417924'),
(9, 2, 0, 2.5, '', '2021-05-21 04:33:20.125175'),
(10, 2, 0, 4, '', '2021-05-21 04:54:07.546430'),
(11, 2, 0, 4, 'dtugd', '2021-05-21 05:30:08.597901'),
(12, 1, 0, 0.5, '', '2021-05-21 05:32:21.403806');

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
  `location` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `fullname`, `username`, `email`, `phone`, `service`, `location`) VALUES
(1, 'Jacky Itha', 'jacky', 'jacky@gmail.com', '0701314414', '1', '10'),
(2, 'Arap Cheru', 'Arap', 'g@g.c', '0790780464', '3', '10'),
(3, 'Zac Mwangi', 'Zac', 'Zacmwangi14@gmail.com', '0730780464', '2', '12'),
(4, 'Vin Njeru', 'Vinn', 'VNjeru23@gmail.com', '0716631107', '4', '11'),
(5, 'Lewis Ng\'anga', 'Lewis', 'LewisN@gmail.com', '0714479133', '5', '13'),
(6, 'Josephat Ndungu', 'Jose', 'Jose123@gmail.com', '0718201905', '8', '9'),
(7, 'Victoria Nyaguthii', 'Vicky', 'Vtoria@gmail.com', '0711552145', '1', '12'),
(8, 'Tracy wakuraya', 'Tracy', 'Twakuraya@gmail.com', '07283013988', '2', '10'),
(9, 'Linet Njaguara', 'Njaguara', 'Njaguara4@gmail.com', '0720243803', '5', '12'),
(10, 'Eric Maina', 'Eric', 'Emaina@gmail.com', '0720813635', '6', '9'),
(11, 'Moffat Waweru', 'Moffat', 'MoffatW@gmail.com', '0712361222', '7', '11');

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
  MODIFY `feedback_id` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `sys_meta`
--
ALTER TABLE `sys_meta`
  MODIFY `meta_id` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
