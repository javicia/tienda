# Ferretería Online con Spring Framework

Este repositorio contiene el código fuente de una aplicación web de una tienda en línea para una ferretería. La aplicación está desarrollada utilizando tecnologías de Spring Framework, incluyendo Spring Boot, Spring MVC, Spring Data JPA, Spring Security y Thymeleaf para las vistas.

## Descripción del Proyecto

La Ferretería Online es una plataforma que permite a los clientes explorar y comprar productos de ferretería desde la comodidad de sus hogares. Los usuarios pueden registrarse, buscar productos, agregar productos al carrito de compras, realizar pagos y gestionar sus pedidos.

## Tecnologías Utilizadas

- Spring Framework
- Spring Boot
- Spring MVC: Para el desarrollo de controladores web.
- Spring Data JPA: Para la persistencia de datos en una base de datos relacional.
- Spring Security: Para la autenticación y autorización de usuarios.
- Thymeleaf: Para la generación de vistas HTML.
- Base de Datos MySQL: Para almacenar información sobre productos, pedidos y usuarios.

## Estructura del Proyecto

- `src/main/java/com/ecommerce`: Contiene el código fuente de la aplicación.
  - `controller`: Controladores que gestionan las solicitudes HTTP.
  - `model`: Definiciones de modelos de datos (por ejemplo, Product, User, Order).
  - `repository`: Repositorios de Spring Data JPA para acceder a la base de datos.
  - `security`: Configuración de Spring Security.
  - `service`: Servicios que implementan la lógica de negocio.

- `src/main/resources`: Contiene los archivos de recursos.
  - `templates`: Plantillas Thymeleaf para las vistas HTML.
  - `static`: Archivos estáticos (CSS, JavaScript, imágenes).
  - `application.properties`: Configuración de la aplicación.

## Ejecución del Proyecto

Asegúrate de tener Java y Maven instalados en tu sistema.
1. Clona este repositorio a tu máquina local.
2. Configura una base de datos MySQL, yo usé MySQL Workbench 8.0, y actualiza la configuración en `application.properties`.
3. Ejecuta la aplicación usando el siguiente comando:

```bash
mvn spring-boot:run
