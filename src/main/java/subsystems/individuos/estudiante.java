package subsystems.individuos;

import java.time.LocalDate;

public class estudiante extends personas{
    String ce;
    String nacionalidad;
    String procedencia;
    String grupoSanguineo;
    String cualSeguro;
    String medicoTratante;
    String tlfMedicoTratante;
    int tallaZapato;
    int tallaCamisa;
    int tallaPantalon;
    int nivel;
    int edad;
    int estatura;
    int peso;
    int periodoCurso;
    int periodoCursado;
    boolean lateralidad;
    boolean asegurado;
    byte turno;

    estudiante(String nombres, String apellidos, String lugarNac, LocalDate fechaNac,
               String cualSeguro,  String grupoSanguineo, String tlfMedicoTratante,
               String medicoTratante, String ce, String nacionalidad, String procedencia,
               int tallaZapato, int tallaCamisa, int tallaPantalon, int nivel, int edad,
               int estatura, int peso, int periodoCurso, int periodoCursado, boolean lateralidad,
               boolean asegurado, byte turno){

        this.nombres = nombres;
        this.apellidos = apellidos;
        this.lugarNac = lugarNac;
        this.fechaNac = fechaNac;
        this.ce = ce;
        this.nacionalidad = nacionalidad;
        this.procedencia = procedencia;
        this.tallaZapato = tallaZapato;
        this.tallaCamisa = tallaCamisa;
        this.tallaPantalon = tallaPantalon;
        this.nivel = nivel;
        this.edad = edad;
        this.estatura = estatura;
        this.peso = peso;
        this.periodoCurso = periodoCurso;
        this.periodoCursado = periodoCursado;
        this.turno = turno;
        this.cualSeguro = cualSeguro;
        this.lateralidad = lateralidad;
        this.asegurado = asegurado;
        this.grupoSanguineo = grupoSanguineo;
        this.tlfMedicoTratante = tlfMedicoTratante;
        this.medicoTratante = medicoTratante;

    }
    public String getCe() {
        return ce;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public String getCualSeguro() {
        return cualSeguro;
    }

    public String getMedicoTratante() {
        return medicoTratante;
    }

    public String getTlfMedicoTratante() {
        return tlfMedicoTratante;
    }

    public int getTallaZapato() {
        return tallaZapato;
    }

    public int getTallaCamisa() {
        return tallaCamisa;
    }

    public int getTallaPantalon() {
        return tallaPantalon;
    }

    public int getNivel() {
        return nivel;
    }

    public int getEdad() {
        return edad;
    }

    public int getEstatura() {
        return estatura;
    }

    public int getPeso() {
        return peso;
    }

    public int getPeriodoCurso() {
        return periodoCurso;
    }

    public int getPeriodoCursado() {
        return periodoCursado;
    }

    public boolean isLateralidad() {
        return lateralidad;
    }

    public boolean isAsegurado() {
        return asegurado;
    }

    public byte getTurno() {
        return turno;
    }

    public void setCe(String ce) {
        this.ce = ce;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public void setCualSeguro(String cualSeguro) {
        this.cualSeguro = cualSeguro;
    }

    public void setMedicoTratante(String medicoTratante) {
        this.medicoTratante = medicoTratante;
    }

    public void setTlfMedicoTratante(String tlfMedicoTratante) {
        this.tlfMedicoTratante = tlfMedicoTratante;
    }

    public void setTallaZapato(int tallaZapato) {
        this.tallaZapato = tallaZapato;
    }

    public void setTallaCamisa(int tallaCamisa) {
        this.tallaCamisa = tallaCamisa;
    }

    public void setTallaPantalon(int tallaPantalon) {
        this.tallaPantalon = tallaPantalon;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setPeriodoCurso(int periodoCurso) {
        this.periodoCurso = periodoCurso;
    }

    public void setPeriodoCursado(int periodoCursado) {
        this.periodoCursado = periodoCursado;
    }

    public void setLateralidad(boolean lateralidad) {
        this.lateralidad = lateralidad;
    }

    public void setAsegurado(boolean asegurado) {
        this.asegurado = asegurado;
    }

    public void setTurno(byte turno) {
        this.turno = turno;
    }
}
