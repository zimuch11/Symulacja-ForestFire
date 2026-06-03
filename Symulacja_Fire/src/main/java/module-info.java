module com.example.symulacja_fire {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.symulacja_fire to javafx.fxml;
    exports com.example.symulacja_fire;
}