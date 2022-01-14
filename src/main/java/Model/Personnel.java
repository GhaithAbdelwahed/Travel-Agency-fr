package Model;

public class Personnel extends Personne {

    private String fonction;

    public Personnel(int cin, String nom, String prenom, int num, String adresse, String fonction) {
        super(cin, nom, prenom, num, adresse);
        this.fonction= fonction;
    }

@Override
    public int getCin() {
        return cin;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public String getPrenom() {
        return prenom;
    }

    @Override
    public int getNum() {
        return num;
    }

    @Override
    public String getAdresse() {
        return adresse;
    }

    public String getFonction() {
        return fonction;
    }
}
