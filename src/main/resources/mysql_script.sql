-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema grupo26
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema grupo26
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `grupo26` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `grupo26` ;

-- -----------------------------------------------------
-- Table `grupo26`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `grupo26`.`product` (
  `product_id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(255) NOT NULL,
  `cost` FLOAT NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `price` FLOAT NOT NULL,
  PRIMARY KEY (`product_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `grupo26`.`storage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `grupo26`.`storage` (
  `storage_id` BIGINT NOT NULL AUTO_INCREMENT,
  `location` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`storage_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `grupo26`.`batch`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `grupo26`.`batch` (
  `batch_id` BIGINT NOT NULL AUTO_INCREMENT,
  `batch_price` FLOAT NOT NULL,
  `quantity_received` INT NOT NULL,
  `reception_date` DATE NULL DEFAULT NULL,
  `supplier` VARCHAR(60) NOT NULL,
  `product_id` BIGINT NOT NULL,
  `storage_id` BIGINT NOT NULL,
  PRIMARY KEY (`batch_id`),
  UNIQUE INDEX `UK_7x4v9hlet33uhabjrwo37u2gs` (`product_id` ASC) VISIBLE,
  INDEX `FKfyjrsol65jp9r3pl1nt0u8a52` (`storage_id` ASC) VISIBLE,
  CONSTRAINT `FK1oku9ugxas81uu408c8b9wb8h`
    FOREIGN KEY (`product_id`)
    REFERENCES `grupo26`.`product` (`product_id`),
  CONSTRAINT `FKfyjrsol65jp9r3pl1nt0u8a52`
    FOREIGN KEY (`storage_id`)
    REFERENCES `grupo26`.`storage` (`storage_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `grupo26`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `grupo26`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `activo` BIT(1) NOT NULL,
  `dni` INT NOT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `fecha_actualizacion` DATETIME(6) NULL DEFAULT NULL,
  `fecha_alta` DATETIME(6) NULL DEFAULT NULL,
  `last_name` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `user` VARCHAR(255) NULL DEFAULT NULL,
  `user_role` TINYINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `grupo26`.`sale`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `grupo26`.`sale` (
  `id_sale` BIGINT NOT NULL AUTO_INCREMENT,
  `sale_date` DATE NOT NULL,
  `id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id_sale`),
  INDEX `FK56srlde2nqef1w8wex9rtl2uw` (`id` ASC) VISIBLE,
  CONSTRAINT `FK56srlde2nqef1w8wex9rtl2uw`
    FOREIGN KEY (`id`)
    REFERENCES `grupo26`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `grupo26`.`sale_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `grupo26`.`sale_item` (
  `sale_item_id` BIGINT NOT NULL AUTO_INCREMENT,
  `price` FLOAT NOT NULL,
  `quantity` INT NOT NULL,
  `product_id` BIGINT NULL DEFAULT NULL,
  `sale_id_sale` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`sale_item_id`),
  UNIQUE INDEX `UK_393r3cboi7glp3pn7dthkvxn4` (`product_id` ASC) VISIBLE,
  UNIQUE INDEX `UK_n0q6yh5krfjc7ymkrmh180r56` (`sale_id_sale` ASC) VISIBLE,
  CONSTRAINT `FK8agurweqysjtehuggjo9qrrp0`
    FOREIGN KEY (`sale_id_sale`)
    REFERENCES `grupo26`.`sale` (`id_sale`),
  CONSTRAINT `FKih76x9f1o1asbp51c70hin53n`
    FOREIGN KEY (`product_id`)
    REFERENCES `grupo26`.`product` (`product_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `grupo26`.`stock`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `grupo26`.`stock` (
  `id_stock` BIGINT NOT NULL AUTO_INCREMENT,
  `available_quantity` INT NOT NULL,
  `min_quantity` INT NOT NULL,
  `product_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id_stock`),
  UNIQUE INDEX `UK_khabtqwr86p7x9mt2krib98tx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `FKjghkvw2snnsr5gpct0of7xfcf`
    FOREIGN KEY (`product_id`)
    REFERENCES `grupo26`.`product` (`product_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `grupo26`.`supply_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `grupo26`.`supply_order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `quantity_ordered` INT NOT NULL,
  `supplier` VARCHAR(255) NULL DEFAULT NULL,
  `user` VARCHAR(255) NULL DEFAULT NULL,
  `product_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_4nll0ijbiyio9y79jklcb8qdp` (`product_id` ASC) VISIBLE,
  CONSTRAINT `FKc4bfmppyn5k1vmgv080bb2d78`
    FOREIGN KEY (`product_id`)
    REFERENCES `grupo26`.`product` (`product_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
