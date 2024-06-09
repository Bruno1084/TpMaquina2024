package Logica.Clases;

public class Cliente extends Persona {
    private boolean alta;
    private int cantCompras;

    public Cliente(int id, long dni, String nombre, String direccion, long telefono, boolean alta, int cantCompras){
        super(id, dni, nombre, direccion, telefono);
        this.alta = alta;
        this.cantCompras = cantCompras;
    }
    public Cliente(){}

    public boolean getAlta() {
        return alta;
    }
    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public int getCantCompras() {
        return cantCompras;
    }
    public void setCantCompras(int cantCompras) {
        this.cantCompras = cantCompras;
    }

    @Override
    public String toString(){
        return getId() + ", " + getDni() + ", " + getNombre() + ", " + getDireccion() + ", " + getTelefono() + ", " + alta + ", " + cantCompras + "\n";
    }
}
