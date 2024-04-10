package GUI;

import Logica.Controladores.ClienteController;
import Logica.Controladores.EmpleadoController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;

public class GUIController {
    @FXML
    private EmpleadoController empleadoController;
    @FXML
    private ClienteController clienteController;


    @FXML
    private Button btn_Empleados;
    @FXML
    private Button btn_Proveedores;
    @FXML
    private Button btn_Clientes;
    @FXML
    private Button btn_Materiales;
    @FXML
    private Button btn_Ventas;
    @FXML
    private Button btn_Compras;

    @FXML
    TabPane mainTabPane;

    @FXML
    private void handleButtonEmpleados(){
        mainTabPane.getSelectionModel().select(0);
    }

    @FXML
    private void handleButtonProveedores(){
        mainTabPane.getSelectionModel().select(1);
    }

    @FXML
    private void handleButtonClientes(){
        mainTabPane.getSelectionModel().select(2);
    }

    @FXML
    private void handleButtonMateriales(){
        mainTabPane.getSelectionModel().select(3);
    }

    @FXML
    private void handleButtonVentas(){
        mainTabPane.getSelectionModel().select(4);
    }

    @FXML
    private void handleButtonCompras(){
        mainTabPane.getSelectionModel().select(5);
    }
}
