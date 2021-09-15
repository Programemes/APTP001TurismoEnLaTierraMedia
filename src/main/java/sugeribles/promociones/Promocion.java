package sugeribles.promociones;

import sugeribles.Atraccion;

import java.util.Objects;

public abstract class Promocion {
    private final Atraccion atraccionA;
    private final Atraccion atraccionB;

    public Promocion(Atraccion atraccionA, Atraccion atraccionB) throws Exception {
        if (atraccionA.getTipo() != atraccionB.getTipo()){
            throw new Exception("Las atracciones no son del mismo tipo.");
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Promocion promocion = (Promocion) o;
        return Objects.equals(atraccionA, promocion.atraccionA) && Objects.equals(atraccionB, promocion.atraccionB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(atraccionA, atraccionB);
    }
}
