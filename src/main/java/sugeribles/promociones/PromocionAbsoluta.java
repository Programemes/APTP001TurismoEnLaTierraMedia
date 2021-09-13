package sugeribles.promociones;

import sugeribles.atracciones.Atraccion;

public class PromocionAbsoluta extends Promocion implements IPromocion {
    private final int precioFinal;

    public PromocionAbsoluta(Atraccion atraccionA, Atraccion atraccionB, int precioFinal) throws Exception {
        super(atraccionA, atraccionB);
        if (precioFinal >= atraccionA.getCosto() + atraccionB.getCosto()){
            throw new Exception("La promocion da un costo igual o superior a la suma del valor de las atracciones");
        }
        this.precioFinal = precioFinal;
    }

    @Override
    public Object retornarPromocion() {
        return precioFinal;
    }
}
