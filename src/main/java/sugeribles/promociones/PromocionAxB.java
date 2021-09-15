package sugeribles.promociones;

import sugeribles.Atraccion;

public class PromocionAxB extends Promocion implements IPromocion {
    private final Atraccion atraccionRegalo;

    public PromocionAxB(Atraccion atraccionA, Atraccion atraccionB, Atraccion atraccionRegalo) throws Exception {
        super(atraccionA, atraccionB);
        if (atraccionRegalo.getTipo() != atraccionB.getTipo()){
            throw new Exception("Las atracciones no son del mismo tipo.");
        }
        this.atraccionRegalo = atraccionRegalo;
    }

    @Override
    public Object retornarPromocion() {
        return atraccionRegalo;
    }
}
