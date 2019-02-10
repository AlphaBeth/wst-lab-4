# Web service technologies, Laboratory work #3

## Task description

> Основываясь на информации из раздела 2.8, добавить поддержку обработки
> ошибок в сервис. Возможные ошибки, которые могут происходить при добавлении
> новых записей – например, неверное значение одного из полей, при изменении,
> удалении – попытка изменить или удалить несуществующую запись.
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
- standalone-jaxws -- standalone version of exterminatus service
- jaxws-client -- console client for web service
