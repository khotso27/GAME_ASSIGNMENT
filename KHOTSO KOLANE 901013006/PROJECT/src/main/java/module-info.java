module com.example.gamepro {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.gamepro to javafx.fxml;
    exports com.example.gamepro;
}