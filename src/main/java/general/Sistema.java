package general;

import comparadores.ComparadorAtraccion;
import promociones.Promocion;
import promociones.PromocionAbsoluta;
import promociones.PromocionAxB;
import promociones.PromocionPorcentaje;

import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private List<Usuario> usuarios;
    private final Atraccion[] atracciones;
    private final Promocion[] promociones;

    public Sistema(List<Usuario> usuarios, Atraccion[] atracciones, Promocion[] promociones) {
        this.usuarios = usuarios;
        this.atracciones = atracciones;
        this.promociones = promociones;
    }

    public void crearItinerario (Usuario user) throws Exception {
        List<Atraccion> atraccionesTipo = obtenerAtraccionesTipo(user.getTipoFavorito());
        List<Atraccion> atraccionesItinerario = new ArrayList<>();
        int costoActual;
        int valorTemporal;
        atraccionesTipo.sort(new ComparadorAtraccion());

        do {
            if (atraccionesTipo.isEmpty()){
                throw new Exception("No hay atraccion que coincida con el gusto del usuario.");
            }
            costoActual = 0;
            double tiempoActual = 0;
            for (Promocion promocion : promociones) {
                if (promocion.getClass() == PromocionAbsoluta.class) {
                    if ((costoActual + (int) promocion.retornarPromocion() <= user.getPresupuesto()) && (tiempoActual + promocion.getAtraccionA().getTiempo() + promocion.getAtraccionB().getTiempo() <= user.getTiempoDisponible())) {
                        atraccionesItinerario.add(promocion.getAtraccionA());
                        atraccionesItinerario.add(promocion.getAtraccionB());
                        costoActual += (int) promocion.retornarPromocion();
                    }
                }
                if (promocion.getClass() == PromocionPorcentaje.class){
                    valorTemporal = promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto() - (promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto() * (int) promocion.retornarPromocion());
                    if ((costoActual + valorTemporal <= user.getPresupuesto()) && (tiempoActual + promocion.getAtraccionA().getTiempo() + promocion.getAtraccionB().getTiempo() <= user.getTiempoDisponible())) {
                        atraccionesItinerario.add(promocion.getAtraccionA());
                        atraccionesItinerario.add(promocion.getAtraccionB());
                        costoActual += valorTemporal;
                    }
                }
                if (promocion.getClass() == PromocionAxB.class){
                    valorTemporal = promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto();
                    Atraccion atraccionAux = (Atraccion) promocion.retornarPromocion();
                    if ((costoActual + valorTemporal <= user.getPresupuesto()) && (tiempoActual + promocion.getAtraccionA().getTiempo() + promocion.getAtraccionB().getTiempo() + atraccionAux.getTiempo() <= user.getTiempoDisponible())) {
                        atraccionesItinerario.add(promocion.getAtraccionA());
                        atraccionesItinerario.add(promocion.getAtraccionB());
                        atraccionesItinerario.add(atraccionAux);
                        costoActual += valorTemporal;
                    }
                }
            }
            for (Atraccion atraccion : atraccionesTipo) {
                if ((costoActual + atraccion.getCosto() <= user.getPresupuesto()) && (tiempoActual + atraccion.getTiempo() <= user.getTiempoDisponible())) {
                    atraccionesItinerario.add(atraccion);
                }
            }
            atraccionesItinerario.remove(0);
        }while (!user.recibirItinerario(new Itinerario((Atraccion[]) atraccionesItinerario.toArray(), costoActual)));
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
