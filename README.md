# Módulo A9 - Métodos y Algoritmos Greedy

Este repositorio contiene una colección de métodos implementados como parte del módulo **A9**, enfocados en algoritmos greedy para la resolución de problemas comunes. Los métodos están diseñados para ser genéricos y aplicables en una variedad de escenarios.


## Métodos Implementados

### 1. `llenarCDVoraz`
**Archivo:** `LlenarCD.java`

Selecciona un conjunto de programas que quepan en un CD de capacidad limitada utilizando un enfoque voraz.

- **Entradas:**
  - `capacidadMax` (int): Capacidad máxima del CD.
  - `espacioProgramas` (Map<String, Integer>): Espacio requerido por cada programa.
- **Salida:** Lista de programas que caben en el CD.

---

### 2. `llenarMochila`
**Archivo:** `MochilaVoraz.java`

Resuelve el problema de la mochila, seleccionando los objetos de manera voraz para maximizar el uso del volumen disponible.

- **Entradas:**
  - `volumenMaximo` (int): Capacidad máxima de la mochila.
  - `cantidades` (Map<String, Integer>): Cantidad de cada objeto disponible.
  - `volumenes` (Map<String, Integer>): Volumen de cada objeto.
- **Salida:** Un mapa con la cantidad de objetos seleccionados.

---

### 3. `horarioExamenes`
**Archivo:** `HorarioExamenes.java`

Asigna días de la semana para exámenes de asignaturas, asegurando que dos asignaturas adyacentes (con estudiantes comunes) no se programen el mismo día.

- **Entradas:**
  - `g` (Grafo<String, T>): Grafo representando las relaciones entre asignaturas.
  - `diasSemana` (String[]): Días disponibles para programar.
- **Salida:** Un mapa que asocia cada asignatura con un día.

---

### 4. `darCambio`
**Archivo:** `DarCambio.java`

Calcula la cantidad de billetes y monedas necesarios para devolver un importe utilizando un enfoque voraz.

- **Entradas:**
  - `importeDevolver` (int): Monto a devolver.
  - `mapCanti` (Map<Integer, Integer>): Cantidad disponible de cada denominación.
- **Salida:** Mapa de billetes/monedas utilizados.

---

### 5. `dijkstra`
**Archivo:** `Dijkstra.java`

Implementa el algoritmo de Dijkstra para encontrar la distancia mínima desde un vértice origen a todos los demás vértices en un grafo ponderado.

- **Entradas:**
  - `g` (Grafo<E, Integer>): Grafo ponderado.
  - `v` (Vertice<E>): Vértice inicial.
- **Salida:** Mapa con la distancia mínima a cada vértice.


# Diagrama de Flujo para el Algoritmo de Dijkstra

## Inicio
1. **Inicialización:**
   - Crear una lista `porVisitar` para almacenar los vértices no visitados.
   - Crear un mapa `mapa` para almacenar las distancias, inicializando todas a `Integer.MAX_VALUE`.
   - Establecer la distancia del vértice inicial `v` a 0 en `mapa`.

2. **Agregar todos los vértices a `porVisitar` y al mapa:**
   - Iterar por todos los vértices del grafo `g`.
   - Agregar cada vértice a `porVisitar`.
   - Inicializar su distancia en el `mapa` a `Integer.MAX_VALUE`.

3. **Mientras `porVisitar` no esté vacío:**
   1. **Seleccionar el vértice con menor distancia:**
      - Llamar a la función `seleccionar` que recorre `mapa` y encuentra el vértice con la menor distancia entre los no visitados.
   2. **Eliminar el vértice seleccionado de `porVisitar`.**
   3. **Iterar sobre los arcos del vértice seleccionado:**
      - Para cada arco que conecte al vértice seleccionado con otro destino:
        - Si el destino aún está en `porVisitar`:
          1. Calcular la nueva distancia como la suma de la distancia del vértice actual y la etiqueta del arco.
          2. Si la nueva distancia es menor que la distancia almacenada en el mapa:
             - Actualizar la distancia del destino en el mapa.

4. **Fin del ciclo**
   - Repetir hasta que todos los vértices hayan sido visitados.

## Salida
- Devolver el mapa `mapa` que contiene las distancias mínimas desde el vértice inicial a todos los demás.

### Función seleccionar

1. Recorrer el mapa de distancias.
2. Para cada vértice:
   - Verificar si el vértice está en `porVisitar`.
   - Comparar su distancia con la menor distancia encontrada hasta el momento.
3. Retornar el vértice con menor distancia que cumpla las condiciones:
   - Pertenece a `porVisitar`.
   - Su distancia es menor o igual a las demás.

**Fin**


---

### 6. `colorearMapa`
**Archivo:** `ColorearMapa.java`

Asigna colores a un grafo utilizando un enfoque voraz, asegurando que dos vértices adyacentes no compartan el mismo color.

- **Entradas:**
  - `g` (Grafo<E, Integer>): Grafo a colorear.
  - `colores` (String[]): Colores disponibles.
- **Salida:** Mapa que asocia cada vértice con un color.

---
