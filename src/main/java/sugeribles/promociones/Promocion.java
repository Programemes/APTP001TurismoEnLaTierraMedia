package sugeribles.promociones;

import sugeribles.Atraccion;

import java.util.Objects;

public abstract class Promocion implements Comparable<IPromocion>, IPromocion{
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

    @Override
    public int compareTo(IPromocion promocion){
        int totalPromocionA = 0;
        if (this.getClass() == PromocionAbsoluta.class) {
            totalPromocionA = (int) this.retornarPromocion();
        }

        if (this.getClass() == PromocionPorcentaje.class) {
            totalPromocionA = (int) (this.getAtraccionA().getCosto() + this.getAtraccionB().getCosto() - ((this.getAtraccionA().getCosto() + this.getAtraccionB().getCosto()) * ((double)this.retornarPromocion() / 100)));
        }

        if (this.getClass() == PromocionAxB.class) {
            totalPromocionA = this.getAtraccionA().getCosto() + this.getAtraccionB().getCosto();
        }


        int totalPromocionB = 0;
        if (promocion.getClass() == PromocionAbsoluta.class) {
            totalPromocionB = (int) promocion.retornarPromocion();
        }

        if (promocion.getClass() == PromocionPorcentaje.class) {
            totalPromocionB = (int) (promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto() - ((promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto()) * ((double)promocion.retornarPromocion() / 100)));
        }

        if (promocion.getClass() == PromocionAxB.class) {
            totalPromocionB = promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto();
        }

        if (totalPromocionA == totalPromocionB){
            return Double.compare(this.getAtraccionA().getTiempo() + this.getAtraccionB().getTiempo(), promocion.getAtraccionA().getTiempo() + promocion.getAtraccionB().getTiempo());
        }
        return Integer.compare(totalPromocionA, totalPromocionB);
    }
}
