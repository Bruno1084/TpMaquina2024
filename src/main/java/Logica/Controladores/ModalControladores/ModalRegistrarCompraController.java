package Logica.Controladores.ModalControladores;
import Logica.Clases.Compra;
import Logica.Clases.DetalleCompra;
import Logica.Clases.DetalleVenta;
import Logica.Clases.Venta;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;


public class ModalRegistrarCompraController {
    @FXML
    private TableView<DetalleCompra> tableDetalleCompra;
    @FXML
    private TableColumn<DetalleCompra, Integer> columnIdMaterial;
    @FXML
    private TableColumn<DetalleCompra, Integer> columnCantidad;
    @FXML
    private TableColumn<DetalleCompra, Long> columnPeso;
    @FXML
    private TableColumn<DetalleCompra, Long> columnPrecio;
    @FXML
    private TableColumn columnEliminar;

    // Venta object inputs
    @FXML
    private TextField inputIdProveedor;
    @FXML
    private TextField inputFechaVenta;
    @FXML
    private TextField inputDespachado;

    // DetalleCompa object inputs
    @FXML
    private TextField inputIdMaterial;
    @FXML
    private TextField inputCantidad;
    @FXML
    private TextField inputPeso;
    @FXML
    private TextField inputPrecio;
    @FXML
    private TextField inputIdVenta;

    private ArrayList<Venta> listaVentas = new ArrayList<>();
    private ArrayList<Compra> listaCompras = new ArrayList<>();


    public void initialize(){
        columnIdMaterial.setCellValueFactory(new PropertyValueFactory<>("IdMaterial"));
        columnCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        columnPeso.setCellValueFactory(new PropertyValueFactory<>("Peso"));
        columnPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
        columnEliminar.setCellFactory(param -> {
            final TableCell<DetalleVenta, String> cell = new TableCell<>(){

                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);

                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else{
                        final Button eliminarButton = new Button("Eliminar");
                        eliminarButton.setOnAction(event -> {
                            DetalleVenta detalleVenta = getTableView().getItems().get(getIndex());
                            //handleBtnEliminar(detalleVenta);
                            handleBtnEliminar();
                        });
                        setGraphic(eliminarButton);
                        setText(null);
                    }
                }
            };
            return cell;
        });

    }

    public void handleBtnEliminar(){
        System.out.println("Btn eliminar row on table pressed");
    }


 }
