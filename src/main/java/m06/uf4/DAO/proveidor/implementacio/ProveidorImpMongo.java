package m06.uf4.DAO.proveidor.implementacio;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import m06.uf4.DAO.DAOFactory.MongoDAOFactory;
import m06.uf4.DAO.comanda.Comanda;
import m06.uf4.DAO.producte.Producte;
import m06.uf4.DAO.producte.ProducteDAO;
import m06.uf4.DAO.proveidor.Proveidor;
import m06.uf4.DAO.proveidor.ProveidorDAO;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ProveidorImpMongo implements ProveidorDAO {
    private static MongoCollection<Proveidor> mongoCollection;
    private static MongoDatabase database;

    public ProveidorImpMongo() {
        database = MongoDAOFactory.conexion();
    }

    public static void conectar(){
        mongoCollection = database.getCollection("PROV", Proveidor.class);
    }

    @Override
    public boolean insertar(Proveidor prov) {
        conectar();
        mongoCollection.insertOne(prov);
        MongoDAOFactory.close();
        return true;
    }

    @Override
    public int insertarLlista(List<Proveidor> provs) {
        conectar();

        for (Proveidor proveidorJSON : provs) {
            mongoCollection.insertOne(proveidorJSON);
        }

        MongoDAOFactory.close();
        return provs.size();
    }

    @Override
    public boolean eliminar(int idProv) {
        boolean valor = false;
        DeleteResult deleteResult = database.getCollection("PROV").deleteOne(eq("id_prov", idProv));
        System.out.println("Proveidores eliminados: " + deleteResult.getDeletedCount());
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
    public boolean modificarQuantitat(Proveidor prov) {
        return false;
    }

    @Override
    public Proveidor consultar(int idProv) {
        conectar();
        return mongoCollection.find(eq("id_prov", idProv)).first();
    }

    @Override
    public List<Proveidor> consultarLlista() {
        conectar();
        List<Proveidor> ListProveidors = new ArrayList<>();
        FindIterable<Proveidor> proveidors  =  mongoCollection.find();
        proveidors.forEach(ListProveidors::add);

        return ListProveidors;
    }

    @Override
    public Proveidor consultarPerIdProducte(int productID) {
        conectar();
        return mongoCollection.find(eq("id_producte", productID)).first();
    }
}
