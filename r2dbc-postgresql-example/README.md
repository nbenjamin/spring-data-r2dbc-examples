#### Spring Data R2dbc PostgreSQL Example

##### Dependency required for spring-data-r2dbc
```xml

<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-r2dbc</artifactId>
    <version>${spring-data-r2dbc.version}</version>
  </dependency>
  <dependency>
    <groupId>io.r2dbc</groupId>
    <artifactId>r2dbc-postgresql</artifactId>
    <version>${r2dbc.version}</version>
  </dependency>
```
##### Setup
1. This example use [docker](https://www.docker.com/) in locally to spin up postgreSQL
2. If you have docker installed in your system, please run the following command to start 
[PostgreSQL](https://www.postgresql.org/) and [Adminer](https://www.adminer.org/) (Database management web tool)

  ```properties
  docker-compose -f docker/docker-compose.yml up 
  ```
3. Verify postgreSQL is up and running
```properties
docker ps -a | grep postgres 
```
output
```properties
48414afd73fc  postgres   "docker-entrypoint.s…"   2 days ago  etc....

```
4. Verify [Adminer](https://www.adminer.org/) (Database management web tool) is up and running

```properties
docker ps -a | grep adminer 
```
output
```properties
3b2402e3d8cb        adminer                                 "entrypoint.sh docke…"   2 days ago  
```
5. You can access [Adminer](https://www.adminer.org/) from browser using the following url
```properties
http://localhost:8080/
```

##### Unit test

The unit testcases will be using [TestContainers](https://www.testcontainers.org/) to spin up the lightweight
postgresql container instance.

##### Running the application
Right click and run`R2dbcPostgresqlExampleApplication` from any IDE or you can run 
from command line using the following

```properties
java -jar target/r2dbc-postgresql-example-0.0.1-SNAPSHOT.jar
```

##### Create a new USER

```properties
curl -X POST \
  http://localhost:7878/users \
  -H 'Content-Type: application/json' \
  -d '{
	"name": "demo"
}'
```

##### get all USERS
```properties
curl -X GET http://localhost:7878/users

response:
[{"id":1,"name":"demo"}]%
```
