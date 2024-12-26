import grafo.*;
import java.util.Iterator;
import java.util.Map;

import lista.*;
import mapa.*;


public class a9 {


    public static <E> Map<Vertice<E>, Integer> dijkstra (Grafo<E,Integer> g, Vertice<E> v){
        Lista<Vertice<E>> porVisitar = new ListaEnlazada();
        Iterator<Vertice<E>> it = g.vertices();
        Map<Vertice<E>,Integer> mapa = new HashMap<>();

        while(it.hashNext()){
            Vertice<E> vertice=it.next();
            porVisitar.insertarPrincipio(vertice);
            mapa.insertar(vertice, Integer.MAX_VALUE);
        }
        mapa.insertar(v, 0);

        while(!porVisitar.esVacio()){
            Vertice<E> vertice = seleccionar(mapa, porVisitar);
            porVisitar.suprimir(vertice);
            Iterator<Arco<E,Integer>> arcos = g.arcos();
            while(arcos.hashNext()){
                Arco<E,Integer> arco=arcos.next();
                if(arco.getOrigen().equals(vertice) && porVisitar.contiene(arco.getDestino())){
                    int distanciaActual = mapa.get(vertice) + arco.getEtiqueta();
                    if(distanciaActual >= 0 && distanciaActual <= mapa.get(arco.getDestino())){
                        mapa.insertar(arco.getDestino(),mapa.get(vertice) + arco.getEtiqueta());

                    }
                }
            }
        }
        return mapa;
    }

    private static <E> Vertice<E> seleccionar(Map<Vertice<E>, Integer> mapa, Lista<Vetice<E>> porVisitar){
        Vertice<E> toRet = null;
        Vetice<E> vertice;
        int tam=Integer.MAX_VALUE;
        Iterator<Vertice<E>> it =mapa.getClaves();

        while(it.hasNext()){
            vertice=it.next();
            if(porVisitar.contiene(vertice) && mapa.get(vertice) <= tam){
                tam = mapa.get(vertice);
                toRet = vertice;
            }
        }
        return toRet;
    }



    public static <E> Map<Vertice<E>, String> colorearMapa(Grafo<E,Integer> g,String[] colores){
        Lista<Vertice<E> porVisitar = new ListaEnlazada<>();
        Map<Vertice<E>, String> mapaColoreado = new HashMap<>();
        Iterator<Vertice<E>> it =g.vertices();
        while(it.hashNext()){
            porVisitar.insertarFinal(it.next());
        }

        while(!porVisitar.esVacio()){
            Vertice<E> v = seleccionarVertice(porVisitar);
            porVisitar.suprimir(v);
            String color = seleccionarColor(colores, mapaColoreado, v , g);
            mapaColoreado.insertar(v, color);
        }

        return mapaColoreado;
    }


    private static <E> Vertice<E> seleccionarVertice(Lista<Vertice<E>> porViistar){
        return porVisitar.recuperar();
    }

    private static <E> String seleccionarColor (String[] colores, Map<Vertice){
        //Los predecesores no pueden tener el mismo color ni estar en porVisitar
        boolean [] coloresValidos = new boolean [4];
        for (int i = 0; i < coloresValidos.length; i++) {
            coloresValidos[i] = true;
        }

        Iterator<Arco<E,Integer>> it = g.arcos();
        while(it.hasNext()){
            Arco<E,Integer> arco = it.next();
            String colorNoValido = mapaColoreado.get(arco.getOrigen());
            if(arco.getDestomp().equals(v) && colorNoValido != null){
                for (int i = 0; i < coloresValidos.length; i++) {
                    if(colores[i].equals(colorNoValido)){
                        coloresValidos[i]=false;
                    }
                }
            }
        }

        int pos=0;
        while(pos<4 && !coloresValidos[pos]){
            pos++;
        }
        return (pos <4 ) ? colroes[pos] : null;
    }



    public static Map<Integer, Integer> darCambio(int importeDevolver,Map<Integer, Integer> mapCanti){
        Map<Integer,Integer> solucion = new HashMap<>();
        boolean haySolucion = true;
        while(importeDevolver > 0 && haySolucion){
            int billete = seleccioanrBillete(importeDevolver,mapCanti);
            if(billete!=-1){
                solucion.insertar(billete, 0); //LA primera vez no estará insertado
                while(importeDevolver >= billete && mapCanti.get(billete) > 0){
                    importeDevolver -= billete;
                    mapCanti.insertar(billete, mapCanti.get(billete) -1);
                    solucion.insertar(billete, solucion.get(billete) +1);
                }
                if(mapCanti.get(billete) == 0){
                    mapCanti.eliminar(billete);
                }else{
                    haySolucion = false;
                }
            }
        }
        return (haySolucion) ? solucion : new HashMap<>();
    }



