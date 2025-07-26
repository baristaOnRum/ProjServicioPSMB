package subsystems;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import subsystems.individuos.autorizado;
import subsystems.individuos.diagnostico;
import subsystems.individuos.estudiante;
import subsystems.individuos.representante;

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

    public static void removerRepresentante(int ci){
        sql = "DELETE FROM representante" +
                " WHERE ciRepresentante = ?;";
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);
            query.setInt(1, ci);

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
        if (!"any".equals(busquedaQuery)){
            sql = "SELECT " +
                    "nombres, apellidos, lugarNac, fechaNac, " +
                    "ciRepresentante, edad, menores6, estdCiv, nacionalidad, " +
                    "direccionHab, direccionTrabj, ocupacion, gradoEstudios, " +
                    "tlf1, tlf2, tlfTrabajo, tlfCasa, correo, img " +
                    "FROM representante WHERE " + criterio + " = \'" + busquedaQuery + "\'";
        } else {
            sql = "SELECT * FROM representante";
        }
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
            
            return representantes;
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

    public static void updateRepresentante(representante representante){
        sql = " UPDATE representante SET apellidos = ?," +
                "nombres = ?, estdCiv = ?, lugarNac = ?, fechaNac = ?," +
                "nacionalidad = ?, edad = ?, direccionHab = ?, gradoEstudios = ?," +
                "ocupacion = ?, direccionTrabj = ?, tlf1 = ?, tlf2 = ?," +
                "tlfTrabajo = ?, tlfCasa = ?, correo = ?, menores6 = ?," +
                "img = ? WHERE ciRepresentante = ?;"; // VALUES ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"

        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            query.setString(1, representante.getApellidos()); // cirepresentante
            query.setString(2, representante.getNombres()); // apellidos
            query.setString(3, representante.getEstadoCivil()); // nombres
            query.setString(4, representante.getLugarNac()); // estdciv
            query.setDate(5, Date.valueOf(representante.getFechaNac())); // lugar_nac
            query.setString(6, representante.getNacionalidad()); // fecha_nac (formato YYYY-MM-DD es estándar)
            query.setInt(7, representante.getEdad()); // nacionalidad
            query.setString(8, representante.getDireccionHab()); // direccion_hab
            query.setString(9, representante.getGradoEstudios()); // grado_estudios
            query.setString(10, representante.getOcupacion()); // ocupacion
            query.setString(11, representante.getDireccionTrabj()); // direccion_trabj
            query.setString(12, representante.getTlf1()); // tlf
            query.setString(13, representante.getTlf2()); // tlf
            query.setString(14, representante.getTlfTrabajo()); // tlf
            query.setString(15, representante.getTlfCasa()); // tlf
            query.setString(16, representante.getCorreo()); // correo
            query.setBoolean(17, representante.isNinosMenor6()); // niños menores de 6
            if (representante.getImg() != null) {
                query.setBytes(18, representante.getImg());
            } else {
                System.err.println("Imagen no existente");
                query.setNull(18, java.sql.Types.BLOB); // Set as NULL if file not found
            }
            query.setInt(19,representante.getCi());

            // --- Ejecución de la consulta ---
            int columnasAfectadas = query.executeUpdate();
            System.out.print(columnasAfectadas);

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

    public void sendEstudiante(estudiante estudiante){

        sql = "INSERT INTO `mydb`.`estudiante` (`ciEstudiante`, `apellidos`, `nombres`,"+
        "`fechaNac`, `lugarNac`, `nacionalidad`, `edad`, `procedencia`,"+
        "`tallaCam`, `tallaPant`, `tallaZap`, `peso`, `estatura`, `periodioCurso`,"+
        "`periodoCursado`, `img`, `lateralidad`, `grupoSanguineo`, `asegurado`,"+
        "`cualSeguro`, `medicoTratante`, `tlfMedicoTratante`)"+
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            query.setString(1, estudiante.getCe()); // ceestudiante
            query.setString(2, estudiante.getApellidos()); // apellidos
            query.setString(3, estudiante.getNombres()); // nombres
            query.setDate(6, Date.valueOf(estudiante.getFechaNac())); // fecha_nac (formato YYYY-MM-DD es estándar)
            query.setString(5, estudiante.getLugarNac()); // lugar_nac
            query.setString(7, estudiante.getNacionalidad()); // nacionalidad
            query.setInt(8, estudiante.getEdad()); // edad
            query.setString(9, estudiante.getProcedencia()); // procedencia
            query.setInt(10, estudiante.getTallaCamisa()); // tallaCam
            query.setInt(11, estudiante.getTallaPantalon()); // tallaPant
            query.setInt(12, estudiante.getTallaZapato()); // tallaZap
            query.setDouble(13, estudiante.getPeso()); // peso
            query.setDouble(14, estudiante.getEstatura()); // estatura
            query.setInt(15, estudiante.getPeriodoCurso()); // periodoCurso
            query.setInt(16, estudiante.getPeriodoCursado()); // periodoCursado
             if (estudiante.getImg() != null) {
                 query.setBytes(17, estudiante.getImg());
             } else {
                 System.err.println("Imagen no existente");
                 query.setNull(17, java.sql.Types.BLOB); // Set as NULL if file not found
             }
            query.setBoolean(18, estudiante.isLateralidad()); // lateralidad
            query.setString(19, estudiante.getGrupoSanguineo()); // grupoSanguineo
            query.setBoolean(20, estudiante.isAsegurado()); // asegurado
            query.setString(21, estudiante.getCualSeguro()); // cualSeguro
            query.setString(22, estudiante.getMedicoTratante()); // medicoTratante
            query.setString(23, estudiante.getTlfMedicoTratante()); // tlfMedicoTratante
            
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

    public void removerEstudiante(String ce){

        sql = "DELETE FROM estudiante" +
                " WHERE ciEstudiante = ?;";
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);
            query.setString(1, ce);

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

    public static List<estudiante> buscarEstudiante(String criterio, String busquedaQuery){
        List<estudiante> estudiantes = new ArrayList<>();

        sql = "SELECT " +
                "nombres, apellidos, lugarNac, fechaNac, " +
                "ciEstudiante, edad, procedencia, nacionalidad, " +
                "tallaCamisa, tallaPantalon, tallaZapato, peso,"+
                "estatura, periodoCurso, periodoCursado," +
                "lateralidad, grupoSanguineo, asegurado,"+
                "cualSeguro, medicoTratante, tlfMedicoTratante " +
                "FROM estudiante WHERE " + criterio + " = \'" + busquedaQuery + "\'";
                
    try {
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
        System.out.println("Database connection started.");
        PreparedStatement query = conexion.prepareStatement(sql);

        ResultSet rs = query.executeQuery();

        while (rs.next()){
            estudiante estudiante = new estudiante();
            estudiante.setNombres(rs.getString("nombres"));
            estudiante.setApellidos(rs.getString("apellidos"));
            estudiante.setLugarNac(rs.getString("lugarNac"));
            estudiante.setFechaNac(rs.getDate("fechaNac").toLocalDate());
            estudiante.setCe(rs.getString("ciEstudiante"));
            estudiante.setEdad(rs.getInt("edad"));
            estudiante.setProcedencia(rs.getString("procedencia"));
            estudiante.setNacionalidad(rs.getString("nacionalidad"));
            estudiante.setTallaCamisa(rs.getInt("tallaCamisa"));
            estudiante.setTallaPantalon(rs.getInt("tallaPantalon"));
            estudiante.setTallaZapato(rs.getInt("tallaZapato"));
            estudiante.setPeso(rs.getInt("peso"));
            estudiante.setEstatura(rs.getInt("estatura"));
            estudiante.setPeriodoCurso(rs.getInt("periodoCurso"));
            estudiante.setPeriodoCursado(rs.getInt("periodoCursado"));
            estudiante.setLateralidad(rs.getBoolean("lateralidad"));
            estudiante.setGrupoSanguineo(rs.getString("grupoSanguineo"));
            estudiante.setAsegurado(rs.getBoolean("asegurado"));
            estudiante.setCualSeguro(rs.getString("cualSeguro"));
            estudiante.setMedicoTratante(rs.getString("medicoTratante"));
            estudiante.setTlfMedicoTratante(rs.getString("tlfMedicoTratante"));
            estudiante.setImg(rs.getBytes("img"));

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
    return estudiantes;
    }

    public static estudiante fetchEstudiante(String ce){ 

        sql = "SELECT " +
                "nombres, apellidos, lugarNac, fechaNac, " +
                "ciEstudiante, edad, procedencia, nacionalidad, " +
                "tallaCamisa, tallaPantalon, tallaZapato, peso,"+
                "estatura, periodoCurso, periodoCursado," +
                "lateralidad, grupoSanguineo, asegurado,"+
                "cualSeguro, medicoTratante, tlfMedicoTratante " +
                "FROM estudiante WHERE ciEstudiante = "+ ce;

        estudiante estudiante = new estudiante();

        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                estudiante.setNombres(rs.getString("nombres"));
                estudiante.setApellidos(rs.getString("apellidos"));
                estudiante.setLugarNac(rs.getString("lugarNac"));
                estudiante.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                estudiante.setCe(rs.getString("ceEstudiante"));
                estudiante.setEdad(rs.getInt("edad"));
                estudiante.setProcedencia(rs.getString("procedencia"));
                estudiante.setNacionalidad(rs.getString("nacionalidad"));
                estudiante.setTallaCamisa(rs.getInt("tallaCamisa"));
                estudiante.setTallaPantalon(rs.getInt("tallaPantalon"));
                estudiante.setTallaZapato(rs.getInt("tallaZapato"));
                estudiante.setPeso(rs.getInt("peso"));
                estudiante.setEstatura(rs.getInt("estatura"));
                estudiante.setPeriodoCurso(rs.getInt("periodoCurso"));
                estudiante.setPeriodoCursado(rs.getInt("periodoCursado"));
                estudiante.setLateralidad(rs.getBoolean("lateralidad"));
                estudiante.setGrupoSanguineo(rs.getString("grupoSanguineo"));
                estudiante.setAsegurado(rs.getBoolean("asegurado"));
                estudiante.setCualSeguro(rs.getString("cualSeguro"));
                estudiante.setMedicoTratante(rs.getString("medicoTratante"));
                estudiante.setTlfMedicoTratante(rs.getString("tlfMedicoTratante"));
                estudiante.setImg(rs.getBytes("img"));

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
        if (estudiante != null){ return estudiante; } else { return null;}
    }

    public void sendNomina(){}

    public void removerNomina(){}

    public void buscarNomina(){}

    public void fetchNomina(){}

    public void sendNominaMaestra(){}

    public void removerNominaMaestra(){}

    public void buscarNominaMaestra(){}

    public void fetchNominaMaestra(){}

    public void sendAutorizado(autorizado autorizado){

        sql = "INSERT INTO autorizadoRetiro (ciAutorizado, apellidos, nombre, tlf1, tlf2) VALUES (?,?,?,?,?)";

        try{
            conexion = DriverManager.getConnection(url, "root", "1234");
            System.out.println("Database connecion started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            query.setInt(1, autorizado.getCi()); // cirepresentante
            query.setString(2, autorizado.getApellidos()); // apellidos
            query.setString(3, autorizado.getNombres()); // nombres
            query.setString(13, autorizado.getTlf1()); // tlf
            query.setString(14, autorizado.getTlf2()); // tlf

        }catch(SQLException e){
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

    public void removerAutorizado(autorizado autorizado){

        sql = "DELETE FROM autorizadoRetiro WHERE ciAutorizado = ?";

        try{
            conexion = DriverManager.getConnection(url, "root", "1234");
            System.out.println("Database connecion started.");
            PreparedStatement query = conexion.prepareStatement(sql);
            
            query.setInt(1, autorizado.getCi()); // cirepresentante

        }catch(SQLException e){
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

    public void buscarAutorizado(autorizado autorizado){

        sql = "SELECT ciAutorizado FROM autorizadoRetiro WHERE ciAutorizado = ?";

        try{
            conexion = DriverManager.getConnection(url, "root", "1234");
            System.out.println("Database connecion started.");
            PreparedStatement query = conexion.prepareStatement(sql);
            
            query.setInt(1, autorizado.getCi()); // cirepresentante

        }catch(SQLException e){
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

    public void fetchAutorizado(autorizado autorizado){}

    public void sendDiagnostico(diagnostico diagnostico){
    
        sql = "INSERT INTO `mydb`.`diagnostico` (`estudiante_ciEstudiante`,"+
                "`problemaParto`, `problemaMotor`, `problemaLenguaje`,"+
                "`problemaCognitivo`, `alergiaMedicamento`, `condicionExtra`,"+
                "`cualParto`, `cualMotor`, `cualLenguaje`, `cualCognitivo`,"+
                "`cualAMedicamento`, `cualExtra`, `alergia`, `cualAlergia`,"+
                "`enfermedadesPadecidas`, `edadHablar`, `edadCaminar`,"+
                "`enfermedadEmbarazo`, `cualEnfEmbarazo`, `embarazoPlanif`,"+
                "`medicamentoFiebre`, `vacBCG`, `vacTRIPLE`, `vacPOLIO`, `vacTIFUS`,"+
                "`otroVacAplicadas`, `cualVacAplicada`, `consultaPsilg`,"+
                "`consultaPsipeg`, `consultaNeur`, `consultaLeng`, `consultaOtro`,"+
                "`especifiqueConsultaOtro`, `comeAyudado`, `buenApetito`,"+
                "`horaDormir`, `horaLevantarse`, `conQuienDuerme`, `chupaDedo`,"+
                "`edadDejarPañales`, `seOrinaDia`, `seOrinaNoche`, `evacuaDia`, `pideAydaAseo`)"+
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"+
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
   
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            // --- Asignación de parámetros ---
            query.setString(1, diagnostico.getCe()); // estudiante_ciEstudiante
            query.setBoolean(2, diagnostico.isProblemaParto()); // problemaParto
            query.setBoolean(3, diagnostico.isProblemaMotor()); // problemaMotor
            query.setBoolean(4, diagnostico.isProblemaLenguaje()); // problemaLenguaje
            query.setBoolean(5, diagnostico.isProblemaCognitivo()); // problemaC
            query.setBoolean(6, diagnostico.isAlergiaMedicamento()); // alergiaMedicamento
            query.setBoolean(7, diagnostico.isCondicionExtra()); // condicionExtra
            query.setString(8, diagnostico.getCualParto()); // cualParto
            query.setString(9, diagnostico.getCualMotor()); // cualMotor
            query.setString(10, diagnostico.getCualLenguaje()); // cualLenguaje
            query.setString(11, diagnostico.getCualCognitivo()); // cualCognitivo
            query.setString(12, diagnostico.getCualAMedicamento()); // cualAMedicamento
            query.setString(13, diagnostico.getCualExtra()); // cualExtra
            query.setBoolean(14, diagnostico.isAlergia()); // alergia
            query.setString(15, diagnostico.getCualAlergia()); // cualAlergia
            query.setString(16, diagnostico.getEnfermedadesPadecidas()); // enfermedadesPadecidas
            query.setInt(17, diagnostico.getEdadHablar()); // edadHablar
            query.setInt(18, diagnostico.getEdadCaminar()); // edadCaminar
            query.setBoolean(19, diagnostico.isEnfermedadEmbarazo()); // enfermedadEmbarazo
            query.setString(20, diagnostico.getCualEnfEmbarazo()); // cualEnfEmbarazo
            query.setBoolean(21, diagnostico.isEmbarazoPlanif()); // embarazoPlanif
            query.setString(22, diagnostico.getMedicamentoFiebre()); // medicamentoFiebre
            query.setBoolean(23, diagnostico.isVacBCG()); // vacBCG
            query.setBoolean(24, diagnostico.isVacTRIPLE()); // vacTRIPLE
            query.setBoolean(25, diagnostico.isVacPOLIO()); // vacPOLIO
            query.setBoolean(26, diagnostico.isVacTIFUS()); // vacTIF
            query.setBoolean(27, diagnostico.isOtroVacAplicadas()); // otroVacAplicadas
            query.setString(28, diagnostico.getCualVacAplicada()); // cualVacAplicada
            query.setBoolean(29, diagnostico.isConsultaPsilg()); // consultaPsilg
            query.setBoolean(30, diagnostico.isConsultaPsipeg()); // consultaPsipe
            query.setBoolean(31, diagnostico.isConsultaNeur()); // consultaNeur
            query.setBoolean(32, diagnostico.isConsultaLeng()); // consultaLeng
            query.setBoolean(33, diagnostico.isConsultaOtro()); // consultaOtro
            query.setString(34, diagnostico.getEspecifiqueConsultaOtro()); // especifiqueConsultaOtro
            query.setBoolean(35, diagnostico.isComeAyudado()); // comeAyudado
            query.setBoolean(36, diagnostico.isBuenApetito()); // buenApetito
            query.setTime(37, Time.valueOf(diagnostico.getHoraDormir())); // horaDormir
            query.setTime(38, Time.valueOf(diagnostico.getHoraLevantarse())); // horaLevantarse
            query.setString(39, diagnostico.getConQuienDuerme()); // conQuienDuerme
            query.setBoolean(40, diagnostico.isChupaDedo()); // chupaDedo
            query.setInt(41, diagnostico.getEdadDejarPanales()); // edadDejarPañales
            query.setBoolean(42, diagnostico.isSeOrinaDia()); // seOrinaDia
            query.setBoolean(43, diagnostico.isSeOrinaNoche()); // seOrinaNoche
            query.setBoolean(44, diagnostico.isEvacuaDia()); // evacuaDia
            query.setBoolean(45, diagnostico.isPideAyudaAseo()); // pideAydaAseo


            
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

    public void editarDiagnostico(){
        sql = "UPDATE diagnostico SET problemaParto = ?, problemaMotor = ?, problemaLenguaje = ?," +
                "problemaCognitivo = ?, alergiaMedicamento = ?, condicionExtra = ?," +
                "cualParto = ?, cualMotor = ?, cualLenguaje = ?, cualCognitivo = ?," +
                "cualAMedicamento = ?, cualExtra = ?, alergia = ?, cualAlergia = ?," +
                "enfermedadesPadecidas = ?, edadHablar = ?, edadCaminar = ?," +
                "enfermedadEmbarazo = ?, cualEnfEmbarazo = ?, embarazoPlanif = ?," +
                "medicamentoFiebre = ?, vacBCG = ?, vacTRIPLE = ?, vacPOLIO = ?," +
                "vacTIFUS = ?, otroVacAplicadas = ?, cualVacAplicada = ?," +
                "consultaPsilg = ?, consultaPsipeg = ?, consultaNeur = ?," +
                "consultaLeng = ?, consultaOtro = ?, especifiqueConsultaOtro = ?," +
                "comeAyudado = ?, buenApetito = ?, horaDormir = ?," +
                "horaLevantarse = ?, conQuienDuerme = ?, chupaDedo = ?," +
                "edadDejarPañales = ?, seOrinaDia = ?, seOrinaNoche = ?," +
                "evacuaDia = ?, pideAydaAseo= ? WHERE estudiante_ciEstudiante= ?";

        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");

        }
        catch(SQLException e){
            System.err.println("Cannot connect to the database!");
            e.printStackTrace();
        }
    }

    public void removerDiagnostico(){}

    public void setRelRepresentado(){}

    public void setRelAutorizado(){}

    public void crearUsuario(){}

    public void removerUsuario(){}

    public void buscarUsuario(acceso acceso){
        
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

    public void aumentarNivelEstd(){}


    //Main
    public void main(String args[]) throws SQLException{

        //connparamsinit();
        //sendRepresentante();

    }
}

