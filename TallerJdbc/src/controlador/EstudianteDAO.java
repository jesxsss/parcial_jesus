package controlador;

import conexion_jdbc.Conexion;
import modelo.Estudiante;

import java.sql.*;

public class EstudianteDAO {

    public void insertar(Estudiante e) {
        String sql = "INSERT INTO estudiantes (nombre, apellido, correo, edad, estado_civil) VALUES (?,?,?,?,?)";
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setString(3, e.getCorreo());
            ps.setInt(4, e.getEdad());
            ps.setString(5, e.getEstadoCivil());
            ps.executeUpdate();
            System.out.println(" Estudiante insertado correctamente.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void actualizar(Estudiante e) {
        String sql = "UPDATE estudiantes SET nombre=?, apellido=?, edad=?, estado_civil=? WHERE correo=?";
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setInt(3, e.getEdad());
            ps.setString(4, e.getEstadoCivil());
            ps.setString(5, e.getCorreo());
            ps.executeUpdate();
            System.out.println(" Estudiante actualizado.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void eliminar(String correo) {
        String sql = "DELETE FROM estudiantes WHERE correo=?";
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            ps.executeUpdate();
            System.out.println(" Estudiante eliminado.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void listarTodos() {
        String sql = "SELECT * FROM estudiantes";
        try (Connection con = Conexion.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("nombre") + " " +
                                rs.getString("apellido") + " | " +
                                rs.getString("correo") + " | " +
                                rs.getInt("edad") + " | " +
                                rs.getString("estado_civil")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void consultarPorCorreo(String correo) {
        String sql = "SELECT * FROM estudiantes WHERE correo=?";
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println(
                            rs.getInt("id") + " | " +
                                    rs.getString("nombre") + " " +
                                    rs.getString("apellido") + " | " +
                                    rs.getString("correo") + " | " +
                                    rs.getInt("edad") + " | " +
                                    rs.getString("estado_civil")
                    );
                } else {
                    System.out.println("No se encontró estudiante con ese correo.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}