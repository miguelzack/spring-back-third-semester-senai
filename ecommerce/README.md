# E-commerce API

API REST de e-commerce desenvolvida com Spring Boot. O projeto oferece cadastro e autenticação de usuários, catálogo de produtos e categorias, pedidos, pagamentos, controle de acesso por roles e upload de imagens de produtos.

## Tecnologias utilizadas

- Java 25
- Spring Boot 4
- Spring Web MVC, Spring Data JPA e Spring Security
- JWT
- MySQL
- Maven Wrapper
- Swagger / OpenAPI (Springdoc)

## Requisitos

- Java 25
- MySQL em execução

O Maven Wrapper já está incluído no projeto, portanto não é necessário instalar o Maven separadamente.

## Banco de dados

A configuração local usa:

- Banco: `ecommerce_dev`
- Host: `localhost`
- Porta: `3306`
- Usuário: `root`
- Senha: `root`

Crie o banco antes de iniciar a aplicação:

```sql
CREATE DATABASE ecommerce_dev;
```

A configuração local fica em `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_dev
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
upload.dir=uploads/photos

#spring.sql.init.mode=always
```

O Hibernate cria ou atualiza as tabelas com `ddl-auto=update`; as consultas SQL são exibidas e formatadas no console. A inicialização por `data.sql` fica desabilitada por padrão.

## Como executar

Com o MySQL e o banco criados, execute na pasta `ecommerce`:

Linux/macOS:

```bash
./mvnw spring-boot:run
```

Windows:

```bat
mvnw.cmd spring-boot:run
```

## Documentação da API

Os grupos principais de endpoints são autenticação e usuários, produtos, categorias, pedidos e pagamentos.

| Método | Endpoint | Descrição |
| --- | --- | --- |
| POST | `/usuario/cadastro` | Cadastra um usuário. |
| POST | `/usuario/login` | Autentica e retorna um token JWT. |
| GET | `/usuario/me` | Retorna o usuário autenticado. |
| GET | `/usuario/view` | Lista usuários. |
| GET | `/usuario/view/{id}` | Busca usuário por ID. |
| DELETE | `/usuario/delete/{id}` | Remove usuário. |
| POST | `/produto/cadastro` | Cadastra produto com imagem multipart. |
| GET | `/produto/view` | Lista produtos; aceita `categoriaId` opcional. |
| GET | `/produto/view/{id}` | Busca produto por ID. |
| PUT | `/produto/{id}` | Atualiza produto. |
| DELETE | `/produto/delete/{id}` | Remove produto. |
| GET | `/categoria/view` | Lista categorias. |
| GET | `/categoria/view/{id}` | Busca categoria por ID. |
| POST | `/pedido/cadastro` | Cria pedido para o usuário autenticado. |
| GET | `/pedido/view` | Lista pedidos permitidos ao usuário. |
| GET | `/pedido/view/{id}` | Busca pedido por ID. |
| DELETE | `/pedido/delete/{id}` | Remove pedido. |
| POST | `/pagamento/cadastro` | Registra pagamento. |
| GET | `/pagamento/view` | Lista pagamentos. |

As rotas públicas são o cadastro/login, a consulta de produtos e a consulta de categorias. As demais exigem Bearer Token. As roles existentes são `USER` e `ADMIN`; operações administrativas de produtos, exclusão de usuários e pedidos, e rotas de pagamento são restritas a `ADMIN`, conforme a configuração de segurança.

## Swagger

Com a aplicação em execução, consulte e teste a API em [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html). A interface OpenAPI possui o esquema `bearerAuth`; informe o JWT obtido em `/usuario/login` para testar rotas protegidas.

## Uploads

As imagens enviadas no cadastro de produtos são salvas em `uploads/photos`. A pasta é criada automaticamente quando necessário e os arquivos são servidos em `/uploads/photos/{arquivo}`.

## Estrutura do projeto

```text
src/main/java/com/dm/ecommerce/
├── config          # segurança, OpenAPI e arquivos estáticos
├── controllers     # endpoints HTTP
├── DTOs            # objetos de requisição e resposta
├── entity          # entidades JPA
├── repositories    # acesso a dados
└── service         # regras da aplicação e upload de fotos
```
