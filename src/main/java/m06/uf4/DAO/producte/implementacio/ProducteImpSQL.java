package m06.uf4.DAO.producte.implementacio;

import m06.uf4.DAO.DAOFactory.DAOFactory;
import m06.uf4.DAO.DAOFactory.SQLDAOFactory;
import m06.uf4.DAO.comanda.Comanda;
import m06.uf4.DAO.empleat.Empleat;
import m06.uf4.DAO.producte.Producte;
import m06.uf4.DAO.producte.ProducteDAO;
import m06.uf4.DAO.proveidor.Proveidor;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

// EMPLEAT_ID, COGNOM, OFICI, CAP, DATA_ALTA, SALARI, COMISSIO, DEPT_NO

public class ProducteImpSQL implements ProducteDAO {
    Connection conexion;

    public ProducteImpSQL() { conexion = SQLDAOFactory.crearConexion();
    }

    @Override
    public boolean insertar(Producte pro) {
        boolean valor = false;
        String sql = "INSERT INTO PRODUCTE VALUES(?, ?, ?, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, pro.getId_producte());
            sentencia.setString(2, pro.getDescripcio());
            sentencia.setInt(3, pro.getStockactual());
            sentencia.setInt(4, pro.getStockminim());
            sentencia.setDouble(5, pro.getPreu());
            int filas = sentencia.executeUpdate();
            System.out.printf("Filas insertadas: %d%n", filas);
            if (filas > 0) {
                valor = true;
                System.out.printf("Producte %d insertado%n", pro.getId_producte());
            }
            sentencia.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return valor;
    }

    @Override
    public int insertarLlista(List<Producte> productes) {
        return 0;
    }


    @Override
    public boolean eliminar(int productId) {
        boolean valor = false;
        String sql = "DELETE FROM PRODUCTE WHERE id_producte = ? ";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, productId);
            int filas = sentencia.executeUpdate();
            System.out.printf("Filas eliminadas: %d%n", filas);
            if (filas > 0) {
                valor = true;
                System.out.printf("Producte %d eliminado%n", productId);
            }
            sentencia.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return valor;
    }

    @Override
    public boolean eliminarConjunt() {
        boolean valor = false;
        String sql = "DELETE FROM PRODUCTE ";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            int filas = sentencia.executeUpdate();
            System.out.printf("Filas eliminadas: %d%n", filas);
            if (filas > 0) {
                valor = true;
            }
            sentencia.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return valor;
    }

    @Override
    public boolean modificarStock(Producte pro) {
        boolean valor = false;
        String sql = "UPDATE PRODUCTE SET stockactual = ?, stockminim = ?WHERE id_producte = ? ";
        PreparedStatement sentencia;
        try {
            Producte producte = consultar(pro.getId_producte());
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(3, pro.getId_producte());
            sentencia.setInt(1, pro.getStockactual());
            sentencia.setInt(2, pro.getStockminim());

            int filas = sentencia.executeUpdate();
            System.out.printf("Filas modificadas: %d%n", filas);
            if (filas > 0) {
                valor = true;
                System.out.printf("Producte %d modificado%n", pro.getId_producte());
            }
            if (pro.getStockminim() > pro.getStockactual()){
                event1314(pro);
            }

            sentencia.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return valor;
    }

    @Override
    public Producte consultar(int productID) {
        String sql = "SELECT * FROM PRODUCTE WHERE id_producte = ?";
        PreparedStatement sentencia;
        Producte pro = new Producte();
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, productID);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                pro.setId_producte(rs.getInt("id_producte"));
                pro.setDescripcio(rs.getString("descripcio"));
                pro.setStockactual(rs.getInt("stockactual"));
                pro.setStockminim(rs.getInt("stockminim"));
                pro.setPreu(rs.getDouble("preu"));
            }else
                System.out.printf("Producte: %d No existe%n",productID);
            rs.close();
            sentencia.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return pro;
    }

    @Override
    public List<Producte> consultarLlista() {
        String sql = "SELECT * FROM PRODUCTE ";
        PreparedStatement sentencia;
        Producte pro = new Producte();
        List<Producte> listProds = new ArrayList<>();
        try {
            sentencia = conexion.prepareStatement(sql);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                pro.setId_producte(rs.getInt("id_producte"));
                pro.setDescripcio(rs.getString("descripcio"));
                pro.setStockactual(rs.getInt("stockactual"));
                pro.setStockminim(rs.getInt("stockminim"));
                pro.setPreu(rs.getDouble("preu"));
                listProds.add(pro);
            }
            rs.close();
            sentencia.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return listProds;
    }

    private void event1314(Producte producte){
        Comanda comanda = new Comanda();
        SQLDAOFactory sqldaoFactory = (SQLDAOFactory) DAOFactory.getDAOFactory(1);

        List<Comanda> listComandes = sqldaoFactory.getComandaDAO().consultarLlista();
        comanda.setId_comanda(listComandes.get(listComandes.size()-1).getId_comanda() + 1);

        comanda.setId_producte(producte.getId_producte());

        List<Proveidor> listProveidors = sqldaoFactory.getProveidorDAO().consultarLlista();
        listProveidors.forEach(proveidor -> {
            if (proveidor.getId_producte() == producte.getId_producte()){
                comanda.setId_prov(proveidor.getId_prov());

            }
        });

        comanda.setQuantitat(25);
        Calendar c = Calendar.getInstance();
        c.setTime(comanda.getData_comanda());
        c.add(Calendar.DATE, 4);
        comanda.setData_tramesa(c.getTime());

        comanda.setTotal(producte.getPreu() * 25);

        sqldaoFactory.getComandaDAO().insertar(comanda);

        Proveidor prov = sqldaoFactory.getProveidorDAO().consultar(comanda.getId_prov());
        prov.setQuantitat(prov.getQuantitat() + comanda.getQuantitat());

        sqldaoFactory.getProveidorDAO().modificarQuantitat(prov);

    }

    private void MensajeExcepcion(SQLException e) {
        System.out.printf("HA OCURRIDO UNA EXCEPCIÓN:%n");
        System.out.printf("Mensaje : %s %n", e.getMessage());
        System.out.printf("SQL estado: %s %n", e.getSQLState());
        System.out.printf("Cód error : %s %n", e.getErrorCode());
    }
}
