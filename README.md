# ForumHub API

## Sobre o Projeto

O **ForumHub** é uma API REST desenvolvida em Java com Spring Boot, criada como projeto de consolidação de conhecimentos nas tecnologias Java e Spring Boot. O objetivo foi implementar um fórum de discussão no nível do back-end, permitindo o gerenciamento completo de tópicos de dúvidas com autenticação e segurança.

A API permite que usuários autenticados criem, listem, atualizem e deletem tópicos de discussão — operação conhecida como **CRUD (Create, Read, Update, Delete)**.

---

## Funcionalidades

- Criação, listagem, atualização e exclusão de tópicos
- Autenticação de usuários via **JWT (JSON Web Token)**
- Rotas protegidas — apenas usuários autenticados acessam os recursos
- Validação de campos de entrada com mensagens de erro claras
- Tratamento de erros padronizado

---

## Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|---|---|---|
| Java | 17+ | Linguagem principal |
| Spring Boot | 4.0.3 | Framework principal |
| Spring Web | - | Criação das rotas REST |
| Spring Data JPA | - | Mapeamento e persistência de dados |
| Spring Security | - | Autenticação e autorização |
| Hibernate | 7.2.4 | ORM para mapeamento de entidades |
| MySQL | 8.0+ | Banco de dados relacional |
| JWT (Auth0) | 4.4.0 | Geração e validação de tokens |
| BCrypt | - | Hash de senhas |
| Validation | - | Validação de campos de entrada |
| Dev Tools | - | Reload automático em desenvolvimento |
| Maven | 4 | Gerenciamento de dependências |

---

## Como criar o projeto com Spring Initializr

