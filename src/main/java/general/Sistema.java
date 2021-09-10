package general;

import comparadores.ComparadorAtraccion;
import promociones.IPromocion;
import promociones.PromocionAbsoluta;
import promociones.PromocionAxB;
import promociones.PromocionPorcentaje;

import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private List<Usuario> usuarios;
    private final Atraccion[] atracciones;
    private final IPromocion[] promociones;

    public Sistema(List<Usuario> usuarios, Atraccion[] atracciones, IPromocion[] promociones) {
        this.usuarios = usuarios;
        this.atracciones = atracciones;
        this.promociones = promociones;
    }

    public void crearItinerario (Usuario user) {
        List<Atraccion> atraccionesTipo = obtenerAtraccionesTipo(user.getTipoFavorito());
        List<Atraccion> atraccionesItinerario = new ArrayList<>();
        List<Atraccion> atraccionesIgnoradas = new ArrayList<>();
        int costoActual;
        int valorTemporal;
        atraccionesTipo.sort(new ComparadorAtraccion());

        do {
            if(!atraccionesItinerario.isEmpty()) {
                atraccionesItinerario.remove(0);
            }
            costoActual = 0;
            double tiempoActual = 0;
            for (IPromocion promocion : promociones) {
                if (promocion == null) {
                    break;
                }
                if (promocion.getAtraccionA().getTipo() == user.getTipoFavorito()) {
                    if (promocion.getClass() == PromocionAbsoluta.class) {
                        if ((costoActual + (int) promocion.retornarPromocion() <= user.getPresupuesto()) && (tiempoActual + promocion.getAtraccionA().getTiempo() + promocion.getAtraccionB().getTiempo() <= user.getTiempoDisponible())) {
                            atraccionesItinerario.add(promocion.getAtraccionA());
                            atraccionesItinerario.add(promocion.getAtraccionB());
                            costoActual += (int) promocion.retornarPromocion();
                            atraccionesIgnoradas.add(promocion.getAtraccionA());
                            atraccionesIgnoradas.add(promocion.getAtraccionB());
                        }
                    }
                    if (promocion.getClass() == PromocionPorcentaje.class) {
                        valorTemporal = promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto() - (promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto() * (int) promocion.retornarPromocion());
                        if ((costoActual + valorTemporal <= user.getPresupuesto()) && (tiempoActual + promocion.getAtraccionA().getTiempo() + promocion.getAtraccionB().getTiempo() <= user.getTiempoDisponible())) {
                            atraccionesItinerario.add(promocion.getAtraccionA());
                            atraccionesItinerario.add(promocion.getAtraccionB());
                            costoActual += valorTemporal;
                            atraccionesIgnoradas.add(promocion.getAtraccionA());
                            atraccionesIgnoradas.add(promocion.getAtraccionB());
                        }
                    }
                    if (promocion.getClass() == PromocionAxB.class) {
                        valorTemporal = promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto();
                        Atraccion atraccionAux = (Atraccion) promocion.retornarPromocion();
                        if ((costoActual + valorTemporal <= user.getPresupuesto()) && (tiempoActual + promocion.getAtraccionA().getTiempo() + promocion.getAtraccionB().getTiempo() + atraccionAux.getTiempo() <= user.getTiempoDisponible())) {
                            atraccionesItinerario.add(promocion.getAtraccionA());
                            atraccionesItinerario.add(promocion.getAtraccionB());
                            atraccionesItinerario.add(atraccionAux);
                            costoActual += valorTemporal;
                            atraccionesIgnoradas.add(promocion.getAtraccionA());
                            atraccionesIgnoradas.add(promocion.getAtraccionB());
                            atraccionesIgnoradas.add(atraccionAux);
                        }
                    }
                }
            }
            for (Atraccion atraccion : atraccionesTipo) {
                if ((atraccion != null) && (costoActual + atraccion.getCosto() <= user.getPresupuesto()) && (tiempoActual + atraccion.getTiempo() <= user.getTiempoDisponible())) {
                    if(!atraccionesIgnoradas.contains(atraccion)) {
                        atraccionesItinerario.add(atraccion);
                        costoActual += atraccion.getCosto();
                    }
                }
            }

            //Incluyo las atracciones que no son del mismo tipo
            atraccionesIgnoradas.clear();
            atraccionesIgnoradas.addAll(atraccionesItinerario);
            for (Atraccion atraccion : atracciones) {
                if ((atraccion != null) && (costoActual + atraccion.getCosto() <= user.getPresupuesto()) && (tiempoActual + atraccion.getTiempo() <= user.getTiempoDisponible())) {
                    if(!atraccionesIgnoradas.contains(atraccion)) {
                        atraccionesItinerario.add(atraccion);
                    }
                }
            }
        }while (!user.recibirItinerario(new Itinerario(atraccionesItinerario.toArray(new Atraccion[0]), costoActual)));
    }

    public void agregarUsuario(Usuario user) throws Exception {
        if (existeUsuario(user)){
            throw new Exception("Este usuario ya existe");
        }
        usuarios.add(user);
    }

    private boolean existeUsuario(Usuario user){
        return usuarios.contains(user);
    }

    public void removeUsuario(Usuario user){
        usuarios.remove(user);
    }

    private List<Atraccion> obtenerAtraccionesTipo(ENUMTIPO tipo){
        List<Atraccion> listaAtraccion = new ArrayList<>();
        for (Atraccion atraccion : atracciones) {
            if (atraccion.getTipo() == tipo){
                listaAtraccion.add(atraccion);
            }
        }
        return listaAtraccion;
    }
}
