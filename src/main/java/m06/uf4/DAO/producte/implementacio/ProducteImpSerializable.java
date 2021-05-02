package m06.uf4.DAO.producte.implementacio;

import m06.uf4.DAO.comanda.Comanda;
import m06.uf4.DAO.empleat.Empleat;
import m06.uf4.DAO.empleat.EmpleatDAO;
import m06.uf4.DAO.producte.Producte;
import m06.uf4.DAO.producte.ProducteDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProducteImpSerializable implements ProducteDAO {
    File file;
    public ProducteImpSerializable() {
        file = new File("src/main/shopFitxers/productes.dat");
    }

    @Override
    public boolean insertar(Producte product) {
        return false;
    }

    @Override
    public int insertarLlista(List<Producte> productes) {
        try {
            file.delete();
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);


            for (int i=0;i< productes.size(); i++){
                objectOutputStream.writeObject(productes.get(i));
                objectOutputStream.writeInt(productes.get(i).getId_producte());
                objectOutputStream.writeUTF(productes.get(i).getDescripcio());
                objectOutputStream.writeInt(productes.get(i).getStockactual());
                objectOutputStream.writeInt(productes.get(i).getStockminim());
                objectOutputStream.writeDouble(productes.get(i).getPreu());
            }
            objectOutputStream.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return productes.size();
    }

    @Override
    public boolean eliminar(int productId) {
        return false;
    }

    @Override
    public boolean eliminarConjunt() {
        return false;
    }

    @Override
    public boolean modificarStock(Producte product) {
        return false;
    }

    @Override
    public Producte consultar(int productID) {
        return null;
    }

    @Override
    public List<Producte> consultarLlista() {
        List<Producte> Listproductes = new ArrayList<>();
        Producte producte;

        try {
            ObjectInputStream ObjectInputStream = new ObjectInputStream(new FileInputStream(file));
            int i = 1;
            try {
                while (true) {
                    producte = (Producte) ObjectInputStream.readObject();
                    Listproductes.add(producte);
                    System.out.print(i + "=>");
                    i++;

                }
            } catch (EOFException eo) {
                System.out.println("FIN DE LECTURA.");
            } catch (StreamCorruptedException x) {
            }
            ObjectInputStream.close();
        }catch (Exception e){

        }
        return Listproductes;
    }
}
