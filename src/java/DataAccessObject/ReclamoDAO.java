package DataAccessObject;

import BusinessEntity.Reclamo;
import DBConnection.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class ReclamoDAO implements IBaseDAO<Reclamo> {

    private Connection conn;

    public ReclamoDAO() {
        conn = Conexion.getConexion();
    }

    @Override
    public boolean Create(Reclamo r) {
        String sql = "INSERT INTO RECLAMO (id_reclamo, id_venta, fecha_reclamo, descripcion, estado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, r.getIdReclamo());
            ps.setInt(2, r.getIdVenta());
            ps.setDate(3, r.getFechaReclamo());
            ps.setString(4, r.getDescripcion());
            ps.setString(5, r.getEstado());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Create (Reclamo): " + e.getMessage());
        }
        return false;
    }

    @Override
    public Reclamo Read(String id) {
        Reclamo r = null;
        String sql = "SELECT * FROM RECLAMO WHERE id_reclamo = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                r = new Reclamo(
                    rs.getInt("id_reclamo"),
                    rs.getInt("id_venta"),
                    rs.getDate("fecha_reclamo"),
                    rs.getString("descripcion"),
                    rs.getString("estado")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error en Read (Reclamo): " + e.getMessage());
        }
        return r;
    }

    @Override
    public ArrayList<Reclamo> ReadAll() {
        ArrayList<Reclamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM RECLAMO";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Reclamo r = new Reclamo(
                    rs.getInt("id_reclamo"),
                    rs.getInt("id_venta"),
                    rs.getDate("fecha_reclamo"),
                    rs.getString("descripcion"),
                    rs.getString("estado")
                );
                lista.add(r);
            }
        } catch (SQLException e) {
            System.out.println("Error en ReadAll (Reclamo): " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean Update(Reclamo r) {
        String sql = "UPDATE RECLAMO SET id_venta = ?, fecha_reclamo = ?, descripcion = ?, estado = ? WHERE id_reclamo = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, r.getIdVenta());
            ps.setDate(2, r.getFechaReclamo());
            ps.setString(3, r.getDescripcion());
            ps.setString(4, r.getEstado());
            ps.setInt(5, r.getIdReclamo());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Update (Reclamo): " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean Delete(String id) {
        String sql = "DELETE FROM RECLAMO WHERE id_reclamo = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Delete (Reclamo): " + e.getMessage());
        }
        return false;
    }
}

