package com.example.Controller;

import Model.Offre;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

public class MenuGestionOffreController implements Initializable {
    @FXML
    private TextField btnsearch;

    @FXML
    private Button search;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnRetour;

    @FXML
    private Button btnSupprimer;

    @FXML
    private Button btntache;

    @FXML
    private TableColumn<Offre, Integer> colid;

    @FXML
    private TableColumn<Offre, Date> coldate_d;

    @FXML
    private TableColumn<Offre, Date> coldate_r;

    @FXML
    private TableColumn<Offre, String> coldescription;

    @FXML
    private TableColumn<Offre, String> coltypedetransport;

    @FXML
    private TableColumn<Offre, String> colnomcompagnie;

    @FXML
    private TableColumn<Offre, String> colnomhotel;

    @FXML
    private TableColumn<Offre, String> colguide;

    @FXML
    private TableColumn<Offre, String> coldestination;

    @FXML
    private TableColumn<Offre, Double> coletarif;

    @FXML
    private TableView<Offre> tvOffres;


    @FXML
    private TextField tfid;

    @FXML
    private TextField tfdate_d;

    @FXML
    private TextField tfdate_r;

    @FXML
    private TextField tfdescription;

    @FXML
    private TextField tfdestination;

    @FXML
    private TextField tftarif;

    @FXML
    private TextField tfguide;

    @FXML
    private TextField tfnomcompagnie;

    @FXML
    private TextField tfnomhotel;

    @FXML
    private TextField tftypedetransport;

    @FXML
    private DatePicker tfdated;

    @FXML
    private DatePicker tfdater;

    @FXML
    void GoToPrincipal(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnRetour.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipal-view.fxml"));
        primaryStage.setTitle("menu Personnel");
        primaryStage.setScene(new Scene(root, 1370, 700));
        primaryStage.show();


    }
    @FXML
    void gototache (ActionEvent event) throws IOException {
        Stage stage = (Stage) btnRetour.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MenuPersonnel-Tache-view.fxml"));
        primaryStage.setTitle("menu Personnel View");
        primaryStage.setScene(new Scene(root, 1370, 700));
        primaryStage.show();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showOffre();
    }

    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agencedevoyage", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public ObservableList<Offre> getOffreList(){
        ObservableList<Offre> offreList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM offres";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Offre o;
            while(rs.next()){
                o = new Offre(rs.getInt("ido"), rs.getString("description"), rs.getDate("date_dep"),
                        rs.getDate("date_ret"), rs.getString("type_transport"), rs.getString("nomCompagnie"),
                        rs.getString("destination"),rs.getString("nomHotel"),rs.getString("guide"),
                        rs.getDouble("tarif"));
                offreList.add(o);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return offreList;
    }

    public void showOffre(){
        ObservableList<Offre> list = getOffreList();

        colid.setCellValueFactory(new PropertyValueFactory<Offre, Integer>("ido"));
        coldescription.setCellValueFactory(new PropertyValueFactory<Offre, String>("description"));
        coldate_d.setCellValueFactory(new PropertyValueFactory<Offre, Date>("date_dep"));
        coldate_r.setCellValueFactory(new PropertyValueFactory<Offre, Date>("date_ret"));
        coltypedetransport.setCellValueFactory(new PropertyValueFactory<Offre, String>("type_transport"));
        colnomcompagnie.setCellValueFactory(new PropertyValueFactory<Offre, String>("nomCompagnie"));
        coldestination.setCellValueFactory(new PropertyValueFactory<Offre, String>("destination"));
        colnomhotel.setCellValueFactory(new PropertyValueFactory<Offre, String>("nomHotel"));
        colguide.setCellValueFactory(new PropertyValueFactory<Offre, String>("guide"));
        coletarif.setCellValueFactory(new PropertyValueFactory<Offre, Double>("tarif"));


        tvOffres.setItems(list);
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

    private boolean validatefieldInsert(){
        if (tfid.getText().isEmpty() | tfdescription.getText().isEmpty() | tfdestination.getText().isEmpty()
                | tfguide.getText().isEmpty() | tfnomcompagnie.getText().isEmpty()
                | tfnomhotel.getText().isEmpty() | tftypedetransport.getText().isEmpty()
                | tftarif.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("Tout les champs doivent être remplis");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    @FXML
    void handleInsertButton(ActionEvent event) {
        if (validatefieldInsert()){
        String query = "INSERT INTO offres VALUES (" + tfid.getText() + ",'" + tfdescription.getText() + "','"  + tfdated.getValue().toString() + "','"
                + tfdater.getValue().toString() + "','"  + tfdestination.getText() + "','"  + tftypedetransport.getText() + "','"  + tfnomcompagnie.getText() + "','"
                + tfnomhotel.getText() + "','"  + tfguide.getText() + "'," + tftarif.getText() + ")";
        executeQuery(query);
        showOffre();
        }
    }

    @FXML
    void handleModifierBtn(ActionEvent event) {

        String query = "UPDATE  offres SET description  = '" + tfdescription.getText() + "', date_dep = '" +
                tfdated.getValue().toString() + "', date_ret = '" + tfdater.getValue().toString() + "', destination = '" + tfdestination.getText() + "', type_transport = '" + tftypedetransport.getText() + "', nomCompagnie = '" + tfnomcompagnie.getText() +
                "', nomHotel = '" + tfnomhotel.getText() + "', guide = '" + tfguide.getText() + "', tarif = " + tftarif.getText() + " WHERE ido = " + tfid.getText() + "";
        executeQuery(query);
        showOffre();

    }

    @FXML
    void handleSupprimerBtn(ActionEvent event) {

        String query = "DELETE FROM offres WHERE ido =" + tfid.getText() + "";
        executeQuery(query);
        showOffre();
    }

    @FXML
    void search(ActionEvent event) {
        ObservableList<Offre> liste1 = getOffreList();
        ObservableList<Offre> search =FXCollections.observableArrayList();


        colid.setCellValueFactory(new PropertyValueFactory<Offre, Integer>("ido"));
        coldescription.setCellValueFactory(new PropertyValueFactory<Offre, String>("description"));
        coldate_d.setCellValueFactory(new PropertyValueFactory<Offre, Date>("date_dep"));
        coldate_r.setCellValueFactory(new PropertyValueFactory<Offre, Date>("date_ret"));
        coltypedetransport.setCellValueFactory(new PropertyValueFactory<Offre, String>("type_transport"));
        colnomcompagnie.setCellValueFactory(new PropertyValueFactory<Offre, String>("nomCompagnie"));
        coldestination.setCellValueFactory(new PropertyValueFactory<Offre, String>("destination"));
        colnomhotel.setCellValueFactory(new PropertyValueFactory<Offre, String>("nomHotel"));
        colguide.setCellValueFactory(new PropertyValueFactory<Offre, String>("guide"));
        coletarif.setCellValueFactory(new PropertyValueFactory<Offre, Double>("tarif"));



        if (btnsearch.getText().isEmpty()){
            tvOffres.setItems(liste1);
        }
        else {
            liste1.stream().filter(e->String.valueOf(e.getIdo()).equals(btnsearch.getText())).forEach(f->search.add(f));
            tvOffres.setItems(search);
        }

    }

}

