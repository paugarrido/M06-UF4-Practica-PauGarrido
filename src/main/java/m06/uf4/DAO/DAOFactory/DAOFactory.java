package m06.uf4.DAO.DAOFactory;

import m06.uf4.DAO.comanda.ComandaDAO;
import m06.uf4.DAO.empleat.EmpleatDAO;
import m06.uf4.DAO.producte.ProducteDAO;
import m06.uf4.DAO.proveidor.ProveidorDAO;

public abstract class DAOFactory {

    public static final int MYSQL = 1;
    public static final int MONGO = 2;
    public static final int SERIALIZABLE = 3;

    public abstract ComandaDAO getComandaDAO();
    public abstract ProducteDAO getProducteDAO();
    public abstract ProveidorDAO getProveidorDAO();
    public abstract EmpleatDAO getEmpleatDAO();


    public static DAOFactory getDAOFactory(int bd) {
        switch (bd) {
            case MYSQL:
                return new SQLDAOFactory();
            case MONGO:
                return new MongoDAOFactory();
            case SERIALIZABLE:
                return new SerializableDAOFactory();
            default:
                return null;
        }
    }
}
