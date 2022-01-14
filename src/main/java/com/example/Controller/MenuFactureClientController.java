package com.example.Controller;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import static javafx.application.Platform.exit;

public class MenuFactureClientController {
    LocalDateTime myDateObj = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String formattedDate = myDateObj.format(myFormatObj);
    String s = "";


    @FXML
    private Button btnPrint;

    @FXML
    private Label labelnumfact;

    @FXML
    private Label labelDatearr;

    @FXML
    private Label labelDatedep;

    @FXML
    private Label labelDest;

    @FXML
    private Label labelCin;

    @FXML
    private Label labelID;

    @FXML
    private Label labelMontant;

    @FXML
    private Label labelNom;

    @FXML
    private Label labelPrenom;

    @FXML
    private Button btnconfirmer;


    @FXML
    private Button btnretour;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_montant;

    @FXML
    private Label labeldate;



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
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


    @FXML
    void print(ActionEvent event) {
        Document docu = new Document();
        try {
            PdfWriter.getInstance(docu, new FileOutputStream("factureClient.pdf"));
            docu.open();

            docu.add(new Paragraph("        ******************* Facture ******************* " + ""
                    +"\n\n GOGO & THABOUTA TRAVEL AGENCY"
                    +"\n 22, Avenue Enicarthage"
                    +"\nTelephone: +216 93583860 " +
                     "\n             +216 90179039"
                    + "\n\n\n Nom : " + labelNom.getText() + ""
                    + "\n Prenom: " +labelPrenom.getText() + ""
                    + "\n CIN : " + labelCin.getText()+ ""
                    + "\n Destination : "+ labelDest.getText()+""
                    +" \n Date de départ : "+ labelDatedep.getText() + ""
                    +" \n Date arrivée : " + labelDatearr.getText() + ""
                    + "\n Montant à payer : " + labelMontant.getText() +""
                    + "\n\n\n                                         Fait à Tunis le: "+ formattedDate +""
                    + "\n                                             Signature", FontFactory.getFont(FontFactory.TIMES_ROMAN,18,Font.NORMAL,BaseColor.BLACK)));
            docu.close();
            Desktop.getDesktop().open(new File("factureClient.pdf"));
        } catch (FileNotFoundException | DocumentException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void gotooffre(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnretour.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MenuClient_fonction_view.fxml"));
        primaryStage.setTitle("menu admin");
        primaryStage.setScene(new Scene(root, 1370, 700));
        primaryStage.show();
    }

    public void showInfo(String id, String montant, String nom, String prenom, String cin, String destinat, String date_dep, String date_arr){
        labelID.setText(id);
        labelMontant.setText(montant);
        labelNom.setText(nom);
        labelPrenom.setText(prenom);
        labelCin.setText(cin);
        labelDest.setText(destinat);
        labelDatedep.setText(date_dep);
        labelDatearr.setText(date_arr);
        labeldate.setText(formattedDate);

    }

    @FXML
    void close(ActionEvent event) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Merci");
            alert.setHeaderText(null);
            alert.setContentText("❤   Merci pour votre confiance à bientôt!   ❤");
            alert.showAndWait();
            exit();
    }

}
