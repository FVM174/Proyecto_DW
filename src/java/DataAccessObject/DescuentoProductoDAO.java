package DataAccessObject;

import BusinessEntity.DescuentoProducto;
import DBConnection.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class DescuentoProductoDAO implements IBaseDAO<DescuentoProducto> {

    private Connection conn;

    public DescuentoProductoDAO() {
        conn = Conexion.getConexion();
    }

    @Override
    public boolean Create(DescuentoProducto dp) {
        String sql = "INSERT INTO DESCUENTO_PRODUCTO (id_producto, id_descuento) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, dp.getIdProducto());
            ps.setInt(2, dp.getIdDescuento());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Create (DescuentoProducto): " + e.getMessage());
        }
        return false;
    }

    @Override
    public DescuentoProducto Read(String id) {
        DescuentoProducto dp = null;
        String sql = "SELECT * FROM DESCUENTO_PRODUCTO WHERE id_producto = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dp = new DescuentoProducto(
                    rs.getInt("id_producto"),
                    rs.getInt("id_descuento")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error en Read (DescuentoProducto): " + e.getMessage());
        }
        return dp;
    }

    @Override
    public ArrayList<DescuentoProducto> ReadAll() {
        ArrayList<DescuentoProducto> lista = new ArrayList<>();
        String sql = "SELECT * FROM DESCUENTO_PRODUCTO";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                DescuentoProducto dp = new DescuentoProducto(
                    rs.getInt("id_producto"),
                    rs.getInt("id_descuento")
                );
                lista.add(dp);
            }
        } catch (SQLException e) {
            System.out.println("Error en ReadAll (DescuentoProducto): " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean Update(DescuentoProducto dp) {
        String sql = "UPDATE DESCUENTO_PRODUCTO SET id_descuento = ? WHERE id_producto = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, dp.getIdDescuento());
            ps.setInt(2, dp.getIdProducto());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Update (DescuentoProducto): " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean Delete(String id) {
        String sql = "DELETE FROM DESCUENTO_PRODUCTO WHERE id_producto = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Delete (DescuentoProducto): " + e.getMessage());
        }
        return false;
    }
}

