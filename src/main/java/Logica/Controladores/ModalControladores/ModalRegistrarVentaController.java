package Logica.Controladores.ModalControladores;
import Logica.Clases.*;
import Utils.CustomMenuItem;
import Utils.FileDetalleVentaManager;
import Utils.FileMaterialManager;
import Utils.FileProveedorManager;
import Utils.FileVentaManager;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
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
    @FXML
    private TextField inputMaterial;
    @FXML
    private ContextMenu contextMaterial;


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
    @FXML
    private ContextMenu contextIdProveedor;



    // Buttons
    @FXML
    Button btnAgregarMaterial;
    @FXML
    Button btnHacerCompra;

    // List
    private ArrayList<Venta> listaVentas = FileVentaManager.readAllLines();
    private ArrayList<Material> listaMaterial = FileMaterialManager.readAllLines();
    private ArrayList<Proveedor> listaProveedor = FileProveedorManager.readAllLines();
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
    public void handleInputMaterial(){
        contextMaterial.getItems().clear();

        for(Material material : listaMaterial){
            Utils.CustomMenuItem customMenuItem = new CustomMenuItem(material.getNombre(), material);

            customMenuItem.setOnAction(event -> {
                inputIdMaterial.setText(String.valueOf(material.getId()));
                inputPrecio.setText(String.valueOf(material.getPrecioCompra()));
                inputMaterial.setText(material.getNombre());
            });
            contextMaterial.getItems().add(customMenuItem);
        }
        contextMaterial.show(inputMaterial, Side.BOTTOM, 0, 0);
    }

    @FXML
    public void handleInputIdProveedor(){
        contextIdProveedor.getItems().clear();

        for(Proveedor proveedor : listaProveedor){
            CustomMenuItem customMenuItem = new CustomMenuItem(String.valueOf(proveedor.getId()), proveedor);

            customMenuItem.setOnAction(event -> {
                inputIdProveedor.setText(String.valueOf(proveedor.getId()));
                inputProveedor.setText(proveedor.getNombre());
            });
            contextIdProveedor.getItems().add(customMenuItem);
        }
        contextIdProveedor.show(inputIdProveedor, Side.BOTTOM, 0, 0);
    }

    @FXML
    public void handleCalcularPrecio(){
        ObservableList<DetalleVenta> data = tableDetalleVentas.getItems();
        long precioFinal = data.stream().mapToLong(material -> (long) (material.getCantidad() * material.getPeso() * material.getPrecio())).sum();
        inputPrecioTotal.setText(String.valueOf(precioFinal));
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
        handleCalcularPrecio();
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
