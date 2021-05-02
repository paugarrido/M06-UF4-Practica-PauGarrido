package m06.uf4.DAO.producte.implementacio;

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
import m06.uf4.DAO.producte.ProducteDAO;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class ProducteImpMongo implements ProducteDAO {
    private static MongoCollection<Producte> mongoCollection;
    private static MongoDatabase database;

    public ProducteImpMongo() {
        database = MongoDAOFactory.conexion();
    }

    public static void conectar(){
        mongoCollection = database.getCollection("PRODUCTE", Producte.class);
    }


    @Override
    public boolean insertar(Producte producte) {
        conectar();
        mongoCollection.insertOne(producte);
        MongoDAOFactory.close();
        return true;
    }

    @Override
    public int insertarLlista(List<Producte> productes) {
        conectar();

        for (Producte p : productes) {
            mongoCollection.insertOne(p);
        }

        MongoDAOFactory.close();
        return productes.size();
    }

    @Override
    public boolean eliminar(int productId) {
        boolean valor = false;
        DeleteResult deleteResult = database.getCollection("PRODUCTE").deleteOne(eq("id_producte", productId));
        System.out.println("Productes eliminados: " + deleteResult.getDeletedCount());
        if (deleteResult.getDeletedCount() > 0){
            valor = true;
        }
        return valor;
    }

    @Override
    public boolean eliminarConjunt() {
        List<Producte> productes = consultarLlista();
        boolean flag = true;
        for (Producte producte : productes){
            if (eliminar(producte.getId_producte())){
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public boolean modificarStock(Producte product) {
        boolean valor = false;
        UpdateResult updateResult = database.getCollection("PRODUCTE").updateOne(eq("id_producte", product.getId_producte()), Updates.combine(set("descripcio",product.getDescripcio()
        ), set("id_producte",product.getId_producte()), set("preu",product.getPreu()), set("stockactual",product.getStockactual()),set("stockminim",product.getStockminim()
        )));

        System.out.println("Productes actualizados: " + updateResult.getMatchedCount());

        if (updateResult.getMatchedCount() > 0){
            valor = true;
        }

        return valor;
    }

    @Override
    public Producte consultar(int productID) {
        conectar();
        return mongoCollection.find(eq("id_producte", productID)).first();

    }

    @Override
    public List<Producte> consultarLlista() {
        conectar();
        List<Producte> ListProductes = new ArrayList<>();
        FindIterable<Producte> producte  =  mongoCollection.find();
        producte.forEach(ListProductes::add);

        return ListProductes;
    }



}
