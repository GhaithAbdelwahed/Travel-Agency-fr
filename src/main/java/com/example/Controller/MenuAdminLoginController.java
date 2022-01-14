package com.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class MenuAdminLoginController {
    @FXML
    private PasswordField password;

    @FXML
    private Button buttonloginadmin;

    @FXML
    private TextField username;

    @FXML
    private Label wrongLogin;

    @FXML
    private Button back;

    @FXML
    void adminlogin(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {

        if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {

            Stage stage = (Stage) buttonloginadmin.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("GestionPersonnel.fxml"));
            primaryStage.setTitle("menu Gestion Personnel");
            primaryStage.setScene(new Scene(root, 1370, 700));
            primaryStage.show();
        }
        else if (username.getText().isEmpty() && password.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Admin Warning");
            alert.setHeaderText(null);
            alert.setContentText("Tous le champs doivent être remplis !");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Admin Warning");
            alert.setHeaderText(null);
            alert.setContentText("Le Username ou le Password est incorrect !");
            alert.showAndWait();
        }
    }
    @FXML
    void GoToPrincipal(ActionEvent event) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipal-view.fxml"));
        primaryStage.setTitle("menu Principal");
        primaryStage.setScene(new Scene(root, 1370, 700));
        primaryStage.show();
    }

}
