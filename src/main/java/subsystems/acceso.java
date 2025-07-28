package subsystems;
import java.sql.*;

public class acceso {


    //Datos acceso
    public int tipo_acceso;
    public String nombre_usuario;
    public String contrasenaHash;
    public String twoFA;

    //Datos conexión
    public String connURL;
    public String passDB;
    public String userDB;

    public int getTipo_acceso() {
        return tipo_acceso;
    }

    public void setTipo_acceso(int tipo_acceso) {
        this.tipo_acceso = tipo_acceso;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    public String getConnURL() {
        return connURL;
    }

    public void setConnURL(String connURL) {
        this.connURL = connURL;
    }

    public String getPassDB() {
        return passDB;
    }

    public void setPassDB(String passDB) {
        this.passDB = passDB;
    }

    public String getUserDB() {
        return userDB;
    }

    public void setUserDB(String userDB) {
        this.userDB = userDB;
    }

    public String getTwoFA() {
        return twoFA;
    }

    public void setTwoFA(String twoFA) {
        this.twoFA = twoFA;
    }
}
