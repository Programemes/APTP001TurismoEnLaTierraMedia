package sugeribles.promociones;

import sugeribles.atracciones.Atraccion;

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

    @Override
    public String toString() {
        return this.getClass().toString() + "[" +
                "atraccionA: " + atraccionA +
                ", atraccionB: " + atraccionB +
                ']';
    }
}
