package subsystems;

import java.sql.*;

public class connectDB {

    //Definir parámetros de conexión
    String url;
    String user;
    String pass;
    Connection conexion;
    String sql;
    //Definir funciones

    //Inicialización de variables (no estáticas)
    public void connparamsinit(){
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

    //Definición de funciones de envío de datos

/*
    public void sendRepresentante(){

        try {
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            query.setInt(1, ci); // cirepresentante
            query.setString(2, rep_apellidos); // apellidos
            query.setString(3, rep_nombres); // nombres
            query.setString(4, estado_civil); // estdciv
            query.setString(5, lugar_nac); // lugar_nac
            query.setString(6, fecha_nac); // fecha_nac (formato YYYY-MM-DD es estándar)
            query.setString(7, nacionalidad); // nacionalidad
            query.setInt(8, edad); // edad
            query.setString(9, direccion_hab); // direccion_hab
            query.setString(10, grado_estudios); // grado_estudios
            query.setString(11, ocupacion); // ocupacion
            query.setString(12, direccion_trabj); // direccion_trabj
            query.setLong(13, celular); // tlf
            query.setString(14, correo); // correo
            query.setString(15, rol); // parentesco
            query.setInt(16, menores_6); // niños menores de 6

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
*/

    public void removerRepresentante(){}

    public void buscarRepresentante(){}

    public void fetchRepresentante(){}

    public void sendEstudiante(){
    }

    public void removerEstudiante(){}

    public void buscarEstudiante(){}

    public void fetchEstudiante(){

    }

    public void sendNomina(){

    }

    public void removerNomina(){}

    public void buscarNomina(){}

    public void fetchNomina(){}

    public void sendNominaMaestra(){}

    public void removerNominaMaestra(){}

    public void buscarNominaMaestra(){}

    public void fetchNominaMaestra(){}

    public void sendAutorizado(){

    }

    public void removerAutorizado(){}

    public void buscarAutorizado(){}

    public void fetchAutorizado(){}

    public void sendDiagnostico(){}

    public void removerDiagnostico(){}

    public void setRelRepresentado(){}

    public void setRelAutorizado(){}

    public void crearUsuario(){}

    public void removerUsuario(){}

    public void buscarUsuario(){
        
        String nombre_usuario;
        nombre_usuario = "SELEC * FROM usuario WHERE usuario = ?";
        String hash;
        hash = "SELEC * FROM usuario WHERE hash = ?";
        String permiso;
        permiso = "SELEC * FROM usuario WHERE permsiso = ?";
        
    }

    public void promoverAño(){}

    public void crearFamiliar(){}

    public void fetchFamiliar(){}


    //Main
    public void main(String args[]) throws SQLException{

        //connparamsinit();
        //sendRepresentante();

    }
}
