package promociones;

import general.Atraccion;

public class PromocionPorcentaje implements Promocion{
    private final double porcentaje;
    private final Atraccion atraccionA;
    private final Atraccion atraccionB;

    public PromocionPorcentaje(Atraccion atraccionA, Atraccion atraccionB, double porcentaje) {
        this.atraccionA = atraccionA;
        this.atraccionB = atraccionB;
        this.porcentaje = porcentaje;
    }

    public Atraccion getAtraccionA() {
        return atraccionA;
    }

    public Atraccion getAtraccionB() {
        return atraccionB;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    @Override
    public Object retornarPromocion() {
        return porcentaje;
    }
}
