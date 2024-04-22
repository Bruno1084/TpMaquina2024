package Logica.Controladores;

import Logica.Clases.Empleado;
import Utils.FileManager;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.Date;

public class EmpleadoController {
    @FXML
    private TableView<Empleado> tableEmpleados;
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
    private TableColumn<Date, Empleado> columnFechaIngreso;

    private FileManager fileManager = new FileManager("src/main/java/Permanencia/Empleado.txt", "Empleado");


    public void initialize(){

    }

}
