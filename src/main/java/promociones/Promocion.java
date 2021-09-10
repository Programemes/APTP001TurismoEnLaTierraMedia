package promociones;

import general.Atraccion;

public abstract class Promocion {
    private final Atraccion atraccionA;
    private final Atraccion atraccionB;

    public Promocion(Atraccion atraccionA, Atraccion atraccionB) {
        this.atraccionA = atraccionA;
        this.atraccionB = atraccionB;
    }

    public Atraccion getAtraccionA() {
        return atraccionA;
    }

    public Atraccion getAtraccionB() {
        return atraccionB;
    }
}
