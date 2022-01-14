package Model;

import java.util.Date;

public class facture {
    int ido;
    int cin;
    int idf;
    double montant;
    Date date;

    public facture(int idf,int ido, double montant, Date date, int cin) {
        this.idf = idf;
        this.ido = ido;
        this.montant = montant;
        this.date = date;
        this.cin = cin;
    }

    public int getIdo() {
        return ido;
    }

    public int getCin() {
        return cin;
    }

    public int getIdf() {
        return idf;
    }

    public double getMontant() {
        return montant;
    }

    public Date getDate() {
        return date;
    }
}
