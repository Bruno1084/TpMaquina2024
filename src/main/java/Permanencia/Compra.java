package Permanencia;

import java.util.Date;

public class Compra {
    private Date fecha;
    private Cliente cliente;
    private String pagada;
    private Empleado empleado;

    public Compra(Date fecha, Cliente cliente, String pagada, Empleado empleado) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.pagada = pagada;
        this.empleado = empleado;
    }
    public Compra(){}

    //Getters y setters
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
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
