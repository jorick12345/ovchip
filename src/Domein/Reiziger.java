package Domein;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reiziger {

    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;

    private Adress adress;

    private List<Ovchipkaart> ovchipkaarten;

    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }
    public Reiziger(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Adress getAdress() {
        return adress;
    }

    public List<Ovchipkaart> getOvchipkaarten() {
        return ovchipkaarten;
    }

    public void setOvchipkaarten(List<Ovchipkaart> ovchipkaarten) {
        this.ovchipkaarten = ovchipkaarten;
    }

    public String toString(){
        String s = "";
        if(getAdress()==null){
            s = "reiziger "+ getId()+" : "+ getVoorletters()+" "+getTussenvoegsel()+" "+getAchternaam()+" geboren op "+getGeboortedatum()+" null";

        }
        if(getTussenvoegsel()==null){
            this.tussenvoegsel = "";
        }
        else{
        s = "reiziger "+ getId()+" : "+ getVoorletters()+" "+getTussenvoegsel()+" "+getAchternaam()+" geboren op "+getGeboortedatum()+adress;

        }
        return s;
    }
}

