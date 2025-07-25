package subsystems.individuos;

import java.time.*;
import java.sql.Time;

public class diagnostico {
    String ce;
    int edadHablar;
    int edadCaminar;
    int edadDejarPanales;
    boolean problemaParto;
    boolean problemaMotor;
    boolean problemaLenguaje;
    boolean problemaCognitivo;
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
    String cualMedicamento;
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
                       boolean problemaCognitivo, boolean alergiaMedicamento, boolean enfermedadEmbarazo,
                       boolean embarazoPlanif, boolean vacBCG, boolean vacTRIPLE, boolean vacPOLIO, boolean vacTIFUS,
                       boolean otroVacAplicadas, boolean consultaPsilg, boolean consultaPsipeg,
                       boolean consultaNeur, boolean consultaLeng, boolean consultaOtro, boolean condicionExtra,
                       boolean comeAyudado, boolean buenApetito, boolean chupaDedo, boolean seOrinaDia,
                       boolean seOrinaNoche, boolean evacuaDia, boolean pideAyudaAseo, String cualParto,
                       String cualMotor, String cualLenguaje, String cualCoginitivo, String cualMedicamento,
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
        this.cualMedicamento = cualMedicamento;
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

    public String getCualCoginitivo() {
        return cualCoginitivo;
    }

    public String getCualMedicamento() {
        return cualMedicamento;
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

    diagnostico(String ce, boolean problemaParto,
                boolean problemaMotor, boolean problemaLenguaje, boolean problemaCognitivo,
                boolean alergiaMedicamento, boolean condicionExtra, String cualParto,
                String cualMotor, String cualLenguaje, String cualCoginitivo, String cualMedicamento,
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
        this.cualMedicamento = cualMedicamento;
        this.cualExtra = cualExtra;


    }
}
