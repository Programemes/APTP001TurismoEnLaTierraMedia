package sugeribles.promociones;

import sugeribles.atracciones.Atraccion;

public class PromocionPorcentaje extends Promocion implements IPromocion {
    private final double porcentaje;

    public PromocionPorcentaje(Atraccion atraccionA, Atraccion atraccionB, double porcentaje) throws Exception {
        super(atraccionA, atraccionB);
        if (porcentaje >= 100){
            throw new Exception("La promocion da un costo igual o superior a la suma del valor de las atracciones");
        }
        this.porcentaje = porcentaje;
    }

    @Override
    public Object retornarPromocion() {
        return porcentaje;
    }
}
