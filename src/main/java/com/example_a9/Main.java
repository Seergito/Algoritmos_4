package com.example_a9;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }


    public static Lista<String> llenarCDVoraz(int capacidadMax, Map<String, Integer> espacioProgramas) {
    // Inicializa una lista para almacenar los programas seleccionados
    Lista<String> CD = new ListaEnlazada<>();
    // Bandera para indicar si quedan programas por analizar
    boolean quedanProgramas = true;

    // Mientras haya capacidad en el CD y existan programas por considerar
    while (capacidadMax > 0 && quedanProgramas) {
        // Selecciona el programa que mejor se ajusta a la capacidad restante
        String programa = seleccionarPrograma(capacidadMax, espacioProgramas);

        // Si se encuentra un programa válido
        if (programa != null) {
            // Reduce la capacidad disponible del CD según el tamaño del programa
            capacidadMax -= espacioProgramas.get(programa);
            // Añade el programa seleccionado al final de la lista del CD
            CD.insertarFinal(programa);
            // Elimina el programa del mapa para no volver a considerarlo
            espacioProgramas.eliminar(programa);
        } else {
            // Si no se encuentra un programa válido, finaliza el proceso
            quedanProgramas = false;
        }
    }
    // Devuelve la lista de programas seleccionados para llenar el CD
    return CD;
}


private static String seleccionarPrograma(int capacidadMax, Map<String, Integer> espacioProgramas) {
    // Variable para almacenar el programa seleccionado
    String toRet = null;
    // Iterador para recorrer las claves (nombres de programas) del mapa
    Iterator<String> it = espacioProgramas.getClaves();

    // Recorre todos los programas disponibles
    while (it.hasNext()) {
        String actual = it.next();

        // Verifica si el programa actual cabe en la capacidad disponible
        // y si es más grande que el programa seleccionado hasta ahora (optimización)
        if (espacioProgramas.get(actual) <= capacidadMax &&
            (toRet == null || espacioProgramas.get(toRet) < espacioProgramas.get(actual))) {
            // Actualiza el programa seleccionado al actual
            toRet = actual;
        }
    }

    // Devuelve el nombre del programa seleccionado o null si no se encontró
    return toRet;
}


public static Map<String, Integer> llenarMochila(int volumenMaximo, Map<String,Integer> cantidades, Map<String,Integer> volumenes) {

    // Inicializa el mapa para almacenar la solución, es decir, los objetos seleccionados y sus cantidades
    Map<String, Integer> solucion = new HashMap<>();

    // Bandera para indicar si todavía quedan opciones válidas para llenar la mochila
    boolean quedanOpciones = true;

    // Mientras haya volumen disponible en la mochila y existan objetos seleccionables
    while (volumenMaximo > 0 && quedanOpciones) {
        // Selecciona el objeto más grande que pueda caber en la mochila
        String objeto = seleccionarMochila(volumenMaximo, volumenes);

        // Si se encuentra un objeto válido
        if (objeto != null) {
            // Inicializa la cantidad de este objeto en la solución
            solucion.put(objeto, 0);

            // Mientras el objeto pueda caber en la mochila y queden unidades disponibles
            while (volumenMaximo >= volumenes.get(objeto) && cantidades.get(objeto) > 0) {
                // Reduce el volumen disponible en la mochila según el tamaño del objeto
                volumenMaximo -= volumenes.get(objeto);

                // Incrementa la cantidad de este objeto en la solución
                solucion.put(objeto, solucion.get(objeto) + 1);

                // Reduce la cantidad disponible de este objeto en el inventario
                cantidades.put(objeto, cantidades.get(objeto) - 1);
            }

            // Si ya no quedan unidades de este objeto, elimínalo de las listas de selección
            if (cantidades.get(objeto) == 0) {
                cantidades.remove(objeto);
                volumenes.remove(objeto);
            }

        } else {
            // Si no hay objetos válidos para agregar, termina el proceso
            quedanOpciones = false;
        }
    }

    // Devuelve el mapa con los objetos seleccionados y sus cantidades
    return solucion;
}


public static <T> Map<Vertice<String>, String> horarioExamens(Grafo<String, T> g, String[] diasSemana) {
    // Mapa para almacenar la solución, es decir, qué día se asigna a cada asignatura.
    Map<Vertice<String>, String> solucion = new HashMap<>();

    // Iterador para recorrer los vértices del grafo (las asignaturas).
    Iterator<Vertice<String>> asignaturas = g.vertices();
    Vertice<String> asignatura;

    // Recorremos todas las asignaturas del grafo.
    while (asignaturas.hasNext()) {
        asignatura = asignaturas.next();

        // Selecciona un día válido para la asignatura actual considerando restricciones.
        int posicion = seleccionarDia(g.adyacentes(asignatura), diasSemana, solucion);

        // Si se encuentra un día válido para la asignatura, lo asigna.
        if (posicion != -1) {
            solucion.put(asignatura, diasSemana[posicion]);
        } else {
            // Si no se puede asignar ningún día válido, se devuelve un mapa vacío (sin solución).
            return new HashMap<Vertice<String>, String>();
        }
    }

    // Devuelve el mapa con la solución: cada asignatura asociada a un día.
    return solucion;
}

