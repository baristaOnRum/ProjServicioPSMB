package subsystems.individuos;

import java.time.LocalDate;

public class maestro extends personas{

    int ci;
    int rif;
    int ciJefeClap;
    int cantPers1x10;
    int nivel;
    String seccion;
    String titulo;
    String direccionCobro;
    String tlf1;
    String tlf2;
    String munElec;
    String parrqElec;
    String cenVot;
    String munRes;
    String parrqRes;
    String comRes;
    String calleRes;
    String nombreJefeClap;
    String observaciones;
    LocalDate fechaIngreso;
    boolean turno;
    boolean estatus;
    boolean sexo;
    boolean tiene1x10;
    boolean haVotado;
    byte[] ciCopia;
    byte[] rifCopia;
    byte[] tituloCopia;

    public maestro(int ci, int rif, int ciJefeClap, int cantPers1x10, int nivel,
                   String nombres, String apellidos, String seccion, String titulo,
                   String direccionCobro, String tlf1, String tlf2, String munElec,
                   String parrqElec, String cenVot, String munRes, String parrqRes,
                   String comRes, String calleRes, String nombreJefeClap, String observaciones,
                   LocalDate fechaIngreso, LocalDate fechaNac, boolean turno, boolean estatus,
                   boolean sexo, boolean tiene1x10, boolean haVotado,
                   byte[] ciCopia, byte[] rifCopia, byte[] tituloCopia) {
        this.ci = ci;
        this.rif = rif;
        this.ciJefeClap = ciJefeClap;
        this.cantPers1x10 = cantPers1x10;
        this.nivel = nivel;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.seccion = seccion;
        this.titulo = titulo;
        this.direccionCobro = direccionCobro;
        this.tlf1 = tlf1;
        this.tlf2 = tlf2;
        this.munElec = munElec;
        this.parrqElec = parrqElec;
        this.cenVot = cenVot;
        this.munRes = munRes;
        this.parrqRes = parrqRes;
        this.comRes = comRes;
        this.calleRes = calleRes;
        this.nombreJefeClap = nombreJefeClap;
        this.observaciones = observaciones;
        this.fechaNac = fechaNac;
        this.fechaIngreso = fechaIngreso;
        this.turno = turno;
        this.estatus = estatus;
        this.sexo = sexo;
        this.tiene1x10 = tiene1x10;
        this.haVotado = haVotado;
        this.ciCopia = ciCopia;
        this.rifCopia = rifCopia;
        this.tituloCopia = tituloCopia;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public int getRif() {
        return rif;
    }

    public void setRif(int rif) {
        this.rif = rif;
    }

    public int getCiJefeClap() {
        return ciJefeClap;
    }

    public void setCiJefeClap(int ciJefeClap) {
        this.ciJefeClap = ciJefeClap;
    }

    public int getCantPers1x10() {
        return cantPers1x10;
    }

    public void setCantPers1x10(int cantPers1x10) {
        this.cantPers1x10 = cantPers1x10;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDireccionCobro() {
        return direccionCobro;
    }

    public void setDireccionCobro(String direccionCobro) {
        this.direccionCobro = direccionCobro;
    }

    public String getTlf1() {
        return tlf1;
    }

    public void setTlf1(String tlf1) {
        this.tlf1 = tlf1;
    }

    public String getTlf2() {
        return tlf2;
    }

    public void setTlf2(String tlf2) {
        this.tlf2 = tlf2;
    }

    public String getMunElec() {
        return munElec;
    }

    public void setMunElec(String munElec) {
        this.munElec = munElec;
    }

    public String getParrqElec() {
        return parrqElec;
    }

    public void setParrqElec(String parrqElec) {
        this.parrqElec = parrqElec;
    }

    public String getCenVot() {
        return cenVot;
    }

    public void setCenVot(String cenVot) {
        this.cenVot = cenVot;
    }

    public String getMunRes() {
        return munRes;
    }

    public void setMunRes(String munRes) {
        this.munRes = munRes;
    }

    public String getParrqRes() {
        return parrqRes;
    }

    public void setParrqRes(String parrqRes) {
        this.parrqRes = parrqRes;
    }

    public String getComRes() {
        return comRes;
    }

    public void setComRes(String comRes) {
        this.comRes = comRes;
    }

    public String getCalleRes() {
        return calleRes;
    }

    public void setCalleRes(String calleRes) {
        this.calleRes = calleRes;
    }

    public String getNombreJefeClap() {
        return nombreJefeClap;
    }

    public void setNombreJefeClap(String nombreJefeClap) {
        this.nombreJefeClap = nombreJefeClap;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public boolean isTiene1x10() {
        return tiene1x10;
    }

    public void setTiene1x10(boolean tiene1x10) {
        this.tiene1x10 = tiene1x10;
    }

    public boolean isHaVotado() {
        return haVotado;
    }

    public void setHaVotado(boolean haVotado) {
        this.haVotado = haVotado;
    }

    public byte[] getCiCopia() {
        return ciCopia;
    }

    public void setCiCopia(byte[] ciCopia) {
        this.ciCopia = ciCopia;
    }

    public byte[] getRifCopia() {
        return rifCopia;
    }

    public void setRifCopia(byte[] rifCopia) {
        this.rifCopia = rifCopia;
    }

    public byte[] getTituloCopia() {
        return tituloCopia;
    }

    public void setTituloCopia(byte[] tituloCopia) {
        this.tituloCopia = tituloCopia;
    }
}
