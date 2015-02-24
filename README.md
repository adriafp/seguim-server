# Seguim-Server

Seguim-Server is a project is about an http-server with a REST framework plus an object database. The purpose of this project is to allow developers to implement a very easy and light API Rest.

  - Seguim-server-core: Is the core of the http-server.
  - Seguim-server-db: Is an object database. Persisted in binary json and can work in memory or persisted in files.
  - Seguim-server-webapp-example: Is a web application (REST API) that uses seguim-server-core and seguim-server-db as example.

## Before start (Requirements)

* Jdk 1.7
* Maven 2 or 3
* Git
* curl (for testing)

## Quick start

#### Clone the project and install the jar in your local maven repository:

```sh
$ git clone https://github.com/adriafp/seguim-server.git
$ cd seguim-server
$ mvn clean install
```

#### Start the web server:

```sh
$ cd seguim-server-webapp-example/target/classes
```

Linux

```sh
$ sh startup.sh 
```

Windows

```sh
$ startup.bat 
```


#### Testing the API REST
##### GET /index:

```sh
$ curl -i -H "Accept: application/json" http://localhost:8000/index
```

Result:

```
HTTP/1.1 200 OK
Content-length: 18
Date: Tue, 24 Feb 2015 10:49:41 GMT
{"Hello":"World!"}
```

##### POST /user:

```sh
$ curl -H "Content-Type: application/json" -d '{"name":"adria","surname":"febrer","email":"adria@seguim.com"}' http://localhost:8000/user
```

Result:

```
{"success":"true","user":{"id":2,"name":"adria","surname":"febrer","email":"adria@seguim.com"}}
```

##### GET /user/{id}:

```sh
$ curl -i -H "Accept: application/json" http://localhost:8000/user/1
```

Result

```
HTTP/1.1 200 OK
Content-length: 78
Date: Tue, 24 Feb 2015 10:52:44 GMT
{"user":{"id":0,"name":"adria","surname":"febrer","email":"adria@seguim.com"}}
```