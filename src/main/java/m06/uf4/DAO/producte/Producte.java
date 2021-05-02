package m06.uf4.DAO.producte;

import java.io.Serializable;

public class Producte implements Serializable {

    private int id_producte;
    private String descripcio;
    private int stockactual;
    private int stockminim;
    private double preu;

    public Producte() {
    }

    public Producte(int id_producte, String descripcio, int stockactual, int stockminim, double preu) {
        this.id_producte = id_producte;
        this.descripcio = descripcio;
        this.stockactual = stockactual;
        this.stockminim = stockminim;
        this.preu = preu;
    }

    @Override
    public String toString() {
        return "Producte{" +
                "id_producte=" + id_producte +
                ", descripcio='" + descripcio + '\'' +
                ", stockactual=" + stockactual +
                ", stockminim=" + stockminim +
                ", preu=" + preu +
                '}';
    }

    public int getId_producte() {
        return id_producte;
    }

    public void setId_producte(int id_producte) {
        this.id_producte = id_producte;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public int getStockactual() {
        return stockactual;
    }

    public void setStockactual(int stockactual) {
        this.stockactual = stockactual;
    }

    public int getStockminim() {
        return stockminim;
    }

    public void setStockminim(int stockminim) {
        this.stockminim = stockminim;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }
}
