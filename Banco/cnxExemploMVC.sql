1. 
CREATE TABLESPACE tbsExemploMVC
DATAFILE 'D:\Oracle\TableSpace\tbsExemploMVC.dbf'
SIZE 100m AUTOEXTEND ON NEXT 100m
EXTENT MANAGEMENT LOCAL;

2.
CREATE USER usuarioMVC identified by 123456
DEFAULT TABLESPACE tbsExemploMVC
QUOTA UNLIMITED ON tbsExemploMVC;

3.
GRANT CREATE SESSION, ALTER SESSION, CREATE DATABASE LINK,
CREATE MATERIALIZED VIEW, CREATE PROCEDURE, CREATE PUBLIC SYNONYM,
CREATE ROLE, CREATE SEQUENCE, CREATE SYNONYM, CREATE TABLE,
CREATE TRIGGER, CREATE TYPE, CREATE VIEW, UNLIMITED TABLESPACE TO usuarioMVC;

CREATE TABLE tblCARGO
(
  idCargo   NUMBER(5)     PRIMARY KEY,
  nomeCargo VARCHAR2(50)  UNIQUE NOT NULL,
  statusCargo NUMBER(1)   NOT NULL
);

CREATE SEQUENCE seqPKCARGO
START WITH 1 INCREMENT BY 1 
NOCACHE 
NOCYCLE;

INSERT INTO tblCARGO VALUES (seqPKCargo.NEXTVAL,'Chefe da Bagaça', 1);

commit

CREATE TABLE tblFUNCIONARIO
(
  idFunc    NUMBER        NOT NULL,
  nome      VARCHAR2(100) NOT NULL,
  sexo      CHAR(1)       NOT NULL,
  dataNasc  DATE          NOT NULL,
  cpf       CHAR(11)      NOT NULL,
  salario   NUMBER(10,2)  NOT NULL,
  email     VARCHAR2(100) NULL,
  telefone  VARCHAR2(15)  NOT NULL,
  foto      VARCHAR2(100) NOT NULL,
  idCargo   NUMBER        NOT NULL,
  statusFunc NUMBER(1)    NOT NULL,
  
  CONSTRAINT PK_FUNCIONARIO PRIMARY KEY (idFunc),
  CONSTRAINT FK_CARGO_FUNC  FOREIGN KEY (idCargo)
                                      REFERENCES tblCARGO(idCargo)
);

CREATE SEQUENCE seqPKFUNCIONARIO
START WITH 1 INCREMENT BY 1 
NOCACHE 
NOCYCLE;

SELECT * FROM tblFUNCIONARIO
SELECT * FROM tblCARGO

SELECT * FROM tblFuncionario WHERE idFunc = 2 OR nome LIKE '%NULL%' OR cpf = ''

INSERT INTO tblFUNCIONARIO (idFunc, nome, sexo, dataNasc, cpf, salario, email, telefone, foto, idCargo, statusFunc)
VALUES (seqPKFUNCIONARIO.NEXTVAL, 'Leandro', 'M', '08/12/81', '30040050060', 1000.00, 'lele@ig.com', '1141414141', 'C;/...', 5, 1)

UPDATE tblFuncionario SET nome = 'Julieta Tah', sexo = 'F', dataNasc = '02/10/80', cpf = '12345678910', 
							salario = 1500, email = 'jutah@gmail.com', telefone = '1198765421', foto = 'C:\Users\leandro_000597\Documents\Fotos\semFoto.jpg',
							idCargo = 5, statusFunc = 1 WHERE idFunc = 6


CREATE TABLE tblUSUARIO
(
  idUsuario   VARCHAR2(30) PRIMARY KEY,
  senha       VARCHAR2(30) NOT NULL,
  nivelAcesso NUMBER(1)    NOT NULL,
  idFunc      NUMBER       NOT NULL,
  statusFunc  NUMBER(1)    NOT NULL,

  CONSTRAINT FK_USUARIO_FUNC  FOREIGN KEY (idFunc)
                                      REFERENCES tblFUNCIONARIO(idFunc)
);

