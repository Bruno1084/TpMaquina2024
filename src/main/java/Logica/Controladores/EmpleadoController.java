package Logica.Controladores;
import Logica.Clases.Empleado;
import Utils.FileEmpleadoManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;


public class EmpleadoController {
    // Table columns and view
    @FXML
    private TableView<Empleado> tableEmpleados;
    @FXML
    private TableColumn<Empleado, Integer> columnID;
    @FXML
    private TableColumn<Empleado, Integer> columnDni;
    @FXML
    private TableColumn<Empleado, String> columnNombre;
    @FXML
    private TableColumn<Empleado, String> columnDireccion;
    @FXML
    private TableColumn<Empleado, Long> columnTelefono;
    @FXML
    private TableColumn<Empleado, Integer> columnNroLegajo;
    @FXML
    private TableColumn<Empleado, String> columnFechaIngreso;
    @FXML
    private TableColumn<Empleado, Boolean> columnAlta;

    // Form inputs
    @FXML
    private TextField inputDNI;
    @FXML
    private TextField inputNombre;
    @FXML
    private TextField inputDireccion;
    @FXML
    private TextField inputNroLegajo;
    @FXML
    private TextField inputTelefono;
    @FXML
    private TextField inputFechaIngreso;


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
        columnFechaIngreso.setCellValueFactory(new PropertyValueFactory<>("FechaIngreso"));
        columnAlta.setCellValueFactory(new PropertyValueFactory<>("Alta"));

        listaEmpleados = loadListaEmpleados();
        tableEmpleados.setItems(loadTableEmpleados());

        tableEmpleados.setOnMouseClicked(event ->{
            if(!tableEmpleados.getSelectionModel().isEmpty()){
                Empleado empleado = tableEmpleados.getSelectionModel().getSelectedItem();
                inputDNI.setText(String.valueOf(empleado.getDni()));
                inputNombre.setText(empleado.getNombre());
                inputDireccion.setText(empleado.getDireccion());
                inputTelefono.setText(String.valueOf(empleado.getTelefono()));
                inputNroLegajo.setText(String.valueOf(empleado.getNroLegajo()));
                inputFechaIngreso.setText(empleado.getFechaIngreso());
                inputAlta.setSelected(empleado.getAlta());
                indice = empleado.getId();
            }
        });
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
            boolean alta = empleado.getAlta();

            Empleado newEmpleado = new Empleado(id, dni, nombre, direccion, telefono, nroLegajo, fechaIngreso, alta);
            data.add(newEmpleado);
        });

        return data;
    }

    public Empleado getLastEmpleado(){
        return listaEmpleados.get(listaEmpleados.size() -1);
    }

    public void clearInputs(){
        inputDNI.setText("");
        inputNombre.setText("");
        inputDireccion.setText("");
        inputTelefono.setText("");
        inputNroLegajo.setText("");
        inputFechaIngreso.setText("");
        inputAlta.setSelected(false);
    }

    public boolean checkInputs(){
        if(inputDNI.getText().isEmpty() || inputNombre.getText().isEmpty() ||
                inputDireccion.getText().isEmpty() || inputTelefono.getText().isEmpty() ||
                inputNroLegajo.getText().isEmpty() || inputFechaIngreso.getText().isEmpty()) {
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
            int nroLegajo = Integer.parseInt(inputNroLegajo.getText());
            String fechaIngreso = inputFechaIngreso.getText();
            boolean alta = inputAlta.isSelected();
            int id = listaEmpleados.isEmpty() ? 1 : listaEmpleados.get(listaEmpleados.size() - 1).getId() + 1;

            Empleado empleado = new Empleado(id, dni, nombre, direccion, telefono, nroLegajo, fechaIngreso, alta);
            listaEmpleados.add(empleado);
            fileEmpleadoManager.writeLine(empleado);

            tableEmpleados.setItems(loadTableEmpleados());
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
            int nroLegajo = Integer.parseInt(inputNroLegajo.getText());
            String fechaIngreso = inputFechaIngreso.getText();
            boolean alta = inputAlta.isSelected();

            Empleado empleado = new Empleado(indice, dni, nombre, direccion, telefono, nroLegajo, fechaIngreso, alta);
            fileEmpleadoManager.editLine(indice, empleado);
            listaEmpleados = loadListaEmpleados();
            tableEmpleados.setItems(loadTableEmpleados());
            clearInputs();
        }
    }

    @FXML
    public void handleBtnEliminar(){
        if (!tableEmpleados.getSelectionModel().isEmpty()) {
            Empleado selectedEmpleado = tableEmpleados.getSelectionModel().getSelectedItem();
            fileEmpleadoManager.deleteLine(selectedEmpleado.getId());
            listaEmpleados = loadListaEmpleados();
            tableEmpleados.setItems(loadTableEmpleados());
            clearInputs();
        }
    }
}
