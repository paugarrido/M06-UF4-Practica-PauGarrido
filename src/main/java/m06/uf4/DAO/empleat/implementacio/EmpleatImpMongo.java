package m06.uf4.DAO.empleat.implementacio;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import m06.uf4.DAO.DAOFactory.MongoDAOFactory;
import m06.uf4.DAO.comanda.Comanda;
import m06.uf4.DAO.empleat.Empleat;
import m06.uf4.DAO.empleat.EmpleatDAO;
import m06.uf4.DAO.producte.Producte;
import m06.uf4.DAO.producte.implementacio.ProducteImpMongo;
import org.bson.Document;


import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;



public class EmpleatImpMongo implements EmpleatDAO {

    private static MongoCollection<Empleat> mongoCollection;
    private static MongoDatabase database;

    public EmpleatImpMongo() {
        database = MongoDAOFactory.conexion();
    }

    public static void conectar(){
        mongoCollection = database.getCollection("EMPLEAT", Empleat.class);
    }


    @Override
    public boolean insertar(Empleat empleat) {
        conectar();
        mongoCollection.insertOne(empleat);
        MongoDAOFactory.close();
        return true;
    }

    @Override
    public int insertarLlista(List<Empleat> empleats) {
        conectar();

        for (Empleat empleatJSON : empleats) {
            mongoCollection.insertOne(empleatJSON);
        }

        MongoDAOFactory.close();
        return empleats.size();
    }

    @Override
    public boolean eliminar(int empleatId) {
        boolean valor = false;
        DeleteResult deleteResult = database.getCollection("EMPLEAT").deleteOne(eq("empleat_id", empleatId));
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
    public boolean modificar(Empleat empleat) {

        boolean valor = false;

        UpdateResult updateResult = database.getCollection("EMPLEAT").updateOne(eq("empleat_id", empleat.getEmplatID()), Updates.combine(set("cap_id",empleat.getCapId()
        ), set("cognom",empleat.getCognom()), set("dept_no",empleat.getDepNo()),set("empleat_id",empleat.getEmplatID()), set("ofici",empleat.getOfici()),set("salari",empleat.getSalari()
        ), set("comissio",empleat.getComissio()), set("data_alta",empleat.getDataAlta())));

        System.out.println("Empleats actualizados: " + updateResult.getMatchedCount());

        if (updateResult.getMatchedCount() > 0){
            valor = true;
        }

        return valor;
    }

    @Override
    public Empleat consultar(int empleatId) {
        conectar();
        return mongoCollection.find(eq("empleat_id", empleatId)).first();
    }

    @Override
    public List<Empleat> consultarLlista() {
        conectar();
        List<Empleat> ListEmpleats = new ArrayList<>();
        FindIterable<Empleat> empleat  =  mongoCollection.find();
        empleat.forEach(ListEmpleats::add);

        return ListEmpleats;
    }
}
