package tsp;

import java.io.*;
import java.util.ArrayList;

public class BusqEx {
  private int distancias [][];
  private int n;
  ArrayList<ArrayList<Integer>> posibilidades;
  ArrayList<Integer> solucion;
  int recorrido;
  
  public BusqEx (String file) throws FileNotFoundException, IOException {
    posibilidades = new ArrayList<ArrayList<Integer>> ();
    solucion = new ArrayList<Integer> ();
    recorrido = 0;
    
    String datos = new String ();
    BufferedReader reader = new BufferedReader (new FileReader (file));
    
    if(reader.ready())
      datos = reader.readLine();
    
    n = Integer.parseInt(datos);
    distancias = new int [n][n];
    
    for(int i = 0; i < n; i++) {
      distancias[i][i] = 0;
    }
    
    while(reader.ready()) {
      datos = reader.readLine();
      String [] valores = datos.split("\\s+");
      
      if (valores.length != 3) {
        System.out.print("Error en el fichero de entrada.");
        System.exit(0);
      }
      
      distancias[Integer.parseInt(valores[0]) - 1][Integer.parseInt(valores[1]) - 1] = Integer.parseInt(valores[2]);
      distancias[Integer.parseInt(valores[1]) - 1][Integer.parseInt(valores[0]) - 1] = Integer.parseInt(valores[2]); 
    }
    
    reader.close();
  }
    
  void resolver () {
    ArrayList<Integer> inicio = new ArrayList<Integer> ();
    inicio.add(0);
    combinaciones(inicio);
    
    recorrido = Integer.MAX_VALUE;
    
    for(int k = 0; k < posibilidades.size(); k++) {
      ArrayList<Integer> opcion = posibilidades.get(k);
      int aux = 0;
      for(int i = 0; i < n; i++) {
        aux += distancias[opcion.get(i)][opcion.get(i+1)];
      }
      if(recorrido > aux) {
        recorrido = aux;
        solucion = opcion;
      }
    }
  }
  
  void combinaciones (ArrayList<Integer> aux) {
    if(aux.size() == n) {
      aux.add(0);
      posibilidades.add(aux);
    } 
    else {
      for(int i = 0; i < n; i++) {
        if(!aux.contains(i)) {
          aux.add(i);
          combinaciones(copiarArray(aux));
          aux.remove(new Integer(i));
        }
      }
    }
  }
  
  public ArrayList<Integer> copiarArray (ArrayList<Integer> original) {
    ArrayList<Integer> copia = new ArrayList<Integer> ();
    for(int i = 0; i < original.size(); i++) {
      int elemento = original.get(i);
      copia.add(new Integer(elemento));
    }
    return copia;
  }
  
  public void mostrarSolucion () {
    System.out.println("Distancia minima recorrida: " + recorrido);
    
    System.out.print("Ruta óptima: ");
    for(int i = 0; i < n; i++) {
      System.out.print((solucion.get(i) + 1) + " -> ");
    }
    System.out.print((solucion.get(n) + 1) + "\n");
  }
  
}
