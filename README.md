# Insperscore - Sistema de Gerenciamento de Futebol

API REST desenvolvida com Spring Boot para gerenciar informações sobre times, jogadores, estádios, campeonatos e partidas de futebol.

## 🎯 Início Rápido

```cmd
# Clone e entre no diretório
git clone <url-do-repositorio>
cd Projeto-aqr-obj1

# Execute a aplicação
mvnw.cmd spring-boot:run

# Acesse o Swagger UI
http://localhost:8080/swagger-ui.html
```

## 🛠️ Stack Tecnológica

![Java](https://img.shields.io/badge/Java-21-orange?style=flat&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen?style=flat&logo=spring)
![H2](https://img.shields.io/badge/H2-Database-blue?style=flat)
![Maven](https://img.shields.io/badge/Maven-3.6+-red?style=flat&logo=apache-maven)

## 📋 Pré-requisitos

Antes de executar a aplicação, certifique-se de ter instalado:

- **Java 21** ou superior ([Download JDK](https://www.oracle.com/java/technologies/downloads/))
- **Maven 3.6+** (ou use o Maven Wrapper incluído no projeto)
- **Git** (para clonar o repositório)

## 🚀 Como Executar a Aplicação


### Opção 1: Pela IDE (IntelliJ IDEA / Eclipse)

1. Importe o projeto como um projeto Maven
2. Aguarde o download das dependências
3. Execute a classe `InsperscoreApplication.java` (método `main`)

## 🌐 Acessando a Aplicação

Após iniciar a aplicação, ela estará disponível em:

- **URL Base da API:** `http://localhost:8080`
- **Swagger UI (Documentação Interativa):** `http://localhost:8080/swagger-ui.html`
- **OpenAPI Docs (JSON):** `http://localhost:8080/api-docs`
- **Console H2 Database:** `http://localhost:8080/h2-console`

## 📚 Endpoints da API

### Autenticação
- `POST /api/auth/register` - Registrar novo usuário
- `POST /api/auth/login` - Fazer login

### Times
- `GET /api/times` - Listar todos os times
- `GET /api/times/{id}` - Buscar time por ID
- `POST /api/times` - Criar novo time
- `PUT /api/times/{id}` - Atualizar time
- `DELETE /api/times/{id}` - Deletar time

### Jogadores
- `GET /api/jogadores` - Listar todos os jogadores
- `GET /api/jogadores/{id}` - Buscar jogador por ID
- `POST /api/jogadores` - Criar novo jogador
- `PUT /api/jogadores/{id}` - Atualizar jogador
- `DELETE /api/jogadores/{id}` - Deletar jogador

### Estádios
- `GET /api/estadios` - Listar todos os estádios
- `GET /api/estadios/{id}` - Buscar estádio por ID
- `POST /api/estadios` - Criar novo estádio
- `PUT /api/estadios/{id}` - Atualizar estádio
- `DELETE /api/estadios/{id}` - Deletar estádio

### Campeonatos
- `GET /api/campeonatos` - Listar todos os campeonatos
- `GET /api/campeonatos/{id}` - Buscar campeonato por ID
- `POST /api/campeonatos` - Criar novo campeonato
- `PUT /api/campeonatos/{id}` - Atualizar campeonato
- `DELETE /api/campeonatos/{id}` - Deletar campeonato

### Partidas
- `GET /api/partidas` - Listar todas as partidas
- `GET /api/partidas/{id}` - Buscar partida por ID
- `POST /api/partidas` - Criar nova partida
- `PUT /api/partidas/{id}` - Atualizar partida
- `DELETE /api/partidas/{id}` - Deletar partida

## 🗄️ Banco de Dados

A aplicação utiliza **H2 Database** (banco em memória) para desenvolvimento e testes.

### Configurações do H2:
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** *(deixe em branco)*
- **Driver:** `org.h2.Driver`

### Console H2:
Acesse `http://localhost:8080/h2-console` e use as credenciais acima para visualizar o banco de dados.

## ⚙️ Configurações

As configurações da aplicação estão em `src/main/resources/application.properties`:

```properties
spring.application.name=insperscore
spring.jpa.hibernate.ddl-auto=update
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.enabled=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.show-sql=true
```

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **Spring Security** + **JWT**
- **H2 Database**
- **Maven**
- **SpringDoc OpenAPI (Swagger)**
- **BCrypt** (para hash de senhas)

## 📝 Estrutura do Projeto

```
src/main/java/com/pelo/insperscore/
├── autenticacao/          # Autenticação e segurança (JWT, usuarios)
├── campeonatos/           # Gerenciamento de campeonatos
├── config/                # Configurações do Spring Security
├── estadios/              # Gerenciamento de estádios
├── jogadores/             # Gerenciamento de jogadores
├── partidas/              # Gerenciamento de partidas
├── times/                 # Gerenciamento de times
└── InsperscoreApplication.java  # Classe principal
```

## 🔧 Desenvolvimento

### Executar Testes

```cmd
mvnw.cmd test
```

### Limpar Build

```cmd
mvnw.cmd clean
```

### Gerar JAR sem executar testes

```cmd
mvnw.cmd clean package -DskipTests
```


## 🐛 Troubleshooting

### Porta 8080 já em uso
Se a porta 8080 já estiver em uso, você pode alterar em `application.properties`:
```properties
server.port=8081
```

### Erro de compilação
Certifique-se de estar usando Java 21:
```cmd
java -version
```

### Erro ao baixar dependências
Execute com limpeza do cache Maven:
```cmd
mvnw.cmd clean install -U
```

## 👥 Contribuidores

Desenvolvido como projeto acadêmico para Arquitetura de Software.

Nicolas Volf, Leonardo Souza, Arthur Belei, Emanuel Apolinario, Mariana Goes

