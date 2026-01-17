Solución Prueba Teórica – Desarrollo Java

1. ¿Cuáles de los siguientes es verdadero? (Seleccione todos los que apliquen)

A. Bunny es una clase.
B. bun es una clase.
C. main es una clase.
D. Bunny es una referencia a un object.
E. bun es una referencia a un object.
F. main es una referencia a un object.
G. Ninguna de las anteriores.

Respuesta: A y E.

Bunny sí es una clase y bun es una referencia a un objeto (una variable que apunta a una instancia creada con new Bunny()).
Los demás no pueden ser porque bun no es clase, es variable. Main no es clase ni objeto, es un método.

2. Dado: ¿Cuál es el resultado?

Opciones:
A. 0
B. 01
C. 02
D. 03
E. 013
F. 023
G. Error de compilación.

Respuesta: D. 03

Si se tiene que x = -1 y y = -5, entonces entra al primer if (x < 5) pero al segundo if (y > 0) no.
Como no hay llaves, el else se pega al if (y > 0). Por eso entra al else y concatena "3".
Luego imprime “0” + “3” = 03.

3. Dado: ¿Cuál es el resultado?

Opciones:
A. 9
B. 10
C. 11
D. 12
E. Error de compilación.
F. Una excepción en tiempo de ejecución.

Respuesta: B. 10

El count inicia en 0. En el constructor sin parámetros, mientras count < 10, se crea new Bunnies(++count) y va incrementando.
Al final del while queda count = 10.
Después se imprime count++, entonces imprime 10 y luego internamente lo sube a 11.

4. Dado: ¿Cuáles de las siguientes respuestas son correctas? (Seleccione todas las que apliquen)

Opciones:
A. 0
B. Error de compilación línea 2
C. Error de compilación línea 4
D. Error de compilación línea 5
E. Error de compilación línea 6
F. 0null
G. nullnull

Respuesta: E. Error de compilación línea 6

Porque la variable a_b es local y no tiene valor por defecto. En Java, las variables locales deben inicializarse antes de usarse.
En cambio $ sí es un campo estático y por defecto vale 0.

5. ¿Cuál de las siguientes afirmaciones es correcta?

A. "X extends Y" es correcto si y solo si X es una clase y Y es una interface.
B. "X extends Y" es correcto si y solo si X es una interface y Y es una clase.
C. "X extends Y" es correcto si X y Y son ambas clases o ambas interfaces.
D. "X extends Y" es correcto para todas las combinaciones.

Respuesta: C.

Una clase no puede extender una interface (debería usar implements) y una interface no puede extender una clase.
Por eso extends funciona cuando ambos son del mismo tipo: clase-clase o interface-interface.

6. Dado: (Threads / join) ¿Cuál es el resultado?

Opciones:
A. 4
B. 5
C. 8
D. 9
E. Error de compilación
F. Se lanza una excepción en tiempo de ejecución

Respuesta: D. 9

Aquí lo que sucede es que se crea el objeto y en el constructor se asigna x = 5 y se llama a start(). Como se ejecuta el thread, entra a run() y hace x \*= 2, entonces x pasa a 10. En makeItSo() se hace join(), o sea, se espera a que el thread termine antes de seguir. Luego hace x = x - 1, por lo que queda 9, y eso es lo que imprime.

7. Dado: (Constructor vs método con void) ¿Cuál es el resultado?

Opciones:
A. 0
B. 4
C. Error de compilación en la línea 3
D. Error de compilación en la línea 4
E. Error de compilación en la línea 7
F. Error de compilación en la línea 3

Respuesta: A. 0

Porque public void Salmon() no es constructor, es un método normal (tiene tipo de retorno void). Entonces el constructor real que se usa es el constructor por defecto (sin cuerpo), y el campo count queda con su valor por defecto, que es 0. Por eso al imprimir, sale 0.

8. Dado: (extends / implements) ¿Cuáles de las siguientes respuestas son correctas? (Seleccione todas las que apliquen)

Opciones:
A. Clase A no compila
B. Clase B no compila
C. Clase C no compila
D. Clase D no compila
E. Clase E no compila
F. Clase F no compila
G. Todas las clases compilan

Respuesta: B, C, D y E.

B no compila porque no se puede repetir implements dos veces. Lo correcto es implements i1, i2.
C no compila porque implements c1 está mal: implements es solo para interfaces, no para clases. Si fuera una clase debería ser extends c1.
D no compila por sintaxis: no va coma después de extends c1. Debe ser extends c1 implements i2.
E no compila porque una clase no puede extends interfaces, ahí debe usar implements. Además extends en clases es solo para una clase.
Las que sí compilan:
A sí compila: extends c2 implements i1
F sí compila: implements i1, i2

9. SQL – Modelo PERSONA / CIUDAD / PAIS

Tablas:

PERSONA(id_persona pk, nombre, email, id_ciudad fk)
CIUDAD(id_ciudad pk, nombre_ciudad, id_pais fk)
PAIS(id_pais pk, nombre_pais)

9.1 El nombre, email, ciudad y país de todas las personas
SELECT
p.nombre,
p.email,
c.nombre_ciudad AS ciudad,
pa.nombre_pais AS pais
FROM PERSONA p
JOIN CIUDAD c ON c.id_ciudad = p.id_ciudad
JOIN PAIS pa ON pa.id_pais = c.id_pais;

9.2 Los países que no tienen personas asociadas
SELECT
pa.id_pais,
pa.nombre_pais
FROM PAIS pa
WHERE NOT EXISTS (
SELECT 1
FROM CIUDAD c
JOIN PERSONA p ON p.id_ciudad = c.id_ciudad
WHERE c.id_pais = pa.id_pais
);

9.3 Personas que viven en ciudades diferentes de Medellín y Bogotá y con correo terminado en “.com”
SELECT
p.nombre,
p.email,
c.nombre_ciudad AS ciudad,
pa.nombre_pais AS pais
FROM PERSONA p
JOIN CIUDAD c ON c.id_ciudad = p.id_ciudad
JOIN PAIS pa ON pa.id_pais = c.id_pais
WHERE c.nombre_ciudad NOT IN ('Medellín', 'Bogotá')
AND LOWER(p.email) LIKE '%.com';
