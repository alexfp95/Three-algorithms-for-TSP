package tsp;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ProgDin {
  private int distancias [][];
  private int n;
  private HashMap<ArrayList<Integer>, Integer> hash;
  private int indexMin;
  private int solucion[];
  private int minimo;
  
  public ProgDin (String file) throws FileNotFoundException, IOException {
    indexMin = 0;
    minimo = 0;
    
    
    String datos = new String ();
    BufferedReader reader = new BufferedReader (new FileReader (file));
    
    if(reader.ready())
      datos = reader.readLine();
    
    n = Integer.parseInt(datos);
    distancias = new int [n][n];
    hash = new HashMap<ArrayList<Integer>, Integer> ();
    solucion = new int [n];
    
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
  
  void mostrarMatrizDistancias () {
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        System.out.print(distancias[i][j] + "\t");
      }
      System.out.println();
    }
  }
  
  void resolver () {
    int distancia = 0;
    Boolean actualizado = false;
    
    /*for(int elem = 1; elem < n; elem++) {
      ArrayList<Integer> S = new ArrayList<Integer> ();
      for(int s = 1; s < n; s++) {
        S.add(s);
        for(int i = 1; i < n; i++) {
          if(S.contains(new Integer(i))) {
            S.remove((new Integer(i)));
            actualizado = true;
          }
          g(i, S);
          if(actualizado) {
            S.add(new Integer(i));
            actualizado = false;
          }
        }
      }
    }*/
       
    ArrayList<Integer> C = new ArrayList<Integer> ();
    
    for(int i = 1; i < n; i++)
      C.add(i);
    
    distancia = g(0, C);
  }
  
  int g (int num, ArrayList<Integer> S) {
    
    if(S.isEmpty()) {
      return distancias[num][0];
    }
    else {
      ArrayList<Integer> aux = new ArrayList<Integer> ();
      aux.add(num);
      aux.addAll(S);
      
      if(hash.containsKey(aux)) {
        return hash.get(aux);
      }
      
      else {
        if(S.size() > 1) {
          ArrayList<Integer> minimizar = new ArrayList<Integer> ();
          for(int i = 0; i < S.size(); i++) {
            ArrayList<Integer> copia = copiarArray(S);
            int num2 = copia.get(i);
            copia.remove(new Integer(num2));
            minimizar.add(distancias[num][num2] + g(num2, copia));
          }
          minimo = min(minimizar);
          hash.put(aux, minimo);
          
          /*System.out.print(aux.get(0) + " - ");
          for(int i = 1; i < aux.size(); i++)
            System.out.print(aux.get(i) + " ");
          System.out.println();*/
           
         // solucion[num] = S.get(indexMin);
            
          return minimo;
        }
        if(S.size() == 1) {
          int num2 = S.get(0);
          S.remove(new Integer(num2));
          minimo = distancias[num][num2] + g(num2, S);
          hash.put(aux, minimo);
          
          /*System.out.print(aux.get(0) + " , ");
          for(int i = 1; i < aux.size(); i++)
            System.out.print(aux.get(i) + " ");
          System.out.println();*/
                    
          return minimo;
        }
      }
    }
    return minimo;
  }
  
  public int min (ArrayList<Integer> numeros) {
    int minimo = Integer.MAX_VALUE;
    for(int i = 0; i < numeros.size(); i++) {
      if(minimo > numeros.get(i)) {
        minimo = numeros.get(i);
        indexMin = i;
      }
    }
    return minimo;
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
    System.out.println("Distancia minima recorrida: " + minimo);
    
    /*System.out.print("Ruta óptima: 1 -> ");
    for(int i = 0; i < (n - 1); i++) {
      System.out.print((solucion[i] + 1) + " -> ");
    }
    System.out.print("1 \n"); */
  }

  
}
