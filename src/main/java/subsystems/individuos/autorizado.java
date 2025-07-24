package subsystems.individuos;

public class autorizado extends personas{
    int ci;
    String tlf1;
    String tlf2;

    autorizado(int ci, String nombres, String apellidos, String tlf1, String tlf2){
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tlf1 = tlf1;
        this.tlf2 = tlf2;
    }
}
