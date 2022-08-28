# eMovie
Aplicación hecha en Android para mostrar listado de películas y series agrupadas por categorías como *Próximos estrenos*, *Tendencia* y *Recomendados para ti*. Además se muestra un detalle del ítem seleccionado.

Los datos son tomados de [The movie database API V3](https://developers.themoviedb.org/3/getting-started/introduction)

<img src="https://www.themoviedb.org/assets/2/v4/logos/v2/blue_short-8e7b30f73a4020692ccca9c88bafe5dcb6f8a62a4c6bc55cd9ba82bb2cd95f6c.svg" width="100"/>

## Estructura
Se utilizó arquitectura MVVM, con Clean Architecture y Principios SOLID.

## Diseño
Cuenta con la opción de utilizar `LightTheme` y `DarkTheme`.

| Light Theme | Dark Theme |
| :------------: | :------------: |
| <img src="https://user-images.githubusercontent.com/33495370/187045438-ae01c313-f7d0-4eaf-8c4b-a70056075191.png" width="300"/> | <img src="https://user-images.githubusercontent.com/33495370/187045448-77c52eba-ac60-40e6-b8fa-9a8ef7266199.png" width="300"/> |
| <img src="https://user-images.githubusercontent.com/33495370/187045441-9946e4c1-4009-427e-b81f-193a9b23b12e.png" width="300"/> | <img src="https://user-images.githubusercontent.com/33495370/187045449-9eaebd02-9e2e-4829-9929-862e7d438f6c.png" width="300"/> |
| <img src="https://user-images.githubusercontent.com/33495370/187045444-53880141-fb25-416e-a236-048d254d2dd6.png" width="300"/> | <img src="https://user-images.githubusercontent.com/33495370/187045450-a1dfbc1f-4ae3-40f0-9ca8-41e4ef60ab60.png" width="300"/> |

## Librerías utilizadas
 - [Retrofit](https://square.github.io/retrofit/) - Convierte nuestras peticiones HTTP a una interfaz de nuestro código.
 - [OkHttp interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) - Registra en un log las peticiones a la API.
 - [Dagger Hilt](https://dagger.dev/hilt/) - A través de Hilt se implementa la inyección de dependencias de una forma fácil y rápida.
 - [Glide](https://github.com/bumptech/glide) - Glide nos ayuda a gestionar la carga de imágenes de manera más fácil.
 - [Coroutines](https://developer.android.com/kotlin/coroutines) - Las corrutinas de Android nos ayuda a facilitar los llamados asíncronos que hacemos en la app.
 - [Room](https://developer.android.com/training/data-storage/room) - Utilizado para almacenar los datos localmente.
 - [JUnit](https://junit.org/junit4/) - Utilizada para los tests unitarios.
 - [Mockk](https://mockk.io/) - Librería especializada para realizar tests con Kotlin.
 
## Instalación
Puedes descargar el archivo [APK]() en tu teléfono para instalar la app directamente.

## Preguntas
**1. ¿En qué consiste el principio de responsabilidad única? ¿Cuál es su propósito?**

Es el primero de los cinco principios SOLID. Consiste en que un objeto sea encargado de hacer una sola cosa. Y el propósito de este principio es que tengamos clases con menos líneas de código, que sea fácil de entender, que sea fácil de testear, que sea fácil de modificar, en pocas palabras nos facilita mucho el trabajo.

**2. ¿Qué características tiene, según su opinión, un “buen” código o código limpio?**

Clean code es un concepto que abarca un conjunto de buenas prácticas que hacen que nuestro código se vea organizado y sea fácil de entender. Si lo llevamos a cabo de manera correcta, podemos evitarnos muchas cosas, como por ejemplo, tener que documentar mucha parte del código que escribimos. Algunas de las características más importantes que encuentro son:
Nombrar variables y funciones correctamente, evitar funciones con muchas líneas de código, evitar funciones con muchos parámetros, evitar acoplamiento entre clases, no repetir código, hacer tests (esto nos ayuda a tener un código más limpio)

**3. Detalla cómo harías todo aquello que no hayas llegado a completar.**

Creo que se cumplió con todos los puntos propuestos en el ejercicio.
Aunque considero que se puede hacer una muy buena mejora en la capa de Data, creando una clase modelo abstracta utilizando el concepto de polimorfismo, ya que otras clases podrían heredar de esta y utilizar sus funciones y propiedades que son muy similares entre sí.
