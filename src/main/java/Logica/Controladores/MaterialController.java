package Logica.Controladores;
import Logica.Clases.Material;
import Utils.FileMaterialManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;


public class MaterialController {
    @FXML
    private TableView<Material> tableMateriales;
    @FXML
    private TableColumn<Material, Integer> columnID;
    @FXML
    private TableColumn<Material, String> columnNombre;
    @FXML
    private TableColumn<Material, String> columnDescripcion;
    @FXML
    private TableColumn<Material, String> columnMedida;
    @FXML
    private TableColumn<Material, Integer> columnStock;
    @FXML
    private TableColumn<Material, Float> columnPrecioCompra;
    @FXML
    private TableColumn<Material, Float> columnPrecioVenta;

    // Form inputs
    @FXML
    private TextField inputNombre;
    @FXML
    private TextArea inputDescripcion;
    @FXML
    private TextField inputTipoMedida;
    @FXML
    private TextField inputStock;
    @FXML
    private TextField inputPrecioCompra;
    @FXML
    private TextField inputPrecioVenta;

    // Tab buttons
    @FXML
    private Button btnAniadir;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnEditar;

    // Other things
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

        listaMateriales = loadListaMateriales();
        tableMateriales.setItems(loadTableMateriales());

        tableMateriales.setOnMouseClicked(event ->{
            if(!tableMateriales.getSelectionModel().isEmpty()){
                Material material = tableMateriales.getSelectionModel().getSelectedItem();
                inputNombre.setText(material.getNombre());
                inputDescripcion.setText(material.getDescripcion());
                inputTipoMedida.setText(material.getTipoMedida());
                inputStock.setText(String.valueOf(material.getStock()));
                inputPrecioCompra.setText(String.valueOf(material.getPrecioCompra()));
                inputPrecioVenta.setText(String.valueOf(material.getPrecioVenta()));
                indice = material.getId();
            }
        });
    }

    public ArrayList<Material> loadListaMateriales(){
        ArrayList<Material> material;
        material = FileMaterialManager.readAllLines();

        return material;
    }

    public ObservableList<Material> loadTableMateriales(){
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

    public Material getLastMaterial(){
        return listaMateriales.get(listaMateriales.size() -1);
    }

    public void clearInputs(){
        inputNombre.setText("");
        inputDescripcion.setText("");
        inputTipoMedida.setText("");
        inputStock.setText("");
        inputPrecioCompra.setText("");
        inputPrecioVenta.setText("");
    }

    public boolean checkInputs(){
        if(inputNombre.getText().isEmpty() || inputDescripcion.getText().isEmpty() ||
                inputTipoMedida.getText().isEmpty() || inputStock.getText().isEmpty() ||
                inputPrecioCompra.getText().isEmpty() || inputPrecioVenta.getText().isEmpty()) {
            System.out.println("There is an empty input");
            return false;
        }
        return true;
    }

    @FXML
    public void handleBtnAniadir(){
        if (checkInputs()){
            String nombre = inputNombre.getText();
            String descripcion = inputDescripcion.getText();
            String tipoMedida = inputTipoMedida.getText();
            int stock = Integer.parseInt(inputStock.getText());
            float precioCompra = Float.parseFloat(inputPrecioCompra.getText());
            float precioVenta = Float.parseFloat(inputPrecioVenta.getText());
            int id = listaMateriales.isEmpty() ? 1 : listaMateriales.get(listaMateriales.size() - 1).getId() + 1;

            Material material = new Material(id, nombre, descripcion, tipoMedida, stock, precioCompra, precioVenta);
            listaMateriales.add(material);
            FileMaterialManager.writeLine(material);
            tableMateriales.setItems(loadTableMateriales());
            clearInputs();
        }
    }

    @FXML
    public void handleBtnEditar(){
        if (checkInputs()){
            String nombre = inputNombre.getText();
            String descripcion = inputDescripcion.getText();
            String tipoMedida = inputTipoMedida.getText();
            int stock = Integer.parseInt(inputStock.getText());
            float precioCompra = Float.parseFloat(inputPrecioCompra.getText());
            float precioVenta = Float.parseFloat(inputPrecioVenta.getText());

            Material material = new Material(indice, nombre, descripcion, tipoMedida, stock, precioCompra, precioVenta);
            FileMaterialManager.editLine(indice, material);
            listaMateriales = loadListaMateriales();
            tableMateriales.setItems(loadTableMateriales());
            clearInputs();
        }
    }

    @FXML
    public void handleBtnEliminar(){
        if (!tableMateriales.getSelectionModel().isEmpty()) {
            Material selectedMaterial = tableMateriales.getSelectionModel().getSelectedItem();
            FileMaterialManager.deleteLine(selectedMaterial.getId());
            listaMateriales = loadListaMateriales();
            tableMateriales.setItems(loadTableMateriales());
            clearInputs();
        }
    }
}
