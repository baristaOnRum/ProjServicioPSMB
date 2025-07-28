package subsystems.individuos;

import java.time.LocalDate;

public class representante extends personas{

    int ci;
    int edad;
    boolean ninosMenor6;
    String estadoCivil;
    String lugarNac;
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
    byte[] img;
    LocalDate fechaNac;


//    representante(int ci, int edad, boolean ninosMenor6, String nombres,
//                  String apellidos, String lugarNac, LocalDate fechaNac,
//                  String estadoCivil, String nacionalidad, String direccionHab,
//                  String direccionTrabj, String ocupacion, String gradoEstudios,
//                  String tlf1, String tlf2, String tlfTrabajo, String tlfCasa,
//                  String correo){
//
//        this.ci = ci;
//        this.edad = edad;
//        this.ninosMenor6 = ninosMenor6;
//        this.nombres = nombres;
//        this.apellidos = apellidos;
//        this.lugarNac = lugarNac;
//        this.fechaNac = fechaNac;
//        this.estadoCivil = estadoCivil;
//        this.nacionalidad = nacionalidad;
//        this.direccionHab = direccionHab;
//        this.direccionTrabj = direccionTrabj;
//        this.ocupacion = ocupacion;
//        this.gradoEstudios = gradoEstudios;
//        this.tlf1 = tlf1;
//        this.tlf2 = tlf2;
//        this. tlfTrabajo = tlfTrabajo;
//        this.tlfCasa = tlfCasa;
//        this.correo = correo;
//
//    }


    @Override
    public String getLugarNac() {
        return lugarNac;
    }

    @Override
    public void setLugarNac(String lugarNac) {
        this.lugarNac = lugarNac;
    }

    @Override
    public LocalDate getFechaNac() {
        return fechaNac;
    }

    @Override
    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
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

    public void setCi(int ci) {
        this.ci = ci;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNinosMenor6(boolean ninosMenor6) {
        this.ninosMenor6 = ninosMenor6;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setDireccionHab(String direccionHab) {
        this.direccionHab = direccionHab;
    }

    public void setDireccionTrabj(String direccionTrabj) {
        this.direccionTrabj = direccionTrabj;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public void setGradoEstudios(String gradoEstudios) {
        this.gradoEstudios = gradoEstudios;
    }

    public void setTlf1(String tlf1) {
        this.tlf1 = tlf1;
    }

    public void setTlf2(String tlf2) {
        this.tlf2 = tlf2;
    }

    public void setTlfTrabajo(String tlfTrabajo) {
        this.tlfTrabajo = tlfTrabajo;
    }

    public void setTlfCasa(String tlfCasa) {
        this.tlfCasa = tlfCasa;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
