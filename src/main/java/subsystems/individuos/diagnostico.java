package subsystems.individuos;

import java.time.LocalTime;

public class diagnostico {
    String ce;
    int edadHablar;
    int edadCaminar;
    int edadDejarPanales;
    boolean problemaParto;
    boolean problemaMotor;
    boolean problemaLenguaje;
    boolean problemaCognitivo;
    boolean alergia;
    boolean alergiaMedicamento;
    boolean enfermedadEmbarazo;
    boolean embarazoPlanif;
    boolean vacBCG;
    boolean vacTRIPLE;
    boolean vacPOLIO;
    boolean vacTIFUS;
    boolean otroVacAplicadas;
    boolean consultaPsilg;
    boolean consultaPsipeg;
    boolean consultaNeur;
    boolean consultaLeng;
    boolean consultaOtro;
    boolean condicionExtra;
    boolean comeAyudado;
    boolean buenApetito;
    boolean chupaDedo;
    boolean seOrinaDia;
    boolean seOrinaNoche;
    boolean evacuaDia;
    boolean pideAyudaAseo;
    String cualParto;
    String cualMotor;
    String cualLenguaje;
    String cualCoginitivo;
    String cualAlergia;
    String cualAMedicamento;
    String cualEnfEmbarazo;
    String medicamentoFiebre;
    String cualVacAplicada;
    String especifiqueConsultaOtro;
    String cualExtra;
    String enfermedadesPadecidas;
    String conQuienDuerme;
    LocalTime horaDormir;
    LocalTime horaLevantarse;

    public diagnostico(String ce, int edadHablar, int edadCaminar, int edadDejarPanales,
                       boolean problemaParto, boolean problemaMotor, boolean problemaLenguaje,
                       boolean problemaCognitivo, boolean alergia, boolean alergiaMedicamento, boolean enfermedadEmbarazo,
                       boolean embarazoPlanif, boolean vacBCG, boolean vacTRIPLE, boolean vacPOLIO, boolean vacTIFUS,
                       boolean otroVacAplicadas, boolean consultaPsilg, boolean consultaPsipeg,
                       boolean consultaNeur, boolean consultaLeng, boolean consultaOtro, boolean condicionExtra,
                       boolean comeAyudado, boolean buenApetito, boolean chupaDedo, boolean seOrinaDia,
                       boolean seOrinaNoche, boolean evacuaDia, boolean pideAyudaAseo, String cualParto,
                       String cualMotor, String cualLenguaje, String cualCoginitivo, String cualAlergia, String cualAMedicamento,
                       String cualEnfEmbarazo, String medicamentoFiebre, String cualVacAplicada,
                       String especifiqueConsultaOtro, String cualExtra, String enfermedadesPadecidas,
                       String conQuienDuerme, LocalTime horaDormir, LocalTime horaLevantarse) {
        this.ce = ce;
        this.edadHablar = edadHablar;
        this.edadCaminar = edadCaminar;
        this.edadDejarPanales = edadDejarPanales;
        this.problemaParto = problemaParto;
        this.problemaMotor = problemaMotor;
        this.problemaLenguaje = problemaLenguaje;
        this.problemaCognitivo = problemaCognitivo;
        this.alergia = alergia;
        this.alergiaMedicamento = alergiaMedicamento;
        this.enfermedadEmbarazo = enfermedadEmbarazo;
        this.embarazoPlanif = embarazoPlanif;
        this.vacBCG = vacBCG;
        this.vacTRIPLE = vacTRIPLE;
        this.vacPOLIO = vacPOLIO;
        this.vacTIFUS = vacTIFUS;
        this.otroVacAplicadas = otroVacAplicadas;
        this.consultaPsilg = consultaPsilg;
        this.consultaPsipeg = consultaPsipeg;
        this.consultaNeur = consultaNeur;
        this.consultaLeng = consultaLeng;
        this.consultaOtro = consultaOtro;
        this.condicionExtra = condicionExtra;
        this.comeAyudado = comeAyudado;
        this.buenApetito = buenApetito;
        this.chupaDedo = chupaDedo;
        this.seOrinaDia = seOrinaDia;
        this.seOrinaNoche = seOrinaNoche;
        this.evacuaDia = evacuaDia;
        this.pideAyudaAseo = pideAyudaAseo;
        this.cualParto = cualParto;
        this.cualMotor = cualMotor;
        this.cualLenguaje = cualLenguaje;
        this.cualCoginitivo = cualCoginitivo;
        this.cualAlergia = cualAlergia;
        this.cualAMedicamento = cualAMedicamento;
        this.cualEnfEmbarazo = cualEnfEmbarazo;
        this.medicamentoFiebre = medicamentoFiebre;
        this.cualVacAplicada = cualVacAplicada;
        this.especifiqueConsultaOtro = especifiqueConsultaOtro;
        this.cualExtra = cualExtra;
        this.enfermedadesPadecidas = enfermedadesPadecidas;
        this.conQuienDuerme = conQuienDuerme;
        this.horaDormir = horaDormir;
        this.horaLevantarse = horaLevantarse;
    }

