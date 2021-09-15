package comparadores;

import sugeribles.promociones.IPromocion;

import java.util.Comparator;

public class ComparadorPromocion implements Comparator<IPromocion> {

    @Override
    public int compare(IPromocion promocionA, IPromocion promocionB) {
        return (int) Math.signum(promocionB.compareTo(promocionA));
    }
}
