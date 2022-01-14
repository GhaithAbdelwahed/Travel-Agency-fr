package com.example.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import Model.Personnel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class GestionPersonnelController implements Initializable {

    @FXML
    private TableColumn<Personnel, String> colfonction;

    @FXML
    private TextField tfprenom;

    @FXML
    private TextField tfcin;

    @FXML
    private TextField tfnum;

    @FXML
    private TableColumn<Personnel, String> colprenom;

    @FXML
    private TableColumn<Personnel, String> coladresse;

    @FXML
    private TextField tfnom;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField tfadresse;

    @FXML
    private TextField tffonction;

    @FXML
    private TableColumn<Personnel, String> colNom;

    @FXML
    private Button btnDelete;

    @FXML
    private Button back;

    @FXML
    private TableView<Personnel> tvPersonnel;

    @FXML
    private TableColumn<Personnel, Integer> colCin;

    @FXML
    private TableColumn<Personnel, Integer> colnum;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showPersonnel();
    }

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

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if(event.getSource() == btnInsert){
            InserHandle();
        }else if (event.getSource() == btnUpdate){
            updateRecord();
        }else if(event.getSource() == btnDelete){
            deleteButton();
        }

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

    public ObservableList<Personnel> getPersonnelList(){
        ObservableList<Personnel> PersonnelList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM personnel";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Personnel books;
            while(rs.next()){
                books = new Personnel(rs.getInt("cin"), rs.getString("nom"), rs.getString("prenom"), rs.getInt("num"), rs.getString("adresse"), rs.getString("fonction"));
                PersonnelList.add(books);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return PersonnelList;
    }

    public void showPersonnel(){
        ObservableList<Personnel> list = getPersonnelList();

        colCin.setCellValueFactory(new PropertyValueFactory<Personnel, Integer>("cin"));
        colNom.setCellValueFactory(new PropertyValueFactory<Personnel, String>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<Personnel, String>("prenom"));
        colnum.setCellValueFactory(new PropertyValueFactory<Personnel, Integer>("num"));
        coladresse.setCellValueFactory(new PropertyValueFactory<Personnel, String>("adresse"));
        colfonction.setCellValueFactory(new PropertyValueFactory<Personnel, String>("fonction"));


        tvPersonnel.setItems(list);
    }

    @FXML
    private void InserHandle(){
        if (validatefieldInsert()) {
            String query = "INSERT INTO personnel VALUES (" + tfcin.getText() + ",'" + tfnom.getText() + "','" + tfprenom.getText() + "',"
                    + tfnum.getText() + ",'" + tfadresse.getText() + "','" + tffonction.getText() + "')";
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Personnel Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Personnel insérer avec succés !");
            alert.showAndWait();

            executeQuery(query);
            showPersonnel();
        }
    }
    private boolean validatefieldInsert(){
        if (tfcin.getText().isEmpty() | tfnom.getText().isEmpty() | tfprenom.getText().isEmpty()
        | tfnum.getText().isEmpty() | tfadresse.getText().isEmpty() | tffonction.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("Tout les champs doivent être remplis");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private void updateRecord(){
        String query = "UPDATE  personnel SET nom  = '" + tfnom.getText() + "', prenom = '" +
                tfprenom.getText() + "', num = " + tfnum.getText() + ", adresse = '" + tfadresse.getText() + "', fonction = '" + tffonction.getText() + "' WHERE cin = " + tfcin.getText() + "";

        executeQuery(query);
        showPersonnel();
    }

    private void deleteButton(){
        String query = "DELETE FROM personnel WHERE cin =" + tfcin.getText() + "";

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Personnel Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Personnel supprimé avec succés !");

        alert.showAndWait();
        executeQuery(query);
        showPersonnel();
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}