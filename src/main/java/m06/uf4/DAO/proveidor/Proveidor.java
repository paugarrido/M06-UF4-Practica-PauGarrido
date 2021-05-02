package m06.uf4.DAO.proveidor;

import java.io.Serializable;



public class Proveidor implements Serializable {

    private int id_prov;
    private String nom;
    private String adreca;
    private String ciutat;
    private String estat;
    private String codi_postal;
    private int area;
    private String telefon;
    private int id_producte;
    private int quantitat;
    private double limit_credit;
    private String observacions;

    public Proveidor() {
    }

    public Proveidor(int id_prov, String nom, String adreca, String ciutat, String estat, String codi_postal, int area, String telefon, int id_producte, int quantitat, double limit_credit, String observacions) {
        this.id_prov = id_prov;
        this.nom = nom;
        this.adreca = adreca;
        this.ciutat = ciutat;
        this.estat = estat;
        this.codi_postal = codi_postal;
        this.area = area;
        this.telefon = telefon;
        this.id_producte = id_producte;
        this.quantitat = quantitat;
        this.limit_credit = limit_credit;
        this.observacions = observacions;
    }

    @Override
    public String toString() {
        return "Proveidor{" +
                "id_prov=" + id_prov +
                ", nom='" + nom + '\'' +
                ", adreca='" + adreca + '\'' +
                ", ciutat='" + ciutat + '\'' +
                ", estat='" + estat + '\'' +
                ", codi_postal='" + codi_postal + '\'' +
                ", area=" + area +
                ", telefon='" + telefon + '\'' +
                ", id_producte=" + id_producte +
                ", quantitat=" + quantitat +
                ", limit_credit=" + limit_credit +
                ", observacions='" + observacions + '\'' +
                '}';
    }

    public int getId_prov() {
        return id_prov;
    }

    public void setId_prov(int id_prov) {
        this.id_prov = id_prov;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public String getCodi_postal() {
        return codi_postal;
    }

    public void setCodi_postal(String codi_postal) {
        this.codi_postal = codi_postal;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public int getId_producte() {
        return id_producte;
    }

    public void setId_producte(int id_producte) {
        this.id_producte = id_producte;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    public double getLimit_credit() {
        return limit_credit;
    }

    public void setLimit_credit(double limit_credit) {
        this.limit_credit = limit_credit;
    }

    public String getObservacions() {
        return observacions;
    }

    public void setObservacions(String observacions) {
        this.observacions = observacions;
    }
}
