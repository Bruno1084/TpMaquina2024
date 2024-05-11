package Logica.Clases;

public class DetalleVenta {
    private int idMaterial;
    private int cantidad;
    private long peso;
    private long precio;

    public DetalleVenta(int idMaterial, int cantidad, long peso, long precio) {
        this.idMaterial = idMaterial;
        this.cantidad = cantidad;
        this.peso = peso;
        this.precio = precio;
    }
    public DetalleVenta(){}


    //Getters y setters
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
}


