# Prueba T茅cnica v2 appgate - Rub茅n Dar铆o Ballesteros Padilla
Repositorio prueba t茅cnica appgate

## Calculator API REST 馃捇
API REST para calcular: suma, resta, multiplicaci贸n, divisi贸n y potenciaci贸n de un conjunto de n煤meros.

Los servicios responden al siguiente flujo:
*	El cliente llama un servicio para empezar (Nueva sesi贸n).
*	El cliente llama un servicio para agregar los operandos (Agregar operando), tantas veces como se quiera.
*	El cliente llama un servicio para ejecutar una operaci贸n matem谩tica (Realizar operaci贸n).

## Propuesta T茅cnica
_Por la necesidad que existe de tener desacoplado las instancias y de conocer el comportamiento de los objetos se utiliza el patron de comportamiento Observer, con el cual se garantiza conocer el estado de la sesi贸n y de esta forma al cambiar el identificador de la misma se crea un nuevo ambiente o entorno. A demas como patr贸n de creaci贸n o construcci贸n se utiliza el patr贸n Builder, debido a que es necesario generar diferentes tipos de operaciones bajo un mismo paso a paso de contrucci贸n y permite que el codigo pueda crecer a futuro o agregar otra operaci贸n sin afectar la totalidad de las capas del servicio. Esta propuesta se describe en el siguiente diagrama:_

![Diagrama de componente](/documents/images/test_appgate.png)

## Contruido con

* [Maven](https://maven.apache.org/), para el manejo de dependencias
* [Spring Boot](https://spring.io/projects/spring-boot), Para minizar esfuerzos de configuraci贸n sobre Spring

### Build Project
_Por la forma en la que esta construido es requerido nuestro archivo de dependencias pom.xml. y ejecutar el comando Maven para su empaquetado:_

```
mvn install
```
### Docker Images

_Para contruit la image se debe ejecutar el siguiente comando:_

```
docker build -t test.appgate .
```

### Tests
_Se pueden ejecutar el conjunto de pruebas unitarias con Maven:_

```
mvn test
```


### Run Docker Container
_Esta aplicaci贸n corre por el puerto 8081 seg煤n lo definido en las configuraciones de Spring Boot y para lanzar el contenedor por consola ejecutar el comando:_

```
docker run -it -d -p 8081:8081 --name test.appgate.container test.appgate
```
### Test API REST - CURL
_Este servicio dispone de tres operaciones GET(newSession, addValue, executeOperation) y para su correcto funcionamiento el servicio debe iniciar una instancia o sesi贸n, es por ellos que debe lanzarse primero la operaci贸n "newSession". Estas operaciones se describen a continucaci贸n:_

* **newSession:** Obtiene nueva sesion o ambiente con instancias reseteadas.
```
CURL: http://localhost:8081/calculator/newSession
```
* **addValue:** Permite agregar operandos y envia como path variable el valor del operando _{value}_.
```
CURL: http://localhost:8081/calculator/addValue/{value}
```
* **executeOperation:** Permite ejecutar las operaciones proporcionadas por e usuario y envia como path variable el valor de la operaci贸n operando _{operation}_. _valores: suma, resta, multiplicacion, division y potencia._
```
CURL: http://localhost:8081/calculator/executeOperation/{operation}
```

### Deploy
_Para este proceso se contempla un proceso de integraci贸n continua comun y simple en donde cada confirmaci贸n o publicaci贸n dispara el Pipeline Jenkins para clonar y posteriormente generar los pasos de contrucci贸n y ejecuci贸n. Esto se describe mejor en el [Jenkinsfile](service/Jenkinsfile) y el diagrama siguiente:_ 

![Diagrama de estrategia](documents/images/estrategia_ci_cu.png)

### Unit Test

#### Basic Cases
* Divisi贸n por cero
* Potenciaci贸n elevada a cero
* Potenciaci贸n elevada a valores m谩ximos.
* Instanciaci贸n de factories y builders.
* Multiplicaci贸n
* Suma
* Resta de negativos
* Excepciones esperadas
