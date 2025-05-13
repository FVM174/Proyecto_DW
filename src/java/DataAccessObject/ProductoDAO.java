package DataAccessObject;

import BusinessEntity.Producto;
import DBConnection.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class ProductoDAO implements IBaseDAO<Producto> {

    private Connection conn;

    public ProductoDAO() {
        conn = Conexion.getConexion();
    }

    @Override
    public boolean Create(Producto p) {
        String sql = "INSERT INTO PRODUCTO (id_producto, nombre_producto, descripcion_producto, precio_unitario, id_marca, id_categoria, color) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getIdProducto());
            ps.setString(2, p.getNombreProducto());
            ps.setString(3, p.getDescripcionProducto());
            ps.setFloat(4, p.getPrecioUnitario());
            ps.setInt(5, p.getIdMarca());
            ps.setInt(6, p.getIdCategoria());
            ps.setString(7, p.getColor());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Create (Producto): " + e.getMessage());
        }
        return false;
    }

    @Override
    public Producto Read(String id) {
        Producto p = null;
        String sql = "SELECT * FROM PRODUCTO WHERE id_producto = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre_producto"),
                    rs.getString("descripcion_producto"),
                    rs.getFloat("precio_unitario"),
                    rs.getInt("id_marca"),
                    rs.getInt("id_categoria"),
                    rs.getString("color")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error en Read (Producto): " + e.getMessage());
        }
        return p;
    }

    @Override
    public ArrayList<Producto> ReadAll() {
        ArrayList<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTO";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Producto p = new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre_producto"),
                    rs.getString("descripcion_producto"),
                    rs.getFloat("precio_unitario"),
                    rs.getInt("id_marca"),
                    rs.getInt("id_categoria"),
                    rs.getString("color")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error en ReadAll (Producto): " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean Update(Producto p) {
        String sql = "UPDATE PRODUCTO SET nombre_producto = ?, descripcion_producto = ?, precio_unitario = ?, id_marca = ?, id_categoria = ?, color = ? WHERE id_producto = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNombreProducto());
            ps.setString(2, p.getDescripcionProducto());
            ps.setFloat(3, p.getPrecioUnitario());
            ps.setInt(4, p.getIdMarca());
            ps.setInt(5, p.getIdCategoria());
            ps.setString(6, p.getColor());
            ps.setInt(7, p.getIdProducto());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Update (Producto): " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean Delete(String id) {
        String sql = "DELETE FROM PRODUCTO WHERE id_producto = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Delete (Producto): " + e.getMessage());
        }
        return false;
    }
}

