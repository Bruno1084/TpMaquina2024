package Logica.Controladores;

import Logica.Clases.Cliente;
import Logica.Clases.Compra;
import Logica.Clases.Empleado;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CompraController {
    @FXML
    TableView<Compra> tableCompras;
    @FXML
    TableColumn<String, Compra> columnFecha;
    @FXML
    TableColumn<Cliente, Compra> columnCliente;
    @FXML
    TableColumn<String, Compra> columnPagado;
    @FXML
    TableColumn<Empleado, Compra> columnEmpleado;


    public void initialize(){

    }
}
