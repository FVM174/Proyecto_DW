package DataAccessObject;

import BusinessEntity.MovimientoInventario;
import DBConnection.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class MovimientoInventarioDAO implements IBaseDAO<MovimientoInventario> {

    private Connection conn;

    public MovimientoInventarioDAO() {
        conn = Conexion.getConexion();
    }

    @Override
    public boolean Create(MovimientoInventario m) {
        String sql = "INSERT INTO MOVIMIENTO_INVENTARIO (id_movimiento, id_producto, tipo_movimiento, cantidad, fecha_movimiento, motivo) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, m.getIdMovimiento());
            ps.setInt(2, m.getIdProducto());
            ps.setString(3, m.getTipoMovimiento());
            ps.setInt(4, m.getCantidad());
            ps.setDate(5, m.getFechaMovimiento());
            ps.setString(6, m.getMotivo());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Create (MovimientoInventario): " + e.getMessage());
        }
        return false;
    }

    @Override
    public MovimientoInventario Read(String id) {
        MovimientoInventario m = null;
        String sql = "SELECT * FROM MOVIMIENTO_INVENTARIO WHERE id_movimiento = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                m = new MovimientoInventario(
                    rs.getInt("id_movimiento"),
                    rs.getInt("id_producto"),
                    rs.getString("tipo_movimiento"),
                    rs.getInt("cantidad"),
                    rs.getDate("fecha_movimiento"),
                    rs.getString("motivo")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error en Read (MovimientoInventario): " + e.getMessage());
        }
        return m;
    }

    @Override
    public ArrayList<MovimientoInventario> ReadAll() {
        ArrayList<MovimientoInventario> lista = new ArrayList<>();
        String sql = "SELECT * FROM MOVIMIENTO_INVENTARIO";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                MovimientoInventario m = new MovimientoInventario(
                    rs.getInt("id_movimiento"),
                    rs.getInt("id_producto"),
                    rs.getString("tipo_movimiento"),
                    rs.getInt("cantidad"),
                    rs.getDate("fecha_movimiento"),
                    rs.getString("motivo")
                );
                lista.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Error en ReadAll (MovimientoInventario): " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean Update(MovimientoInventario m) {
        String sql = "UPDATE MOVIMIENTO_INVENTARIO SET id_producto = ?, tipo_movimiento = ?, cantidad = ?, fecha_movimiento = ?, motivo = ? WHERE id_movimiento = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, m.getIdProducto());
            ps.setString(2, m.getTipoMovimiento());
            ps.setInt(3, m.getCantidad());
            ps.setDate(4, m.getFechaMovimiento());
            ps.setString(5, m.getMotivo());
            ps.setInt(6, m.getIdMovimiento());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Update (MovimientoInventario): " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean Delete(String id) {
        String sql = "DELETE FROM MOVIMIENTO_INVENTARIO WHERE id_movimiento = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Delete (MovimientoInventario): " + e.getMessage());
        }
        return false;
    }
}

