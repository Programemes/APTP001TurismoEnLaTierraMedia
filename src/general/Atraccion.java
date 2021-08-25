package general;

public class Atraccion {
    private String nombre;
    private int costo;
    private double tiempo;
    private int cupo;
    private ENUMTIPO tipo;

    public Atraccion(String nombre, int costo, double tiempo, int cupo, ENUMTIPO tipo) {
        this.nombre = nombre;
        this.costo = costo;
        this.tiempo = tiempo;
        this.cupo = cupo;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public ENUMTIPO getTipo() {
        return tipo;
    }

    public void setTipo(ENUMTIPO tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Atraccion[" + "nombre: " + nombre + ", costo: " + costo + ", tiempo: " + tiempo + ", cupo: " + cupo + ", tipo: " + tipo + ']';
    }
}
