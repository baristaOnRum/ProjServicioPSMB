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

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getTlf1() {
        return tlf1;
    }

    public void setTlf1(String tlf1) {
        this.tlf1 = tlf1;
    }

    public String getTlf2() {
        return tlf2;
    }

    public void setTlf2(String tlf2) {
        this.tlf2 = tlf2;
    }
}
