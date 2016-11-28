USE URBANO;

INSERT INTO Veiculo(chassi, modelo, marca, ano, kilometragem, cor, combustivel, finalPlaca, preco, versao, tipo, numPortas, cambio, detalhes) 
	VALUES 	('9BWZZZ377VT001928','NX400', 'HONDA', '2001/2002', '89068', 'Preto', 'GASOLINA', '008', '8000.00', 'SPORT', 'MOTO', null, 'MANUAL', '400cc'),
			('9BWZZZ377VT001921','Iron 883', 'Harley Davidson', '2012/2013', '50002', 'Vermelho', 'GASOLINA', '726', '27800.00', 'CUSTOM', 'MOTO', null, 'MANUAL', '600cc'),
	 		('9BWZZZ377VT001922','PX', 'HONDA', '2015/2016', '0', 'Branca', 'GASOLINA', '112', '11490.00', 'SCOOTER', 'MOTO', null, 'MANUAL', '150cc'),
			('9BWZZZ377VT001929','Cbx Twister 250', 'HONDA', '2006/2007', '150726', 'Preto', 'GASOLINA', '008', '5799.99', null, 'MOTO', null, 'MANUAL', '250cc'),
			('9BWZZZ377VT001920','XTZ 250 Lander', 'YAMAHA', '2010/2011', '275098', 'Azul', 'GASOLINA', '008', '8000.00', 'LANDER', 'MOTO', null, 'MANUAL', '250cc');


INSERT INTO Veiculo(chassi, modelo, marca, ano, kilometragem, cor, combustivel, finalPlaca, preco, versao, tipo, numPortas, cambio, detalhes) 
	VALUES 	('LJCPCBLCX11000237','Siena', ' Fiat', '2003/2004', '67854', 'Preto', 'GASOLINA', '567', '1900.00', 'Attractive ', 'CARRO', 4, 'AUTOMATICO', '1955cm'),
			('LJCPCBLCX11000238','Celta', 'General Motors', '2008/2009', '76853', 'Azul', 'GASOLINA', '745', '23800.00', ' LS', 'CARRO', 4, 'MANUAL', '1822cm'),
	 		('LJCPCBLCX11000239','Voyage', 'Volkswagen', '2015/2016', '56748', 'Prata', 'GASOLINA', '142', '11322.00', 'Highline', 'CARRO', 2, 'MANUAL', '1645cm'),
			('LJCPCBLCX11000240','HB20', 'Hyundai', '2007/2008', '0', 'Preto', 'GASOLINA', '333', '50079.99', 'R-Spec', 'CARRO', 4, 'AUTOMATICO', '1591cm'),
			('LJCPCBLCX11000241','Corsa', 'General Motors', '2005/2006', '23412', 'Cinza', 'GASOLINA', '112', '1200.00', 'null', 'CARRO', 4, 'AUTOMATICO', '1796cm');
            
INSERT INTO LOGIN (usuario, senha)
	VALUES  ('usuario0', '001'),
			('usuario1', '001'),
			('usuario2', '001'),
            ('usuario3', '001'),
            ('usuario4', '001'),
            ('usuario5', '001'),
            ('usuario6', '001'),
            ('usuario7', '001'),
            ('usuario8', '001'),
            ('usuario9', '001');
    
INSERT INTO CLIENTE (tipo, id_login)
	VALUES  ('FISICO', (SELECT ID FROM LOGIN WHERE usuario = 'usuario0')),
			('FISICO', (SELECT ID FROM LOGIN WHERE usuario = 'usuario1')),
            ('FISICO', (SELECT ID FROM LOGIN WHERE usuario = 'usuario2')),
            ('FISICO', (SELECT ID FROM LOGIN WHERE usuario = 'usuario3')),
            ('FISICO', (SELECT ID FROM LOGIN WHERE usuario = 'usuario4')),
            
            ('JURIDICO', (SELECT ID FROM LOGIN WHERE usuario = 'usuario5')),
            ('JURIDICO', (SELECT ID FROM LOGIN WHERE usuario = 'usuario6')),
            ('JURIDICO', (SELECT ID FROM LOGIN WHERE usuario = 'usuario7')),
            ('JURIDICO', (SELECT ID FROM LOGIN WHERE usuario = 'usuario8')),
            ('JURIDICO', (SELECT ID FROM LOGIN WHERE usuario = 'usuario9'));

