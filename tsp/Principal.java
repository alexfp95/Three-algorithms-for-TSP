package tsp;

import java.io.*;
import java.util.Random;

public class Principal {

  public static void main(String[] args) throws FileNotFoundException, IOException {
    System.out.println("Resolución del Problema del Viajante de Comercio");
    System.out.println("Alexis Daniel Fuentes Pérez");
    
    Clock temporizador = new Clock ();
    
    BusqEx busqex = new BusqEx (args[0]);
    ProgDin progdin = new ProgDin (args[0]);
    MasCercano mcerc = new MasCercano (args[0]);
    
    System.out.println("\nMatriz de distancias: ");
    progdin.mostrarMatrizDistancias();
        
    System.out.println("\nBUSQUEDA EXHAUSTIVA");  
    temporizador.reset();
    temporizador.start();
    busqex.resolver();
    temporizador.stop();
    busqex.mostrarSolucion();
    System.out.println("Tiempo de ejecucion: " + (temporizador.elapsedTime() / Math.pow(10, 9)) + " segundos");
   
    System.out.println("\nPROGRAMACION DINAMICA");
    temporizador.reset();
    temporizador.start();
    progdin.resolver();
    temporizador.stop();
    progdin.mostrarSolucion();
    System.out.println("Tiempo de ejecucion: " + (temporizador.elapsedTime() / Math.pow(10, 9)) + " segundos");
    
    System.out.println("\nMAS CERCANO NO VISITADO");
    temporizador.reset();
    temporizador.start();
    mcerc.resolver();
    temporizador.stop();
    mcerc.mostrarSolucion();
    System.out.println("Tiempo de ejecucion: " + (temporizador.elapsedTime() / Math.pow(10, 9)) + " segundos");
  }

}
