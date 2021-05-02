package m06.uf4.DAO.comanda;

import java.io.Serializable;
import java.util.Date;

public class Comanda implements Serializable {

    private int id_comanda;
    private int id_producte;
    private Date data_comanda;
    private int quantitat;
    private int id_prov;
    private Date data_tramesa;
    private double total;

    public Comanda() {
    }

    public Comanda(int id_comanda, int id_producte, Date data_comanda, int quantitat, int id_prov, Date data_tramesa, double total) {
        this.id_comanda = id_comanda;
        this.id_producte = id_producte;
        this.data_comanda = data_comanda;
        this.quantitat = quantitat;
        this.id_prov = id_prov;
        this.data_tramesa = data_tramesa;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "id_comanda=" + id_comanda +
                ", id_producte=" + id_producte +
                ", data_comanda=" + data_comanda +
                ", quantitat=" + quantitat +
                ", id_prov=" + id_prov +
                ", data_tramesa=" + data_tramesa +
                ", total=" + total +
                '}';
    }

    public int getId_comanda() {
        return id_comanda;
    }

    public void setId_comanda(int id_comanda) {
        this.id_comanda = id_comanda;
    }

    public int getId_producte() {
        return id_producte;
    }

    public void setId_producte(int id_producte) {
        this.id_producte = id_producte;
    }

    public Date getData_comanda() {
        return data_comanda;
    }

    public void setData_comanda(Date data_comanda) {
        this.data_comanda = data_comanda;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    public int getId_prov() {
        return id_prov;
    }

    public void setId_prov(int id_prov) {
        this.id_prov = id_prov;
    }

    public Date getData_tramesa() {
        return data_tramesa;
    }

    public void setData_tramesa(Date data_tramesa) {
        this.data_tramesa = data_tramesa;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
