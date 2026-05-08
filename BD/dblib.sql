DROP DATABASE IF EXISTS dblib;
CREATE DATABASE dblib;
USE dblib;

CREATE TABLE Usuario (
    idUsuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    telefone VARCHAR(20), 
    clb VARCHAR(50) UNIQUE NOT NULL,
    idade INT
);

CREATE TABLE Livro (
	idLivro INT PRIMARY KEY AUTO_INCREMENT,
    autor VARCHAR(100),
    titulo VARCHAR(100),
    editora VARCHAR(100),
    dataPublicacao DATE
);

CREATE TABLE Exemplar(
	idExemplar INT PRIMARY KEY AUTO_INCREMENT,
    qntdDisponivel INT,
    fk_idLivro INT,
    FOREIGN KEY (fk_idLivro) REFERENCES Livro(idLivro)
);

CREATE TABLE Emprestimo(
	idEmprestimo INT PRIMARY KEY AUTO_INCREMENT,
    dataEmprestimo DATE,
    dataPrevistaDevolucao DATE, 
    dataDevolucao DATE,
    statusDevolucao BOOL, -- 0 é não devolvido e 1 é devolvido
    fk_idUsuario INT,
    fk_idExemplar INT,
    FOREIGN KEY (fk_idUsuario) REFERENCES Usuario(idUsuario),
    FOREIGN KEY (fk_idExemplar) REFERENCES Exemplar(idExemplar)
);

-- Inserções que foram pedidas no enunciado do desafio
INSERT INTO Usuario (nome, email, telefone, clb, idade) VALUES 
('João Silva', 'joao.silva@email.com', '(11) 99999-8888', 'CLB-12345', 25),
('Maria Oliveira', 'maria.o@email.com', '(11) 97777-6666', 'CLB-67890', 30);

INSERT INTO Livro (titulo, autor, editora, dataPublicacao) VALUES 
('Dom Casmurro', 'Machado de Assis', 'Editora Record', '1899-01-01'),
('O Alquimista', 'Paulo Coelho', 'Editora Rocco', '1988-05-15'),
('1984', 'George Orwell', 'Editora Abril', '1984-01-01');

INSERT INTO Exemplar (qntdDisponivel, fk_idLivro) VALUES 
(3, 1),
(1, 2),
(0, 3);

INSERT INTO Emprestimo (dataEmprestimo, dataPrevistaDevolucao, dataDevolucao, statusDevolucao, fk_idUsuario, fk_idExemplar) VALUES 
('2023-10-01', '2023-10-15', '2023-10-14', 1, 1, 1);

-- Seleções gerais
SELECT * FROM Usuario;
SELECT * FROM Livro;
SELECT * FROM Exemplar;
SELECT * FROM Emprestimo;

-- Seleção pra ver o livro emprestado formatada
SELECT 
    U.nome AS Usuario, 
    L.titulo AS Livro, 
    E.dataEmprestimo, 
    E.dataDevolucao 
FROM Emprestimo E
JOIN Usuario U ON E.fk_idUsuario = U.idUsuario
JOIN Exemplar EX ON E.fk_idExemplar = EX.idExemplar
JOIN Livro L ON EX.fk_idLivro = L.idLivro;

-- Demais seleções
SELECT COUNT(fk_idUsuario) FROM Emprestimo WHERE statusDevolucao = 1 AND fk_idUsuario = 1;
SELECT qntdDisponivel FROM EXEMPLAR WHERE fk_idLivro = 1;

-- Alguns updates
UPDATE Exemplar SET qntdDisponivel = qntdDisponivel - 1 WHERE idExemplar = 1;
UPDATE Emprestimo SET dataDevolucao = '2026-05-02' WHERE idEmprestimo = 2;

-- Alguns deletes
DELETE FROM Usuario WHERE idUsuario = 1;
DELETE FROM Livro WHERE titulo = 'Dom Casmurro';
