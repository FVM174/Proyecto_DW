package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/GRUPO3";
    private static final String USER = "root";
    private static final String PASSWORD = "GRUPO3";

    private static Connection conn = null;

    public static Connection getConexion() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Conexión establecida con éxito.");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("❌ Error en la conexión: " + e.getMessage());
            }
        }
        return conn;
    }
}

