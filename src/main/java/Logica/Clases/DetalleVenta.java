package Logica.Clases;


public class DetalleVenta {
    private int idDetalleVenta;
    private int idMaterial;
    private int cantidad;
    private long peso;
    private long precio;
    private int idVenta;

    public DetalleVenta(int idDetalleVenta, int idMaterial, int cantidad, long peso, long precio, int idVenta) {
        this.idDetalleVenta = idDetalleVenta;
        this.idMaterial = idMaterial;
        this.cantidad = cantidad;
        this.peso = peso;
        this.precio = precio;
        this.idVenta = idVenta;
    }
    public DetalleVenta(){}


    //Getters y setters
    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }
    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public int getIdMaterial() {
        return idMaterial;
    }
    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public long getPeso() {
        return peso;
    }
    public void setPeso(long peso) {
        this.peso = peso;
    }

    public long getPrecio() {
        return precio;
    }
    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public int getIdVenta() {
        return idVenta;
    }
    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    @Override
    public String toString(){
        return idDetalleVenta + ", " + idMaterial + ", " +
                cantidad + ", " + peso + ", " + precio + ", " + idVenta + "\n";
    }
}


