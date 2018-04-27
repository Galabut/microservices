docker run -d --hostname my-rabbit --name rabbitHystrix -e RABBITMQ_DEFAULT_USER=guest -e RABBITMQ_DEFAULT_PASS=guest -p 5672:5672 -p 15672:15672 rabbitmq:3-management

docker run --name microservices -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=microservices -p 3306:3306 -d mysql:5.6.40