
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
  `marca` VARCHAR(45) NOT NULL,
  `tamanho` DOUBLE NOT NULL,
  `cor` VARCHAR(45) NOT NULL,
  `altura` DOUBLE NULL,
  `largura` DOUBLE NULL,
  `diametro` DOUBLE NULL,
  `material` VARCHAR(45) NULL,
  `steamCompativel` VARCHAR(100) NULL,
  `preco` DOUBLE NULL,
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
  `capacidade` DOUBLE NULL,
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
  `lavavel` TINYINT NOT NULL,
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
  `compri_Downstem` DOUBLE NULL,
  `material` VARCHAR(45) NULL,
  `preco` DOUBLE NULL,
  PRIMARY KEY (`id_steam`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ArguileBar`.`alunimio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ArguileBar`.`alunimio` (
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
-- Table `ArguileBar`.`itensArguile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ArguileBar`.`itensArguile` (
  `id_itensArguile` INT NOT NULL AUTO_INCREMENT,
  `alunimio_id_aluminio` INT NOT NULL,
  `carvao_id_carvao` INT NOT NULL,
  `rosh_id_rosh` INT NOT NULL,
  `mangueira_id_mangueira` INT NOT NULL,
  `essencia_id_essencia` INT NOT NULL,
  `steam_id_steam` INT NOT NULL,
  `vaso_id_vaso` INT NOT NULL,
  PRIMARY KEY (`id_itensArguile`, `alunimio_id_aluminio`, `carvao_id_carvao`, `rosh_id_rosh`, `mangueira_id_mangueira`, `essencia_id_essencia`, `steam_id_steam`, `vaso_id_vaso`),
  INDEX `fk_itensArguile_alunimio1_idx` (`alunimio_id_aluminio` ASC),
  INDEX `fk_itensArguile_carvao1_idx` (`carvao_id_carvao` ASC),
  INDEX `fk_itensArguile_rosh1_idx` (`rosh_id_rosh` ASC),
  INDEX `fk_itensArguile_mangueira1_idx` (`mangueira_id_mangueira` ASC),
  INDEX `fk_itensArguile_essencia1_idx` (`essencia_id_essencia` ASC),
  INDEX `fk_itensArguile_steam1_idx` (`steam_id_steam` ASC),
  INDEX `fk_itensArguile_vaso1_idx` (`vaso_id_vaso` ASC),
  CONSTRAINT `fk_tb_itensArguile_tb_arguile1`
    FOREIGN KEY (`id_itensArguile`)
    REFERENCES `ArguileBar`.`tb_arguile` (`id_arguile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_itensArguile_alunimio1`
    FOREIGN KEY (`alunimio_id_aluminio`)
    REFERENCES `ArguileBar`.`alunimio` (`id_aluminio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_itensArguile_carvao1`
    FOREIGN KEY (`carvao_id_carvao`)
    REFERENCES `ArguileBar`.`carvao` (`id_carvao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_itensArguile_rosh1`
    FOREIGN KEY (`rosh_id_rosh`)
    REFERENCES `ArguileBar`.`rosh` (`id_rosh`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_itensArguile_mangueira1`
    FOREIGN KEY (`mangueira_id_mangueira`)
    REFERENCES `ArguileBar`.`mangueira` (`id_mangueira`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_itensArguile_essencia1`
    FOREIGN KEY (`essencia_id_essencia`)
    REFERENCES `ArguileBar`.`essencia` (`id_essencia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_itensArguile_steam1`
    FOREIGN KEY (`steam_id_steam`)
    REFERENCES `ArguileBar`.`steam` (`id_steam`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_itensArguile_vaso1`
    FOREIGN KEY (`vaso_id_vaso`)
    REFERENCES `ArguileBar`.`vaso` (`id_vaso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ArguileBar`.`produtosDiversos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ArguileBar`.`produtosDiversos` (
  `id_prodDiversos` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `marca` VARCHAR(45) NOT NULL,
  `peso` VARCHAR(45) NULL,
  `preco` VARCHAR(45) NULL,
  PRIMARY KEY (`id_prodDiversos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ArguileBar`.`pedidoItens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ArguileBar`.`pedidoItens` (
  `id_pedidoItens` INT NOT NULL,
  `produtosDiversos_id_produtosDiversos` INT NOT NULL,
  `quantidade` INT NULL,
  PRIMARY KEY (`id_pedidoItens`, `produtosDiversos_id_produtosDiversos`),
  INDEX `fk_tb_pedidoItens_tb_produtosDiversos1_idx` (`produtosDiversos_id_produtosDiversos` ASC),
  CONSTRAINT `fk_tb_pedidoItens_tb_produtosDiversos1`
    FOREIGN KEY (`produtosDiversos_id_produtosDiversos`)
    REFERENCES `ArguileBar`.`produtosDiversos` (`id_prodDiversos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
  `telefoneResidencial` VARCHAR(45) NULL,
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
-- Table `ArguileBar`.`arguile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ArguileBar`.`arguile` (
  `id_arguile` INT NOT NULL AUTO_INCREMENT,
  `itensArguile_id_itensArguile` INT NOT NULL,
  `itensArguile_alunimio_id_aluminio` INT NOT NULL,
  `itensArguile_carvao_id_carvao` INT NOT NULL,
  `itensArguile_rosh_id_rosh` INT NOT NULL,
  `itensArguile_mangueira_id_mangueira` INT NOT NULL,
  `itensArguile_essencia_id_essencia` INT NOT NULL,
  `itensArguile_steam_id_steam` INT NOT NULL,
  `itensArguile_vaso_id_vaso` INT NOT NULL,
  PRIMARY KEY (`id_arguile`, `itensArguile_id_itensArguile`, `itensArguile_alunimio_id_aluminio`, `itensArguile_carvao_id_carvao`, `itensArguile_rosh_id_rosh`, `itensArguile_mangueira_id_mangueira`, `itensArguile_essencia_id_essencia`, `itensArguile_steam_id_steam`, `itensArguile_vaso_id_vaso`),
  INDEX `fk_arguile_itensArguile1_idx` (`itensArguile_id_itensArguile` ASC, `itensArguile_alunimio_id_aluminio` ASC, `itensArguile_carvao_id_carvao` ASC, `itensArguile_rosh_id_rosh` ASC, `itensArguile_mangueira_id_mangueira` ASC, `itensArguile_essencia_id_essencia` ASC, `itensArguile_steam_id_steam` ASC, `itensArguile_vaso_id_vaso` ASC),
  CONSTRAINT `fk_arguile_itensArguile1`
    FOREIGN KEY (`itensArguile_id_itensArguile` , `itensArguile_alunimio_id_aluminio` , `itensArguile_carvao_id_carvao` , `itensArguile_rosh_id_rosh` , `itensArguile_mangueira_id_mangueira` , `itensArguile_essencia_id_essencia` , `itensArguile_steam_id_steam` , `itensArguile_vaso_id_vaso`)
    REFERENCES `ArguileBar`.`itensArguile` (`id_itensArguile` , `alunimio_id_aluminio` , `carvao_id_carvao` , `rosh_id_rosh` , `mangueira_id_mangueira` , `essencia_id_essencia` , `steam_id_steam` , `vaso_id_vaso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ArguileBar`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ArguileBar`.`pedido` (
  `id_pedido` INT NOT NULL,
  `status_id_status` INT NOT NULL,
  `funcionario_id_funcionario` INT NOT NULL,
  `mesa_id_mesa` INT NOT NULL,
  `pedidoItens_id_pedidoItens` INT NOT NULL,
  `pedidoItens_produtosDiversos_id_prodDiversos` INT NOT NULL,
  `arguile_id_arguile` INT NOT NULL,
  `arguile_itensArguile_id_itensArguile` INT NOT NULL,
  `arguile_itensArguile_alunimio_id_aluminio` INT NOT NULL,
  `arguile_itensArguile_carvao_id_carvao` INT NOT NULL,
  `arguile_itensArguile_rosh_id_rosh` INT NOT NULL,
  `arguile_itensArguile_mangueira_id_mangueira` INT NOT NULL,
  `arguile_itensArguile_essencia_id_essencia` INT NOT NULL,
  `arguile_itensArguile_steam_id_steam` INT NOT NULL,
  `arguile_itensArguile_vaso_id_vaso` INT NOT NULL,
  PRIMARY KEY (`id_pedido`, `status_id_status`, `funcionario_id_funcionario`, `mesa_id_mesa`, `pedidoItens_id_pedidoItens`, `pedidoItens_produtosDiversos_id_prodDiversos`, `arguile_id_arguile`, `arguile_itensArguile_id_itensArguile`, `arguile_itensArguile_alunimio_id_aluminio`, `arguile_itensArguile_carvao_id_carvao`, `arguile_itensArguile_rosh_id_rosh`, `arguile_itensArguile_mangueira_id_mangueira`, `arguile_itensArguile_essencia_id_essencia`, `arguile_itensArguile_steam_id_steam`, `arguile_itensArguile_vaso_id_vaso`),
  INDEX `fk_tb_pedido_tb_status1_idx` (`status_id_status` ASC),
  INDEX `fk_tb_pedido_tb_funcionario1_idx` (`funcionario_id_funcionario` ASC),
  INDEX `fk_tb_pedido_tb_mesa1_idx` (`mesa_id_mesa` ASC),
  INDEX `fk_tb_pedido_tb_pedidoItens1_idx` (`pedidoItens_id_pedidoItens` ASC, `pedidoItens_produtosDiversos_id_prodDiversos` ASC),
  INDEX `fk_pedido_arguile1_idx` (`arguile_id_arguile` ASC, `arguile_itensArguile_id_itensArguile` ASC, `arguile_itensArguile_alunimio_id_aluminio` ASC, `arguile_itensArguile_carvao_id_carvao` ASC, `arguile_itensArguile_rosh_id_rosh` ASC, `arguile_itensArguile_mangueira_id_mangueira` ASC, `arguile_itensArguile_essencia_id_essencia` ASC, `arguile_itensArguile_steam_id_steam` ASC, `arguile_itensArguile_vaso_id_vaso` ASC),
  CONSTRAINT `fk_tb_pedido_tb_status1`
    FOREIGN KEY (`status_id_status`)
    REFERENCES `ArguileBar`.`statusPedido` (`id_status`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_pedido_tb_funcionario1`
    FOREIGN KEY (`funcionario_id_funcionario`)
    REFERENCES `ArguileBar`.`funcionario` (`id_funcionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_pedido_tb_mesa1`
    FOREIGN KEY (`mesa_id_mesa`)
    REFERENCES `ArguileBar`.`mesa` (`id_mesa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_pedido_tb_pedidoItens1`
    FOREIGN KEY (`pedidoItens_id_pedidoItens` , `pedidoItens_produtosDiversos_id_prodDiversos`)
    REFERENCES `ArguileBar`.`pedidoItens` (`id_pedidoItens` , `produtosDiversos_id_produtosDiversos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_arguile1`
    FOREIGN KEY (`arguile_id_arguile` , `arguile_itensArguile_id_itensArguile` , `arguile_itensArguile_alunimio_id_aluminio` , `arguile_itensArguile_carvao_id_carvao` , `arguile_itensArguile_rosh_id_rosh` , `arguile_itensArguile_mangueira_id_mangueira` , `arguile_itensArguile_essencia_id_essencia` , `arguile_itensArguile_steam_id_steam` , `arguile_itensArguile_vaso_id_vaso`)
    REFERENCES `ArguileBar`.`arguile` (`id_arguile` , `itensArguile_id_itensArguile` , `itensArguile_alunimio_id_aluminio` , `itensArguile_carvao_id_carvao` , `itensArguile_rosh_id_rosh` , `itensArguile_mangueira_id_mangueira` , `itensArguile_essencia_id_essencia` , `itensArguile_steam_id_steam` , `itensArguile_vaso_id_vaso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
