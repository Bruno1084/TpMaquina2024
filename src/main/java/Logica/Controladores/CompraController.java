package Logica.Controladores;
import Logica.Clases.Compra;
import Utils.FileCompraManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
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

    // Form inputs
    @FXML
    private TextField inputFecha;
    @FXML
    private TextField inputPagado;
    @FXML
    private TextField inputCliente;
    @FXML
    private TextField inputEmpleado;

    // Tab buttons
    @FXML
    private Button btnAniadir;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnRegistrarCompra;

    // Other things
    private ArrayList<Compra> listaCompras = new ArrayList<>();
    private static int indice = 0;


    public void initialize(){
        columnID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        columnFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        columnCliente.setCellValueFactory(new PropertyValueFactory<>("IdCliente"));
        columnPagado.setCellValueFactory(new PropertyValueFactory<>("Pagada"));
        columnEmpleado.setCellValueFactory(new PropertyValueFactory<>("IdEmpleado"));

        listaCompras = loadListaCompras();
        tableCompras.setItems(loadTableCompra());

        tableCompras.setOnMouseClicked(event ->{
            if(!tableCompras.getSelectionModel().isEmpty()){
                Compra compra = tableCompras.getSelectionModel().getSelectedItem();
                inputFecha.setText(compra.getFecha());
                inputPagado.setText(compra.getPagada());
                inputCliente.setText(String.valueOf(compra.getIdCliente()));
                inputEmpleado.setText(String.valueOf(compra.getIdEmpleado()));
                indice = compra.getId();
            }
        });
    }

    public ArrayList<Compra> loadListaCompras(){
        ArrayList<Compra> compra;
        compra = FileCompraManager.readAllLines();

        return compra;
    }

    public ObservableList<Compra> loadTableCompra(){
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

    public Compra getLastCompra(){
        return listaCompras.get(listaCompras.size() -1);
    }

    public void clearInputs(){
        inputFecha.setText("");
        inputPagado.setText("");
        inputEmpleado.setText("");
        inputCliente.setText("");
    }

    public boolean checkInputs(){
        if(inputFecha.getText().isEmpty() || inputPagado.getText().isEmpty() ||
                inputCliente.getText().isEmpty() || inputEmpleado.getText().isEmpty()) {
            System.out.println("There is an empty input");
            return false;
        }
        return true;
    }

    @FXML
    public void handleBtnAniadir(){
        if (checkInputs()){
            String fecha = inputFecha.getText();
            String pagado = inputPagado.getText();
            int idEmpleado = Integer.parseInt(inputEmpleado.getText());
            int idCliente = Integer.parseInt(inputCliente.getText());
            int id = listaCompras.isEmpty() ? 1 : listaCompras.get(listaCompras.size() - 1).getId() + 1;

            Compra compra = new Compra(id, fecha,idCliente, pagado, idEmpleado);
            listaCompras.add(compra);
            FileCompraManager.writeLine(compra);

            tableCompras.setItems(loadTableCompra());
            clearInputs();
        }
    }

    @FXML
    public void handleBtnEditar(){
        if (checkInputs()){
            String fecha = inputFecha.getText();
            String pagado = inputPagado.getText();
            int idEmpleado = Integer.parseInt(inputEmpleado.getText());
            int idCliente = Integer.parseInt(inputCliente.getText());

            Compra compra = new Compra(indice, fecha, idCliente, pagado, idEmpleado);
            FileCompraManager.editLine(indice, compra);
            listaCompras = loadListaCompras();
            tableCompras.setItems(loadTableCompra());
            clearInputs();
        }
    }

    @FXML
    public void handleBtnEliminar(){
        if (!tableCompras.getSelectionModel().isEmpty()) {
            Compra selectedCompra = tableCompras.getSelectionModel().getSelectedItem();
            FileCompraManager.deleteLine(selectedCompra.getId());
            listaCompras = loadListaCompras();
            tableCompras.setItems(loadTableCompra());
            clearInputs();
        }
    }

    @FXML
    public void handleBtnRegistrarCompra() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/ModalControladores/modalRegistrarCompra.fxml"));
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(fxmlLoader.load()));

            secondStage.setResizable(false);
            secondStage.show();
        } catch (IOException exception){
            System.out.println("Error on method handleBtnRegistrarCompra");
            exception.printStackTrace();
        }
    }
}
