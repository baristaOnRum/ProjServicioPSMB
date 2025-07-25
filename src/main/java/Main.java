//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import forms.*;
import subsystems.*;
import subsystems.individuos.*;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

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
        juanDoe.setCi(87654321); // Cédula de Identidad
        juanDoe.setEdad(69);
        juanDoe.setNinosMenor6(true); // Asumiendo que tiene niños menores de 6 años
        juanDoe.setEstadoCivil("S"); // 'C' para Casado, 'S' para Soltero, etc.
        juanDoe.setNacionalidad("Venezolana");
        juanDoe.setDireccionHab("Av. Libertador, Edif. Sol, Apto. 6B");
        juanDoe.setDireccionTrabj("Calle Infierno, Centro Empresarial, Ofic. 101");
        juanDoe.setOcupacion("Embajador");
        juanDoe.setGradoEstudios("Universitario");
        juanDoe.setTlf1("0412-7654321");
        juanDoe.setTlf2("0212-3456789");
        juanDoe.setTlfTrabajo("0212-5551113");
        juanDoe.setTlfCasa("0212-3332323");
        juanDoe.setCorreo("juan.Doe@example.com");
        try (InputStream is = Main.class.getResourceAsStream("/iconos/set1/close-line_240.png")){
            juanDoe.setImg(utils.leerISAByteArr(is));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<representante> representantes = connectDB.buscarRepresentante("nacionalidad", "venezolana");
        System.out.println(representantes);

        System.out.println(representantes.get(0).getCi());
        System.out.println(representantes.get(0).getNacionalidad());
        //for ((representantes.get(0) : ))

    }
}