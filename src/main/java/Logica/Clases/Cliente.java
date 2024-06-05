package Logica.Clases;

public class Cliente extends Persona {

    public Cliente(int id, long dni, String nombre, String direccion, long telefono){
        super(id, dni, nombre, direccion, telefono);
    }
    public Cliente(){}

    @Override
    public String toString(){
        return getId() + ", " + getDni() + ", " + getNombre() + ", " + getDireccion() + ", " + getTelefono() + "\n";
    }
}
