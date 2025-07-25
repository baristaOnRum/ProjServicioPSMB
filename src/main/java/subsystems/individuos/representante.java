package subsystems.individuos;

import java.time.LocalDate;

public class representante extends personas{

    int ci;
    int edad;
    boolean ninosMenor6;
    String estadoCivil;
    String nacionalidad;
    String direccionHab;
    String direccionTrabj;
    String ocupacion;
    String gradoEstudios;
    String tlf1;
    String tlf2;
    String tlfTrabajo;
    String tlfCasa;
    String correo;

    representante(int ci, int edad, boolean ninosMenor6, String nombres,
                  String apellidos, String lugarNac, LocalDate fechaNac,
                  String estadoCivil, String nacionalidad, String direccionHab,
                  String direccionTrabj, String ocupacion, String gradoEstudios,
                  String tlf1, String tlf2, String tlfTrabajo, String tlfCasa,
                  String correo){

        this.ci = ci;
        this.edad = edad;
        this.ninosMenor6 = ninosMenor6;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.lugarNac = lugarNac;
        this.fechaNac = fechaNac;
        this.estadoCivil = estadoCivil;
        this.nacionalidad = nacionalidad;
        this.direccionHab = direccionHab;
        this.direccionTrabj = direccionTrabj;
        this.ocupacion = ocupacion;
        this.gradoEstudios = gradoEstudios;
        this.tlf1 = tlf1;
        this.tlf2 = tlf2;
        this. tlfTrabajo = tlfTrabajo;
        this.tlfCasa = tlfCasa;
        this.correo = correo;

    }

    public int getCi() {
        return ci;
    }

    public int getEdad() {
        return edad;
    }

    public boolean isNinosMenor6() {
        return ninosMenor6;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getDireccionHab() {
        return direccionHab;
    }

    public String getDireccionTrabj() {
        return direccionTrabj;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public String getGradoEstudios() {
        return gradoEstudios;
    }

    public String getTlf1() {
        return tlf1;
    }

    public String getTlf2() {
        return tlf2;
    }

    public String getTlfTrabajo() {
        return tlfTrabajo;
    }

    public String getTlfCasa() {
        return tlfCasa;
    }

    public String getCorreo() {
        return correo;
    }


}
