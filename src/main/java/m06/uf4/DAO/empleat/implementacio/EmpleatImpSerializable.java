package m06.uf4.DAO.empleat.implementacio;

import m06.uf4.DAO.comanda.Comanda;
import m06.uf4.DAO.empleat.Empleat;
import m06.uf4.DAO.empleat.EmpleatDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleatImpSerializable implements EmpleatDAO {
    File file;
    public EmpleatImpSerializable() {
        file = new File("src/main/shopFitxers/empleats.dat");
    }

    @Override
    public boolean insertar(Empleat emp) {
        System.out.println("No implementat");
        return false;
    }

    @Override
    public int insertarLlista(List<Empleat> emps) {
        try {

            file.delete();
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (int i=0;i< emps.size(); i++){
                objectOutputStream.writeObject(emps.get(i));
                objectOutputStream.writeInt(emps.get(i).getEmplatID());
                objectOutputStream.writeUTF(emps.get(i).getCognom());
                objectOutputStream.writeUTF(emps.get(i).getOfici());
                objectOutputStream.writeInt(emps.get(i).getCapId());
                objectOutputStream.writeUTF(String.valueOf(emps.get(i).getDataAlta()));
                objectOutputStream.writeInt(emps.get(i).getEmplatID());
                objectOutputStream.writeInt(emps.get(i).getComissio());
                objectOutputStream.writeInt(emps.get(i).getDepNo());
            }
            objectOutputStream.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return emps.size();
    }

    @Override
    public boolean eliminar(int empId) {
        System.out.println("No implementat");
        return false;
    }

    @Override
    public boolean eliminarConjunt() {
        System.out.println("No implementat");
        return true;
    }

    @Override
    public boolean modificar(Empleat emp) {
        System.out.println("No implementat");
        return false;
    }

    @Override
    public Empleat consultar(int empId) {
        System.out.println("No implementat");
        return null;
    }

    @Override
    public List<Empleat> consultarLlista() {
        List<Empleat> Listempleat = new ArrayList<>();
        Empleat empleat;

        try {
            ObjectInputStream ObjectInputStream = new ObjectInputStream(new FileInputStream(file));

            try {
                while (true) {
                    empleat = (Empleat) ObjectInputStream.readObject();
                    Listempleat.add(empleat);
                }
            } catch (EOFException eo) {
                System.out.println("FIN DE LECTURA.");
            } catch (StreamCorruptedException x) {

            }
            ObjectInputStream.close();
        }catch (Exception e){

        }
        return Listempleat;
    }
}
