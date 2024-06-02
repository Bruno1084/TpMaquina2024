package Logica.Controladores;
import Logica.Clases.Empleado;
import Logica.Clases.Proveedor;
import Utils.FileProveedorManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;


public class ProveedorController {
    // Table columns and view
    @FXML
    private TableView<Proveedor> tableProveedores;
    @FXML
    private TableColumn<Integer, Proveedor> columnID;
    @FXML
    private TableColumn<Long, Proveedor> columnDni;
    @FXML
    private TableColumn<String, Proveedor> columnNombre;
    @FXML
    private TableColumn<String, Proveedor> columnDireccion;
    @FXML
    private TableColumn<Long, Proveedor> columnTelefono;
    @FXML
    private TableColumn<Long, Proveedor> columnCuil;
    @FXML
    private TableColumn<String, Proveedor> columnCiudad;

    // Form inputs
    @FXML
    private TextField inputDNI;
    @FXML
    private TextField inputNombre;
    @FXML
    private TextField inputDireccion;
    @FXML
    private TextField inputTelefono;
    @FXML
    private TextField inputCuil;
    @FXML
    private TextField inputCiudad;

    // Tab buttons
    @FXML
    private Button btnAniadir;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnEditar;

    // Other things
    private final FileProveedorManager fileProveedorManager = new FileProveedorManager("src/main/java/Permanencia/Proveedor.txt", "Proveedor");
    private ArrayList<Proveedor> listaProveedores = new ArrayList<>();
    private static int indice = 0;

    public void initialize(){
        columnID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        columnDni.setCellValueFactory(new PropertyValueFactory<>("Dni"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        columnDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        columnTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        columnCuil.setCellValueFactory(new PropertyValueFactory<>("Cuil"));
        columnCiudad.setCellValueFactory(new PropertyValueFactory<>("Ciudad"));

        listaProveedores = loadListaProveedores();
        tableProveedores.setItems(loadTableProveedores());

        tableProveedores.setOnMouseClicked(event ->{
            if(!tableProveedores.getSelectionModel().isEmpty()){
                Proveedor proveedor = tableProveedores.getSelectionModel().getSelectedItem();
                inputDNI.setText(String.valueOf(proveedor.getDni()));
                inputNombre.setText(proveedor.getNombre());
                inputDireccion.setText(proveedor.getDireccion());
                inputTelefono.setText(String.valueOf(proveedor.getTelefono()));
                inputCuil.setText(String.valueOf(proveedor.getCuil()));
                inputCiudad.setText(proveedor.getCiudad());
                indice = proveedor.getId();
            }
        });
    }


    public ArrayList<Proveedor> loadListaProveedores(){
        ArrayList<Proveedor> proveedor;
        proveedor = fileProveedorManager.readAllLines();

        return proveedor;
    }

    public ObservableList<Proveedor> loadTableProveedores(){
        ObservableList<Proveedor> data = FXCollections.observableArrayList();

        listaProveedores.forEach(cliente -> {
            int id = cliente.getId();
            long dni = cliente.getDni();
            String nombre = cliente.getNombre();
            String direccion = cliente.getDireccion();
            long telefono = cliente.getTelefono();
            long cuil = cliente.getCuil();
            String ciudad = cliente.getCiudad();

            Proveedor newProveedor = new Proveedor(id, dni, nombre, direccion, telefono, cuil, ciudad);
            data.add(newProveedor);
        });

        return data;
    }

    public Proveedor getLastProveedor(){
        return listaProveedores.get(listaProveedores.size() -1);
    }

    public void clearInputs(){
        inputDNI.setText("");
        inputNombre.setText("");
        inputDireccion.setText("");
        inputTelefono.setText("");
        inputCuil.setText("");
        inputCiudad.setText("");
    }

    public boolean checkInputs(){
        if(inputDNI.getText().isEmpty() || inputNombre.getText().isEmpty() ||
                inputDireccion.getText().isEmpty() || inputTelefono.getText().isEmpty() ||
                inputCuil.getText().isEmpty() || inputCiudad.getText().isEmpty()) {
            System.out.println("There is an empty input");
            return false;
        }
        return true;
    }

    @FXML
    public void handleBtnAniadir(){
        if (checkInputs()){
            long dni = Long.parseLong(inputDNI.getText());
            String nombre = inputNombre.getText();
            String direccion = inputDireccion.getText();
            long telefono = Long.parseLong(inputTelefono.getText());
            long cuil = Long.parseLong(inputCuil.getText());
            String ciudad = inputCiudad.getText();
            int id = listaProveedores.isEmpty() ? 1 : listaProveedores.get(listaProveedores.size() - 1).getId() + 1;

            Proveedor proveedor = new Proveedor(id, dni, nombre, direccion, telefono, cuil, ciudad);
            listaProveedores.add(proveedor);
            fileProveedorManager.writeLine(proveedor);

            tableProveedores.setItems(loadTableProveedores());
            clearInputs();
        }
    }

    @FXML
    public void handleBtnEditar(){
        if (checkInputs()){
            long dni = Long.parseLong(inputDNI.getText());
            String nombre = inputNombre.getText();
            String direccion = inputDireccion.getText();
            long telefono = Long.parseLong(inputTelefono.getText());
            long cuil = Long.parseLong(inputCuil.getText());
            String ciudad = inputCiudad.getText();

            Proveedor proveedor = new Proveedor(indice, dni, nombre, direccion, telefono, cuil, ciudad);
            fileProveedorManager.editLine(indice, proveedor);
            listaProveedores = loadListaProveedores();

            tableProveedores.setItems(loadTableProveedores());
            clearInputs();
        }
    }

    @FXML
    public void handleBtnEliminar(){
        System.out.println("Botón btnEliminar presionado");
    }
}
