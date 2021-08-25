cd .\dan-lab-ui\
npm run build
cd ..
Remove-Item -path ./service-gateway/src/main/resources/public/ -recurse -ErrorAction SilentlyContinue
Copy-Item '.\dan-lab-ui\build\' -Destination '.\service-gateway\src\main\resources\public\' -Recurse
git add .\service-gateway\src\main\resources\public\*
./mvnw clean install -pl service-gateway -am
docker-compose -f ./docker/dan-infra.yml -p "dan-container" up -d --remove-orphans

