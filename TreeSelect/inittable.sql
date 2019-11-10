-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.0.17 - MySQL Community Server - GPL
-- 服务器OS:                        Win64
-- HeidiSQL 版本:                  10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE TABLE `treenodes` (
                             `id` INT(11) NOT NULL AUTO_INCREMENT,
                             `nodename` VARCHAR(50) NULL DEFAULT NULL,
                             `pid` INT(11) NULL DEFAULT NULL,
                             PRIMARY KEY (`id`)
)
    COLLATE='utf8_general_ci'
    ENGINE=InnoDB
    AUTO_INCREMENT=18
;

-- Dumping data for table test.treenodes: ~15 rows (大约)
/*!40000 ALTER TABLE `treenodes` DISABLE KEYS */;
INSERT INTO `treenodes` (`id`, `nodename`, `pid`) VALUES
(1, 'A', 0),
(2, 'B', 1),
(3, 'C', 1),
(4, 'D', 2),
(5, 'E', 2),
(6, 'F', 3),
(7, 'G', 6),
(8, 'H', 0),
(9, 'I', 8),
(10, 'J', 8),
(11, 'K', 8),
(12, 'L', 9),
(13, 'M', 9),
(14, 'N', 12),
(15, 'O', 12),
(16, 'P', 15),
(17, 'Q', 15);
/*!40000 ALTER TABLE `treenodes` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
