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
}
