package Logica.Clases;
import java.util.List;


public class Venta {
    private int idVenta;
    private int idProveedor;
    private String fechaVenta;
    List<DetalleVenta> materialesDetalle;
    private boolean despachado;

    public Venta(int idVenta, int idProveedor, String fechaVenta, boolean despachado) {
        this.idVenta = idVenta;
        this.idProveedor = idProveedor;
        this.fechaVenta = fechaVenta;
        this.despachado = despachado;
    }
    public Venta(){}

    //Getters y setters
    public int getIdVenta() {
        return idVenta;
    }
    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdProveedor() {
        return idProveedor;
    }
    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }
    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public List<DetalleVenta> getMaterialesDetalle() {
        return materialesDetalle;
    }
    public void setMaterialesDetalle(List<DetalleVenta> materialesDetalle) {
        this.materialesDetalle = materialesDetalle;
    }

    public boolean isDespachado() {
        return despachado;
    }
    public void setDespachado(boolean despachado) {
        this.despachado = despachado;
    }
}