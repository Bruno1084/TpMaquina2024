package Logica.Clases;

import Logica.Clases.Persona;

public class Proveedor extends Persona {
    public static int id = 0;
    private int idProveedor;
    private long cuil;
    private String ciudad;

    public Proveedor(long dni, String nombre, String direccion, long telefono, long cuil, String ciudad){
        super(dni, nombre, direccion, telefono);
        this.cuil = cuil;
        this.ciudad = ciudad;
        this.idProveedor = Proveedor.id++;
    }
    public Proveedor(){
        this.idProveedor = Proveedor.id++;
    }

    //Getters y setters
    public int getId() {
        return idProveedor;
    }

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
