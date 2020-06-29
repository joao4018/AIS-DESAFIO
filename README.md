# AIS DESAFIO API-MOVIE

A API-MOVIE é uma API para cadastro de filmes criado utilizando Spring Boot e documentação feita em Swagger e como banco de dados optei por H2 por ser feito em memória facilita a execução do projeto.


Forma de Uso:
 
 Para Buildar o projeto:
  ```
    mvn clean install
  ```
  
  O projeto está baseado em super POM.xml basta o clean install na pasta geral, porém caso venha sofrer algum erro execute o processo para cada módulo do projeto.
  
  Para Rodar o projeto:
  
  Cada Módulo necessita de executado siga a ordem a baixo:
  
  1 - Discovery
  
    ```
    mvn spring-boot:run
    ```
    
  2 - Gateway
  
    ```
    mvn spring-boot:run
    ```
    
  3 - Auth e Movie
  
    ```
    mvn spring-boot:run
    ```
    
  Serviço de Encrypter utilizei para me ajudar a encriptar as senhas de uma forma off do projeto.
  Serviço de Token está sendo utilizado para encriptar o token.
  O core está separado apenas por boas práticas.
  
  Ao executar os testes da aplicação se atentar aos testes não mockados, precisam de uma atenção tanto para ter determinados dados no banco como a aplicação em execução.


Passo a Passo para utilizar o software
1 - Cadastre o usuário por esse link: http://localhost:8080/gateway/auth/swagger-ui.html#/user-info-controller
2 - Pegue o token por meio de algum aplicativo como o postman utilizando essa url no modo POST: http://localhost:8080/gateway/auth/login

utiliza um body semelhante a esse:
{
  "username": "joao",
  "password": "123"
}

3 - com o token em mãos, utilize o link a baixo para utilizar o crud do MOVIE-API:http://localhost:8080/gateway/movie/swagger-ui.html#/movie-controller/listUsingGET




