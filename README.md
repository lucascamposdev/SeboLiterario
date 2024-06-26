# Sebo Literário (Backend)

# Sobre o projeto

Este projeto foi desenvolvido como parte de um trabalho de extensão universitária que visa impactar a comunidade e comércio local através dos conhecimentos adquiridos em curso. 
O alvo deste projeto foi um sebo literário que utilizava-se de métodos manuais para seu gerenciamento, o que limitava sua eficiência operacional. 
O sistema desenvolvido simplifica significativamente esse processo ao automatizar tarefas e fornecer acesso simplificado às informações essenciais.

# Diagrama Entidade Relacionamento

![Imagem](./public/diagramaer.png)

# Modelagem das Tabelas
  <details>
	  <summary> Expandir</summary>

   
  ## Livros

  ```
	CREATE TABLE livro (
	    id SERIAL PRIMARY KEY,
	    titulo VARCHAR(255) NOT NULL,
	    preco NUMERIC(10, 2) NOT NULL,
	    quantidade INTEGER NOT NULL
	);		
 ```

  ### Transações

  ```
	CREATE TABLE transacao (
	    id SERIAL PRIMARY KEY,
	    tipo VARCHAR(50) NOT NULL,
	    data TIMESTAMP NOT NULL,
	    livro_id INTEGER NOT NULL,
	    valor NUMERIC(10, 2) NOT NULL,
	    CONSTRAINT fk_livro FOREIGN KEY (livro_id) REFERENCES livro (id),
	);
```
</details>

# Diagrama de Atividades

![Imagem](./public/diagramaativ.png)

# Tecnologias utilizadas
- Java 17
- Spring Boot 3.2.5
- JPA / Hibernate
- Maven
- PostgreSQL

## API Endpoints

- Adicionar Livro
```
POST /api/v1/livros

{
	"titulo": "Sherlock Holmes",
	"quantidade": 1,
	"preco": 5.59
}
```

<hr>

- Vender Livro
```
PUT /api/v1/livros/:id
```

<hr>

- Trocar Livros
```
POST /api/v1/livros/trade

{
	"livroRecebido": {
		"titulo": "Novo Livro",
		"preco": 10.20,
		"quantidade": 1
	},
	"livroTrocadoId": 1
}
```

<hr>

- Buscar todos os livros
```
GET /api/v1/livros
```

<hr>

- Buscar informações de um livro específico
```
GET /api/v1/livros/:id
```

<hr>

- Busca por livros baseado no titulo
```
GET api/v1/livros/search?titulo=abc
```

<hr>

# Autor

Lucas Campos

https://www.linkedin.com/in/lucascamposdev/


