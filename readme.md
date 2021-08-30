Aplicación de gestion de pedidos basada en microservicios
-
Integrantes del proyecto:
- Bernhardt, Milton
- Brizi, Elias
- Luz, Tamara

---

En este proyecto se aloja lo necesario para correr la aplicación basada en microservicios:
- service-gateway: microservicio que sirve de puerta externa de la aplicación y rutea el tráfico hacia los microservicios internos 
- service-discovery: microservicio encargado del descubrir los microservicios internos
- ms-usuario: microservicio de usuarios
- ms-pedido: microservicio de pedido
- ms-producto: microservicio de productos
- ms-cuentacorriente: microservicio de cuentas corrientes
- docker-container: contiene los archivos necesarios para levantar la aplicación

---

Para instalar la aplicación se necesitan tener los microservicios instalados y compilados y asi poder levantar el container de docker.

Para eso ejecutar el script "install-application.bat", el cual primero levanta las imágenes de las cuales los microservicios dependen, luego compilan los ms y luego levanta el container final, que contiene las imágenes de las dependencias y las de los ms.

---
Se necesitan tener los siguientes puertos disponibles:
- 3000: servicio de gráficos relacionados con los microservicios activos proveído por grafana.
- 5432: para el acceso a postgres.
- 8761: microservicio encargado de descubrir los servicios disponibles.
- 8181: para correr el microservicio que sirve de gateway para el resto de servicios y corre el frontend.
- 9000: microservicio de gestión de usuarios, clientes, empleados y obras.
- 9001: microservicio para la gestión de productos en venta.
- 9002: microservicio para la gestión de pedidos de productos por los clientes.
- 9003: microservicio de gestión de las cuentas corrientes de los clientes.
- 9004: microservicio que sirve para gestión las liquidaciones de los empleados
- 9090: para correr el servicio de prometheus.
- 61616: artemis
---
Password: "dan2021" es tanto el usuario y contraseña requerida por la base de datos y por la aplicación.
Para grafana usar "admin" como usuario y "dan2021" como password.
---
Para probar con insomnia y usar la gateway fijarse que en auth tenga "Authorization: Bearer " más el token generado por al loguearse (se puede generar uno con el método gateway - /api/login -> loginUser en insomnia) 

---
Para usar un dashboard precreado de grafana, importar el archivo grafana/dashboards/mydashboards.json en grafana.







