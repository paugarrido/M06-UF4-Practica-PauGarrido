package m06.uf4.DAO.proveidor.implementacio;

import m06.uf4.DAO.DAOFactory.SQLDAOFactory;
import m06.uf4.DAO.empleat.Empleat;
import m06.uf4.DAO.producte.Producte;
import m06.uf4.DAO.proveidor.Proveidor;
import m06.uf4.DAO.proveidor.ProveidorDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// EMPLEAT_ID, COGNOM, OFICI, CAP, DATA_ALTA, SALARI, COMISSIO, DEPT_NO

public class ProveidorImpSQL implements ProveidorDAO {
    Connection conexion;

    public ProveidorImpSQL() { conexion = SQLDAOFactory.crearConexion();
    }

    @Override
    public boolean insertar(Proveidor prov) {
        boolean valor = false;
        String sql = "INSERT INTO PROV VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1,prov.getId_prov() );
            sentencia.setString(2, prov.getNom());
            sentencia.setString(3, prov.getAdreca());
            sentencia.setString(4, prov.getCiutat());
            sentencia.setString(5, prov.getEstat());
            sentencia.setString(6, prov.getCodi_postal());
            sentencia.setInt(7, prov.getArea());
            sentencia.setString(8, prov.getTelefon());
            sentencia.setInt(9, prov.getId_producte());
            sentencia.setInt(10, prov.getQuantitat());
            sentencia.setDouble(11, prov.getLimit_credit());
            sentencia.setString(12, prov.getObservacions() );

            int filas = sentencia.executeUpdate();
            System.out.printf("Filas insertadas: %d%n", filas);
            if (filas > 0) {
                valor = true;
                System.out.printf("Proveidor %d insertado%n", prov.getId_prov());
            }
            sentencia.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return valor;
    }

    @Override
    public int insertarLlista(List<Proveidor> provs) {
        for (Proveidor proveidor : provs){
            insertar(proveidor);
        }
        return provs.size();
    }

    @Override
    public boolean eliminar(int idProv) {
        boolean valor = false;
        String sql = "DELETE FROM PROV WHERE id_prov = ? ";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, idProv);
            int filas = sentencia.executeUpdate();
            System.out.printf("Filas eliminadas: %d%n", filas);
            if (filas > 0) {
                valor = true;
                System.out.printf("Proveidor %d eliminado%n", idProv);
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
        String sql = "DELETE FROM PROV ";
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
        return valor;    }

    @Override
    public boolean modificarQuantitat(Proveidor prov) {
        boolean valor = false;
        String sql = "UPDATE PROV SET quantitat = ? WHERE id_prov = ? ";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, prov.getQuantitat());
            sentencia.setInt(2, prov.getId_prov());
            int filas = sentencia.executeUpdate();
            System.out.printf("Filas modificadas: %d%n", filas);
            if (filas > 0) {
                valor = true;
                System.out.printf("Proveidor %d modificado%n", prov.getId_prov());
            }
            sentencia.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return valor;
    }

    @Override
    public Proveidor consultar(int idProv) {
        String sql = "SELECT * FROM PROV WHERE id_prov = ?";
        PreparedStatement sentencia;
        Proveidor prov = new Proveidor();
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, idProv);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                prov.setId_prov(rs.getInt("id_prov"));
                prov.setNom(rs.getString("nom"));
                prov.setAdreca(rs.getString("adreca"));
                prov.setCiutat(rs.getString("ciutat"));
                prov.setEstat(rs.getString("estat"));
                prov.setCodi_postal(rs.getString("codi_postal"));
                prov.setArea(rs.getInt("area"));
                prov.setTelefon(rs.getString("telefon"));
                prov.setId_producte(rs.getInt("id_producte"));
                prov.setQuantitat(rs.getInt("quantitat"));
                prov.setLimit_credit(rs.getDouble("limit_credit"));
                prov.setObservacions(rs.getString("observacions"));
            }else
                System.out.printf("Proveidor: %d No existe%n",idProv);
            rs.close();
            sentencia.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return prov;
    }

    @Override
    public List<Proveidor> consultarLlista() {
        String sql = "SELECT * FROM PROV";
        PreparedStatement sentencia;
        Proveidor prov = new Proveidor();
        List<Proveidor> listProvs = new ArrayList<>();
        try {
            sentencia = conexion.prepareStatement(sql);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                prov.setId_prov(rs.getInt("id_prov"));
                prov.setNom(rs.getString("nom"));
                prov.setAdreca(rs.getString("adreca"));
                prov.setCiutat(rs.getString("ciutat"));
                prov.setEstat(rs.getString("estat"));
                prov.setCodi_postal(rs.getString("codi_postal"));
                prov.setArea(rs.getInt("area"));
                prov.setTelefon(rs.getString("telefon"));
                prov.setId_producte(rs.getInt("id_producte"));
                prov.setQuantitat(rs.getInt("quantitat"));
                prov.setLimit_credit(rs.getDouble("limit_credit"));
                prov.setObservacions(rs.getString("observacions"));
                listProvs.add(prov);
            }
            rs.close();
            sentencia.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return listProvs;
    }

    @Override
    public Proveidor consultarPerIdProducte(int productID) {
        String sql = "SELECT * FROM PROV WHERE id_producte = ?";
        PreparedStatement sentencia;
        Proveidor prov = new Proveidor();
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, productID);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                prov.setId_prov(rs.getInt("id_prov"));
                prov.setNom(rs.getString("nom"));
                prov.setAdreca(rs.getString("adreca"));
                prov.setCiutat(rs.getString("ciutat"));
                prov.setEstat(rs.getString("estat"));
                prov.setCodi_postal(rs.getString("codi_postal"));
                prov.setArea(rs.getInt("area"));
                prov.setTelefon(rs.getString("telefon"));
                prov.setId_producte(rs.getInt("id_producte"));
                prov.setQuantitat(rs.getInt("quantitat"));
                prov.setLimit_credit(rs.getDouble("limit_credit"));
                prov.setObservacions(rs.getString("observacions"));

            }else
                System.out.printf("Producte: %d No existe%n",productID);
            rs.close();
            sentencia.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return prov;
    }

    private void MensajeExcepcion(SQLException e) {
        System.out.printf("HA OCURRIDO UNA EXCEPCIÓN:%n");
        System.out.printf("Mensaje : %s %n", e.getMessage());
        System.out.printf("SQL estado: %s %n", e.getSQLState());
        System.out.printf("Cód error : %s %n", e.getErrorCode());
    }

}
