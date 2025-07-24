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
