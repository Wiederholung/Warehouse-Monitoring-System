-- MySQL Script generated by MySQL Workbench
-- Wed Jul  6 18:35:01 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema wmsdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema wmsdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `wmsdb` DEFAULT CHARACTER SET utf8 ;
USE `wmsdb` ;

-- -----------------------------------------------------
-- Table `wmsdb`.`warehouse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wmsdb`.`warehouse` (
  `wh_id` INT NOT NULL AUTO_INCREMENT,
  `wh_name` VARCHAR(45) NOT NULL,
  `wh_type` VARCHAR(45) NULL DEFAULT 'default type',
  `num_shelf` INT NULL DEFAULT 0,
  `num_good` INT NULL DEFAULT 0,
  `creat_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`wh_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wmsdb`.`staff`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wmsdb`.`staff` (
  `staff_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `staff_password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `age` INT NULL,
  `gender` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `is_manager` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`staff_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wmsdb`.`staff_access`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wmsdb`.`staff_access` (
  `staff_staff_id` INT NOT NULL,
  `warehouse_wh_id` INT NOT NULL,
  PRIMARY KEY (`staff_staff_id`, `warehouse_wh_id`),
  INDEX `fk_staff_has_warehouse_warehouse1_idx` (`warehouse_wh_id` ASC) VISIBLE,
  INDEX `fk_staff_has_warehouse_staff_idx` (`staff_staff_id` ASC) VISIBLE,
  CONSTRAINT `fk_staff_has_warehouse_staff`
    FOREIGN KEY (`staff_staff_id`)
    REFERENCES `wmsdb`.`staff` (`staff_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_staff_has_warehouse_warehouse1`
    FOREIGN KEY (`warehouse_wh_id`)
    REFERENCES `wmsdb`.`warehouse` (`wh_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wmsdb`.`shelf`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wmsdb`.`shelf` (
  `shelf_id` INT NOT NULL AUTO_INCREMENT,
  `shelf_name` VARCHAR(45) NULL,
  `num_floor` INT NOT NULL DEFAULT 5,
  `warehouse_wh_id` INT NOT NULL,
  PRIMARY KEY (`shelf_id`, `warehouse_wh_id`),
  INDEX `fk_shelf_warehouse1_idx` (`warehouse_wh_id` ASC) VISIBLE,
  CONSTRAINT `fk_shelf_warehouse1`
    FOREIGN KEY (`warehouse_wh_id`)
    REFERENCES `wmsdb`.`warehouse` (`wh_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wmsdb`.`good`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wmsdb`.`good` (
  `good_id` INT NOT NULL AUTO_INCREMENT,
  `good_name` VARCHAR(45) NOT NULL,
  `good_type` VARCHAR(45) NOT NULL DEFAULT 'default type',
  `floor` INT NOT NULL DEFAULT 1,
  `warehousing_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `shelf_shelf_id` INT NOT NULL,
  `shelf_warehouse_wh_id` INT NOT NULL,
  PRIMARY KEY (`good_id`, `shelf_shelf_id`, `shelf_warehouse_wh_id`),
  INDEX `fk_good_shelf1_idx` (`shelf_shelf_id` ASC, `shelf_warehouse_wh_id` ASC) VISIBLE,
  CONSTRAINT `fk_good_shelf1`
    FOREIGN KEY (`shelf_shelf_id` , `shelf_warehouse_wh_id`)
    REFERENCES `wmsdb`.`shelf` (`shelf_id` , `warehouse_wh_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wmsdb`.`robot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wmsdb`.`robot` (
  `robot_id` INT NOT NULL AUTO_INCREMENT,
  `warehouse_wh_id` INT NOT NULL,
  PRIMARY KEY (`robot_id`, `warehouse_wh_id`),
  INDEX `fk_robot_warehouse1_idx` (`warehouse_wh_id` ASC) VISIBLE,
  CONSTRAINT `fk_robot_warehouse1`
    FOREIGN KEY (`warehouse_wh_id`)
    REFERENCES `wmsdb`.`warehouse` (`wh_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wmsdb`.`log_robot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wmsdb`.`log_robot` (
  `log_id` INT NOT NULL AUTO_INCREMENT,
  `send_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `host_ip` VARCHAR(45) NULL,
  `file_size` DECIMAL(10,3) NULL,
  `rec_status` TINYINT NULL,
  `write_status` TINYINT NULL,
  `robot_robot_id` INT NOT NULL,
  `robot_warehouse_wh_id` INT NOT NULL,
  PRIMARY KEY (`log_id`, `robot_robot_id`, `robot_warehouse_wh_id`),
  INDEX `fk_log_robot_robot1_idx` (`robot_robot_id` ASC, `robot_warehouse_wh_id` ASC) VISIBLE,
  CONSTRAINT `fk_log_robot_robot1`
    FOREIGN KEY (`robot_robot_id` , `robot_warehouse_wh_id`)
    REFERENCES `wmsdb`.`robot` (`robot_id` , `warehouse_wh_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
