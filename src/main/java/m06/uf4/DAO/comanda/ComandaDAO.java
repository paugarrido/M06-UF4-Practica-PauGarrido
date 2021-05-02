package m06.uf4.DAO.comanda;


import java.util.List;

public interface ComandaDAO {

    public boolean insertar(Comanda comanda);
    public int insertarLlista(List<Comanda> comandes);

    public boolean eliminar(int comandaId);
    public boolean eliminarConjunt();

    public boolean modificar(Comanda comanda);

    public Comanda consultar(int comandaId);
    public List<Comanda> consultarLlistaPerProducte(int productID);
    public List<Comanda> consultarLlista();
}
