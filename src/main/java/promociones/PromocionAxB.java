package promociones;

import general.Atraccion;

public class PromocionAxB implements Promocion{
    private final Atraccion atraccionA;
    private final Atraccion atraccionB;
    private final Atraccion atraccionRegalo;

    public PromocionAxB(Atraccion atraccionA, Atraccion atraccionB, Atraccion atraccionRegalo) {
        this.atraccionA = atraccionA;
        this.atraccionB = atraccionB;
        this.atraccionRegalo = atraccionRegalo;
    }

    public Atraccion getAtraccionA() {
        return atraccionA;
    }

    public Atraccion getAtraccionB() {
        return atraccionB;
    }

    @Override
    public Object retornarPromocion() {
        return atraccionRegalo;
    }
}
