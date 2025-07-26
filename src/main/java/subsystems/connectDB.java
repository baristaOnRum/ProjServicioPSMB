package subsystems;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Blob;
import subsystems.individuos.*;

public class connectDB {

    //Definir parámetros de conexión
    static String url;
    static String user;
    static String pass;
    static Connection conexion;
    static String sql;
    //Definir funciones

    //Inicialización de variables (no estáticas)
    public void connparamsinit(){
        url = "jdbc:mysql://localhost:3306/mydb";
        user = "root";
        pass = "xxx";
        conexion = null;
    }

    //Definición de funciones de envío de datos

    public static void sendRepresentante(representante representante){

        sql = "INSERT INTO representante " +
                "(ciRepresentante, apellidos, nombres, estdCiv, lugarNac, fechaNac," +
                "nacionalidad, edad, direccionHab, gradoEstudios, ocupacion, direccionTrabj," +
                "tlf1, tlf2, tlfTrabajo, tlfCasa, correo, menores6, img)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            query.setInt(1, representante.getCi()); // cirepresentante
            query.setString(2, representante.getApellidos()); // apellidos
            query.setString(3, representante.getNombres()); // nombres
            query.setString(4, representante.getEstadoCivil()); // estdciv
            query.setString(5, representante.getLugarNac()); // lugar_nac
            query.setDate(6, Date.valueOf(representante.getFechaNac())); // fecha_nac (formato YYYY-MM-DD es estándar)
            query.setString(7, representante.getNacionalidad()); // nacionalidad
            query.setInt(8, representante.getEdad()); // edad
            query.setString(9, representante.getDireccionHab()); // direccion_hab
            query.setString(10, representante.getGradoEstudios()); // grado_estudios
            query.setString(11, representante.getOcupacion()); // ocupacion
            query.setString(12, representante.getDireccionTrabj()); // direccion_trabj
            query.setString(13, representante.getTlf1()); // tlf
            query.setString(14, representante.getTlf2()); // tlf
            query.setString(15, representante.getTlfTrabajo()); // tlf
            query.setString(16, representante.getTlfCasa()); // tlf
            query.setString(17, representante.getCorreo()); // correo
            query.setBoolean(18, representante.isNinosMenor6()); // niños menores de 6
            if (representante.getImg() != null) {
                query.setBytes(19, representante.getImg());
            } else {
                System.err.println("Imagen no existente");
                query.setNull(19, java.sql.Types.BLOB); // Set as NULL if file not found
            }

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

    public static void removerRepresentante(representante representante){
        sql = "DELETE FROM representante" +
                " WHERE ciRepresentante = ?;";
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);
            query.setInt(1, representante.getCi());

            // --- Ejecución de la consulta ---
            int columnasAfectadas = query.executeUpdate();

            // --- Verificación del resultado ---
            if (columnasAfectadas > 0) {
                System.out.println("¡Fila insertada exitosamente!");
            } else {
                System.out.println("La inserción de la fila ha fallado.");
            }
        } catch(SQLException e) {
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

    public static List<representante> buscarRepresentante(String criterio, String busquedaQuery){
        List<representante> representantes = new ArrayList<>();

        sql = "SELECT " +
                "nombres, apellidos, lugarNac, fechaNac, " +
                "ciRepresentante, edad, menores6, estdCiv, nacionalidad, " +
                "direccionHab, direccionTrabj, ocupacion, gradoEstudios, " +
                "tlf1, tlf2, tlfTrabajo, tlfCasa, correo, img " +
                "FROM representante WHERE " + criterio + " = \'" + busquedaQuery + "\'";
    try {
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
        System.out.println("Database connection started.");
        PreparedStatement query = conexion.prepareStatement(sql);

        ResultSet rs = query.executeQuery();

        while (rs.next()){
            representante representante = new representante();
            representante.setNombres(rs.getString("nombres"));
            representante.setApellidos(rs.getString("apellidos"));
            representante.setLugarNac(rs.getString("lugarNac"));
            representante.setFechaNac(rs.getDate("fechaNac").toLocalDate());
            representante.setCi(rs.getInt("ciRepresentante"));
            representante.setEdad(rs.getInt("edad"));
            representante.setNinosMenor6(rs.getBoolean("menores6"));
            representante.setEstadoCivil(rs.getString("estdCiv"));
            representante.setNacionalidad(rs.getString("nacionalidad"));
            representante.setDireccionHab(rs.getString("direccionHab"));
            representante.setDireccionTrabj(rs.getString("direccionTrabj"));
            representante.setOcupacion(rs.getString("ocupacion"));
            representante.setGradoEstudios(rs.getString("gradoEstudios"));
            representante.setTlf1(rs.getString("tlf1"));
            representante.setTlf2(rs.getString("tlf2"));
            representante.setTlfTrabajo(rs.getString("tlfTrabajo"));
            representante.setTlfCasa(rs.getString("tlfCasa"));
            representante.setCorreo(rs.getString("correo"));
            representante.setImg(rs.getBytes("img"));
            representantes.add(representante);
        }
    } catch(SQLException e) {
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
    return representantes;
}

    public static representante fetchRepresentante(int ci) {
        sql = "SELECT " +
                "nombres, apellidos, lugarNac, fechaNac, " +
                "ciRepresentante, edad, menores6, estdCiv, nacionalidad, " +
                "direccionHab, direccionTrabj, ocupacion, gradoEstudios, " +
                "tlf1, tlf2, tlfTrabajo, tlfCasa, correo, img " +
                "FROM representante WHERE ciRepresentante = " + ci;
        representante representante = new representante();

        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                representante.setNombres(rs.getString("nombres"));
                representante.setApellidos(rs.getString("apellidos"));
                representante.setLugarNac(rs.getString("lugarNac"));
                representante.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                representante.setCi(rs.getInt("ciRepresentante"));
                representante.setEdad(rs.getInt("edad"));
                representante.setNinosMenor6(rs.getBoolean("menores6"));
                representante.setEstadoCivil(rs.getString("estdCiv"));
                representante.setNacionalidad(rs.getString("nacionalidad"));
                representante.setDireccionHab(rs.getString("direccionHab"));
                representante.setDireccionTrabj(rs.getString("direccionTrabj"));
                representante.setOcupacion(rs.getString("ocupacion"));
                representante.setGradoEstudios(rs.getString("gradoEstudios"));
                representante.setTlf1(rs.getString("tlf1"));
                representante.setTlf2(rs.getString("tlf2"));
                representante.setTlfTrabajo(rs.getString("tlfTrabajo"));
                representante.setTlfCasa(rs.getString("tlfCasa"));
                representante.setCorreo(rs.getString("correo"));
                representante.setImg(rs.getBytes("img"));
            }

        } catch(SQLException e) {
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
        if (representante != null){ return representante; } else { return null;}
    }

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
