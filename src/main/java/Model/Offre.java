package Model;

import java.util.Date;

public class Offre {
    private int ido;
    private String description ;
    private Date date_dep;
    private Date date_ret;
    private String destination;
    private String type_transport;
    private String nomCompagnie;
    private String nomHotel;
    private String guide;
    private double tarif;



    //constructeur
    public Offre (int ido , String description,Date date_dep,Date date_ret,String type_transport, String nomCompagnie,
                  String destination, String nomHotel, String guide,double tarif)
    {

        this.ido =ido;
        this.description=description;
        this.date_dep =date_dep;
        this.date_ret=date_ret;
        this.destination=destination;
        this.type_transport=type_transport;
        this.nomCompagnie=nomCompagnie;
        this.nomHotel=nomHotel;
        this.guide=guide;
        this.tarif=tarif;
    }

    public int getIdo() {
        return ido;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate_dep() {
        return date_dep;
    }

    public Date getDate_ret() {
        return date_ret;
    }

    public double getTarif() {
        return tarif;
    }

    public String getType_transport() {
        return type_transport;
    }

    public String getNomCompagnie() {
        return nomCompagnie;
    }

    public String getNomHotel() {
        return nomHotel;
    }

    public String getGuide() {
        return guide;
    }


    public String getDestination() {
        return destination;
    }
}
