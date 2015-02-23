# Seguim-Server

Seguim-Server is a project is about an http-server with a REST framework plus an object database. The purpose of this project is to allow developers to implement a very easy and light API Rest.

  - Seguim-server-core: Is the core of the http-server.
  - Seguim-server-db: Is an object database. Persisted in binary json and can work in memory or persisted in files.
  - Seguim-server-webapp-example: Is a web application (REST API) that uses seguim-server-core and seguim-server-db as example.

##Quick start
```sh
$ git clone https://github.com/adriafp/seguim-server.git
$ cd seguim-server
$ mvn clean install
```
