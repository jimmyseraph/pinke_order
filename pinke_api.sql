-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: 2018-11-04 06:44:02
-- 服务器版本： 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pinke_api`
--

-- --------------------------------------------------------

--
-- 表的结构 `access_token`
--

DROP TABLE IF EXISTS `access_token`;
CREATE TABLE IF NOT EXISTS `access_token` (
  `token_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `private_key` varchar(128) NOT NULL,
  `token` varchar(128) NOT NULL,
  `user_id` int(11) NOT NULL,
  `expired` timestamp NOT NULL,
  PRIMARY KEY (`token_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `access_token`
--

INSERT INTO `access_token` (`token_id`, `private_key`, `token`, `user_id`, `expired`) VALUES
(1, 'd20da389-b1b1-4042-bce9-af763a5b6e75', 'eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJsaXVkYW8wMDEiLCJleHAiOjE1NDEzMDM3OTd9.AYMq0IbNm_1xikMtMh2SVes5_YUyI0Hto4tN_E_hmcQ', 1, '2018-11-04 03:56:38');

-- --------------------------------------------------------

--
-- 表的结构 `order_info`
--

DROP TABLE IF EXISTS `order_info`;
CREATE TABLE IF NOT EXISTS `order_info` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `is_paid` tinyint(4) NOT NULL,
  `address` varchar(128) NOT NULL,
  `amount` double NOT NULL,
  `receiver` varchar(32) NOT NULL,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `order_info`
--

INSERT INTO `order_info` (`order_id`, `is_paid`, `address`, `amount`, `receiver`, `status`) VALUES
(1, 0, '南京东路88号7号楼1018室', 202.78, 'liudao', 0),
(2, 0, '南京东路188号1号楼208室', 224.78, 'liudao', 1),
(3, 0, '金桥路2004号98号楼705室', 236.9, 'xiaoming', 1);

-- --------------------------------------------------------

--
-- 表的结构 `user_info`
--

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE IF NOT EXISTS `user_info` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `user_password` varchar(32) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `user_info`
--

INSERT INTO `user_info` (`user_id`, `user_name`, `user_password`) VALUES
(1, 'liudao001', 'e10adc3949ba59abbe56e057f20f883e'),
(2, 'liudao002', 'e10adc3949ba59abbe56e057f20f883e');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
