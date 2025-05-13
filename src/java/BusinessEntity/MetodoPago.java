package BusinessEntity;

public class MetodoPago {
    private int idMetodo;
    private String nombreMetodo;

    public MetodoPago() {}

    public MetodoPago(int idMetodo, String nombreMetodo) {
        this.idMetodo = idMetodo;
        this.nombreMetodo = nombreMetodo;
    }

    public int getIdMetodo() {
        return idMetodo;
    }

    public void setIdMetodo(int idMetodo) {
        this.idMetodo = idMetodo;
    }

    public String getNombreMetodo() {
        return nombreMetodo;
    }

    public void setNombreMetodo(String nombreMetodo) {
        this.nombreMetodo = nombreMetodo;
    }
}

