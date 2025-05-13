
package DataAccessObject;

import BusinessEntity.DetalleVenta;
import DBConnection.Conexion;

import java.sql.*;
import java.util.ArrayList;
public class DetalleVentaDAO implements IBaseDAO<DetalleVenta> {

    private Connection conn;

    public DetalleVentaDAO() {
        conn = Conexion.getConexion();
    }

    @Override
    public boolean Create(DetalleVenta d) {
        String sql = "INSERT INTO DETALLE_VENTA (id_detalle_venta, id_venta, id_producto, cantidad, precio_unitario_venta, subtotal) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, d.getIdDetalleVenta());
            ps.setInt(2, d.getIdVenta());
            ps.setInt(3, d.getIdProducto());
            ps.setInt(4, d.getCantidad());
            ps.setFloat(5, d.getPrecioUnitarioVenta());
            ps.setFloat(6, d.getSubtotal());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Create: " + e.getMessage());
        }
        return false;
    }

    @Override
    public DetalleVenta Read(String id) {
        DetalleVenta d = null;
        String sql = "SELECT * FROM DETALLE_VENTA WHERE id_detalle_venta = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                d = new DetalleVenta(
                    rs.getInt("id_detalle_venta"),
                    rs.getInt("id_venta"),
                    rs.getInt("id_producto"),
                    rs.getInt("cantidad"),
                    rs.getFloat("precio_unitario_venta"),
                    rs.getFloat("subtotal")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error en Read: " + e.getMessage());
        }
        return d;
    }

    @Override
    public ArrayList<DetalleVenta> ReadAll() {
        ArrayList<DetalleVenta> lista = new ArrayList<>();
        String sql = "SELECT * FROM DETALLE_VENTA";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                DetalleVenta d = new DetalleVenta(
                    rs.getInt("id_detalle_venta"),
                    rs.getInt("id_venta"),
                    rs.getInt("id_producto"),
                    rs.getInt("cantidad"),
                    rs.getFloat("precio_unitario_venta"),
                    rs.getFloat("subtotal")
                );
                lista.add(d);
            }
        } catch (SQLException e) {
            System.out.println("Error en ReadAll: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean Update(DetalleVenta d) {
        String sql = "UPDATE DETALLE_VENTA SET id_venta = ?, id_producto = ?, cantidad = ?, precio_unitario_venta = ?, subtotal = ? WHERE id_detalle_venta = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, d.getIdVenta());
            ps.setInt(2, d.getIdProducto());
            ps.setInt(3, d.getCantidad());
            ps.setFloat(4, d.getPrecioUnitarioVenta());
            ps.setFloat(5, d.getSubtotal());
            ps.setInt(6, d.getIdDetalleVenta());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Update: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean Delete(String id) {
        String sql = "DELETE FROM DETALLE_VENTA WHERE id_detalle_venta = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Delete: " + e.getMessage());
        }
        return false;
    }
}
