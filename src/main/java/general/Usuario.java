package general;

public class Usuario {
    private final ENUMTIPO tipoFavorito;
    private final int presupuesto;
    private final double tiempoDisponible;

    public Usuario(ENUMTIPO tipoFavorito, int presupuesto, double tiempoDisponible) {
        this.tipoFavorito = tipoFavorito;
        this.presupuesto = presupuesto;
        this.tiempoDisponible = tiempoDisponible;
    }

    public ENUMTIPO getTipoFavorito() {
        return tipoFavorito;
    }

    public int getPresupuesto() {
        return presupuesto;
    }

    public double getTiempoDisponible() {
        return tiempoDisponible;
    }

    @Override
    public String toString() {
        return "Usuario" + "tipoFavorito=" + tipoFavorito + ", presupuesto=" + presupuesto + ", tiempoDisponible=" + tiempoDisponible + '}';
    }


}
