!#/bin/bash
cd ms-liquidacion
./mvnw  install
cd ../ms-cuentacorriente
./mvnw  install
cd ../ms-pedido
./mvnw  install 
cd ../ms-producto
./mvnw  install
cd ../ms-usuario
./mvnw install  
cd ../service-discovery
./mvnw install  
cd ../service-gateway
./mvnw install
