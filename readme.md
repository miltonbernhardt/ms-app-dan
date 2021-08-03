Aplicación de gestion de pedidos basada en microservicios
-
Integrantes del proyecto:
- Bernhardt, Milton
- Brizi, Elias
- Luz, Tamara


------



En este proyecto se aloja lo necesario para correr la aplicación basada en microservicios:
- service-gateway: microservicio que sirve de puerta externa de la aplicación y rutea el tráfico hacia los microservicios internos 
- service-discovery: microservicio encargado del descubrir los microservicios internos
- ms-usuario: microservicio de usuarios
- ms-pedido: microservicio de pedido
- ms-producto: microservicio de productos
- ms-cuentacorriente: microservicio de cuentas corrientes
- docker-container: contiene los archivos necesarios para levantar la aplicación

Pasos para la instalación:
-
1. Comentar (usando '#') en el archivo [docker/dan-infra.yml](docker/dan-infra.yml) todo de los siguientes servicios (los microservicios no se compilan si las aplicaciones que necesitan no están activas):
   - **service-gateway**
   - **service-discovery** 
   - **ms-cuentacorriente**
   - **ms-pedido**
   - **ms-producto**
   - **ms-usuario**
2. Levantar las imágenes necesarias para la compilación de los microservicios (postgres, artemis, mongodb)
   - `docker image build artemis -t artemis:1.0.0`
   - `docker-compose -f docker/dan-infra.yml up -d`
3. Se necesitan compilar las imágenes de los microservicios, para eso correr los siguientes comandos:
   - `cd ./service-discovery`
   - `./mvnw install`
   - `cd ../service-gateway`
   - `./mvnw install`
   - `cd ../ms-cuentacorriente`
   - `./mvnw install`
   - `cd ../ms-pedido`
   - `./mvnw install`
   - `cd ../ms-producto`
   - `./mvnw install`
   - `cd ../ms-usuario`
   - `./mvnw install`
4. Descomentar lo comentado en el paso 1 en [docker/dan-infra.yml](docker/dan-infra.yml)
5. Levantar el resto de imagenes
   - `docker-compose -f docker/dan-infra.yml up -d`







