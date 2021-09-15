package comparadores;

import sugeribles.Atraccion;

import java.util.Comparator;

public class ComparadorAtraccion implements Comparator<Atraccion> {

    @Override
    public int compare(Atraccion atraccionA, Atraccion atraccionB) {
        return (int) Math.signum(atraccionB.compareTo(atraccionA));
    }
}
