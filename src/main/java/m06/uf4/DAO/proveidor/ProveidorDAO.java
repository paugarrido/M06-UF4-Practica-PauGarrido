package m06.uf4.DAO.proveidor;


import java.util.List;

public interface ProveidorDAO {
    public boolean insertar(Proveidor prov);
    public int insertarLlista(List<Proveidor> provs);

    public boolean eliminar(int idProv);
    public boolean eliminarConjunt();

    public boolean modificarQuantitat(Proveidor prov); //a partir del id de producte


    public Proveidor consultar(int idProv);
    public List<Proveidor> consultarLlista();
    public Proveidor consultarPerIdProducte(int productID);
}
