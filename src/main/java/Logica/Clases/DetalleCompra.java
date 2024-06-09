package Logica.Clases;

public class DetalleCompra {
    private int idMaterial;
    private long cantidad;
    private long peso;
    private float precio;
    private int idCompra;

    public DetalleCompra(int idMaterial, long cantidad, long peso, float precio, int idCompra){
        this.idMaterial = idMaterial;
        this.cantidad = cantidad;
        this.peso = peso;
        this.precio = precio;
        this.idCompra = idCompra;
    }

    public int getIdMaterial() {
        return idMaterial;
    }
    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public long getCantidad() {
        return cantidad;
    }
    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public long getPeso() {
        return peso;
    }
    public void setPeso(long peso) {
        this.peso = peso;
    }

    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getIdCompra() {
        return idCompra;
    }
    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }
}
