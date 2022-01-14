package com.example.Controller;

import Model.facture;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

public class MenuConsulterFacturesController implements Initializable {
    @FXML
    private TextField tfsearc;

    @FXML
    private Button back;

    @FXML
    private Button btntache;

    @FXML
    private TableColumn<facture, Integer> colcin;

    @FXML
    private TableColumn<facture, Date> coldate;

    @FXML
    private TableColumn<facture, Integer> colidf;

    @FXML
    private TableColumn<facture, Integer> colido;

    @FXML
    private TableColumn<facture, Double> colmontant;

    @FXML
    private TableView<facture> tvfacture;

    public void initialize(URL url, ResourceBundle resourceBundle) {

        showfacture();
        search_f();

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

    public ObservableList<facture> getFactureList() {
        ObservableList<facture> factureList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM facture";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            facture f;
            while (rs.next()) {
                f = new facture(rs.getInt("idf"), rs.getInt("ido"), rs.getDouble("montant"),
                        rs.getDate("date"), rs.getInt("cin"));
                factureList.add(f);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return factureList;
    }

    public void showfacture() {
        ObservableList<facture> list = getFactureList();

        colidf.setCellValueFactory(new PropertyValueFactory<facture, Integer>("idf"));
        colido.setCellValueFactory(new PropertyValueFactory<facture, Integer>("ido"));
        colmontant.setCellValueFactory(new PropertyValueFactory<facture, Double>("montant"));
        coldate.setCellValueFactory(new PropertyValueFactory<facture, Date>("date"));
        colcin.setCellValueFactory(new PropertyValueFactory<facture, Integer>("cin"));


         tvfacture.setItems(list);
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
    @FXML
    void gototache (ActionEvent event) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MenuPersonnel-Tache-view.fxml"));
        primaryStage.setTitle("menu admin");
        primaryStage.setScene(new Scene(root, 1370, 700));
        primaryStage.show();

    }

    @FXML
    void search_f() {

        ObservableList<facture> facturechoix = getFactureList();

        colidf.setCellValueFactory(new PropertyValueFactory<facture, Integer>("idf"));
        colido.setCellValueFactory(new PropertyValueFactory<facture, Integer>("ido"));
        colmontant.setCellValueFactory(new PropertyValueFactory<facture, Double>("montant"));
        coldate.setCellValueFactory(new PropertyValueFactory<facture, Date>("date"));
        colcin.setCellValueFactory(new PropertyValueFactory<facture, Integer>("cin"));


        tvfacture.setItems(facturechoix);



        FilteredList<facture> filteredData = new FilteredList<>(facturechoix, b -> true);
        tfsearc.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(fac -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(fac.getIdf()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else return false;

            } );
        } );
        SortedList<facture> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvfacture.comparatorProperty());
        tvfacture.setItems(sortedData);


    }

}
