package m06.uf4.DAO.empleat;

import java.util.List;

public interface EmpleatDAO {
    public boolean insertar(Empleat emps);
    public int insertarLlista(List<Empleat> emps);

    public boolean eliminar(int empId);
    public boolean eliminarConjunt();

    public boolean modificar(Empleat emp);

    public Empleat consultar(int empId);
    public List<Empleat> consultarLlista();
}
