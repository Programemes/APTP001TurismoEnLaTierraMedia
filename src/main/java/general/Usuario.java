package general;

 import java.util.Arrays;
import java.util.Scanner;

public class Usuario {
    private final ENUMTIPO tipoFavorito;
    private int presupuesto;
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


    public boolean recibirItinerario(Itinerario itinerario) {
        System.out.println(Arrays.toString(itinerario.getAtracciones()));
        Scanner in = new Scanner(System.in);
        String decision;
        do {
            System.out.println("Si acepta el itinerario escriba \"Si\" de lo contrario escriba \"No\".");
            decision = in.nextLine();
        }while (!decision.equals("Si") && !decision.equals("No"));
        if (decision.equals("Si")) {
            System.out.println(itinerario.getTotal());
            this.presupuesto -= itinerario.getTotal();
            return true;
        }
        return false;
    }
}
