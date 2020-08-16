# Proximity Server

## Getting Started

### Prerequisite 
You will need to download and install the following pieces of software:
* [Java](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - version 8 is recommended
* [Maven](https://www.baeldung.com/install-maven-on-windows-linux-mac)

And for Production you will also need:
* [Docker](https://docs.docker.com/install/)
* [Docker-compose](https://docs.docker.com/compose/install/)

## Installation
To run the application you will need to run the following lines of code
```sh
git clone https://github.com/christophperrins/proximity-project
cd proximity-project
mvn package
```

For development testing:
```sh
java -jar target/proximity-0.1.0.jar
```

For Production:
```sh
docker-compose build
docker-compose up -d
```

## Running Tests

To run JUnit tests on the controller and service classes:
```sh
mvn test
```

**To test the endpoints manually go to [/swagger-ui.html](http://localhost:9001/swagger-ui.html)**


## Scaling and high availability
### Making it scalable
To make applications scalable you need to have systems built for horizontal scalability. This means having multiple machines being able to run in parallel. This means not saving anything in cache or locally and instead connecting information through a database. 

When multiple servers are running you next need to use a load balancer. My favorite is nginx due to the simplicity and capability of the software. This is if you want to control the entire process.

An alternative would be to use an orchestration tool such as docker swarm or kubernetes - these can spin up many instances of the server and balance requests between them.

### High availability
Cloud services provide access to machines with a very high uptime - but not never 100%. For a business this poses a large risk.

It is recommended therefore that for a region two instances are running per availability zone, with a minimum of two availability zones being used. If a server needs to be updated in an availbility zone, it should be able to continue without problem. If an availibility goes down due to electrical/thermal or any other reason, the backup availbility zone should be activated. To ensure even higher availability the same steps could be does in another region altogether and act as a backup. So in summary 2 or more regions should be used (not always possible given law restrictions), using at least two availability zones in those regions, with at least two servers running in each of those zones meaning 2 * 2 * 2 = 8 minimum servers for high availability.

## Authors
* Chris Perrins

