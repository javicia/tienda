FERRETERÍA ONLINE CON SPRING FRAMEWORK
Este proyecto es una aplicación web de una tienda en línea para una ferretería, desarrollada con el uso de tecnologías de Spring Framework, incluyendo Spring Boot, Spring MVC, 
Spring Data JPA, Spring Security, y Thymeleaf para las vistas. 

DESCRIPCIÓN DEL PROYECTO 
La Ferretería Online es una plataforma que permite a los clientes explorar y comprar productos de ferretería desde la comodidad de sus hogares. Los usuarios pueden registrarse, 
buscar productos, agregar productos al carrito de compras, realizar pagos y gestionar sus pedidos. 

TECNOLOGÍAS UTILIZADAS
Spring Framework
Spring Boot
Spring MVC: Para el desarrollo de controladores web.
Spring Data JPA: Para la persistencia de datos en una base de datos relacional.
Spring Security: Para la autenticación y autorización de usuarios.
Thymeleaf: Para la generación de vistas HTML.
Base de Datos MySQL: Para almacenar información sobre productos, pedidos y usuarios.

ESTRUCTURA DEL PROYECTO
src/main/java/com/ecommerce: Contiene el código fuente de la aplicación.
controller: Controladores que gestionan las solicitudes HTTP.
model: Definiciones de modelos de datos (por ejemplo, Product, User, Order).
repository: Repositorios de Spring Data JPA para acceder a la base de datos.
security: Configuración de Spring Security.
service: Servicios que implementan la lógica de negocio.
src/main/resources: Contiene los archivos de recursos.
templates: Plantillas Thymeleaf para las vistas HTML.
static: Archivos estáticos (CSS, JavaScript, imágenes).
application.properties: Configuración de la aplicación.

EJECUCIÓN DEL PROYECTO
Asegúrate de tener Java y Maven instalados en tu sistema.
Clona este repositorio a tu máquina local.
Configura una base de datos MySQL, yo usé MySQLWorkbench 8.0 y actualiza la configuración en application.properties.
Ejecuta la aplicación usando el siguiente comando:
mvn spring-boot:run
La aplicación estará disponible en http://localhost:8080.

CONTRIBUCIONES
Si deseas contribuir a este proyecto, ¡te damos la bienvenida! Siéntete libre de hacer un fork del repositorio, hacer tus cambios y enviar un pull request.
