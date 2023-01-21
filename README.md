# Projekt-AF-KW-MM

## Running project

1. Build project with gradle. Run `./gradlew build` in service direcotry.
2. Build and run docker images using `docker compose up --build -d` in root directory.

## Temp test endpoints

GET /service1/hello - hello world from service1
GET /producer/hello - hello world from producer service
POST /producer/sendMessage/{name} - send message to queue with content defined by path variable {name}

## Images Repository
Built artifacts are pushed to Docker Hub repositories:
- [Api gateway](https://hub.docker.com/r/kerdamon/api-gateway)
- [Service1](https://hub.docker.com/r/kerdamon/service1)
- [Producer service](https://hub.docker.com/r/kerdamon/producer)

## CI/CD

### Branches and workflows

#### Build and test project with Gradle and Docker

This workflow is launched when pull request to master branch is made. It builds services, tests them, checks them for vulnerabilities with snyk and builds images with docker compose.

#### Build images and push to production

This workflow is launched when pull request to prod branch is made. It builds services and images, pushes them to [Docker hub registry](https://hub.docker.com/r/kerdamon) and sends command to VM to build this images.

### How to add new service (or change name of existing one) to existing workflows

1. In services  directory create directory with service source code and build tool files.
2. In service directory create Dockerfile that builds image of this service.
3. Add service file name to settings.gradle in services directory.
4. Add service to docker-compose file in root directory.
5. Add configuration to api gateway.
6. [CI/CD] Add service name to strategy.matrix.Services in build-services, test-services, and snyk testing in build-and-push-to-prod and build-and-test-app workflows.
7. [CI/CD] Add loading build folder in load-builds action (.github/actions/load-builds). Just copy one of previous ones and change name.


### Virtual machine with application

Whole application is automatically deployed on virtual machine. Machine is located in AGH WFiIS internal network, so whole communication need to be done through proxy of taurus.fis.agh.edu.pl. 

Internal ip of VM: 172.20.73.3
