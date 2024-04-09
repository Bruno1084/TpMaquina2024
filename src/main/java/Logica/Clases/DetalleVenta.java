package Logica.Clases;

public class DetalleVenta {
    private Material material;
    private int cantidad;
    private long peso;
    private long precio;

    public DetalleVenta(Material material, int cantidad, long peso, long precio) {
        this.material = material;
        this.cantidad = cantidad;
        this.peso = peso;
        this.precio = precio;
    }
    public DetalleVenta(){}

    //Getters y setters
    public Material getMaterial() {
        return material;
    }
    public void setMaterial(Material material) {
        this.material = material;
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


