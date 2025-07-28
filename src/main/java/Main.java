//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import forms.*;
import subsystems.*;
import subsystems.individuos.*;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

public class Main {
    private static void checkInit(){
        //TODO: Checkear: Existencia de una base de datos guardada -> Existencia de usuarios en la DB,
        //                SI NO -> Solicitar enlace de conexión a DB; Verificar existencia otra vez ->
        //                Verificar usuarios; SI NO USUARIO Crear usuarios. El primer usuario siempre es Administrador
    }

    private static acceso setAcceso(acceso acceso){
        //TODO: Llamar al inicio de sesión, devolver un acceso válido;

        return acceso;
    }

    public static void main(String[] args) {
                menuPrincipal main = new menuPrincipal();
                //file_picker main = new file_picker();
                //busqueda_representante main = new busqueda_representante();
                //res_busqueda_rep main = new res_busqueda_rep();
                //res_busqueda_obr main = new res_busqueda_obr();
                //conf_frame main = new conf_frame();
                //conf_users main = new conf_users();
                //menu_reportes main = new menu_reportes();
        List<familia> list = connectDB.fetchRelFamiliar("E-10000000");
        for (familia a : list){
            System.out.println(a.getNombres());
        }
        acceso accesoPresente = new acceso();
        checkInit();
        setAcceso(accesoPresente);
        main.setVisible(true);
        
    }
}