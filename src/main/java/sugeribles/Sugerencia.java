package sugeribles;

import sugeribles.promociones.IPromocion;

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
}