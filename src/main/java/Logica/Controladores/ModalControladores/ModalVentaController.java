package Logica.Controladores.ModalControladores;
import Logica.Clases.DetalleVenta;
import Logica.Clases.Empleado;
import Logica.Clases.Venta;
import Utils.FileDetalleVentaManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;


public class ModalVentaController {
    // Table columns and view
    @FXML
    TableView<DetalleVenta> tableDetalleVentas;
    @FXML
    TableColumn<DetalleVenta, Integer> columnID;
    @FXML
    TableColumn<DetalleVenta, Integer> columnMaterial;
    @FXML
    TableColumn<DetalleVenta, Integer> columnCantidad;
    @FXML
    TableColumn<DetalleVenta, Long> columnPeso;
    @FXML
    TableColumn<DetalleVenta, Long> columnPrecio;

    // Form inputs
    @FXML
    private TextField inputMaterial;
    @FXML
    private TextField inputCantidad;
    @FXML
    private TextField inputPrecio;
    @FXML
    private TextField inputPeso;

    // Tab buttons
    @FXML
    private Button btnAniadir;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnEditar;

    // Other things
    private Venta venta;
    private final FileDetalleVentaManager fileDetalleVentaManager = new FileDetalleVentaManager("src/main/java/Permanencia/DetalleVenta.txt", "DetalleVenta");
    private ArrayList<DetalleVenta> listaDetalleVentas = new ArrayList<>();
    private static int indice = 0;


    public void initialize(){
        columnID.setCellValueFactory(new PropertyValueFactory<>("IdDetalleVenta"));
        columnMaterial.setCellValueFactory(new PropertyValueFactory<>("IdMaterial"));
        columnCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        columnPeso.setCellValueFactory(new PropertyValueFactory<>("Peso"));
        columnPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));

        if (venta != null) {
            listaDetalleVentas = loadListaDetalleVentas();
            tableDetalleVentas.setItems(loadTableDetalleVentas());
        }

        tableDetalleVentas.setOnMouseClicked(event ->{
            if(!tableDetalleVentas.getSelectionModel().isEmpty()){
                DetalleVenta detalleVenta = tableDetalleVentas.getSelectionModel().getSelectedItem();
                inputMaterial.setText(String.valueOf(detalleVenta.getIdMaterial()));
                inputCantidad.setText(String.valueOf(detalleVenta.getCantidad()));
                inputPeso.setText(String.valueOf(detalleVenta.getPeso()));
                inputPrecio.setText(String.valueOf(detalleVenta.getPrecio()));
                indice = detalleVenta.getIdDetalleVenta();
            }
        });
    }

    public ArrayList<DetalleVenta> loadListaDetalleVentas(){
        ArrayList<DetalleVenta> detalleVentas;
        detalleVentas = fileDetalleVentaManager.readLinesOfVenta();

        ArrayList<DetalleVenta> filteredDetalleVentas = new ArrayList<>();
        for (DetalleVenta dv : detalleVentas) {
            if (dv.getIdVenta() == venta.getIdVenta()) {
                filteredDetalleVentas.add(dv);
            }
        }
        return filteredDetalleVentas;
    }

    public ObservableList<DetalleVenta> loadTableDetalleVentas(){
        ObservableList<DetalleVenta> data = FXCollections.observableArrayList();

        listaDetalleVentas.forEach(detalleVenta -> {
            int id = detalleVenta.getIdDetalleVenta();
            int idMaterial = detalleVenta.getIdMaterial();
            int cantidad = detalleVenta.getCantidad();
            long peso = detalleVenta.getPeso();
            long precio = detalleVenta.getPrecio();

            DetalleVenta newDetalleVenta = new DetalleVenta(id, idMaterial, cantidad, peso, precio, venta.getIdVenta());
            data.add(newDetalleVenta);
        });

        return data;
    }

    public void clearInputs(){
        inputMaterial.setText("");
        inputCantidad.setText("");
        inputPeso.setText("");
        inputPrecio.setText("");
    }

    public boolean checkInputs(){
        if(inputMaterial.getText().isEmpty() || inputCantidad.getText().isEmpty() ||
            inputPeso.getText().isEmpty() || inputPrecio.getText().isEmpty()) {
                System.out.println("There is an empty input");
            return false;
        }
        return true;
    }

    public void setVenta(Venta venta){
        this.venta = venta;
        // Refresh the table with the filtered details
        listaDetalleVentas = loadListaDetalleVentas();
        tableDetalleVentas.setItems(loadTableDetalleVentas());
    }


    @FXML
    public void handleBtnAniadir(){
        if (checkInputs()){
            int idMaterial = Integer.parseInt(inputMaterial.getText());
            int cantidad = Integer.parseInt(inputCantidad.getText());
            long peso = Long.parseLong(inputPeso.getText());
            long precio = Long.parseLong(inputPrecio.getText());
            int id = listaDetalleVentas.isEmpty() ? 1 : listaDetalleVentas.get(listaDetalleVentas.size() - 1).getIdDetalleVenta() + 1;

            DetalleVenta detalleVenta = new DetalleVenta(id, idMaterial, cantidad, peso, precio, venta.getIdVenta());
            listaDetalleVentas.add(detalleVenta);
            fileDetalleVentaManager.writeLine(detalleVenta);

            tableDetalleVentas.setItems(loadTableDetalleVentas());
            clearInputs();
        }
    }

    @FXML
    public void handleBtnEditar(){
        if (checkInputs()){
            int idMaterial = Integer.parseInt(inputMaterial.getText());
            int cantidad = Integer.parseInt(inputCantidad.getText());
            long peso = Long.parseLong(inputPeso.getText());
            long precio = Long.parseLong(inputPrecio.getText());

            DetalleVenta detalleVenta = new DetalleVenta(indice, idMaterial, cantidad, peso, precio, venta.getIdVenta());
            fileDetalleVentaManager.editLine(indice, detalleVenta);
            listaDetalleVentas = loadListaDetalleVentas();
            tableDetalleVentas.setItems(loadTableDetalleVentas());
            clearInputs();
        }
    }

    @FXML
    public void handleBtnEliminar(){
        if (!tableDetalleVentas.getSelectionModel().isEmpty()) {
            DetalleVenta selectedDetalleVenta = tableDetalleVentas.getSelectionModel().getSelectedItem();
            fileDetalleVentaManager.deleteLine(selectedDetalleVenta.getIdDetalleVenta());
            listaDetalleVentas = loadListaDetalleVentas();
            tableDetalleVentas.setItems(loadTableDetalleVentas());
            clearInputs();
        }
    }
}