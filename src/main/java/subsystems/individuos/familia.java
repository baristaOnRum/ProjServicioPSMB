package subsystems.individuos;

public class familia extends personas{
    String ceRelacionado;
    String parentesco;
    String ocupacion;
    int edad;

    public String getCeRelacionado() {
        return ceRelacionado;
    }

    public void setCeRelacionado(String ceRelacionado) {
        this.ceRelacionado = ceRelacionado;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
