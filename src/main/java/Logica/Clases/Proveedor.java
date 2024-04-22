package Logica.Clases;


public class Proveedor extends Persona {
    private long cuil;
    private String ciudad;

    public Proveedor(long dni, String nombre, String direccion, long telefono, long cuil, String ciudad){
        super(dni, nombre, direccion, telefono);
        this.cuil = cuil;
        this.ciudad = ciudad;
    }
    public Proveedor(){}


    //Getters y setters
    public long getCuil() {
        return cuil;
    }
    public void setCuil(long cuil) {
        this.cuil = cuil;
    }

    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
