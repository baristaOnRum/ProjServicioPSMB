package subsystems.individuos;

import java.time.LocalDate;

public class estudiante extends personas{
    String ce;
    String nacionalidad;
    String procedencia;
    int tallaZapato;
    int tallaCamisa;
    int tallaPantalon;
    int nivel;
    int edad;
    int estatura;
    int peso;
    int periodoCurso;
    int periodoCursado;
    byte turno;

    estudiante(String nombres, String apellidos, String lugarNac, LocalDate fechaNac,
               String ce, String nacionalidad, String procedencia, int tallaZapato,
               int tallaCamisa, int tallaPantalon, int nivel, int edad, int estatura,
               int peso, int periodoCurso, int periodoCursado, byte turno){

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

    }

}