private static int seleccionarDia(Iterator<Vertice<String>> ady, String[] diasSemana, Map<Vertice<String>, String> solucion) {
    // Array para marcar los días inválidos según las restricciones.
    boolean[] diaInvalido = new boolean[diasSemana.length];

    // Recorremos los adyacentes (asignaturas conectadas a la actual) para determinar días ocupados.
    while (ady.hasNext()) {
        Vertice<String> asignatura = ady.next();
        String dia = solucion.get(asignatura);

        // Si la asignatura adyacente ya tiene un día asignado
        if (dia != null) {
            int i = 0;
            boolean encontrado = false;

            // Busca el índice del día en el array de días de la semana.
            while (i < diasSemana.length && !encontrado) {
                if (diasSemana[i].equals(dia)) {
                    encontrado = true;
                    diaInvalido[i] = true; // Marca el día como inválido.
                }
                i++;
            }
        }
    }

    // Busca un día válido en el array de días de la semana.
    for (int i = 0; i < diasSemana.length; i++) {
        if (!diaInvalido[i]) {
            return i; // Devuelve el índice del primer día válido encontrado.
        }
    }

    // Si no hay días válidos disponibles, devuelve -1.
    return -1;
}


public static Map<Integer, Integer> darCambio(int importeDevolver, Map<Integer, Integer> mapCanti) {
    // Mapa para almacenar la solución: billete -> cantidad usada.
    Map<Integer, Integer> solucion = new HashMap<>();
    boolean haySolucion = true;

    // Mientras quede importe por devolver y haya solución posible.
    while (importeDevolver > 0 && haySolucion) {
        // Selecciona el billete más grande que se pueda usar.
        int billete = seleccionarBillete(importeDevolver, mapCanti);

        // Si hay un billete válido.
        if (billete != -1) {
            // Inserta el billete en la solución si no está previamente.
            solucion.put(billete, 0);

            // Mientras el billete pueda restar al importe y haya suficiente cantidad disponible.
            while (importeDevolver >= billete && mapCanti.get(billete) > 0) {
                // Resta el valor del billete al importe a devolver.
                importeDevolver -= billete;

                // Actualiza la cantidad restante de ese billete en el mapa.
                mapCanti.put(billete, mapCanti.get(billete) - 1);

                // Incrementa la cantidad utilizada de ese billete en la solución.
                solucion.put(billete, solucion.get(billete) + 1);
            }

            // Si ya no quedan billetes de este tipo, se eliminan del mapa.
            if (mapCanti.get(billete) == 0) {
                mapCanti.remove(billete);
            }
        } else {
            // Si no se puede encontrar un billete válido, no hay solución.
            haySolucion = false;
        }
    }

    // Si hay solución, devuelve el mapa con los billetes usados; si no, devuelve un mapa vacío.
    return (haySolucion) ? solucion : new HashMap<>();
}


private static int seleccionarBillete(int importeDevolver, Map<Integer, Integer> mapCanti) {
    int toRet = -1; // Almacena el billete más grande válido encontrado.
    Iterator<Integer> it = mapCanti.keySet().iterator();
    int actual;

    // Recorre todos los billetes disponibles.
    while (it.hasNext()) {
        actual = it.next();

        // Busca el billete más grande que no supere el importe a devolver.
        if (actual > toRet && actual <= importeDevolver) {
            toRet = actual;
        }
    }

    // Devuelve el billete seleccionado, o -1 si no hay ninguno válido.
    return toRet;
}



public static <E> Map<Vertice<E>, Integer> dijkstra(Grafo<E, Integer> g, Vertice<E> v) {
    // Lista de vértices pendientes de visitar.
    Lista<Vertice<E>> porVisitar = new ListaEnlazada();
    // Iterador para recorrer los vértices del grafo.
    Iterator<Vertice<E>> it = g.vertices();
    // Mapa para almacenar las distancias mínimas desde el vértice inicial a cada vértice.
    Map<Vertice<E>, Integer> mapa = new HashMap<>();

    // Inicialización de las estructuras:
    while (it.hasNext()) {
        Vertice<E> vertice = it.next();
        // Agrega todos los vértices a la lista de pendientes.
        porVisitar.insertarPrincipio(vertice);
        // Inicializa las distancias como infinito (representado por Integer.MAX_VALUE).
        mapa.put(vertice, Integer.MAX_VALUE);
    }
    // La distancia al vértice inicial es 0.
    mapa.put(v, 0);

    // Mientras haya vértices por visitar.
    while (!porVisitar.esVacio()) {
        // Selecciona el vértice con la distancia mínima que aún no ha sido procesado.
        Vertice<E> vertice = seleccionar(mapa, porVisitar);
        // Marca el vértice como visitado eliminándolo de la lista.
        porVisitar.suprimir(vertice);

        // Itera sobre todos los arcos del grafo.
        Iterator<Arco<E, Integer>> arcos = g.arcos();
        while (arcos.hasNext()) {
            Arco<E, Integer> arco = arcos.next();
            // Si el arco parte del vértice actual y apunta a un vértice pendiente de visitar.
            if (arco.getOrigen().equals(vertice) && porVisitar.contiene(arco.getDestino())) {
                // Calcula la distancia tentativa desde el vértice inicial al destino del arco.
                int distanciaActual = mapa.get(vertice) + arco.getEtiqueta();

                // Si la nueva distancia es mejor (menor) que la registrada actualmente en el mapa.
                if (distanciaActual >= 0 && distanciaActual < mapa.get(arco.getDestino())) {
                    // Actualiza la distancia mínima para el vértice destino.
                    mapa.put(arco.getDestino(), distanciaActual);
                }
            }
        }
    }

    // Devuelve el mapa con las distancias mínimas.
    return mapa;
}


