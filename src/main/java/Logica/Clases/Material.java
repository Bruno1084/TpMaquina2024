package Logica.Clases;

public class Material {
    private int id;
    private String nombre;
    private String descripcion;
    private String tipoMedida;
    private int stock;
    private long precioCompra;
    private long precioVenta;

    public Material(int id, String nombre, String descripcion, String tipoMedida, int stock, long precioCompra, long precioVenta){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoMedida = tipoMedida;
        this.stock = stock;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }
    public Material(){}


    //Getters y setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoMedida() {
        return tipoMedida;
    }
    public void setTipoMedida(String tipoMedida) {
        this.tipoMedida = tipoMedida;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public long getPrecioCompra() {
        return precioCompra;
    }
    public void setPrecioCompra(long precioCompra) {
        this.precioCompra = precioCompra;
    }

    public long getPrecioVenta() {
        return precioVenta;
    }
    public void setPrecioVenta(long precioVenta) {
        this.precioVenta = precioVenta;
    }

    @Override
    public String toString(){
        return id + ", " + nombre + ", " + descripcion + ", " + tipoMedida +
                ", " + stock + ", " + precioCompra + ", " + precioVenta + "\n";
    }
}
