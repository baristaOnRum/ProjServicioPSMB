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

    public String getCe() {
        return ce;
    }

    public void setCe(String ce) {
        this.ce = ce;
    }

    public boolean isOriginales() {
        return originales;
    }

    public void setOriginales(boolean originales) {
        this.originales = originales;
    }

    public boolean isFotocopias() {
        return fotocopias;
    }

    public void setFotocopias(boolean fotocopias) {
        this.fotocopias = fotocopias;
    }

    public boolean isPartidaNac() {
        return partidaNac;
    }

    public void setPartidaNac(boolean partidaNac) {
        this.partidaNac = partidaNac;
    }

    public boolean isCertificadoVacuna() {
        return certificadoVacuna;
    }

    public void setCertificadoVacuna(boolean certificadoVacuna) {
        this.certificadoVacuna = certificadoVacuna;
    }

    public boolean isCedulaMadre() {
        return cedulaMadre;
    }

    public void setCedulaMadre(boolean cedulaMadre) {
        this.cedulaMadre = cedulaMadre;
    }

    public boolean isCedulaPadre() {
        return cedulaPadre;
    }

    public void setCedulaPadre(boolean cedulaPadre) {
        this.cedulaPadre = cedulaPadre;
    }

    public boolean isCedulaRep() {
        return cedulaRep;
    }

    public void setCedulaRep(boolean cedulaRep) {
        this.cedulaRep = cedulaRep;
    }

    public String getResponsableInscripcion() {
        return responsableInscripcion;
    }

    public void setResponsableInscripcion(String responsableInscripcion) {
        this.responsableInscripcion = responsableInscripcion;
    }

    public String getPersonaInscribe() {
        return personaInscribe;
    }

    public void setPersonaInscribe(String personaInscribe) {
        this.personaInscribe = personaInscribe;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
}
