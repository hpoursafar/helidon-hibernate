# sms-service-hibernate

Helidon MP application that uses the dbclient API with OracleDB database.

## Build and run


With JDK21
```bash
mvn package
java -jar target/sms-service-hibernate.jar
```

## Exercise the application

Basic:
```
curl -X GET http://localhost:8080/simple-greet
Hello World!
```


JSON:
```
curl -X GET http://localhost:8080/greet
{"message":"Hello World!"}

curl -X GET http://localhost:8080/greet/Joe
{"message":"Hello Joe!"}

curl -X PUT -H "Content-Type: application/json" -d '{"greeting" : "Hola"}' http://localhost:8080/greet/greeting

curl -X GET http://localhost:8080/greet/Jose
{"message":"Hola Jose!"}
```


Tracing:
```
curl -X GET http://localhost:8080/tracing
"Hello World!"

curl -X GET http://localhost:8080/tracing/span
{"Span":"PropagatedSpan{ImmutableSpanContext{traceId=...}}"}

curl -X GET http://localhost:8080/tracing/custom
{
  "Custom Span": "SdkSpan{traceId=..."
}
```



## Try health

```
curl -s -X GET http://localhost:8080/health
{"outcome":"UP",...

```

## Tracing

### Set up Jaeger

First, you need to run the Jaeger tracer. Helidon will communicate with this tracer at runtime.

Run Jaeger within a docker container:
```
docker run -d --name jaeger\
   -e COLLECTOR_ZIPKIN_HOST_PORT=:9411\
   -e COLLECTOR_OTLP_ENABLED=true\
   -p 6831:6831/udp\
   -p 6832:6832/udp\
   -p 5778:5778\
   -p 16686:16686\
   -p 4317:4317\   
   -p 4318:4318\
   -p 14250:14250\
   -p 14268:14268\
   -p 14269:14269\
   -p 9411:9411\
   jaegertracing/all-in-one:1.43
```

### View Tracing Using Jaeger UI

Jaeger provides a web-based UI at http://localhost:16686, where you can see a visual representation of
the same data and the relationship between spans within a trace.


## Building a Native Image

The generation of native binaries requires an installation of GraalVM 22.1.0+.

You can build a native binary using Maven as follows:

```
mvn -Pnative-image install -DskipTests
```

The generation of the executable binary may take a few minutes to complete depending on
your hardware and operating system. When completed, the executable file will be available
under the `target` directory and be named after the artifact ID you have chosen during the
project generation phase.



## Try metrics

```
# Prometheus Format
curl -s -X GET http://localhost:8080/metrics
# TYPE base:gc_g1_young_generation_count gauge
. . .

# JSON Format
curl -H 'Accept: application/json' -X GET http://localhost:8080/metrics
{"base":...
. . .
```



### Database Setup

Start your database before running this example.

Example docker commands to start databases in temporary containers:

Oracle:
```
docker run --rm --name xe -p 1521:1521 -p 8888:8080 wnameless/oracle-xe-11g-r2
```
For details on an Oracle Docker image, see https://github.com/oracle/docker-images/tree/master/OracleDatabase/SingleInstance



## Building the Docker Image

```
docker build -t sms-service-hibernate .
```

## Running the Docker Image

```
docker run --rm -p 8080:8080 sms-service-hibernate:latest
```

Exercise the application as described above.
                                
