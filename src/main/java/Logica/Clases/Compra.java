package Logica.Clases;


public class Compra {
    private String fecha;
    private Cliente cliente;
    private String pagada;
    private Empleado empleado;

    public Compra(String fecha, Cliente cliente, String pagada, Empleado empleado) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.pagada = pagada;
        this.empleado = empleado;
    }
    public Compra(){}


    //Getters y setters
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getPagada() {
        return pagada;
    }
    public void setPagada(String pagada) {
        this.pagada = pagada;
    }

    public Empleado getEmpleado() {
        return empleado;
    }
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
