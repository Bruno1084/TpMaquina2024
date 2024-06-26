package Logica.Controladores.ModalControladores;
import Logica.Clases.*;
import Utils.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import Utils.CustomMenuItem;


public class ModalRegistrarCompraController {
    // Table columns
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

    // Compra inputs
    @FXML
    private TextField inputMaterial;
    @FXML
    private TextField inputIdMaterial;
    @FXML
    private TextField inputCantidad;
    @FXML
    private TextField inputPrecio;
    @FXML
    private TextField inputPeso;
    @FXML
    private ContextMenu contextIdMaterial;
    @FXML
    private ContextMenu contextMaterial;

    // Compra object inputs
    @FXML
    private TextField inputFechaVenta;
    @FXML
    private TextField inputIdCliente;
    @FXML
    private TextField inputPagado;
    @FXML
    private TextField inputIdEmpleado;
    @FXML
    private TextField inputPrecioTotal;
    @FXML
    private ContextMenu contextCliente;
    @FXML
    private ContextMenu contextEmpleado;

    @FXML
    private TextField inputNombreCliente;
    @FXML
    private TextField inputNombreEmpleado;


    // Buttons
    @FXML
    Button btnAgregarMaterial;
    @FXML
    Button btnHacerCompra;

    // Lists
    private ArrayList<Compra> listaCompras = FileCompraManager.readAllLines();
    private ArrayList<Material> listaMaterial = FileMaterialManager.readAllLines();
    private ArrayList<Cliente> listaClientes = FileClienteManager.readAllLines();
    private ArrayList<Empleado> listaEmpleados = FileEmpleadoManager.readAllLines();
    private ArrayList<DetalleCompra> listaDetalleCompras = new ArrayList<>();
    private static int idDetalle = 1;
    private static int indice = 0;

