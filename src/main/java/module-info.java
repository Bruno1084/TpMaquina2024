module org.example.tpmaquina2024_javafx {
    requires javafx.controls;
    requires javafx.fxml;

    opens GUI to javafx.fxml;
    exports GUI;

    opens Logica.Controladores to javafx.fxml;
    opens Logica.Clases to javafx.base;
}
