package subsystems.individuos;

public class familia extends personas{
    String ceRelacionado;
    String parentesco;
    String ocupacion;
    int edad;

    public familia(String nombres, String apellidos, String ceRelacionado,
                   String parentesco, String ocupacion, int edad) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ceRelacionado = ceRelacionado;
        this.parentesco = parentesco;
        this.ocupacion = ocupacion;
        this.edad = edad;
    }

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
