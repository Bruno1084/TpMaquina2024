package Logica.Clases;


public class Empleado extends Persona {
    private int nroLegajo;
    private String fechaIngreso;
    private boolean alta;


    public Empleado(int id, long dni, String nombre, String direccion, long telefono, int nroLegajo, String fechaIngreso, boolean alta){
        super(id, dni, nombre, direccion, telefono);
        this.nroLegajo = nroLegajo;
        this.fechaIngreso = fechaIngreso;
        this.alta = alta;
    }
    public Empleado(){}


    //Getters y setters
    public int getNroLegajo() {
        return nroLegajo;
    }
    public void setNroLegajo(int nroLegajo) {
        this.nroLegajo = nroLegajo;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }
    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public boolean getAlta() {
        return alta;
    }
    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    @Override
    public String toString(){
        return getId() + ", " + getDni() + ", " + getNombre() + ", " + getDireccion()
                + ", " + getTelefono() + ", " + nroLegajo + ", " + fechaIngreso + ", " + alta + "\n";
    }
}
