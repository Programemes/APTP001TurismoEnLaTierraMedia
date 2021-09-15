package usuarios;

 import sugeribles.Sugerencia;
 import sugeribles.Atraccion;
 import Enumeradores.ENUMTIPO;

 import java.util.*;

public class Usuario {
    private final int DNI;
    private final ENUMTIPO tipoFavorito;
    private final int dineroInicial;
    private int dineroDisponible;
    private final double tiempoInicial;
    private double tiempoDisponible;
    private final List<Atraccion> atracciones;
    private int costoTotal;

    public Usuario(int DNI, ENUMTIPO tipoFavorito, int dineroInicial, double tiempoDisponible) {
        this.DNI = DNI;
        this.tipoFavorito = tipoFavorito;
        this.dineroInicial = dineroInicial;
        this.dineroDisponible = dineroInicial;
        this.tiempoInicial = tiempoDisponible;
        this.tiempoDisponible = tiempoDisponible;
        costoTotal = 0;
        atracciones = new ArrayList<>();
    }

    public int getDNI() {
        return DNI;
    }

    public ENUMTIPO getTipoFavorito() {
        return tipoFavorito;
    }

    public int getDineroDisponible() {
        return dineroDisponible;
    }

    public double getTiempoDisponible() {
        return tiempoDisponible;
    }

    @Override
    public String toString() {
        return "Usuario" + "tipoFavorito=" + tipoFavorito + ", presupuesto=" + dineroDisponible + ", tiempoDisponible=" + tiempoDisponible + '}';
    }


    public void recibirSugerencia(Sugerencia sugerencia) {
        Scanner in = new Scanner(System.in);
        System.out.println("\n\nSe ha hecho la siguiente sugerencia:\n");
        if (sugerencia.getPromocion() == null){
            System.out.println("La atraccion:\n" + sugerencia.getAtracciones()[0]
                    + "\nTotal a pagar: " + sugerencia.getTotal() + " monedas");
        }else{
            StringBuilder concatenado = new StringBuilder();
            for (Atraccion atraccion : sugerencia.getAtracciones()) {
                concatenado.append(atraccion).append("\n");
            }
            System.out.println("La promocion es de tipo: " + sugerencia.getPromocion().getClass().toString().split("\\.")[2]
                    + "\nLas atracciones:\n" + concatenado
                    + "\nTotal a pagar: " + sugerencia.getTotal() + " monedas."
                    + "\nSe ahorra " + calcularDifereciaMonedas(sugerencia) + " monedas.");
        }
        String aceptar;
        do {
            System.out.println("\n¿Desea aceptar la sugerencia?\nIngrese \"Si\" para aceptar, de lo contrario ingrese \"No\" para rechazarlo.");
            aceptar = in.nextLine();
        }while (!aceptar.equalsIgnoreCase("Si") && !aceptar.equalsIgnoreCase("No"));
        if (aceptar.equalsIgnoreCase("Si")){
            dineroDisponible -= sugerencia.getTotal();
            for (Atraccion atraccion : sugerencia.getAtracciones()) {
                tiempoDisponible -= atraccion.getTiempo();
                atraccion.ocuparUnLugar();
            }
            costoTotal += sugerencia.getTotal();
            Collections.addAll(atracciones, sugerencia.getAtracciones());
        }
    }

    private int calcularDifereciaMonedas(Sugerencia sugerencia){
        int valorOriginal = 0;
        for (int i = 0; i < sugerencia.getAtracciones().length; i++) {
            valorOriginal += sugerencia.getAtracciones()[i].getCosto();
        }
        return valorOriginal - sugerencia.getTotal();
    }

    public List<Atraccion> getAtracciones() {
        return atracciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return DNI == usuario.DNI;
    }

    @Override
    public int hashCode() {
        return Objects.hash(DNI);
    }

    public int getCostoTotal() {
        return costoTotal;
    }

    public int getDineroInicial() {
        return dineroInicial;
    }

    public double getTiempoInicial() {
        return tiempoInicial;
    }
}
