-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: mysqlContainer
-- Generation Time: Jun 30, 2024 at 06:02 PM
-- Server version: 8.4.0
-- PHP Version: 8.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `invoiceManagement`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`user`@`%` PROCEDURE `calculate_annual_revenue` (IN `input_year` INT)   BEGIN
    DECLARE annual_revenue DECIMAL(10, 2);

    SELECT IFNULL(SUM(amount), 0) INTO annual_revenue
    FROM invoice
    WHERE YEAR(created_date) = input_year;

    INSERT INTO revenue_report (year, month, day, amount)
    VALUES (input_year, 0, 0, annual_revenue);
END$$

CREATE DEFINER=`user`@`%` PROCEDURE `calculate_daily_revenue` (IN `input_date` DATE)   BEGIN
    DECLARE daily_revenue DECIMAL(10, 2);

    SELECT IFNULL(SUM(amount), 0) INTO daily_revenue
    FROM invoice
    WHERE created_date = input_date;

    INSERT INTO revenue_report (year, month, day, amount)
    VALUES (YEAR(input_date), MONTH(input_date), DAY(input_date), daily_revenue);
END$$

CREATE DEFINER=`user`@`%` PROCEDURE `calculate_monthly_revenue` (IN `input_year` INT, IN `input_month` INT)   BEGIN
    DECLARE monthly_revenue DECIMAL(10, 2);

    SELECT IFNULL(SUM(amount), 0) INTO monthly_revenue
    FROM invoice
    WHERE YEAR(created_date) = input_year AND MONTH(created_date) = input_month;

    INSERT INTO revenue_report (year, month, day, amount)
    VALUES (input_year, input_month, 0, monthly_revenue);
END$$

--
-- Functions
--
CREATE DEFINER=`user`@`%` FUNCTION `calculate_revenue_by_cashier` (`input_cashier_id` INT) RETURNS DECIMAL(10,2) DETERMINISTIC BEGIN
    DECLARE total_revenue DECIMAL(10, 2);
    
    SELECT SUM(amount) INTO total_revenue
    FROM invoice
    WHERE cashier_id = input_cashier_id;
    
    RETURN IFNULL(total_revenue, 0);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `cashier`
--

CREATE TABLE `cashier` (
  `id` int NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `cashier`
--

INSERT INTO `cashier` (`id`, `name`) VALUES
(1, 'Alice Johnson'),
(2, 'Bob Brown');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `name`, `phone`) VALUES
(1, 'John Doe', '123-456-7890'),
(2, 'Jane Smith', '098-765-4321');

-- --------------------------------------------------------

--
-- Stand-in structure for view `customer_product_purchases`
-- (See below for the actual view)
--
CREATE TABLE `customer_product_purchases` (
`amount` decimal(10,2)
,`created_date` date
,`customer_id` int
,`customer_name` varchar(100)
,`product_id` int
,`product_name` varchar(100)
,`quantity` int
);

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `id` int NOT NULL,
  `customer_id` int DEFAULT NULL,
  `cashier_id` int DEFAULT NULL,
  `amount` decimal(10,2) NOT NULL,
  `created_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`id`, `customer_id`, `cashier_id`, `amount`, `created_date`) VALUES
(1, 1, 1, 40.00, '2024-06-28'),
(2, 2, 2, 70.00, '2024-06-28');

-- --------------------------------------------------------

--
-- Table structure for table `invoice_detail`
--

CREATE TABLE `invoice_detail` (
  `id` int NOT NULL,
  `quantity` int NOT NULL,
  `product_id` int DEFAULT NULL,
  `product_price` decimal(10,2) NOT NULL,
  `invoice_id` int DEFAULT NULL,
  `amount` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `invoice_detail`
--

INSERT INTO `invoice_detail` (`id`, `quantity`, `product_id`, `product_price`, `invoice_id`, `amount`) VALUES
(1, 2, 1, 10.00, 1, 20.00),
(2, 1, 2, 20.00, 1, 20.00),
(3, 3, 1, 10.00, 2, 30.00),
(4, 2, 2, 20.00, 2, 40.00);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `price`) VALUES
(1, 'Product A', 10.00),
(2, 'Product B', 20.00);

-- --------------------------------------------------------

--
-- Table structure for table `revenue_report`
--

CREATE TABLE `revenue_report` (
  `id` int NOT NULL,
  `year` int NOT NULL,
  `month` int NOT NULL,
  `day` int NOT NULL,
  `amount` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `revenue_report`
--

INSERT INTO `revenue_report` (`id`, `year`, `month`, `day`, `amount`) VALUES
(1, 2024, 6, 28, 110.00),
(2, 2024, 0, 0, 110.00),
(3, 2024, 0, 0, 110.00),
(4, 2023, 0, 0, 0.00);

-- --------------------------------------------------------

--
-- Structure for view `customer_product_purchases`
--
DROP TABLE IF EXISTS `customer_product_purchases`;

CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `customer_product_purchases`  AS SELECT `c`.`id` AS `customer_id`, `c`.`name` AS `customer_name`, `p`.`id` AS `product_id`, `p`.`name` AS `product_name`, `id`.`quantity` AS `quantity`, `id`.`amount` AS `amount`, `i`.`created_date` AS `created_date` FROM (((`customer` `c` join `invoice` `i` on((`c`.`id` = `i`.`customer_id`))) join `invoice_detail` `id` on((`i`.`id` = `id`.`invoice_id`))) join `product` `p` on((`id`.`product_id` = `p`.`id`))) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cashier`
--
ALTER TABLE `cashier`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `cashier_id` (`cashier_id`);

--
-- Indexes for table `invoice_detail`
--
ALTER TABLE `invoice_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `invoice_id` (`invoice_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `revenue_report`
--
ALTER TABLE `revenue_report`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cashier`
--
ALTER TABLE `cashier`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `invoice_detail`
--
ALTER TABLE `invoice_detail`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `revenue_report`
--
ALTER TABLE `revenue_report`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `invoice_ibfk_2` FOREIGN KEY (`cashier_id`) REFERENCES `cashier` (`id`);

--
-- Constraints for table `invoice_detail`
--
ALTER TABLE `invoice_detail`
  ADD CONSTRAINT `invoice_detail_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `invoice_detail_ibfk_2` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
