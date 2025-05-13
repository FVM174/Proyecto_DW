package DataAccessObject;

import BusinessEntity.Categoria;
import DBConnection.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class CategoriaDAO implements IBaseDAO<Categoria> {

    private Connection conn;

    public CategoriaDAO() {
        conn = Conexion.getConexion();
    }

    @Override
    public boolean Create(Categoria c) {
        String sql = "INSERT INTO CATEGORIA (id_categoria, nombre_categoria) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, c.getIdCategoria());
            ps.setString(2, c.getNombreCategoria());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Create (Categoria): " + e.getMessage());
        }
        return false;
    }

    @Override
    public Categoria Read(String id) {
        Categoria c = null;
        String sql = "SELECT * FROM CATEGORIA WHERE id_categoria = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Categoria(
                    rs.getInt("id_categoria"),
                    rs.getString("nombre_categoria")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error en Read (Categoria): " + e.getMessage());
        }
        return c;
    }

    @Override
    public ArrayList<Categoria> ReadAll() {
        ArrayList<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM CATEGORIA";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Categoria c = new Categoria(
                    rs.getInt("id_categoria"),
                    rs.getString("nombre_categoria")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error en ReadAll (Categoria): " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean Update(Categoria c) {
        String sql = "UPDATE CATEGORIA SET nombre_categoria = ? WHERE id_categoria = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNombreCategoria());
            ps.setInt(2, c.getIdCategoria());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Update (Categoria): " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean Delete(String id) {
        String sql = "DELETE FROM CATEGORIA WHERE id_categoria = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Delete (Categoria): " + e.getMessage());
        }
        return false;
    }
}
