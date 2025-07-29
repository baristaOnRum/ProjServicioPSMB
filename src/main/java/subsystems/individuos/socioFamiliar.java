package subsystems.individuos;

public class socioFamiliar {
    private String estudiante_ciEstudiante;
    private String vivienda;
    private String tipoVivienda;
    private String cuidaNinoHogar;
    private String relacionAmbienteFamiliar;
    private String estdCivilPadres;
    private String consultaAtend;
    private boolean visitPsicopedagogo;
    private boolean visitPsicologo;
    private boolean visitNeurologo;
    private boolean visitTerapLeng;

    public String getEstudiante_ciEstudiante() {
        return estudiante_ciEstudiante;
    }

    public void setEstudiante_ciEstudiante(String estudiante_ciEstudiante) {
        this.estudiante_ciEstudiante = estudiante_ciEstudiante;
    }

    public String getVivienda() {
        return vivienda;
    }

    public void setVivienda(String vivienda) {
        this.vivienda = vivienda;
    }

    public String getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(String tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public String getCuidaNinoHogar() {
        return cuidaNinoHogar;
    }

    public void setCuidaNinoHogar(String cuidaNinoHogar) {
        this.cuidaNinoHogar = cuidaNinoHogar;
    }

    public String getRelacionAmbienteFamiliar() {
        return relacionAmbienteFamiliar;
    }

    public void setRelacionAmbienteFamiliar(String relacionAmbienteFamiliar) {
        this.relacionAmbienteFamiliar = relacionAmbienteFamiliar;
    }

    public String getEstdCivilPadres() {
        return estdCivilPadres;
    }

    public void setEstdCivilPadres(String estdCivilPadres) {
        this.estdCivilPadres = estdCivilPadres;
    }
    
    public String getConsultaAtend(){
        return consultaAtend;
    }
    
    public void setConsultaAtend(String a){
        this.consultaAtend = a;
    }

    public boolean isVisitPsicopedagogo() {
        return visitPsicopedagogo;
    }

    public void setVisitPsicopedagogo(boolean visitPsicopedagogo) {
        this.visitPsicopedagogo = visitPsicopedagogo;
    }

    public boolean isVisitPsicologo() {
        return visitPsicologo;
    }

    public void setVisitPsicologo(boolean visitPsicologo) {
        this.visitPsicologo = visitPsicologo;
    }

    public boolean isVisitNeurologo() {
        return visitNeurologo;
    }

    public void setVisitNeurologo(boolean visitNeurologo) {
        this.visitNeurologo = visitNeurologo;
    }

    public boolean isVisitTerapLeng() {
        return visitTerapLeng;
    }

    public void setVisitTerapLeng(boolean visitTerapLeng) {
        this.visitTerapLeng = visitTerapLeng;
    }
}
