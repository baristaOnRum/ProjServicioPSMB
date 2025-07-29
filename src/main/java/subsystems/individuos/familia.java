package subsystems.individuos;

public class familia extends personas{
    String ceRelacionado;
    String parentesco;
    String ocupacion;
    String edad;

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

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
}
