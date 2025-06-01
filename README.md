## spring app template

| __JaCoCo Test Coverage__ | [![coverage](https://raw.githubusercontent.com/ebritt07/spring-app/badges/jacoco.svg)](https://github.com/ebritt07/spring-app/actions/workflows/build.yml) |
|:-------------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------|

### install

- install jdk 21
- mvn install

### running locally

- run TestApplication.java
- test the API's using http://localhost:8445/spring-app/swagger-ui/index.html
- inspect the local in memory DB getting updated using:
    - http://localhost:8445/spring-app/h2-console/
        - use the url and login info supplied in application.yaml

### deployment

- on pull requests to master, the app will be dockerized and pushed to ECR with tag name as the source branch
- on pushes to master or tags, the ecr version is the branch name
