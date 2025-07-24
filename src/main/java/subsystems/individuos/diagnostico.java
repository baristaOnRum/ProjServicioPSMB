package subsystems.individuos;

public class diagnostico {
    String ce;
    boolean problemaParto;
    boolean problemaMotor;
    boolean problemaLenguaje;
    boolean problemaCognitivo;
    boolean alergiaMedicamento;
    boolean condicionExtra;
    String cualParto;
    String cualMotor;
    String cualLenguaje;
    String cualCoginitivo;
    String cualMedicamento;
    String cualExtra;

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
