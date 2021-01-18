package co.edu.unipiloto.stopwatch;

public class Vuelta {

    //Atributos
    private int numVuelta;
    private String tiempo;

    //Constructor


    public Vuelta(int numVuelta, String tiempo) {
        this.numVuelta = numVuelta;
        this.tiempo = tiempo;
    }

    //metodos funcionales


    //getters
    public int getNumVuelta() {return numVuelta;}
    public String getTiempo() {return tiempo;}

    //Setters
    public void setNumVuelta(int numVuelta) {this.numVuelta = numVuelta;}
    public void setTiempo(String tiempo) {this.tiempo = tiempo;}

}