private static <E> Vertice<E> seleccionar(Map<Vertice<E>, Integer> mapa, Lista<Vertice<E>> porVisitar) {
    Vertice<E> toRet = null; // Vértice con la menor distancia.
    Vertice<E> vertice; // Variable temporal para iterar.
    int tam = Integer.MAX_VALUE; // Inicializa con el valor máximo posible.

    // Iterador para recorrer las claves del mapa.
    Iterator<Vertice<E>> it = mapa.keySet().iterator();

    while (it.hasNext()) {
        vertice = it.next();
        // Si el vértice está en la lista de pendientes y tiene la distancia mínima conocida.
        if (porVisitar.contiene(vertice) && mapa.get(vertice) < tam) {
            tam = mapa.get(vertice); // Actualiza la distancia mínima.
            toRet = vertice; // Actualiza el vértice seleccionado.
        }
    }

    // Devuelve el vértice con la distancia mínima.
    return toRet;
}


public static <E> Map<Vertice<E>, String> colorearMapa(Grafo<E, Integer> g, String[] colores) {
    // Lista de vértices pendientes de colorear.
    Lista<Vertice<E>> porVisitar = new ListaEnlazada<>();
    // Mapa que almacena el color asignado a cada vértice.
    Map<Vertice<E>, String> mapaColoreado = new HashMap<>();
    // Iterador para recorrer los vértices del grafo.
    Iterator<Vertice<E>> it = g.vertices();

    // Agrega todos los vértices del grafo a la lista de pendientes.
    while (it.hasNext()) {
        porVisitar.insertarFinal(it.next());
    }

    // Mientras haya vértices pendientes de colorear.
    while (!porVisitar.esVacio()) {
        // Selecciona un vértice de la lista.
        Vertice<E> v = seleccionarVertice(porVisitar);
        // Elimina el vértice seleccionado de la lista de pendientes.
        porVisitar.suprimir(v);
        // Selecciona un color válido para el vértice.
        String color = seleccionarColor(colores, mapaColoreado, v, g);
        // Asigna el color al vértice en el mapa.
        mapaColoreado.put(v, color);
    }

    // Devuelve el mapa con los colores asignados.
    return mapaColoreado;
}


private static <E> String seleccionarColor(String[] colores, Map<Vertice<E>, String> mapaColoreado, Vertice<E> v, Grafo<E, Integer> g) {
    // Inicializa un arreglo de booleanos para verificar colores válidos.
    boolean[] coloresValidos = new boolean[colores.length];
    for (int i = 0; i < coloresValidos.length; i++) {
        coloresValidos[i] = true; // Todos los colores son válidos inicialmente.
    }

    // Itera sobre todos los arcos del grafo.
    Iterator<Arco<E, Integer>> it = g.arcos();
    while (it.hasNext()) {
        Arco<E, Integer> arco = it.next();
        // Si el arco tiene al vértice actual como destino.
        if (arco.getDestino().equals(v)) {
            // Obtiene el color del vértice de origen del arco.
            String colorNoValido = mapaColoreado.get(arco.getOrigen());
            // Si el vértice de origen ya tiene un color asignado.
            if (colorNoValido != null) {
                // Marca el color como no válido.
                for (int i = 0; i < colores.length; i++) {
                    if (colores[i].equals(colorNoValido)) {
                        coloresValidos[i] = false;
                    }
                }
            }
        }
    }

    // Busca el primer color válido.
    int pos = 0;
    while (pos < colores.length && !coloresValidos[pos]) {
        pos++;
    }

    // Devuelve el color seleccionado o null si no hay colores válidos.
    return (pos < colores.length) ? colores[pos] : null;
}

private static <E> Vertice<E> seleccionarVertice(Lista<Vertice<E>> porVisitar) {
    return porVisitar.recuperar();
}

}