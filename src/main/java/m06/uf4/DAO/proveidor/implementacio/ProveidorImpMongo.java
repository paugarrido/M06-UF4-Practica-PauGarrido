package m06.uf4.DAO.proveidor.implementacio;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import m06.uf4.DAO.DAOFactory.MongoDAOFactory;
import m06.uf4.DAO.comanda.Comanda;
import m06.uf4.DAO.producte.Producte;
import m06.uf4.DAO.producte.ProducteDAO;
import m06.uf4.DAO.proveidor.Proveidor;
import m06.uf4.DAO.proveidor.ProveidorDAO;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

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
        mongoCollection.insertMany(provs);
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
        List<Proveidor> proveidors = consultarLlista();
        boolean flag = true;
        for (Proveidor proveidor : proveidors){
            if (eliminar(proveidor.getId_prov())){
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public boolean modificarQuantitat(Proveidor prov) {
        boolean valor = false;
        UpdateResult updateResult = database.getCollection("PROV").updateOne(eq("id_prov", prov.getId_prov()), Updates.combine(set("ciutat",prov.getCiutat()
                ), set("estat",prov.getEstat()), set("id_producte",prov.getId_producte()),set("id_prov",prov.getId_prov()), set("quantitat",prov.getQuantitat()),set("telefon",prov.getTelefon()
                ), set("codi_postal",prov.getCodi_postal()), set("limit_credit",prov.getLimit_credit()),set("nom",prov.getNom()),
                set("adreca", prov.getAdreca()),set("observacions",prov.getObservacions()),set("area",prov.getArea())));

        System.out.println("Proveidors actualizados: " + updateResult.getMatchedCount());

        if (updateResult.getMatchedCount() > 0){
            valor = true;
        }
        return valor;
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
