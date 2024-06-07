package Logica.Clases;

public class Cliente extends Persona {
    private boolean alta;

    public Cliente(int id, long dni, String nombre, String direccion, long telefono, boolean alta){
        super(id, dni, nombre, direccion, telefono);
        this.alta = alta;
    }
    public Cliente(){}

    public boolean getAlta() {
        return alta;
    }
    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    @Override
    public String toString(){
        return getId() + ", " + getDni() + ", " + getNombre() + ", " + getDireccion() + ", " + getTelefono() + ", " + alta + "\n";
    }
}
