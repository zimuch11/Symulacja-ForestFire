package com.example.symulacja_fire;

public abstract class Cell {
    protected int x;
    protected int y;

    /**<h1> flammable <h1>
     * Metoda okreslająca, czy dana komórka jest łatwopalna
     */
    public abstract boolean flammable();

    /**<h1> isOnFire <h1>
     * Metoda określająca, czy dana komórka pali się
     * @return zwraca true, jeżeli komórka się pali i false w przeciwnym wypadku
     */
    public boolean isOnFire(){
        return false;
    };

    /**<h1> ignite <h1>
     * Metoda odpowiadająca za zapalanie się komórki.
     */
    public void ignite() {
    }
}
