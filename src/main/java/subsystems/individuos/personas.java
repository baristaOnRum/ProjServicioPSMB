package subsystems.individuos;

import java.time.LocalDate;

public class personas {

    String nombres;
    String apellidos;
    String lugarNac;
    LocalDate fechaNac;

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getLugarNac() {
        return lugarNac;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

}
