package Logica.Clases;

public class Cliente extends Persona {
    public static int id = 0;
    private int idCliente;

    public Cliente(long dni, String nombre, String direccion, long telefono){
        super(dni, nombre, direccion, telefono);
        this.idCliente = Cliente.id++;
    }
    public Cliente(){
        this.idCliente = Cliente.id++;
    }

    //Getters y setters
    public int getId() {
        return idCliente;
    }
}
