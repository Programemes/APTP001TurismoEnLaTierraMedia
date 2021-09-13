package sistema;

import sugeribles.atracciones.Atraccion;
import sugeribles.atracciones.ENUMTIPO;
import sugeribles.Sugerencia;
import sugeribles.promociones.IPromocion;
import sugeribles.promociones.PromocionAbsoluta;
import sugeribles.promociones.PromocionAxB;
import sugeribles.promociones.PromocionPorcentaje;
import usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private final List<Usuario> usuarios;
    private final Atraccion[] atracciones;
    private final IPromocion[] promociones;

    public Sistema(List<Usuario> usuarios, Atraccion[] atracciones, IPromocion[] promociones) {
        this.usuarios = usuarios;
        this.atracciones = atracciones;
        this.promociones = promociones;
    }

    public void sugerir(Usuario user) {
        sugerirPromociones(user);
        sugerirAtracciones(user);
    }

    public void sugerirPromociones(Usuario user){
        List<Atraccion> listaAtracciones = new ArrayList<>();
        int valorTemporal;

        for (IPromocion promocion : promociones) {
            int costoActual = 0;
            double tiempoActual = 0;

            if (promocion.getAtraccionA().getTipo() == user.getTipoFavorito()) {
                if (promocion.getClass() == PromocionAbsoluta.class) {
                    if ((costoActual + (int) promocion.retornarPromocion() <= user.getPresupuesto()) && (tiempoActual + promocion.getAtraccionA().getTiempo() + promocion.getAtraccionB().getTiempo() <= user.getTiempoDisponible())) {
                        listaAtracciones.add(promocion.getAtraccionA());
                        listaAtracciones.add(promocion.getAtraccionB());
                        costoActual += (int) promocion.retornarPromocion();
                        user.recibirSugerencia(new Sugerencia(listaAtracciones.toArray(new Atraccion[0]), promocion, costoActual));
                    }
                }
                if (promocion.getClass() == PromocionPorcentaje.class) {
                    valorTemporal = promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto() - (promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto() * (int) promocion.retornarPromocion());
                    if ((costoActual + valorTemporal <= user.getPresupuesto()) && (tiempoActual + promocion.getAtraccionA().getTiempo() + promocion.getAtraccionB().getTiempo() <= user.getTiempoDisponible())) {
                        listaAtracciones.add(promocion.getAtraccionA());
                        listaAtracciones.add(promocion.getAtraccionB());
                        costoActual += valorTemporal;
                        user.recibirSugerencia(new Sugerencia(listaAtracciones.toArray(new Atraccion[0]), promocion, costoActual));
                    }
                }
                if (promocion.getClass() == PromocionAxB.class) {
                    valorTemporal = promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto();
                    Atraccion atraccionAux = (Atraccion) promocion.retornarPromocion();
                    if ((costoActual + valorTemporal <= user.getPresupuesto()) && (tiempoActual + promocion.getAtraccionA().getTiempo() + promocion.getAtraccionB().getTiempo() + atraccionAux.getTiempo() <= user.getTiempoDisponible())) {
                        listaAtracciones.add(promocion.getAtraccionA());
                        listaAtracciones.add(promocion.getAtraccionB());
                        listaAtracciones.add(atraccionAux);
                        costoActual += valorTemporal;
                        user.recibirSugerencia(new Sugerencia(listaAtracciones.toArray(new Atraccion[0]), promocion, costoActual));
                    }
                }
            }
        }

        //Ofrezco promociones pero que no son del mismo tipo.

        for (IPromocion promocion : promociones) {
            int costoActual = 0;
            double tiempoActual = 0;

            if (promocion.getAtraccionA().getTipo() != user.getTipoFavorito()) {
                if (promocion.getClass() == PromocionAbsoluta.class) {
                    if ((costoActual + (int) promocion.retornarPromocion() <= user.getPresupuesto()) && (tiempoActual + promocion.getAtraccionA().getTiempo() + promocion.getAtraccionB().getTiempo() <= user.getTiempoDisponible())) {
                        listaAtracciones.add(promocion.getAtraccionA());
                        listaAtracciones.add(promocion.getAtraccionB());
                        costoActual += (int) promocion.retornarPromocion();
                        user.recibirSugerencia(new Sugerencia(listaAtracciones.toArray(new Atraccion[0]), promocion, costoActual));
                    }
                }
                if (promocion.getClass() == PromocionPorcentaje.class) {
                    valorTemporal = promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto() - (promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto() * (int) promocion.retornarPromocion());
                    if ((costoActual + valorTemporal <= user.getPresupuesto()) && (tiempoActual + promocion.getAtraccionA().getTiempo() + promocion.getAtraccionB().getTiempo() <= user.getTiempoDisponible())) {
                        listaAtracciones.add(promocion.getAtraccionA());
                        listaAtracciones.add(promocion.getAtraccionB());
                        costoActual += valorTemporal;
                        user.recibirSugerencia(new Sugerencia(listaAtracciones.toArray(new Atraccion[0]), promocion, costoActual));
                    }
                }
                if (promocion.getClass() == PromocionAxB.class) {
                    valorTemporal = promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto();
                    Atraccion atraccionAux = (Atraccion) promocion.retornarPromocion();
                    if ((costoActual + valorTemporal <= user.getPresupuesto()) && (tiempoActual + promocion.getAtraccionA().getTiempo() + promocion.getAtraccionB().getTiempo() + atraccionAux.getTiempo() <= user.getTiempoDisponible())) {
                        listaAtracciones.add(promocion.getAtraccionA());
                        listaAtracciones.add(promocion.getAtraccionB());
                        listaAtracciones.add(atraccionAux);
                        costoActual += valorTemporal;
                        user.recibirSugerencia(new Sugerencia(listaAtracciones.toArray(new Atraccion[0]), promocion, costoActual));
                    }
                }
            }
        }
    }

    private void sugerirAtracciones (Usuario user){
        int costoActual = 0;
        double tiempoActual = 0;
        Atraccion[] atraccionesTipo = obtenerAtraccionesTipo(user.getTipoFavorito());

        for (Atraccion atraccion : atraccionesTipo) {
            if ((atraccion != null) && (costoActual + atraccion.getCosto() <= user.getPresupuesto()) && (tiempoActual + atraccion.getTiempo() <= user.getTiempoDisponible())) {
                user.recibirSugerencia(new Sugerencia(new Atraccion[]{atraccion}, null, atraccion.getCosto()));
            }
        }

        //Incluyo las atracciones que no son del mismo tipo
        for (Atraccion atraccion : atracciones) {
            if ((atraccion != null) && (costoActual + atraccion.getCosto() <= user.getPresupuesto()) && (tiempoActual + atraccion.getTiempo() <= user.getTiempoDisponible())) {
                user.recibirSugerencia(new Sugerencia(new Atraccion[]{atraccion}, null, atraccion.getCosto()));
            }
        }
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

    public void removerUsuario(Usuario user){
        usuarios.remove(user);
    }

    private Atraccion[] obtenerAtraccionesTipo(ENUMTIPO tipo){
        List<Atraccion> listaAtraccion = new ArrayList<>();
        for (Atraccion atraccion : atracciones) {
            if (atraccion.getTipo() == tipo){
                listaAtraccion.add(atraccion);
            }
        }
        return listaAtraccion.toArray(new Atraccion[0]);
    }

    public static void main(String[] args) throws Exception {
        List<Usuario> usuarioList = new ArrayList<>();
        usuarioList.add(new Usuario(ENUMTIPO.PAISAJE, 7, 10));
        usuarioList.add(new Usuario(ENUMTIPO.ADVENTURA, 9, 15));
        usuarioList.add(new Usuario(ENUMTIPO.PAISAJE, 100, 123));
        Atraccion[] atracciones = new Atraccion[5];
        atracciones[0] = new Atraccion("Atraccion prueba 1", 12, 3, 5, ENUMTIPO.PAISAJE);
        atracciones[1] = new Atraccion("Atraccion prueba 2", 23, 8, 3, ENUMTIPO.PAISAJE);
        atracciones[2] = new Atraccion("Atraccion prueba 3", 5, 6, 63, ENUMTIPO.ADVENTURA);
        atracciones[3] = new Atraccion("Atraccion prueba 4", 12, 1, 6, ENUMTIPO.ADVENTURA);
        atracciones[4] = new Atraccion("Atraccion prueba 5", 7, 4, 32, ENUMTIPO.PAISAJE);
        IPromocion[] promociones = new IPromocion[2];
        promociones[0] = new PromocionAbsoluta(atracciones[2], atracciones[3], 6);
        promociones[1] = new PromocionAxB(atracciones[0], atracciones[1], atracciones[4]);
        Sistema sistema = new Sistema(usuarioList, atracciones, promociones);
        int i = 0;
        for (Usuario usuario : usuarioList) {
            System.out.println("""
                    ----------------------------------------------------
                    ----------------------------------------------------
                    ----------------------------------------------------""");
            System.out.println("Usuario numero " + i++);
            sistema.sugerir(usuario);
        }
    }
}
