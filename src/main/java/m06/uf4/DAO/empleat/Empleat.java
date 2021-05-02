package m06.uf4.DAO.empleat;


/*CREATE TABLE IF NOT EXISTS shop.EMPLEAT (
 EMPLEAT_ID    SMALLINT (4) UNSIGNED,
 COGNOM    VARCHAR (10) NOT NULL,
 OFICI     VARCHAR (10),
 CAP_ID       SMALLINT (4) UNSIGNED,
 DATA_ALTA DATE,
 SALARI    INT UNSIGNED,
 COMISSIO  INT UNSIGNED,
 DEPT_NO   TINYINT (2) UNSIGNED NOT NULL,
 PRIMARY KEY (EMPLEAT_NO));*/


import java.io.Serializable;
import java.sql.Date;

public class Empleat implements Serializable {

    private int emplatID;
    private String cognom;
    private String ofici;
    private int capId;
    private Date dataAlta;
    private int salari;
    private int comissio;
    private int depNo;

    public Empleat(){}

    public Empleat(int emplatID, String cognom, String ofici, int capId, Date dataAlta, int salari, int comissio, int depNo) {
        this.emplatID = emplatID;
        this.cognom = cognom;
        this.ofici = ofici;
        this.capId = capId;
        this.dataAlta = dataAlta;
        this.salari = salari;
        this.comissio = comissio;
        this.depNo = depNo;
    }

    @Override
    public String toString() {
        return "Empleat{" +
                "emplatID=" + emplatID +
                ", cognom='" + cognom + '\'' +
                ", ofici='" + ofici + '\'' +
                ", capId=" + capId +
                ", dataAlta='" + dataAlta + '\'' +
                ", salari=" + salari +
                ", comissio=" + comissio +
                ", depNo=" + depNo +
                '}';
    }

    public int getEmplatID() {
        return emplatID;
    }

    public void setEmplatID(int emplatID) {
        this.emplatID = emplatID;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getOfici() {
        return ofici;
    }

    public void setOfici(String ofici) {
        this.ofici = ofici;
    }

    public int getCapId() {
        return capId;
    }

    public void setCapId(int capId) {
        this.capId = capId;
    }

    public Date getDataAlta() {
        return dataAlta;
    }

    public void setDataAlta(Date dataAlta) {
        this.dataAlta = dataAlta;
    }

    public int getSalari() {
        return salari;
    }

    public void setSalari(int salari) {
        this.salari = salari;
    }

    public int getComissio() {
        return comissio;
    }

    public void setComissio(int comissio) {
        this.comissio = comissio;
    }

    public int getDepNo() {
        return depNo;
    }

    public void setDepNo(int depNo) {
        this.depNo = depNo;
    }

}


