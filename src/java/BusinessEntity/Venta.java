package BusinessEntity;

import java.sql.Date;

public class Venta {
    private int idVenta;
    private Date fechaVenta;
    private float totalVenta;
    private int idCliente;
    private int idEmpleado;
    private int idMetodo;

    public Venta() {}

    public Venta(int idVenta, Date fechaVenta, float totalVenta, int idCliente, int idEmpleado, int idMetodo) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.totalVenta = totalVenta;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.idMetodo = idMetodo;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public float getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(float totalVenta) {
        this.totalVenta = totalVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdMetodo() {
        return idMetodo;
    }

    public void setIdMetodo(int idMetodo) {
        this.idMetodo = idMetodo;
    }
}

