package Logica.Clases;

public abstract class Persona {
    private long dni;
    private String nombre;
    private String direccion;
    private long telefono;

    public Persona(long dni, String nombre, String direccion, long telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    public Persona(){}


    //Getters y setters
    public long getDni() {
        return dni;
    }
    public void setDni(long dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getTelefono() {
        return telefono;
    }
    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }
}
