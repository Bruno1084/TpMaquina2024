package Logica.Controladores;
import Logica.Clases.Cliente;
import Logica.Clases.Compra;
import Utils.FileClienteManager;
import Utils.FileCompraManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;


public class ClienteController {
    // Table clientes and view
    @FXML
    private TableView<Cliente> tableClientes;
    @FXML
    private TableColumn<Cliente, Integer> columnID;
    @FXML
    private TableColumn<Cliente, Integer> columnDni;
    @FXML
    private TableColumn<Cliente, String> columnNombre;
    @FXML
    private TableColumn<Cliente, String> columnDireccion;
    @FXML
    private TableColumn<Cliente, Long> columnTelefono;
    @FXML
    private TableColumn<Cliente, Boolean> columnAlta;
    @FXML
    private TableColumn<Cliente, Integer> columnCantCompras;

    // Table compras and columns
    @FXML
    private TableView<Compra> tableCompras;
    @FXML
    private TableColumn<Compra, Integer> columnIdCompra;
    @FXML
    private TableColumn<Compra, String> columnFechaCompra;
    @FXML
    private TableColumn<Compra, Integer> columnIdEmpleado;
    @FXML
    private TableColumn<Compra, String> columnPagado;

    // Form inputs
    @FXML
    private TextField inputDni;
    @FXML
    private TextField inputNombre;
    @FXML
    private TextField inputDireccion;
    @FXML
    private TextField inputTelefono;
    @FXML
    private TextField inputCantCompras;

    // Tab buttons
    @FXML
    private Button btnAniadir;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnEditar;
    @FXML
    private RadioButton inputAlta;

    // Other things
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private static int indice = 0;


    public void initialize(){
        // Set columns from table Clientes
        columnID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        columnDni.setCellValueFactory(new PropertyValueFactory<>("Dni"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        columnDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        columnTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        columnAlta.setCellValueFactory(new PropertyValueFactory<>("Alta"));
        columnCantCompras.setCellValueFactory(new PropertyValueFactory<>("CantCompras"));

        // Set columns from table Compras
        columnIdCompra.setCellValueFactory(new PropertyValueFactory<>("Id"));
        columnFechaCompra.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        columnIdEmpleado.setCellValueFactory(new PropertyValueFactory<>("IdEmpleado"));
        columnPagado.setCellValueFactory(new PropertyValueFactory<>("Pagada"));

        listaClientes = loadListaClientes();
        tableClientes.setItems(loadTableClientes());

        tableClientes.setOnMouseClicked(event ->{
            if(!tableClientes.getSelectionModel().isEmpty()){
                Cliente cliente = tableClientes.getSelectionModel().getSelectedItem();
                inputDni.setText(String.valueOf(cliente.getDni()));
                inputNombre.setText(cliente.getNombre());
                inputDireccion.setText(cliente.getDireccion());
                inputTelefono.setText(String.valueOf(cliente.getTelefono()));
                inputAlta.setSelected(cliente.getAlta());
                inputCantCompras.setText(String.valueOf(cliente.getCantCompras()));

                indice = cliente.getId();
                loadTableCompras();
            }
        });
    }

    public ArrayList<Cliente> loadListaClientes(){
        ArrayList<Cliente> cliente;
        cliente = FileClienteManager.readAllLines();

        return cliente;
    }

    public ObservableList<Cliente> loadTableClientes(){
        ObservableList<Cliente> data = FXCollections.observableArrayList();

        listaClientes.forEach(cliente -> {
            int id = cliente.getId();
            long dni = cliente.getDni();
            String nombre = cliente.getNombre();
            String direccion = cliente.getDireccion();
            long telefono = cliente.getTelefono();
            boolean alta = cliente.getAlta();
            int cantCompra = cliente.getCantCompras();

            Cliente newCliente = new Cliente(id, dni, nombre, direccion, telefono, alta, cantCompra);
            data.add(newCliente);
        });

        return data;
    }

    public void loadTableCompras(){
        ArrayList<Compra> compras = FileCompraManager.readAllLinesByClient(indice);
        ObservableList<Compra> data = FXCollections.observableArrayList();
        data.addAll(compras);
        tableCompras.setItems(data);
    }

    public Cliente getLastCliente(){
        return listaClientes.get(listaClientes.size() -1);
    }

    public void clearInputs(){
        inputDni.setText("");
        inputNombre.setText("");
        inputDireccion.setText("");
        inputTelefono.setText("");
        inputAlta.setSelected(false);
        inputCantCompras.setText("");
    }

    public boolean checkInputs(){
        if(inputDni.getText().isEmpty() || inputNombre.getText().isEmpty() ||
                inputDireccion.getText().isEmpty() || inputTelefono.getText().isEmpty() || inputCantCompras.getText().isEmpty()) {
            System.out.println("There is an empty input");
            return false;
        }
        return true;
    }

    @FXML
    public void handleBtnAniadir(){
        if (checkInputs()){
            long dni = Long.parseLong(inputDni.getText());
            String nombre = inputNombre.getText();
            String direccion = inputDireccion.getText();
            long telefono = Long.parseLong(inputTelefono.getText());
            boolean alta = inputAlta.isSelected();
            int cantCompra = Integer.parseInt(inputCantCompras.getText());
            int id = listaClientes.isEmpty() ? 1 : listaClientes.get(listaClientes.size() - 1).getId() + 1;

            Cliente cliente = new Cliente(id, dni, nombre, direccion, telefono, alta, cantCompra);
            listaClientes.add(cliente);
            FileClienteManager.writeLine(cliente);

            tableClientes.setItems(loadTableClientes());
            clearInputs();
        }
    }

    @FXML
    public void handleBtnEditar(){
        if (checkInputs()){
            long dni = Long.parseLong(inputDni.getText());
            String nombre = inputNombre.getText();
            String direccion = inputDireccion.getText();
            long telefono = Long.parseLong(inputTelefono.getText());
            boolean alta = inputAlta.isSelected();
            int cantCompras = Integer.parseInt(inputCantCompras.getText());

            Cliente cliente = new Cliente(indice, dni, nombre, direccion, telefono, alta, cantCompras);
            FileClienteManager.editLine(indice, cliente);
            listaClientes = loadListaClientes();
            tableClientes.setItems(loadTableClientes());
            clearInputs();
        }
    }

    @FXML
    public void handleBtnEliminar(){
        if (!tableClientes.getSelectionModel().isEmpty()) {
            Cliente selectedCliente = tableClientes.getSelectionModel().getSelectedItem();
            FileClienteManager.deleteLine(selectedCliente.getId());
            listaClientes = loadListaClientes();
            tableClientes.setItems(loadTableClientes());
            clearInputs();
        }
    }
}