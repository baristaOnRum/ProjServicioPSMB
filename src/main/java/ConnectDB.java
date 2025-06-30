import java.sql.*;

public class ConnectDB {
    String url;
    String user;
    String pass;
    Connection conexion;
    String sql;
    public void paramsinit(){
        url = "jdbc:mysql://localhost:3306/mydb";
        user = "root";
        pass = "xxx";
        conexion = null;
        sql = "INSERT INTO representante (" +
                "cirepresentante, apellidos, nombres, estdciv, " +
                "`lugar(nac)`, `fecha(nac)`, nacionalidad, edad, " +
                "`direccion(hab)`, `grado(estudios)`, ocupacion, " +
                "`direccion(trabj)`, tlf, correo, parentesco, " +
                "`niños menores de 6`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }
    public void sendRepresentante(){

        try {
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            query.setInt(1, 28123456); // cirepresentante
            query.setString(2, "González Pérez"); // apellidos
            query.setString(3, "Carlos Eduardo"); // nombres
            query.setString(4, "Casado"); // estdciv
            query.setString(5, "Maturín, Monagas"); // lugar_nac
            query.setString(6, "1985-10-20"); // fecha_nac (formato YYYY-MM-DD es estándar)
            query.setString(7, "Venezolana"); // nacionalidad
            query.setString(8, "38"); // edad
            query.setString(9, "Av. Libertador,"); // direccion_hab
            query.setString(10, "Licenciado en Administración"); // grado_estudios
            query.setString(11, "Administrador"); // ocupacion
            query.setString(12, "Zona Industrial, Calle Princ"); // direccion_trabj
            query.setString(13, "0416-6921122"); // tlf
            query.setString(14, "carlos.gonzalez.test@email.com"); // correo
            query.setString(15, "Padre"); // parentesco
            query.setString(16, "1"); // niños menores de 6

            // --- Ejecución de la consulta ---
            int columnasAfectadas = query.executeUpdate();

            // --- Verificación del resultado ---
            if (columnasAfectadas > 0) {
                System.out.println("¡Fila insertada exitosamente!");
            } else {
                System.out.println("La inserción de la fila ha fallado.");
            }


        }
        catch(SQLException e) {
            System.err.println("Cannot connect to the database!");
            e.printStackTrace();
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                    System.out.println("Database connection closed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public static void main(String args[]) throws SQLException{
        
    paramsinit();

    }
}
