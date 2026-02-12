-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 12, 2026 at 08:16 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `penjualanmobil`
--

-- --------------------------------------------------------

--
-- Table structure for table `bayar_cicilan`
--

CREATE TABLE `bayar_cicilan` (
  `kode_cicilan` varchar(255) NOT NULL,
  `kode_kredit` varchar(255) NOT NULL,
  `tanggal_cicilan` date NOT NULL,
  `cicilanke` int(11) NOT NULL,
  `jumlah_cicilan` int(11) NOT NULL,
  `sisa_cicilan` int(11) NOT NULL,
  `sisa_harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bayar_cicilan`
--

INSERT INTO `bayar_cicilan` (`kode_cicilan`, `kode_kredit`, `tanggal_cicilan`, `cicilanke`, `jumlah_cicilan`, `sisa_cicilan`, `sisa_harga`) VALUES
('CC001', 'BC005', '2025-01-06', 1, 240800000, 11, 220733334),
('CC002', 'BC007', '2025-01-05', 1, 11466666, 11, 126133334),
('CC003', 'BC007', '2026-01-31', 1, 11466666, 11, 126133334);

-- --------------------------------------------------------

--
-- Table structure for table `belicash`
--

CREATE TABLE `belicash` (
  `kode_cash` varchar(255) NOT NULL,
  `ktp` varchar(255) NOT NULL,
  `kode_mobil` varchar(255) NOT NULL,
  `cash_tgl` date NOT NULL,
  `cash_bayar` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `belicash`
--

INSERT INTO `belicash` (`kode_cash`, `ktp`, `kode_mobil`, `cash_tgl`, `cash_bayar`) VALUES
('KBC001', '123456781', 'MM001', '2026-01-01', 350000000),
('KBC002', '123456789', 'MM005', '2025-01-29', 700000000),
('KBC003', '123456789', 'MM005', '2026-01-26', 700000000),
('KBC004', '3171051203980003', 'MM003', '2026-01-20', 200000000);

-- --------------------------------------------------------

--
-- Table structure for table `kredit`
--

CREATE TABLE `kredit` (
  `kode_kredit` varchar(255) NOT NULL,
  `kode_paket` varchar(255) NOT NULL,
  `ktp` varchar(255) NOT NULL,
  `kode_mobil` varchar(255) NOT NULL,
  `tanggal_kredit` varchar(255) NOT NULL,
  `bayar_kredit` int(11) NOT NULL,
  `tenor` int(11) NOT NULL,
  `totalcicil` int(11) NOT NULL,
  `gambarktp` varchar(255) NOT NULL,
  `gambarslip` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kredit`
--

INSERT INTO `kredit` (`kode_kredit`, `kode_paket`, `ktp`, `kode_mobil`, `tanggal_kredit`, `bayar_kredit`, `tenor`, `totalcicil`, `gambarktp`, `gambarslip`) VALUES
('BC001', 'KP001', '123456781', 'KK001', '2025-09-17', 85000000, 6, 510000000, '', ''),
('BC0010', 'KP001', '123456781', 'MM001', '2026-01-16', 28729166, 6, 172375000, 'uploads\\ktp_1768956689377_Air-fryer-french-fries-recipe.jpg', 'uploads\\slip_1768956689413_Air-fryer-french-fries-recipe.jpg'),
('BC002', 'KP001', '123456781', 'KK001', '2025-10-02', 85000000, 6, 510000000, '', ''),
('BC005', 'KP002', '123456781', 'MM001', '2026-01-01', 20066666, 12, 240800000, '', ''),
('BC007', 'KP002', '123456789', 'MM003', '2026-01-04', 11466666, 12, 137600000, '', ''),
('BC008', 'KP002', '3171051203980003', 'MM001', '2026-01-11', 20066666, 12, 240800000, '', ''),
('BC009', 'KP001', '123456781', 'MM005', '2026-01-04', 57458333, 6, 344750000, 'uploads\\ktp_1768794354490_avanza.png', 'uploads\\slip_1768794354497_avanza.png');

-- --------------------------------------------------------

--
-- Table structure for table `mobil`
--

CREATE TABLE `mobil` (
  `kode_mobil` varchar(255) NOT NULL,
  `merk` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `warna` varchar(255) NOT NULL,
  `harga` bigint(11) NOT NULL,
  `gambar` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mobil`
--

INSERT INTO `mobil` (`kode_mobil`, `merk`, `type`, `warna`, `harga`, `gambar`) VALUES
('MM001', 'Honda', 'WR-V', 'Ignite Red Metallic', 350000000, 'uploads/wrv.png'),
('MM002', 'Toyota', 'Avanza', 'Grey Metallic', 260000000, 'uploads/avanza.png'),
('MM003', 'Honda', 'Brio', 'Carnival Yellow', 200000000, ''),
('MM004', 'Mitsubishi', 'Xpander', 'Jet Black Mica', 300000000, ''),
('MM005', 'Toyota', 'Fortuner', 'Super White', 700000000, ''),
('MM006', 'asa', 'as', 'as', 34325456, 'uploads/61pOXvxcYkL._SL1001_.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `paket`
--

CREATE TABLE `paket` (
  `kode_paket` varchar(255) NOT NULL,
  `uang_muka` int(11) NOT NULL,
  `tenor` int(11) NOT NULL,
  `bunga_cicilan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `paket`
--

INSERT INTO `paket` (`kode_paket`, `uang_muka`, `tenor`, `bunga_cicilan`) VALUES
('KP001', 50, 6, 3),
('KP002', 20, 12, 14);

-- --------------------------------------------------------

--
-- Table structure for table `pembeli`
--

CREATE TABLE `pembeli` (
  `ktp` varchar(255) NOT NULL,
  `nama_pembeli` varchar(255) NOT NULL,
  `alamat_pembeli` varchar(255) NOT NULL,
  `telp_pembeli` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pembeli`
--

INSERT INTO `pembeli` (`ktp`, `nama_pembeli`, `alamat_pembeli`, `telp_pembeli`) VALUES
('123456781', 'Rachel Keithlin', 'Hati Jovian', '12345678'),
('123456789', 'Jeslyn', 'Bali', '123455'),
('3171051203980003', 'Minnie', 'Aussi', '0874238721');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `level` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `level`, `id_user`) VALUES
('manager', 'manager', 2, 1),
('petugas', 'petugas', 1, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bayar_cicilan`
--
ALTER TABLE `bayar_cicilan`
  ADD PRIMARY KEY (`kode_cicilan`),
  ADD KEY `kode_kredit` (`kode_kredit`);

--
-- Indexes for table `belicash`
--
ALTER TABLE `belicash`
  ADD PRIMARY KEY (`kode_cash`),
  ADD KEY `ktp` (`ktp`),
  ADD KEY `kode_mobil` (`kode_mobil`);

--
-- Indexes for table `kredit`
--
ALTER TABLE `kredit`
  ADD PRIMARY KEY (`kode_kredit`),
  ADD KEY `kode_paket` (`kode_paket`,`ktp`,`kode_mobil`),
  ADD KEY `ktp` (`ktp`),
  ADD KEY `kode_beli` (`kode_mobil`);

--
-- Indexes for table `mobil`
--
ALTER TABLE `mobil`
  ADD PRIMARY KEY (`kode_mobil`);

--
-- Indexes for table `paket`
--
ALTER TABLE `paket`
  ADD PRIMARY KEY (`kode_paket`);

--
-- Indexes for table `pembeli`
--
ALTER TABLE `pembeli`
  ADD PRIMARY KEY (`ktp`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
