package m06.uf4.DAO.DAOFactory;

import m06.uf4.DAO.comanda.ComandaDAO;
import m06.uf4.DAO.comanda.implementacio.ComandaImpSQL;
import m06.uf4.DAO.empleat.EmpleatDAO;
import m06.uf4.DAO.empleat.implementacio.EmpleatImpSQL;
import m06.uf4.DAO.producte.ProducteDAO;
import m06.uf4.DAO.producte.implementacio.ProducteImpSQL;
import m06.uf4.DAO.proveidor.ProveidorDAO;
import m06.uf4.DAO.proveidor.implementacio.ProveidorImpSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLDAOFactory extends DAOFactory {
    static Connection conexion = null;
    static String DRIVER = "com.mysql.jdbc.Driver";
    static String URLDB = "jdbc:mysql://192.168.56.101:3306/shop?serverTimezone=UTC";
    static String USUARIO = "user1";
    static String CLAVE = "password1";

    public SQLDAOFactory() {

    }


    public static Connection crearConexion() {
        if (conexion == null) {
            try {
                conexion = DriverManager.getConnection(URLDB, USUARIO, CLAVE);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return conexion;
    }


    @Override
    public ComandaDAO getComandaDAO() {
        return new ComandaImpSQL();
    }

    @Override
    public ProducteDAO getProducteDAO() {
        return new ProducteImpSQL();
    }

    @Override
    public ProveidorDAO getProveidorDAO() {
        return new ProveidorImpSQL();
    }

    @Override
    public EmpleatDAO getEmpleatDAO() {
        return new EmpleatImpSQL();
    }
}
