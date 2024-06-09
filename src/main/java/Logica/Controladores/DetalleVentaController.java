package Logica.Controladores;
import Logica.Clases.DetalleVenta;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.ArrayList;


public class DetalleVentaController {
    @FXML
    TableView<DetalleVenta> tableDetalleVentas;
    @FXML
    TableColumn<Integer, DetalleVenta> columnID;
    @FXML
    TableColumn<Integer, DetalleVenta> columnIdMaterial;
    @FXML
    TableColumn<Integer, DetalleVenta> columnCantidad;
    @FXML
    TableColumn<Long, DetalleVenta> columnPeso;
    @FXML
    TableColumn<Long, DetalleVenta> columnPrecio;

    private ArrayList<DetalleVenta> listaDetallesVenta = new ArrayList<>();
    private static int indice = 0;


    public void initialize(){

    }
}
