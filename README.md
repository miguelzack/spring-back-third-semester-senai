# Spring Back Third Semester SENAI

![Java](https://img.shields.io/badge/Java-25-red?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.x-brightgreen?style=for-the-badge&logo=springboot)
![Maven](https://img.shields.io/badge/Maven-Wrapper-blue?style=for-the-badge&logo=apachemaven)
![MySQL](https://img.shields.io/badge/MySQL-Database-orange?style=for-the-badge&logo=mysql)
![Status](https://img.shields.io/badge/Status-Conclu%C3%ADdo-brightgreen?style=for-the-badge)

Repositório com projetos back-end desenvolvidos durante o **3º semestre do curso técnico de Desenvolvimento de Sistemas do SENAI**.

O objetivo deste repositório é reunir atividades práticas concluídas usando **Java**, **Spring Boot**, **Spring Web**, **Spring Data JPA**, **MySQL**, **Validation**, **Lombok**, upload de arquivos e conceitos de autenticação/autorização com **Spring Security** e **JWT**.

---

## Sobre o repositório

Este repositório funciona como uma coleção de APIs REST independentes.  
Cada pasta representa um projeto Spring Boot separado, com seu próprio `pom.xml`, configurações, entidades, DTOs, controllers, services e repositories.

> Este não é um projeto Maven multi-módulo.  
> Para executar uma aplicação, entre na pasta do projeto desejado e rode o Maven Wrapper dentro dela.

---

## Projetos disponíveis

| Projeto | Descrição | Principais recursos |
|---|---|---|
| `biblioteca` | API para gerenciamento de usuários, empréstimos e carteira de biblioteca | CRUD, relacionamento entre entidades, DTOs e MySQL |
| `ecommerce` | API de e-commerce publicada no Render com banco PostgreSQL/Supabase | JWT, Spring Security, categorias, filtro de produtos, pedidos, pagamentos e imagens no Supabase Storage |
| `find-pet-backend` | API simples para cadastro de pets disponíveis para adoção | Cadastro de pet, enums de tipo e porte |
| `foto` | Projeto focado em cadastro de usuário com upload de foto | Multipart file, upload local e persistência no banco |
| `security` | Projeto de estudo sobre autenticação e autorização | Spring Security, JWT, roles e endpoint admin |
| `toDoComplete` | API de lista de tarefas com usuários e tarefas | CRUD, relacionamento 1:N, status de tarefa e MySQL |

### Versões e configuração por projeto

| Projeto | Spring Boot | Banco | Perfil principal |
|---|---:|---|---|
| `biblioteca` | 4.0.3 | `library` | padrão |
| `ecommerce` | 4.0.2 | PostgreSQL/Supabase | `dev`/produção |
| `find-pet-backend` | 4.0.2 | `HELPPET` | padrão |
| `foto` | 4.0.6 | `photo` | padrão |
| `security` | 4.0.5 | `seguranca` (`dev`) | `dev` |
| `toDoComplete` | 4.0.0 | `todolist` | padrão |

Todos os projetos definem Java 25 no `pom.xml`. As versões acima foram conferidas diretamente nos arquivos de build de cada aplicação.

---

## Tecnologias utilizadas

- **Java 25**
- **Spring Boot 4.x**
- **Spring Web / Spring WebMVC**
- **Spring Data JPA**
- **Spring Security**
- **JWT**
- **Hibernate**
- **MySQL**
- **Bean Validation / Jakarta Validation**
- **Lombok**
- **Maven Wrapper**
- **Multipart File Upload**
- **Arquitetura em camadas**
  - Controller
  - Service
  - Repository
  - Entity
  - DTO

---

## Estrutura geral

```bash
spring-back-third-semester-senai/
│
├── biblioteca/
│   └── API de biblioteca
│
├── ecommerce/
│   └── API de e-commerce com autenticação e upload
│
├── find-pet-backend/
│   └── API para cadastro de pets
│
├── foto/
│   └── API de usuário com upload de foto
│
├── security/
│   └── API de autenticação com JWT
│
├── toDoComplete/
│   └── API de lista de tarefas
│
└── .gitignore
```

---

## Pré-requisitos

Antes de executar os projetos, é necessário ter instalado:

- Java 25 ou versão compatível com o `pom.xml`
- MySQL Server
- Git
- IDE recomendada:
  - IntelliJ IDEA
  - VS Code
  - Eclipse ou Spring Tool Suite
- Cliente para testar APIs:
  - Insomnia
  - Postman
  - Thunder Client

> Os projetos usam **Maven Wrapper**, então não é obrigatório instalar Maven globalmente.

---

## Bancos de dados utilizados

Cada projeto usa um banco MySQL próprio.  
Antes de executar, crie o banco correspondente no MySQL.

```sql
CREATE DATABASE library;
CREATE DATABASE ecommerce_dev;
CREATE DATABASE HELPPET;
CREATE DATABASE photo;
CREATE DATABASE seguranca;
CREATE DATABASE todolist;
```

| Projeto | Banco configurado |
|---|---|
| `biblioteca` | `library` |
| `ecommerce` | `ecommerce_dev` |
| `find-pet-backend` | `HELPPET` |
| `foto` | `photo` |
| `security` | `seguranca` |
| `toDoComplete` | `todolist` |

---

## Configuração do banco

Os arquivos de configuração ficam em:

```bash
src/main/resources/application.properties
```

ou, nos projetos que possuem perfis:

```bash
src/main/resources/application-dev.properties
src/main/resources/application-prod.properties
```

Exemplo de configuração:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

`ecommerce` e `security` iniciam com o perfil `dev` por padrão. O `ecommerce` usa `ecommerce_dev` no desenvolvimento e `ecommerceprod` em produção; `security` usa `seguranca` no desenvolvimento e `prod` em produção. Confirme usuário, senha e banco antes de iniciar cada API.

> Em ambiente real, senhas e dados sensíveis não devem ficar diretamente no repositório.  
> O ideal é usar variáveis de ambiente ou arquivos locais ignorados pelo Git.

---

## Como executar um projeto

Entre na pasta do projeto desejado:

```bash
cd ecommerce
```

Execute com o Maven Wrapper:

### Linux/macOS

```bash
./mvnw spring-boot:run
```

### Windows

```bash
mvnw.cmd spring-boot:run
```

A aplicação será iniciada, por padrão, em:

```bash
http://localhost:8080
```

Se quiser rodar mais de um projeto ao mesmo tempo, configure portas diferentes em cada `application.properties`:

```properties
server.port=8081
```

---

# Projeto `biblioteca`

API para gerenciamento de usuários, empréstimos e carteiras de biblioteca.

## Funcionalidades

- Cadastro de usuários
- Listagem de usuários
- Busca de usuário por ID
- Atualização de e-mail do usuário
- Exclusão de usuário
- Cadastro de empréstimos
- Listagem de empréstimos
- Busca de empréstimo por ID
- Cadastro de carteira da biblioteca
- Busca de carteira por ID
- Consulta dos empréstimos de um usuário

## Principais entidades

- `User`
- `Loan`
- `WalletLibrary`

## Endpoints

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/usuario/cadastro` | Cadastra um usuário |
| `GET` | `/usuario/view` | Lista usuários |
| `GET` | `/usuario/view/{id}` | Busca usuário por ID |
| `PUT` | `/usuario/update/email/{id}` | Atualiza usuário |
| `DELETE` | `/usuario/delete/{id}` | Remove usuário |
| `GET` | `/usuario/view/{id}/loans` | Lista empréstimos de um usuário |
| `POST` | `/emprestimo/cadastro` | Cadastra empréstimo |
| `GET` | `/emprestimo/view` | Lista empréstimos |
| `GET` | `/emprestimo/view/{id}` | Busca empréstimo por ID |
| `POST` | `/carteira/cadastro` | Cadastra carteira |
| `GET` | `/carteira/view/{id}` | Busca carteira por ID |

## Exemplo de cadastro de usuário

```json
{
  "name": "Miguel Silva",
  "email": "miguel@email.com"
}
```

## Exemplo de cadastro de empréstimo

```json
{
  "user_id": 1
}
```

---

# Projeto `ecommerce`

API de e-commerce com gerenciamento de usuários, produtos, pedidos, pagamentos, upload de imagens e autenticação JWT.

## Funcionalidades

- Cadastro de usuários
- Login com JWT
- Controle de acesso por roles
- Cadastro de produtos com imagem via `multipart/form-data`
- Armazenamento público das imagens no Supabase Storage
- Listagem pública de categorias cadastradas diretamente no banco
- Listagem de produtos e filtro por categoria
- Busca de produto por ID
- Atualização de produto
- Exclusão de produto
- Cadastro de pedidos
- Listagem de pedidos
- Busca de pedido por ID
- Exclusão de pedido
- Cadastro de pagamento
- Listagem de pagamentos

## Principais entidades

- `Usuario`
- `Produto`
- `Categoria`
- `Pedido`
- `ItemDoPedido`
- `Pagamento`

## Enums

### Role

```java
ADMIN,
USER
```

### StatusDoPedido

```java
AGUARDANDO_PAGAMENTO,
PAGO,
ENVIADO,
ENTREGUE,
CANCELADO
```

## Categorias e filtro

As categorias são mantidas diretamente no banco e não possuem endpoints de criação, alteração ou exclusão. Elas podem ser consultadas pelo painel administrativo e pelos usuários:

```http
GET /categoria/view
GET /categoria/view/{id}
```

Para filtrar produtos por categoria, use o UUID da categoria:

```http
GET /produto/view?categoriaId=UUID_DA_CATEGORIA
```

Sem o parâmetro `categoriaId`, o endpoint retorna todos os produtos.

## Upload de imagens

O cadastro de produtos utiliza `multipart/form-data`. O arquivo é enviado para o Supabase Storage e a API salva a URL pública no produto.

```properties
SUPABASE_URL=https://seu-projeto.supabase.co
SUPABASE_SERVICE_KEY=sua-chave-secreta
SUPABASE_STORAGE_BUCKET=products
```

## Autenticação

O login retorna um token JWT.

Endpoint:

```http
POST /usuario/login
```

Exemplo:

```json
{
  "email": "admin@email.com",
  "senha": "123456"
}
```

Para acessar rotas protegidas, envie o token no cabeçalho:

```http
Authorization: Bearer SEU_TOKEN_AQUI
```

## Endpoints

| Método | Rota | Descrição | Acesso |
|---|---|---|---|
| `POST` | `/usuario/cadastro` | Cadastra usuário | Público |
| `POST` | `/usuario/login` | Realiza login | Público |
| `GET` | `/usuario/me` | Retorna os dados do usuário autenticado | Usuário/Admin |
| `GET` | `/usuario/view/{id}` | Busca usuário por ID | Usuário/Admin |
| `GET` | `/usuario/view` | Lista usuários | Usuário/Admin |
| `DELETE` | `/usuario/delete/{id}` | Remove usuário | Admin |
| `POST` | `/produto/cadastro` | Cadastra produto com imagem e categorias | Admin |
| `GET` | `/produto/view` | Lista produtos | Público |
| `GET` | `/produto/view?categoriaId={id}` | Filtra produtos por categoria | Público |
| `GET` | `/produto/view/{id}` | Busca produto por ID | Público |
| `PUT` | `/produto/{id}` | Atualiza produto | Admin |
| `DELETE` | `/produto/delete/{id}` | Remove produto | Admin |
| `POST` | `/pedido/cadastro` | Cadastra pedido | Usuário/Admin |
| `GET` | `/pedido/view` | Lista pedidos do usuário ou todos para Admin | Usuário/Admin |
| `GET` | `/pedido/view/{id}` | Busca pedido por ID | Usuário/Admin |
| `DELETE` | `/pedido/delete/{id}` | Remove pedido | Admin |
| `POST` | `/pagamento/cadastro` | Cadastra pagamento | Usuário/Admin |
| `GET` | `/pagamento/view` | Lista pagamentos | Admin |

## Cadastro de produto

Envie como `multipart/form-data`, usando um campo de categoria para cada UUID:

| Campo | Tipo | Obrigatório | Descrição |
|---|---|---:|---|
| `nome` | Texto | Sim | Nome do produto |
| `descricao` | Texto | Sim | Descrição |
| `preco` | Número | Sim | Preço |
| `categoriaIds` | UUID | Não | ID de categoria existente; pode ser repetido |
| `imgUrl` | Arquivo | Sim | Imagem do produto |

Exemplo no Postman:

```text
POST /produto/cadastro
Authorization: Bearer SEU_TOKEN_ADMIN
Body: form-data
nome: Produto demonstrativo
descricao: Produto para teste
preco: 99.90
categoriaIds: UUID_DA_CATEGORIA
imgUrl: imagem.png (File)
```

## Exemplo de cadastro de pedido

```json
{
  "cliente_id": "uuid-do-cliente",
  "momento": "2026-05-20",
  "status": "AGUARDANDO_PAGAMENTO",
  "items": [
    {
      "produto_id": "uuid-do-produto",
      "quantidade": 2,
      "preco": 99.90
    }
  ]
}
```

---

# Projeto `find-pet-backend`

API simples para cadastro de pets disponíveis para adoção.

## Funcionalidades

- Cadastro de pet
- Uso de DTO
- Uso de enums para tipo e porte
- Persistência com MySQL

## Principais entidades

- `PetEntity`

## Enums

### Porte

```java
PEQUENO,
MEDIO,
GRANDE
```

### Tipo

```java
CACHORRO,
GATO,
PASSARO,
ROEDOR
```

## Endpoint

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/pet/savePet` | Cadastra um novo pet |

## Exemplo de cadastro de pet

```json
{
  "nome": "Rex",
  "idade": 4,
  "raca": "Labrador",
  "porte": "MEDIO",
  "tipo": "CACHORRO"
}
```

---

# Projeto `foto`

Projeto focado em cadastro de usuários com upload de foto.

## Funcionalidades

- Cadastro de usuário
- Upload de imagem via `MultipartFile`
- Salvamento do caminho da foto no banco de dados
- Armazenamento local dos arquivos enviados

## Principal entidade

- `User`

## Campos principais

- `id`
- `name`
- `email`
- `photo`

## Endpoint

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/users` | Cadastra usuário com foto |

## Exemplo de envio

Este endpoint utiliza `multipart/form-data`.

Campos esperados:

| Campo | Tipo | Descrição |
|---|---|---|
| `name` | Texto | Nome do usuário |
| `email` | Texto | E-mail do usuário |
| `photo` | Arquivo | Foto enviada |

Exemplo no Insomnia/Postman:

```text
POST http://localhost:8080/users

Body: multipart/form-data

name: Miguel Silva
email: miguel@email.com
photo: arquivo.png
```

---

# Projeto `security`

Projeto de estudo sobre autenticação e autorização usando Spring Security e JWT.

## Funcionalidades

- Cadastro de usuário
- Login com geração de token JWT
- Criptografia de senha com BCrypt
- Validação de token por filtro
- Controle de acesso por role
- Endpoint restrito para administrador

## Principais entidades

- `Usuario`

## Enums

### Role

```java
ROLE_ADMIN,
ROLE_USER
```

## Endpoints

| Método | Rota | Descrição | Acesso |
|---|---|---|---|
| `POST` | `/users` | Cadastra usuário | Público |
| `POST` | `/login` | Realiza login e retorna JWT | Público |
| `GET` | `/admin` | Endpoint protegido para admin | Admin |

## Exemplo de cadastro de usuário

```json
{
  "nome": "Miguel Silva",
  "email": "miguel@email.com",
  "senha": "123456"
}
```

## Exemplo de login

```json
{
  "email": "miguel@email.com",
  "senha": "123456"
}
```

Após o login, envie o token nas rotas protegidas:

```http
Authorization: Bearer SEU_TOKEN_AQUI
```

---

# Projeto `toDoComplete`

API REST para gerenciamento de usuários e tarefas.

## Funcionalidades

- Cadastro de usuários
- Login simples de usuário
- Listagem de usuários
- Busca de usuário por ID
- Atualização de usuário
- Exclusão de usuário
- Cadastro de tarefas
- Listagem de tarefas
- Busca de tarefa por ID
- Atualização de tarefa
- Exclusão de tarefa
- Listagem de tarefas por usuário

## Relacionamento

O projeto utiliza relacionamento **1:N**:

- Um usuário pode ter várias tarefas
- Uma tarefa pertence a apenas um usuário

## Principais entidades

- `Usuario`
- `Tarefa`

## Enum Status

```java
A_FAZER,
EM_PROCESSO,
CONCLUIDO
```

## Endpoints de usuário

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/usuario/cadastro` | Cadastra usuário |
| `POST` | `/usuario/login` | Realiza login simples |
| `GET` | `/usuario/view` | Lista usuários |
| `GET` | `/usuario/view/{id}` | Busca usuário por ID |
| `PUT` | `/usuario/{id}` | Atualiza usuário |
| `DELETE` | `/usuario/delete/{id}` | Remove usuário |
| `GET` | `/usuario/viewtasks/{id}` | Lista tarefas de um usuário |

## Endpoints de tarefa

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/tarefa/cadastro` | Cadastra tarefa |
| `GET` | `/tarefa/view` | Lista tarefas |
| `GET` | `/tarefa/view/{id}` | Busca tarefa por ID |
| `PUT` | `/tarefa/update/{id}` | Atualiza tarefa |
| `DELETE` | `/tarefa/delete/{id}` | Remove tarefa |

## Exemplo de cadastro de tarefa

```json
{
  "usuario_id": 1,
  "nome": "Estudar Spring Boot",
  "descricao": "Revisar controllers, services e repositories",
  "status": "A_FAZER",
  "dtInicio": "2026-05-20",
  "dtFim": "2026-05-25"
}
```

---

## Testando as APIs

Você pode testar os endpoints usando:

- Insomnia
- Postman
- Thunder Client
- cURL

Exemplo com cURL:

```bash
curl -X GET http://localhost:8080/produto/view
```

Exemplo com token JWT:

```bash
curl -X GET http://localhost:8080/admin \
  -H "Authorization: Bearer SEU_TOKEN_AQUI"
```

---

## Conceitos praticados

Este repositório reúne práticas importantes de desenvolvimento back-end:

- Criação de APIs REST
- Organização em camadas
- Uso de DTOs
- Validação de dados
- Persistência com JPA/Hibernate
- Relacionamentos entre entidades
- Repositories com Spring Data JPA
- Services para regras de negócio
- Controllers para exposição de endpoints
- Upload de arquivos
- Autenticação com JWT
- Autorização por roles
- Configuração de profiles
- Integração com MySQL

---

## Observações importantes

- Os projetos deste repositório foram concluídos como atividades acadêmicas do 3º semestre.
- Os endpoints documentados refletem a implementação atual; novas evoluções podem ser feitas em versões futuras.
- As configurações de banco estão voltadas para ambiente local.
- Para produção, recomenda-se:
  - Remover senhas fixas dos arquivos `.properties`
  - Usar variáveis de ambiente
  - Configurar profiles corretamente
  - Evitar versionar arquivos gerados como `target/`
  - Evitar versionar configurações locais da IDE, como `.idea/`
  - Evitar versionar arquivos enviados por upload

---

## Autor

Desenvolvido por **Miguel Silva** durante o curso técnico de Desenvolvimento de Sistemas no **SENAI**.

GitHub: [@miguelzack](https://github.com/miguelzack)

---

## Licença

Este repositório ainda não possui uma licença definida.  
Uso destinado a estudos, atividades acadêmicas e evolução técnica.
