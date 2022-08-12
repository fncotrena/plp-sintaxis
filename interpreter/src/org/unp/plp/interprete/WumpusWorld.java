package org.unp.plp.interprete;

import java.util.HashMap;

class Celda{
private  int filas, columna;
 public Celda (int filas, int columna){
 this.filas= filas;
 this.columna=columna;
 }
}
public class WumpusWorld {
//private   int filas, columna,wumpusFila,wumpusColumna,goldFila,goldColumna;

    private Celda mundoPos, wumpuPos, heroPos,godlPos;
    private HashMap<Object, Object> map= new HashMap<Object, Object>();

public void create(int filas,int columna){
    mundoPos= new Celda(filas,columna);
    map.put("world",mundoPos);
	System.out.println("mundo creado correctamente  "+ mundoPos);
}
public void print(){
    System.out.println("funcion de print wumpu world");
}

public void putGold(int filas,int columna){
    godlPos= new Celda(filas,columna);
    map.put("gold",godlPos);
    System.out.println("oro  creado correctamente  "+ godlPos);


}
    public void PutWumpu(int filas,int columna){
        wumpuPos = new Celda(filas,columna);
        map.put("wumpu", wumpuPos);
        System.out.println("Wumpus  creado correctamente  "+ wumpuPos);


}

    public void PutHero(int filas,int columna){
        heroPos = new Celda(filas,columna);
        map.put("hero", heroPos);
        System.out.println("hero  creado correctamente  "+ heroPos);

    }

}
