package DataAccessObject;

import BusinessEntity.Marca;
import DBConnection.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class MarcaDAO implements IBaseDAO<Marca> {

    private Connection conn;

    public MarcaDAO() {
        conn = Conexion.getConexion();
    }

    @Override
    public boolean Create(Marca m) {
        String sql = "INSERT INTO MARCA (id_marca, nombre_marca) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, m.getIdMarca());
            ps.setString(2, m.getNombreMarca());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Create (Marca): " + e.getMessage());
        }
        return false;
    }

    @Override
    public Marca Read(String id) {
        Marca m = null;
        String sql = "SELECT * FROM MARCA WHERE id_marca = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                m = new Marca(
                    rs.getInt("id_marca"),
                    rs.getString("nombre_marca")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error en Read (Marca): " + e.getMessage());
        }
        return m;
    }

    @Override
    public ArrayList<Marca> ReadAll() {
        ArrayList<Marca> lista = new ArrayList<>();
        String sql = "SELECT * FROM MARCA";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Marca m = new Marca(
                    rs.getInt("id_marca"),
                    rs.getString("nombre_marca")
                );
                lista.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Error en ReadAll (Marca): " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean Update(Marca m) {
        String sql = "UPDATE MARCA SET nombre_marca = ? WHERE id_marca = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getNombreMarca());
            ps.setInt(2, m.getIdMarca());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Update (Marca): " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean Delete(String id) {
        String sql = "DELETE FROM MARCA WHERE id_marca = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Delete (Marca): " + e.getMessage());
        }
        return false;
    }
}
