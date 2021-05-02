package m06.uf4.DAO.comanda.implementacio;

import m06.uf4.DAO.DAOFactory.SQLDAOFactory;
import m06.uf4.DAO.comanda.Comanda;
import m06.uf4.DAO.comanda.ComandaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// EMPLEAT_ID, COGNOM, OFICI, CAP, DATA_ALTA, SALARI, COMISSIO, DEPT_NO

public class ComandaImpSQL implements ComandaDAO {
    Connection conexion;

    public ComandaImpSQL() { conexion = SQLDAOFactory.crearConexion();
    }

    // INSERTAR COMANDA
    @Override
    public boolean insertar(Comanda com) {
        boolean valor = false;
        String sql = "INSERT INTO COMANDA VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, com.getId_comanda());
            sentencia.setInt(2, com.getId_producte());
            sentencia.setDate(3, com.getData_comanda());
            sentencia.setInt(4, com.getQuantitat());
            sentencia.setInt(5, com.getId_prov());
            sentencia.setDate(6, com.getData_tramesa());
            sentencia.setDouble(7, com.getTotal());
            int filas = sentencia.executeUpdate();
            System.out.printf("Filas insertadas: %d%n", filas);
            if (filas > 0){
                valor = true;
                System.out.printf("Comandas %d insertada%n", com.getId_comanda());
            }
            sentencia.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return valor;
    }

    // INSERTAR CONJUNTO COMANDAS

    @Override
    public int insertarLlista(List<Comanda> comandes) {
        for (Comanda comanda : comandes){
            insertar(comanda);
        }
        return comandes.size();
    }


    // ELIMINAR COMANDA
    @Override
    public boolean eliminar(int comandaId) {
        boolean valor = false;
        String sql = "DELETE FROM COMANDA WHERE id_comanda = ? ";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, comandaId);
            int filas = sentencia.executeUpdate();
            System.out.printf("Filas eliminadas: %d%n", filas);
            if (filas > 0) {
                valor = true;
                System.out.printf("Comanda %d eliminada%n", comandaId);
            }
            sentencia.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return valor;
    }

    // ELIMINAR CONJUNTO COMANDAS
    @Override
    public boolean eliminarConjunt() {
        boolean valor = false;
        String sql = "DELETE * FROM COMANDA";
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

    // MODIFICAR COMANDA
    @Override
    public boolean modificar(Comanda com) {
        boolean valor = false;
        String sql = "UPDATE COMANDA SET id_producte= ?, data_comanda = ?, quantitat = ?, id_prov = ?, data_tramesa = ?, total = ?  WHERE id_comanda = ? ";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(7, com.getId_comanda());
            sentencia.setInt(1, com.getId_producte());
            sentencia.setDate(2, com.getData_comanda());
            sentencia.setInt(3, com.getQuantitat());
            sentencia.setInt(4, com.getId_prov());
            sentencia.setDate(5, com.getData_tramesa());
            sentencia.setDouble(6, com.getTotal());
            int filas = sentencia.executeUpdate();
            System.out.printf("Filas modificadas: %d%n", filas);
            if (filas > 0) {
                valor = true;
                System.out.printf("Comanda %d modificada%n", com.getId_comanda());
            }
            sentencia.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return valor;
    }

    // CONSULTAR COMANDA

    @Override
    public Comanda consultar(int comandaId) {
        String sql = "SELECT id_comanda, id_producte, data_comanda , quantitat , id_prov , data_tramesa , total FROM COMANDA WHERE id_comanda = ?";
        PreparedStatement sentencia;
        Comanda com = new Comanda();
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, comandaId);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                com.setId_comanda(rs.getInt("id_comanda"));
                com.setId_producte(rs.getInt("id_producte"));
                com.setData_comanda(rs.getDate("data_comanda"));
                com.setQuantitat(rs.getInt("quantitat"));
                com.setId_prov(rs.getInt("id_prov"));
                com.setData_tramesa(rs.getDate("data_tramesa"));
                com.setTotal(rs.getDouble("total"));
            }else
                System.out.printf("Comanda: %d No existe%n",comandaId);
            rs.close();
            sentencia.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return com;
    }

    @Override
    public List<Comanda> consultarLlistaPerProducte(int productID) {
        String sql = "SELECT id_comanda, id_producte, data_comanda , quantitat , id_prov , data_tramesa , total FROM COMANDA WHERE id_producte = ?";
        PreparedStatement sentencia;
        Comanda com = new Comanda();
        List<Comanda> listComs = new ArrayList<>();
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, productID);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                com.setId_comanda(rs.getInt("id_comanda"));
                com.setId_producte(rs.getInt("id_producte"));
                com.setData_comanda(rs.getDate("data_comanda"));
                com.setQuantitat(rs.getInt("quantitat"));
                com.setId_prov(rs.getInt("id_prov"));
                com.setData_tramesa(rs.getDate("data_tramesa"));
                com.setTotal(rs.getDouble("total"));
                listComs.add(com);
            }
            rs.close();
            sentencia.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return listComs;
    }

    @Override
    public List<Comanda> consultarLlista() {
        String sql = "SELECT id_comanda, id_producte, data_comanda , quantitat , id_prov , data_tramesa , total FROM COMANDA";
        PreparedStatement sentencia;
        Comanda com = new Comanda();
        List<Comanda> listComs = new ArrayList<>();
        try {
            sentencia = conexion.prepareStatement(sql);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                com.setId_comanda(rs.getInt("id_comanda"));
                com.setId_producte(rs.getInt("id_producte"));
                com.setData_comanda(rs.getDate("data_comanda"));
                com.setQuantitat(rs.getInt("quantitat"));
                com.setId_prov(rs.getInt("id_prov"));
                com.setData_tramesa(rs.getDate("data_tramesa"));
                com.setTotal(rs.getDouble("total"));
                listComs.add(com);
            }
            rs.close();
            sentencia.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return listComs;
    }

    private void MensajeExcepcion(SQLException e) {
        System.out.printf("HA OCURRIDO UNA EXCEPCIÓN:%n");
        System.out.printf("Mensaje : %s %n", e.getMessage());
        System.out.printf("SQL estado: %s %n", e.getSQLState());
        System.out.printf("Cód error : %s %n", e.getErrorCode());
    }
}
