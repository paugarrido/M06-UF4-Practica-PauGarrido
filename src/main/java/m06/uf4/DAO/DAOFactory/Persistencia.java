package m06.uf4.DAO.DAOFactory;

public class Persistencia {




    public static void mongo(SerializableDAOFactory serializableDAOFactory,  MongoDAOFactory mongoDAOFactory, SQLDAOFactory sqldaoFactory){
        serializableDAOFactory.getComandaDAO().insertarLlista(mongoDAOFactory.getComandaDAO().consultarLlista());
        serializableDAOFactory.getProducteDAO().insertarLlista(mongoDAOFactory.getProducteDAO().consultarLlista());
        serializableDAOFactory.getProveidorDAO().insertarLlista(mongoDAOFactory.getProveidorDAO().consultarLlista());
        serializableDAOFactory.getEmpleatDAO().insertarLlista(mongoDAOFactory.getEmpleatDAO().consultarLlista());

        sqldaoFactory.getComandaDAO().eliminarConjunt();
        sqldaoFactory.getProducteDAO().eliminarConjunt();
        sqldaoFactory.getProveidorDAO().eliminarConjunt();
        sqldaoFactory.getEmpleatDAO().eliminarConjunt();

        sqldaoFactory.getComandaDAO().insertarLlista(serializableDAOFactory.getComandaDAO().consultarLlista());
        sqldaoFactory.getProducteDAO().insertarLlista(serializableDAOFactory.getProducteDAO().consultarLlista());
        sqldaoFactory.getProveidorDAO().insertarLlista(serializableDAOFactory.getProveidorDAO().consultarLlista());
        sqldaoFactory.getEmpleatDAO().insertarLlista(serializableDAOFactory.getEmpleatDAO().consultarLlista());
    }

    public static void sql(SerializableDAOFactory serializableDAOFactory,  MongoDAOFactory mongoDAOFactory, SQLDAOFactory sqldaoFactory){
        serializableDAOFactory.getComandaDAO().insertarLlista(sqldaoFactory.getComandaDAO().consultarLlista());
        serializableDAOFactory.getProducteDAO().insertarLlista(sqldaoFactory.getProducteDAO().consultarLlista());
        serializableDAOFactory.getProveidorDAO().insertarLlista(sqldaoFactory.getProveidorDAO().consultarLlista());
        serializableDAOFactory.getEmpleatDAO().insertarLlista(sqldaoFactory.getEmpleatDAO().consultarLlista());

        mongoDAOFactory.getComandaDAO().eliminarConjunt();
        mongoDAOFactory.getProducteDAO().eliminarConjunt();
        mongoDAOFactory.getProveidorDAO().eliminarConjunt();
        mongoDAOFactory.getEmpleatDAO().eliminarConjunt();

        mongoDAOFactory.getComandaDAO().insertarLlista(serializableDAOFactory.getComandaDAO().consultarLlista());
        mongoDAOFactory.getProducteDAO().insertarLlista(serializableDAOFactory.getProducteDAO().consultarLlista());
        mongoDAOFactory.getProveidorDAO().insertarLlista(serializableDAOFactory.getProveidorDAO().consultarLlista());
        mongoDAOFactory.getEmpleatDAO().insertarLlista(serializableDAOFactory.getEmpleatDAO().consultarLlista());
    }

}
