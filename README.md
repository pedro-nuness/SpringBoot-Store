# SpringBootStore: Back-end

## Descrição
Este projeto é o back-end de uma aplicação de e-commerce desenvolvido em Java com Spring Boot. Ele fornece uma API RESTful para gerenciamento de produtos, usuários, autenticação, pedidos, categorias, coleções, variações e imagens, além de integração com banco de dados PostgreSQL e autenticação JWT.

## Principais Funcionalidades
- Cadastro, autenticação e gerenciamento de usuários (com roles e refresh tokens)
- Gerenciamento de produtos, categorias, coleções, tipos e variações
- Upload e download de imagens
- Carrinho de compras e pedidos
- Controle de acesso por roles (usuário, admin, master)
- Migração de banco de dados com Flyway
- Configuração de CORS para integração com front-end

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3.4.4
- Spring Data JPA
- Spring Security
- Spring Validation
- Flyway
- PostgreSQL
- JWT (JSON Web Token)
- Lombok
- Maven

## Estrutura do Projeto
```
src/main/java/com/hype/application/
  ├── config/           # Configurações globais (CORS, etc)
  ├── controller/       # Controllers REST (ex: /auth, /product, /user)
  ├── domain/           # Entidades do domínio
  ├── dto/              # Data Transfer Objects
  ├── exception/        # Exceções customizadas
  ├── infra/            # Infraestrutura (segurança, filtros, etc)
  ├── repository/       # Repositórios JPA
  ├── service/          # Serviços de negócio
src/main/resources/
  ├── application.properties # Configurações da aplicação
  └── db/migration/         # Scripts SQL do Flyway
```

## Configuração e Execução
1. **Pré-requisitos:**
   - Java 17+
   - Maven 3.8+
   - PostgreSQL

2. **Configuração do Banco de Dados:**
   - Configure o PostgreSQL e crie um banco chamado `product`.
   - Altere as credenciais em `src/main/resources/application.properties` se necessário:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/product
     spring.datasource.username=postgres
     spring.datasource.password=admin
     ```

3. **Rodando o Projeto:**
   ```shell
   ./mvnw spring-boot:run
   ```
   Ou para buildar:
   ```shell
   ./mvnw clean package
   java -jar target/application-0.0.1-SNAPSHOT.jar
   ```

4. **Migrações:**
   - O Flyway executa automaticamente os scripts em `src/main/resources/db/migration` ao iniciar.

## Endpoints Principais
- **Autenticação:**
  - `POST /auth/register` — Cadastro de usuário
  - `POST /auth/login` — Login e geração de token JWT
- **Produtos:**
  - `GET /product` — Listar produtos
  - `POST /product` — Criar produto (admin)
  - `PUT /product/{id}` — Atualizar produto (admin)
  - `DELETE /product/{id}` — Remover produto (admin)
- **Categorias, Coleções, Tipos, Variações:**
  - CRUD completo via `/category`, `/collection`, `/product_type`, `/variation`
- **Imagens:**
  - `POST /files/upload` — Upload de imagem
  - `GET /files/{filename}` — Download de imagem
- **Carrinho e Pedidos:**
  - `GET /cart` — Visualizar carrinho
  - `POST /cart/add` — Adicionar item ao carrinho
  - `POST /order` — Criar pedido

## Segurança
- Autenticação JWT (Bearer Token)
- Roles: USER, ADMIN, MASTER
- Endpoints protegidos por role conforme `SecurityConfigurations.java`

## Testes
- Testes automatizados com JUnit e Spring Boot Test
- Para rodar os testes:
  ```shell
  ./mvnw test
  ```

## Build e Deploy
- Build com Maven (`./mvnw clean package`)
- O artefato gerado estará em `target/application-0.0.1-SNAPSHOT.jar`
- Pode ser executado em qualquer ambiente com Java 17+

## Observações
- O projeto utiliza Lombok, certifique-se de que seu IDE possui o plugin instalado.
- O CORS está configurado para permitir requisições do front-end em `http://localhost:4200`.
- O diretório de upload de arquivos pode ser alterado em `application.properties`.

## Licença
MIT
