package DataAccessObject;

import BusinessEntity.Venta;
import DBConnection.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class VentaDAO implements IBaseDAO<Venta> {

    private Connection conn;

    public VentaDAO() {
        conn = Conexion.getConexion();
    }

    @Override
    public boolean Create(Venta v) {
        String sql = "INSERT INTO VENTA (id_venta, fecha_venta, total_venta, id_cliente, id_empleado, id_metodo) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, v.getIdVenta());
            ps.setDate(2, v.getFechaVenta());
            ps.setFloat(3, v.getTotalVenta());
            ps.setInt(4, v.getIdCliente());
            ps.setInt(5, v.getIdEmpleado());
            ps.setInt(6, v.getIdMetodo());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Create (Venta): " + e.getMessage());
        }
        return false;
    }

    @Override
    public Venta Read(String id) {
        Venta v = null;
        String sql = "SELECT * FROM VENTA WHERE id_venta = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                v = new Venta(
                    rs.getInt("id_venta"),
                    rs.getDate("fecha_venta"),
                    rs.getFloat("total_venta"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_empleado"),
                    rs.getInt("id_metodo")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error en Read (Venta): " + e.getMessage());
        }
        return v;
    }

    @Override
    public ArrayList<Venta> ReadAll() {
        ArrayList<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM VENTA";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Venta v = new Venta(
                    rs.getInt("id_venta"),
                    rs.getDate("fecha_venta"),
                    rs.getFloat("total_venta"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_empleado"),
                    rs.getInt("id_metodo")
                );
                lista.add(v);
            }
        } catch (SQLException e) {
            System.out.println("Error en ReadAll (Venta): " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean Update(Venta v) {
        String sql = "UPDATE VENTA SET fecha_venta = ?, total_venta = ?, id_cliente = ?, id_empleado = ?, id_metodo = ? WHERE id_venta = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, v.getFechaVenta());
            ps.setFloat(2, v.getTotalVenta());
            ps.setInt(3, v.getIdCliente());
            ps.setInt(4, v.getIdEmpleado());
            ps.setInt(5, v.getIdMetodo());
            ps.setInt(6, v.getIdVenta());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Update (Venta): " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean Delete(String id) {
        String sql = "DELETE FROM VENTA WHERE id_venta = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Delete (Venta): " + e.getMessage());
        }
        return false;
    }
}
