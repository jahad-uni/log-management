## Getting Started

## Project setup For Development

#### Docker Install
- download and install docker engin and docker compose
- you should have docker and docker-compose commands available in your terminal

#### Setup Mongo Instances replica set dns name
- open hosts file of your os and add bellow line to end of it ex: C:\Windows\System32\drivers\etc\hosts OR /etc/hosts
- 127.0.0.1   mongo1 mongo2 mongo3
- save hosts file
- restart your pc
- open terminal
- ping mongo1
- if reply then everything is ok

#### Setup Mongo ReplicaSet Instances
- open terminal
- cd .mongo-replica-set folder
- docker-compose up
- then list all your containers with
- docker ps
- you should see all three mongo containers run and up
- back to terminal
- docker exec -it mongo1 sh
- mongosh mongo1:30001
- config={"_id":"rs0","members":[{"_id":1,"host":"mongo1:30001","priority":3.0},{"_id":2,"host":"mongo2:30002","priority":2.0},{"_id":3,"host":"mongo3:30003","priority":1.0}]}
- rs.initiate(config)
- if print "ok" then everything is ok

#### Connect to mongo instances with mongo3t or robo3t studio
- download robo3t studio
- connect to mongo1:30001 or mongo2:30002 or mongo3:30003

#### Build and Install dependencies
- install [maven](https://maven.apache.org/download.cgi)
- set application.yml active to dev in log-management-app module
- mvn clean install
- if successfully build then everything ok.

#### Run log-management
- log-management-app module has LogManagementRunnerApplication.class
- open and run it with intellij

#### Postman Setup
- open .postman folder
- import Logzi Rest API Collection.postman_collection.json to your postman collections
- import Logzi Dev.postman_enviroment.json to your postman enviroments
- in dev environment postman request to your local and in arvan env request to arvan cloud servers

#### Build and Run log-management Docker Image locally
- set application.yml active to dev in log-management-app module
- mvn clean install 
- docker build -t registry.gitlab.com/logzi/core-microservices/log-management:1.0.0 .
- docker run -p8081:8081 --net mongo-cluster_default registry.gitlab.com/logzi/core-microservices/log-management:1.0.0

#### Docker Useful commands
- docker login url
- docker logout url
- docker ps
- docker volume rm $(docker volume ls -q)
- docker rm -f $(docker ps -a -q) 
 




