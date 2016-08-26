package tsp;

import java.io.*;
import java.util.ArrayList;

public class MasCercano {
  private int distancias [][];
  private int n;
  private ArrayList<Integer> solucion;
  private int recorrido;
  
  public MasCercano (String file) throws FileNotFoundException, IOException {
    String datos = new String ();
    BufferedReader reader = new BufferedReader (new FileReader (file));
    
    if(reader.ready())
      datos = reader.readLine();
    
    n = Integer.parseInt(datos);
    distancias = new int [n][n];
    solucion = new ArrayList<Integer> ();
    recorrido = 0;
    
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
  
  public void resolver () {
    ArrayList<Integer> sinVisitar = new ArrayList<Integer> ();
    
    for(int i = 1; i < n ; i++)
      sinVisitar.add(i);
    
    int cercano = Integer.MAX_VALUE;
    int visitado = 0;
    solucion.add(visitado);
    
    for(int k = 1; k < n; k++) {
      int i = visitado;
      for(int j = 0; j < n; j++) {
        if((distancias[i][j] < cercano) && (sinVisitar.contains(new Integer(j)))) {
          cercano = distancias[i][j];
          visitado = j;
        }
      }
      sinVisitar.remove(new Integer(visitado));
      solucion.add(visitado);
      recorrido += distancias[i][visitado];
      cercano = Integer.MAX_VALUE;
    }
    
    solucion.add(new Integer(0));
    recorrido += distancias[solucion.get(solucion.size() - 2)][0];
  }
  
  public void mostrarSolucion () {
    System.out.println("Distancia minima recorrida: " + recorrido);
    
    System.out.print("Ruta: ");
    for(int i = 0; i < n; i++) {
      System.out.print((solucion.get(i) + 1) + " -> ");
    }
    System.out.print((solucion.get(n) + 1) + "\n");
  }
}
