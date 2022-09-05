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
- import Logzi API Collection.postman_collection.json to your postman collections
- import Logzi Arvan.postman_enviroment.json to your postman enviroments
- import Logzi Dev.postman_enviroment.json to your postman enviroments
- in dev environment postman request to your local and in arvan env request to arvan cloud servers

#### Build and Run log-management Docker Image locally
- set application.yml active to dev in log-management-app module
- mvn clean install 
- docker build -t registry.gitlab.com/logzi/core-microservices/log-management:1.0.0 .
- docker run -p8081:8081 --net mongo-cluster_default registry.gitlab.com/logzi/core-microservices/log-management:1.0.0

## Publish on Arvan cloud servers

#### VPN setup
- download and install jigsaw outline client for your os
- after installed and run outline client
- open : https://s3.amazonaws.com/outline-vpn/invite.html#ss%3A%2F%2FY2hhY2hhMjAtaWV0Zi1wb2x5MTMwNTo4QzlidnprNjFYMnc%40194.5.193.152%3A48416%2F%3Foutline%3D1
- click on "CONNECT THIS DEVICE" button
- click on "COPY" button
- click on "ADD SERVER" button
- if outline client connects then its ok

#### Build and Publish log-management Image on GitLab
- outline client vpn should be connected
- open terminal
- set application.yml active to prod
- cd root folder where Dockerfile exist
- mvn clean install
- docker build -t registry.gitlab.com/logzi/core-microservices/log-management:1.0.0 .
- docker login registry.gitlab.com
- username: logzi-image-publisher
- password: y7GD8Z2_JtzWMvzZBf3p
- docker push registry.gitlab.com/logzi/core-microservices/log-management:1.0.0

#### Deploy Image on Arvan Cloud Servers
- download arvan cli from https://github.com/arvancloud/cli/releases
- add cli to your os environment variables that can run as arvan command in your terminal
- open terminal
- arvan login
- Apikey 8d5e0866-71b6-5f27-84ee-e47844ed048c
- cd .deploy folder
- set new container version in log-management-deploy.yml
- save it
- arvan paas delete deploy log-management
- arvan paas apply -f .\log-management-deploy.yml

#### Docker Useful commands
- docker login url
- docker logout url
- docker ps
- docker volume rm $(docker volume ls -q)
- docker rm -f $(docker ps -a -q) 
 




