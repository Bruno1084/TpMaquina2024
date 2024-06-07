package Logica.Controladores;
import Logica.Clases.Cliente;
import Utils.FileClienteManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;


public class ClienteController {
    // Table columns and view
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

    // Form inputs
    @FXML
    private TextField inputDni;
    @FXML
    private TextField inputNombre;
    @FXML
    private TextField inputDireccion;
    @FXML
    private TextField inputTelefono;

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
    private final FileClienteManager fileClienteManager = new FileClienteManager("src/main/java/Permanencia/Cliente.txt", "Cliente");
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private static int indice = 0;


    public void initialize(){
        columnID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        columnDni.setCellValueFactory(new PropertyValueFactory<>("Dni"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        columnDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        columnTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        columnAlta.setCellValueFactory(new PropertyValueFactory<>("Alta"));

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
                indice = cliente.getId();
            }
        });
    }

    public ArrayList<Cliente> loadListaClientes(){
        ArrayList<Cliente> cliente;
        cliente = fileClienteManager.readAllLines();

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

            Cliente newCliente = new Cliente(id, dni, nombre, direccion, telefono, alta);
            data.add(newCliente);
        });

        return data;
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
    }

    public boolean checkInputs(){
        if(inputDni.getText().isEmpty() || inputNombre.getText().isEmpty() ||
                inputDireccion.getText().isEmpty() || inputTelefono.getText().isEmpty()) {
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
            int id = listaClientes.isEmpty() ? 1 : listaClientes.get(listaClientes.size() - 1).getId() + 1;

            Cliente cliente = new Cliente(id, dni, nombre, direccion, telefono, alta);
            listaClientes.add(cliente);
            fileClienteManager.writeLine(cliente);

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

            Cliente cliente = new Cliente(indice, dni, nombre, direccion, telefono, alta);
            fileClienteManager.editLine(indice, cliente);
            listaClientes = loadListaClientes();
            tableClientes.setItems(loadTableClientes());
            clearInputs();
        }
    }

    @FXML
    public void handleBtnEliminar(){
        if (!tableClientes.getSelectionModel().isEmpty()) {
            Cliente selectedCliente = tableClientes.getSelectionModel().getSelectedItem();
            fileClienteManager.deleteLine(selectedCliente.getId());
            listaClientes = loadListaClientes();
            tableClientes.setItems(loadTableClientes());
            clearInputs();
        }
    }
}
