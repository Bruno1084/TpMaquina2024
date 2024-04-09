package Logica.Clases;

public class Material {
    private String nombre;
    private String descripcion;
    private String tipoMedida;
    private int stock;
    private float precioCompra;
    private float precioVenta;

    public Material(String nombre, String descripcion, String tipoMedida, int stock, float precioCompra, float precioVenta){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoMedida = tipoMedida;
        this.stock = stock;
        this.precioCompra = precioCompra;
        this.precioVenta = precioCompra;
    }

    //Getters y setters
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

    public float getPrecioCompra() {
        return precioCompra;
    }
    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }
    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }
}
