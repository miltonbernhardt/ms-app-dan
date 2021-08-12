docker-compose -f ./docker/dan-infra-without-ms.yml -p "dan-container" up -d
./mvnw install
docker-compose -f ./docker/dan-infra.yml -p "dan-container" up -d

