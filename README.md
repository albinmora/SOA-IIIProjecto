### SOA-IIIProjecto

## INSTITUTO TECNOLÓGICO DE COSTA RICA

#### Estudiante: Albin Mora Valverde Profesor: Raúl Madrigal Acuña





## INTRODUCCIÓN

El presente documento es una guía rápida de como ejecutar la aplicación de SporTec, la cual se encuentra almacenado en un repositorio de GitHub bajo el siguiente link: https://github.com/albinmora/SOA-IIProyecto.git.

## RECURSOS MULTIMEDIA 
Los diferentes recursos múltimedia a los que tiene acceso la APP, ya se encuentran previamente cargados en el sistema, tanto en las carpetas "res/drawable" o la carpeta "assets", por lo cual no es necesario realizar la carga de ninguno de estos archivos.

## Dependencias Externas
La aplicación en Android utiliza como motor de base de datos MongoDB, la cual es accesible por medio de un API RESTfull/Json desarrolado en la plataforma NodeJs, dicho servidor utiliza la tecnología Moongose para minipular la base de datos.

Para poder establecer comunicación con dicha API, se utilizo la biblioteca com.koushikdutta.ion:ion:2.2.1, quien proveé de una manera simple peticiones HTPP.

Las dependencias anteriormente mencionadas, ya se encuentran incluidas dentro del build.gradle.

## EJECUCIÓN 
Para realizar la ejecución de la aplicación, se deberá abrir el proyecto ubicado en la carpeta SporTec, una vez realizado este proceso, se deberá ejecutar la aplicación en el dispositivo que se desee. Otro método para ejecutar la App, es ejecutar el archivo APK, dentro de la SOA-IIProyecto.
