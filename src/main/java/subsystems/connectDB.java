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
import subsystems.individuos.maestro;
import subsystems.individuos.representante;
import subsystems.individuos.trabajador;

public class connectDB {

    //Definir parámetros de conexión
    static String url;
    static String user;
    static String pass;
    static Connection conexion;
    static String sql;
    //Definir funciones

    //Inicialización de variables (no estáticas)
    public void connparamsinit(acceso acceso) {
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
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
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
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
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

    public void sendEstudiante(estudiante estudiante) {

        sql = "INSERT INTO `mydb`.`estudiante` (`ciEstudiante`, `apellidos`, `nombres`," +
                "`fechaNac`, `lugarNac`, `nacionalidad`, `edad`, `procedencia`," +
                "`tallaCam`, `tallaPant`, `tallaZap`, `peso`, `estatura`, `periodioCurso`," +
                "`periodoCursado`, `img`, `lateralidad`, `grupoSanguineo`, `asegurado`," +
                "`cualSeguro`, `medicoTratante`, `tlfMedicoTratante`)" +
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

    public void removerEstudiante(String ce) {

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
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
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
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
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
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
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

    public void sendNomina(trabajador trabajador) {

        sql = "INSERT INTO `mydb`.`trabajadores` (" +
                "`ci_trabajador`, `rif`, `titulo`, `nombres`, `apellidos`, " +
                "`cargo`, `fechaNac`, `fechaIngreso`, `direccionCobro`, `estatus`, `grado`, `sexo`, `tlf1`, `tlf2`, " +
                "`munElec`, `parrqElec`, `centVot`, `munRes`, `parrqRes`, `comRes`, `calleRes`, `nombreJefeCLAP`, `ciJefeCLAP`, " +
                "`tiene1x10`, `cantPersonas1x10`, `voto`, `observaciones`, `ciCopia`, `rifCopia`, `tituloCopia`, `turno`, `img`" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
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

    public void removerNomina(int ci) {

        sql = "DELETE FROM trabajador" +
                " WHERE ci_trabajadores = ?;";
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
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
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
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
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
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
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

    public void sendNominaMaestra(maestro maestro) {
        sql = "INSERT INTO `mydb`.`maestras` (" +
                "`ci_maestra`, `rif`, `titulo`, `nombres`, `apellidos`, `cargo`, `fechaNac`, `fechaIngreso`, `direccionCobro`, `estatus`, `grado`, `sexo`, `tlf1`, `tlf2`, " +
                "`munElec`, `parrqElec`, `centVot`, `munRes`, `parrqRes`, `comRes`, `calleRes`, `nombreJefeCLAP`, `ciJefeCLAP`, `tiene1x10`, `cantPersonas1x10`, " +
                "`voto`, `observaciones`, `ciCopia`, `rifCopia`, `tituloCopia`, `turno`, `img`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
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

    public void removerNominaMaestra(int ci) {
        sql = "DELETE FROM maestras WHERE ci_maestra = ?;";
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
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
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
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
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
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
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
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

    public void sendAutorizado(autorizado autorizado) {

        sql = "INSERT INTO autorizadoRetiro (ciAutorizado, apellidos, nombre, tlf1, tlf2) VALUES (?,?,?,?,?)";

        try {
            conexion = DriverManager.getConnection(url, "root", "1234");
            System.out.println("Database connecion started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            query.setInt(1, autorizado.getCi()); // cirepresentante
            query.setString(2, autorizado.getApellidos()); // apellidos
            query.setString(3, autorizado.getNombres()); // nombres
            query.setString(13, autorizado.getTlf1()); // tlf
            query.setString(14, autorizado.getTlf2()); // tlf

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

    public void removerAutorizado(autorizado autorizado) {

        sql = "DELETE FROM autorizadoRetiro WHERE ciAutorizado = ?";

        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "1234");
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
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
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
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
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

    public void fetchAutorizado(autorizado autorizado) {

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

    public void sendDiagnostico(diagnostico diagnostico) {

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

    public void editarDiagnostico(diagnostico diagnostico) {
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

    public void removerDiagnostico(String ce) {

        sql = "DELETE FROM diagnostico" +
                " WHERE estudiante_ciEstudiante = ?;";

        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
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

    public void setRelRepresentado() {
    }

    public void setRelAutorizado() {
    }

    public void crearUsuario() {
    }

    public void removerUsuario() {
    }

    public void fetchUsuario(acceso accesoPresente) {
        sql = "SELECT * WHERE usuario =" + accesoPresente.getNombre_usuario();

        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
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

    public void promoverAño() {
    }

    public void crearFamiliar() {
    }

    public void fetchFamiliar() {
    }

    public void aumentarNivelEstd() {
    }

    public void fetchRelFamiliar() {

    }

    public void fetchRelRepresentante() {

    }

    ;

    public List<estudiante> fetchRelAutorizado(String ciAutorizado) {

        sql = "SELECT * FROM puederetirar WHERE autorizadoRetiro_ciAutorizado =" + ciAutorizado;
        List<estudiante> ests = new ArrayList<>();

        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "bandidito10");
            System.out.println("Database connection started.");
            PreparedStatement query = conexion.prepareStatement(sql);

            ResultSet rs = query.executeQuery();

            try {
                while (rs.next()) {
                    estudiante estN = new estudiante();
                    estN.setCe(rs.getString("ciEstudiante"));
                    ests.add(estN);
                }
            } catch (SQLException e1) {
                System.out.println("Recuperados todos los estudiantes");
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
}


    //Main
    public void main(String args[]) throws SQLException{

        //connparamsinit();
        //sendRepresentante();

    }
}

