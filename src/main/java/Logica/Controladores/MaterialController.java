package Logica.Controladores;
import Logica.Clases.Cliente;
import Logica.Clases.Material;
import Utils.FileMaterialManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;


public class MaterialController {
    @FXML
    private TableView<Material> tableMateriales;
    @FXML
    private TableColumn<Integer, Material> columnID;
    @FXML
    private TableColumn<String, Material> columnNombre;
    @FXML
    private TableColumn<String, Material> columnDescripcion;
    @FXML
    private TableColumn<String, Material> columnMedida;
    @FXML
    private TableColumn<Integer, Material> columnStock;
    @FXML
    private TableColumn<Float, Material> columnPrecioCompra;
    @FXML
    private TableColumn<Float, Material> columnPrecioVenta;

    private final FileMaterialManager fileMaterialManager = new FileMaterialManager("src/main/java/Permanencia/Material.txt", "Material");
    private ArrayList<Material> listaMateriales = new ArrayList<>();
    private static int indice = 0;



    public void initialize(){
        columnID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        columnDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        columnMedida.setCellValueFactory(new PropertyValueFactory<>("TipoMedida"));
        columnStock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        columnPrecioCompra.setCellValueFactory(new PropertyValueFactory<>("PrecioCompra"));
        columnPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("PrecioVenta"));

        listaMateriales = loadListaEmpleados();
        tableMateriales.setItems(loadTableEmpleados());
    }

    public ArrayList<Material> loadListaEmpleados(){
        ArrayList<Material> material;
        material = fileMaterialManager.readAllLines();

        return material;
    }

    public ObservableList<Material> loadTableEmpleados(){
        ObservableList<Material> data = FXCollections.observableArrayList();

        listaMateriales.forEach(material -> {
            int id = material.getId();
            String nombre = material.getNombre();
            String descripcion = material.getDescripcion();
            String tipoMedida = material.getTipoMedida();
            int stock = material.getStock();
            float precioVenta = material.getPrecioVenta();
            float precioCompra = material.getPrecioCompra();

            Material newMaterial = new Material(id, nombre, descripcion, tipoMedida, stock, precioCompra, precioVenta);
            data.add(newMaterial);
        });

        return data;
    }
}
