package promociones;

import general.Atraccion;

public class PromocionAbsoluta implements Promocion{
    private final int precioFinal;
    private final Atraccion atraccionA;
    private final Atraccion atraccionB;

    public PromocionAbsoluta(Atraccion atraccionA, Atraccion atraccionB, int precioFinal) {
        this.atraccionA = atraccionA;
        this.atraccionB = atraccionB;
        this.precioFinal = precioFinal;
    }

    public Atraccion getAtraccionA() {
        return atraccionA;
    }

    public Atraccion getAtraccionB() {
        return atraccionB;
    }

    @Override
    public Object retornarPromocion() {
        return precioFinal;
    }
}
