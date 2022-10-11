package Domein;

import java.util.Date;

public class Ovchipkaart {
    private int kaartNummer;
    private Date geldigTot;
    private int klasse;
    private double saldo;
    private int reizigersId;

    public Ovchipkaart(int kN, Date gT,int kl,double sl, int rI){
        this.kaartNummer = kN;
        this.geldigTot = gT;
        this.klasse = kl;
        this.saldo = sl;
        this.reizigersId = rI;
    }
    public Ovchipkaart(int kN){
        this.kaartNummer = kN;
    }

    public int getKaartNummer() {
        return kaartNummer;
    }

    public void setKaartNummer(int kaartNummer) {
        this.kaartNummer = kaartNummer;
    }

    public Date getGeldigTot() {
        return geldigTot;
    }

    public void setGeldigTot(Date geldigTot) {
        this.geldigTot = geldigTot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getReizigersId() {
        return reizigersId;
    }

    public void setReizigersId(int reizigersId) {
        this.reizigersId = reizigersId;
    }

    @Override
    public String toString() {
        return "Ovchipkaart{" +
                "kaartNummer=" + kaartNummer +
                ", geldigTot=" + geldigTot +
                ", klasse=" + klasse +
                ", saldo=" + saldo +
                ", reizigers_id="+reizigersId+
                '}'+"\n";
    }
}
