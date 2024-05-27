package Logica.Controladores.ModalControladores;
import Logica.Clases.DetalleVenta;
import Logica.Clases.Venta;
import Utils.FileDetalleVentaManager;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;


public class ModalVentaController {
    @FXML
    TableView<DetalleVenta> tableDetalleVentas;
    @FXML
    TableColumn<Integer, DetalleVenta> columnID;
    @FXML
    TableColumn<Integer, DetalleVenta> columnIdCantidad;
    @FXML
    TableColumn<Long, DetalleVenta> columnPeso;
    @FXML
    TableColumn<Long, DetalleVenta> columnPrecio;

    private Venta venta;
    private final FileDetalleVentaManager fileDetalleVentaManager = new FileDetalleVentaManager("src/main/java/Permanencia/", "DetalleVenta.txt");
    private ArrayList<DetalleVenta> listaDetalleVentas = new ArrayList<>();
    private static int indice = 0;


    public void initialize(){
        columnID.setCellValueFactory(new PropertyValueFactory<>("IdDetalleVenta"));
        columnIdCantidad.setCellValueFactory(new PropertyValueFactory<>("IdMaterial"));
        columnPeso.setCellValueFactory(new PropertyValueFactory<>("Peso"));
        columnPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
    }





    public void setVenta(Venta venta){
        this.venta = venta;
    }
}
