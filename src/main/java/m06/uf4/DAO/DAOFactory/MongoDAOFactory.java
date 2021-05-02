package m06.uf4.DAO.DAOFactory;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import m06.uf4.DAO.comanda.Comanda;
import m06.uf4.DAO.comanda.ComandaDAO;
import m06.uf4.DAO.comanda.implementacio.ComandaImpMongo;
import m06.uf4.DAO.empleat.EmpleatDAO;
import m06.uf4.DAO.empleat.implementacio.EmpleatImpMongo;
import m06.uf4.DAO.producte.ProducteDAO;
import m06.uf4.DAO.producte.implementacio.ProducteImpMongo;
import m06.uf4.DAO.proveidor.ProveidorDAO;
import m06.uf4.DAO.proveidor.implementacio.ProveidorImpMongo;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDAOFactory extends DAOFactory{

    public static MongoClient mongoClient;
    static MongoDatabase myDB;
    private static final String DATABASE = "shop";
    private static final String URL = "mongodb://192.168.56.101:27017";


    public MongoDAOFactory(){

    }

    public static MongoDatabase conexion(){
        CodecRegistry CodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        mongoClient =  MongoClients.create(URL);
        myDB = mongoClient.getDatabase(DATABASE).withCodecRegistry(CodecRegistry);
        return myDB;
    }


    public static void close() {
        mongoClient.close();
    }

    @Override
    public ComandaDAO getComandaDAO() {
        return new ComandaImpMongo();
    }

    @Override
    public ProducteDAO getProducteDAO() {
        return new ProducteImpMongo();
    }

    @Override
    public ProveidorDAO getProveidorDAO() {
        return new ProveidorImpMongo();
    }

    @Override
    public EmpleatDAO getEmpleatDAO() {
        return new EmpleatImpMongo();
    }
}
