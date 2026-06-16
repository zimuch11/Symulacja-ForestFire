package com.example.symulacja_fire;

public class ForestCell extends Cell {
    private int resources;
    private boolean onFire;
    private boolean burnt;


    public ForestCell(int resources) {
        this.resources = resources;
        this.onFire = false;
        this.burnt = false;
    }

    @Override
    public boolean flammable() {
        return true;
    }
    @Override
    public void ignite(){
        onFire=true;
    }
    @Override
    public boolean isOnFire(){
        return onFire;
    }

    /**<h1> burn <h1>
     * Metoda odpowiadajaca za proces palenia się komorki. Z każdym wywołaniem zmniejsza ilość resources o 1.
     */
    public void burn() {
        if (resources > 0) {
            resources--;
        }
    }

    /**<h1> isBurnt <h1>
     * Metoda sprawdzająca czy dana komórka jest spalona.
     * @return zwraca true, gdy jest spalona i false w przeciwnym wypadku
     */
    public boolean isBurnt(){
        if (resources<=0) return true;
        else return false;
    }
}