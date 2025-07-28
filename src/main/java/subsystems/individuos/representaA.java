package subsystems.individuos;

public class representaA {
    private int representante_ciRepresentante;
    private String estudiante_ciEstudiante;
    private boolean rol; // Using boolean for BIT(1)
    private String parentesco;

    public int getRepresentante_ciRepresentante() {
        return representante_ciRepresentante;
    }

    public void setRepresentante_ciRepresentante(int representante_ciRepresentante) {
        this.representante_ciRepresentante = representante_ciRepresentante;
    }

    public String getEstudiante_ciEstudiante() {
        return estudiante_ciEstudiante;
    }

    public void setEstudiante_ciEstudiante(String estudiante_ciEstudiante) {
        this.estudiante_ciEstudiante = estudiante_ciEstudiante;
    }

    public boolean isRol() {
        return rol;
    }

    public void setRol(boolean rol) {
        this.rol = rol;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }
}
