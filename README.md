# Web service technologies, Laboratory work #2

## Task description

> В данной работе в веб-сервис, разработанный в первой работе, необходимо
> добавить методы для создания, изменения и удаления записей из таблицы.
> 
> Метод создания должен принимать значения полей новой записи, метод
> изменения – идентификатор изменяемой записи, а также новые значения полей, а
> метод удаления – только идентификатор удаляемой записи.
> 
> Метод создания должен возвращать идентификатор новой записи, а методы
> обновления или удаления – статус операции. В данной работе следует вносить
> изменения только в standalone-реализацию сервиса.
> 
> В соответствии с изменениями сервиса необходимо обновить и клиентское
> приложение.

## Requirements

- Java 8
- Maven 3+
- Glassfish 4.0
- Postgresql 9.3+

## Getting started

Start with typing 

`mvn clean install`

in project root directory.

## Project structure

The project consists of some modules:

- data-access -- all database-related code (entity classes, data access objects, utilities for query generation)
- exterminatus-service -- implementation of JAX-WS service
- deployment-jaxws -- packaging and configuration of exterminatus service for deployment onto application server
- standalone-jaxws -- standalone version of exterminatus service
- jaxws-client -- console client for web service
