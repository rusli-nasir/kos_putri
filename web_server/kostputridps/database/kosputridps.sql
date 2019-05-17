/*
 Navicat Premium Data Transfer

 Source Server         : localMariadb
 Source Server Type    : MySQL
 Source Server Version : 100310
 Source Host           : localhost:3307
 Source Schema         : kosputridps

 Target Server Type    : MySQL
 Target Server Version : 100310
 File Encoding         : 65001

 Date: 17/05/2019 11:16:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id_admin` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `password` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `nama_lengkap` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `email` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `level` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id_admin`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', 'Ronald', 'Behar_mti@yahoo.co.id', 'admin');
INSERT INTO `admin` VALUES ('Ronald', '202cb962ac59075b964b07152d234b70', 'Umbu Ronald', 'Ronald_IDE@yahoo.co.id', 'pemilik');
INSERT INTO `admin` VALUES ('Nanang', '202cb962ac59075b964b07152d234b70', 'nanang kasim', 'na4ngkasim@gmail.com', 'pemilik');

-- ----------------------------
-- Table structure for icon
-- ----------------------------
DROP TABLE IF EXISTS `icon`;
CREATE TABLE `icon`  (
  `id_icon` int(5) NOT NULL AUTO_INCREMENT,
  `nama_icon` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `gambar` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `keterangan` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id_icon`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for katpenginapan
-- ----------------------------
DROP TABLE IF EXISTS `katpenginapan`;
CREATE TABLE `katpenginapan`  (
  `id_kategori_penginapan` int(5) NOT NULL AUTO_INCREMENT,
  `nama_kategori` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `keterangan` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id_kategori_penginapan`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of katpenginapan
-- ----------------------------
INSERT INTO `katpenginapan` VALUES (1, 'Elit', 'lengkap');
INSERT INTO `katpenginapan` VALUES (2, 'Non Elit', 'kosong');

-- ----------------------------
-- Table structure for modul
-- ----------------------------
DROP TABLE IF EXISTS `modul`;
CREATE TABLE `modul`  (
  `id_modul` int(5) NOT NULL AUTO_INCREMENT,
  `nama_modul` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `link` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `aktif` enum('Y','N') CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `urutan` int(5) NOT NULL,
  PRIMARY KEY (`id_modul`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 9 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of modul
-- ----------------------------
INSERT INTO `modul` VALUES (1, 'Penginapan', '?module=penginapan', 'Y', 1);
INSERT INTO `modul` VALUES (2, 'Admin', '?module=admin', 'Y', 0);
INSERT INTO `modul` VALUES (3, 'Modul', '?module=modul', 'N', 5);
INSERT INTO `modul` VALUES (4, 'Kategori Penginapan', '?module=katpenginapan', 'Y', 2);

-- ----------------------------
-- Table structure for penginapan
-- ----------------------------
DROP TABLE IF EXISTS `penginapan`;
CREATE TABLE `penginapan`  (
  `id_penginapan` int(5) NOT NULL AUTO_INCREMENT,
  `id_pemilik` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `id_kategori_penginapan` int(5) NOT NULL,
  `id_icon` int(5) NOT NULL,
  `nama` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `fasilitas` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `harga` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `status` varchar(300) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `alamat` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `latitude` decimal(10, 6) NOT NULL,
  `longitude` decimal(10, 6) NOT NULL,
  `telepon` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `gambar` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id_penginapan`) USING BTREE,
  INDEX `id_kategori_gereja`(`id_kategori_penginapan`, `id_icon`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 15 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of penginapan
-- ----------------------------
INSERT INTO `penginapan` VALUES (4, 'Ronald', 2, 1, 'Ronald', 'kosong', 'RP.500.000', '1 kamar kosong', 'Jl. Tukad Pakderisan Gg. Darmajaya, Panjer, Kota Denpasar, Bali', -8.687952, 115.226099, '082236058469', 'LOGO.JPG');
INSERT INTO `penginapan` VALUES (5, 'Ronald', 1, 1, 'Nanang', 'Lengkap', 'Rp.600.000', '2 Kamar Kosong', 'Jl.Tukad pakerisan Gg.Darmajaya no.8', -8.687918, 115.225432, '123456789', 'LOGO.JPG');
INSERT INTO `penginapan` VALUES (6, 'Ronald', 1, 1, 'Komang', 'Lengkap', 'Rp.600.000', '2 Kamar Kosong', 'Jl. Tukad Pakderisan Gg. Darmajaya, Panjer, Kota Denpasar, Bali', -9999.999999, 9999.999999, '123456', '20190413_214229.jpg');
INSERT INTO `penginapan` VALUES (13, 'Nanang', 0, 1, 'das', 'sadasd', 'sadasd', 'sdasd', 'asdsad', -8.684827, 115.229720, 'sadsad', 'penginapan_1557269451jpeg');
INSERT INTO `penginapan` VALUES (12, 'Nanang', 2, 1, 'wdd', 'sdasd', '3232', 'sdasd', 'asdsa', -8.685039, 115.229854, '2323', 'penginapan_1557269054');
INSERT INTO `penginapan` VALUES (11, 'Nanang', 1, 1, 'Desasa', 'sdsd', '100010', 'ssd', 'sds', -8.685698, 115.229685, '23232', 'petanu_logo.jpeg');
INSERT INTO `penginapan` VALUES (14, 'Nanang', 0, 1, 'da', 'adad', 'ada', 'dad', 'adad', -8.684679, 115.229741, 'adad', 'penginapan_1557269608.png');

SET FOREIGN_KEY_CHECKS = 1;
