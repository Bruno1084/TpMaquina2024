package Logica.Controladores;
import Logica.Clases.Proveedor;
import Utils.FileProveedorManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;


public class ProveedorController {
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

        listaProveedores = loadListaEmpleados();
        tableProveedores.setItems(loadTableEmpleados());
    }

    public ArrayList<Proveedor> loadListaEmpleados(){
        ArrayList<Proveedor> proveedor;
        proveedor = fileProveedorManager.readAllLines();

        return proveedor;
    }

    public ObservableList<Proveedor> loadTableEmpleados(){
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

}
