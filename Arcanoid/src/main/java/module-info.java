module com.example.arcanoid {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.arcanoid to javafx.fxml;
    exports com.example.arcanoid;
    exports view;
    opens view to javafx.fxml;
}