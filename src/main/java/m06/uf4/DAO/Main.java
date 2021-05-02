package m06.uf4.DAO;

import m06.uf4.DAO.DAOFactory.DAOFactory;
import m06.uf4.DAO.DAOFactory.MongoDAOFactory;
import m06.uf4.DAO.DAOFactory.SQLDAOFactory;
import m06.uf4.DAO.DAOFactory.SerializableDAOFactory;
import m06.uf4.DAO.comanda.implementacio.ComandaImpMongo;
import m06.uf4.DAO.producte.Producte;
import m06.uf4.DAO.producte.implementacio.ProducteImpSQL;
import m06.uf4.DAO.proveidor.Proveidor;
import m06.uf4.DAO.proveidor.implementacio.ProveidorImpSQL;

public class Main {
    public static SQLDAOFactory sqldaoFactory;
    public static MongoDAOFactory mongoDAOFactory;
    public static SerializableDAOFactory serializableDAOFactory;
    public static void main(String[] args) {

        sqldaoFactory = (SQLDAOFactory) DAOFactory.getDAOFactory(1);
        mongoDAOFactory = (MongoDAOFactory) DAOFactory.getDAOFactory(2);
        serializableDAOFactory = (SerializableDAOFactory) DAOFactory.getDAOFactory(3);
        prueba();
    }

    public static void prueba(){
        serializableDAOFactory.getProveidorDAO().insertarLlista(sqldaoFactory.getProveidorDAO().consultarLlista());
    }

}
