CREATE DATABASE Urbano CHARACTER SET utf8 COLLATE utf8_general_ci;

USE Urbano;

CREATE TABLE Cliente (
	id INT UNSIGNED AUTO_INCREMENT,
	tipo ENUM('Fisico', 'Juridico') NOT NULL,
	id_login INT UNSIGNED NOT NULL UNIQUE,
	PRIMARY KEY (id)
) ENGINE=InnoDB;
  
CREATE TABLE Email (
	id INT UNSIGNED AUTO_INCREMENT,
	email VARCHAR(255) NOT NULL,
	tipoEmail ENUM('PESSOAL', 'COMERCIAL', 'OUTROS') NOT NULL,
	id_cliente INT UNSIGNED NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;
    
CREATE TABLE Endereco (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	bairro VARCHAR(255) NOT NULL,
	cep CHAR(8) NOT NULL,
	cidade VARCHAR(255) NOT NULL,
	complemento VARCHAR(255),
	estado ENUM('AC', 'AL', 'AM', 'AP', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA', 'MG', 'MS', 'MT', 'PA', 'PB', 'PE', 'PI', 'PR', 'RJ', 'RN', 'RO', 'RR', 'RS', 'SC', 'SE', 'SP', 'TO') NOT NULL,
	logradouro VARCHAR(255) NOT NULL,
	numero VARCHAR(15) NOT NULL,
	pais VARCHAR(100) NOT NULL,
	tipoEndereco ENUM('RESIDENCIAL', 'COMERCIAL', 'COBRANCA', 'ENTREGA') NOT NULL,
	id_cliente INT UNSIGNED NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;
    
CREATE TABLE Fisico (
	cpf CHAR(11) UNIQUE NOT NULL,
	nome VARCHAR(255) NOT NULL,
	rg VARCHAR(20),
	id_cliente INT UNSIGNED NOT NULL,
	PRIMARY KEY (id_cliente)
) ENGINE=InnoDB;
    
CREATE TABLE Juridico (
	cnpj CHAR(15) UNIQUE NOT NULL,
	ie CHAR(12),
	im CHAR(8),
	nomeFantasia VARCHAR(255) UNIQUE NOT NULL,
	razaoSocial VARCHAR(255) UNIQUE NOT NULL,
	id_cliente INT UNSIGNED NOT NULL,
	PRIMARY KEY (id_cliente)
) ENGINE=InnoDB;
    
CREATE TABLE Login (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	senha VARCHAR(255) BINARY NOT NULL,
	usuario VARCHAR(255) BINARY UNIQUE NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;
    
CREATE TABLE Reservar (
	id BIGINT NOT NULL AUTO_INCREMENT,
	dataRegistro DATETIME NOT NULL,
	dataReserva DATETIME NOT NULL,
	id_cliente INT UNSIGNED NOT NULL,
	id_veiculo bigint NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;
    
CREATE TABLE Telefone (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	ddd CHAR(2) NOT NULL,
	ddi CHAR(4) NOT NULL DEFAULT '0055',
	numero VARCHAR(25) NOT NULL,
	tipoTelefone ENUM('FIXO', 'CELULAR', 'COMERCIAL', 'FAX') NOT NULL,
	id_cliente INT UNSIGNED NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;
    
CREATE TABLE Veiculo (
	id BIGINT AUTO_INCREMENT,
	ano VARCHAR(20) NOT NULL,
	cambio VARCHAR(255) NOT NULL,
	chassi CHAR(17) NOT NULL UNIQUE,
	combustivel ENUM('GASOLINA', 'ALCOOL', 'DIESEL', 'FLEX', 'ELETRICO') NOT NULL,
	cor VARCHAR(100) NOT NULL,
	detalhes TEXT,
	finalPlaca CHAR(3) NOT NULL,
	kilometragem VARCHAR(255) NOT NULL,
	marca VARCHAR(255) NOT NULL,
	modelo VARCHAR(255) NOT NULL,
	numPortas CHAR(1),
	preco DECIMAL(11, 2) NOT NULL,
	tipo ENUM ('CARRO', 'MOTO') NOT NULL,
	versao VARCHAR(255),
	PRIMARY KEY (id)
) ENGINE=InnoDB;
    
ALTER TABLE Cliente ADD CONSTRAINT fk_cliente_login FOREIGN KEY (id_login) REFERENCES Login (id) ON DELETE CASCADE;
ALTER TABLE Email ADD CONSTRAINT fk_emails FOREIGN KEY (id_cliente) REFERENCES Cliente (id) ON DELETE CASCADE;
ALTER TABLE Endereco ADD CONSTRAINT fk_enderecos FOREIGN KEY (id_cliente) REFERENCES Cliente (id) ON DELETE CASCADE;
ALTER TABLE Fisico ADD CONSTRAINT fk_cliente_fisico FOREIGN KEY (id_cliente) REFERENCES Cliente (id) ON DELETE CASCADE;
ALTER TABLE Juridico ADD CONSTRAINT fk_cliente_juridico FOREIGN KEY (id_cliente) REFERENCES Cliente (id) ON DELETE CASCADE;
ALTER TABLE Reservar ADD CONSTRAINT fk_reserva_cliente FOREIGN KEY (id_cliente) REFERENCES Cliente (id) ON DELETE CASCADE;
ALTER TABLE Reservar ADD CONSTRAINT fk_reserva_veiculo FOREIGN KEY (id_veiculo) REFERENCES Veiculo (id) ON DELETE CASCADE;
ALTER TABLE Telefone ADD CONSTRAINT fk_telefones FOREIGN KEY (id_cliente) REFERENCES Cliente (id) ON DELETE CASCADE;


--Motos
INSERT INTO Veiculo(chassi, modelo, marca, ano, kilometragem, cor, combustivel, finalPlaca, preco, versao, tipo, numPortas, cambio, detalhes) 
	VALUES 	('9BWZZZ377VT001928','NX400', 'HONDA', '2001/2002', '89068', 'Preto', 'GASOLINA', '008', '8000.00', 'SPORT', 'MOTO', null, 'MANUAL', '400cc'),
			('9BWZZZ377VT001921','Iron 883', 'Harley Davidson', '2012/2013', '50002', 'Vermelho', 'GASOLINA', '726', '27800.00', 'CUSTOM', 'MOTO', null, 'MANUAL', '600cc'),
	 		('9BWZZZ377VT001922','PX', 'HONDA', '2015/2016', '0', 'Branca', 'GASOLINA', '112', '11490.00', 'SCOOTER', 'MOTO', null, 'MANUAL', '150cc'),
			('9BWZZZ377VT001929','Cbx Twister 250', 'HONDA', '2006/2007', '150726', 'Preto', 'GASOLINA', '008', '5799.99', null, 'MOTO', null, 'MANUAL', '250cc'),
			('9BWZZZ377VT001920','XTZ 250 Lander', 'YAMAHA', '2010/2011', '275098', 'Azul', 'GASOLINA', '008', '8000.00', 'LANDER', 'MOTO', null, 'MANUAL', '250cc');

--Carros
