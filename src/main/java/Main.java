//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import forms.*;
import subsystems.*;
import subsystems.individuos.*;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import subsystems.acceso;

import static java.sql.DriverManager.*;

public class Main {
    
    private static void checkInit(acceso accesoPresente, menuPrincipal menu){
        //Verificamos existencia de DB

        if (utils.obtenerVariable("db_url") == null){
            //Inicializar la creacion de el usuario de DB
            confDBFirst conf = new confDBFirst(accesoPresente, menu);
            conf.setVisible(true);
            conf.repaint();
            conf.revalidate();
        }
        else {
            //Inicializar el inicio de sesion de el usuario en la DB
            confDBF ini = new confDBF(accesoPresente, menu);
            ini.setVisible(true);
            ini.repaint();
            ini.revalidate();
        }
    }


    private static acceso setAcceso(String urlDB, String userDB, acceso acceso){
        //TODO: Llamar al inicio de sesión, devolver un acceso válido;
        acceso.setConnURL(urlDB);
        acceso.setUserDB(userDB);
        acceso.setNombre_usuario("test");
        acceso.setContrasenaHash("test");
        acceso.setTipo_acceso(4);
        //acceso.setPassDB();

        return acceso;
    }

    public static void main(String[] args) {
                menuPrincipal main = null;
                //file_picker main = new file_picker();
                //busqueda_representante main = new busqueda_representante();
                //res_busqueda_rep main = new res_busqueda_rep();
                //res_busqueda_obr main = new res_busqueda_obr();
                //conf_frame main = new conf_frame();
                //conf_users main = new conf_users();
                //menu_reportes main = new menu_reportes();
        acceso accesoPresente = new acceso();
        checkInit(accesoPresente, main);

    }
}