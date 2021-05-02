package m06.uf4.DAO.DAOFactory;

import m06.uf4.DAO.comanda.ComandaDAO;
import m06.uf4.DAO.comanda.implementacio.ComandaImpSerializable;
import m06.uf4.DAO.empleat.EmpleatDAO;
import m06.uf4.DAO.empleat.implementacio.EmpleatImpSerializable;
import m06.uf4.DAO.producte.ProducteDAO;
import m06.uf4.DAO.producte.implementacio.ProducteImpSerializable;
import m06.uf4.DAO.proveidor.ProveidorDAO;
import m06.uf4.DAO.proveidor.implementacio.ProveidorImpSerializable;

public class SerializableDAOFactory extends DAOFactory{
    @Override
    public ComandaDAO getComandaDAO() {
        return new ComandaImpSerializable();
    }

    @Override
    public ProducteDAO getProducteDAO() {
        return new ProducteImpSerializable();
    }

    @Override
    public ProveidorDAO getProveidorDAO() {
        return new ProveidorImpSerializable();
    }

    @Override
    public EmpleatDAO getEmpleatDAO() {
        return new EmpleatImpSerializable();
    }
}
