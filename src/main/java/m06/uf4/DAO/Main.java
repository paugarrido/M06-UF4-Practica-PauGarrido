package m06.uf4.DAO;

import m06.uf4.DAO.DAOFactory.DAOFactory;
import m06.uf4.DAO.DAOFactory.MongoDAOFactory;
import m06.uf4.DAO.DAOFactory.SQLDAOFactory;
import m06.uf4.DAO.DAOFactory.SerializableDAOFactory;
import m06.uf4.DAO.comanda.Comanda;
import m06.uf4.DAO.comanda.implementacio.ComandaImpMongo;
import m06.uf4.DAO.empleat.Empleat;
import m06.uf4.DAO.producte.Producte;
import m06.uf4.DAO.producte.implementacio.ProducteImpSQL;
import m06.uf4.DAO.proveidor.Proveidor;
import m06.uf4.DAO.proveidor.implementacio.ProveidorImpSQL;

import java.util.List;

public class Main {
    public static SQLDAOFactory sqldaoFactory;
    public static MongoDAOFactory mongoDAOFactory;
    public static SerializableDAOFactory serializableDAOFactory;
    public static void main(String[] args) {

        sqldaoFactory = (SQLDAOFactory) DAOFactory.getDAOFactory(1);
        mongoDAOFactory = (MongoDAOFactory) DAOFactory.getDAOFactory(2);
        serializableDAOFactory = (SerializableDAOFactory) DAOFactory.getDAOFactory(3);
    }



    public static void ej1(){
        serializableDAOFactory.getEmpleatDAO().insertarLlista(sqldaoFactory.getEmpleatDAO().consultarLlista());
    }

    public static void ej2(){
        mongoDAOFactory.getEmpleatDAO().insertarLlista(serializableDAOFactory.getEmpleatDAO().consultarLlista());
    }

    public static void ej3(){
        serializableDAOFactory.getComandaDAO().insertarLlista(sqldaoFactory.getComandaDAO().consultarLlista());
    }
    public static void ej4(){
        mongoDAOFactory.getComandaDAO().insertarLlista(serializableDAOFactory.getComandaDAO().consultarLlista());
    }

    public static void ej5(){
        serializableDAOFactory.getProducteDAO().insertarLlista(sqldaoFactory.getProducteDAO().consultarLlista());
    }
    public static void ej6(){
        mongoDAOFactory.getProducteDAO().insertarLlista(serializableDAOFactory.getProducteDAO().consultarLlista());
    }

    public static void ej7(){
        serializableDAOFactory.getProveidorDAO().insertarLlista(sqldaoFactory.getProveidorDAO().consultarLlista());
    }
    public static void ej8(){
        mongoDAOFactory.getProveidorDAO().insertarLlista(serializableDAOFactory.getProveidorDAO().consultarLlista());
    }

    public static void ej9(){
        System.out.println(mongoDAOFactory.getEmpleatDAO().consultar(7654));
    }

    public static void ej10(){
        mongoDAOFactory.getEmpleatDAO().eliminar(7499);
//        serializableDAOFactory.getEmpleatDAO().eliminar(7499);
//        sqldaoFactory.getEmpleatDAO().eliminar(7499);
    }

    public static void ej11(){
        Empleat empleat = mongoDAOFactory.getEmpleatDAO().consultar(7654);
        empleat.setOfici("ANALISTA");
        mongoDAOFactory.getEmpleatDAO().modificar(empleat);
    }

    public static void ej12(){
        List<Comanda> listComandas = sqldaoFactory.getComandaDAO().consultarLlista();
        listComandas.forEach(comanda -> {
            Producte producte = sqldaoFactory.getProducteDAO().consultar(comanda.getId_producte());
            comanda.setTotal(producte.getPreu() * comanda.getQuantitat());
            sqldaoFactory.getComandaDAO().modificar(comanda);
        });
    }

    public static void ej1314(){
        Producte producte = sqldaoFactory.getProducteDAO().consultar(101863);
        producte.setStockactual(2);
        sqldaoFactory.getProducteDAO().modificarStock(producte);
    }






}
