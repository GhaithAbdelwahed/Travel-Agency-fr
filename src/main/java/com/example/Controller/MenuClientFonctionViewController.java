package com.example.Controller;

import Model.Offre;
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

public class MenuClientFonctionViewController implements Initializable {

    @FXML
    private TextField tfsearch;

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
    private Label montant;


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
    private Button select;

    @FXML
    private Label datearr;

    @FXML
    private Label datedep;

    @FXML
    private Label desrination;


    @FXML
    private Button btnreserver;


    @FXML
    private Button btnretour;

    @FXML
    private TextField idoffre;

    @FXML
    private Label wrong;

    ObservableList<Offre> offrechoix;

    @FXML
    void gotoprincipal(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnretour.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipal-view.fxml"));
        primaryStage.setTitle("menu admin");
        primaryStage.setScene(new Scene(root, 1370, 700));
        primaryStage.show();

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

    @FXML
    void gotocoordonnee(ActionEvent event) throws IOException {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuClientCoordonnee.fxml"));
                    Parent root = loader.load();
                    MenuClientCoordonneeController ccontroller = loader.getController();
                    ccontroller.showInformation(wrong.getText(), montant.getText(), desrination.getText(),datedep.getText(), datearr.getText());
                    if (validatechoix()){

                    Stage stage = (Stage) btnreserver.getScene().getWindow();
                    stage.close();

                    Stage primaryStage = new Stage();
                    primaryStage.setTitle("menu Client");
                    primaryStage.setScene(new Scene(root, 1370, 700));
                    primaryStage.show();
    }}


    public void initialize(URL url, ResourceBundle resourceBundle) {

        showOffre();
        search_offre();
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

    public ObservableList<Offre> getOffreList() {
        ObservableList<Offre> offreList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM offres";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Offre o;
            while (rs.next()) {
                o = new Offre(rs.getInt("ido"), rs.getString("description"), rs.getDate("date_dep"),
                        rs.getDate("date_ret"), rs.getString("type_transport"), rs.getString("nomCompagnie"),
                        rs.getString("destination"), rs.getString("nomHotel"), rs.getString("guide"),
                        rs.getDouble("tarif"));
                offreList.add(o);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return offreList;
    }

    public void showOffre() {
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


    @FXML
    void search_offre() {

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

        offrechoix = getOffreList();
        tvOffres.setItems(offrechoix);



        FilteredList<Offre> filteredData = new FilteredList<>(offrechoix, b -> true);
        tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(off -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(off.getIdo()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else if (off.getDestination().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else return false;

            } );
        } );
        SortedList<Offre> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvOffres.comparatorProperty());
        tvOffres.setItems(sortedData);


    }


    @FXML
    void selectoffre(ActionEvent event) {
            tvOffres.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            wrong.setText(String.valueOf(newValue.getIdo()));
            montant.setText(String.valueOf(newValue.getTarif()));
            desrination.setText(String.valueOf(newValue.getDestination()));
            datedep.setText(String.valueOf(newValue.getDate_dep()));
            datearr.setText(String.valueOf(newValue.getDate_ret()));
        });
    }

    private boolean validatechoix() {
        if (wrong.getText().isEmpty() | montant.getText().isEmpty() | desrination.getText().isEmpty())
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation de réservation");
        alert.setHeaderText(null);
        alert.setContentText("Vous devez choisir une offre avant de réserver");
        alert.showAndWait();
        return false;
    }
        return true;
    }

}



