package Logica.Controladores;
import Logica.Clases.DetalleVenta;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


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


    public void initialize(){

    }
}
