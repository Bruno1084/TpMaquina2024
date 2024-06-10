package Logica.Controladores.ModalControladores;
import Logica.Clases.DetalleVenta;
import Logica.Clases.Material;
import Logica.Clases.Venta;
import Utils.FileDetalleVentaManager;
import Utils.FileMaterialManager;
import Utils.FileVentaManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;


public class ModalRegistrarVentaController {
    // Table Columns
    @FXML
    private TableView<DetalleVenta> tableDetalleVentas;
    @FXML
    private TableColumn<DetalleVenta, Integer> columnIdMaterial;
    @FXML
    private TableColumn<DetalleVenta, Integer> columnCantidad;
    @FXML
    private TableColumn<DetalleVenta, Long> columnPeso;
    @FXML
    private TableColumn<DetalleVenta, Long> columnPrecio;
    @FXML
    private TableColumn columnEliminar;

    // Venta inputs
    @FXML
    private TextField inputIdMaterial;
    @FXML
    private TextField inputCantidad;
    @FXML
    private TextField inputPrecio;
    @FXML
    private TextField inputPeso;

    // Venta object inputs
    @FXML
    private TextField inputIdProveedor;
    @FXML
    private TextField inputProveedor;
    @FXML
    private TextField inputFechaVenta;
    @FXML
    private TextField inputDespachado;
    @FXML
    private TextField inputPrecioTotal;

    // Buttons
    @FXML
    Button btnAgregarMaterial;
    @FXML
    Button btnHacerCompra;

    // List
    private ArrayList<Venta> listaVentas = FileVentaManager.readAllLines();
    private ArrayList<Material> listaMaterial = FileMaterialManager.readAllLines();
    private ArrayList<DetalleVenta> listaDetalleVentas = new ArrayList<>();
    private static int idDetalle = 1;
    private static int indice = 0;


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
                        final Button editButton = new Button("Eliminar");
                        editButton.setOnAction(event ->{
                            DetalleVenta modalDetalleVenta = getTableView().getItems().get(getIndex());
                            tableDetalleVentas.getItems().remove(modalDetalleVenta);
                            tableDetalleVentas.refresh();
                            listaDetalleVentas.remove(modalDetalleVenta);
                        });
                        setGraphic(editButton);
                        setText(null);
                    }
                }
            };
            return cell;
        });

        tableDetalleVentas.setOnMouseClicked(event ->{
            if(!tableDetalleVentas.getSelectionModel().isEmpty()){
                DetalleVenta detalleVenta = tableDetalleVentas.getSelectionModel().getSelectedItem();
                inputIdMaterial.setText(String.valueOf(detalleVenta.getIdMaterial()));
                inputCantidad.setText(String.valueOf(detalleVenta.getCantidad()));
                inputPeso.setText(String.valueOf(detalleVenta.getPeso()));
                inputPrecio.setText(String.valueOf(detalleVenta.getPrecio()));
                indice = detalleVenta.getIdDetalleVenta();
            }
        });
    }

    public void clearDetalleInputs(){
        inputIdMaterial.setText("");
        inputCantidad.setText("");
        inputPrecio.setText("");
        inputPeso.setText("");
    }

    public void clearVentaInputs(){
        inputIdProveedor.setText("");
        inputFechaVenta.setText("");
        inputDespachado.setText("");
        inputProveedor.setText("");
        tableDetalleVentas.getItems().clear();
    }


    @FXML
    public void handleBtnAgregarMaterial(){
        int idMaterial = Integer.parseInt(inputIdMaterial.getText());
        int cantidad = Integer.parseInt(inputCantidad.getText());
        long peso = Long.parseLong(inputPeso.getText());
        long precio = Long.parseLong(inputPrecio.getText());
        int idVenta = listaVentas.size() +1;

        DetalleVenta detalleVenta = new DetalleVenta(idDetalle ++, idMaterial, cantidad, peso, precio, idVenta);
        listaDetalleVentas.add(detalleVenta);
        tableDetalleVentas.getItems().add(detalleVenta);

        clearDetalleInputs();
    }

    @FXML
    public void handleBtnHacerCompra(){
        int idProveedor = Integer.parseInt(inputIdProveedor.getText());
        String fechaVenta = inputFechaVenta.getText();
        boolean despachado = Boolean.parseBoolean(inputDespachado.getText());
        int idVenta = listaVentas.size() +1;

        Venta venta = new Venta(idVenta, idProveedor, fechaVenta, despachado);
        FileVentaManager.writeLine(venta);
        FileDetalleVentaManager.writeVentaWithDetails(idVenta, listaDetalleVentas);

        clearVentaInputs();
    }
}
