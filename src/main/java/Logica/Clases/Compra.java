package Logica.Clases;


public class Compra {
    private int id;
    private String fecha;
    private int idCliente;
    private String pagada;
    private int idEmpleado;

    public Compra(int id, String fecha, int idCliente, String pagada, int idEmpleado) {
        this.id = id;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.pagada = pagada;
        this.idEmpleado = idEmpleado;
    }
    public Compra(){}


    //Getters y setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getPagada() {
        return pagada;
    }
    public void setPagada(String pagada) {
        this.pagada = pagada;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}
