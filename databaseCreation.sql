-- -----------------------------------------------------
-- CREATING TABLES
-- -----------------------------------------------------

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema web_services
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `web_services` ;

-- -----------------------------------------------------
-- Schema web_services
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `web_services` DEFAULT CHARACTER SET utf8 ;
USE `web_services` ;

-- -----------------------------------------------------
-- Table `web_services`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `web_services`.`user` ;

CREATE TABLE IF NOT EXISTS `web_services`.`user` (
  `ID_user` INT NOT NULL AUTO_INCREMENT,
  `pseudo` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `date_creation` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID_user`)
  )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `web_services`.`multimedia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `web_services`.`multimedia` ;

CREATE TABLE IF NOT EXISTS `web_services`.`multimedia` (
  `ID_multimedia` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(500) NULL,
  `language` VARCHAR(2) NOT NULL,
  `genre` VARCHAR(45) NULL,
  `category` TINYINT(3) NOT NULL,
  `status` TINYINT(3) NOT NULL,
  `date_status` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_upload` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_release` DATE NULL,
  `ID_uploader` INT NOT NULL,
  PRIMARY KEY (`ID_multimedia`),
    FOREIGN KEY (`ID_uploader`)
    REFERENCES `web_services`.`user` (`ID_user`)
    )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `web_services`.`book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `web_services`.`book` ;

CREATE TABLE IF NOT EXISTS `web_services`.`book` (
  `ID_book` INT NOT NULL AUTO_INCREMENT,
  `author` VARCHAR(65) NOT NULL,
  `publisher` VARCHAR(65) NULL,
  `ID_multimedia` INT NOT NULL,
  PRIMARY KEY (`ID_book`),
    FOREIGN KEY (`ID_multimedia`)
    REFERENCES `web_services`.`multimedia` (`ID_multimedia`)
    )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `web_services`.`videoGame`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `web_services`.`videoGame` ;

CREATE TABLE IF NOT EXISTS `web_services`.`videoGame` (
  `ID_videoGame` INT NOT NULL AUTO_INCREMENT,
  `developper` VARCHAR(65) NOT NULL,
  `publisher` VARCHAR(65) NULL,
  `ID_multimedia` INT NOT NULL,
  PRIMARY KEY (`ID_videoGame`),
    FOREIGN KEY (`ID_multimedia`)
    REFERENCES `web_services`.`multimedia` (`ID_multimedia`)
    )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `web_services`.`film`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `web_services`.`film` ;

CREATE TABLE IF NOT EXISTS `web_services`.`film` (
  `ID_film` INT NOT NULL AUTO_INCREMENT,
  `director` VARCHAR(65) NOT NULL,
  `productor` VARCHAR(65) NULL,
  `mainCast` VARCHAR(65) NULL,
  `duration` TIME NULL,
  `ID_multimedia` INT NOT NULL,
  PRIMARY KEY (`ID_film`),
    FOREIGN KEY (`ID_multimedia`)
    REFERENCES `web_services`.`multimedia` (`ID_multimedia`)
    )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `web_services`.`rate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `web_services`.`rate` ;

CREATE TABLE IF NOT EXISTS `web_services`.`rate` (
  `ID_rate` INT NOT NULL AUTO_INCREMENT,
  `value` INT(3) NOT NULL,
  `ID_user` INT NOT NULL,
  `ID_multimedia` INT NOT NULL,
  PRIMARY KEY (`ID_rate`),
    FOREIGN KEY (`ID_user`)
    REFERENCES `web_services`.`user` (`ID_user`),
    FOREIGN KEY (`ID_multimedia`)
    REFERENCES `web_services`.`multimedia` (`ID_multimedia`)
    )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `web_services`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `web_services`.`comment` ;

CREATE TABLE IF NOT EXISTS `web_services`.`comment` (
  `ID_comment` INT NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(300) NOT NULL,
  `ID_user` INT NOT NULL,
  `ID_multimedia` INT NOT NULL,
  PRIMARY KEY (`ID_comment`),
    FOREIGN KEY (`ID_user`)
    REFERENCES `web_services`.`user` (`ID_user`),
    FOREIGN KEY (`ID_multimedia`)
    REFERENCES `web_services`.`multimedia` (`ID_multimedia`)
    )
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;