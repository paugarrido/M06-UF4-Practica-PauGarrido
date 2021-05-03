package m06.uf4.DAO.empleat.implementacio;

import m06.uf4.DAO.DAOFactory.SQLDAOFactory;
import m06.uf4.DAO.comanda.Comanda;
import m06.uf4.DAO.empleat.Empleat;
import m06.uf4.DAO.empleat.EmpleatDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// EMPLEAT_ID, COGNOM, OFICI, CAP, DATA_ALTA, SALARI, COMISSIO, DEPT_NO

public class EmpleatImpSQL implements EmpleatDAO {
    Connection conexion;

    public EmpleatImpSQL() { conexion = SQLDAOFactory.crearConexion();
    }

    // INSERTAR EMPLEAT
    @Override
    public boolean insertar(Empleat emp) {
        boolean valor = false;
        String sql = "INSERT INTO EMPLEAT VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, emp.getEmplatID());
            sentencia.setString(2, emp.getCognom());
            sentencia.setString(3, emp.getOfici());
            sentencia.setInt(4, emp.getCapId());
            sentencia.setDate(5, new Date(emp.getDataAlta().getTime()));
            sentencia.setInt(6, emp.getSalari());
            sentencia.setInt(7, emp.getComissio());
            sentencia.setInt(8, emp.getDepNo());
            int filas = sentencia.executeUpdate();
            System.out.printf("Filas insertadas: %d%n", filas);
            if (filas > 0) {
                valor = true;
                System.out.printf("Empleat %d insertado%n", emp.getEmplatID());
            }
            sentencia.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return valor;
    }

    // INSERTAR LISTA EMPlEADOS
    @Override
    public int insertarLlista(List<Empleat> emps) {
        for (Empleat empleat : emps){
            insertar(empleat);
        }
        return emps.size();
    }

    // ELIMINAR EMPLEAT
    @Override
    public boolean eliminar(int empId) {
        boolean valor = false;
        String sql = "DELETE FROM EMPLEAT WHERE empleat_id = ? ";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, empId);
            int filas = sentencia.executeUpdate();
            System.out.printf("Filas eliminadas: %d%n", filas);
            if (filas > 0) {
                valor = true;
                System.out.printf("Empleat %d eliminado%n", empId);
            }
            sentencia.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return valor;
    }

    // ELIMINAR CONJUNT EMPLEAT
    @Override
    public boolean eliminarConjunt() {
        boolean valor = false;
        String sql = "DELETE FROM EMPLEAT ";
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

    // MODIFICAR EMPLEAT
    @Override
    public boolean modificar(Empleat emp) {
        boolean valor = false;
        String sql = "UPDATE EMPLEAT SET cognom= ?, ofici = ?, cap_id = ?, data_alta = ?, salari = ?, comissio = ?, dept_no = ? WHERE empleat_id = ? ";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(8, emp.getEmplatID());
            sentencia.setString(1, emp.getCognom());
            sentencia.setString(2, emp.getOfici());
            sentencia.setInt(3, emp.getCapId());
            sentencia.setDate(4, new Date(emp.getDataAlta().getTime()));
            sentencia.setInt(5, emp.getSalari());
            sentencia.setInt(6, emp.getComissio());
            sentencia.setInt(7, emp.getDepNo());
            int filas = sentencia.executeUpdate();
            System.out.printf("Filas modificadas: %d%n", filas);
            if (filas > 0) {
                valor = true;
                System.out.printf("Empleat %d modificado%n", emp.getEmplatID());
            }
            sentencia.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return valor;
    }

    // CONSULTAR EMPLEAT
    @Override
    public Empleat consultar(int empId) {
        String sql = "SELECT empleat_id, cognom, ofici, cap_id, data_alta, salari, comissio, dept_no FROM EMPLEAT WHERE empleat_id = ?";
        PreparedStatement sentencia;
        Empleat emp = new Empleat();
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, empId);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                emp.setEmplatID(rs.getInt("empleat_id"));
                emp.setCognom(rs.getString("cognom"));
                emp.setOfici(rs.getString("ofici"));
                emp.setCapId(rs.getInt("cap_id"));
                emp.setDataAlta(new java.util.Date (rs.getDate("data_alta").getTime()));
                emp.setSalari(rs.getInt("salari"));
                emp.setComissio(rs.getInt("comissio"));
                emp.setDepNo(rs.getInt("dept_no"));

            }else
                System.out.printf("Empleat: %d No existe%n",empId);
            rs.close();// liberar recursos
            sentencia.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return emp;
    }

    // CONSULTAR LLISTA EMPLEAT
    @Override
    public List<Empleat> consultarLlista() {
        String sql = "SELECT * FROM EMPLEAT";
        PreparedStatement sentencia;
        Empleat emp = new Empleat();
        List<Empleat> listEmpls = new ArrayList<>();
        try {
            sentencia = conexion.prepareStatement(sql);

            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                emp.setEmplatID(rs.getInt("empleat_id"));
                emp.setCognom(rs.getString("cognom"));
                emp.setOfici(rs.getString("ofici"));
                emp.setCapId(rs.getInt("cap_id"));
                emp.setDataAlta(new java.util.Date (rs.getDate("data_alta").getTime()));
                emp.setSalari(rs.getInt("salari"));
                emp.setComissio(rs.getInt("comissio"));
                emp.setDepNo(rs.getInt("dept_no"));
                listEmpls.add(emp);
            }
            rs.close();
            sentencia.close();
        } catch (SQLException e) {
            MensajeExcepcion(e);
        }
        return listEmpls;    }

    private void MensajeExcepcion(SQLException e) {
        System.out.printf("HA OCURRIDO UNA EXCEPCIÓN:%n");
        System.out.printf("Mensaje : %s %n", e.getMessage());
        System.out.printf("SQL estado: %s %n", e.getSQLState());
        System.out.printf("Cód error : %s %n", e.getErrorCode());
    }
}
