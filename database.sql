-- ===========================
-- CRIAR TABELAS
-- ===========================

CREATE DATABASE platform_courses;
USE platform_courses;
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    tipo ENUM('ALUNO','INSTRUTOR','ADMIN') NOT NULL,
    data_cadastro DATE NOT NULL
);

CREATE TABLE curso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    descricao TEXT,
    categoria VARCHAR(80),
    carga_horaria INT,
    instrutor_id INT NOT NULL,
    criado_em DATE,
    FOREIGN KEY (instrutor_id) REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE modulo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    curso_id INT NOT NULL,
    titulo VARCHAR(150),
    ordem INT,
    FOREIGN KEY (curso_id) REFERENCES curso(id) ON DELETE CASCADE
);

CREATE TABLE aula (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modulo_id INT NOT NULL,
    titulo VARCHAR(150),
    tipo ENUM('VIDEO','TEXTO','QUIZ') DEFAULT 'VIDEO',
    conteudo_url VARCHAR(255),
    duracao_min INT,
    ordem INT,
    FOREIGN KEY (modulo_id) REFERENCES modulo(id) ON DELETE CASCADE
);

CREATE TABLE matricula (
    id INT AUTO_INCREMENT PRIMARY KEY,
    aluno_id INT NOT NULL,
    curso_id INT NOT NULL,
    data_matricula DATE,
    progresso_percent INT DEFAULT 0,
    status ENUM('ATIVO','CONCLUIDO','CANCELADO') DEFAULT 'ATIVO',
    nota_final DECIMAL(4,2),
    FOREIGN KEY (aluno_id) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (curso_id) REFERENCES curso(id) ON DELETE CASCADE
);

CREATE TABLE avaliacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    aluno_id INT NOT NULL,
    curso_id INT NOT NULL,
    nota TINYINT NOT NULL CHECK (nota BETWEEN 1 AND 5),
    comentario TEXT,
    data_avaliacao DATE,
    FOREIGN KEY (aluno_id) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (curso_id) REFERENCES curso(id) ON DELETE CASCADE
);

CREATE TABLE atividade (
    id INT AUTO_INCREMENT PRIMARY KEY,
    curso_id INT NOT NULL,
    titulo VARCHAR(150),
    descricao TEXT,
    pontos INT,
    data_entrega DATE,
    FOREIGN KEY (curso_id) REFERENCES curso(id) ON DELETE CASCADE
);

CREATE TABLE submissao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    atividade_id INT NOT NULL,
    aluno_id INT NOT NULL,
    data_submissao DATE,
    nota DECIMAL(5,2),
    feedback TEXT,
    FOREIGN KEY (atividade_id) REFERENCES atividade(id) ON DELETE CASCADE,
    FOREIGN KEY (aluno_id) REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE certificado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    matricula_id INT NOT NULL,
    codigo VARCHAR(50) UNIQUE,
    data_emissao DATE,
    FOREIGN KEY (matricula_id) REFERENCES matricula(id) ON DELETE CASCADE
);

INSERT INTO usuario (nome,email,senha,tipo,data_cadastro) VALUES
('Ana Silva','ana.silva@example.com','senha123','ALUNO','2025-01-05'),
('Bruno Costa','bruno.costa@example.com','senha123','ALUNO','2025-01-07'),
('Carla Lima','carla.lima@example.com','senha123','ALUNO','2025-01-10'),
('Diego Souza','diego.souza@example.com','senha123','ALUNO','2025-01-12'),
('Eduarda Rosa','eduarda.rosa@example.com','senha123','ALUNO','2025-01-15'),
('Felipe Rocha','felipe.rocha@example.com','senha123','ALUNO','2025-01-18'),
('Gabriela Maia','gabriela.maia@example.com','senha123','ALUNO','2025-01-20'),
('Henrique Alves','henrique.alves@example.com','senha123','INSTRUTOR','2024-12-01'),
('Isabela Ferreira','isabela.ferreira@example.com','senha123','INSTRUTOR','2024-12-05'),
('João Pereira','joao.pereira@example.com','senha123','ADMIN','2024-11-10');

INSERT INTO curso (titulo,descricao,categoria,carga_horaria,instrutor_id,criado_em) VALUES
('Java Básico','Introdução ao Java','Programação',30,8,'2025-01-01'),
('Java Avançado','Conceitos avançados em Java','Programação',60,8,'2025-01-02'),
('Banco de Dados MySQL','Modelagem e SQL','Banco de Dados',40,9,'2025-01-03'),
('Desenvolvimento Web','HTML, CSS, JS','Web',45,8,'2025-01-04'),
('Python para Ciência de Dados','Pandas e Numpy','Data Science',50,9,'2025-01-05'),
('Estruturas de Dados','Listas, Pilhas, Filas','Algoritmos',35,8,'2025-01-06'),
('APIs REST com Spring','Desenvolvendo APIs','Programação',40,8,'2025-01-07'),
('Design de UX','Fundamentos de UX','Design',20,9,'2025-01-08'),
('Testes Automatizados','JUnit, mocks','Qualidade',25,8,'2025-01-09'),
('Docker & Containers','Docker básico','DevOps',15,9,'2025-01-10');