    private static int seleccionarBillete(int importeDevolver, Map<Integer,Integer> mapCanti) {
        int toRet = -1;
        Iterator<Integer> it = mapCanti.getClaves();
        int actual;

        while(it.hasNext()){
            actual = it.next();
            if(actual > toRet && actual <= importeDevolver){
                toRet = actual;
            }
        }
        return toRet;
    }

    public static Lista<String> llenarCDVoraz(int capacidadMax, Map<String, Integer> espacioProgramas){
        Lista<String> CD = new ListaEnlazada<>();
        boolean quedanProgramas = true;
        while(capacidadMax > 0 && quedanProgramas){
            String programa = seleccionarPrograma(capacidadMax,espacioProgramas);
            if(programa != null){
                capacidadMax -= espacioProgramas.get(programa);
                CD.insertarFinal(programa);
                espacioProgramas.eliminar(programa);
            }else{
                quedanProgramas = false;
            }
        }
        return CD;
    }


    private static String seleccionarPrograma(int capacidadMax, Map<String,Integer> espacioProgramas){
        String toRet = null;
        Iterator<String> it = espacioProgramas.getClaves();
        while(it.hasNext()){
            String actual = it.next();
            if(espacioProgramas.get(actual) <= capacidadMax && (toRet == null ||espacioProgramas.get(toRet) < espacioProgramas.get(actual))){
                toRet = actual;
            }
        }
        return toRet;
    }


    public static Map<String, Integer> llenarMochila(int volumenMaximo, Map<String,Integer> cantidades, Map<StringInteger> volumenes){

        Map<String,Integer> solucion= new HashMap<>();
        boolean quedanOpciones = true;
        while(volumenMaximo > 0 && quedanOpciones){
            String objeto = seleccionarMochila(volumenMaximo,volumenes);
            if(objeto != null){
                solucion.insertar(objeto,0);
                while(volumenMaximo >= volumenes.get(objeto) && cantidades.get(objeto) >0){
                    volumenMaximo -= volumenes.get(objeto);
                    solucion.insertar(objeto,solucion.get(objeto)+1);
                    cantidades.insertar(objeto,cantidades.get(objeto)-1);
                }
                if(cantidades.get(objeto)==0){
                    cantidades.eliminar(objeto);
                    volumenes.eliminar(objeto);
                }else{
                    quedanOpciones = false;
                }
            }
        }
        return solucion;
    }
}


    private static String seleccionarMochila(int volumenMaximo,Map<String,Integer> volumenes){
        String toRet = null;
        Iterator<String> it = volumenes.getClaves();
        while(it.hasNext()){
            String actual = it.next();
            int volumen = volumenes.get(actual);
            if(volumen <= volumenMaximo && (toRet == null || volumen > volumenes.get(toRet))){
                toRet = actual;
            }
        }
        return toRet;
    }

    public static <T> Map<Vertice<String>,String> horarioExamens(Grafo<String,T> g, String[] diasSemana) {
        Map<Vertice<String>,String> solucion= new HashMap();
        Iterator<Vertice<String>> asignaturas= g.vertices();
        Vertice<String> asignatura;
        while(asignaturas.hashNext){
            asignatura= asignaturas.next();
            int posicion = seleccionarDia(g.adyacentes(asignatura), diasSemana, solucion);
            if(posicion != -1){
                solucion.insertar(asignatura, diasSemana[posicion]);
            }else{
                return new HashMap<Vertice<String>,String>();
            }
        }
        return solucion;
    }




    private static int seleccionarDia(Iterator<Vertice<String>> ady, String[] diasSemana, Map<Vertice<String>,String> solucion){
        
        boolean[] diaInvalido = new boolean[diasSemana.length];
        while(ady.hashNext){
            Vertice<String> asignatura = ady.next();
            String dia = solucion.get(asignatura);
            if(dia != null){
                int i = 0;
                boolean encontrado = false;
                while(i< diasSemana.length && !encontrado){
                    if(diasSemana[i].equals(dia)){
                        encontrado=true;
                        diaInvalido[i]=true;
                    }
                    i++;
                }
            }
            int toRet = 0;
        }

    }


}
