
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema ArguileBar
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ArguileBar` DEFAULT CHARACTER SET utf8 ;
USE `ArguileBar` ;

-- -----------------------------------------------------
-- Table `ArguileBar`.`vaso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ArguileBar`.`vaso` (
  `id_vaso` INT NOT NULL AUTO_INCREMENT,
  `marca` VARCHAR(30) NULL,
  `tamanho` DOUBLE NULL,
  `cor` VARCHAR(15) NULL,
  `diametro` DOUBLE NULL,
  `material` VARCHAR(45) NULL,
  `preco` DOUBLE NULL,
  `quantidade` DOUBLE NULL,
  PRIMARY KEY (`id_vaso`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ArguileBar`.`rosh`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ArguileBar`.`rosh` (
  `id_rosh` INT NOT NULL AUTO_INCREMENT,
  `marca` VARCHAR(45) NOT NULL,
  `cor` VARCHAR(45) NOT NULL,
  `material` VARCHAR(45) NULL,
  `tamanho` TINYINT NOT NULL,
  `preco` DOUBLE NULL,
  PRIMARY KEY (`id_rosh`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ArguileBar`.`mangueira`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ArguileBar`.`mangueira` (
  `id_mangueira` INT NOT NULL AUTO_INCREMENT,
  `marca` VARCHAR(45) NOT NULL,
  `cor` VARCHAR(45) NULL,
  `material` VARCHAR(45) NULL,
  `comprimento` DOUBLE NOT NULL,
  `preco` DOUBLE NULL,
  PRIMARY KEY (`id_mangueira`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ArguileBar`.`steam`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ArguileBar`.`steam` (
  `id_steam` INT NOT NULL AUTO_INCREMENT,
  `marca` VARCHAR(45) NULL,
  `altura` DOUBLE NULL,
  `quantidade` DOUBLE NULL,
  `material` VARCHAR(45) NULL,
  `preco` DOUBLE NULL,
  PRIMARY KEY (`id_steam`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ArguileBar`.`alunimio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ArguileBar`.`aluminio` (
  `id_aluminio` INT NOT NULL,
  `marca` VARCHAR(45) NULL,
  `quantidade` VARCHAR(45) NULL,
  `preco` DOUBLE NULL,
  PRIMARY KEY (`id_aluminio`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ArguileBar`.`carvao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ArguileBar`.`carvao` (
  `id_carvao` INT NOT NULL,
  `marca` VARCHAR(45) NULL,
  `peso` DOUBLE NULL,
  `preco` DOUBLE NULL,
  PRIMARY KEY (`id_carvao`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ArguileBar`.`statusPedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ArguileBar`.`statusPedido` (
  `id_status` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id_status`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ArguileBar`.`funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ArguileBar`.`funcionario` (
  `id_funcionario` INT NOT NULL,
  `nome_func` VARCHAR(50) NULL,
  `cpf_func` VARCHAR(45) NULL,
  `data_adm` DATETIME NULL,
  `cargo` VARCHAR(45) NULL,
  `salario` DOUBLE NULL,
  `endereco` VARCHAR(45) NULL,
  `telefoneCelular` VARCHAR(45) NULL,
  PRIMARY KEY (`id_funcionario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ArguileBar`.`mesa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ArguileBar`.`mesa` (
  `id_mesa` INT NOT NULL,
  `capacidade` VARCHAR(45) NULL,
  `tb_mesacol` VARCHAR(45) NULL,
  PRIMARY KEY (`id_mesa`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ArguileBar`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ArguileBar`.`pedido` (
  `mesa_id_mesa` INT NOT NULL, 
  `status_id_status` INT NULL,
  PRIMARY KEY (`mesa_id_mesa`),
  CONSTRAINT `constraint_pedido_status`
  FOREIGN KEY (`status_id_status`)
    REFERENCES `ArguileBar`.`statuspedido` (`id_status`))

ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ArguileBar`.`pedido_bebida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ArguileBar`.`pedido_bebida` (
	`pedido_id_pedido` INT NOT NULL,
	`bebida_id_bebida` INT NOT NULL,
	CONSTRAINT `constraint_pedido_bebida`
	FOREIGN KEY (`bebida_id_bebida`)
    REFERENCES `ArguileBar`.`bebida` (`id_prodDiversos`),
    CONSTRAINT `constraint_pedido`
	FOREIGN KEY (`pedido_id_pedido`)
    REFERENCES `ArguileBar`.`pedido` (`mesa_id_mesa`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ArguileBar`.`pedido_essencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ArguileBar`.`pedido_essencia` (
	`pedido_id_pedido` INT NOT NULL,
	`essencia_id_essencia` INT NOT NULL,
	CONSTRAINT `constraint_pedido_essencia`
	FOREIGN KEY (`essencia_id_essencia`)
    REFERENCES `ArguileBar`.`essencia` (`id_essencia`),
    CONSTRAINT `constraint_pedido_pedido`
	FOREIGN KEY (`pedido_id_pedido`)
    REFERENCES `ArguileBar`.`pedido` (`mesa_id_mesa`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ArguileBar`.`essencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ArguileBar`.`essencia` (
  `id_essencia` INT NOT NULL AUTO_INCREMENT,
  `sabor` VARCHAR(45) NULL,
  `peso` VARCHAR(45) NULL,
  `marca` VARCHAR(45) NULL,
  `preco` DOUBLE NULL,
  `categoria` VARCHAR(45) NULL,
  PRIMARY KEY (`id_essencia`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `ArguileBar`.`usuario` (
  `login` INT NOT NULL,
  `senha` VARCHAR(45) NULL,
  PRIMARY KEY (`login`))
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;