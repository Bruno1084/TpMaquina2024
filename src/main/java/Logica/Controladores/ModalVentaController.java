package Logica.Controladores;
import Logica.Clases.Venta;


public class ModalVentaController {
    VentaController controladorPadre;
    Venta venta;

    public void initialize(){

    }


    public void loadVenta(Venta venta){
        setVenta(venta);


    }

    public void setControladorPadre(VentaController ventaController){
        this.controladorPadre = ventaController;
    }

    public void setVenta(Venta venta){
        this.venta = venta;
    }
}
