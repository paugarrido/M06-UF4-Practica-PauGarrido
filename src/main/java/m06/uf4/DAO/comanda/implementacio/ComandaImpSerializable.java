package m06.uf4.DAO.comanda.implementacio;

import m06.uf4.DAO.comanda.Comanda;
import m06.uf4.DAO.comanda.ComandaDAO;
import m06.uf4.DAO.empleat.Empleat;
import m06.uf4.DAO.empleat.EmpleatDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ComandaImpSerializable implements ComandaDAO {
    File file;
    public ComandaImpSerializable() {
        file = new File("src/main/shopFitxers/comandas.dat");
    }


    @Override
    public boolean insertar(Comanda comanda) {
        return false;
    }

    @Override
    public int insertarLlista(List<Comanda> comandes) {
        try {
            file.delete();
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (int i=0;i< comandes.size(); i++){
                objectOutputStream.writeObject(comandes.get(i));
                objectOutputStream.writeInt(comandes.get(i).getId_comanda());
                objectOutputStream.writeInt(comandes.get(i).getId_producte());
                objectOutputStream.writeUTF(String.valueOf(comandes.get(i).getData_comanda()));
                objectOutputStream.writeInt(comandes.get(i).getQuantitat());
                objectOutputStream.writeInt(comandes.get(i).getId_prov());
                objectOutputStream.writeUTF(String.valueOf(comandes.get(i).getData_tramesa()));
                objectOutputStream.writeDouble(comandes.get(i).getTotal());
            }
            objectOutputStream.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return comandes.size();
    }

    @Override
    public boolean eliminar(int comandaId) {
        return false;
    }

    @Override
    public boolean eliminarConjunt() {
        return false;
    }

    @Override
    public boolean modificar(Comanda comanda) {
        return false;
    }

    @Override
    public Comanda consultar(int comandaId) {
        return null;
    }

    @Override
    public List<Comanda> consultarLlistaPerProducte(int productID) {
        return null;
    }

    @Override
    public List<Comanda> consultarLlista() {
        List<Comanda> Listcomandes = new ArrayList<>();
        Comanda comanda;
        try {
            ObjectInputStream ObjectInputStream = new ObjectInputStream(new FileInputStream(file));
            try {
                while (true) {
                    comanda = (Comanda) ObjectInputStream.readObject();
                    Listcomandes.add(comanda);

                }
            } catch (EOFException eo) {
                System.out.println("FIN DE LECTURA.");
            } catch (StreamCorruptedException x) {
            }
            ObjectInputStream.close();
        }catch (Exception e){

        }
        return Listcomandes;
    }
}
