# Projekt-AF-KW-MM

## Repository
Built artifacts are pushed to Docker Hub repositories:
- [Api gateway](https://hub.docker.com/r/kerdamon/api-gateway)
- [Service1](https://hub.docker.com/r/kerdamon/service1)

## CI/CD

### Branches and workflows
### How to add new service (or change name of existing one) to existing workflows

1. Add service name to strategy.matrix.Services in build-services, test-services, and snyk testing in build-and-push-to-prod and build-and-test-app workflows.
2. Add loading build folder in load-builds action (.github/actions/load-builds). Just copy one of previous ones and change name.