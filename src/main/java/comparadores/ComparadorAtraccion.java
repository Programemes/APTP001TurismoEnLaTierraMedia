package comparadores;

import general.Atraccion;

import java.util.Comparator;

public class ComparadorAtraccion implements Comparator<Atraccion> {

    @Override
    public int compare(Atraccion o1, Atraccion o2) {
        return (int) Math.signum(o1.compareTo(o2));
    }
}
