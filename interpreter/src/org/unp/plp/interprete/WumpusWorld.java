package org.unp.plp.interprete;

import java.util.*;

class Celda {
    private int filas, columna;

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public Celda(int filas, int columna) {
        this.filas = filas;
        this.columna = columna;
    }
}

public class WumpusWorld {
//private   int filas, columna,wumpusFila,wumpusColumna,goldFila,goldColumna;

    private Celda mundoPos, wumpuPos, heroPos, godlPos;
    private HashMap<Object, Object> map = new HashMap<Object, Object>();
    java.util.Set<Celda> miHashSet = new HashSet<>();
    List<Celda> pitList = new ArrayList<>(miHashSet);

    public void create(int filas, int columna) {
        mundoPos = new Celda(filas, columna);
        map.put("world", mundoPos);
        System.out.println("mundo creado correctamente  " + mundoPos);
    }

    public void print() {
        System.out.println(map.toString());
        System.out.println(pitList.toString());
    }

    public void putGold(int filas, int columna) {
        if (mundoPos.getColumna() >= columna && mundoPos.getFilas() >= filas) {
            godlPos = new Celda(filas, columna);
            map.put("gold", godlPos);
            System.out.println("oro  creado correctamente  " + godlPos);
        } else System.out.println("error en el tamaño ingresado");


    }

    public void putWumpus(int filas, int columna) {
        if (mundoPos.getColumna() >= columna && mundoPos.getFilas() >= filas) {

            wumpuPos = new Celda(filas, columna);
            map.put("wumpu", wumpuPos);
            System.out.println("Wumpus  creado correctamente  " + wumpuPos);
        } else System.out.println("error en el tamaño ingresado");

    }

    public void putHero(int filas, int columna) {
        if (mundoPos.getColumna() >= columna && mundoPos.getFilas() >= filas) {

            heroPos = new Celda(filas, columna);
            map.put("hero", heroPos);
            System.out.println("hero  creado correctamente  " + heroPos);
        } else System.out.println("error en el tamaño ingresado");

    }

    public void put(String accion,int filas, int columna) {
        switch (accion) {
            case "hero" -> this.putHero(filas, columna);
            case "gold" -> this.putGold(filas, columna);
            case "wumpus" -> this.putWumpus(filas, columna);
            case "pit" -> this.putPit(filas, columna);
            default -> {
            }
        }

    }

    public void putPit(int filas, int columna) {
        if (mundoPos.getColumna() >= columna && mundoPos.getFilas() >= filas) {
            Celda c = new Celda(filas, columna);
            pitList.add(c);
        } else System.out.println("error en el tamaño ingresado");

    }


}
