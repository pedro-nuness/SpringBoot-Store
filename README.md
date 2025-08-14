
<div align="center">
  <img src="https://www.clipartmax.com/png/middle/95-950272_search-for-e-commerce-icon-png.png" width="200" alt="SpringBoot-Store Logo">
  <h1>SPRINGBOOT-STORE</h1>
  <p><b>Uma poderosa API de E-commerce construída com Spring Boot.</b></p>

  <p>
    <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
    <img src="https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot" alt="Spring Boot">
    <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white" alt="Maven">
    <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL">
  </p>
</div>

-----

### Tabela de Conteúdo

  - [Visão Geral](https://www.google.com/search?q=%23vis%C3%A3o-geral)
  - [Funcionalidades](https://www.google.com/search?q=%23funcionalidades)
  - [Estrutura do Projeto](https://www.google.com/search?q=%23estrutura-do-projeto)
  - [Como Começar](https://www.google.com/search?q=%23como-come%C3%A7ar)
  - [Licença](https://www.google.com/search?q=%23licen%C3%A7a)
  - [Agradecimentos](https://www.google.com/search?q=%23agradecimentos)

-----

### Visão Geral

`SPRINGBOOT-STORE` é o backend completo para uma plataforma de e-commerce. Desenvolvido com **Spring Boot**, ele oferece uma **API RESTful** segura e de alto desempenho para gerenciar todos os aspectos de uma loja online. Da autenticação de usuários ao gerenciamento de produtos e carrinhos de compras, esta API foi projetada para ser o coração de uma aplicação web moderna.

A arquitetura do projeto é baseada em camadas bem definidas (**Controladores**, **Serviços** e **Repositórios**), o que facilita a manutenção e a escalabilidade. Com dependências como **Spring Security** para segurança, **Spring Data JPA** para persistência de dados e **Flyway** para migrações de banco de dados, você tem uma base sólida para construir seu frontend.

-----

### Funcionalidades

|      | Componente        | Detalhes                                                                                                                                                                                                                         |
| :--- | :---------------- | :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| ⚙️   | **Arquitetura** | Uma **API RESTful** com uma arquitetura em camadas (**Model-View-Controller**) que promove a separação de responsabilidades. O projeto se beneficia da configuração automática do Spring Boot, acelerando o desenvolvimento.          |
| 🛡️   | **Segurança** | Implementa um sistema de autenticação robusto baseado em **JWT** (JSON Web Tokens). Inclui gerenciamento de **refresh tokens**, filtros de segurança e configuração de CORS para comunicação segura com frontends como o Angular.    |
| 🔌   | **Integrações** | Utiliza **Spring Data JPA** para interações com o banco de dados e **Flyway** para gerenciar a evolução do esquema do banco de dados. Integra também um serviço de armazenamento de arquivos para lidar com imagens de produtos.   |
| 🛒   | **E-commerce** | Gerencia o ciclo de vida completo de uma loja: **catálogo de produtos** com variações e tipos, **coleções**, **carrinhos de compras** de usuários e CRUD completo para **usuários e endereços**.                                |
| 📁   | **Estrutura** | O código é organizado em pacotes lógicos (`controller`, `service`, `repository`, `domain`, `dto`, `infra`), o que melhora a modularidade e a clareza do projeto. Exceções personalizadas garantem um tratamento de erros consistente. |
| 🧪   | **Testes** | Inclui testes para garantir que os componentes-chave do Spring Boot e a inicialização do contexto da aplicação funcionem corretamente.                                                                                             |

-----

### Estrutura do Projeto

A organização do projeto segue as convenções padrão do Maven e do Spring Boot, garantindo que seja fácil de navegar e entender.

```
└── SpringBoot-Store/
    ├── mvnw
    ├── mvnw.cmd
    ├── pom.xml                   # Configurações e dependências do Maven
    └── src/
        ├── main/
        │   ├── java/com/hype/application/  # Código-fonte principal
        │   │   ├── controller/      # Gerencia as requisições HTTP
        │   │   ├── domain/          # Entidades de dados (JPA)
        │   │   ├── dto/             # Objetos de Transferência de Dados
        │   │   ├── infra/           # Configurações de segurança e tratamento de erros
        │   │   ├── repository/      # Camada de persistência de dados
        │   │   └── service/         # Lógica de negócio da aplicação
        │   └── resources/
        │       └── db/migration/    # Scripts de migração do Flyway
        └── test/
            └── java/com/hype/application/  # Testes unitários e de integração
```

-----

### Como Começar

Siga estes passos simples para ter o projeto rodando localmente.

#### Pré-requisitos

Certifique-se de que você tem instalado:

  - **Java Development Kit (JDK)**: Versão 17 ou superior.
  - **Apache Maven**: Para gerenciamento de dependências.

#### Instalação

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/meluiz17/SpringBoot-Store.git
    ```
2.  **Entre no diretório do projeto:**
    ```bash
    cd SpringBoot-Store
    ```
3.  **Compile e instale as dependências:**
    ```bash
    mvn clean install
    ```

#### Uso

Para iniciar a aplicação, execute o comando:

```bash
mvn spring-boot:run
```

A API estará rodando por padrão em `http://localhost:8080`.

#### Testes

Para executar os testes do projeto, use o comando:

```bash
mvn test
```

-----

### Licença

Este projeto está sob a licença **MIT**. Para mais detalhes, consulte o arquivo [LICENSE](https://choosealicense.com/licenses/mit/).

-----

### Agradecimentos

  - Agradecimentos a todos os contribuidores e à comunidade Spring Boot por sua incrível documentação e suporte.
  - [Flyway](https://flywaydb.org/) pelo excelente gerenciamento de migrações de banco de dados.
  - [Lombok](https://projectlombok.org/) por reduzir o código boilerplate.
