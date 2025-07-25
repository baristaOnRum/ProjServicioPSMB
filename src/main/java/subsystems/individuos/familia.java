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
}
