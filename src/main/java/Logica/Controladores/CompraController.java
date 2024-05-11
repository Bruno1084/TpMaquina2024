package Logica.Controladores;
import Logica.Clases.Cliente;
import Logica.Clases.Compra;
import Logica.Clases.Empleado;
import Utils.FileCompraManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;


public class CompraController {
    @FXML
    TableView<Compra> tableCompras;
    @FXML
    TableColumn<Integer, Compra> columnID;
    @FXML
    TableColumn<String, Compra> columnFecha;
    @FXML
    TableColumn<Integer, Compra> columnCliente;
    @FXML
    TableColumn<String, Compra> columnPagado;
    @FXML
    TableColumn<Integer, Compra> columnEmpleado;

    private final FileCompraManager fileCompraManager = new FileCompraManager("src/main/java/Permanencia/Compra.txt", "Compra");
    private ArrayList<Compra> listaCompras = new ArrayList<>();
    private static int indice = 0;


    public void initialize(){
        columnID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        columnFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        columnCliente.setCellValueFactory(new PropertyValueFactory<>("IdCliente"));
        columnPagado.setCellValueFactory(new PropertyValueFactory<>("Pagada"));
        columnEmpleado.setCellValueFactory(new PropertyValueFactory<>("IdEmpleado"));

        listaCompras = loadListaCompras();
        tableCompras.setItems(loadTableEmpleados());

    }

    public ArrayList<Compra> loadListaCompras(){
        ArrayList<Compra> compra;
        compra = fileCompraManager.readAllLines();

        return compra;
    }

    public ObservableList<Compra> loadTableEmpleados(){
        ObservableList<Compra> data = FXCollections.observableArrayList();

        listaCompras.forEach(compra -> {
            int id = compra.getId();
            String fecha = compra.getFecha();
            int idCliente = compra.getIdCliente();
            String pagada = compra.getPagada();
            int idEmpleado = compra.getIdEmpleado();

            Compra newCompra = new Compra(id, fecha, idCliente, pagada, idEmpleado);
            data.add(newCompra);
        });

        return data;
    }
}
