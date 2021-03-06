-- MySQL Script generated by MySQL Workbench
-- Mon Jul  4 21:17:54 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`warehouse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`warehouse` (
  `wh_id` INT NOT NULL,
  `wh_name` VARCHAR(45) NULL,
  `wh_type` VARCHAR(45) NULL,
  `num_shelf` INT NULL,
  `num_good` INT NULL,
  `creat_time` DATETIME NULL,
  PRIMARY KEY (`wh_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`staff`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`staff` (
  `staff_id` INT NOT NULL,
  `username` VARCHAR(45) NULL,
  `staff_password` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `age` INT NULL,
  `gender` VARCHAR(45) NULL,
  `telephone` VARCHAR(45) NULL,
  `is_manager` TINYINT NOT NULL,
  PRIMARY KEY (`staff_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`staff_access`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`staff_access` (
  `staff_staff_id` INT NOT NULL,
  `warehouse_wh_id` INT NOT NULL,
  PRIMARY KEY (`staff_staff_id`, `warehouse_wh_id`),
  INDEX `fk_staff_has_warehouse_warehouse1_idx` (`warehouse_wh_id` ASC) VISIBLE,
  INDEX `fk_staff_has_warehouse_staff_idx` (`staff_staff_id` ASC) VISIBLE,
  CONSTRAINT `fk_staff_has_warehouse_staff`
    FOREIGN KEY (`staff_staff_id`)
    REFERENCES `mydb`.`staff` (`staff_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_staff_has_warehouse_warehouse1`
    FOREIGN KEY (`warehouse_wh_id`)
    REFERENCES `mydb`.`warehouse` (`wh_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`shelf`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`shelf` (
  `shelf_id` INT NOT NULL,
  `shelf_name` VARCHAR(45) NULL,
  `num_floor` INT NULL DEFAULT 5,
  `warehouse_wh_id` INT NOT NULL,
  PRIMARY KEY (`shelf_id`, `warehouse_wh_id`),
  INDEX `fk_shelf_warehouse1_idx` (`warehouse_wh_id` ASC) VISIBLE,
  CONSTRAINT `fk_shelf_warehouse1`
    FOREIGN KEY (`warehouse_wh_id`)
    REFERENCES `mydb`.`warehouse` (`wh_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`good`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`good` (
  `good_id` INT NOT NULL,
  `good_name` VARCHAR(45) NULL,
  `good_type` VARCHAR(45) NULL,
  `floor` INT NULL,
  `warehousing_time` DATETIME NULL,
  `shelf_shelf_id` INT NOT NULL,
  `shelf_warehouse_wh_id` INT NOT NULL,
  PRIMARY KEY (`good_id`, `shelf_shelf_id`, `shelf_warehouse_wh_id`),
  INDEX `fk_good_shelf1_idx` (`shelf_shelf_id` ASC, `shelf_warehouse_wh_id` ASC) VISIBLE,
  CONSTRAINT `fk_good_shelf1`
    FOREIGN KEY (`shelf_shelf_id` , `shelf_warehouse_wh_id`)
    REFERENCES `mydb`.`shelf` (`shelf_id` , `warehouse_wh_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`robot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`robot` (
  `robot_id` INT NOT NULL,
  `warehouse_wh_id` INT NOT NULL,
  PRIMARY KEY (`robot_id`, `warehouse_wh_id`),
  INDEX `fk_robot_warehouse1_idx` (`warehouse_wh_id` ASC) VISIBLE,
  CONSTRAINT `fk_robot_warehouse1`
    FOREIGN KEY (`warehouse_wh_id`)
    REFERENCES `mydb`.`warehouse` (`wh_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`log_robot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`log_robot` (
  `log_id` INT NOT NULL,
  `send_time` DATETIME NULL,
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
    REFERENCES `mydb`.`robot` (`robot_id` , `warehouse_wh_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
