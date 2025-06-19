# Simple CRUD Users

Um projeto Spring Boot simples que implementa um sistema CRUD (Create, Read, Update, Delete) para gerenciamento de usuários com autenticação JWT.

## 📋 Sobre o Projeto

Este é um sistema de gerenciamento de usuários desenvolvido em Java com Spring Boot que oferece:

- **CRUD completo de usuários** - Criar, listar, atualizar e deletar usuários
- **Sistema de autenticação JWT** - Login seguro com tokens JWT
- **Controle de acesso baseado em roles** - Diferentes níveis de permissão (ADMIN, USER, MODERATOR)
- **API REST documentada** - Endpoints bem estruturados com documentação Swagger/OpenAPI
- **Banco de dados PostgreSQL** - Persistência de dados com migrações Flyway

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Security** - Para autenticação e autorização
- **Spring Data JPA** - Para persistência de dados
- **PostgreSQL** - Banco de dados relacional
- **Flyway** - Para migrações do banco de dados
- **JWT** - Para autenticação stateless
- **Lombok** - Para redução de boilerplate
- **Swagger/OpenAPI** - Para documentação da API
- **Maven** - Gerenciamento de dependências
- **Docker** - Containerização

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas bem definidas:

```
src/main/java/com/gaformario/simple_crud/
├── controller/          # Controladores REST
│   ├── AuthController   # Endpoints de autenticação
│   ├── UserController   # Endpoints de usuários
│   └── dto/            # Data Transfer Objects
├── business/           # Regras de negócio
│   ├── AuthService     # Serviços de autenticação
│   └── UserService     # Serviços de usuário
├── infrastructure/     # Camada de infraestrutura
│   ├── entitys/        # Entidades JPA
│   ├── repository/     # Repositórios de dados
│   └── security/       # Configurações de segurança
├── mapper/            # Mapeamento entre DTOs e entidades
└── config/            # Configurações gerais
```

## 🚀 Funcionalidades

### Autenticação
- **POST** `/auth/login` - Fazer login e obter token JWT
- **POST** `/auth/register` - Registrar novo usuário

### Gerenciamento de Usuários
- **GET** `/users` - Listar todos os usuários
- **GET** `/users/{email}` - Buscar usuário por email
- **GET** `/users/profile` - Buscar informações do usuário autenticado
- **POST** `/users` - Criar novo usuário
- **PUT** `/users/{id}` - Atualizar usuário existente
- **DELETE** `/users/{email}` - Deletar usuário por email

### Roles de Usuário
- **USER** - Usuário padrão
- **MODERATOR** - Usuário com permissões moderadas
- **ADMIN** - Usuário com permissões administrativas

## 🔐 Segurança

- Senhas são criptografadas antes do armazenamento
- Autenticação baseada em JWT tokens
- Controle de acesso baseado em roles
- Endpoints protegidos por Spring Security

---

## ⚙️ Configuração

### Variáveis de Ambiente Necessárias

```bash
DB_URL=url_do_bd_postgres
DB_USERNAME=seu_usuario_postgres
DB_PASSWORD=sua_senha_postgres
JWT_SECRET_KEY=sua_chave_secreta_jwt
JWT_ISSUER=seu_issuer_jwt
```


## 🐳 Docker

O projeto inclui um `Dockerfile` para containerização.

## 📊 Migrações de Banco

O projeto usa Flyway para controle de versão do banco de dados:

- **V1**: Criação da tabela `users`
- **V2**: Adição da coluna `role` com valor padrão 'USER'

## 🔧 Como Executar

1. **Clone o repositório**
2. **Configure as variáveis de ambiente**
3. **Execute o projeto**:
   ```bash
   ./mvnw spring-boot:run
   ```
4. **Documentação da API em**: http://localhost:8081/swagger-ui.html



