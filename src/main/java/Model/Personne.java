package Model;

public class Personne {
        protected int cin ;
        protected String nom;
        protected String prenom;
        protected int num;
        protected String adresse;

public Personne(int cin , String nom,String prenom, int num, String adresse) {
        this.cin=cin;
        this.nom=nom;
        this.prenom=prenom;
        this.num=num;
        this.adresse=adresse;
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

    public int getNum() {
        return num;
    }

    public String getAdresse() {
        return adresse;
    }
}
