package DataAccessObject;

import BusinessEntity.Cliente;
import DBConnection.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO implements IBaseDAO<Cliente> {

    private Connection conn;

    public ClienteDAO() {
        conn = Conexion.getConexion();
    }

    @Override
    public boolean Create(Cliente c) {
        String sql = "INSERT INTO CLIENTE (id_cliente, nombre_cliente, direccion_cliente, telefono_cliente) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, c.getIdCliente());
            ps.setString(2, c.getNombreCliente());
            ps.setString(3, c.getDireccionCliente());
            ps.setString(4, c.getTelefonoCliente());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Create (Cliente): " + e.getMessage());
        }
        return false;
    }

    @Override
    public Cliente Read(String id) {
        Cliente c = null;
        String sql = "SELECT * FROM CLIENTE WHERE id_cliente = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nombre_cliente"),
                    rs.getString("direccion_cliente"),
                    rs.getString("telefono_cliente")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error en Read (Cliente): " + e.getMessage());
        }
        return c;
    }

    @Override
    public ArrayList<Cliente> ReadAll() {
        ArrayList<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM CLIENTE";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nombre_cliente"),
                    rs.getString("direccion_cliente"),
                    rs.getString("telefono_cliente")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error en ReadAll (Cliente): " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean Update(Cliente c) {
        String sql = "UPDATE CLIENTE SET nombre_cliente = ?, direccion_cliente = ?, telefono_cliente = ? WHERE id_cliente = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNombreCliente());
            ps.setString(2, c.getDireccionCliente());
            ps.setString(3, c.getTelefonoCliente());
            ps.setInt(4, c.getIdCliente());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Update (Cliente): " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean Delete(String id) {
        String sql = "DELETE FROM CLIENTE WHERE id_cliente = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Delete (Cliente): " + e.getMessage());
        }
        return false;
    }
}
