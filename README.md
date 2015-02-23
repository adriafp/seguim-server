# Seguim-Server

Seguim-Server is a project is about an http-server with a REST framework plus an object database. The purpose of this project is to allow developers to implement a very easy and light API Rest.

  - Seguim-server-core: Is the core of the http-server.
  - Seguim-server-db: Is an object database. Persisted in binary json and can work in memory or persisted in files.
  - Seguim-server-webapp-example: Is a web application (REST API) that uses seguim-server-core and seguim-server-db as example.

## Before start (Requirements)

* Jdk 1.7
* Maven 2 or 3
* Git

## Quick start

#### Clone the project and install the jar in your local maven repository:

```sh
$ git clone https://github.com/adriafp/seguim-server.git
$ cd seguim-server
$ mvn clean install
```

#### Prepare the webapp (REST API) example:

```sh
$ cd seguim-server-webapp-example
$ mvn clean install dependency:copy-dependencies -DoutputDirectory=target/lib
```

#### Start the web server:

```sh
$ cd target
$ java -jar seguim-server-webapp-example-1.0-SNAPSHOT.jar
```