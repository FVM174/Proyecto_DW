package BusinessEntity;

import java.sql.Date;

public class Reclamo {
    private int idReclamo;
    private int idVenta;
    private Date fechaReclamo;
    private String descripcion;
    private String estado;

    public Reclamo() {}

    public Reclamo(int idReclamo, int idVenta, Date fechaReclamo, String descripcion, String estado) {
        this.idReclamo = idReclamo;
        this.idVenta = idVenta;
        this.fechaReclamo = fechaReclamo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(int idReclamo) {
        this.idReclamo = idReclamo;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFechaReclamo() {
        return fechaReclamo;
    }

    public void setFechaReclamo(Date fechaReclamo) {
        this.fechaReclamo = fechaReclamo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

