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

Para eso ejecutar el script "install-application.bat", el cual primero levanta las imagenes de las cuales los microservicios dependen, luego compilan los ms y luego levanta el container final, que contiene las imagenes de las dependencias y las de los ms.







