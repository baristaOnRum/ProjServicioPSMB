package subsystems;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    public static void connparamsinit(acceso acceso) {
        url = acceso.getConnURL();
        user = acceso.getUserDB();
        pass = acceso.getPassDB();
        conexion = null;
    }

    //Definición de funciones de envío de datos

    public static void sendRepresentante(representante representante) {

        sql = "INSERT INTO representante " +
                "(ciRepresentante, apellidos, nombres, estdCiv, lugarNac, fechaNac," +
                "nacionalidad, edad, direccionHab, gradoEstudios, ocupacion, direccionTrabj," +
                "tlf1, tlf2, tlfTrabajo, tlfCasa, correo, menores6, img)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conexion = DriverManager.getConnection(url, user, pass);
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
        } catch (SQLException e) {
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

    public static void removerRepresentante(int ci) {
        sql = "DELETE FROM representante" +
                " WHERE ciRepresentante = ?;";
        try {
            conexion = DriverManager.getConnection(url, user, pass);
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
        } catch (SQLException e) {
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

    public static List<representante> buscarRepresentante(String criterio, String busquedaQuery) {
        List<representante> representantes = new ArrayList<>();
        if (criterio == null) {
            sql = "SELECT * FROM representante";
            System.out.println("INSIDE IF");
            try {
                conexion = DriverManager.getConnection(url, user, pass);
                System.out.println("Database connection started.");
                PreparedStatement query = conexion.prepareStatement(sql);

                System.out.println("INSIDE IF");
                ResultSet rs = query.executeQuery();

                System.out.println("INSIDE IF");
                while (true) {
                    try {
                        rs.next();
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
                        System.out.println(representante.getCi());
                    } catch (SQLException e) {
                        break;
                    }
                }
                return representantes;
            } catch (SQLException e) {
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
        } else {

            sql = "SELECT " +
                    "nombres, apellidos, lugarNac, fechaNac, " +
                    "ciRepresentante, edad, menores6, estdCiv, nacionalidad, " +
                    "direccionHab, direccionTrabj, ocupacion, gradoEstudios, " +
                    "tlf1, tlf2, tlfTrabajo, tlfCasa, correo, img " +
                    "FROM representante WHERE " + criterio + " = \"" + busquedaQuery + "\"";
            try {
                conexion = DriverManager.getConnection(url, user, pass);
                System.out.println("Database connection started.");
                PreparedStatement query = conexion.prepareStatement(sql);

                ResultSet rs = query.executeQuery();

                while (rs.next()) {
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

                    System.out.println(representante.getCi());
                }
                return representantes;
            } catch (SQLException e) {
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
            conexion = DriverManager.getConnection(url, user, pass);
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

        } catch (SQLException e) {
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
        if (representante != null) {
            return representante;
        } else {
            return null;
        }
    }

    public static void updateRepresentante(representante representante) {
        sql = " UPDATE representante SET apellidos = ?," +
                "nombres = ?, estdCiv = ?, lugarNac = ?, fechaNac = ?," +
                "nacionalidad = ?, edad = ?, direccionHab = ?, gradoEstudios = ?," +
                "ocupacion = ?, direccionTrabj = ?, tlf1 = ?, tlf2 = ?," +
                "tlfTrabajo = ?, tlfCasa = ?, correo = ?, menores6 = ?," +
                "img = ? WHERE ciRepresentante = ?;"; // VALUES ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"

        try {
            conexion = DriverManager.getConnection(url, user, pass);
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
            query.setInt(19, representante.getCi());

            // --- Ejecución de la consulta ---
            int columnasAfectadas = query.executeUpdate();
            System.out.print(columnasAfectadas);

        } catch (SQLException e) {
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

    public static void sendEstudiante(estudiante estudiante) {

        String sql = "INSERT INTO estudiante (" +
                "ciEstudiante, apellidos, nombres, fechaNac, lugarNac, nacionalidad, edad, " +
                "procedencia, tallaCam, tallaPant, tallaZap, peso, estatura, nivel, " +
                "turno, periodioCurso, periodoCursado, img, lateralidad, grupoSanguineo, " +
                "asegurado, cualSeguro, medicoTratante, tlfMedicoTratante" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url,user,pass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set parameters for the prepared statement
            pstmt.setString(1, estudiante.getCe()); // ciEstudiante
            pstmt.setString(2, estudiante.getApellidos()); // apellidos (from personas superclass)
            pstmt.setString(3, estudiante.getNombres()); // nombres (from personas superclass)
            pstmt.setDate(4, Date.valueOf(estudiante.getFechaNac())); // fechaNac (from personas superclass, converted to java.sql.Date)
            pstmt.setString(5, estudiante.getLugarNac()); // lugarNac (from personas superclass)
            pstmt.setString(6, estudiante.getNacionalidad()); // nacionalidad
            pstmt.setInt(7, estudiante.getEdad()); // edad
            pstmt.setString(8, estudiante.getProcedencia()); // procedencia
            pstmt.setInt(9, estudiante.getTallaCamisa()); // tallaCam
            pstmt.setInt(10, estudiante.getTallaPantalon()); // tallaPant
            pstmt.setInt(11, estudiante.getTallaZapato()); // tallaZap
            pstmt.setInt(12, estudiante.getPeso()); // peso
            pstmt.setInt(13, estudiante.getEstatura()); // estatura
            pstmt.setInt(14, estudiante.getNivel()); // nivel
            // pstmt.setString(15, estudiante.getSeccion()); // seccion - OMITTED as not in class
            pstmt.setBoolean(15, estudiante.getTurno() == 1); // turno (byte to boolean, assuming 1 is true, 0 is false)
            pstmt.setInt(16, estudiante.getPeriodoCurso()); // periodioCurso
            pstmt.setInt(17, estudiante.getPeriodoCursado()); // periodoCursado

            // Handle image (byte array)
            if (estudiante.getImg() != null) {
                pstmt.setBytes(18, estudiante.getImg()); // img
            } else {
                pstmt.setNull(18, java.sql.Types.BLOB); // Set as NULL if no image
            }

            pstmt.setBoolean(19, estudiante.isLateralidad()); // lateralidad
            pstmt.setString(20, estudiante.getGrupoSanguineo()); // grupoSanguineo
            pstmt.setBoolean(21, estudiante.isAsegurado()); // asegurado
            pstmt.setString(22, estudiante.getCualSeguro()); // cualSeguro
            pstmt.setString(23, estudiante.getMedicoTratante()); // medicoTratante
            pstmt.setString(24, estudiante.getTlfMedicoTratante()); // tlfMedicoTratante

            // Execute the update (insert)
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully into estudiante for CI: " + estudiante.getCe());
            } else {
                System.out.println("No rows affected. Data insertion failed for estudiante for CI: " + estudiante.getCe());
            }
        } catch (SQLException e) {
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

public static void updateEstudiante(estudiante estudiante) throws SQLException {
    // SQL UPDATE statement with placeholders for all columns to be updated
    // The WHERE clause uses ciEstudiante to identify the specific record.
    String sql = "UPDATE `mydb`.`estudiante` SET " +
            "`apellidos` = ?, " +
            "`nombres` = ?, " +
            "`fechaNac` = ?, " +
            "`lugarNac` = ?, " +
            "`nacionalidad` = ?, " +
            "`edad` = ?, " +
            "`procedencia` = ?, " +
            "`tallaCam` = ?, " +
            "`tallaPant` = ?, " +
            "`tallaZap` = ?, " +
            "`peso` = ?, " +
            "`estatura` = ?, " +
            "`periodioCurso` = ?, " +
            "`periodoCursado` = ?, " +
            "`img` = ?, " +
            "`lateralidad` = ?, " +
            "`grupoSanguineo` = ?, " +
            "`asegurado` = ?, " +
            "`cualSeguro` = ?, " +
            "`medicoTratante` = ?, " +
            "`tlfMedicoTratante` = ? " +
            "WHERE `ciEstudiante` = ?"; // Primary key in WHERE clause

    // Use try-with-resources to ensure Connection and PreparedStatement are closed
    try (Connection conexion = DriverManager.getConnection(url, user, pass);
         PreparedStatement query = conexion.prepareStatement(sql)) {

        System.out.println("Conexión a la base de datos iniciada.");

        // Set values for each placeholder in the UPDATE statement
        // The order here must match the order of columns in the SET clause.
        int paramIndex = 1;
        query.setString(paramIndex++, estudiante.getApellidos());
        query.setString(paramIndex++, estudiante.getNombres());
        query.setDate(paramIndex++, Date.valueOf(estudiante.getFechaNac())); // Convert LocalDate to java.sql.Date
        query.setString(paramIndex++, estudiante.getLugarNac());
        query.setString(paramIndex++, estudiante.getNacionalidad());
        query.setInt(paramIndex++, estudiante.getEdad());
        query.setString(paramIndex++, estudiante.getProcedencia());
        query.setInt(paramIndex++, estudiante.getTallaCamisa());
        query.setInt(paramIndex++, estudiante.getTallaPantalon());
        query.setInt(paramIndex++, estudiante.getTallaZapato());
        query.setDouble(paramIndex++, estudiante.getPeso());
        query.setDouble(paramIndex++, estudiante.getEstatura());
        query.setInt(paramIndex++, estudiante.getPeriodoCurso());
        query.setInt(paramIndex++, estudiante.getPeriodoCursado());

        if (estudiante.getImg() != null) {
            query.setBytes(paramIndex++, estudiante.getImg());
        } else {
            System.err.println("Imagen no existente, se establecerá como NULL.");
            query.setNull(paramIndex++, java.sql.Types.BLOB); // Set as NULL if image is not provided
        }

        query.setBoolean(paramIndex++, estudiante.isLateralidad());
        query.setString(paramIndex++, estudiante.getGrupoSanguineo());
        query.setBoolean(paramIndex++, estudiante.isAsegurado());
        query.setString(paramIndex++, estudiante.getCualSeguro());
        query.setString(paramIndex++, estudiante.getMedicoTratante());
        query.setString(paramIndex++, estudiante.getTlfMedicoTratante());

        // Set the value for the WHERE clause (ciEstudiante)
        query.setString(paramIndex++, estudiante.getCe());

        // Execute the update query
        int filasAfectadas = query.executeUpdate();

        // Check the result
        if (filasAfectadas > 0) {
            System.out.println("¡Registro de estudiante actualizado exitosamente para CI: " + estudiante.getCe() + "!");
        } else {
            System.out.println("La actualización del registro de estudiante ha fallado o el CI no existe: " + estudiante.getCe() + ".");
        }

    } catch (SQLException e) {
        System.err.println("Error de base de datos al actualizar estudiante: " + e.getMessage());
        throw e; // Re-throw the exception for higher-level handling
    } finally {
        // Connection and PreparedStatement are automatically closed by try-with-resources
        System.out.println("Conexión a la base de datos cerrada.");
    }
}

    public static void removerEstudiante(String ce) {

        sql = "DELETE FROM estudiante" +
                " WHERE ciEstudiante = ?;";
        try {
            conexion = DriverManager.getConnection(url, user, pass);
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
        } catch (SQLException e) {
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

    public static List<estudiante> buscarEstudiante(String criterio, String busquedaQuery) {
        List<estudiante> estudiantes = new ArrayList<>();
        if (criterio == null) {
            sql = "SELECT * FROM estudiante";
            System.out.println("INSIDE ELSE");
            try {
                conexion = DriverManager.getConnection(url, user, pass);
                System.out.println("Database connection started.");
                PreparedStatement query = conexion.prepareStatement(sql);

                System.out.println("INSIDE ELSE");
                ResultSet rs = query.executeQuery();

                System.out.println("INSIDE ELSE");
                while (true) {
                    try {
                        rs.next();
                        estudiante estudiante = new estudiante();
                        estudiante.setNombres(rs.getString("nombres"));
                        estudiante.setApellidos(rs.getString("apellidos"));
                        estudiante.setLugarNac(rs.getString("lugarNac"));
                        estudiante.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                        estudiante.setCe(rs.getString("ciEstudiante"));
                        estudiante.setNacionalidad(rs.getString("nacionalidad"));
                        estudiante.setProcedencia(rs.getString("procedencia"));
                        estudiante.setGrupoSanguineo(rs.getString("grupoSanguineo"));
                        estudiante.setCualSeguro(rs.getString("cualSeguro"));
                        estudiante.setMedicoTratante(rs.getString("medicoTratante"));
                        estudiante.setTlfMedicoTratante(rs.getString("tlfMedicoTratante"));
                        estudiante.setTallaZapato(rs.getInt("tallaZap"));
                        estudiante.setTallaCamisa(rs.getInt("tallaCam"));
                        estudiante.setTallaPantalon(rs.getInt("tallaPant"));
                        estudiante.setNivel(rs.getInt("nivel"));
                        estudiante.setEdad(rs.getInt("edad"));
                        estudiante.setEstatura(rs.getInt("estatura"));
                        estudiante.setPeso(rs.getInt("peso"));
                        estudiante.setPeriodoCurso(rs.getInt("periodioCurso"));
                        estudiante.setPeriodoCursado(rs.getInt("periodoCursado"));
                        estudiante.setLateralidad(rs.getBoolean("lateralidad"));
                        estudiante.setAsegurado(rs.getBoolean("asegurado"));
                        estudiante.setTurno(rs.getByte("turno"));
                        estudiante.setImg(rs.getBytes("img"));
                        estudiantes.add(estudiante);
                        System.out.println(estudiante.getCe());
                    } catch (SQLException e) {
                        break;
                    }
                }
                return estudiantes;
            } catch (SQLException e) {
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
        } else {

            sql = "SELECT " +
                    "nombres, apellidos, lugarNac, fechaNac, " +
                    "ciestudiante, edad, menores6, estdCiv, nacionalidad, " +
                    "direccionHab, direccionTrabj, ocupacion, gradoEstudios, nivel," +
                    "tlf1, tlf2, tlfTrabajo, tlfCasa, correo, img, periodioCurso " +
                    "FROM estudiante WHERE " + criterio + " = \'" + busquedaQuery + "\'";
            try {
                conexion = DriverManager.getConnection(url, user, pass);
                System.out.println("Database connection started.");
                PreparedStatement query = conexion.prepareStatement(sql);

                ResultSet rs = query.executeQuery();

                while (rs.next()) {
                    estudiante estudiante = new estudiante();
                    estudiante.setNombres(rs.getString("nombres"));
                    estudiante.setApellidos(rs.getString("apellidos"));
                    estudiante.setLugarNac(rs.getString("lugarNac"));
                    estudiante.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                    estudiante.setCe(rs.getString("ciEstudiante"));
                    estudiante.setNacionalidad(rs.getString("nacionalidad"));
                    estudiante.setProcedencia(rs.getString("procedencia"));
                    estudiante.setGrupoSanguineo(rs.getString("grupoSanguineo"));
                    estudiante.setCualSeguro(rs.getString("cualSeguro"));
                    estudiante.setMedicoTratante(rs.getString("medicoTratante"));
                    estudiante.setTlfMedicoTratante(rs.getString("tlfMedicoTratante"));
                    estudiante.setTallaZapato(rs.getInt("tallaZap"));
                    estudiante.setTallaCamisa(rs.getInt("tallaCam"));
                    estudiante.setTallaPantalon(rs.getInt("tallaPant"));
                    estudiante.setNivel(rs.getInt("nivel"));
                    estudiante.setEdad(rs.getInt("edad"));
                    estudiante.setEstatura(rs.getInt("estatura"));
                    estudiante.setPeso(rs.getInt("peso"));
                    estudiante.setPeriodoCurso(rs.getInt("periodioCurso"));
                    estudiante.setPeriodoCursado(rs.getInt("periodoCursado"));
                    estudiante.setLateralidad(rs.getBoolean("lateralidad"));
                    estudiante.setAsegurado(rs.getBoolean("asegurado"));
                    estudiante.setTurno(rs.getByte("turno"));
                    estudiante.setImg(rs.getBytes("img"));
                    estudiantes.add(estudiante);

                    System.out.println(estudiante.getCe());
                }
                return estudiantes;
            } catch (SQLException e) {
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
        return estudiantes;
    }

    public static estudiante fetchEstudiante(String ce) {

        sql = "SELECT * FROM estudiante WHERE ciEstudiante = \"" + ce + "\"";

        estudiante estudiante = new estudiante();

        try {
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                estudiante.setNombres(rs.getString("nombres"));
                estudiante.setApellidos(rs.getString("apellidos"));
                estudiante.setLugarNac(rs.getString("lugarNac"));
                estudiante.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                estudiante.setCe(rs.getString("ciEstudiante"));
                estudiante.setEdad(rs.getInt("edad"));
                estudiante.setNivel(rs.getInt("nivel"));
                estudiante.setProcedencia(rs.getString("procedencia"));
                estudiante.setNacionalidad(rs.getString("nacionalidad"));
                estudiante.setTallaCamisa(rs.getInt("tallaCam"));
                estudiante.setTallaPantalon(rs.getInt("tallaPant"));
                estudiante.setTallaZapato(rs.getInt("tallaZap"));
                estudiante.setPeso(rs.getInt("peso"));
                estudiante.setEstatura(rs.getInt("estatura"));
                estudiante.setPeriodoCurso(rs.getInt("periodioCurso"));
                estudiante.setPeriodoCursado(rs.getInt("periodoCursado"));
                estudiante.setLateralidad(rs.getBoolean("lateralidad"));
                estudiante.setGrupoSanguineo(rs.getString("grupoSanguineo"));
                estudiante.setAsegurado(rs.getBoolean("asegurado"));
                estudiante.setCualSeguro(rs.getString("cualSeguro"));
                estudiante.setMedicoTratante(rs.getString("medicoTratante"));
                estudiante.setTlfMedicoTratante(rs.getString("tlfMedicoTratante"));
                estudiante.setImg(rs.getBytes("img"));

            }

        } catch (SQLException e) {
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
        if (estudiante.getCe() != null) {
            return estudiante;
        } else {
            return null;
        }
    }

    public static void sendNomina(trabajador trabajador) {

        sql = "INSERT INTO `mydb`.`trabajadores` (" +
                "`ci_trabajador`, `rif`, `titulo`, `nombres`, `apellidos`, " +
                "`cargo`, `fechaNac`, `fechaIngreso`, `direccionCobro`, `estatus`, `grado`, `sexo`, `tlf1`, `tlf2`, " +
                "`munElec`, `parrqElec`, `centVot`, `munRes`, `parrqRes`, `comRes`, `calleRes`, `nombreJefeCLAP`, `ciJefeCLAP`, " +
                "`tiene1x10`, `cantPersonas1x10`, `voto`, `observaciones`, `ciCopia`, `rifCopia`, `tituloCopia`, `turno`, `img`" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            query.setInt(1, trabajador.getCi()); // ci_maestra
            query.setInt(2, trabajador.getRif()); // rif
            query.setString(3, trabajador.getTitulo()); // titulo
            query.setString(4, trabajador.getNombres()); // nombres
            query.setString(5, trabajador.getApellidos()); // apellidos
            query.setString(6, trabajador.getCargo()); // cargo
            query.setDate(7, Date.valueOf(trabajador.getFechaNac())); // fecha_nac (formato YYYY-MM-DD es estándar)
            query.setDate(8, Date.valueOf(trabajador.getFechaIngreso())); // fecha_ingreso
            query.setString(9, trabajador.getDireccionCobro()); // direccion_cobro
            query.setBoolean(10, trabajador.isEstatus()); // estatus
            query.setString(11, trabajador.getGrado()); // grado
            query.setBoolean(12, trabajador.isSexo()); // sexo
            query.setString(13, trabajador.getTlf1()); // tlf1
            query.setString(14, trabajador.getTlf2()); // tlf2
            query.setString(15, trabajador.getMunElec()); // mun_elec
            query.setString(16, trabajador.getParrqElec()); // parrq_elec
            query.setString(17, trabajador.getCenVot()); // cent_vot
            query.setString(18, trabajador.getMunRes()); // mun_res
            query.setString(19, trabajador.getParrqRes()); // parrq_res
            query.setString(20, trabajador.getComRes()); // com_res
            query.setString(21, trabajador.getCalleRes()); // calle_res
            query.setString(22, trabajador.getNombreJefeClap()); // nombre_jefe_clap
            query.setInt(23, trabajador.getCiJefeClap()); // ci_jefe_clap
            query.setBoolean(24, trabajador.isTiene1x10()); // tiene_1x10
            query.setInt(25, trabajador.getCantPers1x10()); // cant_personas_1x10
            query.setBoolean(26, trabajador.isHaVotado()); // voto
            query.setString(27, trabajador.getObservaciones()); // observaciones
            if (trabajador.getCiCopia() != null) {
                query.setBytes(32, trabajador.getCiCopia());
            } else {
                System.err.println("Imagen no existente");
                query.setNull(32, java.sql.Types.BLOB); // Set as NULL if file not found
            }
            if (trabajador.getRifCopia() != null) {
                query.setBytes(33, trabajador.getRifCopia());
            } else {
                System.err.println("Imagen no existente");
                query.setNull(33, java.sql.Types.BLOB); // Set as NULL if file not found
            }
            if (trabajador.getTituloCopia() != null) {
                query.setBytes(34, trabajador.getTituloCopia());
            } else {
                System.err.println("Imagen no existente");
                query.setNull(34, java.sql.Types.BLOB); // Set as NULL if file not found
            }
            query.setBoolean(35, trabajador.isTurno()); // turno
            if (trabajador.getImg() != null) {
                query.setBytes(36, trabajador.getImg());
            } else {
                System.err.println("Imagen no existente");
                query.setNull(36, java.sql.Types.BLOB); // Set as NULL if file not found
            }

            // --- Ejecución de la consulta ---
            int columnasAfectadas = query.executeUpdate();

            // --- Verificación del resultado ---
            if (columnasAfectadas > 0) {
                System.out.println("¡Fila insertada exitosamente!");
            } else {
                System.out.println("La inserción de la fila ha fallado.");
            }
        } catch (SQLException e) {
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

    public static void removerNomina(int ci) {

        sql = "DELETE FROM trabajador" +
                " WHERE ci_trabajadores = ?;";
        try {
            conexion = DriverManager.getConnection(url, user, pass);
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
        } catch (SQLException e) {
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

    public static List<trabajador> buscarNomina(String criterio, String busquedaQuery) {
        List<trabajador> trabajadores = new ArrayList<>();
        if (criterio == null) {
            sql = "SELECT * FROM trabajadores";
            System.out.println("INSIDE ELSE");
            try {
                conexion = DriverManager.getConnection(url, user, pass);
                System.out.println("Database connection started.");
                PreparedStatement query = conexion.prepareStatement(sql);

                System.out.println("INSIDE ELSE");
                ResultSet rs = query.executeQuery();

                System.out.println("INSIDE ELSE");
                while (true) {
                    try {
                        rs.next();
                        trabajador trabajador = new trabajador();
                        trabajador.setCi(rs.getInt("ci_trabajador"));
                        trabajador.setRif(rs.getInt("rif"));
                        trabajador.setTitulo(rs.getString("titulo"));
                        trabajador.setNombres(rs.getString("nombres"));
                        trabajador.setApellidos(rs.getString("apellidos"));
                        trabajador.setCargo(rs.getString("cargo"));
                        trabajador.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                        trabajador.setFechaIngreso(rs.getDate("fechaIngreso").toLocalDate());
                        trabajador.setDireccionCobro(rs.getString("direccionCobro"));
                        trabajador.setEstatus(rs.getBoolean("estatus"));
                        trabajador.setGrado(rs.getString("grado"));
                        trabajador.setSexo(rs.getBoolean("sexo"));
                        trabajador.setTlf1(rs.getString("tlf1"));
                        trabajador.setTlf2(rs.getString("tlf2"));
                        trabajador.setMunElec(rs.getString("munElec"));
                        trabajador.setParrqElec(rs.getString("parrqElec"));
                        trabajador.setCenVot(rs.getString("centVot"));
                        trabajador.setMunRes(rs.getString("munRes"));
                        trabajador.setParrqRes(rs.getString("parrqRes"));
                        trabajador.setComRes(rs.getString("comRes"));
                        trabajador.setCalleRes(rs.getString("calleRes"));
                        trabajador.setNombreJefeClap(rs.getString("nombreJefeCLAP"));
                        trabajador.setCiJefeClap(rs.getInt("ciJefeCLAP"));
                        trabajador.setTiene1x10(rs.getBoolean("tiene1x10"));
                        trabajador.setCantPers1x10(rs.getInt("cantPersonas1x10"));
                        trabajador.setHaVotado(rs.getBoolean("voto"));
                        trabajador.setObservaciones(rs.getString("observaciones"));

                        if (rs.getBytes("ciCopia") != null) {
                            trabajador.setCiCopia(rs.getBytes("ciCopia"));
                        } else {
                            System.err.println("Imagen no existente");
                            trabajador.setCiCopia(null); // Set as NULL if file not found
                        }
                        if (rs.getBytes("rifCopia") != null) {
                            trabajador.setRifCopia(rs.getBytes("rifCopia"));
                        } else {
                            System.err.println("Imagen no existente");
                            trabajador.setRifCopia(null); // Set as NULL if file not found
                        }
                        if (rs.getBytes("tituloCopia") != null) {
                            trabajador.setTituloCopia(rs.getBytes("tituloCopia"));
                        } else {
                            System.err.println("Imagen no existente");
                            trabajador.setTituloCopia(null); // Set as NULL if file not found
                        }
                        trabajador.setTurno(rs.getBoolean("turno"));
                        if (rs.getBytes("img") != null) {
                            trabajador.setImg(rs.getBytes("img"));
                        } else {
                            System.err.println("Imagen no existente");
                            trabajador.setImg(null); // Set as NULL if file not found
                        }
                        trabajadores.add(trabajador);
                        System.out.println(trabajador.getCi());
                    } catch (SQLException e) {
                        break;
                    }
                }
                return trabajadores;
            } catch (SQLException e) {
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
        } else {
            sql = "SELECT " +
                    "ci_trabajador, rif, titulo, nombres, apellidos, cargo, fechaNac, fechaIngreso," +
                    "direccionCobro, estatus, grado, sexo, tlf1, tlf2," +
                    "munElec, parrqElec, centVot, munRes, parrqRes," +
                    "comRes, calleRes, nombreJefeCLAP, ciJefeCLAP," +
                    "tiene1x10, cantPersonas1x10, voto, observaciones," +
                    "ciCopia, rifCopia, tituloCopia, turno" +
                    " FROM trabajadores WHERE " + criterio + " = \'" + busquedaQuery + "\'";
            try {
                conexion = DriverManager.getConnection(url, user, pass);
                System.out.println("Database connection started.");
                PreparedStatement query = conexion.prepareStatement(sql);

                ResultSet rs = query.executeQuery();

                while (rs.next()) {
                    trabajador trabajador = new trabajador();
                    trabajador.setCi(rs.getInt("ci_trabajador"));
                    trabajador.setRif(rs.getInt("rif"));
                    trabajador.setTitulo(rs.getString("titulo"));
                    trabajador.setNombres(rs.getString("nombres"));
                    trabajador.setApellidos(rs.getString("apellidos"));
                    trabajador.setCargo(rs.getString("cargo"));
                    trabajador.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                    trabajador.setFechaIngreso(rs.getDate("fechaIngreso").toLocalDate());
                    trabajador.setDireccionCobro(rs.getString("direccionCobro"));
                    trabajador.setEstatus(rs.getBoolean("estatus"));
                    trabajador.setGrado(rs.getString("grado"));
                    trabajador.setSexo(rs.getBoolean("sexo"));
                    trabajador.setTlf1(rs.getString("tlf1"));
                    trabajador.setTlf2(rs.getString("tlf2"));
                    trabajador.setMunElec(rs.getString("munElec"));
                    trabajador.setParrqElec(rs.getString("parrqElec"));
                    trabajador.setCenVot(rs.getString("centVot"));
                    trabajador.setMunRes(rs.getString("munRes"));
                    trabajador.setParrqRes(rs.getString("parrqRes"));
                    trabajador.setComRes(rs.getString("comRes"));
                    trabajador.setCalleRes(rs.getString("calleRes"));
                    trabajador.setNombreJefeClap(rs.getString("nombreJefeCLAP"));
                    trabajador.setCiJefeClap(rs.getInt("ciJefeCLAP"));
                    trabajador.setTiene1x10(rs.getBoolean("tiene1x10"));
                    trabajador.setCantPers1x10(rs.getInt("cantPersonas1x10"));
                    trabajador.setHaVotado(rs.getBoolean("voto"));
                    trabajador.setObservaciones(rs.getString("observaciones"));
                    if (rs.getBytes("ciCopia") != null) {
                        trabajador.setCiCopia(rs.getBytes("ciCopia"));
                    } else {
                        System.err.println("Imagen no existente");
                        trabajador.setCiCopia(null); // Set as NULL if file not found
                    }
                    if (rs.getBytes("rifCopia") != null) {
                        trabajador.setRifCopia(rs.getBytes("rifCopia"));
                    } else {
                        System.err.println("Imagen no existente");
                        trabajador.setRifCopia(null); // Set as NULL if file not found
                    }
                    if (rs.getBytes("tituloCopia") != null) {
                        trabajador.setTituloCopia(rs.getBytes("tituloCopia"));
                    } else {
                        System.err.println("Imagen no existente");
                        trabajador.setTituloCopia(null); // Set as NULL if file not found
                    }
                    trabajador.setTurno(rs.getBoolean("turno"));
                    if (rs.getBytes("img") != null) {
                        trabajador.setImg(rs.getBytes("img"));
                    } else {
                        System.err.println("Imagen no existente");
                        trabajador.setImg(null); // Set as NULL if file not found
                    }
                    trabajadores.add(trabajador);
                    System.out.println(trabajador.getCi());
                }
                return trabajadores;
            } catch (SQLException e) {
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
        return trabajadores;
    }

    public trabajador fetchNomina(int ci) {
        sql = "SELECT " +
                "ci_trabajador, rif, titulo, nombres, apellidos, cargo, fechaNac, fechaIngreso," +
                "direccionCobro, estatus, grado, sexo, tlf1, tlf2," +
                "munElec, parrqElec, centVot, munRes, parrqRes, comRes, calleRes, nombreJefeCLAP, ciJefeCLAP," +
                "tiene1x10, cantPersonas1x10, voto, observaciones," +
                "ciCopia, rifCopia, tituloCopia, turno, img " +
                "FROM trabajadores WHERE ci_trabajador =" + ci;
        trabajador trabajador = new trabajador();

        try {
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            ResultSet rs = query.executeQuery();

            if (rs.next()) {
                trabajador.setCi(rs.getInt("ci_trabajador"));
                trabajador.setRif(rs.getInt("rif"));
                trabajador.setTitulo(rs.getString("titulo"));
                trabajador.setNombres(rs.getString("nombres"));
                trabajador.setApellidos(rs.getString("apellidos"));
                trabajador.setCargo(rs.getString("cargo"));
                trabajador.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                trabajador.setFechaIngreso(rs.getDate("fechaIngreso").toLocalDate());
                trabajador.setDireccionCobro(rs.getString("direccionCobro"));
                trabajador.setEstatus(rs.getBoolean("estatus"));
                trabajador.setGrado(rs.getString("grado"));
                trabajador.setSexo(rs.getBoolean("sexo"));
                trabajador.setTlf1(rs.getString("tlf1"));
                trabajador.setTlf2(rs.getString("tlf2"));
                trabajador.setMunElec(rs.getString("munElec"));
                trabajador.setParrqElec(rs.getString("parrqElec"));
                trabajador.setCenVot(rs.getString("centVot"));
                trabajador.setMunRes(rs.getString("munRes"));
                trabajador.setParrqRes(rs.getString("parrqRes"));
                trabajador.setComRes(rs.getString("comRes"));
                trabajador.setCalleRes(rs.getString("calleRes"));
                trabajador.setNombreJefeClap(rs.getString("nombreJefeCLAP"));
                trabajador.setCiJefeClap(rs.getInt("ciJefeCLAP"));
                trabajador.setTiene1x10(rs.getBoolean("tiene1x10"));
                trabajador.setCantPers1x10(rs.getInt("cantPersonas1x10"));
                trabajador.setHaVotado(rs.getBoolean("voto"));
                trabajador.setObservaciones(rs.getString("observaciones"));
                trabajador.setCiCopia(rs.getBytes("ciCopia"));
                trabajador.setRifCopia(rs.getBytes("rifCopia"));
                trabajador.setTituloCopia(rs.getBytes("tituloCopia"));
                trabajador.setTurno(rs.getBoolean("turno"));
                trabajador.setImg(rs.getBytes("img"));
            }

        } catch (SQLException e) {
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
        return trabajador;
    }

    public static void sendNominaMaestra(maestro maestro) {
        sql = "INSERT INTO `mydb`.`maestras` (" +
                "`ci_maestra`, `rif`, `titulo`, `nombres`, `apellidos`, `cargo`, `fechaNac`, `fechaIngreso`, `direccionCobro`, `estatus`, `grado`, `sexo`, `tlf1`, `tlf2`, " +
                "`munElec`, `parrqElec`, `centVot`, `munRes`, `parrqRes`, `comRes`, `calleRes`, `nombreJefeCLAP`, `ciJefeCLAP`, `tiene1x10`, `cantPersonas1x10`, " +
                "`voto`, `observaciones`, `ciCopia`, `rifCopia`, `tituloCopia`, `turno`, `img`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            query.setInt(1, maestro.getCi());
            query.setInt(2, maestro.getRif());
            query.setString(3, maestro.getTitulo());
            query.setString(4, maestro.getNombres());
            query.setString(5, maestro.getApellidos());
            query.setString(6, maestro.getTitulo());
            query.setDate(7, Date.valueOf(maestro.getFechaNac()));
            query.setDate(8, Date.valueOf(maestro.getFechaIngreso()));
            query.setString(9, maestro.getDireccionCobro());
            query.setBoolean(10, maestro.isEstatus());
            query.setBoolean(12, maestro.isSexo());
            query.setString(13, maestro.getTlf1());
            query.setString(14, maestro.getTlf2());
            query.setString(15, maestro.getMunElec());
            query.setString(16, maestro.getParrqElec());
            query.setString(17, maestro.getCenVot());
            query.setString(18, maestro.getMunRes());
            query.setString(19, maestro.getParrqRes());
            query.setString(20, maestro.getComRes());
            query.setString(21, maestro.getCalleRes());
            query.setString(22, maestro.getNombreJefeClap());
            query.setInt(23, maestro.getCiJefeClap());
            query.setBoolean(24, maestro.isTiene1x10());
            query.setInt(25, maestro.getCantPers1x10());
            query.setBoolean(26, maestro.isHaVotado());
            query.setString(27, maestro.getObservaciones());
            if (maestro.getCiCopia() != null) {
                query.setBytes(28, maestro.getCiCopia());
            } else {
                query.setNull(28, java.sql.Types.BLOB);
            }
            if (maestro.getRifCopia() != null) {
                query.setBytes(29, maestro.getRifCopia());
            } else {
                query.setNull(29, java.sql.Types.BLOB);
            }
            if (maestro.getTituloCopia() != null) {
                query.setBytes(30, maestro.getTituloCopia());
            } else {
                query.setNull(30, java.sql.Types.BLOB);
            }

            query.setBoolean(31, maestro.isTurno());

            if (maestro.getImg() != null) {
                query.setBytes(32, maestro.getImg());
            } else {
                query.setNull(32, java.sql.Types.BLOB);
            }

            int columnasAfectadas = query.executeUpdate();

            if (columnasAfectadas > 0) {
                System.out.println("¡Fila insertada exitosamente en trabajadores_maestra!");
            } else {
                System.out.println("La inserción de la fila ha fallado en trabajadores_maestra.");
            }
        } catch (SQLException e) {
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

    public static void removerNominaMaestra(int ci) {
        sql = "DELETE FROM maestras WHERE ci_maestra = ?;";
        try {
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);
            query.setInt(1, ci);

            // --- Ejecución de la consulta ---
            int columnasAfectadas = query.executeUpdate();

            // --- Verificación del resultado ---
            if (columnasAfectadas > 0) {
                System.out.println("¡Fila eliminada exitosamente!");
            } else {
                System.out.println("La eliminación de la fila ha fallado.");
            }
        } catch (SQLException e) {
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

    public static List<maestro> buscarNominaMaestra(String criterio, String busquedaQuery) {
        List<maestro> maestras = new ArrayList<>();
        if (busquedaQuery == null) {
            sql = "SELECT * FROM maestras";
            try {
                conexion = DriverManager.getConnection(url, user, pass);
                System.out.println("Database connection started.");
                PreparedStatement query = conexion.prepareStatement(sql);

                ResultSet rs = query.executeQuery();

                while (rs.next()) {
                    maestro maestra = new maestro();
                    maestra.setCi(rs.getInt("ci_maestra"));
                    maestra.setRif(rs.getInt("rif"));
                    maestra.setTitulo(rs.getString("titulo"));
                    maestra.setNombres(rs.getString("nombres"));
                    maestra.setApellidos(rs.getString("apellidos"));
                    maestra.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                    maestra.setFechaIngreso(rs.getDate("fechaIngreso").toLocalDate());
                    maestra.setDireccionCobro(rs.getString("direccionCobro"));
                    maestra.setEstatus(rs.getBoolean("estatus"));
                    maestra.setTitulo(rs.getString("grado"));
                    maestra.setSexo(rs.getBoolean("sexo"));
                    maestra.setTlf1(rs.getString("tlf1"));
                    maestra.setTlf2(rs.getString("tlf2"));
                    maestra.setMunElec(rs.getString("munElec"));
                    maestra.setParrqElec(rs.getString("parrqElec"));
                    maestra.setCenVot(rs.getString("centVot"));
                    maestra.setMunRes(rs.getString("munRes"));
                    maestra.setParrqRes(rs.getString("parrqRes"));
                    maestra.setComRes(rs.getString("comRes"));
                    maestra.setCalleRes(rs.getString("calleRes"));
                    maestra.setNombreJefeClap(rs.getString("nombreJefeCLAP"));
                    maestra.setCiJefeClap(rs.getInt("ciJefeCLAP"));
                    maestra.setTiene1x10(rs.getBoolean("tiene1x10"));
                    maestra.setCantPers1x10(rs.getInt("cantPersonas1x10"));
                    maestra.setHaVotado(rs.getBoolean("voto"));
                    maestra.setObservaciones(rs.getString("observaciones"));
                    maestra.setCiCopia(rs.getBytes("ciCopia"));
                    maestra.setRifCopia(rs.getBytes("rifCopia"));
                    maestra.setTituloCopia(rs.getBytes("tituloCopia"));
                    maestra.setTurno(rs.getBoolean("turno"));
                    maestra.setImg(rs.getBytes("img"));
                    maestras.add(maestra);
                }
            } catch (SQLException e) {
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
        } else {
            sql = "SELECT ci_maestra, rif, titulo, nombres, apellidos, cargo, fechaNac, fechaIngreso," +
                    "direccionCobro, estatus, grado, sexo, tlf1, tlf2," +
                    "munElec, parrqElec, centVot, munRes, parrqRes, comRes, calleRes, nombreJefeCLAP, ciJefeCLAP," +
                    "tiene1x10, cantPersonas1x10, voto, observaciones," +
                    "ciCopia, rifCopia, tituloCopia, turno, img " +
                    "FROM maestras WHERE " + criterio + " = '" + busquedaQuery + "'";
            try {
                conexion = DriverManager.getConnection(url, user, pass);
                System.out.println("Database connection started.");
                PreparedStatement query = conexion.prepareStatement(sql);

                ResultSet rs = query.executeQuery();

                while (rs.next()) {
                    maestro maestra = new maestro();
                    maestra.setCi(rs.getInt("ci_maestra"));
                    maestra.setRif(rs.getInt("rif"));
                    maestra.setTitulo(rs.getString("titulo"));
                    maestra.setNombres(rs.getString("nombres"));
                    maestra.setApellidos(rs.getString("apellidos"));
                    maestra.setTitulo(rs.getString("titulo"));
                    maestra.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                    maestra.setFechaIngreso(rs.getDate("fechaIngreso").toLocalDate());
                    maestra.setDireccionCobro(rs.getString("direccionCobro"));
                    maestra.setEstatus(rs.getBoolean("estatus"));
                    maestra.setSexo(rs.getBoolean("sexo"));
                    maestra.setTlf1(rs.getString("tlf1"));
                    maestra.setTlf2(rs.getString("tlf2"));
                    maestra.setMunElec(rs.getString("munElec"));
                    maestra.setParrqElec(rs.getString("parrqElec"));
                    maestra.setCenVot(rs.getString("centVot"));
                    maestra.setMunRes(rs.getString("munRes"));
                    maestra.setParrqRes(rs.getString("parrqRes"));
                    maestra.setComRes(rs.getString("comRes"));
                    maestra.setCalleRes(rs.getString("calleRes"));
                    maestra.setNombreJefeClap(rs.getString("nombreJefeCLAP"));
                    maestra.setCiJefeClap(rs.getInt("ciJefeCLAP"));
                    maestra.setTiene1x10(rs.getBoolean("tiene1x10"));
                    maestra.setCantPers1x10(rs.getInt("cantPersonas1x10"));
                    maestra.setHaVotado(rs.getBoolean("voto"));
                    maestra.setObservaciones(rs.getString("observaciones"));
                    maestra.setCiCopia(rs.getBytes("ciCopia"));
                    maestra.setRifCopia(rs.getBytes("rifCopia"));
                    maestra.setTituloCopia(rs.getBytes("tituloCopia"));
                    maestra.setTurno(rs.getBoolean("turno"));
                    maestra.setImg(rs.getBytes("img"));
                    maestras.add(maestra);
                }
            } catch (SQLException e) {
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
        return maestras;
    }

    public maestro fetchNominaMaestra(int ci) {
        sql = "SELECT " +
                "ci_maestra, rif, titulo, nombres, apellidos, cargo, fechaNac, fechaIngreso," +
                "direccionCobro, estatus, grado, sexo, tlf1, tlf2," +
                "munElec, parrqElec, centVot, munRes, parrqRes, comRes, calleRes, nombreJefeCLAP, ciJefeCLAP," +
                "tiene1x10, cantPersonas1x10, voto, observaciones," +
                "ciCopia, rifCopia, tituloCopia, turno, img " +
                "FROM maestras WHERE ci_maestra =" + ci;
        maestro maestra = new maestro();

        try {
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            ResultSet rs = query.executeQuery();

            if (rs.next()) {
                maestra.setCi(rs.getInt("ci_maestra"));
                maestra.setRif(rs.getInt("rif"));
                maestra.setTitulo(rs.getString("titulo"));
                maestra.setNombres(rs.getString("nombres"));
                maestra.setApellidos(rs.getString("apellidos"));
                maestra.setTitulo(rs.getString("titulo"));
                maestra.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                maestra.setFechaIngreso(rs.getDate("fechaIngreso").toLocalDate());
                maestra.setDireccionCobro(rs.getString("direccionCobro"));
                maestra.setEstatus(rs.getBoolean("estatus"));
                maestra.setSexo(rs.getBoolean("sexo"));
                maestra.setTlf1(rs.getString("tlf1"));
                maestra.setTlf2(rs.getString("tlf2"));
                maestra.setMunElec(rs.getString("munElec"));
                maestra.setParrqElec(rs.getString("parrqElec"));
                maestra.setCenVot(rs.getString("centVot"));
                maestra.setMunRes(rs.getString("munRes"));
                maestra.setParrqRes(rs.getString("parrqRes"));
                maestra.setComRes(rs.getString("comRes"));
                maestra.setCalleRes(rs.getString("calleRes"));
                maestra.setNombreJefeClap(rs.getString("nombreJefeCLAP"));
                maestra.setCiJefeClap(rs.getInt("ciJefeCLAP"));
                maestra.setTiene1x10(rs.getBoolean("tiene1x10"));
                maestra.setCantPers1x10(rs.getInt("cantPersonas1x10"));
                maestra.setHaVotado(rs.getBoolean("voto"));
                maestra.setObservaciones(rs.getString("observaciones"));
                maestra.setCiCopia(rs.getBytes("ciCopia"));
                maestra.setRifCopia(rs.getBytes("rifCopia"));
                maestra.setTituloCopia(rs.getBytes("tituloCopia"));
                maestra.setTurno(rs.getBoolean("turno"));
                maestra.setImg(rs.getBytes("img"));
            }

        } catch (SQLException e) {
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
        return maestra;
    }

    public static void sendAutorizado(autorizado autorizado) {

        sql = "INSERT INTO autorizadoRetiro (ciAutorizado, apellidos, nombre, tlf1, tlf2) VALUES (?,?,?,?,?)";

        try {
            conexion = DriverManager.getConnection(url, "root", "1234");
            System.out.println("Database connecion started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            query.setInt(1, autorizado.getCi()); // cirepresentante
            query.setString(2, autorizado.getApellidos()); // apellidos
            query.setString(3, autorizado.getNombres()); // nombres
            query.setString(4, autorizado.getTlf1()); // tlf
            query.setString(5, autorizado.getTlf2()); // tlf
            query.setString(6, autorizado.getParentesco());

        } catch (SQLException e) {
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

    public static void removerAutorizado(autorizado autorizado) {

        sql = "DELETE FROM autorizadoRetiro WHERE ciAutorizado = ?";

        try {
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connecion started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            query.setInt(1, autorizado.getCi()); // cirepresentante

        } catch (SQLException e) {
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

    public static List<autorizado> buscarAutorizado(String criterio, String busquedaQuery) {
        List<autorizado> autorizados = new ArrayList<>();
        if (criterio == null) {
            sql = "SELECT * FROM autorizadoretiro";
            try {
                conexion = DriverManager.getConnection(url, user, pass);
                System.out.println("Database connection started.");
                PreparedStatement query = conexion.prepareStatement(sql);
                ResultSet rs = query.executeQuery();

                while (true) {
                    try {
                        rs.next();
                        autorizado autor = new autorizado();
                        autor.setCi(rs.getInt("ciAutorizado"));
                        autor.setApellidos(rs.getString("apellidos"));
                        autor.setNombres(rs.getString("nombres"));
                        autor.setTlf1(rs.getString("tlf1"));
                        autor.setTlf2(rs.getString("tlf2"));
                        autor.setParentesco(rs.getString("parentesco"));
                        autorizados.add(autor);
                        System.out.println(autor.getCi());
                    } catch (SQLException e1) {
                        System.out.println("No more rows found");
                        break;
                    }
                }
                return autorizados;
            } catch (SQLException e) {
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
        } else {
            sql = "SELECT ciAutorizado, apellidos, nombres, tlf1, tlf2 FROM autorizadoretiro WHERE " + criterio + " = ?";
            try {
                conexion = DriverManager.getConnection(url, user, pass);
                System.out.println("Database connection started.");
                PreparedStatement query = conexion.prepareStatement(sql);
                query.setString(1, busquedaQuery);
                ResultSet rs = query.executeQuery();

                while (rs.next()) {
                    autorizado aut = new autorizado();
                    aut.setCi(rs.getInt("ciAutorizado"));
                    aut.setApellidos(rs.getString("apellidos"));
                    aut.setNombres(rs.getString("nombres"));
                    aut.setTlf1(rs.getString("tlf1"));
                    aut.setTlf2(rs.getString("tlf2"));
                    aut.setParentesco(rs.getString("parentesco"));
                    autorizados.add(aut);
                }
                return autorizados;
            } catch (SQLException e) {
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
        return autorizados;
    }

    public static void fetchAutorizado(autorizado autorizado) {

        sql = "SELECT ciAutorizado FROM autorizadoRetiro WHERE ciAutorizado = ?";

        try {
            conexion = DriverManager.getConnection(url, "root", "1234");
            System.out.println("Database connecion started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            query.setInt(1, autorizado.getCi()); // cirepresentante

        } catch (SQLException e) {
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

    public static void sendDiagnostico(diagnostico diagnostico, estudiante estudiante) {

        sql = "INSERT INTO `mydb`.`diagnostico` (`estudiante_ciEstudiante`," +
                "`problemaParto`, `problemaMotor`, `problemaLenguaje`," +
                "`problemaCognitivo`, `alergiaMedicamento`, `condicionExtra`," +
                "`cualParto`, `cualMotor`, `cualLenguaje`, `cualCognitivo`," +
                "`cualAMedicamento`, `cualExtra`, `alergia`, `cualAlergia`," +
                "`enfermedadesPadecidas`, `edadHablar`, `edadCaminar`," +
                "`enfermedadEmbarazo`, `cualEnfEmbarazo`, `embarazoPlanif`," +
                "`medicamentoFiebre`, `vacBCG`, `vacTRIPLE`, `vacPOLIO`, `vacTIFUS`," +
                "`otroVacAplicadas`, `cualVacAplicada`, `consultaPsilg`," +
                "`consultaPsipeg`, `consultaNeur`, `consultaLeng`, `consultaOtro`," +
                "`especifiqueConsultaOtro`, `comeAyudado`, `buenApetito`," +
                "`horaDormir`, `horaLevantarse`, `conQuienDuerme`, `chupaDedo`," +
                "`edadDejarPañales`, `seOrinaDia`, `seOrinaNoche`, `evacuaDia`, `pideAydaAseo`)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            // --- Asignación de parámetros ---
            query.setString(1, estudiante.getCe()); // estudiante_ciEstudiante
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
        } catch (SQLException e) {
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

    public static void editarDiagnostico(diagnostico diagnostico) {
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
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            // Se establecen los parametros a editar
            query.setBoolean(1, diagnostico.isProblemaParto()); // problemaParto
            query.setBoolean(2, diagnostico.isProblemaMotor()); // problemaMotor
            query.setBoolean(3, diagnostico.isProblemaLenguaje()); // problemaLenguaje
            query.setBoolean(4, diagnostico.isProblemaCognitivo()); // problemaC
            query.setBoolean(5, diagnostico.isAlergiaMedicamento()); // alergiaMedicamento
            query.setBoolean(6, diagnostico.isCondicionExtra()); // condicionExtra
            query.setString(7, diagnostico.getCualParto()); // cualParto
            query.setString(8, diagnostico.getCualMotor()); // cualMotor
            query.setString(9, diagnostico.getCualLenguaje()); // cualLenguaje
            query.setString(10, diagnostico.getCualCognitivo()); // cualCognitivo
            query.setString(11, diagnostico.getCualAMedicamento()); // cualAMedicamento
            query.setString(12, diagnostico.getCualExtra()); // cualExtra
            query.setBoolean(13, diagnostico.isAlergia()); // alergia
            query.setString(14, diagnostico.getCualAlergia()); // cualAlergia
            query.setString(15, diagnostico.getEnfermedadesPadecidas()); // enfermedadesPadecidas
            query.setInt(16, diagnostico.getEdadHablar()); // edadHablar
            query.setInt(17, diagnostico.getEdadCaminar()); // edadCaminar
            query.setBoolean(18, diagnostico.isEnfermedadEmbarazo()); // enfermedadEmbarazo
            query.setString(19, diagnostico.getCualEnfEmbarazo()); // cualEnfEmbarazo
            query.setBoolean(20, diagnostico.isEmbarazoPlanif()); // embarazoPlanif
            query.setString(21, diagnostico.getMedicamentoFiebre()); // medicamentoFiebre
            query.setBoolean(22, diagnostico.isVacBCG()); // vacBCG
            query.setBoolean(23, diagnostico.isVacTRIPLE()); // vacTRIPLE
            query.setBoolean(24, diagnostico.isVacPOLIO()); // vacPOLIO
            query.setBoolean(25, diagnostico.isVacTIFUS()); // vacTIF
            query.setBoolean(26, diagnostico.isOtroVacAplicadas()); // otroVacAplicadas
            query.setString(27, diagnostico.getCualVacAplicada()); // cualVacAplicada
            query.setBoolean(28, diagnostico.isConsultaPsilg()); // consultaPsilg
            query.setBoolean(29, diagnostico.isConsultaPsipeg()); // consultaPsipe
            query.setBoolean(30, diagnostico.isConsultaNeur()); // consultaNeur
            query.setBoolean(31, diagnostico.isConsultaLeng()); // consultaLeng
            query.setBoolean(32, diagnostico.isConsultaOtro()); // consultaOtro
            query.setString(33, diagnostico.getEspecifiqueConsultaOtro()); // especifiqueConsultaOtro
            query.setBoolean(34, diagnostico.isComeAyudado()); // comeAyudado
            query.setBoolean(35, diagnostico.isBuenApetito()); // buenApetito
            query.setTime(36, Time.valueOf(diagnostico.getHoraDormir())); // horaDormir
            query.setTime(37, Time.valueOf(diagnostico.getHoraLevantarse())); // horaLevantarse
            query.setString(38, diagnostico.getConQuienDuerme()); // conQuienDuerme
            query.setBoolean(39, diagnostico.isChupaDedo()); // chupaDedo
            query.setInt(40, diagnostico.getEdadDejarPanales()); // edadDejarPañales
            query.setBoolean(41, diagnostico.isSeOrinaDia()); // seOrinaDia
            query.setBoolean(42, diagnostico.isSeOrinaNoche()); // seOrinaNoche
            query.setBoolean(43, diagnostico.isEvacuaDia()); // evacuaDia
            query.setBoolean(44, diagnostico.isPideAyudaAseo()); // pideAydaAseo

            // Se establece el ci del estudiante al que pertenece el diagnostico
            query.setString(45, diagnostico.getCe()); // estudiante_ciEstudiante

            // --- Ejecución de la consulta ---
            int columnasAfectadas = query.executeUpdate();
            System.out.print(columnasAfectadas);
            // --- Verificación del resultado ---
            if (columnasAfectadas > 0) {
                System.out.println("¡Fila actualizada exitosamente!");
            } else {
                System.out.println("La actualización de la fila ha fallado.");
            }

        } catch (SQLException e) {
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

    public static void removerDiagnostico(String ce) {

        sql = "DELETE FROM diagnostico" +
                " WHERE estudiante_ciEstudiante = ?;";

        try {
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            query.setString(1, ce); // estudiante_ciEstudiante

            // --- Ejecución de la consulta ---
            int columnasAfectadas = query.executeUpdate();

            // --- Verificación del resultado ---
            if (columnasAfectadas > 0) {
                System.out.println("¡Fila insertada exitosamente!");
            } else {
                System.out.println("La inserción de la fila ha fallado.");
            }
        } catch (SQLException e) {
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

    public static void setRelRepresentado(representante representative, estudiante student, boolean rol, String relationship) {
        String sql = "INSERT INTO representaa (representante_ciRepresentante, estudiante_ciEstudiante, rol, parentesco) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the parameters for the prepared statement
            pstmt.setInt(1, representative.getCi()); // representante_ciRepresentante
            pstmt.setString(2, student.getCe()); // estudiante_ciEstudiante
            pstmt.setBoolean(3, rol); // rol (BIT(1) in MySQL maps to boolean in Java)
            pstmt.setString(4, relationship); // parentesco

            // Execute the update (insert)
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully into representaa for Representative CI: " + representative.getCi() + " and Student CE: " + student.getCe());
            } else {
                System.out.println("No rows affected. Data insertion failed for representaa for Representative CI: " + representative.getCi() + " and Student CE: " + student.getCe());
            }

        } catch (SQLException e) {
            System.err.println("Database error during representaa insertion: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void setRelAutorizado(autorizado authorized, estudiante student) {
        String sql = "INSERT INTO puederetirar (autorizadoRetiro_ciAutorizado, estudiante_ciEstudiante, parentesco) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the parameters for the prepared statement
            pstmt.setInt(1, authorized.getCi()); // autorizadoRetiro_ciAutorizado from autorizado object
            pstmt.setString(2, student.getCe()); // estudiante_ciEstudiante from estudiante object (assuming 'ce' is the student ID)
            pstmt.setString(3, authorized.getParentesco()); // parentesco

            // Execute the update (insert)
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully into puederetirar for CI: " + authorized.getCi() + " and Student CE: " + student.getCe());
            } else {
                System.out.println("No rows affected. Data insertion failed for puederetirar for CI: " + authorized.getCi() + " and Student CE: " + student.getCe());
            }

        } catch (SQLException e) {
            System.err.println("Database error during puederetirar insertion: " + e.getMessage());
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

    public static void setSocioFamiliar(socioFamiliar socioFamiliar){
        String sql = "INSERT INTO socioFamiliar (" +
                "estudiante_ciEstudiante, vivienda, tipoVivienda, cuidaNiñoHogar, estdCivilPadres, " +
                "consultAtend, visitPsicopedagogo, visitPsicologo, visitNeurologo, visitTerapLeng" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the parameters for the prepared statement using getters
            pstmt.setString(1, socioFamiliar.getEstudiante_ciEstudiante());
            pstmt.setString(2, socioFamiliar.getVivienda());
            pstmt.setString(3, socioFamiliar.getTipoVivienda());
            pstmt.setString(4, socioFamiliar.getCuidaNinoHogar());
            // Note: relacionAmbienteFamiliar is in the class but not in the new table schema, so it's omitted here.
            pstmt.setString(5, socioFamiliar.getEstdCivilPadres());
            pstmt.setString(6, socioFamiliar.getConsultaAtend());
            pstmt.setBoolean(7, socioFamiliar.isVisitPsicopedagogo());
            pstmt.setBoolean(8, socioFamiliar.isVisitPsicologo());
            pstmt.setBoolean(9, socioFamiliar.isVisitNeurologo());
            pstmt.setBoolean(10, socioFamiliar.isVisitTerapLeng());


            // Execute the update (insert)
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully into socioFamiliar for Student CE: " + socioFamiliar.getEstudiante_ciEstudiante());
            } else {
                System.out.println("No rows affected. Data insertion failed for socioFamiliar for Student CE: " + socioFamiliar.getEstudiante_ciEstudiante());
            }
        } catch (SQLException e) {
            System.err.println("Database error during socioFamiliar insertion: " + e.getMessage());
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

    public static socioFamiliar fetchSocioFamiliar(String ciEstudiante) {
        socioFamiliar sf = null;
        String sql = "SELECT estudiante_ciEstudiante, vivienda, tipoVivienda, " +
                "cuidaNiñoHogar, relacionAmbienteFamiliar, estdCivilPadres, " +
                "consultAtend, visitPsicopedagogo, visitPsicologo, visitNeurologo, visitTerapLeng " + // Added
                "FROM socioFamiliar WHERE estudiante_ciEstudiante = ?";

        try {
            conexion = DriverManager.getConnection(url, user, pass); // Use try-with-resources for connection
            PreparedStatement pstmt = conexion.prepareStatement(sql);

            pstmt.setString(1, ciEstudiante); // Set the CI parameter

            try (ResultSet rs = pstmt.executeQuery()) { // Use try-with-resources for ResultSet
                if (rs.next()) {
                    sf = new socioFamiliar();
                    sf.setEstudiante_ciEstudiante(rs.getString("estudiante_ciEstudiante"));
                    sf.setVivienda(rs.getString("vivienda"));
                    sf.setTipoVivienda(rs.getString("tipoVivienda"));
                    sf.setCuidaNinoHogar(rs.getString("cuidaNiñoHogar"));
                    sf.setRelacionAmbienteFamiliar(rs.getString("relacionAmbienteFamiliar"));
                    sf.setEstdCivilPadres(rs.getString("estdCivilPadres"));
                    sf.setConsultaAtend(rs.getString("consultAtend")); // Set consultAtend
                    sf.setVisitPsicopedagogo(rs.getBoolean("visitPsicopedagogo")); // Set boolean
                    sf.setVisitPsicologo(rs.getBoolean("visitPsicologo"));       // Set boolean
                    sf.setVisitNeurologo(rs.getBoolean("visitNeurologo"));       // Set boolean
                    sf.setVisitTerapLeng(rs.getBoolean("visitTerapLeng"));       // Set boolean
                } else {
                    System.out.println("No socioFamiliar data found for student CI: " + ciEstudiante);
                }
            } // ResultSet is closed automatically
        } catch (SQLException e) {
            System.err.println("Database error during socioFamiliar fetch: " + e.getMessage());
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
        return sf;
    }
    
    public static void sendDocumentos(documentos documentos){
                String sql = "INSERT INTO documentos (" +
                     "estudiante_ciEstudiante, originales, fotocopias, partidaNac, certificadoVacunas, " +
                     "cedulaMadre, cedulaPadre, cedulaRep, responsableInscripcion, personaInscribe, " +
                     "fechaInscripcion, observaciones) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Usamos try-with-resources para asegurar que la conexión y el PreparedStatement se cierren automáticamente.
        try (Connection conexion = DriverManager.getConnection(url,user,pass);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            // Establece los parámetros de la consulta SQL usando los valores del objeto documentoss.
            // Los índices de los parámetros corresponden a los '?' en la consulta SQL, empezando desde 1.
            pstmt.setString(1, documentos.getCe());
            pstmt.setBoolean(2, documentos.isOriginales());
            pstmt.setBoolean(3, documentos.isFotocopias());
            pstmt.setBoolean(4, documentos.isPartidaNac());
            pstmt.setBoolean(5, documentos.isCertificadoVacuna());
            pstmt.setBoolean(6, documentos.isCedulaMadre());
            pstmt.setBoolean(7, documentos.isCedulaPadre());
            pstmt.setBoolean(8, documentos.isCedulaRep());
            pstmt.setString(9, documentos.getResponsableInscripcion());
            pstmt.setString(10, documentos.getPersonaInscribe());

            // Para LocalDate, usamos java.sql.Date.valueOf() para convertirlo a un tipo compatible con SQL DATE.
            pstmt.setDate(11, java.sql.Date.valueOf(documentos.getFechaInscripcion()));
            pstmt.setString(12, documentos.getObservaciones());

            // Ejecuta la consulta de inserción.
            // executeUpdate() devuelve el número de filas afectadas (1 si la inserción fue exitosa).
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully into estudiante for CI: " + documentos.getCe());
            } else {
                System.out.println("No rows affected. Data insertion failed for estudiante for CI: " + documentos.getCe());
            }
        } catch (SQLException e) {
            // Manejo de excepciones SQL: Imprime el error y devuelve false.
            System.err.println("Error al insertar el documentos: " + e.getMessage());
            e.printStackTrace(); // Imprime la traza completa para depuración
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

    public static void crearUsuario(acceso acceso) {

        String sql = "INSERT INTO acceso (usuario, hash, permiso, twoFA) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the values for the prepared statement using getters from the Acceso object
            pstmt.setInt(3, acceso.getTipo_acceso());       // Maps to 'permiso'
            pstmt.setString(1, acceso.getNombre_usuario()); // Maps to 'usuario'
            pstmt.setString(2, acceso.getContrasenaHash()); // Maps to 'hash'
            pstmt.setString(4, acceso.getTwoFA());          // Maps to 'twoFA'

            // Execute the insert statement
            int rowsAffected = pstmt.executeUpdate();

            // Return true if at least one row was successfully inserted
            if (rowsAffected > 0){
                System.out.println("Data inserted successfully into usuario for usuario: " + acceso.getNombre_usuario());
            } else {
                System.out.println("Data couldn't be inserted for usuario: " + acceso.getNombre_usuario());
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar registro de acceso: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for detailed debugging
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

    public static void removerUsuario(String usuario) {
        // SQL DELETE statement with a WHERE clause to target a specific user.
        String sql = "DELETE FROM acceso WHERE usuario = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the username parameter for the WHERE clause
            pstmt.setString(1, usuario);

            // Execute the delete statement
            int rowsAffected = pstmt.executeUpdate();

            // Return true if at least one row was deleted
            if (rowsAffected > 0){
                System.out.println("Data deleted successfully for usuario: " + usuario);
            } else {
                System.out.println("Data couldn't be deleted for usuario: " + usuario);
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar registro de acceso para usuario: " + usuario + " - " + e.getMessage());
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

    public static void fetchUsuario(acceso accesoPresente) {
        sql = "SELECT * WHERE usuario =" + accesoPresente.getNombre_usuario();

        try {
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            ResultSet rs = query.executeQuery();
            try {
                rs.next();
                //accesoPresente.getNombre_usuario().equals(rs.getString("usuario"));
            } catch (SQLException e1) {
                System.out.println("USUARIO NO EXISTENTE");
                e1.printStackTrace();
            }
        } catch (SQLException e) {
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

    public static List<acceso> getAllAccesos() {
        String sql = "SELECT id, usuario, hash, permiso, twoFA FROM acceso";
        List<acceso> accesos = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Iterate through the result set to fetch each row
            try {
            while (rs.next()) {
                // Create a new Acceso object for each row
                acceso acceso = new acceso();

                // Set properties using setters
                acceso.setTipo_acceso(rs.getInt("permiso"));
                acceso.setNombre_usuario(rs.getString("usuario"));
                acceso.setContrasenaHash(rs.getString("hash"));
                acceso.setTwoFA(rs.getString("twoFA")); // Set the twoFA value using its setter

                // Add the populated Acceso object to the list
                accesos.add(acceso);
            } } catch (SQLException e1){
                System.out.println("Recuperados todos los usuarios de la base de datos");
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener todos los registros de acceso: " + e.getMessage());
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
        return accesos;
    }

    public static void promoverAño() {
    }

    public static void aumentarNivelEstd() {
    }

    public static List<familia> fetchRelFamiliar(String cEstudiante) {

        sql = "SELECT estudiante_ciEstudiante, nombre, apellido, edad, parentezco, ocupacion " +
                "FROM `familiares extra` WHERE estudiante_ciEstudiante = \"" + cEstudiante + "\"";
        List<familia> fams = null;

        try {
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            ResultSet rs = query.executeQuery();
            fams = new ArrayList<>();

            try {
                while (rs.next()) {
                    familia repF = new familia();
                    repF.setCeRelacionado(rs.getString("estudiante_ciEstudiante"));
                    repF.setNombres(rs.getString("nombre"));
                    repF.setApellidos(rs.getString("apellido"));
                    repF.setEdad(rs.getString("edad"));
                    repF.setOcupacion(rs.getString("ocupacion"));
                    repF.setParentesco(rs.getString("parentezco"));
                    fams.add(repF);
                }
            } catch (SQLException e1) {
                System.out.println("Recuperados todos los estudiantes");
                return fams;
            }

        } catch (SQLException e) {
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
        return fams;
    }

    public static void setFamilia(familia familia, estudiante estudiante) {
        // SQL INSERT statement for 'familiares extra' table
        String sql = "INSERT INTO `familiares extra` (" +
                "nombre, apellido, edad, parentezco, ocupacion, estudiante_ciEstudiante) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {

            conexion = DriverManager.getConnection(url, user, pass); // Use try-with-resources for connection
            PreparedStatement pstmt = conexion.prepareStatement(sql);

            // Set the parameters for the prepared statement using getters from the familia object
            pstmt.setString(1, familia.getNombres());
            pstmt.setString(2, familia.getApellidos());
            pstmt.setString(3, familia.getEdad());
            pstmt.setString(4, familia.getParentesco()); // Note 'parentezco' for the column name
            pstmt.setString(5, familia.getOcupacion());
            pstmt.setString(6, estudiante.getCe());

            // Execute the update (insert)
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully into `familiares extra` for student CE: " + estudiante.getCe());
            } else {
                System.out.println("No rows affected. Data insertion failed for `familiares extra` for student CE: " + estudiante.getCe());
            }
        } catch (SQLException e) {
            System.err.println("Database error during `familiares extra` insertion: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<representaA> fetchRelRepresentante(int ciRep) {

        sql = "SELECT * FROM retiraa WHERE representante_ciRepresentante =" + ciRep;
        List<representaA> reps = null;

        try {
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            ResultSet rs = query.executeQuery();
            reps = new ArrayList<>();

            try {
                while (rs.next()) {
                    representaA repN = new representaA();
                    repN.setEstudiante_ciEstudiante(rs.getString("estudiante_ciEstudiante"));
                    repN.setRepresentante_ciRepresentante(rs.getInt("representante_ciRepresentante"));
                    repN.setRol(rs.getBoolean("rol"));
                    repN.setParentesco(rs.getString("parentesco"));
                    reps.add(repN);
                }
            } catch (SQLException e1) {
                System.out.println("Recuperados todos los estudiantes");
                return reps;
            }

        } catch (SQLException e) {
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
        return reps;
    }

    public static List<retiraA> fetchRelAutorizado(String ciAutorizado) {

        sql = "SELECT * FROM puederetirar WHERE autorizadoRetiro_ciAutorizado =" + ciAutorizado;
        List<retiraA> rets = null;

        try {
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            ResultSet rs = query.executeQuery();
            rets = new ArrayList<>();

            try {
                while (rs.next()) {
                    retiraA retN = new retiraA();
                    retN.setAutorizadoRetiro_ciAutorizado(rs.getInt("autorizadoRetiro_ciAutorizado"));
                    retN.setEstudiante_ciEstudiante(rs.getString("estudiante_ciEstudiante"));
                    retN.setParentesco(rs.getString("parentesco"));
                    rets.add(retN);
                }
            } catch (SQLException e1) {
                System.out.println("Recuperados todos los estudiantes");
                return rets;
            }

        } catch (SQLException e) {
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
        return rets;
    }
}

