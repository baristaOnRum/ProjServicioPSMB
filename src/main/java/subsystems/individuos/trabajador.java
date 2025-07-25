package subsystems.individuos;

import java.time.LocalDate;

public class trabajador extends personas{

    int ci;
    int rif;
    int ciJefeClap;
    int cantPers1x10;
    String cargo;
    String titulo;
    String direccionCobro;
    String grado;
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

    public int getCi() {
        return ci;
    }

    public int getRif() {
        return rif;
    }

    public int getCiJefeClap() {
        return ciJefeClap;
    }

    public int getCantPers1x10() {
        return cantPers1x10;
    }

    public String getCargo() {
        return cargo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDireccionCobro() {
        return direccionCobro;
    }

    public String getGrado() {
        return grado;
    }

    public String getTlf1() {
        return tlf1;
    }

    public String getTlf2() {
        return tlf2;
    }

    public String getMunElec() {
        return munElec;
    }

    public String getParrqElec() {
        return parrqElec;
    }

    public String getCenVot() {
        return cenVot;
    }

    public String getMunRes() {
        return munRes;
    }

    public String getParrqRes() {
        return parrqRes;
    }

    public String getComRes() {
        return comRes;
    }

    public String getCalleRes() {
        return calleRes;
    }

    public String getNombreJefeClap() {
        return nombreJefeClap;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public boolean isTurno() {
        return turno;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public boolean isSexo() {
        return sexo;
    }

    public boolean isTiene1x10() {
        return tiene1x10;
    }

    public boolean isHaVotado() {
        return haVotado;
    }

    public byte[] getCiCopia() {
        return ciCopia;
    }

    public byte[] getRifCopia() {
        return rifCopia;
    }

    public byte[] getTituloCopia() {
        return tituloCopia;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public void setRif(int rif) {
        this.rif = rif;
    }

    public void setCiJefeClap(int ciJefeClap) {
        this.ciJefeClap = ciJefeClap;
    }

    public void setCantPers1x10(int cantPers1x10) {
        this.cantPers1x10 = cantPers1x10;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDireccionCobro(String direccionCobro) {
        this.direccionCobro = direccionCobro;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public void setTlf1(String tlf1) {
        this.tlf1 = tlf1;
    }

    public void setTlf2(String tlf2) {
        this.tlf2 = tlf2;
    }

    public void setMunElec(String munElec) {
        this.munElec = munElec;
    }

    public void setParrqElec(String parrqElec) {
        this.parrqElec = parrqElec;
    }

    public void setCenVot(String cenVot) {
        this.cenVot = cenVot;
    }

    public void setMunRes(String munRes) {
        this.munRes = munRes;
    }

    public void setParrqRes(String parrqRes) {
        this.parrqRes = parrqRes;
    }

    public void setComRes(String comRes) {
        this.comRes = comRes;
    }

    public void setCalleRes(String calleRes) {
        this.calleRes = calleRes;
    }

    public void setNombreJefeClap(String nombreJefeClap) {
        this.nombreJefeClap = nombreJefeClap;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public void setTiene1x10(boolean tiene1x10) {
        this.tiene1x10 = tiene1x10;
    }

    public void setHaVotado(boolean haVotado) {
        this.haVotado = haVotado;
    }

    public void setCiCopia(byte[] ciCopia) {
        this.ciCopia = ciCopia;
    }

    public void setRifCopia(byte[] rifCopia) {
        this.rifCopia = rifCopia;
    }

    public void setTituloCopia(byte[] tituloCopia) {
        this.tituloCopia = tituloCopia;
    }

    public trabajador(int ci, int rif, int ciJefeClap, int cantPers1x10, String nombres,
                      String apellidos, String cargo, String titulo, String direccionCobro,
                      String grado, String tlf1, String tlf2, String munElec, String parrqElec,
                      String cenVot, String munRes, String parrqRes, String comRes,
                      String calleRes, String nombreJefeClap, String observaciones,
                      LocalDate fechaIngreso, LocalDate fechaNac, boolean turno, boolean estatus,
                      boolean sexo, boolean tiene1x10, boolean haVotado, byte[] ciCopia,
                      byte[] rifCopia, byte[] tituloCopia) {
        this.ci = ci;
        this.rif = rif;
        this.ciJefeClap = ciJefeClap;
        this.cantPers1x10 = cantPers1x10;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cargo = cargo;
        this.titulo = titulo;
        this.direccionCobro = direccionCobro;
        this.grado = grado;
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
}
