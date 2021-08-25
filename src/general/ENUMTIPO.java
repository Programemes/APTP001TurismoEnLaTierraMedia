package general;

public enum ENUMTIPO {
    ADVENTURA("Adventura"),
    PAISAJE("Paisaje"),
    DEGUSTACION("Degustacion");

	private final String tipo;

    ENUMTIPO(String tipo) {
        this.tipo = tipo;
    }

	public String getTipo() {
		return tipo;
	}
}
