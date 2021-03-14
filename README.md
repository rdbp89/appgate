# Prueba Técnica v2 appgate - Rubén Darío Ballesteros Padilla
Repositorio prueba técnica appgate

## Calculator API REST 💻
API REST para calcular: suma, resta, multiplicación, división y potenciación de un conjunto de números.

Los servicios responden al siguiente flujo:
*	El cliente llama un servicio para empezar (Nueva sesión).
*	El cliente llama un servicio para agregar los operandos (Agregar operando), tantas veces como se quiera.
*	El cliente llama un servicio para ejecutar una operación matemática (Realizar operación).

## Propuesta Técnica
_Por la necesidad que existe de tener desacoplado las instancias y de conocer el comportamiento de los objetos se utiliza el patron de comportamiento Observer, con el cual se garantiza conocer el estado de la sesión y de esta forma al cambiar el identificador de la misma se crea un nuevo ambiente o entorno. A demas como patrón de creación o construcción se utiliza el patrón Builder, debido a que es necesario generar diferentes tipos de operaciones bajo un mismo paso a paso de contrucción y permite que el codigo pueda crecer a futuro o agregar otra operación sin afectar la totalidad de las capas del servicio. Esta propuesta se describe en el siguiente diagrama:_

![Diagrama de componente](/documents/images/test_appgate.png)
