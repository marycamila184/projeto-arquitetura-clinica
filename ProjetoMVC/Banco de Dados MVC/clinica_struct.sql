-- Gerado por Oracle SQL Developer Data Modeler 4.1.5.907
--   em:        2018-03-05 11:08:15 BRT
--   site:      Oracle Database 11g
--   tipo:      Oracle Database 11g


DROP TABLE IF EXISTS ANIMAL ;
DROP TABLE IF EXISTS ESPECIE ;
DROP TABLE IF EXISTS TIPO_ANIMAL ;


CREATE TABLE ANIMAL
  (
    ID             BIGINT IDENTITY NOT NULL ,
    NOME           VARCHAR (50) NOT NULL ,
    NASCIMENTO     DATE ,
    ESPECIE_ID BIGINT NOT NULL ,
    constraint ANIMAL_PK primary key (ID)
  ) ;

CREATE TABLE TIPO_ANIMAL
  (
    ACRONIMO  CHAR (3) NOT NULL ,
    NOME      VARCHAR (100) NOT NULL ,
    DESCRICAO VARCHAR (255),
    CONSTRAINT TIPO_ANIMAL_PK PRIMARY KEY (ACRONIMO)
  ) ;

  CREATE TABLE ESPECIE
  (
    ID                   BIGINT IDENTITY NOT NULL ,
    NOME                 VARCHAR (50) NOT NULL ,
    DESCRICAO            VARCHAR (100) ,
    TIPO_ANIMAL_ACRONIMO CHAR (3) NOT NULL,
    CONSTRAINT ESPECIE_PK PRIMARY KEY (ID)
  ) ;

-- RODAR SOMENTE DEPOIS DE CRIAR AS TABELAS

ALTER TABLE TIPO_ANIMAL ADD CONSTRAINT TIPO_ANIMAL_UN UNIQUE ( NOME ) ;

ALTER TABLE ESPECIE ADD CONSTRAINT ESPECIE_NOME UNIQUE ( NOME ) ;

ALTER TABLE ANIMAL ADD CONSTRAINT ANIMAL_ESPECIE_FK FOREIGN KEY ( ESPECIE_ID ) REFERENCES ESPECIE ( ID ) ;

ALTER TABLE ESPECIE ADD CONSTRAINT ESPECIE_TIPO_ANIMAL_FK FOREIGN KEY ( TIPO_ANIMAL_ACRONIMO ) REFERENCES TIPO_ANIMAL ( ACRONIMO ) ;