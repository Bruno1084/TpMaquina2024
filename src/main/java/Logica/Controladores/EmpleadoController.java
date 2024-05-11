package Logica.Controladores;
import Logica.Clases.Empleado;
import Utils.FileEmpleadoManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;


public class EmpleadoController {
    @FXML
    private TableView<Empleado> tableEmpleados;
    @FXML
    private TableColumn<Integer, Empleado> columnID;
    @FXML
    private TableColumn<Integer, Empleado> columnDni;
    @FXML
    private TableColumn<String, Empleado> columnNombre;
    @FXML
    private TableColumn<String, Empleado> columnDireccion;
    @FXML
    private TableColumn<Long, Empleado> columnTelefono;
    @FXML
    private TableColumn<Integer, Empleado> columnNroLegajo;
    @FXML
    private TableColumn<String, Empleado> columnFechaIngreso;

    private final FileEmpleadoManager fileEmpleadoManager = new FileEmpleadoManager("src/main/java/Permanencia/Empleado.txt", "Empleado");
    private ArrayList<Empleado> listaEmpleados = new ArrayList<>();
    private static int indice = 0;

    public void initialize(){
        columnID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        columnDni.setCellValueFactory(new PropertyValueFactory<>("Dni"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        columnDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        columnTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        columnNroLegajo.setCellValueFactory(new PropertyValueFactory<>("NroLegajo"));
        columnFechaIngreso.setCellValueFactory(new PropertyValueFactory<>("NroLegajo"));

        listaEmpleados = loadListaEmpleados();
        tableEmpleados.setItems(loadTableEmpleados());
    }


    public ArrayList<Empleado> loadListaEmpleados(){
        ArrayList<Empleado> empleados;
        empleados = fileEmpleadoManager.readAllLines();


        return empleados;
    }

    public ObservableList<Empleado> loadTableEmpleados(){
        ObservableList<Empleado> data = FXCollections.observableArrayList();

        listaEmpleados.forEach(empleado -> {
            int id = empleado.getId();
            long dni = empleado.getDni();
            String nombre = empleado.getNombre();
            String direccion = empleado.getDireccion();
            long telefono = empleado.getTelefono();
            int nroLegajo = empleado.getNroLegajo();
            String fechaIngreso = empleado.getFechaIngreso();

            Empleado newEmpleado = new Empleado(id, dni, nombre, direccion, telefono, nroLegajo, fechaIngreso);
            data.add(newEmpleado);
        });

        return data;
    }


}
