package Logica.Clases;

import Logica.Clases.DetalleVenta;
import Logica.Clases.Proveedor;

import java.util.Date;
import java.util.List;

public class Venta {
    public static int id = 0;
    private int idVenta;
    private Proveedor proveedor;
    private Date fechaVenta;
    List<DetalleVenta> materialesDetalle;
    private Date fecha;
    private boolean despachado;

    public Venta(Proveedor proveedor, Date fechaVenta, List<DetalleVenta> materialesDetalle, Date fecha, boolean despachado) {
        this.proveedor = proveedor;
        this.fechaVenta = fechaVenta;
        this.materialesDetalle = materialesDetalle;
        this.fecha = fecha;
        this.despachado = despachado;
        this.idVenta = Venta.id++;
    }
    public Venta(){
        this.idVenta = Venta.id++;
    }

    //Getters y setters
    public int getId() {
        return idVenta;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }
    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public List<DetalleVenta> getMaterialesDetalle() {
        return materialesDetalle;
    }
    public void setMaterialesDetalle(List<DetalleVenta> materialesDetalle) {
        this.materialesDetalle = materialesDetalle;
    }

    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isDespachado() {
        return despachado;
    }
    public void setDespachado(boolean despachado) {
        this.despachado = despachado;
    }
}
