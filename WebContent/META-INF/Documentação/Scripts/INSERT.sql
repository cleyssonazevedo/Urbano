USE URBANO;

/* Motoso */
INSERT INTO Veiculo(chassi, modelo, marca, ano, kilometragem, cor, combustivel, finalPlaca, preco, versao, tipo, numPortas, cambio, detalhes) 
	VALUES 	('9BWZZZ377VT001928','NX400', 'HONDA', '2001/2002', '89068', 'Preto', 'GASOLINA', '008', '8000.00', 'SPORT', 'MOTO', null, 'MANUAL', '400cc'),
			('9BWZZZ377VT001921','Iron 883', 'Harley Davidson', '2012/2013', '50002', 'Vermelho', 'GASOLINA', '726', '27800.00', 'CUSTOM', 'MOTO', null, 'MANUAL', '600cc'),
	 		('9BWZZZ377VT001922','PX', 'HONDA', '2015/2016', '0', 'Branca', 'GASOLINA', '112', '11490.00', 'SCOOTER', 'MOTO', null, 'MANUAL', '150cc'),
			('9BWZZZ377VT001929','Cbx Twister 250', 'HONDA', '2006/2007', '150726', 'Preto', 'GASOLINA', '008', '5799.99', null, 'MOTO', null, 'MANUAL', '250cc'),
			('9BWZZZ377VT001920','XTZ 250 Lander', 'YAMAHA', '2010/2011', '275098', 'Azul', 'GASOLINA', '008', '8000.00', 'LANDER', 'MOTO', null, 'MANUAL', '250cc');

/* Carros */
INSERT INTO Veiculo(chassi, modelo, marca, ano, kilometragem, cor, combustivel, finalPlaca, preco, versao, tipo, numPortas, cambio, detalhes) 
	VALUES 	('LJCPCBLCX11000237','Siena', ' Fiat', '2003/2004', '67854', 'Preto', 'GASOLINA', '567', '1900.00', 'Attractive ', 'CARRO', 4, 'AUTOMATICO', '1955cm'),
			('LJCPCBLCX11000238','Celta', 'General Motors', '2008/2009', '76853', 'Azul', 'GASOLINA', '745', '23800.00', ' LS', 'CARRO', 4, 'MANUAL', '1822cm'),
	 		('LJCPCBLCX11000239','Voyage', 'Volkswagen', '2015/2016', '56748', 'Prata', 'GASOLINA', '142', '11322.00', 'Highline', 'CARRO', 2, 'MANUAL', '1645cm'),
			('LJCPCBLCX11000240','HB20', 'Hyundai', '2007/2008', '0', 'Preto', 'GASOLINA', '333', '50079.99', 'R-Spec', 'CARRO', 4, 'AUTOMATICO', '1591cm'),
			('LJCPCBLCX11000241','Corsa', 'General Motors', '2005/2006', '23412', 'Cinza', 'GASOLINA', '112', '1200.00', 'null', 'CARRO', 4, 'AUTOMATICO', '1796cm');