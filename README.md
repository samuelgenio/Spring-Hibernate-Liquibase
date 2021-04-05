# Spring-Hibernate-Liquibase
Cadastro de Despesas com Spring Mysql e React

### Instruções

* Primeiro é necessário executar container com MYSQL

```
cd database
docker-compose up
```

* Para executar Rest Service

```
cd back
./mvnw package && java -jar target/back-0.0.1.jar
```

acessar url http://localhost:8080/contas

* Para executar Front

```
cd front
npm install
npm start
```

acessar url http://localhost:3000

Você terá acesso a seguinte tela (Poderá informar qualquer e-mail.):

![Tela principal](https://raw.githubusercontent.com/samuelgenio/Spring-Hibernate-Liquibase/main/imgs/img1.PNG)

![Lista de Despesas](https://raw.githubusercontent.com/samuelgenio/Spring-Hibernate-Liquibase/main/imgs/img2.PNG)

![Adicionar Despesas](https://raw.githubusercontent.com/samuelgenio/Spring-Hibernate-Liquibase/main/imgs/img3.PNG)

