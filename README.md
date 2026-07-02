# 🏥 API REST - Clínica Médica SpringBoot

API REST completa de gerenciamento de uma clínica médica desenvolvida com **Spring Boot**. Projeto realizado como parte dos estudos em **Java Spring**.

---

## 📋 Sobre o Projeto

Esta API foi desenvolvida para gerenciar operações essenciais de uma clínica médica, incluindo cadastro de médicos, pacientes e agendamento de consultas. É um projeto educacional que demonstra boas práticas com Spring Boot, segurança com JWT e integração com banco de dados MySQL.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17** - Linguagem de programação
- **Spring Boot 4.0.5** - Framework web
- **Spring Security** - Autenticação e autorização
- **JWT (Java-JWT 4.2.1)** - Autenticação por token
- **Spring Data JPA** - Persistência de dados
- **Hibernate** - ORM para banco de dados
- **MySQL** - Banco de dados relacional
- **Flyway** - Migrações de banco de dados
- **Lombok** - Redução de boilerplate
- **Maven** - Gerenciador de dependências

---

## 📦 Dependências Principais

```xml
- spring-boot-starter-webmvc
- spring-boot-starter-data-jpa
- spring-boot-starter-security
- spring-boot-starter-validation
- spring-boot-starter-flyway
- mysql-connector-j
- java-jwt (4.2.1)
- lombok
```

---

## 🚀 Como Instalar e Executar

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6+
- MySQL 8.0+
- Git

### Passos de Instalação

1. **Clone o repositório**
```bash
git clone https://github.com/GustavoSilva-dev/API-REST-Clinica-Medica-SpringBoot.git
cd API-REST-Clinica-Medica-SpringBoot
```

2. **Configure o banco de dados**
Edite o arquivo `application.properties` ou `application.yml`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/clinica_medica
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
```

3. **Instale as dependências**
```bash
mvn clean install
```

4. **Execute a aplicação**
```bash
mvn spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

---

## 📚 Estrutura do Projeto

```
src/
├── main/
│   ├── java/med/voll/api/
│   │   ├── controller/       # Controladores REST
│   │   ├── domain/           # Entidades e modelos
│   │   ├── repository/       # Interfaces de acesso a dados
│   │   ├── service/          # Lógica de negócio
│   │   ├── dto/              # Data Transfer Objects
│   │   ├── security/         # Autenticação JWT
│   │   └── infra/            # Configurações
│   └── resources/
│       ├── application.properties
│       └── db/migration/     # Scripts Flyway
└── test/                     # Testes unitários e integração
```

---

## 🔐 Autenticação e Segurança

A API utiliza **Spring Security** com autenticação via **JWT (JSON Web Token)**.

### Como Autenticar

1. **Fazer login**
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "medico@clinica.com",
  "password": "senha123"
}
```

2. **Resposta com Token**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer"
}
```

3. **Usar o token nas requisições**
```http
GET /api/medicos
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

---

## 📡 Endpoints Principais

### 👨‍⚕️ Médicos

```http
GET    /api/medicos              # Listar todos os médicos
POST   /api/medicos              # Cadastrar novo médico
GET    /api/medicos/{id}         # Obter médico por ID
PUT    /api/medicos/{id}         # Atualizar médico
DELETE /api/medicos/{id}         # Deletar médico
```

### 👤 Pacientes

```http
GET    /api/pacientes            # Listar todos os pacientes
POST   /api/pacientes            # Cadastrar novo paciente
GET    /api/pacientes/{id}       # Obter paciente por ID
PUT    /api/pacientes/{id}       # Atualizar paciente
DELETE /api/pacientes/{id}       # Deletar paciente
```

### 📅 Consultas

```http
⚠️  EM DESENVOLVIMENTO ⚠️
As funcionalidades de consultas ainda estão sendo implementadas.
Os endpoints para agendamento e gerenciamento de consultas serão adicionados em breve.
```

---

## ✅ Validações Implementadas

- ✔️ Validação de email (formato válido)
- ✔️ Validação de CPF (formato e digitos)
- ✔️ Validação de campos obrigatórios
- ✔️ Validação de tamanho de strings
- ✔️ Verificação de duplicação de dados

---

## 🔄 Migrações com Flyway

O projeto utiliza **Flyway** para versionamento do banco de dados. As migrações estão em:
```
src/main/resources/db/migration/
```

Novos scripts SQL devem seguir o padrão: `V{version}__{description}.sql`

---

## 🧪 Executar Testes

```bash
# Executar todos os testes
mvn test

# Executar teste específico
mvn test -Dtest=NomeDaClasse

# Com cobertura
mvn test jacoco:report
```

---

## 📝 Exemplos de Requisições

### Cadastrar Médico

```http
POST /api/medicos
Content-Type: application/json
Authorization: Bearer {token}

{
  "nome": "Dr. João Silva",
  "email": "joao@clinica.com",
  "crm": "123456",
  "especialidade": "CARDIOLOGIA",
  "telefone": "11999999999"
}
```

### Cadastrar Paciente

```http
POST /api/pacientes
Content-Type: application/json
Authorization: Bearer {token}

{
  "nome": "Maria Santos",
  "email": "maria@email.com",
  "cpf": "12345678900",
  "telefone": "11988888888",
  "endereco": "Rua Principal, 123"
}
```

---

## 🐛 Tratamento de Erros

A API retorna respostas padronizadas com códigos HTTP apropriados:

```json
{
  "timestamp": "2026-05-17T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Email já cadastrado no sistema",
  "path": "/api/medicos"
}
```

---

## 📈 Status do Projeto

- ✅ Autenticação e autorização com JWT
- ✅ CRUD de Médicos
- ✅ CRUD de Pacientes
- ⏳ **CRUD de Consultas (EM DESENVOLVIMENTO)**
- ⏳ Relatórios
- ⏳ Testes automatizados

---

## 🤝 Contribuindo

1. Faça um Fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

---

## 📞 Contato

- **GitHub**: [@GustavoSilva-dev](https://github.com/GustavoSilva-dev)
- **Projeto**: [API-REST-Clinica-Medica-SpringBoot](https://github.com/GustavoSilva-dev/API-REST-Clinica-Medica-SpringBoot)

---

## 📄 Licença

Este projeto é de código aberto e disponível sob a licença MIT.

---

**Desenvolvido com ❤️ por GustavoSilva-dev**
