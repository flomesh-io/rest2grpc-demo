## Build the project:
In the project root folder:

```shell
./mvnw clean install
```

## Run gRPC server:
In the project root folder:
```shell
cd server
../mvnw spring-boot:run
```


## Run gRPC client:
In the project root folder:
```shell
cd client
../mvnw spring-boot:run
```

## Test it:
```shell
❯ curl http://localhost:8888/
Hello: Flomesh%
```
