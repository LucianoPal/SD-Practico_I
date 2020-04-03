# SD-PracticoI - Introducción a los Sistemas Distribuidos

## Informe

En primer lugar, vamos a generar una serie de pasos a seguir detallados para poder correr los ejercicios a la hora de realizar la corrección. Los mismos son los siguientes: <br>
<br>
**1.	Descargar el repositorio:**

Una vez ingresado al link del repositorio GitHub que enviamos al correo correspondiente, podemos obtener un proyecto Git de dos maneras. La primera es clonar el repositorio localmente o descargarlo de manera comprimida (ZIP).

**2.	Importar proyecto:**

Luego de haber descomprimido o clonado el repositorio, debemos en Eclipse ir a: <br>
**2.1**	File> Import. <br>
**2.2**	Maven> Existing Maven Proyects> Next. <br>
**2.3**	En la nueva ventana podremos buscar luego de clickear Browse, el directorio donde se encuentra el proyecto descargado. <br> 
**2.4**	Finalmente, terminamos clickeando Finish.

**3.	Ejecución:**

En cada uno de los ejercicios, luego de ingresar a: Practico_I > src/main/java y al paquete del punto que se desea corroborar debemos seguir el siguiente orden de ejecución: <br>
Primero, ejecutar el servidor. En los puntos 1 – 5 el nombre de la clase es “Server”. En los dos puntos restantes (6 y 7) posee el nombre de “ServerMain”. <br>
Luego, se debe ejecutar al cliente. En todos los puntos llamado “Client”.

**4.	Resultados:**

* Punto 1: Escriba un servidor que, usando sockets, reciba un mensaje de texto y lo repita a su cliente. Programe también el cliente para verificar y probar el comportamiento del servidor. Realice la implementación en TCP y comente los resultados. <br>
En lo que respecta a esta consigna podemos decir que el mensaje que ingresa el usuario por consola se envía desde el cliente al servidor y este último devuelve una respuesta luego de agregarle algunos caracteres para verificar que lo recibió correctamente. Por ejemplo: <br>
C --&gt;  ‘Hello World’ --&gt;  S --&gt;  ‘Hello World, soy el server’ --&gt;  C <br>
Como observación, podemos añadir que el Server no acepta más de una petición al mismo tiempo y la conexión se cierra inmediatamente después del intercambio.

* Punto 2: Modifique el programa anterior para que pueda atender varios clientes a la vez. <br>
La modificación básica que introduce este punto es que el servidor no “muere” una vez atendida la petición del cliente. Es decir, se cierra la conexión, pero el server continúa escuchando para futuras peticiones.

* Punto 3: Escriba un servidor de mensajes en colas, que permita a los clientes dejar un mensaje (identificando de alguna forma a quién se lo dejan), y bajar los mensajes que le están dirigidos. La comunicación entre cliente y servidor debe ser mediante sockets, y el servidor debe poder atender varios clientes a la vez. <br>
En este punto básicamente se le presenta un menú al usuario en el cual en primer lugar debe ingresar su id (identificador) y luego tiene las opciones para enviar o leer un mensaje y salir. En el primer caso, se deberá colocar el ID del destinatario y posteriormente el asunto y cuerpo del mensaje. Si se desea leer, el server traerá aquellos mensajes coincidentes con el id ingresado al comienzo, en caso de tener. Por último, la opción de salir cerrará la conexión, aunque nuevamente, el servidor continuará escuchando para poder atender otras peticiones.

* Punto 4: Modifique el programa anterior para que el mensaje de la cola sea borrado por el servidor una vez que el cliente confirma, mediante un mensaje de tipo ack, que efectivamente recibió el mensaje que estaba en la cola. <br>
En esta consigna se agrega la opción de borrar mensajes en el menú presentado al usuario. Si no posee mensajes sin leer se lo advertirá y sino, borrará todos luego de leerlos.

* Punto 5: Escribir un servicio que devuelva información de clima del lugar donde reside el servidor. Esta información podrá generarse de forma aleatoria. Deberá ser realizado con RMI. Para ello considere la interface remota, la clase (lado servidor) que implementa esa interface, el servidor, y un cliente que permita probar este funcionamiento. <br>
Tenemos las clases “Client”, “Server”, “IntRemote” y “ServerImplementer”. En esta ocasión, el servidor implementador está obligado a definir el método getClima() ya que implementa IntRemote, y básicamente lo que hace es tomar una descripción aleatoria de una lista y un número random para definir la temperatura. Por ejemplo: ‘Ventoso 30°’
Cabe aclarar que el servicio continúa en escucha.

* Punto 6: Escribir un servidor utilizando RMI, que ofrezca la posibilidad de sumar y restar vectores de enteros. Introduzca un error en su código que modifique los vectores recibidos por parámetro. ¿Qué impacto se genera? ¿Qué conclusión saca sobre la forma de pasaje de parámetros en RMI? Mostrar claramente los valores de los vectores del lado cliente, antes y después de la ejecución de la suma o resta. <br>
Tenemos las clases “Client”, “ServerMain”, “IntRemote” y “ServerImplementer”. En el cliente generamos dos vectores con números aleatorios y el servidor implementador es quien se encarga de hacer la operación solicitada. El error que ingresamos a modo de prueba en esta misma clase es v1.add(0); por lo cual, modificamos el vector. Esto se puede visualizar en las líneas 16 y 34 respectivamente.  <br>
Desde el punto de vista del cliente no se genera ningún impacto ya que obtiene el resultado esperado y sigue viendo los vectores como si no hubiesen sufrido tal modificación. Esto nos hace llegar a la conclusión de que, el pasaje de parámetros por RMI tal y como lo utilizamos nosotros es por valor. Esto quiere decir que trabajamos con una copia de la variable. No podría ser por referencia debido que, al poder estar ubicados en distintos lugares y equipos, no tendrán un esquema de direcciones de memoria en común para acceder a los datos de las variables.

* Punto 7: Implemente un servidor RMI que resuelva tareas genéricas. Para ello tener en cuenta la interface Tarea, que tendrá un método ejecutar(). El objetivo es que desde el cliente se puedan escribir objetos (que implementen la interface Tarea) que hagan un cálculo concreto (calcular un número aleatorio, un primo, el valor de Pi con cierta precisión, etc.), y que esa tarea se pase al servidor RMI, quien hará el cálculo y devolverá el valor al cliente. <br>
Finalmente, en esta última consigna las clases principales son Client, IntRemote, ServerImplementer, ServerMain y Tarea. Tarea, como el punto lo indica, es una interface que tiene el método ejecutar. Y el funcionamiento se basa en que, desde el cliente, se pueden ir creando objetos específicos que implementan esta interfaz tal como la clase Pi, Primo, Aleatorio que colocamos a modo de ejemplo. Luego el ServerImplementer es quien se encarga de ejecutar esa tarea y devolver el resultado. <br>
Planteado de esta manera, podemos crear nuevas clases desde el cliente que resuelvan algún cálculo concreto y los mismos serán resueltos en el servidor RMI.

**5.	Evaluaciones de métricas y tiempos:** 
En cada uno de los puntos lo que hicimos fue añadir una marca de tiempo antes de enviar los datos al servidor y una posterior a la respuesta de los datos solicitados. En algunos puntos la diferencia será prácticamente despreciable puesto que el servidor corre localmente, pero de igual manera, hicimos la prueba en dos máquinas diferentes y el punto 3 y 4, por ejemplo, arrojaron una diferencia entre 4 y 5 milisegundos aproximadamente.