INSERT INTO modulo (curso_id,titulo,ordem) VALUES
(1,'Introdução ao Java',1),
(1,'Tipos e Variáveis',2),
(2,'Generics e Collections',1),
(3,'Modelagem Relacional',1),
(4,'Front-end básico',1),
(5,'Introdução ao Python',1),
(6,'Vetores e Listas',1),
(7,'Spring Boot Início',1),
(8,'Princípios de UX',1),
(9,'Fundamentos de Testes',1);

INSERT INTO aula (modulo_id,titulo,tipo,conteudo_url,duracao_min,ordem) VALUES
(1,'Apresentação do curso','VIDEO','http://video.example.com/java1',10,1),
(1,'Instalando Java','TEXTO','http://docs.example.com/install-java',15,2),
(2,'Primitivos','VIDEO','http://video.example.com/java2',20,1),
(3,'ArrayList vs LinkedList','VIDEO','http://video.example.com/collections',25,1),
(4,'HTML5 Básico','VIDEO','http://video.example.com/html',30,1),
(5,'Pandas Series','VIDEO','http://video.example.com/pandas',22,1),
(6,'Implementando Pilha','TEXTO','http://docs.example.com/pilha',18,1),
(7,'Criando projeto Spring','VIDEO','http://video.example.com/spring',28,1),
(8,'Heurísticas de Nielsen','TEXTO','http://docs.example.com/ux',12,1),
(9,'Escrevendo testes com JUnit','VIDEO','http://video.example.com/junit',20,1);


INSERT INTO matricula (aluno_id,curso_id,data_matricula,progresso_percent,status,nota_final) VALUES
(1,1,'2025-02-01',50,'ATIVO',NULL),
(2,1,'2025-02-02',100,'CONCLUIDO',8.50),
(3,2,'2025-02-03',20,'ATIVO',NULL),
(4,3,'2025-02-04',75,'ATIVO',NULL),
(5,4,'2025-02-05',100,'CONCLUIDO',9.20),
(6,5,'2025-02-06',10,'ATIVO',NULL),
(7,6,'2025-02-07',40,'ATIVO',NULL),
(1,3,'2025-02-08',30,'ATIVO',NULL),
(2,4,'2025-02-09',60,'ATIVO',NULL),
(3,1,'2025-02-10',80,'ATIVO',NULL);

INSERT INTO avaliacao (aluno_id,curso_id,nota,comentario,data_avaliacao) VALUES
(2,1,5,'Ótimo curso','2025-03-01'),
(5,4,4,'Bom conteúdo','2025-03-02'),
(1,1,4,'Bastante didático','2025-03-03'),
(3,2,3,'Faltou exemplos','2025-03-04'),
(4,3,5,'Excelente!','2025-03-05'),
(6,5,4,'Conteúdo prático','2025-03-06'),
(7,6,3,'Pode melhorar','2025-03-07'),
(2,4,4,'Boa didática','2025-03-08'),
(1,3,5,'Aprendi muito','2025-03-09'),
(3,1,4,'Recomendado','2025-03-10');

INSERT INTO atividade (curso_id,titulo,descricao,pontos,data_entrega) VALUES
(1,'Trabalho 1','Resolver exercícios básicos',10,'2025-03-15'),
(1,'Quiz 1','Pequeno quiz sobre tipos',5,'2025-03-20'),
(2,'Projeto Final','Entrega obrigatória',50,'2025-04-01'),
(3,'Modelagem','Criar diagrama ER',15,'2025-03-22'),
(4,'Criar página','Fazer página HTML',10,'2025-03-25'),
(5,'Pandas assignment','Limpeza de dados',20,'2025-03-27'),
(6,'Implementar pilha','Código + testes',10,'2025-03-29'),
(7,'API simples','Criar endpoint REST',20,'2025-04-05'),
(8,'Heurísticas de UX','Avaliar interface',10,'2025-03-30'),
(9,'Testes','Escrever testes JUnit',10,'2025-03-31');

INSERT INTO submissao (atividade_id,aluno_id,data_submissao,nota,feedback) VALUES
(1,1,'2025-03-10',8.0,'Boa entrega'),
(1,2,'2025-03-11',9.0,'Ótimo'),
(2,1,'2025-03-21',7.5,'Rever algumas respostas'),
(3,3,'2025-03-28',NULL,'Aguardando correção'),
(4,4,'2025-03-22',9.5,'Excelente'),
(5,5,'2025-03-25',8.5,'OK'),
(6,6,'2025-03-27',NULL,'Aguardando'),
(7,7,'2025-03-29',10.0,'Perfeito'),
(8,2,'2025-04-02',9.0,'Muito bom'),
(9,1,'2025-03-30',8.0,'Bom');

INSERT INTO certificado (matricula_id,codigo,data_emissao) VALUES
(2,'CERT-0001','2025-04-10'),
(5,'CERT-0002','2025-04-11'),
(5,'CERT-0003','2025-04-12'),
(2,'CERT-0004','2025-04-13'),
(10,'CERT-0005','2025-04-14'),
(5,'CERT-0006','2025-04-15'),
(2,'CERT-0007','2025-04-16'),
(1,'CERT-0008','2025-04-17'),
(3,'CERT-0009','2025-04-18'),
(4,'CERT-0010','2025-04-19');



