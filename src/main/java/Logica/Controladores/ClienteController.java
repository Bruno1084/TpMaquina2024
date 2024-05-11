package Logica.Controladores;
import Logica.Clases.Cliente;
import Logica.Clases.Empleado;
import Utils.FileClienteManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ClienteController {
    @FXML
    private TableView<Cliente> tableClientes;
    @FXML
    private TableColumn<Integer, Cliente> columnID;
    @FXML
    private TableColumn<Integer, Cliente> columnDni;
    @FXML
    private TableColumn<String, Cliente> columnNombre;
    @FXML
    private TableColumn<String, Cliente> columnDireccion;
    @FXML
    private TableColumn<Long, Cliente> columnTelefono;

    private final FileClienteManager fileClienteManager = new FileClienteManager("src/main/java/Permanencia/Cliente.txt", "Cliente");
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private static int indice = 0;


    public void initialize(){
        columnID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        columnDni.setCellValueFactory(new PropertyValueFactory<>("Dni"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        columnDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        columnTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));

        listaClientes = loadListaEmpleados();
        tableClientes.setItems(loadTableEmpleados());
    }

    public ArrayList<Cliente> loadListaEmpleados(){
        ArrayList<Cliente> cliente;
        cliente = fileClienteManager.readAllLines();

        return cliente;
    }

    public ObservableList<Cliente> loadTableEmpleados(){
        ObservableList<Cliente> data = FXCollections.observableArrayList();

        listaClientes.forEach(cliente -> {
            int id = cliente.getId();
            long dni = cliente.getDni();
            String nombre = cliente.getNombre();
            String direccion = cliente.getDireccion();
            long telefono = cliente.getTelefono();

            Cliente newCliente = new Cliente(id, dni, nombre, direccion, telefono);
            data.add(newCliente);
        });

        return data;
    }

}
