	 	 	 	  
Trabalho de Banco de Dados \- Modelo Relacional

PLATAFORMA DE CURSOS ONLINE

USUÁRIO (ID, nome, email, tipo, senha, data\_cadastro)

CURSO (ID, titulo, descrição, categoria, carga\_horaria, criado\_em, id\_instrutor)

MODULO (ID, id\_curso, titulo, ordem)

AULA (ID, id\_modulo, titulo, tipo, url\_conteudo, duracao, ordem)

ATIVIDADE (ID, id\_curso, titulo, descrição, pontos, data\_entrega)

SUBMISSÃO (ID, id\_atividade, id\_aluno, data\_submissão, nota, feedback)

AVALIACAO (ID, id\_aluno, id\_curso, nota, comentario, data\_avaliacao)

MATRICULA (ID, id\_aluno, id\_curso, data\_matricula, progresso\_percent, status, nota\_final)

CERTIFICADO (ID, id\_matricula, codigo, data\_emissao)  
