package DataAccessObject;

import BusinessEntity.Descuento;
import DBConnection.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class DescuentoDAO implements IBaseDAO<Descuento> {

    private Connection conn;

    public DescuentoDAO() {
        conn = Conexion.getConexion();
    }

    @Override
    public boolean Create(Descuento d) {
        String sql = "INSERT INTO DESCUENTO (id_descuento, porcentaje, fecha_inicio, fecha_fin) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, d.getIdDescuento());
            ps.setFloat(2, d.getPorcentaje());
            ps.setDate(3, d.getFechaInicio());
            ps.setDate(4, d.getFechaFin());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Create (Descuento): " + e.getMessage());
        }
        return false;
    }

    @Override
    public Descuento Read(String id) {
        Descuento d = null;
        String sql = "SELECT * FROM DESCUENTO WHERE id_descuento = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                d = new Descuento(
                    rs.getInt("id_descuento"),
                    rs.getFloat("porcentaje"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error en Read (Descuento): " + e.getMessage());
        }
        return d;
    }

    @Override
    public ArrayList<Descuento> ReadAll() {
        ArrayList<Descuento> lista = new ArrayList<>();
        String sql = "SELECT * FROM DESCUENTO";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Descuento d = new Descuento(
                    rs.getInt("id_descuento"),
                    rs.getFloat("porcentaje"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin")
                );
                lista.add(d);
            }
        } catch (SQLException e) {
            System.out.println("Error en ReadAll (Descuento): " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean Update(Descuento d) {
        String sql = "UPDATE DESCUENTO SET porcentaje = ?, fecha_inicio = ?, fecha_fin = ? WHERE id_descuento = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setFloat(1, d.getPorcentaje());
            ps.setDate(2, d.getFechaInicio());
            ps.setDate(3, d.getFechaFin());
            ps.setInt(4, d.getIdDescuento());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Update (Descuento): " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean Delete(String id) {
        String sql = "DELETE FROM DESCUENTO WHERE id_descuento = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Delete (Descuento): " + e.getMessage());
        }
        return false;
    }
}

