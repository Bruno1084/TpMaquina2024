package Logica.Clases;

import java.util.Date;

public class Empleado extends Persona {
    private int nroLegajo;
    private Date fechaIngreso;

    public Empleado(int dni, String nombre, String direccion, long telefono, int nroLegajo, Date fechaIngreso){
        super(dni, nombre, direccion, telefono);
        this.nroLegajo = nroLegajo;
        this.fechaIngreso = fechaIngreso;
    }

    //Getters y setters
    public int getNroLegajo() {
        return nroLegajo;
    }
    public void setNroLegajo(int nroLegajo) {
        this.nroLegajo = nroLegajo;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
