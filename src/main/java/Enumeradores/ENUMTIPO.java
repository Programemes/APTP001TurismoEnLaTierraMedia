package Enumeradores;

public enum ENUMTIPO {
    ADVENTURA("Adventura"),
    PAISAJE("Paisaje"),
    DEGUSTACION("Degustacion");

    private final String tipo;

    ENUMTIPO(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