Acesse **[https://start.spring.io](https://start.spring.io)** e configure:

| Campo | Valor |
|---|---|
| Project | Maven |
| Language | Java |
| Spring Boot | 4.0.3 |
| Group | com.forumHub |
| Artifact | ForumHub |
| Packaging | Jar |
| Java | 17 |

### Dependências a adicionar:

- Spring Web
- Spring Data JPA
- Spring Security
- MySQL Driver
- Spring Boot DevTools
- Validation

Clique em **Generate** para baixar o projeto e abra no IntelliJ.

> **Dependência adicional** — após criar o projeto, adicione manualmente no `pom.xml` a biblioteca JWT:
> ```xml
> <dependency>
>     <groupId>com.auth0</groupId>
>     <artifactId>java-jwt</artifactId>
>     <version>4.4.0</version>
> </dependency>
> ```

---

## Como instalar e configurar o MySQL

### Instalação no Ubuntu

```bash
# Atualizar pacotes
sudo apt update

# Instalar o MySQL
sudo apt install mysql-server

# Verificar se está rodando
sudo systemctl status mysql

# Configurar segurança
sudo mysql_secure_installation
```

### Criar o banco de dados

```bash
# Acessar o MySQL
sudo mysql

# Criar o banco
CREATE DATABASE forumhub;

# Criar usuário dedicado
CREATE USER 'forumhub'@'localhost' IDENTIFIED BY 'sua-senha';

# Conceder permissões
GRANT ALL PRIVILEGES ON forumhub.* TO 'forumhub'@'localhost';
FLUSH PRIVILEGES;

# Sair
EXIT;
```

### Inserir dados iniciais

```sql
-- Acessar com o usuário criado
mysql -u forumhub -p forumhub

-- Inserir um usuário de teste (senha deve ser hash BCrypt)
INSERT INTO usuarios (nome, email, senha)
VALUES ('Seu Nome', 'seu@email.com', '$2a$10$-SEU_HASH_AQUI');

-- Inserir um curso de teste
INSERT INTO cursos (nome, categoria)
VALUES ('Spring Boot', 'BACKEND');
```

> **Gerar hash BCrypt** — para gerar o hash da senha, rode o seguinte teste no projeto:
> ```java
> @Test
> void gerarHash() {
>     var encoder = new BCryptPasswordEncoder();
>     System.out.println(encoder.encode("sua_senha"));
> }
> ```

---

## Configuração do `application.properties`

```properties
spring.application.name=ForumHub

# Banco de dados
spring.datasource.url=jdbc:mysql://localhost:3306/forumhub
spring.datasource.username=forumhub
spring.datasource.password=sua-senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT
api.security.token.secret=forumhub-secret-key-2026
```

---

## Estrutura do Projeto

```
src/main/java/com/forumHub/ForumHub/
│
├── ForumHubApplication.java
│
├── controller/                         → Rotas da API
│   ├── AutenticacaoController.java     → POST /auth/login
│   └── TopicoController.java           → CRUD /topicos
│
├── domain/                             → Entidades JPA
│   ├── Curso.java
│   ├── Perfil.java
│   ├── Resposta.java
│   ├── StatusTopico.java               → Enum: ABERTO, FECHADO, SOLUCIONADO
│   ├── Topico.java
│   └── Usuario.java
│
├── dto/                                → Objetos de transferência de dados
│   ├── autenticacao/
│   │   ├── LoginRequestDTO.java        → Entrada: email + senha
│   │   └── TokenResponseDTO.java       → Saída: token JWT
│   └── topico/
│       ├── TopicoRequestDTO.java       → Entrada: criar tópico
│       ├── TopicoResponseDTO.java      → Saída: dados do tópico
│       └── TopicoUpdateDTO.java        → Entrada: atualizar tópico
│
├── infra/
│   ├── exception/
│   │   └── TratamentoErros.java        → Respostas de erro padronizadas
│   └── security/
│       ├── SecurityConfig.java         → Configuração do Spring Security
│       ├── SecurityFilter.java         → Filtro de validação do token JWT
│       └── TokenService.java           → Geração e validação do JWT
│
├── repository/                         → Acesso ao banco de dados
│   ├── CursoRepository.java
│   ├── PerfilRepository.java
│   ├── RespostaRepository.java
│   ├── TopicoRepository.java
│   └── UsuarioRepository.java
│
└── service/                            → Regras de negócio
    ├── AutenticacaoService.java        → Carrega usuário para autenticação
    └── TopicoService.java              → Lógica do CRUD de tópicos

src/main/resources/
├── application.properties             → Configurações da aplicação
└── db/migration/                      → Scripts SQL (referência)
    ├── V1__create-table-perfil.sql
    ├── V2__create-table-usuarios.sql
    ├── V3__create-table-cursos.sql
    ├── V4__create-table-topicos.sql
    └── V5__create-table-respostas.sql
```

---

## Fluxo dos Dados

```
                        ┌─────────────┐
                        │   Cliente   │
                        │  (Insomnia) │
                        └──────┬──────┘
                               │ HTTP Request
                               ▼
                    ┌──────────────────────┐
                    │    SecurityFilter    │ ← Valida token JWT
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │     Controller       │ ← Recebe e roteia a requisição
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │      Service         │ ← Aplica regras de negócio
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │     Repository       │ ← Acessa o banco de dados
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │    Banco MySQL       │ ← Persiste os dados
                    └──────────────────────┘
```

---

## Como instalar e usar o Insomnia

### Instalação no Ubuntu

```bash
sudo snap install insomnia
```

Ou acesse **[https://insomnia.rest/download](https://insomnia.rest/download)** e baixe o instalador.

### Como testar a API

#### 1 — Fazer login e obter o token

```
POST http://localhost:8080/auth/login
Body (JSON):
{
    "email": "seu@email.com",
    "senha": "sua_senha"
}
```

Copie o token retornado.

#### 2 — Usar o token nas próximas requisições

Em cada requisição clique em **Auth → Bearer Token** e cole o token.

### Endpoints disponíveis

| Método | Rota | Descrição | Autenticação |
|---|---|---|---|
| POST | /auth/login | Fazer login | Não |
| POST | /topicos | Criar tópico | Sim |
| GET | /topicos | Listar todos os tópicos | Sim |
| GET | /topicos/{id} | Buscar tópico por id | Sim |
| PUT | /topicos/{id} | Atualizar tópico | Sim |
| DELETE | /topicos/{id} | Deletar tópico | Sim |

### Exemplo de requisição — criar tópico

```json
POST http://localhost:8080/topicos
Authorization: Bearer SEU_TOKEN_AQUI

{
    "titulo": "Dúvida sobre Spring Boot",
    "mensagem": "Como funciona a injeção de dependência?",
    "autorId": 1,
    "cursoId": 1
}
```

### Exemplo de resposta

```json
{
    "id": 1,
    "titulo": "Dúvida sobre Spring Boot",
    "mensagem": "Como funciona a injeção de dependência?",
    "dataCriacao": "2025-01-03T19:09:23",
    "status": "ABERTO",
    "nomeAutor": "Arthur",
    "nomeCurso": "Spring Boot"
}
```

---

## Regras de Validação

| Campo | Regra |
|---|---|
| titulo (criar) | Obrigatório, não pode ser vazio |
| mensagem (criar) | Obrigatório, não pode ser vazio |
| autorId | Obrigatório |
| cursoId | Obrigatório |
| titulo (atualizar) | Mínimo 3 caracteres |
| mensagem (atualizar) | Mínimo 10 caracteres |
| email (login) | Obrigatório, formato válido |
| senha (login) | Obrigatório |

---

## Segurança

- Senhas armazenadas com hash **BCrypt** — nunca em texto puro
- Autenticação via **JWT** com expiração de 2 horas
- Todas as rotas exceto `/auth/login` exigem token válido
- Token enviado no header `Authorization: Bearer {token}`
- Banco de dados acessível apenas via `localhost` — sem exposição externa

---

## Autor

Desenvolvido por **Gabriel** como projeto de consolidação de conhecimentos em Java e Spring Boot.
