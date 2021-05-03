package m06.uf4.DAO.proveidor.implementacio;

import m06.uf4.DAO.producte.Producte;
import m06.uf4.DAO.producte.ProducteDAO;
import m06.uf4.DAO.proveidor.Proveidor;
import m06.uf4.DAO.proveidor.ProveidorDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProveidorImpSerializable implements ProveidorDAO {
    File file;
    public ProveidorImpSerializable() {
        file = new File("src/main/shopFitxers/proveidors.dat");
    }


    @Override
    public boolean insertar(Proveidor prov) {
        return false;
    }

    @Override
    public int insertarLlista(List<Proveidor> provs) {
        try {

            file.delete();
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (int i=0;i< provs.size(); i++){
                objectOutputStream.writeObject(provs.get(i));

            }
            objectOutputStream.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return provs.size();
    }

    @Override
    public boolean eliminar(int idProv) {
        return false;
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
        return null;
    }

    @Override
    public List<Proveidor> consultarLlista() {
        List<Proveidor> Listproveidors = new ArrayList<>();
        Proveidor proveidors;
        try {
            ObjectInputStream ObjectInputStream = new ObjectInputStream(new FileInputStream(file));
            try {
                while (true) {
                    proveidors = (Proveidor) ObjectInputStream.readObject();
                    Listproveidors.add(proveidors);

                }
            } catch (EOFException eo) {
                System.out.println("FIN DE LECTURA.");
            } catch (StreamCorruptedException x) {
            }
            ObjectInputStream.close();
        }catch (Exception e){

        }
        return Listproveidors;
    }

    @Override
    public Proveidor consultarPerIdProducte(int productID) {
        return null;
    }
}
