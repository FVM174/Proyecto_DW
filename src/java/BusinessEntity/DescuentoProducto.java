package BusinessEntity;

public class DescuentoProducto {
    private int idProducto;
    private int idDescuento;

    public DescuentoProducto() {}

    public DescuentoProducto(int idProducto, int idDescuento) {
        this.idProducto = idProducto;
        this.idDescuento = idDescuento;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(int idDescuento) {
        this.idDescuento = idDescuento;
    }
}

