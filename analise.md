## Projeto: Plataforma de Cursos
---

### 1. Introdução

Este relatório apresenta uma análise do sistema "Plataforma de Cursos", cujo objetivo é descrever o contexto geral, a arquitetura e as principais entidades que compõem o seu modelo de dados. A análise foi conduzida a partir da inspeção do código-fonte Java e do script de criação do banco de dados (`database.sql`), buscando fornecer uma visão clara da estrutura e das funcionalidades do sistema.

### 2. Arquitetura e Tecnologias

O sistema é uma aplicação de desktop desenvolvida na plataforma Java, utilizando a biblioteca **Swing** para a construção da interface gráfica. A arquitetura do projeto adota um padrão semelhante ao MVC (Model-View-Controller), com uma separação clara de responsabilidades:

- **Modelo (`pct.example.model`):** Contém as classes de domínio que representam as entidades de negócio do sistema.
- **Visão (`pct.example.view`):** Composta por classes que constroem e gerenciam as telas da aplicação.
- **Acesso a Dados (`pct.example.dao`):** Implementa o padrão *Data Access Object (DAO)*, que abstrai e centraliza o acesso ao banco de dados, separando a lógica de negócio das operações de persistência.

A persistência dos dados é realizada em um banco de dados relacional, e a comunicação é gerenciada pela classe `ConnectionFactory`, que provavelmente utiliza JDBC (Java Database Connectivity).

### 3. Modelo de Dados e Entidades Principais

O domínio do sistema é estruturado em torno de um conjunto de entidades inter-relacionadas que representam os conceitos fundamentais de uma plataforma de ensino.

#### 3.1. Entidade: Usuário
A entidade `Usuario` é a base para todos os atores do sistema.

- **Atributos:** `id` (identificador único), `nome`, `email`, `senha`, e `tipo`, que define o perfil do usuário (`ALUNO`, `INSTRUTOR`, `ADMIN`).
- **Descrição:** Representa qualquer pessoa que interaja com o sistema, cujas permissões são determinadas pelo seu tipo.

#### 3.2. Entidade: Curso
Representa a oferta educacional principal da plataforma.

- **Atributos:** `id`, `titulo`, `descricao`, `categoria`, `carga_horaria`.
- **Relacionamentos:** Cada curso é associado a um `Usuario` do tipo `INSTRUTOR`. Um curso contém uma coleção de `Módulos`.

#### 3.3. Entidade: Módulo
Organiza o conteúdo de um `Curso` em seções lógicas.

- **Atributos:** `id`, `titulo`, `ordem`.
- **Relacionamentos:** Um `Módulo` está obrigatoriamente vinculado a um `Curso`.

#### 3.4. Entidade: Aula
Constitui a unidade elementar de ensino dentro de um `Módulo`.

- **Atributos:** `id`, `titulo`, `tipo` ('VIDEO', 'TEXTO', 'QUIZ'), `conteudo_url` (link para o material), `duracao_min`.
- **Relacionamentos:** Uma `Aula` pertence a um único `Módulo`.

#### 3.5. Entidade: Matrícula
Formaliza a relação de aprendizado entre um aluno e um curso.

- **Atributos:** `id`, `data_matricula`, `progresso_percent`, `status`.
- **Relacionamentos:** Funciona como uma tabela de associação entre as entidades `Usuario` (com perfil `ALUNO`) e `Curso`.

#### 3.6. Entidades Complementares
O sistema inclui outras entidades que suportam o processo de aprendizagem e avaliação:
- **Avaliacao:** Permite que alunos forneçam feedback sobre os cursos.
- **Atividade e Submissao:** Representam tarefas e suas respectivas entregas pelos alunos.
- **Certificado:** Gerado para o aluno após a conclusão bem-sucedida de um curso.

### 4. Conclusão

A análise do projeto "Plataforma de Cursos" identifica um sistema desktop Java Swing com arquitetura em camadas e persistência em banco de dados relacional. O modelo de dados suporta a gestão de usuários com perfis distintos, cursos, módulos, aulas, matrículas, avaliações, atividades e certificações. A estrutura permite a administração e consumo de conteúdo educacional online.