# Spring Cloud Microservice POC 

## Eureka and Spring-Config 

The branch `eureka-spring-config` uses Netflix Eureka as DNS and for Service Discovery and Spring Config for application configuration.

### Config Server

Implementation of the spring cloud configuration service.  
Loads the configuration from a git repository and provides this data as HTTP API.  

    config-service$ mvn spring-boot:run

open browser at http://localhost:8888/movie-service/default

### Discovery Server

Uses Netflix Eureka as DNS for service discovery.  
All services need a discovery client to connect to this service.  

    discovery-server$ mvn spring-boot:run

open eureka gui in a browser at http://localhost:8761/

### Movie Service

Simple example service that provides some sample movies through a REST API.  
This service loads the environment specific configuration from the Config Server and
connects itself to the Discovery Server

    movie-service$ mvn spring-boot:run

open browser at http://localhost:8000/movies


### Api Gateway

This movie service client is the front door for all internal services.  
The Api Gateway is the only service, that is available from a client perspective.  
It can be used to proxy services or to modify the service response to a more apropriate client format.  
This also uses Netflix Zuul for dynamic routing, Netflix Ribbon for load balancing
and Netflix Hystrix for circuit breaking.  

    api-gateway$ mvn spring-boot:run 

example call of the movie titles only http://localhost:9999/movies/titles  
also take a look into the health check, to see some of the magic that happens in the background http://localhost:9999/health

## Consul

In the `master` branch you find a setup where we use hashicorp (consul)[https://www.consul.io/] for service discovery, dns and configuration.  
Consul contains a simple key/value store, that could be used to store properties or even yaml configs in it.  

Before we get startet we need to setup a local consul using docker

    $ docker run -p 8400:8400 -p 8500:8500 -p 8600:53/udp -h node1 progrium/consul -server -bootstrap -ui-dir /ui

This will start consul agent in server mode with a gui at http://docker:8500/ui/  
Now we are able to connect to the consul agent.

First lets put some simple configuration data for the application port in the key/value store

    $ curl -X PUT -d 'server.port: 8000' http://docker:8500/v1/kv/config/movie-service/data
    $ curl -X PUT -d 'server.port: 9999' http://docker:8500/v1/kv/config/api-gateway/data

Now you yan start the movie-service and the api-gateway as described before and everything should work.

