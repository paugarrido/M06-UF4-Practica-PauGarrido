package m06.uf4.DAO.producte;

import java.util.List;

public interface ProducteDAO {
    public boolean insertar(Producte product);
    public int insertarLlista(List<Producte> productes);


    public boolean eliminar(int productId);
    public boolean eliminarConjunt();

    public boolean modificarStock(Producte product);

    public Producte consultar(int productID);
    public List<Producte> consultarLlista();
}
