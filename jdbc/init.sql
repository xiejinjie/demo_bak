CREATE TABLE `treenodes` (
                             `id` INT(11) NOT NULL AUTO_INCREMENT,
                             `nodename` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
                             `pid` INT(11) NULL DEFAULT NULL,
                             PRIMARY KEY (`id`)
)
    COLLATE='utf8mb4_0900_as_ci'
    ENGINE=InnoDB
    AUTO_INCREMENT=18
;
INSERT INTO `treenodes` (`id`, `nodename`, `pid`) VALUES (1, 'A', 0);
INSERT INTO `treenodes` (`id`, `nodename`, `pid`) VALUES (2, 'B', 1);
INSERT INTO `treenodes` (`id`, `nodename`, `pid`) VALUES (3, 'C', 1);
INSERT INTO `treenodes` (`id`, `nodename`, `pid`) VALUES (4, 'D', 2);
INSERT INTO `treenodes` (`id`, `nodename`, `pid`) VALUES (5, 'E', 2);
INSERT INTO `treenodes` (`id`, `nodename`, `pid`) VALUES (6, 'F', 3);
INSERT INTO `treenodes` (`id`, `nodename`, `pid`) VALUES (7, 'G', 6);
INSERT INTO `treenodes` (`id`, `nodename`, `pid`) VALUES (8, 'H', 0);
INSERT INTO `treenodes` (`id`, `nodename`, `pid`) VALUES (9, 'I', 8);
INSERT INTO `treenodes` (`id`, `nodename`, `pid`) VALUES (10, 'J', 8);
INSERT INTO `treenodes` (`id`, `nodename`, `pid`) VALUES (11, 'K', 8);
INSERT INTO `treenodes` (`id`, `nodename`, `pid`) VALUES (12, 'L', 9);
INSERT INTO `treenodes` (`id`, `nodename`, `pid`) VALUES (13, 'M', 9);
INSERT INTO `treenodes` (`id`, `nodename`, `pid`) VALUES (14, 'N', 12);
INSERT INTO `treenodes` (`id`, `nodename`, `pid`) VALUES (15, 'O', 12);
INSERT INTO `treenodes` (`id`, `nodename`, `pid`) VALUES (16, 'P', 15);
INSERT INTO `treenodes` (`id`, `nodename`, `pid`) VALUES (17, 'Q', 15);


delimiter $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `fun1`(
    IN `node_id` INT,
    OUT `node_name` VARCHAR(50)
)
    LANGUAGE SQL
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN
    select nodename into node_name from treenodes where id = node_id;
END $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `fun2`()
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
select * from treenodes;
END $$
delimiter ;