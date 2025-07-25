//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import forms.*;
import subsystems.*;
import subsystems.individuos.*;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        //menuPrincipal main = new menuPrincipal();
        //file_picker main = new file_picker();
        ///planilla_inscripcion main = new planilla_inscripcion();
        //busqueda_representante main = new busqueda_representante();
        //res_busqueda_rep main = new res_busqueda_rep();
        //res_busqueda_obr main = new res_busqueda_obr();
        //conf_frame main = new conf_frame();
        //conf_users main = new conf_users();
        //menu_reportes main = new menu_reportes();

        //main.setVisible(true);
        //docGen.generarDoc();

        representante juanDoe = new representante();

        // 1. Setters heredados de la clase Personas
        juanDoe.setNombres("Maria");
        juanDoe.setApellidos("Gonzales Perez");
        juanDoe.setLugarNac("Caracas");
        juanDoe.setFechaNac(LocalDate.of(1985, 10, 25)); // Año-Mes-Día

        // 2. Setters de la clase Representante
        juanDoe.setCi(12345678); // Cédula de Identidad
        juanDoe.setEdad(38);
        juanDoe.setNinosMenor6(true); // Asumiendo que tiene niños menores de 6 años
        juanDoe.setEstadoCivil("C"); // 'C' para Casado, 'S' para Soltero, etc.
        juanDoe.setNacionalidad("Venezolana");
        juanDoe.setDireccionHab("Av. Libertador, Edif. Sol, Apto. 5B");
        juanDoe.setDireccionTrabj("Calle Comercio, Centro Empresarial, Ofic. 101");
        juanDoe.setOcupacion("Administradora");
        juanDoe.setGradoEstudios("Universitario");
        juanDoe.setTlf1("0412-1234567");
        juanDoe.setTlf2("0212-9876543");
        juanDoe.setTlfTrabajo("0212-5551234");
        juanDoe.setTlfCasa("0212-6667890");
        juanDoe.setCorreo("maria.gonzales@example.com");
        try (InputStream is = Main.class.getResourceAsStream("/iconos/set1/close-line_240.png")){
            juanDoe.setImg(utils.leerISAByteArr(is));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        connectDB.removerRepresentante(juanDoe);
    }
}