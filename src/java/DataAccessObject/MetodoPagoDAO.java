package DataAccessObject;

import BusinessEntity.MetodoPago;
import DBConnection.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class MetodoPagoDAO implements IBaseDAO<MetodoPago> {

    private Connection conn;

    public MetodoPagoDAO() {
        conn = Conexion.getConexion();
    }

    @Override
    public boolean Create(MetodoPago mp) {
        String sql = "INSERT INTO METODO_PAGO (id_metodo, nombre_metodo) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, mp.getIdMetodo());
            ps.setString(2, mp.getNombreMetodo());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Create (MetodoPago): " + e.getMessage());
        }
        return false;
    }

    @Override
    public MetodoPago Read(String id) {
        MetodoPago mp = null;
        String sql = "SELECT * FROM METODO_PAGO WHERE id_metodo = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                mp = new MetodoPago(
                    rs.getInt("id_metodo"),
                    rs.getString("nombre_metodo")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error en Read (MetodoPago): " + e.getMessage());
        }
        return mp;
    }

    @Override
    public ArrayList<MetodoPago> ReadAll() {
        ArrayList<MetodoPago> lista = new ArrayList<>();
        String sql = "SELECT * FROM METODO_PAGO";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                MetodoPago mp = new MetodoPago(
                    rs.getInt("id_metodo"),
                    rs.getString("nombre_metodo")
                );
                lista.add(mp);
            }
        } catch (SQLException e) {
            System.out.println("Error en ReadAll (MetodoPago): " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean Update(MetodoPago mp) {
        String sql = "UPDATE METODO_PAGO SET nombre_metodo = ? WHERE id_metodo = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mp.getNombreMetodo());
            ps.setInt(2, mp.getIdMetodo());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Update (MetodoPago): " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean Delete(String id) {
        String sql = "DELETE FROM METODO_PAGO WHERE id_metodo = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Delete (MetodoPago): " + e.getMessage());
        }
        return false;
    }
}

