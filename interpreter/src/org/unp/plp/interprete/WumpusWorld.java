package org.unp.plp.interprete;
class celda{
private  int filas, columna;
 public celda (int filas, int columna){
 this.filas= filas;
 this.columna=columna;
 }
}
public class WumpusWorld {
private   int filas, columna,wumpusFila,wumpusColumna,goldFila,goldColumna;
private Map<String,celda> ;
public void create(int filas,int columna){
	System.out.println("mundo creado f"+filas+" c"+columna);
               
}
public void print(){
	System.out.println("funcion de print wumpu world");
}

public void putGold(int filas,int columna){
	System.out.println("funcion de put wumpu world");
}

}
