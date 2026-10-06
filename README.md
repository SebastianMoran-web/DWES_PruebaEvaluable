# HelpDesk del Centro
 
Aplicación de consola para registrar, consultar, cerrar y conservar entre
ejecuciones las incidencias de un centro educativo.
Desarrollado por Sebastián Morán Quispe
 
Práctica evaluable 1 · Desarrollo Web en Entorno Servidor · 2.º DAW
 
## Instrucciones de ejecución
 
### Desde IntelliJ IDEA
 
1. Abre el proyecto como proyecto Maven (`File > Open`, selecciona la
  carpeta que contiene `pom.xml`).
2. Ejecuta la clase `AplicacionHelpDesk`.
3. Para ejecutar las pruebas: panel lateral **Maven > Lifecycle > test**,
  o clic derecho sobre `src/test/java` → **Run 'Tests...'**.
 
### Datos de persistencia
 
La aplicación lee y escribe el archivo `tickets.txt` en el directorio
raíz del proyecto.
Si el archivo no existe al arrancar, la aplicación se inicia con una
colección vacía sin dar error.
 
Formato de cada línea: `id;cerrado;descripcion`
 
```
1;false;Falla el teclado
2;true;Sin conexión a Internet
```
 
## Responsabilidades de las clases
 
| Clase | Responsabilidad |
|---|---|
| `Ticket` | Datos, validación y estado de una incidencia individual. Identificador y descripción inmutables tras la creación; el único cambio de estado permitido es el cierre, mediante `cerrar()`. |
| `GestorTickets` | Colección de incidencias, asignación automática de identificadores, búsqueda y cálculo de estadísticas. |
| `ArchivoTickets` | Lectura y escritura de `tickets.txt`. Valida el formato de cada línea y detecta identificadores repetidos; si el archivo está corrupto, lanza `IOException` y no se carga nada parcialmente. |
| `AplicacionHelpDesk` | Menú, Scanner (`teclado`) y mensajes de consola. Coordina `GestorTickets` y `ArchivoTickets`, capturando sus excepciones para informar al usuario sin terminar el programa. |
 
## Funcionalidades del menú
 
```
HELPDESK DEL CENTRO
1. Crear incidencia
2. Listar incidencias
3. Buscar incidencia por identificador
4. Cerrar incidencia
5. Mostrar estadísticas
6. Guardar incidencias
0. Salir
```
 
- **Crear**: pide una descripción y asigna un identificador positivo,
 único y automático. Rechaza descripciones nulas, vacías o solo con
 espacios, sin consumir un identificador si falla.
- **Listar**: muestra id, descripción y estado de cada incidencia; avisa
 si no hay ninguna.
- **Buscar**: pide un id y muestra la incidencia, o informa si no existe.
- **Cerrar**: distingue entre ticket inexistente, ya cerrado y abierto
 (que se cierra con éxito).
- **Estadísticas**: total de incidencias, abiertas y cerradas.
- **Guardar**: escribe la colección completa en `tickets.txt`,
 sustituyendo el contenido anterior. El guardado no es automático al
 salir: hay que elegir esta opción antes de cerrar la aplicación.
- **Salir**: finaliza el programa de forma controlada.
## Pruebas automáticas
 
Cubren, como mínimo:
 
- **`TicketTest`**: estado inicial abierto, cierre, cierre repetido,
 rechazo de descripción en blanco, rechazo de descripción nula, rechazo
 de identificador cero.
- **`GestorTicketsTest`**: colección inicialmente vacía, identificadores
 consecutivos, búsqueda que devuelve el mismo objeto (no una copia),
 búsqueda inexistente, creación inválida sin alterar colección ni
 contador, protección de la lista interna frente a modificaciones
 externas, y estadísticas (gestor vacío, dos abiertas, dos con una
 cerrada).
Cada prueba prepara sus propios datos, no usa `Scanner` y no depende del
orden de ejecución.
 
## Limitaciones conocidas
 
- El guardado en archivo no es automático: si se sale de la aplicación
 sin elegir la opción 6, los cambios de esa sesión se pierden.
- La ruta de `tickets.txt` es relativa al directorio de ejecución; si se
 lanza el programa desde una ubicación distinta, puede no encontrar el
 archivo esperado (se tratará como primer arranque, con colección vacía).
- La comprobación de identificadores repetidos y de persistencia completa
 se verifica mediante el escenario de demostración manual descrito en el
 enunciado
