package Model;

public class Client {
    private int cin;
    private String nom;
    private String prenom;
    private String adresse;
    private int numero;

    public Client(int cin, String nom, String prenom, String adresse, int numero) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numero = numero;
    }

    public int getCin() {
        return cin;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getNumero() {
        return numero;
    }
}
