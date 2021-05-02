package m06.uf4.DAO.comanda.implementacio;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import m06.uf4.DAO.DAOFactory.MongoDAOFactory;
import m06.uf4.DAO.comanda.Comanda;
import m06.uf4.DAO.comanda.ComandaDAO;
import m06.uf4.DAO.empleat.Empleat;
import m06.uf4.DAO.empleat.EmpleatDAO;
import m06.uf4.DAO.producte.Producte;
import m06.uf4.DAO.producte.implementacio.ProducteImpMongo;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class ComandaImpMongo implements ComandaDAO {

    private static MongoCollection<Comanda> mongoCollection;
    private static MongoDatabase database;

    public ComandaImpMongo() {
        database = MongoDAOFactory.conexion();
    }

    public static void conectar(){
        mongoCollection = database.getCollection("COMANDA", Comanda.class);
    }

    @Override
    public boolean insertar(Comanda comanda) {
        conectar();
        mongoCollection.insertOne(comanda);
        MongoDAOFactory.close();
        return true;
    }

    @Override
    public int insertarLlista(List<Comanda> comandes) {
        conectar();

        for (Comanda comandaJSON : comandes) {
            mongoCollection.insertOne(comandaJSON);
        }

        MongoDAOFactory.close();
        return comandes.size();
    }

    @Override
    public boolean eliminar(int comandaId) {
        boolean valor = false;
        DeleteResult deleteResult = database.getCollection("COMANDA").deleteOne(eq("id_comanda", comandaId));
        System.out.println("Comandas eliminadas: " + deleteResult.getDeletedCount());
        if (deleteResult.getDeletedCount() > 0){
            valor = true;
        }
        return valor;
    }

    @Override
    public boolean eliminarConjunt() {
        return false;
    }

    @Override
    public boolean modificar(Comanda comanda) {
        boolean valor = false;
        ProducteImpMongo producteImpMongo = new ProducteImpMongo();
        Producte producte = producteImpMongo.consultar(comanda.getId_producte());
        UpdateResult updateResult = database.getCollection("COMANDA").updateOne(eq("id_comanda", comanda.getId_comanda()), Updates.combine(set("data_comanda",comanda.getData_comanda()
        ), set("id_comanda",comanda.getId_comanda()), set("id_producte",comanda.getId_producte()), set("id_prov",comanda.getId_prov()),set("quantitat",comanda.getQuantitat()
        ), set("total", producte.getPreu() * comanda.getQuantitat()), set("data_tramesa",comanda.getData_tramesa())));

        System.out.println("Comandas actualizadas: " + updateResult.getMatchedCount());

        if (updateResult.getMatchedCount() > 0){
            valor = true;
        }

        return valor;
    }

    @Override
    public Comanda consultar(int comandaId) {
        conectar();
        return mongoCollection.find(eq("id_comanda", comandaId)).first();

    }

    @Override
    public List<Comanda> consultarLlistaPerProducte(int productID) {

        conectar();
        List<Comanda> ListComandas = new ArrayList<>();
        FindIterable<Comanda> comanda = mongoCollection.find(eq("id_producte",productID));
        comanda.forEach(ListComandas::add);

        return ListComandas;
    }

    @Override
    public List<Comanda> consultarLlista() {

        conectar();
        List<Comanda> ListComandas = new ArrayList<>();
        FindIterable<Comanda> comanda  =  mongoCollection.find();
        comanda.forEach(ListComandas::add);

        return ListComandas;
    }


}
