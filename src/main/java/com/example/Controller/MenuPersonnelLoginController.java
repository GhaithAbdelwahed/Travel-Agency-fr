package com.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class MenuPersonnelLoginController {



    @FXML
    private Button back;

    @FXML
    private Button buttonloginpersonnel;

    @FXML
    private TextField tfname;

    @FXML
    private PasswordField tfpassword;
    @FXML
    private Label wrongLogin;


    @FXML
    void GoToPrincipal(ActionEvent event)  throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipal-view.fxml"));
        primaryStage.setTitle("menu Principal");
        primaryStage.setScene(new Scene(root, 1370, 700));
        primaryStage.show();

    }
    private void checkLogin() throws IOException {
        if (tfname.getText().toString().equals("personnel") && tfpassword.getText().toString().equals("personnel")) {

            Stage stage = (Stage) buttonloginpersonnel.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("MenuPersonnel-Tache-view.fxml"));
            primaryStage.setTitle("menu Personnel");
            primaryStage.setScene(new Scene(root, 1370, 700));
            primaryStage.show();
        }
        else if (tfname.getText().isEmpty() && tfpassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Personnel Warning");
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
    void personnellogin(ActionEvent event) throws IOException {
        checkLogin();

    }

}

