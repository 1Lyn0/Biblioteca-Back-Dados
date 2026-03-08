
CREATE DATABASE db_biblioteca;

USE db_biblioteca;

CREATE TABLE `aluno` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `telefone` varchar(11) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `cep` varchar(8) NOT NULL,
  `estado` varchar(45) NOT NULL,
  `cidade` varchar(45) NOT NULL,
  `endereco` varchar(100) NOT NULL,
  `bairro` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `autor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `autor` varchar(100) NOT NULL,
  `pseudonimo` varchar(45) DEFAULT NULL,
  `nacionalidade` varchar(45) DEFAULT NULL,
  `endereco_web` varchar(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telefone` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `editora` (
  `id` int NOT NULL AUTO_INCREMENT,
  `editora` varchar(45) NOT NULL,
  `cnpj` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `telefone` varchar(14) NOT NULL,
  `cep` varchar(45) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `endereco` varchar(100) DEFAULT NULL,
  `nacionalidade` varchar(45) DEFAULT NULL,
  `endereco_web` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `categoria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `categoria` varchar(50) NOT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `livro` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_categoria` int NOT NULL,
  `id_editora` int NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `ano_publicacao` int DEFAULT NULL,
  `isbn` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_livro_categoria1_idx` (`id_categoria`),
  KEY `fk_livro_editora1_idx` (`id_editora`),
  CONSTRAINT `fk_livro_categoria1` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`),
  CONSTRAINT `fk_livro_editora1` FOREIGN KEY (`id_editora`) REFERENCES `editora` (`id`)
);

CREATE TABLE `livro_autor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_livro` int NOT NULL,
  `id_autor` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_livro_autor` (`id_livro`,`id_autor`),
  KEY `fk_livro_has_autor_autor1_idx` (`id_autor`),
  KEY `fk_livro_has_autor_livro1_idx` (`id_livro`),
  CONSTRAINT `fk_livro_has_autor_autor1` FOREIGN KEY (`id_autor`) REFERENCES `autor` (`id`),
  CONSTRAINT `fk_livro_has_autor_livro1` FOREIGN KEY (`id_livro`) REFERENCES `livro` (`id`) ON DELETE CASCADE
);

CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `usuario` varchar(100) NOT NULL,
  `senha` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `emprestimo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  `id_aluno` int NOT NULL,
  `data_emprestimo` date NOT NULL,
  `data_devolucao_prevista` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_emprestimo_aluno1_idx` (`id_aluno`),
  KEY `fk_emprestimo_usuario1_idx` (`id_usuario`),
  CONSTRAINT `fk_emprestimo_aluno1` FOREIGN KEY (`id_aluno`) REFERENCES `aluno` (`id`),
  CONSTRAINT `fk_emprestimo_usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
);

CREATE TABLE `emprestimo_livro` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_emprestimo` int NOT NULL,
  `id_livro` int NOT NULL,
  `data_devolucao` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fk_emprestimo_livro_unique` (`id_emprestimo`,`id_livro`),
  KEY `fk_emprestimo_has_livro_livro1_idx` (`id_livro`),
  KEY `fk_emprestimo_has_livro_emprestimo1_idx` (`id_emprestimo`),
  CONSTRAINT `fk_emprestimo_has_livro_emprestimo1` FOREIGN KEY (`id_emprestimo`) REFERENCES `emprestimo` (`id`),
  CONSTRAINT `fk_emprestimo_has_livro_livro1` FOREIGN KEY (`id_livro`) REFERENCES `livro` (`id`)
);

CREATE TABLE `parametro` (
  `id` int NOT NULL AUTO_INCREMENT,
  `parametro` tinytext NOT NULL,
  `valor` float NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `multa` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_emprestimo_livro` int NOT NULL,
  `valor` float NOT NULL,
  `data_pagamento` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_emprestimo_livro_UNIQUE` (`id_emprestimo_livro`),
  KEY `fk_multa_emprestimo1_idx` (`id_emprestimo_livro`),
  CONSTRAINT `fk_multa_emprestimo_livro` FOREIGN KEY (`id_emprestimo_livro`) REFERENCES `emprestimo_livro` (`id`)
);