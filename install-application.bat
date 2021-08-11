docker-compose -f ./docker/dan-infra-1.yml -p "dan-container" up -d
./mvnw install
docker-compose -f ./docker/dan-infra-2.yml -p "dan-container" up -d