/* ERRO EM FISICO */
INSERT INTO FISICO (nome, cpf, rg, id_cliente)
	VALUES  ('Maria Aparecida Anunciação', '09877687437' , '77845896-6', (SELECT ID FROM CLIENTE WHERE id_login = (SELECT ID FROM LOGIN WHERE usuario = 'usuario0'))),
            ('Francisco de Assis Matos', '32564985256' , '4475452-3', (SELECT ID FROM CLIENTE WHERE ID_LOGIN = (SELECT ID FROM LOGIN WHERE usuario = 'usuario1')))
            ('Aldair Silva', '25469896514' , '11424587-7', (SELECT ID FROM CLIENTE WHERE ID_LOGIN = (SELECT ID FROM LOGIN WHERE usuario = 'usuario2'))),
            ('Benedito Francisco Souza', '52136458896' , '42517896-8', (SELECT ID FROM CLIENTE WHERE ID_LOGIN = (SELECT ID FROM LOGIN WHERE usuario = 'usuario3'))),
            ('Joaquina Elisabeth Monteiro', '47854512023' , '12458795-6', (SELECT ID FROM CLIENTE WHERE ID_LOGIN = (SELECT ID FROM LOGIN WHERE usuario = 'usuario4')));

INSERT INTO JURIDICO (nomeFantasia, razaoSocial, cnpj, ie, im, id_cliente)
	VALUES  ('EMPRESA A', 'EMPRESA A LTDA', '21942117000164', null, null, (SELECT ID FROM CLIENTE WHERE id_login = (SELECT ID FROM LOGIN WHERE usuario = 'usuario5'))),
			('EMPRESA B', 'EMPRESA B LTDA', '83170679000194', null, null, (SELECT ID FROM CLIENTE WHERE id_login = (SELECT ID FROM LOGIN WHERE usuario = 'usuario6'))),
            ('EMPRESA C', 'EMPRESA C LTDA', '87535753000180', null, null, (SELECT ID FROM CLIENTE WHERE id_login = (SELECT ID FROM LOGIN WHERE usuario = 'usuario7'))),
            ('EMPRESA D', 'EMPRESA D LTDA', '22440177000141', null, null, (SELECT ID FROM CLIENTE WHERE id_login = (SELECT ID FROM LOGIN WHERE usuario = 'usuario8'))),
            ('EMPRESA E', 'EMPRESA E LTDA', '52232796000101', null, null, (SELECT ID FROM CLIENTE WHERE id_login = (SELECT ID FROM LOGIN WHERE usuario = 'usuario9')));

INSERT INTO ENDERECO (logradouro, numero, bairro, cidade, estado, pais, complemento ,cep, tipoEndereco, id_cliente)
	VALUES  ('R. America', '126' , 'JD.Felicidade' , 'São Paulo', 'SP', 'Brasil', null,'08290920', 'RESIDENCIAL',(SELECT ID_CLIENTE FROM FISICO WHERE CPF = '32564985256')),
			('R. Coronel Basilio', '214', 'JD. Campo limpo', 'São paulo', 'SP', 'Brasil', 'Andar 1', '92872672', 'RESIDENCIAL', (SELECT ID_CLIENTE FROM FISICO WHERE CPF = '32514784523')),
            ('R. Eliseu Mota', '15', 'Estrela do Mar', 'São Paulo', 'SP', 'Brasil', 'Terro', '82765100', 'RESIDENCIAL', (SELECT ID_CLIENTE FROM FISICO WHERE CPF = '25469896514')),
            ('R. Marilia Bartolomeu', '89', 'JD. Das Rosas', 'São Paulo', 'SP', 'Brasil', null,  '86270099', 'RESIDENCIAL', (SELECT ID_CLIENTE FROM FISICO WHERE CPF = '52136458896')),
            ('R. Das Flores', '13548', 'Santana', 'São Paulo', 'SP', 'Brasil', null, '77266119', 'RESIDENCIAL', (SELECT ID_CLIENTE FROM FISICO WHERE CPF = '47854512023')),
          
            ('Av. Aguas Claras', '56',  'JD São Paulo',  'São Paulo', 'SP', 'Brasil', 'Andar 20', '82780099', 'COMERCIAL', (SELECT ID_CLIENTE FROM JURIDICO WHERE CNPJ = '21942117000164')),
            ('R. Matias de Azevedo', '1000', 'JD das Camelias', 'São Paulo', 'SP', 'Brasil', 'Andar 10', '22611028','COMERCIAL', (SELECT ID_CLIENTE FROM JURIDICO WHERE CNPJ = '83170679000194')),
			('Av. Mustafá Lourene', '3438', 'Vl Albertina', 'São Paulo', 'SP', 'Brasil', 'Subsolo', '77550099', 'COMERCIAL', (SELECT ID_CLIENTE FROM JURIDICO WHERE CNPJ = '87535753000180')),
			('Av. Antonio Maria de Laet', '4', 'Tucuruvi', 'São Paulo', 'SP', 'Brasil', null, '11226582', 'COMERCIAL', (SELECT ID_CLIENTE FROM JURIDICO WHERE CNPJ = '22440177000141')),
			('Av. Aguas Claras', '56',  'JD São Paulo',  'São Paulo', 'SP', 'Brasil', 'Andar 19', '82780099', 'COMERCIAL', (SELECT ID_CLIENTE FROM JURIDICO WHERE CNPJ = '52232796000101'));

