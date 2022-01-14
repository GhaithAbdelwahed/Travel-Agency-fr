module com.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires jdk.compiler;
    requires kernel;
    requires itextpdf;


    opens com.example.Controller to javafx.fxml;
    exports com.example.Controller;
    exports Model;
    opens Model to javafx.fxml;
}