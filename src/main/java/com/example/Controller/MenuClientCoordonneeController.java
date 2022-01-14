package com.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;

public class MenuClientCoordonneeController {

    @FXML
    private Button btnok;

    @FXML
    private Button btnprincipal;

    @FXML
    private Button btnretour;

    @FXML
    private TextField tfadresse;

    @FXML
    private TextField tfcin;

    @FXML
    private Label labelID;

    @FXML
    private Label labelMontant;

    @FXML
    private Label labeldatearr;

    @FXML
    private Label labeldatedep;

    @FXML
    private Label labeldestination;


    @FXML
    private TextField tfnom;

    @FXML
    private TextField tfnumero;
    Date d = Date.valueOf(java.time.LocalDate.now());


    @FXML
    private TextField tfprenom;

    public void showInformation(String id, String montant, String destination, String date_dep, String date_arr) {
        labelID.setText(id);
        labelMontant.setText(montant);
        labeldestination.setText(destination);
        labeldatedep.setText(date_arr);
        labeldatearr.setText(date_arr);

    }

    @FXML
    void gotofacture(ActionEvent event) throws IOException {
        if (validateCoordonné()) {
            // 1- Insertion dans La table Client
            String query = "INSERT INTO clients VALUES (" + tfcin.getText() + ",'" + tfnom.getText() + "','" + tfprenom.getText() + "','"
                    + tfadresse.getText() + "'," + tfnumero.getText() + ")";
            executeQuery(query);

            // 2- Insertion dans La table facture

            String s = null; // auto increment
            String query1 = "INSERT INTO facture VALUES (" + null + "," + Integer.parseInt(labelID.getText()) + "," + Double.parseDouble(labelMontant.getText()) + ",'"
                    + d.toString() + "'," + Integer.parseInt(tfcin.getText()) + ")";
            executeQuery(query1);
            // 3- Passer à la scene suivante
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuFactureClient.fxml"));
            Parent root = loader.load();
            MenuFactureClientController ccontroller = loader.getController();
            ccontroller.showInfo(labelID.getText(), labelMontant.getText(), tfnom.getText(), tfprenom.getText(), tfcin.getText(), labeldestination.getText(), labeldatedep.getText(), labeldatearr.getText());

            Stage stage = (Stage) btnok.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            primaryStage.setTitle("menu Consulter Facture");
            primaryStage.setScene(new Scene(root, 1370, 700));
            primaryStage.show();
        }
    }

    @FXML
    void gotoprincipal(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnprincipal.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipal-view.fxml"));
        primaryStage.setTitle("menu Principal");
        primaryStage.setScene(new Scene(root, 1370, 700));
        primaryStage.show();
    }

    @FXML
    void gotoretour(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnretour.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MenuClient_fonction_view.fxml"));
        primaryStage.setTitle("menu Client");
        primaryStage.setScene(new Scene(root, 1370, 700));
        primaryStage.show();
    }


    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agencedevoyage", "root", "");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }


    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean validateCoordonné() {
        if (tfcin.getText().isEmpty() | tfnom.getText().isEmpty() | tfadresse.getText().isEmpty()
                | tfnumero.getText().isEmpty() | tfprenom.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("Tout les champs doivent être remplis");
            alert.showAndWait();
            return false;
        }
        return true;
    }
}


