package BusinessEntity;

import java.sql.Date;

public class Descuento {
    private int idDescuento;
    private float porcentaje;
    private Date fechaInicio;
    private Date fechaFin;

    public Descuento() {}

    public Descuento(int idDescuento, float porcentaje, Date fechaInicio, Date fechaFin) {
        this.idDescuento = idDescuento;
        this.porcentaje = porcentaje;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(int idDescuento) {
        this.idDescuento = idDescuento;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}

