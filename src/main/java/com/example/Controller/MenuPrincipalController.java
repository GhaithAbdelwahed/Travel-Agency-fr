package com.example.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;



public class MenuPrincipalController {
    //Menu Principal
    @FXML
    private Button idp;

    @FXML
    private Button ida;

    @FXML
    private Button idc;


    @FXML
    public void adminview() throws IOException {
        Stage stage = (Stage) ida.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MenuAdmin-login-view.fxml"));
        primaryStage.setTitle("menu admin");
        primaryStage.setScene(new Scene(root, 1370, 700));
        primaryStage.show();

    }

    public void personnelview() throws IOException {
        Stage stage = (Stage) idp.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MenuPersonnel-Login-view.fxml"));
        primaryStage.setTitle("menu Personnel");
        primaryStage.setScene(new Scene(root, 1370, 700));
        primaryStage.show();

    }

    public void clientview() throws IOException {
        Stage stage = (Stage) idc.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MenuClient_fonction_view.fxml"));
        primaryStage.setTitle("menu Client");
        primaryStage.setScene(new Scene(root, 1370, 700));
        primaryStage.show();
    }

}