    public String getCe() {
        return ce;
    }

    public int getEdadHablar() {
        return edadHablar;
    }

    public int getEdadCaminar() {
        return edadCaminar;
    }

    public int getEdadDejarPanales() {
        return edadDejarPanales;
    }

    public boolean isProblemaParto() {
        return problemaParto;
    }

    public boolean isProblemaMotor() {
        return problemaMotor;
    }

    public boolean isProblemaLenguaje() {
        return problemaLenguaje;
    }

    public boolean isProblemaCognitivo() {
        return problemaCognitivo;
    }

    public boolean isAlergia(){
        return alergia;
    }

    public boolean isAlergiaMedicamento() {
        return alergiaMedicamento;
    }

    public boolean isEnfermedadEmbarazo() {
        return enfermedadEmbarazo;
    }

    public boolean isEmbarazoPlanif() {
        return embarazoPlanif;
    }

    public boolean isVacBCG() {
        return vacBCG;
    }

    public boolean isVacTRIPLE() {
        return vacTRIPLE;
    }

    public boolean isVacPOLIO() {
        return vacPOLIO;
    }

    public boolean isVacTIFUS() {
        return vacTIFUS;
    }

    public boolean isOtroVacAplicadas() {
        return otroVacAplicadas;
    }

    public boolean isConsultaPsilg() {
        return consultaPsilg;
    }

    public boolean isConsultaPsipeg() {
        return consultaPsipeg;
    }

    public boolean isConsultaNeur() {
        return consultaNeur;
    }

    public boolean isConsultaLeng() {
        return consultaLeng;
    }

    public boolean isConsultaOtro() {
        return consultaOtro;
    }

    public boolean isCondicionExtra() {
        return condicionExtra;
    }

    public boolean isComeAyudado() {
        return comeAyudado;
    }

    public boolean isBuenApetito() {
        return buenApetito;
    }

    public boolean isChupaDedo() {
        return chupaDedo;
    }

    public boolean isSeOrinaDia() {
        return seOrinaDia;
    }

    public boolean isSeOrinaNoche() {
        return seOrinaNoche;
    }

    public boolean isEvacuaDia() {
        return evacuaDia;
    }

    public boolean isPideAyudaAseo() {
        return pideAyudaAseo;
    }

    public String getCualParto() {
        return cualParto;
    }

    public String getCualMotor() {
        return cualMotor;
    }

    public String getCualLenguaje() {
        return cualLenguaje;
    }

    public String getCualCognitivo() {
        return cualCoginitivo;
    }

    public String getCualAlergia() {
        return cualAlergia;
    }

    public String getCualAMedicamento() {
        return cualAMedicamento;
    }

    public String getCualEnfEmbarazo() {
        return cualEnfEmbarazo;
    }

    public String getMedicamentoFiebre() {
        return medicamentoFiebre;
    }

    public String getCualVacAplicada() {
        return cualVacAplicada;
    }

    public String getEspecifiqueConsultaOtro() {
        return especifiqueConsultaOtro;
    }

    public String getCualExtra() {
        return cualExtra;
    }

    public String getEnfermedadesPadecidas() {
        return enfermedadesPadecidas;
    }

    public String getConQuienDuerme() {
        return conQuienDuerme;
    }

    public LocalTime getHoraDormir() {
        return horaDormir;
    }

    public LocalTime getHoraLevantarse() {
        return horaLevantarse;
    }

    public void setCe(String ce) {
        this.ce = ce;
    }

    public void setEdadHablar(int edadHablar) {
        this.edadHablar = edadHablar;
    }

    public void setEdadCaminar(int edadCaminar) {
        this.edadCaminar = edadCaminar;
    }

    public void setEdadDejarPanales(int edadDejarPanales) {
        this.edadDejarPanales = edadDejarPanales;
    }

    public void setProblemaParto(boolean problemaParto) {
        this.problemaParto = problemaParto;
    }

    public void setProblemaMotor(boolean problemaMotor) {
        this.problemaMotor = problemaMotor;
    }

    public void setProblemaLenguaje(boolean problemaLenguaje) {
        this.problemaLenguaje = problemaLenguaje;
    }

    public void setProblemaCognitivo(boolean problemaCognitivo) {
        this.problemaCognitivo = problemaCognitivo;
    }

    public void setAlergiaMedicamento(boolean alergiaMedicamento) {
        this.alergiaMedicamento = alergiaMedicamento;
    }

    public void setEnfermedadEmbarazo(boolean enfermedadEmbarazo) {
        this.enfermedadEmbarazo = enfermedadEmbarazo;
    }

    public void setEmbarazoPlanif(boolean embarazoPlanif) {
        this.embarazoPlanif = embarazoPlanif;
    }

