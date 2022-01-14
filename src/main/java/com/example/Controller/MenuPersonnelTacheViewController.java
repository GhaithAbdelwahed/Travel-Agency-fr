package com.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPersonnelTacheViewController {

    @FXML
    private Button back;

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
    @FXML
    void consulterfac(ActionEvent event) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MenuConsulterFactures.fxml"));
        primaryStage.setTitle("menu Consulter Facture");
        primaryStage.setScene(new Scene(root, 1370, 700));
        primaryStage.show();

    }

    @FXML
    void gestionoff(ActionEvent event) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MenuGestionOffre.fxml"));
        primaryStage.setTitle("menu Gestion Offre");
        primaryStage.setScene(new Scene(root, 1370, 700));
        primaryStage.show();

    }




}
