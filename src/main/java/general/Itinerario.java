package general;

public class Itinerario{
    private final Atraccion[] atracciones;
    private final int total;

    public Itinerario (Atraccion [] atracciones, int total){
        this.atracciones = atracciones;
        this.total = total;
    }

    public Atraccion[] getAtracciones() {
        return atracciones;
    }

    public int getTotal() {
        return total;
    }
}