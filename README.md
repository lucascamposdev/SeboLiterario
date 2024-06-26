# Sebo Literário (Backend)

# Sobre o projeto

Este projeto foi desenvolvido como parte de um trabalho de extensão universitária que visa impactar a comunidade e comércio local através dos conhecimentos adquiridos em curso. 
O alvo deste projeto foi um sebo literário que utilizava-se de métodos manuais para seu gerenciamento, o que limitava sua eficiência operacional. 
O sistema desenvolvido simplifica significativamente esse processo ao automatizar tarefas e fornecer acesso simplificado às informações essenciais.

# Funcionalidades

#Usuarios
- Cadastro de conta Usuário
- Atualização dos dados de um Usuário
- Deleção de Conta
  
#Lojas
- Cadastro de conta Loja
- Busca por dados de uma Loja
- Busca por nome de Lojas
- Busca por Lojas que entregam em determinada localidade
- Atualização dos dados de uma Loja
- Deleção de Conta

#Produtos
- Cadastro de Produtos
- Atualização dos dados de um Produto
- Deleção de um Produto

# Tecnologias utilizadas
- Java 17
- Spring Boot 3.2.5
- JPA / Hibernate
- Maven
- PostgreSQL

A API poderá ser acessada em http://localhost:8080

# Como executar o projeto

```bash
# clonar repositório
git clone https://github.com/lucascamposdev/FoodDeliveryAPI.git

# executar o projeto (Docker)
docker compose up --build

# executar os testes
mvn test
```

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


