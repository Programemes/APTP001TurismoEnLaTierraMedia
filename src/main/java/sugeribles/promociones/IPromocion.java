package sugeribles.promociones;

import sugeribles.atracciones.Atraccion;

public interface IPromocion {
    Object retornarPromocion();
    Atraccion getAtraccionA();
    Atraccion getAtraccionB();
}
