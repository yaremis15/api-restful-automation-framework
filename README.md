# api-restful-automation-framework

Repositorio del framework de automatización de api testing para la validación de pruebas sobre la api de restful-booker.
***

## 👷 Detalles del Tester

* Name: `Yaremis Martinez Cogollo`
* Correo:`yaremis99@hotmail.com`


***

## ▶️ Ejecutando los Scripts de Pruebas

Ejecute el siguiente comando para configurar gradle correctamente:

```bash
./gradlew build
```

Para realizar la ejecución de los test utilicé el siguiente comando:

```bash
./gradlew clean test --no-build-cache -D cucumber.filter.tags="@integrationTest"
```

**Opcional**: Ir a las clases de la carpeta **runners** `src/test/java/org/api/testing/reto/runners/TestSuiteRunner.java` y
ejecutar
`@integrationTest` Permite la ejecución de toda la automatización
`@createToken`
`@createBooking`
`@getBookings`
`@updateBooking`
`@deleteBooking`

el Runner con el `@Tag` deseado.

***

## 📄 Generación de informes

El informe al detalle con los pasos y resultados de las pruebas que se registrará en la ruta:
`target/site/serenity/index.html`.

Este comando permitirá generar el reporte:

```bash
./gradlew aggregate
```

Adicional se genera un reporte single page si se desea una revisión de alto nivel sobre los resultados de los test, el
cual se registrará en la ruta:
`target/site/serenity/serenity-summary.html`.

Este comando permitirá generar los reportes mencionados:

```bash
./gradlew reports
```