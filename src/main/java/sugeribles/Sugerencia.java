package sugeribles;

import sugeribles.atracciones.Atraccion;
import sugeribles.promociones.IPromocion;

import java.util.Arrays;

public class Sugerencia {
    private final Atraccion[] atracciones;
    private final IPromocion promocion;
    private final int total;

    public Sugerencia(Atraccion[] atracciones, IPromocion promocion, int total) {
        this.atracciones = atracciones;
        this.promocion = promocion;
        this.total = total;
    }

    public Atraccion[] getAtracciones() {
        return atracciones;
    }

    public IPromocion getPromocion() {
        return promocion;
    }
    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Itinerario [" +
                "atracciones: " + Arrays.toString(atracciones) +
                ", promocion :" + promocion +
                ", total: " + total +
                ']';
    }
}