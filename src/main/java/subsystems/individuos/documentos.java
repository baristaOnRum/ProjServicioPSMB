package subsystems.individuos;

import java.time.LocalDate;

public class documentos {
    String ce;
    boolean originales;
    boolean fotocopias;
    boolean partidaNac;
    boolean certificadoVacuna;
    boolean cedulaMadre;
    boolean cedulaPadre;
    boolean cedulaRep;
    String responsableInscripcion;
    String personaInscribe;
    String observaciones;
    LocalDate fechaInscripcion;

    documentos(String ce, boolean originales, boolean fotocopias,
               boolean partidaNac, boolean certificadoVacuna, boolean cedulaMadre,
               boolean cedulaPadre, boolean cedulaRep, String responsableInscripcion,
               String personaInscribe, String observaciones, LocalDate fechaInscripcion){

        this.ce = ce;
        this.originales = originales;
        this.fotocopias = fotocopias;
        this.partidaNac = partidaNac;
        this.certificadoVacuna = certificadoVacuna;
        this.cedulaMadre = cedulaMadre;
        this.cedulaPadre = cedulaPadre;
        this.cedulaRep = cedulaRep;
        this.responsableInscripcion = responsableInscripcion;
        this.personaInscribe = personaInscribe;
        this.observaciones = observaciones;
        this.fechaInscripcion = fechaInscripcion;

    }

}