    public void setVacBCG(boolean vacBCG) {
        this.vacBCG = vacBCG;
    }

    public void setVacTRIPLE(boolean vacTRIPLE) {
        this.vacTRIPLE = vacTRIPLE;
    }

    public void setVacPOLIO(boolean vacPOLIO) {
        this.vacPOLIO = vacPOLIO;
    }

    public void setVacTIFUS(boolean vacTIFUS) {
        this.vacTIFUS = vacTIFUS;
    }

    public void setOtroVacAplicadas(boolean otroVacAplicadas) {
        this.otroVacAplicadas = otroVacAplicadas;
    }

    public void setConsultaPsilg(boolean consultaPsilg) {
        this.consultaPsilg = consultaPsilg;
    }

    public void setConsultaPsipeg(boolean consultaPsipeg) {
        this.consultaPsipeg = consultaPsipeg;
    }

    public void setConsultaNeur(boolean consultaNeur) {
        this.consultaNeur = consultaNeur;
    }

    public void setConsultaLeng(boolean consultaLeng) {
        this.consultaLeng = consultaLeng;
    }

    public void setConsultaOtro(boolean consultaOtro) {
        this.consultaOtro = consultaOtro;
    }

    public void setCondicionExtra(boolean condicionExtra) {
        this.condicionExtra = condicionExtra;
    }

    public void setComeAyudado(boolean comeAyudado) {
        this.comeAyudado = comeAyudado;
    }

    public void setBuenApetito(boolean buenApetito) {
        this.buenApetito = buenApetito;
    }

    public void setChupaDedo(boolean chupaDedo) {
        this.chupaDedo = chupaDedo;
    }

    public void setSeOrinaDia(boolean seOrinaDia) {
        this.seOrinaDia = seOrinaDia;
    }

    public void setSeOrinaNoche(boolean seOrinaNoche) {
        this.seOrinaNoche = seOrinaNoche;
    }

    public void setEvacuaDia(boolean evacuaDia) {
        this.evacuaDia = evacuaDia;
    }

    public void setPideAyudaAseo(boolean pideAyudaAseo) {
        this.pideAyudaAseo = pideAyudaAseo;
    }

    public void setCualParto(String cualParto) {
        this.cualParto = cualParto;
    }

    public void setCualMotor(String cualMotor) {
        this.cualMotor = cualMotor;
    }

    public void setCualLenguaje(String cualLenguaje) {
        this.cualLenguaje = cualLenguaje;
    }

    public void setCualCoginitivo(String cualCoginitivo) {
        this.cualCoginitivo = cualCoginitivo;
    }

    public void setCualAlergia(String cualAlergia) {
        this.cualAlergia = cualAlergia;
    }

    public void setCualAMedicamento(String cualAMedicamento) {
        this.cualAMedicamento = cualAMedicamento;
    }

    public void setCualEnfEmbarazo(String cualEnfEmbarazo) {
        this.cualEnfEmbarazo = cualEnfEmbarazo;
    }

    public void setMedicamentoFiebre(String medicamentoFiebre) {
        this.medicamentoFiebre = medicamentoFiebre;
    }

    public void setCualVacAplicada(String cualVacAplicada) {
        this.cualVacAplicada = cualVacAplicada;
    }

    public void setEspecifiqueConsultaOtro(String especifiqueConsultaOtro) {
        this.especifiqueConsultaOtro = especifiqueConsultaOtro;
    }

    public void setCualExtra(String cualExtra) {
        this.cualExtra = cualExtra;
    }

    public void setEnfermedadesPadecidas(String enfermedadesPadecidas) {
        this.enfermedadesPadecidas = enfermedadesPadecidas;
    }

    public void setConQuienDuerme(String conQuienDuerme) {
        this.conQuienDuerme = conQuienDuerme;
    }

    public void setHoraDormir(LocalTime horaDormir) {
        this.horaDormir = horaDormir;
    }

    public void setHoraLevantarse(LocalTime horaLevantarse) {
        this.horaLevantarse = horaLevantarse;
    }

    diagnostico(String ce, boolean problemaParto,
                boolean problemaMotor, boolean problemaLenguaje, boolean problemaCognitivo,
                boolean alergiaMedicamento, boolean condicionExtra, String cualParto,
                String cualMotor, String cualLenguaje, String cualCoginitivo, String cualAMedicamento,
                String cualExtra){
        this.ce = ce;
        this.problemaParto = problemaParto;
        this.problemaMotor = problemaMotor;
        this.problemaLenguaje = problemaLenguaje;
        this.problemaCognitivo = problemaCognitivo;
        this.alergiaMedicamento = alergiaMedicamento;
        this.condicionExtra = condicionExtra;
        this.cualParto = cualParto;
        this.cualMotor = cualMotor;
        this.cualLenguaje = cualLenguaje;
        this.cualCoginitivo = cualCoginitivo;
        this.cualAMedicamento = cualAMedicamento;
        this.cualExtra = cualExtra;


    }
}