INSERT INTO EMAIL (email, tipoEmail, id_cliente)
	VALUES  ('francisco_Matos@gmail.com', 'PESSOAL', (SELECT ID_CLIENTE FROM FISICO WHERE CPF = '')),
			('maria_ap@hotmail.com', 'PESSOAL', (SELECT ID_CLIENTE FROM FISICO WHERE CPF = '')),
			('silva_asilva@marsoft.com.br', 'PESSOAL', (SELECT ID_CLIENTE FROM FISICO WHERE CPF = '')),
			('souza.souza@gmail.com', 'PESSOAL', (SELECT ID_CLIENTE FROM FISICO WHERE CPF = '')),
			
            ('bitencourt.cesar@outlook.com.br', 'COMERCIAL', (SELECT ID_CLIENTE FROM JURIDICO WHERE CNPJ = '87535753000180')),
            ('marciobrandaof@hotmail.com', 'COMERCIAL', (SELECT ID_CLIENTE FROM JURIDICO WHERE CNPJ = '22440177000141'));

INSERT INTO TELEFONE (ddd, numero, tipoTelefone, id_cliente)
	VALUES  ('11', '2253-2563', 'PESSOAL', (SELECT ID_CLIENTE FROM FISICO WHERE CPF = '')),
			('11', '9562-6311', 'PESSOAL', (SELECT ID_CLIENTE FROM FISICO WHERE CPF = '')),
			('12', '8543-2569', 'PESSOAL', (SELECT ID_CLIENTE FROM FISICO WHERE CPF = '')),
			
            ('12', '7452-2536', 'COMERCIAL', (SELECT ID_CLIENTE FROM JURIDICO WHERE CNPJ = '22440177000141')),
			('11', '2212-1456', 'COMERCIAL', (SELECT ID_CLIENTE FROM JURIDICO WHERE CNPJ = '87535753000180')),
			('12', '5569-9852', 'COMERCIAL', (SELECT ID_CLIENTE FROM JURIDICO WHERE CNPJ = '52232796000101'));
            
INSERT INTO RESERVAR (dataRegistro, dataReserva, id_cliente, id_veiculo)
	VALUES  (now(), '#10/12/2016 10:00', (SELECT ID_CLIENTE FROM FISICO WHERE CPF = '')),
			(now(), '#10/12/2016 10:00', (SELECT ID_CLIENTE FROM FISICO WHERE CPF = '')),
            (now(), '#10/12/2016 10:00', (SELECT ID_CLIENTE FROM FISICO WHERE CPF = '')),
            (now(), '#10/12/2016 10:00', (SELECT ID_CLIENTE FROM FISICO WHERE CPF = '')),
            (now(), '#10/12/2016 10:00', (SELECT ID_CLIENTE FROM FISICO WHERE CPF = '')),
            
            (now(), '#01/12/2016 10:00', (SELECT ID_CLIENTE FROM JURIDICO WHERE CNPJ = '')),
            (now(), '#02/12/2016 10:00', (SELECT ID_CLIENTE FROM JURIDICO WHERE CNPJ = '')),
            (now(), '#01/12/2016 11:00', (SELECT ID_CLIENTE FROM JURIDICO WHERE CNPJ = '')),
            (now(), '#05/12/2016 18:00', (SELECT ID_CLIENTE FROM JURIDICO WHERE CNPJ = '')),
            (now(), '#11/12/2016 09:00', (SELECT ID_CLIENTE FROM JURIDICO WHERE CNPJ = ''));