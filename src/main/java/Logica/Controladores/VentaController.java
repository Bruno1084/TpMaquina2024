package Logica.Controladores;
import Logica.Clases.Venta;
import Logica.Controladores.ModalControladores.ModalVentaController;
import Utils.FileVentaManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;


public class VentaController {
    // Table columns and view
    @FXML
    private TableView<Venta> tableVentas;
    @FXML
    private TableColumn<Venta, Integer> columnID;
    @FXML
    private TableColumn<Venta, Integer> columnIdProveedor;
    @FXML
    private TableColumn columnDetalleVenta;
    @FXML
    private TableColumn<Venta, String> columnFechaVenta;
    @FXML
    private TableColumn<Venta, Boolean> columnDespachado;

    // Form inputs
    @FXML
    private TextField inputProveedor;
    @FXML
    private TextField inputDespachado;
    @FXML
    private TextField inputFechaVenta;

    // Tab buttons
    @FXML
    private Button btnAniadir;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnRegistrarVenta;

    // Other things
    private ArrayList<Venta> listaVentas = new ArrayList<>();
    private static int indice = 0;

    public void initialize(){
        columnID.setCellValueFactory(new PropertyValueFactory<>("IdVenta"));
        columnIdProveedor.setCellValueFactory(new PropertyValueFactory<>("IdProveedor"));
        columnFechaVenta.setCellValueFactory(new PropertyValueFactory<>("FechaVenta"));
        columnDespachado.setCellValueFactory(new PropertyValueFactory<>("Despachado"));

        //Don't touch
        columnDetalleVenta.setCellFactory(param -> {
            final TableCell<Venta, String> cell = new TableCell<>(){

                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);

                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else{
                        final Button detalleButton = new Button("Detalle");
                        detalleButton.setOnAction(event -> {
                            Venta venta = getTableView().getItems().get(getIndex());
                            handleDetalleButton(venta);
                        });
                        setGraphic(detalleButton);
                        setText(null);
                    }
                }
            };
            return cell;
        });

        listaVentas = loadListaVentas();
        tableVentas.setItems(loadTableVentas());

        tableVentas.setOnMouseClicked(event ->{
            if(!tableVentas.getSelectionModel().isEmpty()){
                Venta venta = tableVentas.getSelectionModel().getSelectedItem();
                inputProveedor.setText(String.valueOf(venta.getIdProveedor()));
                inputDespachado.setText(String.valueOf(venta.isDespachado()));
                inputFechaVenta.setText(venta.getFechaVenta());

                indice = venta.getIdVenta();
            }
        });
    }


    public ArrayList<Venta> loadListaVentas(){
        ArrayList<Venta> data;
        data = FileVentaManager.readAllLines();

        return data;
    }

    public ObservableList<Venta> loadTableVentas(){
        ObservableList<Venta> data = FXCollections.observableArrayList();

        listaVentas.forEach(venta -> {
            int id = venta.getIdVenta();
            int idProveedor = venta.getIdProveedor();
            String fechaVenta = venta.getFechaVenta();
            boolean despachado = venta.isDespachado();

            Venta newVenta = new Venta(id, idProveedor, fechaVenta, despachado);
            data.add(newVenta);
        });

        return data;
    }

    public Venta getLastVenta(){
        return listaVentas.get(listaVentas.size() -1);
    }

    public void clearInputs(){
        inputFechaVenta.setText("");
        inputDespachado.setText("");
        inputProveedor.setText("");
    }

    public boolean checkInputs(){
        if(inputFechaVenta.getText().isEmpty() || inputDespachado.getText().isEmpty() ||
                inputProveedor.getText().isEmpty()) {
            System.out.println("There is an empty input");
            return false;
        }
        return true;
    }

    public void handleDetalleButton(Venta venta){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/ModalControladores/modalDetalleVenta.fxml"));
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(fxmlLoader.load()));

            ModalVentaController controller = fxmlLoader.getController();
            controller.setVenta(venta); // Pasar la venta seleccionada al controlador del modal

            secondStage.setResizable(false);
            secondStage.show();
        }catch (IOException exception){
            System.out.println("Error path is incorrect");
            exception.printStackTrace();
        }
    }


    @FXML
    public void handleBtnAniadir(){
        if (checkInputs()){
            int idProveedor = Integer.parseInt(inputProveedor.getText());
            String fechaVenta = inputFechaVenta.getText();
            boolean isDespachado = Boolean.getBoolean(inputDespachado.getText());
            int id = listaVentas.isEmpty() ? 1 : listaVentas.get(listaVentas.size() - 1).getIdVenta() + 1;

            Venta venta = new Venta(id, idProveedor, fechaVenta, isDespachado);
            listaVentas.add(venta);
            FileVentaManager.writeLine(venta);

            tableVentas.setItems(loadTableVentas());
            clearInputs();
        }
    }
    @FXML
    public void handleBtnEditar(){
        if (checkInputs()){
            int idProveedor = Integer.parseInt(inputProveedor.getText());
            String fechaVenta = inputFechaVenta.getText();
            boolean isDespachado = Boolean.getBoolean(inputDespachado.getText());

            Venta venta = new Venta(indice, idProveedor, fechaVenta, isDespachado);
            FileVentaManager.editLine(indice, venta);
            listaVentas = loadListaVentas();
            tableVentas.setItems(loadTableVentas());
            clearInputs();
        }
    }
    @FXML
    public void handleBtnEliminar(){
        if (!tableVentas.getSelectionModel().isEmpty()) {
            Venta selectedVenta = tableVentas.getSelectionModel().getSelectedItem();
            FileVentaManager.deleteLine(selectedVenta.getIdVenta());
            listaVentas = loadListaVentas();
            tableVentas.setItems(loadTableVentas());
            clearInputs();
        }
    }

    @FXML
    public void handleBtnRegistrarVenta(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/ModalControladores/modalRegistrarVenta.fxml"));
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(fxmlLoader.load()));
            secondStage.setResizable(false);
            secondStage.show();
        }catch (IOException exception){
            System.out.println("Error on HandleBtnRegistrarVenta on VentaController");
            exception.printStackTrace();
        }
    }
}