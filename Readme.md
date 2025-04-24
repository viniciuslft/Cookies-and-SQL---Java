# Testes com Cookies, Carrinho de Compras e CRUD em Java

Este projeto tem como objetivo implementar funcionalidades básicas de um **sistema de carrinho de compras** com **testes de cookies** e **sessões**, além de realizar operações de **CRUD** simples (Criar, Ler, Atualizar e Deletar) em um banco de dados, utilizando **Java**, **JSP**, **Apache Tomcat** e **Jakarta**.

## Funcionalidades

- **Carrinho de Compras**: Simulação de um carrinho de compras onde os usuários podem adicionar, remover e visualizar itens.
- **Cookies e Sessões**: Armazenamento e manipulação de dados de sessão utilizando cookies, permitindo que o carrinho de compras persista enquanto o usuário navega.
- **CRUD Básico**: Operações básicas de **Cadastro**, **Leitura**, **Atualização** e **Exclusão** de produtos no banco de dados.
- **Conexão com Banco de Dados**: Integração com banco de dados para armazenamento dos dados dos produtos e usuários.

## Tecnologias Utilizadas

### Linguagens:
- <a href="https://www.java.com/" target="_blank"><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" height="30" alt="Java logo" /></a>
  <img width="12" />
- <a href="https://www.mysql.com/" target="_blank"><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original-wordmark.svg" height="30" alt="MySQL logo" /></a>
  <img width="12" />

### Servidor e Frameworks:
- <a href="https://tomcat.apache.org/" target="_blank"><img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/tomcat/tomcat-original-wordmark.svg" height="30" alt="Apache Tomcat logo" /></a>
  <img width="12" />
  
### Bancos de Dados:
- <a href="https://www.mysql.com/" target="_blank"><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original-wordmark.svg" height="30" alt="MySQL logo" /></a>
  <img width="12" />

## Funcionalidades Implementadas

- **Carrinho de Compras**: O carrinho permite que o usuário adicione e remova produtos, com informações armazenadas na sessão e nos cookies.
  
- **Gerenciamento de Sessão e Cookies**: O sistema usa sessões para manter o estado do carrinho durante a navegação e cookies para persistir dados entre diferentes sessões do usuário.

- **Operações CRUD**: 
  - **Cadastrar Produtos**: Adiciona produtos ao banco de dados.
  - **Visualizar Produtos**: Lista todos os produtos cadastrados.
  - **Atualizar Produtos**: Permite a edição de informações de produtos.
  - **Excluir Produtos**: Permite a remoção de produtos do banco de dados.

## Como Rodar o Projeto

### Pré-Requisitos:

- Java 11 ou superior.
- Apache Tomcat 9.x ou superior.
- Banco de Dados MySQL (ou outro banco de sua escolha configurado corretamente).

### Passos:

1. Clone este repositório:
   ```bash
   git clone https://github.com/viniciuslft/Cookies-and-SQL-Java.git
