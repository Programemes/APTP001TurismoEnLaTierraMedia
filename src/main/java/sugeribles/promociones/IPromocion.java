package sugeribles.promociones;

import sugeribles.Atraccion;

public interface IPromocion extends Comparable<IPromocion> {
    Object retornarPromocion();
    Atraccion getAtraccionA();
    Atraccion getAtraccionB();
}
