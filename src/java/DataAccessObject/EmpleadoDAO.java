package DataAccessObject;

import BusinessEntity.Empleado;
import DBConnection.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class EmpleadoDAO implements IBaseDAO<Empleado> {

    private Connection conn;

    public EmpleadoDAO() {
        conn = Conexion.getConexion();
    }

    @Override
    public boolean Create(Empleado e) {
        String sql = "INSERT INTO EMPLEADO (id_empleado, nombre_empleado, puesto, telefono_empleado, correo_empleado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, e.getIdEmpleado());
            ps.setString(2, e.getNombreEmpleado());
            ps.setString(3, e.getPuesto());
            ps.setString(4, e.getTelefonoEmpleado());
            ps.setString(5, e.getCorreoEmpleado());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error en Create (Empleado): " + ex.getMessage());
        }
        return false;
    }

    @Override
    public Empleado Read(String id) {
        Empleado e = null;
        String sql = "SELECT * FROM EMPLEADO WHERE id_empleado = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                e = new Empleado(
                    rs.getInt("id_empleado"),
                    rs.getString("nombre_empleado"),
                    rs.getString("puesto"),
                    rs.getString("telefono_empleado"),
                    rs.getString("correo_empleado")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error en Read (Empleado): " + ex.getMessage());
        }
        return e;
    }

    @Override
    public ArrayList<Empleado> ReadAll() {
        ArrayList<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM EMPLEADO";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Empleado e = new Empleado(
                    rs.getInt("id_empleado"),
                    rs.getString("nombre_empleado"),
                    rs.getString("puesto"),
                    rs.getString("telefono_empleado"),
                    rs.getString("correo_empleado")
                );
                lista.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("Error en ReadAll (Empleado): " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public boolean Update(Empleado e) {
        String sql = "UPDATE EMPLEADO SET nombre_empleado = ?, puesto = ?, telefono_empleado = ?, correo_empleado = ? WHERE id_empleado = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getNombreEmpleado());
            ps.setString(2, e.getPuesto());
            ps.setString(3, e.getTelefonoEmpleado());
            ps.setString(4, e.getCorreoEmpleado());
            ps.setInt(5, e.getIdEmpleado());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error en Update (Empleado): " + ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean Delete(String id) {
        String sql = "DELETE FROM EMPLEADO WHERE id_empleado = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error en Delete (Empleado): " + ex.getMessage());
        }
        return false;
    }
}

