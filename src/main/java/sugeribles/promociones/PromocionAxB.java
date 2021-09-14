package sugeribles.promociones;

import sugeribles.atracciones.Atraccion;

public class PromocionAxB extends Promocion implements IPromocion {
    private final Atraccion atraccionRegalo;

    public PromocionAxB(Atraccion atraccionA, Atraccion atraccionB, Atraccion atraccionRegalo) {
        super(atraccionA, atraccionB);
        this.atraccionRegalo = atraccionRegalo;
    }

    @Override
    public Object retornarPromocion() {
        return atraccionRegalo;
    }
}