    public void initialize(){
        columnIdMaterial.setCellValueFactory(new PropertyValueFactory<>("IdMaterial"));
        columnCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        columnPeso.setCellValueFactory(new PropertyValueFactory<>("Peso"));
        columnPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
        columnEliminar.setCellFactory(param -> {
            final TableCell<DetalleCompra, String> cell = new TableCell<>(){

                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);

                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else{
                        final Button eliminarButton = new Button("Eliminar");
                        eliminarButton.setOnAction(event -> {
                            DetalleCompra modalDetalleCompra = getTableView().getItems().get(getIndex());
                            tableDetalleCompra.getItems().remove(modalDetalleCompra);
                            tableDetalleCompra.refresh();
                            listaDetalleCompras.remove(modalDetalleCompra);
                        });
                        setGraphic(eliminarButton);
                        setText(null);
                    }
                }
            };
            return cell;
        });

        tableDetalleCompra.setOnMouseClicked(event ->{
            if(!tableDetalleCompra.getSelectionModel().isEmpty()){
                DetalleCompra detalleCompra = tableDetalleCompra.getSelectionModel().getSelectedItem();
                inputIdMaterial.setText(String.valueOf(detalleCompra.getIdMaterial()));
                inputCantidad.setText(String.valueOf(detalleCompra.getCantidad()));
                inputPeso.setText(String.valueOf(detalleCompra.getPeso()));
                inputPrecio.setText(String.valueOf(detalleCompra.getPrecio()));
                indice = detalleCompra.getIdDetalleCompra();
            }
        });
    }

    public void clearDetalleInputs(){
        inputIdMaterial.setText("");
        inputCantidad.setText("");
        inputPrecio.setText("");
        inputPeso.setText("");
        inputMaterial.setText("");
    }

    public void clearCompraInputs(){
        inputFechaVenta.setText("");
        inputIdCliente.setText("");
        inputPagado.setText("");
        inputIdEmpleado.setText("");
        inputPrecioTotal.setText("");

        tableDetalleCompra.getItems().clear();
    }

    @FXML
    public void handleInputIdMaterial(){
        contextIdMaterial.getItems().clear();

        for(Material material : listaMaterial){
            CustomMenuItem customMenuItem = new CustomMenuItem(String.valueOf(material.getId()), material);

            customMenuItem.setOnAction(event -> {
                inputMaterial.setText(material.getNombre());
                inputIdMaterial.setText(String.valueOf(material.getId()));
                inputPrecio.setText(String.valueOf(material.getPrecioCompra()));
            });
            contextIdMaterial.getItems().add(customMenuItem);
        }
        contextIdMaterial.show(inputIdMaterial, Side.BOTTOM, 0, 0);
    }

    @FXML
    public void handleInputMaterial(){
        contextMaterial.getItems().clear();

        for(Material material : listaMaterial){
            CustomMenuItem customMenuItem = new CustomMenuItem(material.getNombre(), material);

            customMenuItem.setOnAction(event -> {
                inputIdMaterial.setText(String.valueOf(material.getId()));
                inputPrecio.setText(String.valueOf(material.getPrecioCompra()));
                inputMaterial.setText(material.getNombre());
            });
            contextMaterial.getItems().add(customMenuItem);
        }
        contextMaterial.show(inputMaterial, Side.BOTTOM, 0, 0);
    }

    public void handleInputIdCliente(){
        contextCliente.getItems().clear();

        for(Cliente cliente : listaClientes){
            CustomMenuItem customMenuItem = new CustomMenuItem(String.valueOf(cliente.getId()), cliente);

            customMenuItem.setOnAction(event -> {
                inputIdCliente.setText(String.valueOf(cliente.getId()));
                inputNombreCliente.setText(cliente.getNombre());
            });
            contextCliente.getItems().add(customMenuItem);
        }
        contextCliente.show(inputIdCliente, Side.BOTTOM, 0, 0);
    }

    public void handleInputIdEmpleado(){
        contextEmpleado.getItems().clear();

        for(Empleado empleado : listaEmpleados){
            CustomMenuItem customMenuItem = new CustomMenuItem(String.valueOf(empleado.getId()), empleado);

            customMenuItem.setOnAction(event -> {
                inputIdEmpleado.setText(String.valueOf(empleado.getId()));
                inputNombreEmpleado.setText(empleado.getNombre());
            });
            contextEmpleado.getItems().add(customMenuItem);
        }
        contextEmpleado.show(inputIdEmpleado, Side.BOTTOM, 0, 0);
    }

    @FXML
    public void handleCalcularPrecio(){
        ObservableList<DetalleCompra> data = tableDetalleCompra.getItems();
        long precioFinal = data.stream().mapToLong(material -> (long) (material.getCantidad() * material.getPeso() * material.getPrecio())).sum();
        inputPrecioTotal.setText(String.valueOf(precioFinal));
    }

    @FXML
    public void handleBtnAgregarMaterial(){
        int idMaterial = Integer.parseInt(inputIdMaterial.getText());
        int cantidad = Integer.parseInt(inputCantidad.getText());
        long peso = Long.parseLong(inputPeso.getText());
        long precio = Long.parseLong(inputPrecio.getText());
        int idVenta = listaCompras.size() +1;

        DetalleCompra detalleCompra = new DetalleCompra(idDetalle++, idMaterial, cantidad, peso, precio, idVenta);
        listaDetalleCompras.add(detalleCompra);
        tableDetalleCompra.getItems().add(detalleCompra);

        clearDetalleInputs();
        handleCalcularPrecio();
    }

    @FXML
    public void handleBtnHacerCompra(){
        int idCliente = Integer.parseInt(inputIdCliente.getText());
        String fechaVenta = inputFechaVenta.getText();
        String pagado = inputPagado.getText();
        int idEmpleado = Integer.parseInt(inputIdEmpleado.getText());
        int idCompra = listaCompras.size() +1;

        Compra compra = new Compra(idCompra, fechaVenta, idCliente, pagado, idEmpleado);
        FileCompraManager.writeLine(compra);
        FileDetalleCompraManager.writeVentaWithDetails(idCompra, listaDetalleCompras);

        FileClienteManager.incrementCompras(idCliente);
        clearCompraInputs();
    }


 }
