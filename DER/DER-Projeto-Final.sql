-- MySQL Script generated by MySQL Workbench
-- Thu Jul 28 15:45:54 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema _projeto-final
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema _projeto-final
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `_projeto-final` DEFAULT CHARACTER SET utf8 ;
USE `_projeto-final` ;

-- -----------------------------------------------------
-- Table `_projeto-final`.`tb_temas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `_projeto-final`.`tb_temas` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `ongs` VARCHAR(255) NOT NULL,
  `governo` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `_projeto-final`.`tb_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `_projeto-final`.`tb_usuario` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `login` VARCHAR(255) NOT NULL,
  `senha` VARCHAR(255) NOT NULL,
  `senha_admin` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `_projeto-final`.`tb_postagens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `_projeto-final`.`tb_postagens` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(255) NOT NULL,
  `subtitulo` VARCHAR(255) NULL,
  `autor` VARCHAR(255) NOT NULL,
  `conteudo` VARCHAR(1000) NOT NULL,
  `foto` VARCHAR(255) NULL,
  `tb_tema_id` BIGINT NOT NULL,
  `tb_usuario_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tb_postagens_tb_tema_idx` (`tb_tema_id` ASC) VISIBLE,
  INDEX `fk_tb_postagens_tb_usuario1_idx` (`tb_usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_tb_postagens_tb_tema`
    FOREIGN KEY (`tb_tema_id`)
    REFERENCES `_projeto-final`.`tb_temas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_postagens_tb_usuario1`
    FOREIGN KEY (`tb_usuario_id`)
    REFERENCES `_projeto-final`.`tb_usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
