package Logica.Controladores;
import Logica.Clases.Venta;
import Logica.Controladores.ModalControladores.ModalVentaController;
import Utils.FileVentaManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;


public class VentaController {
    @FXML
    private TableView<Venta> tableVentas;
    @FXML
    private TableColumn<Integer, Venta> columnID;
    @FXML
    private TableColumn<Integer, Venta> columnIdProveedor;
    @FXML
    private TableColumn columnDetalleVenta;
    @FXML
    private TableColumn<String, Venta> columnFechaVenta;

    @FXML
    private TableColumn<Boolean, Venta> columnDespachado;

    private final FileVentaManager fileVentaManager = new FileVentaManager("src/main/java/Permanencia/Venta.txt", "Venta");
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
    }


    public ArrayList<Venta> loadListaVentas(){
        ArrayList<Venta> data;
        data = fileVentaManager.readAllLines();

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

    public void handleDetalleButton(Venta venta){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/ModalControladores/modalDetalleVenta.fxml"));
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(fxmlLoader.load()));

            ModalVentaController controller = fxmlLoader.getController();
            controller.setVenta(venta);

            //fxmlLoader.setController(controller);
            secondStage.setResizable(false);
            secondStage.show();
        }catch (IOException exception){
            System.out.println("Error path is incorrect");
            exception.printStackTrace();
        }
    }
}