package forms;

import java.awt.Component;
import java.awt.Image;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import subsystems.*;
import subsystems.individuos.*;
//import forms.pops.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class customCellRenderer implements TableCellRenderer{
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        table.setRowHeight(100);
        return (Component) value;
    }


}
public class menuPrincipal extends javax.swing.JFrame {
    
private static String cEstudiante;
ButtonGroup procedGroup = new ButtonGroup();
ButtonGroup sexGroup = new ButtonGroup();
ButtonGroup lateralidadGroup = new ButtonGroup();
ButtonGroup embPlanif = new ButtonGroup();
ButtonGroup probParto = new ButtonGroup();
ButtonGroup probMotor = new ButtonGroup();
ButtonGroup probLenguaje = new ButtonGroup();
ButtonGroup probCogn = new ButtonGroup();
ButtonGroup tieneAlerg = new ButtonGroup();
ButtonGroup AlergMed = new ButtonGroup();
ButtonGroup condicionGroup = new ButtonGroup();
ButtonGroup comeAyud = new ButtonGroup();
ButtonGroup buenApetit = new ButtonGroup();
ButtonGroup duermeTard = new ButtonGroup();
ButtonGroup seOrinaDia = new ButtonGroup();
ButtonGroup evacuaDia = new ButtonGroup();
ButtonGroup aseaSolo = new ButtonGroup();
ButtonGroup seOrinaNoche = new ButtonGroup();
ButtonGroup socFamEstiloCasa = new ButtonGroup();
ButtonGroup socFamTipoCasa = new ButtonGroup();
ButtonGroup socFamEstdCiv = new ButtonGroup();
ButtonGroup chupaDedo = new ButtonGroup();
ButtonGroup repLegalEstdCiv = new ButtonGroup();
ButtonGroup repLegalNac = new ButtonGroup();
ButtonGroup repLegalInstrucc = new ButtonGroup();
ButtonGroup madreLegalEstdCiv = new ButtonGroup();
ButtonGroup madreLegalNac = new ButtonGroup();
ButtonGroup madreLegalInstrucc = new ButtonGroup();
ButtonGroup padreLegalEstdCiv = new ButtonGroup();
ButtonGroup padreLegalInstrucc = new ButtonGroup();

byte[] fileBytes = null;
byte[] fileBytesMad = null;
byte[] fileBytesPadre = null;
byte[] fileBytesRep = null;

    
private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(menuPrincipal.class.getName());

public static boolean validateAndNotify(Component parentComponent, LinkedHashMap<String, String> map){
    
    //Chequeamos la data
        
        List<String> missingFields = new ArrayList<>();

        // Iterate through the map and check each field
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String fieldName = entry.getKey();
            String fieldValue = entry.getValue();

            // Check if the field is null or empty after trimming
            if (fieldValue == null || fieldValue.trim().isEmpty()) {
                missingFields.add(fieldName);
            }
        }

        // If there are missing fields, construct and display the message
        if (!missingFields.isEmpty()) {
            StringBuilder message = new StringBuilder("Los siguientes campos son obligatorios y están vacíos:\n\n");
            for (String field : missingFields) {
                message.append("- ").append(field).append("\n");
            }
            message.append("\nPor favor, complete la información para continuar.");

            JOptionPane.showMessageDialog(parentComponent, message.toString(), "Campos Obligatorios Faltantes", JOptionPane.WARNING_MESSAGE);
            return false;
        }    
    return true;
}

    private String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText(); // Return the text of the selected button
            }
        }
        return null; // No button is selected in the group
    }


public menuPrincipal(acceso accesoPresente) {
    initComponents();
            //Creando ButtonGroups

            //Personal
            procedGroup.add(radio_hogar_niñ);
            procedGroup.add(radio_multihogar_niñ);
            procedGroup.add(radio_hogar_cuidado_niñ);
            procedGroup.add(radio_mismo_plantel_niñ);
            procedGroup.add(radio_otro_plantel_niñ);
            procedGroup.add(radio_guarderia_niñ);
            procedGroup.add(radio_otros_niñ);

            sexGroup.add(rdb_mascNiñ);
            sexGroup.add(rdb_femNiñ);

            lateralidadGroup.add(rdo_diestro);
            lateralidadGroup.add(rdo_zurdo);
            lateralidadGroup.add(rdo_ambidiestro);
            lateralidadGroup.add(rdo_desconocido);

            //Salud
            embPlanif.add(rdo_siEmbPLan);
            embPlanif.add(rdo_noEmbPlan);

            probParto.add(rdo_siPrmParto);
            probParto.add(rdo_noPrmParto);

            probMotor.add(rdo_siPromMotr);
            probMotor.add(rdo_noPrmMotr);

            probLenguaje.add(rdo_siPromLeng);
            probLenguaje.add(rdo_noPrmLeng);

            probCogn.add(rdo_siPromCog);
            probCogn.add(rdo_noPromCog);

            tieneAlerg.add(rdo_siAlergia);
            tieneAlerg.add(rdo_noAlergia);

            AlergMed.add(rdo_siAlergiaMed);
            AlergMed.add(rdo_noAlergiaMed);

            condicionGroup.add(rdo_siCondExtra);
            condicionGroup.add(rdo_noCondExtra);

            //Otros

            comeAyud.add(rdo_siCmeAyda);
            comeAyud.add(rdo_noCmeAyda);


            buenApetit.add(rdo_siBuenApetito);
            buenApetit.add(rdo_noBuenApetito);

            duermeTard.add(rdo_duermTardeSi);
            duermeTard.add(rdo_duermTardeNo);
            duermeTard.add(rdo_duermTardeAveces);

            chupaDedo.add(lbl_chupaDedoSi);
            chupaDedo.add(lbl_chupaDedoNo);
            chupaDedo.add(lbl_chupaDedoAvece);


            seOrinaDia.add(rdo_orinaRopaDiaSi);
            seOrinaDia.add(rdo_orinaRopaDiaNo);
            seOrinaDia.add(rdo_orinaRopaDiaAvece);


            seOrinaNoche.add(rdo_orinaRopaNocheSi);
            seOrinaNoche.add(rdo_orinaRopaNocheNo);
            seOrinaNoche.add(rdo_orinaRopaNocheAvece);


            evacuaDia.add(rdo_evacDiaSi);
            evacuaDia.add(rdo_evacDiaNo);
            evacuaDia.add(rdo_evacDiaAvece);


            aseaSolo.add(rdo_aseaSoloSi);
            aseaSolo.add(rdo_aseaSoloNo);

            //Socio-familiar


            socFamEstiloCasa.add(rdio_alquilada);
            socFamEstiloCasa.add(rdio_prestada);
            socFamEstiloCasa.add(rdio_propia);


            socFamTipoCasa.add(rdio_casa);
            socFamTipoCasa.add(rdio_apartamento);
            socFamTipoCasa.add(rdio_zinc);
            socFamTipoCasa.add(rdio_otro);
            
            socFamEstdCiv.add(check_s_padres);
            socFamEstdCiv.add(check_c_padres);
            socFamEstdCiv.add(check_d_padres);
            socFamEstdCiv.add(check_v_padres);

            //Representante legal

            repLegalEstdCiv.add(check_s_rep);
            repLegalEstdCiv.add(check_c_rep);
            repLegalEstdCiv.add(check_d_rep);
            repLegalEstdCiv.add(check_v_rep);

            repLegalNac.add(check_ven_rep);
            repLegalNac.add(check_ext_rep);

            repLegalInstrucc.add(check_no_bachiller_rep);
            repLegalInstrucc.add(chec_bachiller_rep);
            repLegalInstrucc.add(check_tsu_rep);
            repLegalInstrucc.add(check_superior_rep);

            //Madre

            madreLegalEstdCiv.add(check_s_madre);
            madreLegalEstdCiv.add(check_c_madre);
            madreLegalEstdCiv.add(check_d_madre);
            madreLegalEstdCiv.add(check_v_madre);

            madreLegalNac.add(check_ven_madre);
            madreLegalNac.add(check_ext_madre);

            madreLegalInstrucc.add(check_no_bachiller_madre);
            madreLegalInstrucc.add(chec_bachiller_madre);
            madreLegalInstrucc.add(check_tsu_madre);
            madreLegalInstrucc.add(check_superior_madre);

            //Padre

            padreLegalEstdCiv.add(check_s_padre);
            padreLegalEstdCiv.add(check_c_padre);
            padreLegalEstdCiv.add(check_d_padre);
            padreLegalEstdCiv.add(check_v_padre);
            ButtonGroup padreLegalNac = new ButtonGroup();
            padreLegalNac.add(check_ven_padre);
            padreLegalNac.add(check_ext_padre);

            padreLegalInstrucc.add(check_no_bachiller_padre);
            padreLegalInstrucc.add(chec_bachiller_padre);
            padreLegalInstrucc.add(check_tsu_padre);
            padreLegalInstrucc.add(check_superior_padre);

    panel.add(principal);
    
    
    //los accesos se dan desde el menu de busqueda, se limita la informacion que pueden usar los usuarios
    acceso accs = new acceso();

   switch(accs.getTipo_acceso()){
       case 1 -> {
           btn_inscribirEstd.setEnabled(false);
           btn_inscribirNom.setEnabled(false);
           //solo ver
       }
       case 2 -> {
           btn_inscribirNom.setEnabled(false);
           //solo inscribir estudiantes
       }
       case 3 ->{
           //edicion total
       }
 }
     
    
}

public void createConfPanel(){
  //  conf_frame conf_main = new conf_frame();
  //  conf_main.setVisible(true);
}

/**
 * This method is called from within the constructor to initialize the form.
 * WARNING: Do NOT modify this code. The content of this method is always
 * regenerated by the Form Editor.
 */
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        principal = new javax.swing.JPanel();
        headderMenu = new javax.swing.JPanel();
        txtMenuPrincipal1 = new javax.swing.JLabel();
        logoPreescolar = new javax.swing.JLabel();
        imgHerramienta = new javax.swing.JLabel();
        btnConfiguracion = new javax.swing.JButton();
        imgEstudiante = new javax.swing.JLabel();
        btnEstudiantes = new javax.swing.JButton();
        imgFamilia = new javax.swing.JLabel();
        btnRepresentantes = new javax.swing.JButton();
        imgAutorizado = new javax.swing.JLabel();
        btnAutorizados = new javax.swing.JButton();
        busquedaEstudiantes = new javax.swing.JPanel();
        headderBusqEst = new javax.swing.JPanel();
        lbl_busqEstudiante = new javax.swing.JLabel();
        btn_cerrarBusqEst = new javax.swing.JButton();
        btn_volverBusqEst = new javax.swing.JButton();
        cmb_filtroBusqEstd = new javax.swing.JComboBox<>();
        txt_filtroBusqEstd = new javax.swing.JTextField();
        scrl_busqEstd = new javax.swing.JScrollPane();
        tbl_busqEstd = new javax.swing.JTable();
        img_busqEstd = new javax.swing.JLabel();
        btn_inscribirEstd = new javax.swing.JButton();
        btn_verBusqEstd = new javax.swing.JButton();
        img_logoInstitucionBusqEstd = new javax.swing.JLabel();
        btn_buscarEstd = new javax.swing.JButton();
        btn_eliminarBusqEstd = new javax.swing.JButton();
        btn_eliminarBusqEstd1 = new javax.swing.JButton();
        busquedaRepresentante = new javax.swing.JPanel();
        headderBusqRep = new javax.swing.JPanel();
        lbl_busqRepresentante = new javax.swing.JLabel();
        btn_cerrarBusqRep = new javax.swing.JButton();
        btn_volverBusqRep = new javax.swing.JButton();
        cmb_filtroBusqRep = new javax.swing.JComboBox<>();
        txt_filtroBusqRep = new javax.swing.JTextField();
        scrl_busqRep = new javax.swing.JScrollPane();
        tbl_busqRep = new javax.swing.JTable();
        img_busqRep = new javax.swing.JLabel();
        btn_verBusqRep = new javax.swing.JButton();
        img_logoInstitucionBusqRep = new javax.swing.JLabel();
        btn_buscarRep = new javax.swing.JButton();
        busquedaAutorizados = new javax.swing.JPanel();
        headderBusqAut = new javax.swing.JPanel();
        lbl_busqAutorizado = new javax.swing.JLabel();
        btn_cerrarBusqAut = new javax.swing.JButton();
        btn_volverBusqAut = new javax.swing.JButton();
        cmb_filtroBusqAut = new javax.swing.JComboBox<>();
        txt_filtroBusqAut = new javax.swing.JTextField();
        scrl_busqAut = new javax.swing.JScrollPane();
        tbl_busqAut = new javax.swing.JTable();
        btn_verBusqAut = new javax.swing.JButton();
        btn_buscarAut = new javax.swing.JButton();
        busquedaNomina = new javax.swing.JPanel();
        headderBusqNomina = new javax.swing.JPanel();
        lbl_busqNomina = new javax.swing.JLabel();
        btn_volverBusqNom = new javax.swing.JButton();
        btn_cerrarBusqNom = new javax.swing.JButton();
        cmb_filtroBusqNom = new javax.swing.JComboBox<>();
        txt_filtroBusqNom = new javax.swing.JTextField();
        scl_busqNom = new javax.swing.JScrollPane();
        tbl_busqNomina = new javax.swing.JTable();
        img_busqNomina = new javax.swing.JLabel();
        btn_inscribirNom = new javax.swing.JButton();
        btn_verNom = new javax.swing.JButton();
        btn_editarBusqNom = new javax.swing.JButton();
        im_logoInstitucionBusqNom = new javax.swing.JLabel();
        btn_buscarNomina = new javax.swing.JButton();
        informacionEstudiante = new javax.swing.JPanel();
        headderEstudiante = new javax.swing.JPanel();
        lbl_headderEstudiante = new javax.swing.JLabel();
        btn_repEstd = new javax.swing.JButton();
        btn_repEstd1 = new javax.swing.JButton();
        img_infEstudiante = new javax.swing.JLabel();
        lbl_ce = new javax.swing.JLabel();
        panelDatosEstd = new javax.swing.JTabbedPane();
        datosEstd = new javax.swing.JPanel();
        lbl_nombreNiñ = new javax.swing.JLabel();
        txt_nombreNiñ = new javax.swing.JTextField();
        lbl_apellidoNiñ = new javax.swing.JLabel();
        txt_apellidoNiñ = new javax.swing.JTextField();
        lbl_edadNiñ = new javax.swing.JLabel();
        lbl_sexoNiñ = new javax.swing.JLabel();
        rdb_mascNiñ = new javax.swing.JRadioButton();
        rdb_femNiñ = new javax.swing.JRadioButton();
        lbl_lugarNacNiñ = new javax.swing.JLabel();
        txt_lugarNacNiñ = new javax.swing.JTextField();
        lbl_munNacNiñ = new javax.swing.JLabel();
        txt_municNiñ = new javax.swing.JTextField();
        lbl_estadoNiñ = new javax.swing.JLabel();
        txt_estadoNiñ = new javax.swing.JTextField();
        lbl_nacionalidadNiñ = new javax.swing.JLabel();
        txt_nacionalidadNiñ = new javax.swing.JTextField();
        label_edad_niñ = new javax.swing.JLabel();
        text_edad_niñ = new javax.swing.JTextField();
        label_procedencia_niñ = new javax.swing.JLabel();
        radio_hogar_niñ = new javax.swing.JRadioButton();
        radio_mismo_plantel_niñ = new javax.swing.JRadioButton();
        radio_multihogar_niñ = new javax.swing.JRadioButton();
        radio_otro_plantel_niñ = new javax.swing.JRadioButton();
        radio_hogar_cuidado_niñ = new javax.swing.JRadioButton();
        radio_guarderia_niñ = new javax.swing.JRadioButton();
        radio_otros_niñ = new javax.swing.JRadioButton();
        label_tallas_niñ = new javax.swing.JLabel();
        label_camisa_niñ = new javax.swing.JLabel();
        text_tall_camisa_niñ = new javax.swing.JTextField();
        label_tall_pantalon_niñ = new javax.swing.JLabel();
        text_tall_pantalon_niñ = new javax.swing.JTextField();
        label_tall_zapato_niñ = new javax.swing.JLabel();
        text_tall_zapato_niñ = new javax.swing.JTextField();
        label_medidas_niñ = new javax.swing.JLabel();
        label_peso_niñ = new javax.swing.JLabel();
        text_peso_niñ = new javax.swing.JTextField();
        label_estatura_niñ = new javax.swing.JLabel();
        text_estatura_niñ = new javax.swing.JTextField();
        lbl_edadCaminar = new javax.swing.JLabel();
        txt_edadCaminar = new javax.swing.JTextField();
        lbl_lateralidad = new javax.swing.JLabel();
        rdo_diestro = new javax.swing.JRadioButton();
        rdo_zurdo = new javax.swing.JRadioButton();
        rdo_ambidiestro = new javax.swing.JRadioButton();
        rdo_desconocido = new javax.swing.JRadioButton();
        diaNacNiñField = new javax.swing.JTextField();
        mesNacNiñField = new javax.swing.JTextField();
        añoNacNiñField = new javax.swing.JTextField();
        diaNacNiñLbl = new javax.swing.JLabel();
        mesNacNiñLbl = new javax.swing.JLabel();
        añoNacNiñLbl = new javax.swing.JLabel();
        diagEstd = new javax.swing.JPanel();
        lbl_prmParto = new javax.swing.JLabel();
        rdo_noPrmParto = new javax.swing.JRadioButton();
        rdo_siPrmParto = new javax.swing.JRadioButton();
        lbl_promPartoCual = new javax.swing.JLabel();
        txt_prmPartocual = new javax.swing.JTextField();
        lbl_prbDesarr = new javax.swing.JLabel();
        lbl_motor = new javax.swing.JLabel();
        rdo_noPrmMotr = new javax.swing.JRadioButton();
        rdo_siPromMotr = new javax.swing.JRadioButton();
        label_motor_cual = new javax.swing.JLabel();
        text_motor_cual = new javax.swing.JTextField();
        label_lenguaje = new javax.swing.JLabel();
        rdo_noPrmLeng = new javax.swing.JRadioButton();
        rdo_siPromLeng = new javax.swing.JRadioButton();
        label_lenguaje_cual = new javax.swing.JLabel();
        text_lenguaje_cual = new javax.swing.JTextField();
        label_cognitivo = new javax.swing.JLabel();
        rdo_noPromCog = new javax.swing.JRadioButton();
        rdo_siPromCog = new javax.swing.JRadioButton();
        label_cognitivo_cual = new javax.swing.JLabel();
        text_cognitivo_cual = new javax.swing.JTextField();
        label_alergia = new javax.swing.JLabel();
        rdo_noAlergia = new javax.swing.JRadioButton();
        rdo_siAlergia = new javax.swing.JRadioButton();
        label_alergia_cual = new javax.swing.JLabel();
        text_alergia_cual = new javax.swing.JTextField();
        label_alergiaMed = new javax.swing.JLabel();
        rdo_noAlergiaMed = new javax.swing.JRadioButton();
        rdo_siAlergiaMed = new javax.swing.JRadioButton();
        label_alergiaMed_cual = new javax.swing.JLabel();
        text_alergia_cual1 = new javax.swing.JTextField();
        label_condicion = new javax.swing.JLabel();
        rdo_noCondExtra = new javax.swing.JRadioButton();
        rdo_siCondExtra = new javax.swing.JRadioButton();
        label_condicion_cual = new javax.swing.JLabel();
        text_condicion_cual = new javax.swing.JTextField();
        lbl_embPlan = new javax.swing.JLabel();
        rdo_siEmbPLan = new javax.swing.JRadioButton();
        rdo_noEmbPlan = new javax.swing.JRadioButton();
        lbl_enfPadecidas = new javax.swing.JLabel();
        txt_enfPadecidas = new javax.swing.JTextField();
        lbl_grpoSang = new javax.swing.JLabel();
        txt_grpoSang = new javax.swing.JTextField();
        lbl_fiebreAlta = new javax.swing.JLabel();
        txt_fiebreAlta = new javax.swing.JTextField();
        lbl_vac = new javax.swing.JLabel();
        chk_bcg = new javax.swing.JCheckBox();
        chk_triple = new javax.swing.JCheckBox();
        chk_polio = new javax.swing.JCheckBox();
        chk_tifus = new javax.swing.JCheckBox();
        chk_viruela = new javax.swing.JCheckBox();
        lbl_otraVac = new javax.swing.JLabel();
        txt_otraVac = new javax.swing.JTextField();
        lbl_medicTratante = new javax.swing.JLabel();
        txt_medicoTratante = new javax.swing.JTextField();
        txt_tlfMedicoTrat = new javax.swing.JTextField();
        lbl_tlfMedicTrat = new javax.swing.JLabel();
        otros = new javax.swing.JPanel();
        lbl_cmeAyda = new javax.swing.JLabel();
        rdo_siCmeAyda = new javax.swing.JRadioButton();
        rdo_noCmeAyda = new javax.swing.JRadioButton();
        lbl_buenAptito = new javax.swing.JLabel();
        rdo_siBuenApetito = new javax.swing.JRadioButton();
        rdo_noBuenApetito = new javax.swing.JRadioButton();
        lbl_horaAcostar = new javax.swing.JLabel();
        lbl_horaLevantar = new javax.swing.JLabel();
        lbl_duermTarde = new javax.swing.JLabel();
        rdo_duermTardeSi = new javax.swing.JRadioButton();
        rdo_duermTardeNo = new javax.swing.JRadioButton();
        rdo_duermTardeAveces = new javax.swing.JRadioButton();
        lbl_qnDuermeNino = new javax.swing.JLabel();
        txt_qnDuermeNino = new javax.swing.JTextField();
        lbl_chupaDedo = new javax.swing.JLabel();
        lbl_chupaDedoSi = new javax.swing.JRadioButton();
        lbl_chupaDedoNo = new javax.swing.JRadioButton();
        lbl_chupaDedoAvece = new javax.swing.JRadioButton();
        lbl_edadDejPan = new javax.swing.JLabel();
        txt_edadDejPan = new javax.swing.JTextField();
        lbl_orinaRopaDia = new javax.swing.JLabel();
        rdo_orinaRopaDiaSi = new javax.swing.JRadioButton();
        rdo_orinaRopaDiaNo = new javax.swing.JRadioButton();
        rdo_orinaRopaDiaAvece = new javax.swing.JRadioButton();
        lbl_orinaRopaNoch = new javax.swing.JLabel();
        rdo_orinaRopaNocheAvece = new javax.swing.JRadioButton();
        rdo_orinaRopaNocheNo = new javax.swing.JRadioButton();
        rdo_orinaRopaNocheSi = new javax.swing.JRadioButton();
        lbl_evacDia = new javax.swing.JLabel();
        rdo_evacDiaSi = new javax.swing.JRadioButton();
        rdo_evacDiaNo = new javax.swing.JRadioButton();
        rdo_evacDiaAvece = new javax.swing.JRadioButton();
        lbl_aseaSolo = new javax.swing.JLabel();
        rdo_aseaSoloSi = new javax.swing.JRadioButton();
        rdo_aseaSoloNo = new javax.swing.JRadioButton();
        cBoxHoraAc = new javax.swing.JComboBox<>();
        cBoxMinAc = new javax.swing.JComboBox<>();
        cBoxHoraLev = new javax.swing.JComboBox<>();
        cBoxMinLev = new javax.swing.JComboBox<>();
        lbl_horaAcostar1 = new javax.swing.JLabel();
        lbl_horaAcostar2 = new javax.swing.JLabel();
        lbl_horaAcostar3 = new javax.swing.JLabel();
        lbl_horaAcostar4 = new javax.swing.JLabel();
        panelRepresentanteEstd = new javax.swing.JTabbedPane();
        dat_madre = new javax.swing.JPanel();
        label_apellido_madre = new javax.swing.JLabel();
        text_apellidos_madre = new javax.swing.JTextField();
        label_nombes_madre = new javax.swing.JLabel();
        text_nombres_madre = new javax.swing.JTextField();
        label_ci_madre = new javax.swing.JLabel();
        text_ci_madre = new javax.swing.JTextField();
        label_estadociv_madre = new javax.swing.JLabel();
        check_s_madre = new javax.swing.JCheckBox();
        check_c_madre = new javax.swing.JCheckBox();
        check_d_madre = new javax.swing.JCheckBox();
        check_v_madre = new javax.swing.JCheckBox();
        label_fec_nac_madre = new javax.swing.JLabel();
        label_edad_madre = new javax.swing.JLabel();
        text_edad_madre = new javax.swing.JTextField();
        label_lugar_nac_madre = new javax.swing.JLabel();
        text_lugar_nac_madre = new javax.swing.JTextField();
        label_nacionalidad_madre = new javax.swing.JLabel();
        check_ven_madre = new javax.swing.JCheckBox();
        check_ext_madre = new javax.swing.JCheckBox();
        label_direc_hab_madre = new javax.swing.JLabel();
        text_direc_hab_madre = new javax.swing.JTextField();
        label_grado_madre = new javax.swing.JLabel();
        check_no_bachiller_madre = new javax.swing.JCheckBox();
        chec_bachiller_madre = new javax.swing.JCheckBox();
        check_tsu_madre = new javax.swing.JCheckBox();
        check_superior_madre = new javax.swing.JCheckBox();
        label_ocupacion_madre = new javax.swing.JLabel();
        text_ocupacion_madre = new javax.swing.JTextField();
        label_direc_trabaj_madre = new javax.swing.JLabel();
        text_direc_trabj_madre = new javax.swing.JTextField();
        label_correo_madre = new javax.swing.JLabel();
        text_correo_madre = new javax.swing.JTextField();
        label_tlf_madre = new javax.swing.JLabel();
        text_tlf_madre = new javax.swing.JTextField();
        repLegalLabel = new javax.swing.JLabel();
        repLegalMadreCheckbox = new javax.swing.JCheckBox();
        parentescoMadreTexto = new javax.swing.JLabel();
        fieldParentescoMadre = new javax.swing.JTextField();
        diaNacMadField = new javax.swing.JTextField();
        añoNacMadField = new javax.swing.JTextField();
        mesNacMadField = new javax.swing.JTextField();
        diaNacNiñLbl1 = new javax.swing.JLabel();
        mesNacNiñLbl1 = new javax.swing.JLabel();
        añoNacNiñLbl1 = new javax.swing.JLabel();
        btn_aggImgMad = new javax.swing.JButton();
        img_infMad = new javax.swing.JLabel();
        lbl_ce2 = new javax.swing.JLabel();
        txt_rutImgMad = new javax.swing.JTextField();
        dat_padre = new javax.swing.JPanel();
        label_apellido_padre = new javax.swing.JLabel();
        text_apellidos_padre = new javax.swing.JTextField();
        label_nombes_padre = new javax.swing.JLabel();
        text_nombres_padre = new javax.swing.JTextField();
        label_ci_padre = new javax.swing.JLabel();
        text_ci_padre = new javax.swing.JTextField();
        label_estadociv_padre = new javax.swing.JLabel();
        check_s_padre = new javax.swing.JCheckBox();
        check_c_padre = new javax.swing.JCheckBox();
        check_d_padre = new javax.swing.JCheckBox();
        check_v_padre = new javax.swing.JCheckBox();
        label_fec_nac_padre = new javax.swing.JLabel();
        label_edad_padre = new javax.swing.JLabel();
        text_edad_padre = new javax.swing.JTextField();
        label_lugar_nac_padre = new javax.swing.JLabel();
        text_lugar_nac_padre = new javax.swing.JTextField();
        label_nacionalidad_padre = new javax.swing.JLabel();
        check_ven_padre = new javax.swing.JCheckBox();
        check_ext_padre = new javax.swing.JCheckBox();
        label_direc_hab_padre = new javax.swing.JLabel();
        text_direc_hab_padre = new javax.swing.JTextField();
        label_grado_padre = new javax.swing.JLabel();
        check_no_bachiller_padre = new javax.swing.JCheckBox();
        chec_bachiller_padre = new javax.swing.JCheckBox();
        check_tsu_padre = new javax.swing.JCheckBox();
        check_superior_padre = new javax.swing.JCheckBox();
        label_ocupacion_padre = new javax.swing.JLabel();
        text_ocupacion_padre = new javax.swing.JTextField();
        label_direc_trabaj_padre = new javax.swing.JLabel();
        text_direc_trabj_padre = new javax.swing.JTextField();
        label_correo_padre = new javax.swing.JLabel();
        text_correo_padre = new javax.swing.JTextField();
        label_tlf_padre = new javax.swing.JLabel();
        text_tlf_padre = new javax.swing.JTextField();
        repLegalPadre = new javax.swing.JLabel();
        repLegalPadreCheckbox = new javax.swing.JCheckBox();
        parentescoPadreTexto = new javax.swing.JLabel();
        fieldParentescoPadre = new javax.swing.JTextField();
        diaNacNiñLbl2 = new javax.swing.JLabel();
        diaNacPadreField = new javax.swing.JTextField();
        mesNacNiñLbl2 = new javax.swing.JLabel();
        mesNacPadreField = new javax.swing.JTextField();
        añoNacNiñLbl2 = new javax.swing.JLabel();
        añoNacPadreField = new javax.swing.JTextField();
        txt_rutImgPadre = new javax.swing.JTextField();
        lbl_ce3 = new javax.swing.JLabel();
        btn_aggImgPadre = new javax.swing.JButton();
        img_infPadre = new javax.swing.JLabel();
        dat_rep = new javax.swing.JPanel();
        label_apellido_rep = new javax.swing.JLabel();
        text_apellidos_rep = new javax.swing.JTextField();
        label_nombes_rep = new javax.swing.JLabel();
        text_nombres_rep = new javax.swing.JTextField();
        label_ci_rep = new javax.swing.JLabel();
        text_ci_rep = new javax.swing.JTextField();
        label_estadociv_rep = new javax.swing.JLabel();
        check_s_rep = new javax.swing.JCheckBox();
        check_c_rep = new javax.swing.JCheckBox();
        check_d_rep = new javax.swing.JCheckBox();
        check_v_rep = new javax.swing.JCheckBox();
        label_fec_nac_rep = new javax.swing.JLabel();
        label_edad_rep = new javax.swing.JLabel();
        text_edad_rep = new javax.swing.JTextField();
        label_lugar_nac_rep = new javax.swing.JLabel();
        text_lugar_nac_rep = new javax.swing.JTextField();
        label_nacionalidad_rep = new javax.swing.JLabel();
        check_ven_rep = new javax.swing.JCheckBox();
        check_ext_rep = new javax.swing.JCheckBox();
        label_direc_hab_rep = new javax.swing.JLabel();
        text_direc_hab_rep = new javax.swing.JTextField();
        label_grado_rep = new javax.swing.JLabel();
        check_no_bachiller_rep = new javax.swing.JCheckBox();
        chec_bachiller_rep = new javax.swing.JCheckBox();
        check_tsu_rep = new javax.swing.JCheckBox();
        check_superior_rep = new javax.swing.JCheckBox();
        label_ocupacion_rep = new javax.swing.JLabel();
        text_ocupacion_rep = new javax.swing.JTextField();
        label_direc_trabaj_rep = new javax.swing.JLabel();
        text_direc_trabj_rep = new javax.swing.JTextField();
        label_correo_rep = new javax.swing.JLabel();
        text_correo_rep = new javax.swing.JTextField();
        label_tlf_rep = new javax.swing.JLabel();
        text_tlf_rep = new javax.swing.JTextField();
        parentescoRepresentanteTexto = new javax.swing.JLabel();
        fieldRepresentante = new javax.swing.JTextField();
        diaNacNiñLbl3 = new javax.swing.JLabel();
        diaNacRepField = new javax.swing.JTextField();
        mesNacNiñLbl3 = new javax.swing.JLabel();
        mesNacRepField = new javax.swing.JTextField();
        añoNacNiñLbl3 = new javax.swing.JLabel();
        añoNacRepField = new javax.swing.JTextField();
        img_infRep = new javax.swing.JLabel();
        lbl_ce4 = new javax.swing.JLabel();
        txt_rutImgRep = new javax.swing.JTextField();
        btn_aggImgRep = new javax.swing.JButton();
        dat_aut = new javax.swing.JPanel();
        lbl_autRetiro = new javax.swing.JLabel();
        scrl_autRetiro = new javax.swing.JScrollPane();
        tbl_autRetiro = new javax.swing.JTable();
        dat_socioFamiliares = new javax.swing.JPanel();
        lbl_casa = new javax.swing.JLabel();
        rdio_propia = new javax.swing.JRadioButton();
        rdio_prestada = new javax.swing.JRadioButton();
        rdio_alquilada = new javax.swing.JRadioButton();
        lbl_tipocasa = new javax.swing.JLabel();
        rdio_zinc = new javax.swing.JRadioButton();
        rdio_casa = new javax.swing.JRadioButton();
        rdio_apartamento = new javax.swing.JRadioButton();
        rdio_otro = new javax.swing.JRadioButton();
        lbl_familiarExtra = new javax.swing.JLabel();
        scrl_familiarExtra = new javax.swing.JScrollPane();
        tbl_familiarExtra = new javax.swing.JTable();
        lbl_cuidaNiñoHogar = new javax.swing.JLabel();
        txt_cuidaNiñoHogar = new javax.swing.JTextField();
        lbl_consultas = new javax.swing.JLabel();
        chk_pspg = new javax.swing.JCheckBox();
        chk_psic = new javax.swing.JCheckBox();
        chk_neur = new javax.swing.JCheckBox();
        chk_terpLenguaje = new javax.swing.JCheckBox();
        chk_otroConsult = new javax.swing.JCheckBox();
        txt_otroConsult = new javax.swing.JTextField();
        lbl_relPadres = new javax.swing.JLabel();
        check_s_padres = new javax.swing.JCheckBox();
        check_c_padres = new javax.swing.JCheckBox();
        check_d_padres = new javax.swing.JCheckBox();
        check_v_padres = new javax.swing.JCheckBox();
        panelDocumentosEstd = new javax.swing.JPanel();
        label_originales = new javax.swing.JLabel();
        check_originales = new javax.swing.JCheckBox();
        label_copias = new javax.swing.JLabel();
        check_copias = new javax.swing.JCheckBox();
        label_partida = new javax.swing.JLabel();
        check_partida = new javax.swing.JCheckBox();
        label_vacunas = new javax.swing.JLabel();
        check_vacunas = new javax.swing.JCheckBox();
        label_cedula = new javax.swing.JLabel();
        check_ci_madre = new javax.swing.JCheckBox();
        check_ci_padre = new javax.swing.JCheckBox();
        check_ci_representante = new javax.swing.JCheckBox();
        label_responsable = new javax.swing.JLabel();
        text_responsable = new javax.swing.JTextField();
        label_persona = new javax.swing.JLabel();
        text_persona = new javax.swing.JTextField();
        label_fecha = new javax.swing.JLabel();
        text_fecha = new javax.swing.JTextField();
        label_observaciones = new javax.swing.JLabel();
        scroll_observaciones = new javax.swing.JScrollPane();
        text_observaciones = new javax.swing.JTextPane();
        btn_aceptarInscripcionNiño = new javax.swing.JButton();
        btn_aggImgNiñ = new javax.swing.JButton();
        lbl_ce1 = new javax.swing.JLabel();
        txt_ce = new javax.swing.JTextField();
        txt_rutImgNiñ = new javax.swing.JTextField();
        informacionNomina = new javax.swing.JPanel();
        headderInfNomina = new javax.swing.JPanel();
        lbl_headderInfNomina = new javax.swing.JLabel();
        imgNomina = new javax.swing.JLabel();
        datPersNomina = new javax.swing.JPanel();
        lbl_ciNomina = new javax.swing.JLabel();
        txt_ciNomina = new javax.swing.JTextField();
        lbl_rifNomina = new javax.swing.JLabel();
        txt_rifNomina = new javax.swing.JTextField();
        lbl_nombreNomina = new javax.swing.JLabel();
        txt_nombreNomina = new javax.swing.JTextField();
        lbl_apellidoNomina = new javax.swing.JLabel();
        txt_apellidoNomina = new javax.swing.JTextField();
        lbl_sexoNomina = new javax.swing.JLabel();
        rdb_mascNomina = new javax.swing.JRadioButton();
        rdb_femNomina = new javax.swing.JRadioButton();
        lbl_fechNacimNomina = new javax.swing.JLabel();
        txt_fechNacimNomina = new javax.swing.JTextField();
        lbl_edadNomina = new javax.swing.JLabel();
        txt_edadNomina = new javax.swing.JTextField();
        lbl_munResNomina = new javax.swing.JLabel();
        txt_munResNomina = new javax.swing.JTextField();
        lbl_prrqResNomina = new javax.swing.JLabel();
        txt_prrqResNomina = new javax.swing.JTextField();
        lbl_comResNomina = new javax.swing.JLabel();
        txt_comResNomina = new javax.swing.JTextField();
        lbl_calleResNomina = new javax.swing.JLabel();
        txt_calleResNomina = new javax.swing.JTextField();
        lbl_tlf1Nomina = new javax.swing.JLabel();
        txt_tlf1Nomina = new javax.swing.JTextField();
        lbl_tlf2Nomina = new javax.swing.JLabel();
        txt_tlf2Nomina = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        datGubNomina = new javax.swing.JPanel();
        lbl_munVotNomina = new javax.swing.JLabel();
        txt_munVotNomina = new javax.swing.JTextField();
        lbl_parrqVotNomina = new javax.swing.JLabel();
        txt_parrqVotNomina = new javax.swing.JTextField();
        lbl_centrVotNomina = new javax.swing.JLabel();
        txt_centrVotNomina = new javax.swing.JTextField();
        lbl_votoNomina = new javax.swing.JLabel();
        rdb_siVot = new javax.swing.JRadioButton();
        rdb_noVot = new javax.swing.JRadioButton();
        lbl_jefComNomina = new javax.swing.JLabel();
        txt_jefComNomina = new javax.swing.JTextField();
        lbl_ciJefComNomina = new javax.swing.JLabel();
        txt_ciJefComNomina = new javax.swing.JTextField();
        lbl_1x10Nomina = new javax.swing.JLabel();
        rdb_si1x10 = new javax.swing.JRadioButton();
        rdb_no1x10 = new javax.swing.JRadioButton();
        lbl_cant1x10Nomina = new javax.swing.JLabel();
        txt_cant1x10Nomina = new javax.swing.JTextField();
        datPlantelNomina = new javax.swing.JPanel();
        lbl_turnoNomina = new javax.swing.JLabel();
        txt_turnoNomina = new javax.swing.JTextField();
        lbl_nivelNominaMaestra = new javax.swing.JLabel();
        txt_nivelNominaMaestra = new javax.swing.JTextField();
        lbl_seccionNominaMaestra = new javax.swing.JLabel();
        txt_seccionNominaMaestra = new javax.swing.JTextField();
        lbl_estatusNomina = new javax.swing.JLabel();
        txt_estatusNomina = new javax.swing.JTextField();
        lbl_fechIngresoNomina = new javax.swing.JLabel();
        txt_fechaIngresoNomina = new javax.swing.JTextField();
        btn_aceptarInscripcionNomina = new javax.swing.JButton();
        btn_aggImgNiñ1 = new javax.swing.JButton();
        txt_rutImgNiñ1 = new javax.swing.JTextField();
        lbl_ce5 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();

        headderMenu.setBackground(new java.awt.Color(27, 120, 101));
        headderMenu.setPreferredSize(new java.awt.Dimension(1336, 64));

        txtMenuPrincipal1.setBackground(new java.awt.Color(204, 204, 255));
        txtMenuPrincipal1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        txtMenuPrincipal1.setForeground(new java.awt.Color(250, 247, 239));
        txtMenuPrincipal1.setText("Menu Principal");

        javax.swing.GroupLayout headderMenuLayout = new javax.swing.GroupLayout(headderMenu);
        headderMenu.setLayout(headderMenuLayout);
        headderMenuLayout.setHorizontalGroup(
            headderMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headderMenuLayout.createSequentialGroup()
                .addGap(497, 497, 497)
                .addComponent(txtMenuPrincipal1)
                .addContainerGap(498, Short.MAX_VALUE))
        );
        headderMenuLayout.setVerticalGroup(
            headderMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtMenuPrincipal1)
        );

        logoPreescolar.setFont(new java.awt.Font("Segoe UI", 0, 4)); // NOI18N
        logoPreescolar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoPreescolar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/logo_inst_512.png"))); // NOI18N
        logoPreescolar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        logoPreescolar.setAlignmentY(0.0F);
        logoPreescolar.setAutoscrolls(true);
        logoPreescolar.setFocusable(false);
        logoPreescolar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        logoPreescolar.setIconTextGap(0);
        logoPreescolar.setRequestFocusEnabled(false);

        imgHerramienta.setFont(new java.awt.Font("Segoe UI", 0, 4)); // NOI18N
        imgHerramienta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgHerramienta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/set1/tools_240.png"))); // NOI18N
        imgHerramienta.setAlignmentY(0.0F);
        imgHerramienta.setAutoscrolls(true);
        imgHerramienta.setFocusable(false);
        imgHerramienta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imgHerramienta.setIconTextGap(0);
        imgHerramienta.setRequestFocusEnabled(false);

        btnConfiguracion.setBackground(new java.awt.Color(62, 181, 157));
        btnConfiguracion.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        btnConfiguracion.setForeground(new java.awt.Color(250, 247, 239));
        btnConfiguracion.setText("CONFIG");
        btnConfiguracion.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnConfiguracion.setIconTextGap(25);
        btnConfiguracion.setMaximumSize(new java.awt.Dimension(100, 40));
        btnConfiguracion.setMinimumSize(new java.awt.Dimension(250, 56));
        btnConfiguracion.setPreferredSize(new java.awt.Dimension(250, 56));
        btnConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracionActionPerformed(evt);
            }
        });

        imgEstudiante.setFont(new java.awt.Font("Segoe UI", 0, 4)); // NOI18N
        imgEstudiante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgEstudiante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/set2/kid_256.png"))); // NOI18N
        imgEstudiante.setToolTipText("");
        imgEstudiante.setAlignmentY(0.0F);
        imgEstudiante.setAutoscrolls(true);
        imgEstudiante.setFocusable(false);
        imgEstudiante.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imgEstudiante.setIconTextGap(0);
        imgEstudiante.setRequestFocusEnabled(false);

        btnEstudiantes.setBackground(new java.awt.Color(62, 181, 157));
        btnEstudiantes.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        btnEstudiantes.setForeground(new java.awt.Color(250, 247, 239));
        btnEstudiantes.setText("ESTUDIANTES");
        btnEstudiantes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEstudiantes.setIconTextGap(25);
        btnEstudiantes.setMaximumSize(new java.awt.Dimension(100, 40));
        btnEstudiantes.setMinimumSize(new java.awt.Dimension(250, 56));
        btnEstudiantes.setPreferredSize(new java.awt.Dimension(256, 56));
        btnEstudiantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstudiantesActionPerformed(evt);
            }
        });

        imgFamilia.setFont(new java.awt.Font("Segoe UI", 0, 4)); // NOI18N
        imgFamilia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgFamilia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/set2/family_256.png"))); // NOI18N
        imgFamilia.setAlignmentY(0.0F);
        imgFamilia.setAutoscrolls(true);
        imgFamilia.setFocusable(false);
        imgFamilia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imgFamilia.setIconTextGap(0);
        imgFamilia.setRequestFocusEnabled(false);

        btnRepresentantes.setBackground(new java.awt.Color(62, 181, 157));
        btnRepresentantes.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        btnRepresentantes.setForeground(new java.awt.Color(250, 247, 239));
        btnRepresentantes.setText("REPRESENTANTES");
        btnRepresentantes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnRepresentantes.setIconTextGap(25);
        btnRepresentantes.setMaximumSize(new java.awt.Dimension(100, 40));
        btnRepresentantes.setMinimumSize(new java.awt.Dimension(250, 56));
        btnRepresentantes.setPreferredSize(new java.awt.Dimension(250, 56));
        btnRepresentantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRepresentantesActionPerformed(evt);
            }
        });

        imgAutorizado.setFont(new java.awt.Font("Segoe UI", 0, 4)); // NOI18N
        imgAutorizado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgAutorizado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/set2/talent_256.png"))); // NOI18N
        imgAutorizado.setAlignmentY(0.0F);
        imgAutorizado.setAutoscrolls(true);
        imgAutorizado.setFocusable(false);
        imgAutorizado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imgAutorizado.setIconTextGap(0);
        imgAutorizado.setRequestFocusEnabled(false);

        btnAutorizados.setBackground(new java.awt.Color(62, 181, 157));
        btnAutorizados.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        btnAutorizados.setForeground(new java.awt.Color(250, 247, 239));
        btnAutorizados.setText("AUTORIZADOS");
        btnAutorizados.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAutorizados.setIconTextGap(25);
        btnAutorizados.setMaximumSize(new java.awt.Dimension(100, 40));
        btnAutorizados.setMinimumSize(new java.awt.Dimension(250, 56));
        btnAutorizados.setPreferredSize(new java.awt.Dimension(250, 56));
        btnAutorizados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAutorizadosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout principalLayout = new javax.swing.GroupLayout(principal);
        principal.setLayout(principalLayout);
        principalLayout.setHorizontalGroup(
            principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(principalLayout.createSequentialGroup()
                .addComponent(headderMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, principalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(principalLayout.createSequentialGroup()
                        .addGroup(principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, principalLayout.createSequentialGroup()
                                .addComponent(imgEstudiante)
                                .addGap(76, 76, 76))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, principalLayout.createSequentialGroup()
                                .addGroup(principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnEstudiantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(imgFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46)))
                        .addComponent(logoPreescolar))
                    .addComponent(btnRepresentantes, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(imgHerramienta, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAutorizados, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imgAutorizado, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(94, 94, 94))
        );
        principalLayout.setVerticalGroup(
            principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(principalLayout.createSequentialGroup()
                .addComponent(headderMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(principalLayout.createSequentialGroup()
                            .addGap(132, 132, 132)
                            .addComponent(logoPreescolar))
                        .addGroup(principalLayout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addGroup(principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(principalLayout.createSequentialGroup()
                                    .addGap(265, 265, 265)
                                    .addComponent(btnEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(imgEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(imgFamilia)
                            .addGap(18, 18, 18)
                            .addComponent(btnRepresentantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(principalLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(imgAutorizado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAutorizados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(imgHerramienta, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        busquedaEstudiantes.setPreferredSize(new java.awt.Dimension(1336, 800));

        headderBusqEst.setBackground(new java.awt.Color(27, 120, 101));

        lbl_busqEstudiante.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbl_busqEstudiante.setForeground(new java.awt.Color(250, 247, 239));
        lbl_busqEstudiante.setText("BUSCAR ESTUDIANTE");

        btn_cerrarBusqEst.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btn_cerrarBusqEst.setText("Cerrar");
        btn_cerrarBusqEst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarBusqEstActionPerformed(evt);
            }
        });

        btn_volverBusqEst.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btn_volverBusqEst.setText("Volver");
        btn_volverBusqEst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volverBusqEstActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headderBusqEstLayout = new javax.swing.GroupLayout(headderBusqEst);
        headderBusqEst.setLayout(headderBusqEstLayout);
        headderBusqEstLayout.setHorizontalGroup(
            headderBusqEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headderBusqEstLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_volverBusqEst)
                .addGap(427, 427, 427)
                .addComponent(lbl_busqEstudiante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 382, Short.MAX_VALUE)
                .addComponent(btn_cerrarBusqEst)
                .addContainerGap())
        );
        headderBusqEstLayout.setVerticalGroup(
            headderBusqEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headderBusqEstLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headderBusqEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headderBusqEstLayout.createSequentialGroup()
                        .addComponent(lbl_busqEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btn_cerrarBusqEst, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_volverBusqEst, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        cmb_filtroBusqEstd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cédula Estudiantil", "Nombre", "Apellido", "Talla Camisa", "Talla Pantalón", "Talla Zapato", "Peso", "Estatura", "Edad", "Ninguno" }));
        cmb_filtroBusqEstd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_filtroBusqEstdActionPerformed(evt);
            }
        });

        tbl_busqEstd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbl_busqEstd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Foto", "Nombres", "Apellidos", "Cédula", "Grupo Sanguineo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_busqEstd.setColumnSelectionAllowed(true);
        tbl_busqEstd.setMinimumSize(new java.awt.Dimension(550, 250));
        tbl_busqEstd.setPreferredSize(new java.awt.Dimension(550, 250));
        scrl_busqEstd.setViewportView(tbl_busqEstd);

        img_busqEstd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_busqEstd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/set1/user-line_240.png"))); // NOI18N

        btn_inscribirEstd.setBackground(new java.awt.Color(27, 120, 101));
        btn_inscribirEstd.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btn_inscribirEstd.setForeground(new java.awt.Color(250, 247, 239));
        btn_inscribirEstd.setText("Añadir Estudiante");
        btn_inscribirEstd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inscribirEstdActionPerformed(evt);
            }
        });

        btn_verBusqEstd.setBackground(new java.awt.Color(139, 208, 194));
        btn_verBusqEstd.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_verBusqEstd.setForeground(new java.awt.Color(250, 247, 239));
        btn_verBusqEstd.setText("Ver");
        btn_verBusqEstd.setMaximumSize(new java.awt.Dimension(100, 20));
        btn_verBusqEstd.setMinimumSize(new java.awt.Dimension(100, 20));
        btn_verBusqEstd.setPreferredSize(new java.awt.Dimension(100, 20));
        btn_verBusqEstd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verBusqEstdActionPerformed(evt);
            }
        });

        img_logoInstitucionBusqEstd.setFont(new java.awt.Font("Segoe UI", 0, 4)); // NOI18N
        img_logoInstitucionBusqEstd.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        img_logoInstitucionBusqEstd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/logo_inst_256.png"))); // NOI18N
        img_logoInstitucionBusqEstd.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        img_logoInstitucionBusqEstd.setAlignmentY(0.0F);
        img_logoInstitucionBusqEstd.setAutoscrolls(true);
        img_logoInstitucionBusqEstd.setFocusable(false);
        img_logoInstitucionBusqEstd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        img_logoInstitucionBusqEstd.setIconTextGap(0);
        img_logoInstitucionBusqEstd.setRequestFocusEnabled(false);

        btn_buscarEstd.setBackground(new java.awt.Color(27, 120, 101));
        btn_buscarEstd.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btn_buscarEstd.setForeground(new java.awt.Color(250, 247, 239));
        btn_buscarEstd.setText("Buscar");
        btn_buscarEstd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarEstdActionPerformed(evt);
            }
        });

        btn_eliminarBusqEstd.setBackground(new java.awt.Color(207, 207, 207));
        btn_eliminarBusqEstd.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_eliminarBusqEstd.setForeground(new java.awt.Color(102, 102, 102));
        btn_eliminarBusqEstd.setText("Eliminar");
        btn_eliminarBusqEstd.setMaximumSize(new java.awt.Dimension(100, 20));
        btn_eliminarBusqEstd.setMinimumSize(new java.awt.Dimension(100, 20));
        btn_eliminarBusqEstd.setPreferredSize(new java.awt.Dimension(100, 20));
        btn_eliminarBusqEstd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarBusqEstdActionPerformed(evt);
            }
        });

        btn_eliminarBusqEstd1.setBackground(new java.awt.Color(139, 208, 194));
        btn_eliminarBusqEstd1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_eliminarBusqEstd1.setForeground(new java.awt.Color(250, 247, 239));
        btn_eliminarBusqEstd1.setText("Emitir Reporte");
        btn_eliminarBusqEstd1.setMaximumSize(new java.awt.Dimension(100, 20));
        btn_eliminarBusqEstd1.setMinimumSize(new java.awt.Dimension(100, 20));
        btn_eliminarBusqEstd1.setPreferredSize(new java.awt.Dimension(100, 20));
        btn_eliminarBusqEstd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarBusqEstd1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout busquedaEstudiantesLayout = new javax.swing.GroupLayout(busquedaEstudiantes);
        busquedaEstudiantes.setLayout(busquedaEstudiantesLayout);
        busquedaEstudiantesLayout.setHorizontalGroup(
            busquedaEstudiantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headderBusqEst, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, busquedaEstudiantesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(busquedaEstudiantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, busquedaEstudiantesLayout.createSequentialGroup()
                        .addComponent(cmb_filtroBusqEstd, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_filtroBusqEstd))
                    .addComponent(scrl_busqEstd, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE))
                .addGroup(busquedaEstudiantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(busquedaEstudiantesLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(img_logoInstitucionBusqEstd))
                    .addGroup(busquedaEstudiantesLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(busquedaEstudiantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(busquedaEstudiantesLayout.createSequentialGroup()
                                .addComponent(btn_buscarEstd, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_inscribirEstd, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_verBusqEstd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(img_busqEstd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(busquedaEstudiantesLayout.createSequentialGroup()
                                .addComponent(btn_eliminarBusqEstd, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_eliminarBusqEstd1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(13, 13, 13))
        );
        busquedaEstudiantesLayout.setVerticalGroup(
            busquedaEstudiantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(busquedaEstudiantesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headderBusqEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(busquedaEstudiantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(busquedaEstudiantesLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(busquedaEstudiantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_filtroBusqEstd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_filtroBusqEstd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_buscarEstd)
                            .addComponent(btn_inscribirEstd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrl_busqEstd, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(busquedaEstudiantesLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(img_busqEstd)
                        .addGap(18, 18, 18)
                        .addComponent(btn_verBusqEstd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(busquedaEstudiantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_eliminarBusqEstd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_eliminarBusqEstd1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(img_logoInstitucionBusqEstd)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        busquedaRepresentante.setPreferredSize(new java.awt.Dimension(1336, 800));

        headderBusqRep.setBackground(new java.awt.Color(27, 120, 101));
        headderBusqRep.setPreferredSize(new java.awt.Dimension(1336, 43));

        lbl_busqRepresentante.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbl_busqRepresentante.setForeground(new java.awt.Color(250, 247, 239));
        lbl_busqRepresentante.setText("BUSQUEDA REPRESENTANTE");

        btn_cerrarBusqRep.setText("Cerrar");
        btn_cerrarBusqRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarBusqRepActionPerformed(evt);
            }
        });

        btn_volverBusqRep.setText("Volver");
        btn_volverBusqRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volverBusqRepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headderBusqRepLayout = new javax.swing.GroupLayout(headderBusqRep);
        headderBusqRep.setLayout(headderBusqRepLayout);
        headderBusqRepLayout.setHorizontalGroup(
            headderBusqRepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headderBusqRepLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_volverBusqRep, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(309, 309, 309)
                .addComponent(lbl_busqRepresentante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 323, Short.MAX_VALUE)
                .addComponent(btn_cerrarBusqRep, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        headderBusqRepLayout.setVerticalGroup(
            headderBusqRepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headderBusqRepLayout.createSequentialGroup()
                .addComponent(lbl_busqRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(headderBusqRepLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headderBusqRepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_volverBusqRep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_cerrarBusqRep, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        cmb_filtroBusqRep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cédula", "Nombre", "Apellido", "Estado Civil", "Grado de Estudios", "Ocupación", "Ninguno" }));
        cmb_filtroBusqRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_filtroBusqRepActionPerformed(evt);
            }
        });

        tbl_busqRep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbl_busqRep.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Foto", "Nombres", "Apellidos", "¿Tiene menores de 6?", "CI"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_busqRep.setColumnSelectionAllowed(true);
        tbl_busqRep.setMinimumSize(new java.awt.Dimension(550, 250));
        tbl_busqRep.setPreferredSize(new java.awt.Dimension(550, 250));
        scrl_busqRep.setViewportView(tbl_busqRep);

        img_busqRep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_busqRep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/set1/user-line_240.png"))); // NOI18N

        btn_verBusqRep.setBackground(new java.awt.Color(139, 208, 194));
        btn_verBusqRep.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_verBusqRep.setForeground(new java.awt.Color(250, 247, 239));
        btn_verBusqRep.setText("Ver");
        btn_verBusqRep.setMaximumSize(new java.awt.Dimension(100, 20));
        btn_verBusqRep.setMinimumSize(new java.awt.Dimension(100, 20));
        btn_verBusqRep.setPreferredSize(new java.awt.Dimension(100, 20));
        btn_verBusqRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verBusqRepActionPerformed(evt);
            }
        });

        img_logoInstitucionBusqRep.setFont(new java.awt.Font("Segoe UI", 0, 4)); // NOI18N
        img_logoInstitucionBusqRep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_logoInstitucionBusqRep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/logo_inst_256.png"))); // NOI18N
        img_logoInstitucionBusqRep.setAlignmentY(0.0F);
        img_logoInstitucionBusqRep.setAutoscrolls(true);
        img_logoInstitucionBusqRep.setFocusable(false);
        img_logoInstitucionBusqRep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        img_logoInstitucionBusqRep.setIconTextGap(0);
        img_logoInstitucionBusqRep.setRequestFocusEnabled(false);

        btn_buscarRep.setBackground(new java.awt.Color(27, 120, 101));
        btn_buscarRep.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btn_buscarRep.setForeground(new java.awt.Color(250, 247, 239));
        btn_buscarRep.setText("Buscar");
        btn_buscarRep.setToolTipText("");
        btn_buscarRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarRepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout busquedaRepresentanteLayout = new javax.swing.GroupLayout(busquedaRepresentante);
        busquedaRepresentante.setLayout(busquedaRepresentanteLayout);
        busquedaRepresentanteLayout.setHorizontalGroup(
            busquedaRepresentanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headderBusqRep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, busquedaRepresentanteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(busquedaRepresentanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(busquedaRepresentanteLayout.createSequentialGroup()
                        .addComponent(cmb_filtroBusqRep, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_filtroBusqRep))
                    .addComponent(scrl_busqRep))
                .addGroup(busquedaRepresentanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, busquedaRepresentanteLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(img_busqRep, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))
                    .addGroup(busquedaRepresentanteLayout.createSequentialGroup()
                        .addGroup(busquedaRepresentanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(busquedaRepresentanteLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(busquedaRepresentanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_buscarRep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_verBusqRep, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)))
                            .addGroup(busquedaRepresentanteLayout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(img_logoInstitucionBusqRep)))
                        .addContainerGap())))
        );
        busquedaRepresentanteLayout.setVerticalGroup(
            busquedaRepresentanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(busquedaRepresentanteLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(headderBusqRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(busquedaRepresentanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_filtroBusqRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_filtroBusqRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscarRep))
                .addGroup(busquedaRepresentanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(busquedaRepresentanteLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrl_busqRep, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(busquedaRepresentanteLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(img_busqRep)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_verBusqRep, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(img_logoInstitucionBusqRep, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))))
        );

        busquedaAutorizados.setPreferredSize(new java.awt.Dimension(1336, 800));

        headderBusqAut.setBackground(new java.awt.Color(27, 120, 101));

        lbl_busqAutorizado.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbl_busqAutorizado.setForeground(new java.awt.Color(250, 247, 239));
        lbl_busqAutorizado.setText("AUTORIZADO PARA RETIRO");

        btn_cerrarBusqAut.setText("Cerrar");
        btn_cerrarBusqAut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarBusqAutActionPerformed(evt);
            }
        });

        btn_volverBusqAut.setText("Volver");
        btn_volverBusqAut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volverBusqAutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headderBusqAutLayout = new javax.swing.GroupLayout(headderBusqAut);
        headderBusqAut.setLayout(headderBusqAutLayout);
        headderBusqAutLayout.setHorizontalGroup(
            headderBusqAutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headderBusqAutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_volverBusqAut, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 327, Short.MAX_VALUE)
                .addComponent(lbl_busqAutorizado)
                .addGap(310, 310, 310)
                .addComponent(btn_cerrarBusqAut, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        headderBusqAutLayout.setVerticalGroup(
            headderBusqAutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headderBusqAutLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headderBusqAutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headderBusqAutLayout.createSequentialGroup()
                        .addComponent(lbl_busqAutorizado, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(headderBusqAutLayout.createSequentialGroup()
                        .addGroup(headderBusqAutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_volverBusqAut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_cerrarBusqAut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        cmb_filtroBusqAut.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "C.I Autorizado", "C.I Representante", "Cédula de Estudiante", "Nombres", "Apellidos", "Teléfono 1", "Teléfono 2", "Ninguno" }));
        cmb_filtroBusqAut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_filtroBusqAutActionPerformed(evt);
            }
        });

        tbl_busqAut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbl_busqAut.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cedula", "Nombre", "Apellido", "Telefono 1", "Telefono 2", "Cédula Estudiante(s)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbl_busqAut.setColumnSelectionAllowed(true);
        tbl_busqAut.setMinimumSize(new java.awt.Dimension(550, 250));
        tbl_busqAut.setPreferredSize(new java.awt.Dimension(550, 250));
        scrl_busqAut.setViewportView(tbl_busqAut);

        btn_verBusqAut.setBackground(new java.awt.Color(139, 208, 194));
        btn_verBusqAut.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_verBusqAut.setForeground(new java.awt.Color(250, 247, 239));
        btn_verBusqAut.setText("Ver");
        btn_verBusqAut.setMaximumSize(new java.awt.Dimension(100, 20));
        btn_verBusqAut.setMinimumSize(new java.awt.Dimension(100, 20));
        btn_verBusqAut.setPreferredSize(new java.awt.Dimension(100, 20));
        btn_verBusqAut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verBusqAutActionPerformed(evt);
            }
        });

        btn_buscarAut.setBackground(new java.awt.Color(27, 120, 101));
        btn_buscarAut.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btn_buscarAut.setForeground(new java.awt.Color(250, 247, 239));
        btn_buscarAut.setText("Buscar");
        btn_buscarAut.setToolTipText("");
        btn_buscarAut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarAutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout busquedaAutorizadosLayout = new javax.swing.GroupLayout(busquedaAutorizados);
        busquedaAutorizados.setLayout(busquedaAutorizadosLayout);
        busquedaAutorizadosLayout.setHorizontalGroup(
            busquedaAutorizadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headderBusqAut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(busquedaAutorizadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(busquedaAutorizadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(busquedaAutorizadosLayout.createSequentialGroup()
                        .addGroup(busquedaAutorizadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(scrl_busqAut, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(busquedaAutorizadosLayout.createSequentialGroup()
                                .addComponent(cmb_filtroBusqAut, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_filtroBusqAut)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_buscarAut, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15))
                    .addGroup(busquedaAutorizadosLayout.createSequentialGroup()
                        .addComponent(btn_verBusqAut, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        busquedaAutorizadosLayout.setVerticalGroup(
            busquedaAutorizadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(busquedaAutorizadosLayout.createSequentialGroup()
                .addComponent(headderBusqAut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(busquedaAutorizadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_filtroBusqAut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_filtroBusqAut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscarAut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrl_busqAut, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_verBusqAut, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        busquedaNomina.setPreferredSize(new java.awt.Dimension(1336, 800));

        headderBusqNomina.setBackground(new java.awt.Color(27, 120, 101));

        lbl_busqNomina.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbl_busqNomina.setForeground(new java.awt.Color(250, 247, 239));
        lbl_busqNomina.setText("BUSQUEDA NÓMINA");

        btn_volverBusqNom.setText("Volver");
        btn_volverBusqNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volverBusqNomActionPerformed(evt);
            }
        });

        btn_cerrarBusqNom.setText("Cerrar");
        btn_cerrarBusqNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarBusqNomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headderBusqNominaLayout = new javax.swing.GroupLayout(headderBusqNomina);
        headderBusqNomina.setLayout(headderBusqNominaLayout);
        headderBusqNominaLayout.setHorizontalGroup(
            headderBusqNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headderBusqNominaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_volverBusqNom, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(431, 431, 431)
                .addComponent(lbl_busqNomina)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_cerrarBusqNom, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        headderBusqNominaLayout.setVerticalGroup(
            headderBusqNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headderBusqNominaLayout.createSequentialGroup()
                .addComponent(lbl_busqNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(headderBusqNominaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headderBusqNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_volverBusqNom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_cerrarBusqNom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        cmb_filtroBusqNom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cédula", "Nombre", "Apellido", "Estado Civil", "Estatus", "Género", "Grado de Estudios", "Ocupación", "Comunidad de residencia", "Parroquia de residencia", "Municipio de residencia", "Ninguno" }));
        cmb_filtroBusqNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_filtroBusqNomActionPerformed(evt);
            }
        });

        tbl_busqNomina.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbl_busqNomina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Foto", "Nombres", "Apellidos", "Cédula", "Estatus"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbl_busqNomina.setColumnSelectionAllowed(true);
        tbl_busqNomina.setMinimumSize(new java.awt.Dimension(550, 250));
        tbl_busqNomina.setPreferredSize(new java.awt.Dimension(550, 250));
        scl_busqNom.setViewportView(tbl_busqNomina);

        img_busqNomina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_busqNomina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/set1/user-line_240.png"))); // NOI18N

        btn_inscribirNom.setBackground(new java.awt.Color(27, 120, 101));
        btn_inscribirNom.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btn_inscribirNom.setForeground(new java.awt.Color(250, 247, 239));
        btn_inscribirNom.setText("Añadir Personal");
        btn_inscribirNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inscribirNomActionPerformed(evt);
            }
        });

        btn_verNom.setBackground(new java.awt.Color(139, 208, 194));
        btn_verNom.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_verNom.setForeground(new java.awt.Color(250, 247, 239));
        btn_verNom.setText("Ver");
        btn_verNom.setMaximumSize(new java.awt.Dimension(100, 20));
        btn_verNom.setMinimumSize(new java.awt.Dimension(100, 20));
        btn_verNom.setPreferredSize(new java.awt.Dimension(100, 20));
        btn_verNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verNomActionPerformed(evt);
            }
        });

        btn_editarBusqNom.setBackground(new java.awt.Color(231, 231, 231));
        btn_editarBusqNom.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_editarBusqNom.setForeground(new java.awt.Color(102, 102, 102));
        btn_editarBusqNom.setText("Eliminar");
        btn_editarBusqNom.setMaximumSize(new java.awt.Dimension(100, 20));
        btn_editarBusqNom.setMinimumSize(new java.awt.Dimension(100, 20));
        btn_editarBusqNom.setPreferredSize(new java.awt.Dimension(100, 20));

        im_logoInstitucionBusqNom.setFont(new java.awt.Font("Segoe UI", 0, 4)); // NOI18N
        im_logoInstitucionBusqNom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        im_logoInstitucionBusqNom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/logo_inst_256.png"))); // NOI18N
        im_logoInstitucionBusqNom.setAlignmentY(0.0F);
        im_logoInstitucionBusqNom.setAutoscrolls(true);
        im_logoInstitucionBusqNom.setFocusable(false);
        im_logoInstitucionBusqNom.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        im_logoInstitucionBusqNom.setIconTextGap(0);
        im_logoInstitucionBusqNom.setRequestFocusEnabled(false);

        btn_buscarNomina.setBackground(new java.awt.Color(27, 120, 101));
        btn_buscarNomina.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btn_buscarNomina.setForeground(new java.awt.Color(250, 247, 239));
        btn_buscarNomina.setText("Buscar");
        btn_buscarNomina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarNominaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout busquedaNominaLayout = new javax.swing.GroupLayout(busquedaNomina);
        busquedaNomina.setLayout(busquedaNominaLayout);
        busquedaNominaLayout.setHorizontalGroup(
            busquedaNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headderBusqNomina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, busquedaNominaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(busquedaNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(busquedaNominaLayout.createSequentialGroup()
                        .addComponent(cmb_filtroBusqNom, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_filtroBusqNom))
                    .addComponent(scl_busqNom, javax.swing.GroupLayout.PREFERRED_SIZE, 930, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(busquedaNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(img_busqNomina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_verNom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_editarBusqNom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(im_logoInstitucionBusqNom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(busquedaNominaLayout.createSequentialGroup()
                        .addComponent(btn_buscarNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_inscribirNom, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36))
        );
        busquedaNominaLayout.setVerticalGroup(
            busquedaNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(busquedaNominaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headderBusqNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(busquedaNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_filtroBusqNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_filtroBusqNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_inscribirNom)
                    .addComponent(btn_buscarNomina))
                .addGroup(busquedaNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(busquedaNominaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scl_busqNom, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(busquedaNominaLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(img_busqNomina)
                        .addGap(12, 12, 12)
                        .addComponent(btn_verNom, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(btn_editarBusqNom, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(im_logoInstitucionBusqNom)))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        informacionEstudiante.setPreferredSize(new java.awt.Dimension(1336, 800));

        headderEstudiante.setBackground(new java.awt.Color(27, 120, 101));
        headderEstudiante.setPreferredSize(new java.awt.Dimension(1000, 32));

        lbl_headderEstudiante.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_headderEstudiante.setForeground(new java.awt.Color(250, 247, 239));
        lbl_headderEstudiante.setText("INFORMACION");

        btn_repEstd.setText("Volver");
        btn_repEstd.setToolTipText("");
        btn_repEstd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_repEstdActionPerformed(evt);
            }
        });

        btn_repEstd1.setText("Cerrar");
        btn_repEstd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_repEstd1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headderEstudianteLayout = new javax.swing.GroupLayout(headderEstudiante);
        headderEstudiante.setLayout(headderEstudianteLayout);
        headderEstudianteLayout.setHorizontalGroup(
            headderEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headderEstudianteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_repEstd)
                .addGap(515, 515, 515)
                .addComponent(lbl_headderEstudiante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_repEstd1)
                .addContainerGap())
        );
        headderEstudianteLayout.setVerticalGroup(
            headderEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headderEstudianteLayout.createSequentialGroup()
                .addGroup(headderEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headderEstudianteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbl_headderEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(headderEstudianteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_repEstd1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(headderEstudianteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_repEstd, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        img_infEstudiante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_infEstudiante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/set1/user-line_240.png"))); // NOI18N

        lbl_ce.setText("C.E:");

        datosEstd.setBackground(new java.awt.Color(255, 255, 204));

        lbl_nombreNiñ.setText("Nombre");

        txt_nombreNiñ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreNiñActionPerformed(evt);
            }
        });

        lbl_apellidoNiñ.setText("Apellido");

        txt_apellidoNiñ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_apellidoNiñActionPerformed(evt);
            }
        });

        lbl_edadNiñ.setText("Fecha Nacimiento");

        lbl_sexoNiñ.setText("Sexo");

        rdb_mascNiñ.setText("Masculino");
        rdb_mascNiñ.setToolTipText("");
        rdb_mascNiñ.setActionCommand("masculino");

        rdb_femNiñ.setText("femenino");

        lbl_lugarNacNiñ.setText("Lugar de Nacimiento");

        txt_lugarNacNiñ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_lugarNacNiñActionPerformed(evt);
            }
        });

        lbl_munNacNiñ.setText("Municipio");

        lbl_estadoNiñ.setText("Estado");

        lbl_nacionalidadNiñ.setText("Nacionalidad");

        label_edad_niñ.setText("Edad");

        text_edad_niñ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_edad_niñActionPerformed(evt);
            }
        });

        label_procedencia_niñ.setText("Procedencia:");

        radio_hogar_niñ.setText("Hogar");
        radio_hogar_niñ.setToolTipText("");
        radio_hogar_niñ.setActionCommand("hogar");
        radio_hogar_niñ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_hogar_niñActionPerformed(evt);
            }
        });

        radio_mismo_plantel_niñ.setText("Del mismo plantel");
        radio_mismo_plantel_niñ.setActionCommand("del mismo plantel");
        radio_mismo_plantel_niñ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_mismo_plantel_niñActionPerformed(evt);
            }
        });

        radio_multihogar_niñ.setText("Multihogar");
        radio_multihogar_niñ.setActionCommand("multihogar");

        radio_otro_plantel_niñ.setText("De otro plantel");
        radio_otro_plantel_niñ.setActionCommand("de otro plantel");
        radio_otro_plantel_niñ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_otro_plantel_niñActionPerformed(evt);
            }
        });

        radio_hogar_cuidado_niñ.setText("Hogar de cuidado");
        radio_hogar_cuidado_niñ.setActionCommand("hogar de cuidado");

        radio_guarderia_niñ.setText("Guarderia");
        radio_guarderia_niñ.setActionCommand("guarderia");

        radio_otros_niñ.setText("Otros");
        radio_otros_niñ.setActionCommand("otros");

        label_tallas_niñ.setText("Tallas:");

        label_camisa_niñ.setText("Camisa");

        text_tall_camisa_niñ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_tall_camisa_niñActionPerformed(evt);
            }
        });

        label_tall_pantalon_niñ.setText("Pantalon");

        label_tall_zapato_niñ.setText("Zapato");

        label_medidas_niñ.setText("Medidas Antropometricas:");

        label_peso_niñ.setText("Peso");

        text_peso_niñ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_peso_niñActionPerformed(evt);
            }
        });

        label_estatura_niñ.setText("Estatura");

        lbl_edadCaminar.setText("A que edad comenzo a caminar");

        lbl_lateralidad.setText("Lateralidad");

        rdo_diestro.setText("Diestro");

        rdo_zurdo.setText("Zurdo");

        rdo_ambidiestro.setText("Ambidiestro");

        rdo_desconocido.setText("No se sabe");

        diaNacNiñLbl.setText("DD:");

        mesNacNiñLbl.setText("MM:");

        añoNacNiñLbl.setText("AÑO:");

        javax.swing.GroupLayout datosEstdLayout = new javax.swing.GroupLayout(datosEstd);
        datosEstd.setLayout(datosEstdLayout);
        datosEstdLayout.setHorizontalGroup(
            datosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosEstdLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(datosEstdLayout.createSequentialGroup()
                        .addComponent(lbl_estadoNiñ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_estadoNiñ, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_munNacNiñ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_municNiñ, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_nacionalidadNiñ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_nacionalidadNiñ, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(datosEstdLayout.createSequentialGroup()
                        .addComponent(label_tallas_niñ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_camisa_niñ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_tall_camisa_niñ, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_tall_pantalon_niñ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_tall_pantalon_niñ, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_tall_zapato_niñ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_tall_zapato_niñ, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_medidas_niñ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_peso_niñ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_peso_niñ, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label_estatura_niñ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_estatura_niñ, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(datosEstdLayout.createSequentialGroup()
                        .addComponent(lbl_nombreNiñ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nombreNiñ, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_apellidoNiñ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_apellidoNiñ, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_sexoNiñ)
                        .addGap(18, 18, 18)
                        .addComponent(rdb_mascNiñ)
                        .addGap(18, 18, 18)
                        .addComponent(rdb_femNiñ))
                    .addGroup(datosEstdLayout.createSequentialGroup()
                        .addComponent(lbl_edadNiñ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(diaNacNiñLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(diaNacNiñField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mesNacNiñLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mesNacNiñField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(añoNacNiñLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(añoNacNiñField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_edad_niñ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_edad_niñ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_lugarNacNiñ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_lugarNacNiñ, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(datosEstdLayout.createSequentialGroup()
                        .addGroup(datosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, datosEstdLayout.createSequentialGroup()
                                .addComponent(lbl_edadCaminar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_edadCaminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_lateralidad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdo_diestro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdo_zurdo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdo_ambidiestro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdo_desconocido))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, datosEstdLayout.createSequentialGroup()
                                .addComponent(label_procedencia_niñ)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radio_hogar_niñ)
                                .addGap(18, 18, 18)
                                .addComponent(radio_multihogar_niñ)
                                .addGap(18, 18, 18)
                                .addComponent(radio_hogar_cuidado_niñ)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radio_mismo_plantel_niñ)
                                .addGap(18, 18, 18)
                                .addComponent(radio_otro_plantel_niñ)
                                .addGap(18, 18, 18)
                                .addComponent(radio_guarderia_niñ)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio_otros_niñ)))
                .addContainerGap(116, Short.MAX_VALUE))
        );
        datosEstdLayout.setVerticalGroup(
            datosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosEstdLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nombreNiñ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_nombreNiñ)
                    .addComponent(txt_apellidoNiñ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_apellidoNiñ)
                    .addComponent(lbl_sexoNiñ)
                    .addComponent(rdb_mascNiñ)
                    .addComponent(rdb_femNiñ))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(datosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(text_edad_niñ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label_edad_niñ)
                        .addComponent(txt_lugarNacNiñ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_lugarNacNiñ))
                    .addGroup(datosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(diaNacNiñField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(mesNacNiñField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(añoNacNiñField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(diaNacNiñLbl)
                        .addComponent(mesNacNiñLbl)
                        .addComponent(añoNacNiñLbl))
                    .addComponent(lbl_edadNiñ))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_estadoNiñ)
                    .addComponent(txt_estadoNiñ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_municNiñ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_munNacNiñ)
                    .addComponent(txt_nacionalidadNiñ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_nacionalidadNiñ))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(datosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radio_hogar_niñ)
                        .addComponent(radio_hogar_cuidado_niñ)
                        .addComponent(radio_mismo_plantel_niñ)
                        .addComponent(radio_guarderia_niñ)
                        .addComponent(radio_otro_plantel_niñ)
                        .addComponent(radio_otros_niñ)
                        .addComponent(label_procedencia_niñ))
                    .addComponent(radio_multihogar_niñ))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_tallas_niñ)
                    .addComponent(label_camisa_niñ)
                    .addComponent(text_tall_camisa_niñ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_tall_pantalon_niñ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_tall_pantalon_niñ)
                    .addComponent(label_tall_zapato_niñ)
                    .addComponent(text_tall_zapato_niñ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_medidas_niñ)
                    .addComponent(label_peso_niñ)
                    .addComponent(text_peso_niñ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_estatura_niñ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_estatura_niñ))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_edadCaminar)
                    .addComponent(txt_edadCaminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_lateralidad)
                    .addComponent(rdo_diestro)
                    .addComponent(rdo_zurdo)
                    .addComponent(rdo_ambidiestro)
                    .addComponent(rdo_desconocido))
                .addContainerGap(118, Short.MAX_VALUE))
        );

        panelDatosEstd.addTab("Datos Personales", datosEstd);

        diagEstd.setBackground(new java.awt.Color(204, 255, 204));

        lbl_prmParto.setText("Presento algun problema durante el parto:");

        rdo_noPrmParto.setText("No");

        rdo_siPrmParto.setText("Si");

        lbl_promPartoCual.setText("Cual?");

        lbl_prbDesarr.setText("Presento problemas durante su desarrollo:");

        lbl_motor.setText("Motor");

        rdo_noPrmMotr.setText("No");

        rdo_siPromMotr.setText("Si");

        label_motor_cual.setText("Cual?");

        label_lenguaje.setText("Lenguaje");

        rdo_noPrmLeng.setText("No");

        rdo_siPromLeng.setText("Si");

        label_lenguaje_cual.setText("Cual?");

        label_cognitivo.setText("Cognitivo");

        rdo_noPromCog.setText("No");

        rdo_siPromCog.setText("Si");
        rdo_siPromCog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_siPromCogActionPerformed(evt);
            }
        });

        label_cognitivo_cual.setText("Cual?");

        label_alergia.setText("Es alergico a algo:");

        rdo_noAlergia.setText("No");

        rdo_siAlergia.setText("Si");

        label_alergia_cual.setText("Cual?");

        label_alergiaMed.setText("Es alergico a algun medicamento:");

        rdo_noAlergiaMed.setText("No");

        rdo_siAlergiaMed.setText("Si");

        label_alergiaMed_cual.setText("Cual?");

        label_condicion.setText("<html>Presenta su niño(a) actualmente alguna condicion que amerite del conocimiento del docente:\n</html>");

        rdo_noCondExtra.setText("No");

        rdo_siCondExtra.setText("Si");

        label_condicion_cual.setText("Cual?");

        lbl_embPlan.setText("Embarazo planificado");

        rdo_siEmbPLan.setText("Si");

        rdo_noEmbPlan.setText("No");

        lbl_enfPadecidas.setText("Enfermedades padecidas:");

        lbl_grpoSang.setText("Grupo sanguineo");

        txt_grpoSang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_grpoSangActionPerformed(evt);
            }
        });

        lbl_fiebreAlta.setText("En caso de fiebre alta que medicamenteo ese le suministra:");

        txt_fiebreAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_fiebreAltaActionPerformed(evt);
            }
        });

        lbl_vac.setText("Vacunas administradas:");

        chk_bcg.setText("BCG");

        chk_triple.setText("TRIPLE");
        chk_triple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_tripleActionPerformed(evt);
            }
        });

        chk_polio.setText("POLIO");

        chk_tifus.setText("TIFUS");

        chk_viruela.setText("Viruela");

        lbl_otraVac.setText("Otras");

        lbl_medicTratante.setText("Medico tratante");

        lbl_tlfMedicTrat.setText("Tlf Medico tratante");

        javax.swing.GroupLayout diagEstdLayout = new javax.swing.GroupLayout(diagEstd);
        diagEstd.setLayout(diagEstdLayout);
        diagEstdLayout.setHorizontalGroup(
            diagEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(diagEstdLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(diagEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(diagEstdLayout.createSequentialGroup()
                        .addComponent(lbl_medicTratante)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_medicoTratante))
                    .addGroup(diagEstdLayout.createSequentialGroup()
                        .addComponent(label_alergiaMed)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdo_siAlergiaMed)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdo_noAlergiaMed)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_alergiaMed_cual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_alergia_cual1))
                    .addGroup(diagEstdLayout.createSequentialGroup()
                        .addComponent(lbl_prmParto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdo_siPrmParto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdo_noPrmParto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_promPartoCual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_prmPartocual))
                    .addGroup(diagEstdLayout.createSequentialGroup()
                        .addComponent(label_alergia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdo_siAlergia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdo_noAlergia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_alergia_cual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_alergia_cual))
                    .addGroup(diagEstdLayout.createSequentialGroup()
                        .addComponent(label_cognitivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdo_siPromCog)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdo_noPromCog)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_cognitivo_cual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_cognitivo_cual))
                    .addGroup(diagEstdLayout.createSequentialGroup()
                        .addComponent(label_lenguaje)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdo_siPromLeng)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdo_noPrmLeng)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_lenguaje_cual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_lenguaje_cual))
                    .addGroup(diagEstdLayout.createSequentialGroup()
                        .addComponent(lbl_motor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdo_siPromMotr)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdo_noPrmMotr)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_motor_cual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_motor_cual, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(diagEstdLayout.createSequentialGroup()
                        .addComponent(lbl_embPlan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdo_siEmbPLan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdo_noEmbPlan))
                    .addComponent(lbl_prbDesarr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(diagEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(diagEstdLayout.createSequentialGroup()
                        .addComponent(label_condicion, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdo_siCondExtra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdo_noCondExtra))
                    .addComponent(lbl_vac)
                    .addGroup(diagEstdLayout.createSequentialGroup()
                        .addComponent(lbl_tlfMedicTrat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_tlfMedicoTrat))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, diagEstdLayout.createSequentialGroup()
                        .addComponent(chk_bcg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chk_triple)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chk_polio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chk_tifus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chk_viruela)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_otraVac)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_otraVac))
                    .addGroup(diagEstdLayout.createSequentialGroup()
                        .addComponent(label_condicion_cual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_condicion_cual))
                    .addGroup(diagEstdLayout.createSequentialGroup()
                        .addComponent(lbl_enfPadecidas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_enfPadecidas))
                    .addGroup(diagEstdLayout.createSequentialGroup()
                        .addComponent(lbl_grpoSang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_grpoSang))
                    .addComponent(lbl_fiebreAlta)
                    .addComponent(txt_fiebreAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        diagEstdLayout.setVerticalGroup(
            diagEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(diagEstdLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(diagEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(diagEstdLayout.createSequentialGroup()
                        .addGroup(diagEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_embPlan)
                            .addComponent(rdo_siEmbPLan)
                            .addComponent(rdo_noEmbPlan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(diagEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_prmParto)
                            .addComponent(lbl_promPartoCual)
                            .addComponent(txt_prmPartocual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdo_siPrmParto)
                            .addComponent(rdo_noPrmParto))
                        .addGroup(diagEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(diagEstdLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(diagEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_motor)
                                    .addComponent(label_motor_cual)
                                    .addComponent(text_motor_cual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdo_siPromMotr)
                                    .addComponent(rdo_noPrmMotr)))
                            .addGroup(diagEstdLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_prbDesarr)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(diagEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_lenguaje_cual)
                            .addComponent(text_lenguaje_cual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_lenguaje)
                            .addComponent(rdo_siPromLeng)
                            .addComponent(rdo_noPrmLeng))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(diagEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(diagEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(label_cognitivo_cual)
                                .addComponent(label_cognitivo)
                                .addComponent(rdo_siPromCog)
                                .addComponent(rdo_noPromCog))
                            .addComponent(text_cognitivo_cual, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(diagEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_alergia_cual)
                            .addComponent(text_alergia_cual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_alergia)
                            .addComponent(rdo_siAlergia)
                            .addComponent(rdo_noAlergia)))
                    .addGroup(diagEstdLayout.createSequentialGroup()
                        .addGroup(diagEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdo_siCondExtra)
                            .addComponent(rdo_noCondExtra)
                            .addComponent(label_condicion, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(diagEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_condicion_cual)
                            .addComponent(text_condicion_cual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(diagEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_enfPadecidas)
                            .addComponent(txt_enfPadecidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(diagEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_grpoSang)
                            .addComponent(txt_grpoSang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_fiebreAlta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_fiebreAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_vac)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(diagEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_alergiaMed_cual)
                    .addComponent(text_alergia_cual1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_alergiaMed)
                    .addComponent(rdo_siAlergiaMed)
                    .addComponent(rdo_noAlergiaMed)
                    .addComponent(chk_bcg)
                    .addComponent(chk_triple)
                    .addComponent(chk_polio)
                    .addComponent(chk_tifus)
                    .addComponent(chk_viruela)
                    .addComponent(lbl_otraVac)
                    .addComponent(txt_otraVac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(diagEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(diagEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_tlfMedicTrat)
                        .addComponent(txt_tlfMedicoTrat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(diagEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_medicTratante)
                        .addComponent(txt_medicoTratante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        panelDatosEstd.addTab("Datos de Salud", diagEstd);

        otros.setBackground(new java.awt.Color(204, 255, 204));

        lbl_cmeAyda.setText("Come ayudado por un Adulto");

        rdo_siCmeAyda.setText("Si");

        rdo_noCmeAyda.setText("No");

        lbl_buenAptito.setText("Tiene buen Apetito");

        rdo_siBuenApetito.setText("Si");

        rdo_noBuenApetito.setText("No");

        lbl_horaAcostar.setText("A que hora se acuesta");

        lbl_horaLevantar.setText("A que hora se levanta");

        lbl_duermTarde.setText("Duerme en la tarde");

        rdo_duermTardeSi.setText("Si");

        rdo_duermTardeNo.setText("No");

        rdo_duermTardeAveces.setText("Aveces");

        lbl_qnDuermeNino.setText("Con quien duerme el niño");

        lbl_chupaDedo.setText("Chupa dedo");

        lbl_chupaDedoSi.setText("Si");

        lbl_chupaDedoNo.setText("No");
        lbl_chupaDedoNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_chupaDedoNoActionPerformed(evt);
            }
        });

        lbl_chupaDedoAvece.setText("Aveces");

        lbl_edadDejPan.setText("A que edad dejo de usar pañales");

        lbl_orinaRopaDia.setText("Se orina en la ropa durante el dia");

        rdo_orinaRopaDiaSi.setText("Si");

        rdo_orinaRopaDiaNo.setText("No");

        rdo_orinaRopaDiaAvece.setText("Aveces");

        lbl_orinaRopaNoch.setText("Se orina en la ropa durante la noche");

        rdo_orinaRopaNocheAvece.setText("Aveces");
        rdo_orinaRopaNocheAvece.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_orinaRopaNocheAveceActionPerformed(evt);
            }
        });

        rdo_orinaRopaNocheNo.setText("No");

        rdo_orinaRopaNocheSi.setText("Si");

        lbl_evacDia.setText("Evacua durante el dia");

        rdo_evacDiaSi.setText("Si");

        rdo_evacDiaNo.setText("No");

        rdo_evacDiaAvece.setText("Aveces");

        lbl_aseaSolo.setText("Se asea solo luego del baño");

        rdo_aseaSoloSi.setText("Si");

        rdo_aseaSoloNo.setText("No");
        rdo_aseaSoloNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_aseaSoloNoActionPerformed(evt);
            }
        });

        cBoxHoraAc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        cBoxMinAc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        cBoxHoraLev.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        cBoxMinLev.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        lbl_horaAcostar1.setText("Hora:");

        lbl_horaAcostar2.setText("Hora:");

        lbl_horaAcostar3.setText("Minuto:");

        lbl_horaAcostar4.setText("Minuto:");

        javax.swing.GroupLayout otrosLayout = new javax.swing.GroupLayout(otros);
        otros.setLayout(otrosLayout);
        otrosLayout.setHorizontalGroup(
            otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(otrosLayout.createSequentialGroup()
                        .addComponent(lbl_chupaDedo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_chupaDedoSi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_chupaDedoNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_chupaDedoAvece))
                    .addGroup(otrosLayout.createSequentialGroup()
                        .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(otrosLayout.createSequentialGroup()
                                    .addComponent(lbl_cmeAyda)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rdo_siCmeAyda)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rdo_noCmeAyda))
                                .addGroup(otrosLayout.createSequentialGroup()
                                    .addComponent(lbl_buenAptito)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rdo_siBuenApetito)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rdo_noBuenApetito))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, otrosLayout.createSequentialGroup()
                                    .addComponent(lbl_qnDuermeNino)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_qnDuermeNino))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, otrosLayout.createSequentialGroup()
                                    .addComponent(lbl_duermTarde)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rdo_duermTardeSi)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rdo_duermTardeNo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rdo_duermTardeAveces)))
                            .addGroup(otrosLayout.createSequentialGroup()
                                .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_horaLevantar)
                                    .addComponent(lbl_horaAcostar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_horaAcostar2)
                                    .addComponent(lbl_horaAcostar1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cBoxHoraAc, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cBoxHoraLev, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_horaAcostar3)
                                    .addComponent(lbl_horaAcostar4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cBoxMinAc, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cBoxMinLev, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(104, 104, 104)
                        .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(otrosLayout.createSequentialGroup()
                                .addComponent(lbl_evacDia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdo_evacDiaSi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdo_evacDiaNo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdo_evacDiaAvece))
                            .addGroup(otrosLayout.createSequentialGroup()
                                .addComponent(lbl_aseaSolo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdo_aseaSoloSi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdo_aseaSoloNo))
                            .addGroup(otrosLayout.createSequentialGroup()
                                .addComponent(lbl_orinaRopaNoch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdo_orinaRopaNocheSi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdo_orinaRopaNocheNo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdo_orinaRopaNocheAvece))
                            .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(otrosLayout.createSequentialGroup()
                                    .addComponent(lbl_edadDejPan)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_edadDejPan))
                                .addGroup(otrosLayout.createSequentialGroup()
                                    .addComponent(lbl_orinaRopaDia)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rdo_orinaRopaDiaSi)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rdo_orinaRopaDiaNo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rdo_orinaRopaDiaAvece))))))
                .addContainerGap(174, Short.MAX_VALUE))
        );
        otrosLayout.setVerticalGroup(
            otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_cmeAyda)
                    .addComponent(rdo_siCmeAyda)
                    .addComponent(rdo_noCmeAyda)
                    .addComponent(lbl_edadDejPan)
                    .addComponent(txt_edadDejPan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(otrosLayout.createSequentialGroup()
                        .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_buenAptito)
                            .addComponent(rdo_siBuenApetito)
                            .addComponent(rdo_noBuenApetito))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(otrosLayout.createSequentialGroup()
                                .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cBoxHoraAc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl_horaAcostar1))
                                    .addComponent(lbl_horaAcostar, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cBoxHoraLev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_horaAcostar2)
                                    .addComponent(lbl_horaLevantar)))
                            .addGroup(otrosLayout.createSequentialGroup()
                                .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cBoxMinAc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_horaAcostar3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cBoxMinLev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_horaAcostar4))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_duermTarde)
                            .addComponent(rdo_duermTardeSi)
                            .addComponent(rdo_duermTardeNo)
                            .addComponent(rdo_duermTardeAveces)))
                    .addGroup(otrosLayout.createSequentialGroup()
                        .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_orinaRopaDia)
                            .addComponent(rdo_orinaRopaDiaSi)
                            .addComponent(rdo_orinaRopaDiaNo)
                            .addComponent(rdo_orinaRopaDiaAvece))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_orinaRopaNoch)
                            .addComponent(rdo_orinaRopaNocheSi)
                            .addComponent(rdo_orinaRopaNocheNo)
                            .addComponent(rdo_orinaRopaNocheAvece))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_evacDia)
                            .addComponent(rdo_evacDiaSi)
                            .addComponent(rdo_evacDiaNo)
                            .addComponent(rdo_evacDiaAvece))
                        .addGap(10, 10, 10)
                        .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_aseaSolo)
                            .addComponent(rdo_aseaSoloSi)
                            .addComponent(rdo_aseaSoloNo))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_qnDuermeNino)
                    .addComponent(txt_qnDuermeNino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_chupaDedo)
                    .addComponent(lbl_chupaDedoSi)
                    .addComponent(lbl_chupaDedoNo)
                    .addComponent(lbl_chupaDedoAvece))
                .addContainerGap(89, Short.MAX_VALUE))
        );

        panelDatosEstd.addTab("Otros", otros);

        dat_madre.setBackground(new java.awt.Color(255, 204, 204));
        dat_madre.setPreferredSize(new java.awt.Dimension(600, 420));
        dat_madre.setRequestFocusEnabled(false);

        label_apellido_madre.setText("Apellidos");

        text_apellidos_madre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_apellidos_madreActionPerformed(evt);
            }
        });

        label_nombes_madre.setText("Nombres");

        text_nombres_madre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_nombres_madreActionPerformed(evt);
            }
        });

        label_ci_madre.setText("C.I");

        label_estadociv_madre.setText("Estado Civil");

        check_s_madre.setText("S");
        check_s_madre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_s_madreActionPerformed(evt);
            }
        });

        check_c_madre.setText("C");
        check_c_madre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_c_madreActionPerformed(evt);
            }
        });

        check_d_madre.setText("D");
        check_d_madre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_d_madreActionPerformed(evt);
            }
        });

        check_v_madre.setText("V");
        check_v_madre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_v_madreActionPerformed(evt);
            }
        });

        label_fec_nac_madre.setText("Fecha Nacimiento");

        label_edad_madre.setText("Edad");

        label_lugar_nac_madre.setText("Lugar de Nacimiento");

        text_lugar_nac_madre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_lugar_nac_madreActionPerformed(evt);
            }
        });

        label_nacionalidad_madre.setText("Nacionalidad");

        check_ven_madre.setText("V");
        check_ven_madre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_ven_madreActionPerformed(evt);
            }
        });

        check_ext_madre.setText("E");
        check_ext_madre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_ext_madreActionPerformed(evt);
            }
        });

        label_direc_hab_madre.setText("Direccion de Habitacion");

        text_direc_hab_madre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_direc_hab_madreActionPerformed(evt);
            }
        });

        label_grado_madre.setText("Grado de instruccion:");

        check_no_bachiller_madre.setText("No Bachiller");
        check_no_bachiller_madre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_no_bachiller_madreActionPerformed(evt);
            }
        });

        chec_bachiller_madre.setText("Bachiller");
        chec_bachiller_madre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chec_bachiller_madreActionPerformed(evt);
            }
        });

        check_tsu_madre.setText("TSU");
        check_tsu_madre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_tsu_madreActionPerformed(evt);
            }
        });

        check_superior_madre.setText("Superior");
        check_superior_madre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_superior_madreActionPerformed(evt);
            }
        });

        label_ocupacion_madre.setText("Ocupacion");

        label_direc_trabaj_madre.setText("Direccion de Trabajo");

        text_direc_trabj_madre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_direc_trabj_madreActionPerformed(evt);
            }
        });

        label_correo_madre.setText("Correo Electronico");

        text_correo_madre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_correo_madreActionPerformed(evt);
            }
        });

        label_tlf_madre.setText("TLF");

        text_tlf_madre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_tlf_madreActionPerformed(evt);
            }
        });

        repLegalLabel.setText("Representante legal");

        repLegalMadreCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repLegalMadreCheckboxActionPerformed(evt);
            }
        });

        parentescoMadreTexto.setText("Parentesco");

        fieldParentescoMadre.setEditable(false);
        fieldParentescoMadre.setText("Madre");
        fieldParentescoMadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldParentescoMadreActionPerformed(evt);
            }
        });

        diaNacNiñLbl1.setText("DD:");

        mesNacNiñLbl1.setText("MM:");

        añoNacNiñLbl1.setText("AÑO:");

        btn_aggImgMad.setText("Agregar Imagen");
        btn_aggImgMad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aggImgMadActionPerformed(evt);
            }
        });

        img_infMad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_infMad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/set1/user-line_240.png"))); // NOI18N
        img_infMad.setMinimumSize(new java.awt.Dimension(0, 0));
        img_infMad.setPreferredSize(new java.awt.Dimension(196, 196));

        lbl_ce2.setText("Ruta:");

        txt_rutImgMad.setEditable(false);

        javax.swing.GroupLayout dat_madreLayout = new javax.swing.GroupLayout(dat_madre);
        dat_madre.setLayout(dat_madreLayout);
        dat_madreLayout.setHorizontalGroup(
            dat_madreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dat_madreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dat_madreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dat_madreLayout.createSequentialGroup()
                        .addGroup(dat_madreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(dat_madreLayout.createSequentialGroup()
                                .addComponent(label_apellido_madre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_apellidos_madre, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label_nombes_madre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_nombres_madre, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label_ci_madre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_ci_madre, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(dat_madreLayout.createSequentialGroup()
                                .addComponent(label_fec_nac_madre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(diaNacNiñLbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(diaNacMadField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mesNacNiñLbl1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mesNacMadField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(añoNacNiñLbl1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(añoNacMadField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(dat_madreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dat_madreLayout.createSequentialGroup()
                                .addComponent(label_estadociv_madre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(check_s_madre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_c_madre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_d_madre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_v_madre))
                            .addGroup(dat_madreLayout.createSequentialGroup()
                                .addComponent(label_edad_madre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_edad_madre, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label_lugar_nac_madre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_lugar_nac_madre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label_nacionalidad_madre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_ven_madre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_ext_madre))))
                    .addGroup(dat_madreLayout.createSequentialGroup()
                        .addComponent(label_direc_hab_madre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_direc_hab_madre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(repLegalLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(repLegalMadreCheckbox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(parentescoMadreTexto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldParentescoMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dat_madreLayout.createSequentialGroup()
                        .addGroup(dat_madreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(dat_madreLayout.createSequentialGroup()
                                .addComponent(label_grado_madre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_no_bachiller_madre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chec_bachiller_madre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_tsu_madre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_superior_madre))
                            .addGroup(dat_madreLayout.createSequentialGroup()
                                .addComponent(label_ocupacion_madre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_ocupacion_madre, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label_direc_trabaj_madre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_direc_trabj_madre, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dat_madreLayout.createSequentialGroup()
                                .addComponent(label_correo_madre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_correo_madre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label_tlf_madre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_tlf_madre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(173, 173, 173)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 244, Short.MAX_VALUE)
                        .addComponent(lbl_ce2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_rutImgMad, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_aggImgMad, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(img_infMad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        dat_madreLayout.setVerticalGroup(
            dat_madreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dat_madreLayout.createSequentialGroup()
                .addGroup(dat_madreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(dat_madreLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(dat_madreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(text_apellidos_madre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_apellido_madre)
                            .addComponent(text_nombres_madre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_nombes_madre)
                            .addComponent(text_ci_madre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_ci_madre)
                            .addComponent(label_estadociv_madre)
                            .addComponent(check_s_madre)
                            .addComponent(check_c_madre)
                            .addComponent(check_d_madre)
                            .addComponent(check_v_madre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dat_madreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dat_madreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(diaNacMadField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(mesNacMadField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(añoNacMadField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(diaNacNiñLbl1)
                                .addComponent(mesNacNiñLbl1)
                                .addComponent(añoNacNiñLbl1))
                            .addGroup(dat_madreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(text_edad_madre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label_edad_madre)
                                .addComponent(label_fec_nac_madre)
                                .addComponent(text_lugar_nac_madre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label_lugar_nac_madre)
                                .addComponent(label_nacionalidad_madre)
                                .addComponent(check_ven_madre)
                                .addComponent(check_ext_madre)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dat_madreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dat_madreLayout.createSequentialGroup()
                                .addGroup(dat_madreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(dat_madreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label_direc_hab_madre)
                                        .addComponent(text_direc_hab_madre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(repLegalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dat_madreLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(dat_madreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(parentescoMadreTexto)
                                            .addComponent(fieldParentescoMadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(repLegalMadreCheckbox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(dat_madreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label_grado_madre)
                                    .addComponent(check_no_bachiller_madre)
                                    .addComponent(chec_bachiller_madre)
                                    .addComponent(check_tsu_madre)
                                    .addComponent(check_superior_madre))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(dat_madreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(text_ocupacion_madre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label_ocupacion_madre)
                                    .addComponent(label_direc_trabaj_madre)
                                    .addComponent(text_direc_trabj_madre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(dat_madreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(dat_madreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label_tlf_madre)
                                        .addComponent(text_tlf_madre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(dat_madreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label_correo_madre)
                                        .addComponent(text_correo_madre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dat_madreLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(dat_madreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(dat_madreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbl_ce2)
                                        .addComponent(txt_rutImgMad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btn_aggImgMad)))))
                    .addComponent(img_infMad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        panelRepresentanteEstd.addTab("Datos Madre", dat_madre);

        dat_padre.setBackground(new java.awt.Color(204, 255, 255));
        dat_padre.setPreferredSize(new java.awt.Dimension(600, 450));

        label_apellido_padre.setText("Apellidos");

        text_apellidos_padre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_apellidos_padreActionPerformed(evt);
            }
        });

        label_nombes_padre.setText("Nombres");

        text_nombres_padre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_nombres_padreActionPerformed(evt);
            }
        });

        label_ci_padre.setText("C.I");

        label_estadociv_padre.setText("Estado Civil");

        check_s_padre.setText("S");
        check_s_padre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_s_padreActionPerformed(evt);
            }
        });

        check_c_padre.setText("C");
        check_c_padre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_c_padreActionPerformed(evt);
            }
        });

        check_d_padre.setText("D");
        check_d_padre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_d_padreActionPerformed(evt);
            }
        });

        check_v_padre.setText("V");
        check_v_padre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_v_padreActionPerformed(evt);
            }
        });

        label_fec_nac_padre.setText("Fecha Nacimiento");

        label_edad_padre.setText("Edad");

        label_lugar_nac_padre.setText("Lugar de Nacimiento");

        text_lugar_nac_padre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_lugar_nac_padreActionPerformed(evt);
            }
        });

        label_nacionalidad_padre.setText("Nacionalidad");

        check_ven_padre.setText("V");
        check_ven_padre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_ven_padreActionPerformed(evt);
            }
        });

        check_ext_padre.setText("E");
        check_ext_padre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_ext_padreActionPerformed(evt);
            }
        });

        label_direc_hab_padre.setText("Direccion de Habitacion");

        text_direc_hab_padre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_direc_hab_padreActionPerformed(evt);
            }
        });

        label_grado_padre.setText("Grado de instruccion:");

        check_no_bachiller_padre.setText("No Bachiller");
        check_no_bachiller_padre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_no_bachiller_padreActionPerformed(evt);
            }
        });

        chec_bachiller_padre.setText("Bachiller");
        chec_bachiller_padre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chec_bachiller_padreActionPerformed(evt);
            }
        });

        check_tsu_padre.setText("TSU");
        check_tsu_padre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_tsu_padreActionPerformed(evt);
            }
        });

        check_superior_padre.setText("Superior");
        check_superior_padre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_superior_padreActionPerformed(evt);
            }
        });

        label_ocupacion_padre.setText("Ocupacion");

        label_direc_trabaj_padre.setText("Direccion de Trabajo");

        label_correo_padre.setText("Correo Electronico");

        text_correo_padre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_correo_padreActionPerformed(evt);
            }
        });

        label_tlf_padre.setText("TLF");

        text_tlf_padre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_tlf_padreActionPerformed(evt);
            }
        });

        repLegalPadre.setText("Representante legal");

        repLegalPadreCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repLegalPadreCheckboxActionPerformed(evt);
            }
        });

        parentescoPadreTexto.setText("Parentesco");

        fieldParentescoPadre.setEditable(false);
        fieldParentescoPadre.setText("Padre");
        fieldParentescoPadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldParentescoPadreActionPerformed(evt);
            }
        });

        diaNacNiñLbl2.setText("DD:");

        mesNacNiñLbl2.setText("MM:");

        añoNacNiñLbl2.setText("AÑO:");

        txt_rutImgPadre.setEditable(false);

        lbl_ce3.setText("Ruta:");

        btn_aggImgPadre.setText("Agregar Imagen");
        btn_aggImgPadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aggImgPadreActionPerformed(evt);
            }
        });

        img_infPadre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_infPadre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/set1/user-line_240.png"))); // NOI18N
        img_infPadre.setMinimumSize(new java.awt.Dimension(0, 0));
        img_infPadre.setPreferredSize(new java.awt.Dimension(196, 196));

        javax.swing.GroupLayout dat_padreLayout = new javax.swing.GroupLayout(dat_padre);
        dat_padre.setLayout(dat_padreLayout);
        dat_padreLayout.setHorizontalGroup(
            dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dat_padreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dat_padreLayout.createSequentialGroup()
                        .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dat_padreLayout.createSequentialGroup()
                                .addComponent(label_grado_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_no_bachiller_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chec_bachiller_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_tsu_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_superior_padre))
                            .addGroup(dat_padreLayout.createSequentialGroup()
                                .addComponent(label_direc_hab_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_direc_hab_padre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(repLegalPadre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(repLegalPadreCheckbox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(parentescoPadreTexto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fieldParentescoPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(dat_padreLayout.createSequentialGroup()
                                .addComponent(label_ocupacion_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_ocupacion_padre, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label_direc_trabaj_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_direc_trabj_padre, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(dat_padreLayout.createSequentialGroup()
                                .addComponent(label_apellido_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_apellidos_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label_nombes_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_nombres_padre, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label_ci_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_ci_padre, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label_estadociv_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(check_s_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_c_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_d_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_v_padre))
                            .addGroup(dat_padreLayout.createSequentialGroup()
                                .addComponent(label_fec_nac_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(diaNacNiñLbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(diaNacPadreField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mesNacNiñLbl2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mesNacPadreField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(añoNacNiñLbl2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(añoNacPadreField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(label_edad_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_edad_padre, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label_lugar_nac_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_lugar_nac_padre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label_nacionalidad_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_ven_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_ext_padre)))
                        .addGap(67, 67, 67))
                    .addGroup(dat_padreLayout.createSequentialGroup()
                        .addComponent(label_correo_padre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_correo_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(label_tlf_padre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_tlf_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_ce3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_rutImgPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_aggImgPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(img_infPadre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        dat_padreLayout.setVerticalGroup(
            dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dat_padreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dat_padreLayout.createSequentialGroup()
                        .addComponent(img_infPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(dat_padreLayout.createSequentialGroup()
                        .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(text_apellidos_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_apellido_padre)
                            .addComponent(text_nombres_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_nombes_padre)
                            .addComponent(text_ci_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_ci_padre)
                            .addComponent(label_estadociv_padre)
                            .addComponent(check_s_padre)
                            .addComponent(check_c_padre)
                            .addComponent(check_d_padre)
                            .addComponent(check_v_padre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(text_edad_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label_edad_padre)
                                .addComponent(text_lugar_nac_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label_lugar_nac_padre)
                                .addComponent(label_nacionalidad_padre)
                                .addComponent(check_ven_padre)
                                .addComponent(check_ext_padre))
                            .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(diaNacPadreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(mesNacPadreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(añoNacPadreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(diaNacNiñLbl2)
                                .addComponent(mesNacNiñLbl2)
                                .addComponent(añoNacNiñLbl2))
                            .addComponent(label_fec_nac_padre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(repLegalPadre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label_direc_hab_padre)
                                    .addComponent(text_direc_hab_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(repLegalPadreCheckbox)
                            .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(parentescoPadreTexto)
                                .addComponent(fieldParentescoPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(7, 7, 7)
                        .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_grado_padre)
                            .addComponent(check_no_bachiller_padre)
                            .addComponent(chec_bachiller_padre)
                            .addComponent(check_tsu_padre)
                            .addComponent(check_superior_padre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(text_ocupacion_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_ocupacion_padre)
                            .addComponent(label_direc_trabaj_padre)
                            .addComponent(text_direc_trabj_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_aggImgPadre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_ce3)
                                .addComponent(txt_rutImgPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(label_correo_padre)
                                .addComponent(text_correo_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label_tlf_padre)
                                .addComponent(text_tlf_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        panelRepresentanteEstd.addTab("Datos Padre", dat_padre);

        dat_rep.setBackground(new java.awt.Color(204, 204, 255));
        dat_rep.setPreferredSize(new java.awt.Dimension(600, 420));

        label_apellido_rep.setText("Apellidos");

        text_apellidos_rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_apellidos_repActionPerformed(evt);
            }
        });

        label_nombes_rep.setText("Nombres");

        text_nombres_rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_nombres_repActionPerformed(evt);
            }
        });

        label_ci_rep.setText("C.I");

        label_estadociv_rep.setText("Estado Civil");

        check_s_rep.setText("S");
        check_s_rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_s_repActionPerformed(evt);
            }
        });

        check_c_rep.setText("C");
        check_c_rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_c_repActionPerformed(evt);
            }
        });

        check_d_rep.setText("D");
        check_d_rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_d_repActionPerformed(evt);
            }
        });

        check_v_rep.setText("V");
        check_v_rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_v_repActionPerformed(evt);
            }
        });

        label_fec_nac_rep.setText("Fecha Nacimiento");

        label_edad_rep.setText("Edad");

        label_lugar_nac_rep.setText("Lugar de Nacimiento");

        text_lugar_nac_rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_lugar_nac_repActionPerformed(evt);
            }
        });

        label_nacionalidad_rep.setText("Nacionalidad");

        check_ven_rep.setText("V");
        check_ven_rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_ven_repActionPerformed(evt);
            }
        });

        check_ext_rep.setText("E");
        check_ext_rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_ext_repActionPerformed(evt);
            }
        });

        label_direc_hab_rep.setText("Direccion de Habitacion");

        text_direc_hab_rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_direc_hab_repActionPerformed(evt);
            }
        });

        label_grado_rep.setText("Grado de instruccion:");

        check_no_bachiller_rep.setText("No Bachiller");
        check_no_bachiller_rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_no_bachiller_repActionPerformed(evt);
            }
        });

        chec_bachiller_rep.setText("Bachiller");
        chec_bachiller_rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chec_bachiller_repActionPerformed(evt);
            }
        });

        check_tsu_rep.setText("TSU");
        check_tsu_rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_tsu_repActionPerformed(evt);
            }
        });

        check_superior_rep.setText("Superior");
        check_superior_rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_superior_repActionPerformed(evt);
            }
        });

        label_ocupacion_rep.setText("Ocupacion");

        text_ocupacion_rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_ocupacion_repActionPerformed(evt);
            }
        });

        label_direc_trabaj_rep.setText("Direccion de Trabajo");

        label_correo_rep.setText("Correo Electronico");

        text_correo_rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_correo_repActionPerformed(evt);
            }
        });

        label_tlf_rep.setText("TLF");

        text_tlf_rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_tlf_repActionPerformed(evt);
            }
        });

        parentescoRepresentanteTexto.setText("Parentesco");

        fieldRepresentante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldRepresentanteActionPerformed(evt);
            }
        });

        diaNacNiñLbl3.setText("DD:");

        mesNacNiñLbl3.setText("MM:");

        añoNacNiñLbl3.setText("AÑO:");

        img_infRep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_infRep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/set1/user-line_240.png"))); // NOI18N
        img_infRep.setMinimumSize(new java.awt.Dimension(0, 0));
        img_infRep.setName(""); // NOI18N
        img_infRep.setPreferredSize(new java.awt.Dimension(196, 196));

        lbl_ce4.setText("Ruta:");

        txt_rutImgRep.setEditable(false);

        btn_aggImgRep.setText("Agregar Imagen");
        btn_aggImgRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aggImgRepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dat_repLayout = new javax.swing.GroupLayout(dat_rep);
        dat_rep.setLayout(dat_repLayout);
        dat_repLayout.setHorizontalGroup(
            dat_repLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dat_repLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dat_repLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dat_repLayout.createSequentialGroup()
                        .addGroup(dat_repLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dat_repLayout.createSequentialGroup()
                                .addComponent(label_grado_rep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_no_bachiller_rep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chec_bachiller_rep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_tsu_rep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_superior_rep))
                            .addGroup(dat_repLayout.createSequentialGroup()
                                .addComponent(label_direc_hab_rep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_direc_hab_rep, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(parentescoRepresentanteTexto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fieldRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(dat_repLayout.createSequentialGroup()
                                .addComponent(label_apellido_rep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_apellidos_rep, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label_nombes_rep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_nombres_rep, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label_ci_rep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_ci_rep, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label_estadociv_rep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(check_s_rep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_c_rep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_d_rep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_v_rep))
                            .addGroup(dat_repLayout.createSequentialGroup()
                                .addComponent(label_ocupacion_rep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_ocupacion_rep, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label_direc_trabaj_rep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_direc_trabj_rep, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(dat_repLayout.createSequentialGroup()
                                .addComponent(label_fec_nac_rep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(diaNacNiñLbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(diaNacRepField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mesNacNiñLbl3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mesNacRepField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(añoNacNiñLbl3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(añoNacRepField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label_edad_rep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_edad_rep, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label_lugar_nac_rep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_lugar_nac_rep, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label_nacionalidad_rep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_ven_rep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_ext_rep)))
                        .addGap(85, 85, 85))
                    .addGroup(dat_repLayout.createSequentialGroup()
                        .addComponent(label_correo_rep)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_correo_rep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(label_tlf_rep)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_tlf_rep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_ce4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_rutImgRep, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_aggImgRep, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(img_infRep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dat_repLayout.setVerticalGroup(
            dat_repLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dat_repLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dat_repLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_apellidos_rep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_apellido_rep)
                    .addComponent(text_nombres_rep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_nombes_rep)
                    .addComponent(text_ci_rep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_ci_rep)
                    .addComponent(label_estadociv_rep)
                    .addComponent(check_s_rep)
                    .addComponent(check_c_rep)
                    .addComponent(check_d_rep)
                    .addComponent(check_v_rep))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dat_repLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dat_repLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(text_edad_rep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label_edad_rep)
                        .addComponent(text_lugar_nac_rep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label_lugar_nac_rep)
                        .addComponent(label_nacionalidad_rep)
                        .addComponent(check_ven_rep)
                        .addComponent(check_ext_rep))
                    .addGroup(dat_repLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(diaNacRepField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(mesNacRepField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(añoNacRepField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(diaNacNiñLbl3)
                        .addComponent(mesNacNiñLbl3)
                        .addComponent(añoNacNiñLbl3))
                    .addComponent(label_fec_nac_rep))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dat_repLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dat_repLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(parentescoRepresentanteTexto)
                        .addComponent(fieldRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dat_repLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_direc_hab_rep)
                        .addComponent(text_direc_hab_rep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dat_repLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_grado_rep)
                    .addComponent(check_no_bachiller_rep)
                    .addComponent(chec_bachiller_rep)
                    .addComponent(check_tsu_rep)
                    .addComponent(check_superior_rep))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dat_repLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_ocupacion_rep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_ocupacion_rep)
                    .addComponent(label_direc_trabaj_rep)
                    .addComponent(text_direc_trabj_rep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dat_repLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dat_repLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_aggImgRep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_ce4)
                        .addComponent(txt_rutImgRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dat_repLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_correo_rep)
                        .addComponent(text_correo_rep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label_tlf_rep)
                        .addComponent(text_tlf_rep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(dat_repLayout.createSequentialGroup()
                .addComponent(img_infRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelRepresentanteEstd.addTab("Datos Representante Legal", dat_rep);

        dat_aut.setBackground(new java.awt.Color(204, 255, 255));

        lbl_autRetiro.setText("Datos autorizado para retiro:");

        tbl_autRetiro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Cedula", "Nombre", "Apellido", "Telefono 1", "Telefono 2", "Parentesco"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrl_autRetiro.setViewportView(tbl_autRetiro);

        javax.swing.GroupLayout dat_autLayout = new javax.swing.GroupLayout(dat_aut);
        dat_aut.setLayout(dat_autLayout);
        dat_autLayout.setHorizontalGroup(
            dat_autLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dat_autLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dat_autLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_autRetiro)
                    .addComponent(scrl_autRetiro, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(518, Short.MAX_VALUE))
        );
        dat_autLayout.setVerticalGroup(
            dat_autLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dat_autLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(lbl_autRetiro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrl_autRetiro, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelRepresentanteEstd.addTab("Autorizado para Retiro", dat_aut);

        dat_socioFamiliares.setBackground(new java.awt.Color(255, 204, 255));

        lbl_casa.setText("Vivienda:");

        rdio_propia.setText("Propia");

        rdio_prestada.setText("Prestada");

        rdio_alquilada.setText("Alquilada");

        lbl_tipocasa.setText("Tipo de Vivienda:");

        rdio_zinc.setText("Zinc");

        rdio_casa.setText("Casa");
        rdio_casa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdio_casaActionPerformed(evt);
            }
        });

        rdio_apartamento.setText("Apartamento");

        rdio_otro.setText("Otro");

        lbl_familiarExtra.setText("Personas que vivan en el hogar con el niño(a):");

        tbl_familiarExtra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido", "Edad", "Parentesco", "Ocupacion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrl_familiarExtra.setViewportView(tbl_familiarExtra);

        lbl_cuidaNiñoHogar.setText("Quien cuida al niño(a) en el hogar:");

        lbl_consultas.setText("Ha asistido a alguna de estas consultas:");

        chk_pspg.setText("Psicopedagogo");

        chk_psic.setText("Psicologo");

        chk_neur.setText("Neurologos");

        chk_terpLenguaje.setText("Terapias del Lenguaje");

        chk_otroConsult.setText("Otro");

        lbl_relPadres.setText("Relación civil de los padres:");

        check_s_padres.setText("S");
        check_s_padres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_s_padresActionPerformed(evt);
            }
        });

        check_c_padres.setText("C");
        check_c_padres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_c_padresActionPerformed(evt);
            }
        });

        check_d_padres.setText("D");
        check_d_padres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_d_padresActionPerformed(evt);
            }
        });

        check_v_padres.setText("V");
        check_v_padres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_v_padresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dat_socioFamiliaresLayout = new javax.swing.GroupLayout(dat_socioFamiliares);
        dat_socioFamiliares.setLayout(dat_socioFamiliaresLayout);
        dat_socioFamiliaresLayout.setHorizontalGroup(
            dat_socioFamiliaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dat_socioFamiliaresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dat_socioFamiliaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dat_socioFamiliaresLayout.createSequentialGroup()
                        .addComponent(lbl_casa)
                        .addGap(18, 18, 18)
                        .addComponent(rdio_propia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdio_prestada)
                        .addGap(18, 18, 18)
                        .addComponent(rdio_alquilada))
                    .addGroup(dat_socioFamiliaresLayout.createSequentialGroup()
                        .addComponent(lbl_tipocasa)
                        .addGap(18, 18, 18)
                        .addComponent(rdio_casa)
                        .addGap(18, 18, 18)
                        .addComponent(rdio_apartamento)
                        .addGap(18, 18, 18)
                        .addComponent(rdio_zinc)
                        .addGap(18, 18, 18)
                        .addComponent(rdio_otro))
                    .addComponent(lbl_familiarExtra)
                    .addComponent(scrl_familiarExtra, javax.swing.GroupLayout.PREFERRED_SIZE, 733, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(dat_socioFamiliaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dat_socioFamiliaresLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(dat_socioFamiliaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_consultas)
                            .addGroup(dat_socioFamiliaresLayout.createSequentialGroup()
                                .addComponent(lbl_cuidaNiñoHogar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_cuidaNiñoHogar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(dat_socioFamiliaresLayout.createSequentialGroup()
                                .addComponent(lbl_relPadres)
                                .addGap(18, 18, 18)
                                .addComponent(check_s_padres)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_c_padres)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_d_padres)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_v_padres)))
                        .addContainerGap(198, Short.MAX_VALUE))
                    .addGroup(dat_socioFamiliaresLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(dat_socioFamiliaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dat_socioFamiliaresLayout.createSequentialGroup()
                                .addComponent(chk_otroConsult)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_otroConsult, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
                            .addGroup(dat_socioFamiliaresLayout.createSequentialGroup()
                                .addComponent(chk_pspg)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chk_psic)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chk_neur)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chk_terpLenguaje)))
                        .addContainerGap())))
        );
        dat_socioFamiliaresLayout.setVerticalGroup(
            dat_socioFamiliaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dat_socioFamiliaresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dat_socioFamiliaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_casa)
                    .addComponent(rdio_propia)
                    .addComponent(rdio_prestada)
                    .addComponent(rdio_alquilada)
                    .addComponent(lbl_cuidaNiñoHogar)
                    .addComponent(txt_cuidaNiñoHogar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dat_socioFamiliaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dat_socioFamiliaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_tipocasa)
                        .addComponent(rdio_zinc)
                        .addComponent(rdio_casa)
                        .addComponent(rdio_apartamento)
                        .addComponent(rdio_otro))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dat_socioFamiliaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(dat_socioFamiliaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(check_s_padres)
                            .addComponent(check_c_padres)
                            .addComponent(check_d_padres)
                            .addComponent(check_v_padres))
                        .addComponent(lbl_relPadres)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dat_socioFamiliaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_familiarExtra)
                    .addComponent(lbl_consultas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dat_socioFamiliaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrl_familiarExtra, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addGroup(dat_socioFamiliaresLayout.createSequentialGroup()
                        .addGroup(dat_socioFamiliaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chk_pspg)
                            .addComponent(chk_psic)
                            .addComponent(chk_neur)
                            .addComponent(chk_terpLenguaje))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dat_socioFamiliaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chk_otroConsult)
                            .addComponent(txt_otroConsult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 47, Short.MAX_VALUE)))
                .addContainerGap())
        );

        panelRepresentanteEstd.addTab("Ambiente Socio Familiar", dat_socioFamiliares);

        panelDocumentosEstd.setPreferredSize(new java.awt.Dimension(600, 420));
        panelDocumentosEstd.setRequestFocusEnabled(false);

        label_originales.setText("Originales");

        label_copias.setText("Copias");

        label_partida.setText("Partidas de nacimiento");

        label_vacunas.setText("Certificado de Vacunas");

        label_cedula.setText("Cedulas de Identidad:");

        check_ci_madre.setText("Madre");

        check_ci_padre.setText("Padre");

        check_ci_representante.setText("Representante");

        label_responsable.setText("Persona responsable en la instirucion en realizar la inscripcion:");

        text_responsable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_responsableActionPerformed(evt);
            }
        });

        label_persona.setText("Persona que inscribe al niño o niña");

        label_fecha.setText("Fecha de incripcicion");

        label_observaciones.setText("Observaciones");

        scroll_observaciones.setViewportView(text_observaciones);

        btn_aceptarInscripcionNiño.setText("ACEPTAR");
        btn_aceptarInscripcionNiño.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarInscripcionNiñoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDocumentosEstdLayout = new javax.swing.GroupLayout(panelDocumentosEstd);
        panelDocumentosEstd.setLayout(panelDocumentosEstdLayout);
        panelDocumentosEstdLayout.setHorizontalGroup(
            panelDocumentosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDocumentosEstdLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDocumentosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDocumentosEstdLayout.createSequentialGroup()
                        .addComponent(label_cedula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(check_ci_madre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(check_ci_padre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(check_ci_representante))
                    .addGroup(panelDocumentosEstdLayout.createSequentialGroup()
                        .addComponent(label_originales)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(check_originales)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_copias)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(check_copias)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_partida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(check_partida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_vacunas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(check_vacunas)
                        .addGap(18, 18, 18)
                        .addComponent(label_observaciones))
                    .addGroup(panelDocumentosEstdLayout.createSequentialGroup()
                        .addComponent(label_persona)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_persona, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_fecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_fecha))
                    .addGroup(panelDocumentosEstdLayout.createSequentialGroup()
                        .addComponent(label_responsable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_responsable, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll_observaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_aceptarInscripcionNiño)
                .addGap(8, 8, 8))
        );
        panelDocumentosEstdLayout.setVerticalGroup(
            panelDocumentosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDocumentosEstdLayout.createSequentialGroup()
                .addGroup(panelDocumentosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDocumentosEstdLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelDocumentosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(scroll_observaciones)
                            .addGroup(panelDocumentosEstdLayout.createSequentialGroup()
                                .addGroup(panelDocumentosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelDocumentosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label_originales)
                                        .addComponent(check_originales)
                                        .addComponent(label_copias)
                                        .addComponent(check_copias)
                                        .addComponent(label_partida)
                                        .addComponent(check_partida)
                                        .addComponent(label_vacunas)
                                        .addComponent(check_vacunas))
                                    .addComponent(label_observaciones))
                                .addGap(9, 9, 9)
                                .addGroup(panelDocumentosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label_cedula)
                                    .addComponent(check_ci_madre)
                                    .addComponent(check_ci_padre)
                                    .addComponent(check_ci_representante))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelDocumentosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label_responsable)
                                    .addComponent(text_responsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelDocumentosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelDocumentosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label_fecha)
                                        .addComponent(text_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelDocumentosEstdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label_persona)
                                        .addComponent(text_persona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(panelDocumentosEstdLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btn_aceptarInscripcionNiño, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        btn_aggImgNiñ.setText("Agregar Imagen");
        btn_aggImgNiñ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aggImgNiñActionPerformed(evt);
            }
        });

        lbl_ce1.setText("Ruta:");

        txt_rutImgNiñ.setEditable(false);

        javax.swing.GroupLayout informacionEstudianteLayout = new javax.swing.GroupLayout(informacionEstudiante);
        informacionEstudiante.setLayout(informacionEstudianteLayout);
        informacionEstudianteLayout.setHorizontalGroup(
            informacionEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, informacionEstudianteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(informacionEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelRepresentanteEstd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, informacionEstudianteLayout.createSequentialGroup()
                        .addGroup(informacionEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(img_infEstudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(informacionEstudianteLayout.createSequentialGroup()
                                .addComponent(lbl_ce)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_ce))
                            .addComponent(btn_aggImgNiñ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(informacionEstudianteLayout.createSequentialGroup()
                                .addComponent(lbl_ce1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_rutImgNiñ, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelDatosEstd, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addComponent(panelDocumentosEstd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1324, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(headderEstudiante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1336, Short.MAX_VALUE)
        );
        informacionEstudianteLayout.setVerticalGroup(
            informacionEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informacionEstudianteLayout.createSequentialGroup()
                .addComponent(headderEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(informacionEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(informacionEstudianteLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(img_infEstudiante))
                    .addComponent(panelDatosEstd, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, informacionEstudianteLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(informacionEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_ce1)
                            .addComponent(txt_rutImgNiñ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_aggImgNiñ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(informacionEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_ce)
                            .addComponent(txt_ce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRepresentanteEstd, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelDocumentosEstd, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        headderInfNomina.setBackground(new java.awt.Color(27, 120, 101));
        headderInfNomina.setPreferredSize(new java.awt.Dimension(1000, 32));

        lbl_headderInfNomina.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_headderInfNomina.setForeground(new java.awt.Color(250, 247, 239));
        lbl_headderInfNomina.setText("INFORMACION NOMINA");

        javax.swing.GroupLayout headderInfNominaLayout = new javax.swing.GroupLayout(headderInfNomina);
        headderInfNomina.setLayout(headderInfNominaLayout);
        headderInfNominaLayout.setHorizontalGroup(
            headderInfNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headderInfNominaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_headderInfNomina)
                .addGap(464, 464, 464))
        );
        headderInfNominaLayout.setVerticalGroup(
            headderInfNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_headderInfNomina, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        imgNomina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgNomina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/set1/user-line_240.png"))); // NOI18N

        datPersNomina.setBackground(new java.awt.Color(255, 255, 204));

        lbl_ciNomina.setText("C.I");

        txt_ciNomina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ciNominaActionPerformed(evt);
            }
        });

        lbl_rifNomina.setText("R.I.F");

        lbl_nombreNomina.setText("Nombre");

        txt_nombreNomina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreNominaActionPerformed(evt);
            }
        });

        lbl_apellidoNomina.setText("Apellido");

        txt_apellidoNomina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_apellidoNominaActionPerformed(evt);
            }
        });

        lbl_sexoNomina.setText("Sexo");

        rdb_mascNomina.setText("Masculino");
        rdb_mascNomina.setToolTipText("");
        rdb_mascNomina.setActionCommand("masculino");

        rdb_femNomina.setText("femenino");

        lbl_fechNacimNomina.setText("Fecha Nacimiento");

        txt_fechNacimNomina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_fechNacimNominaActionPerformed(evt);
            }
        });

        lbl_edadNomina.setText("Edad");

        txt_edadNomina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_edadNominaActionPerformed(evt);
            }
        });

        lbl_munResNomina.setText("Municipio Residencia");

        lbl_prrqResNomina.setText("Parroquia Residencia");

        lbl_comResNomina.setText("Comunidad Residencia");

        lbl_calleResNomina.setText("Calle Residencia");

        lbl_tlf1Nomina.setText("Telefono Principal");

        lbl_tlf2Nomina.setText("Telefono Secundario");

        jCheckBox1.setText("Maestro");

        javax.swing.GroupLayout datPersNominaLayout = new javax.swing.GroupLayout(datPersNomina);
        datPersNomina.setLayout(datPersNominaLayout);
        datPersNominaLayout.setHorizontalGroup(
            datPersNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datPersNominaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datPersNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(datPersNominaLayout.createSequentialGroup()
                        .addGroup(datPersNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(datPersNominaLayout.createSequentialGroup()
                                .addGroup(datPersNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(datPersNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txt_rifNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, datPersNominaLayout.createSequentialGroup()
                                            .addComponent(lbl_nombreNomina)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txt_nombreNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(datPersNominaLayout.createSequentialGroup()
                                        .addComponent(lbl_ciNomina)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_ciNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbl_rifNomina)))
                                .addGroup(datPersNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(datPersNominaLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_apellidoNomina)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_apellidoNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(datPersNominaLayout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(lbl_sexoNomina)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdb_mascNomina)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdb_femNomina)))
                                .addGap(176, 176, 176)
                                .addComponent(jCheckBox1))
                            .addGroup(datPersNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(datPersNominaLayout.createSequentialGroup()
                                    .addComponent(lbl_fechNacimNomina)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_fechNacimNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbl_edadNomina)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_edadNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(datPersNominaLayout.createSequentialGroup()
                                    .addComponent(lbl_munResNomina)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_munResNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbl_prrqResNomina)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_prrqResNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbl_comResNomina)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_comResNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(datPersNominaLayout.createSequentialGroup()
                                    .addComponent(lbl_tlf1Nomina)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_tlf1Nomina, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addGroup(datPersNominaLayout.createSequentialGroup()
                        .addGroup(datPersNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(datPersNominaLayout.createSequentialGroup()
                                .addComponent(lbl_calleResNomina)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_calleResNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(datPersNominaLayout.createSequentialGroup()
                                .addComponent(lbl_tlf2Nomina)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_tlf2Nomina, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        datPersNominaLayout.setVerticalGroup(
            datPersNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datPersNominaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datPersNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_ciNomina)
                    .addComponent(txt_ciNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_rifNomina)
                    .addComponent(txt_rifNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_sexoNomina)
                    .addComponent(rdb_mascNomina)
                    .addComponent(rdb_femNomina)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datPersNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nombreNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_nombreNomina)
                    .addComponent(txt_apellidoNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_apellidoNomina))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datPersNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_fechNacimNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_fechNacimNomina)
                    .addComponent(txt_edadNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_edadNomina))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datPersNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_munResNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_munResNomina)
                    .addComponent(txt_prrqResNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_prrqResNomina)
                    .addComponent(txt_comResNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_comResNomina))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datPersNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_calleResNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_calleResNomina))
                .addGap(8, 8, 8)
                .addGroup(datPersNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_tlf1Nomina)
                    .addComponent(txt_tlf1Nomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datPersNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_tlf2Nomina)
                    .addComponent(txt_tlf2Nomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        datGubNomina.setBackground(new java.awt.Color(255, 204, 204));
        datGubNomina.setPreferredSize(new java.awt.Dimension(600, 420));
        datGubNomina.setRequestFocusEnabled(false);

        lbl_munVotNomina.setText("Municipio Eleccion");

        lbl_parrqVotNomina.setText("Parroquia de Eleccion");

        lbl_centrVotNomina.setText("Centro de Votacion");

        lbl_votoNomina.setText("Ya ha votado?");

        rdb_siVot.setText("Si");

        rdb_noVot.setText("No");

        lbl_jefComNomina.setText("Nombre del Jefe de la Comunidad");

        lbl_ciJefComNomina.setText("C.I del Jefe de la Comunidad");

        lbl_1x10Nomina.setText("Posee el 1x10");

        rdb_si1x10.setText("No");

        rdb_no1x10.setText("Si");

        lbl_cant1x10Nomina.setText("Cantidad de personas en el 1x10");

        javax.swing.GroupLayout datGubNominaLayout = new javax.swing.GroupLayout(datGubNomina);
        datGubNomina.setLayout(datGubNominaLayout);
        datGubNominaLayout.setHorizontalGroup(
            datGubNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datGubNominaLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(datGubNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(datGubNominaLayout.createSequentialGroup()
                        .addGroup(datGubNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_munVotNomina)
                            .addComponent(lbl_parrqVotNomina)
                            .addComponent(lbl_centrVotNomina))
                        .addGap(23, 23, 23)
                        .addGroup(datGubNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_munVotNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_parrqVotNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_centrVotNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(datGubNominaLayout.createSequentialGroup()
                        .addComponent(lbl_votoNomina)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdb_siVot)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdb_noVot)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(datGubNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_jefComNomina)
                    .addComponent(lbl_ciJefComNomina)
                    .addComponent(lbl_1x10Nomina)
                    .addComponent(lbl_cant1x10Nomina))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(datGubNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_ciJefComNomina, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(txt_jefComNomina)
                    .addComponent(txt_cant1x10Nomina)
                    .addGroup(datGubNominaLayout.createSequentialGroup()
                        .addComponent(rdb_no1x10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdb_si1x10)))
                .addGap(15, 15, 15))
        );
        datGubNominaLayout.setVerticalGroup(
            datGubNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datGubNominaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(datGubNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_munVotNomina)
                    .addComponent(txt_munVotNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_jefComNomina)
                    .addComponent(txt_jefComNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datGubNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_parrqVotNomina)
                    .addComponent(txt_parrqVotNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_ciJefComNomina)
                    .addComponent(txt_ciJefComNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datGubNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(datGubNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdb_no1x10)
                        .addComponent(rdb_si1x10))
                    .addGroup(datGubNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_centrVotNomina)
                        .addComponent(txt_centrVotNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_1x10Nomina)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datGubNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(datGubNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_votoNomina)
                        .addComponent(rdb_siVot)
                        .addComponent(rdb_noVot))
                    .addGroup(datGubNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_cant1x10Nomina)
                        .addComponent(txt_cant1x10Nomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );

        datPlantelNomina.setBackground(new java.awt.Color(204, 255, 204));

        lbl_turnoNomina.setText("Turno");

        lbl_nivelNominaMaestra.setText("Nivel");

        lbl_seccionNominaMaestra.setText("Seccion");

        lbl_estatusNomina.setText("Estatus");

        lbl_fechIngresoNomina.setText("Fecha de Ingreso");

        javax.swing.GroupLayout datPlantelNominaLayout = new javax.swing.GroupLayout(datPlantelNomina);
        datPlantelNomina.setLayout(datPlantelNominaLayout);
        datPlantelNominaLayout.setHorizontalGroup(
            datPlantelNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datPlantelNominaLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(datPlantelNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_nivelNominaMaestra)
                    .addComponent(lbl_seccionNominaMaestra)
                    .addComponent(lbl_estatusNomina)
                    .addComponent(lbl_turnoNomina))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(datPlantelNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(datPlantelNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_seccionNominaMaestra)
                        .addComponent(txt_nivelNominaMaestra)
                        .addComponent(txt_estatusNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(datPlantelNominaLayout.createSequentialGroup()
                        .addComponent(txt_turnoNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_fechIngresoNomina)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_fechaIngresoNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        datPlantelNominaLayout.setVerticalGroup(
            datPlantelNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datPlantelNominaLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(datPlantelNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_turnoNomina)
                    .addGroup(datPlantelNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_fechIngresoNomina)
                        .addComponent(txt_fechaIngresoNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_turnoNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datPlantelNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nivelNominaMaestra)
                    .addComponent(txt_nivelNominaMaestra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datPlantelNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_seccionNominaMaestra)
                    .addComponent(txt_seccionNominaMaestra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(datPlantelNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_estatusNomina)
                    .addComponent(txt_estatusNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        btn_aceptarInscripcionNomina.setText("ACEPTAR");
        btn_aceptarInscripcionNomina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarInscripcionNominaActionPerformed(evt);
            }
        });

        btn_aggImgNiñ1.setText("Agregar Imagen");
        btn_aggImgNiñ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aggImgNiñ1ActionPerformed(evt);
            }
        });

        txt_rutImgNiñ1.setEditable(false);

        lbl_ce5.setText("Ruta:");

        javax.swing.GroupLayout informacionNominaLayout = new javax.swing.GroupLayout(informacionNomina);
        informacionNomina.setLayout(informacionNominaLayout);
        informacionNominaLayout.setHorizontalGroup(
            informacionNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informacionNominaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(informacionNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(informacionNominaLayout.createSequentialGroup()
                        .addComponent(datGubNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datPlantelNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_aceptarInscripcionNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(informacionNominaLayout.createSequentialGroup()
                        .addGroup(informacionNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(informacionNominaLayout.createSequentialGroup()
                                .addComponent(lbl_ce5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_rutImgNiñ1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(informacionNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(imgNomina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_aggImgNiñ1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datPersNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(headderInfNomina, javax.swing.GroupLayout.DEFAULT_SIZE, 1252, Short.MAX_VALUE)
        );
        informacionNominaLayout.setVerticalGroup(
            informacionNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informacionNominaLayout.createSequentialGroup()
                .addComponent(headderInfNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(informacionNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(informacionNominaLayout.createSequentialGroup()
                        .addComponent(imgNomina)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(informacionNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_ce5)
                            .addComponent(txt_rutImgNiñ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_aggImgNiñ1))
                    .addComponent(datPersNomina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(informacionNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(informacionNominaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(informacionNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(datPlantelNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datGubNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(informacionNominaLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(btn_aceptarInscripcionNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IM");
        setBackground(new java.awt.Color(197, 232, 225));
        setMinimumSize(new java.awt.Dimension(1336, 800));
        setResizable(false);

        panel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracionActionPerformed
        
        menuConfiguracion config = new menuConfiguracion();
        config.setVisible(true);
        config.repaint();
        config.validate();
        
    }//GEN-LAST:event_btnConfiguracionActionPerformed

    private void btnEstudiantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstudiantesActionPerformed
        panel.removeAll();
        panel.add(busquedaEstudiantes);
        panel.repaint();
        panel.validate();
    }//GEN-LAST:event_btnEstudiantesActionPerformed

    private void btnRepresentantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRepresentantesActionPerformed
        panel.removeAll();
        panel.add(busquedaRepresentante);
        panel.repaint();
        panel.validate();
    }//GEN-LAST:event_btnRepresentantesActionPerformed

    private void btnAutorizadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAutorizadosActionPerformed
        panel.removeAll();
        panel.add(busquedaAutorizados);
        panel.repaint();
        panel.validate();
    }//GEN-LAST:event_btnAutorizadosActionPerformed

    private void btn_verBusqRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_verBusqRepActionPerformed
        
        informacionRepresentante rep = new informacionRepresentante(this, true, connectDB.fetchRepresentante((Integer) tbl_busqRep.getValueAt(tbl_busqRep.getSelectedRow(), 4))); //
        rep.setVisible(true);
        
    }//GEN-LAST:event_btn_verBusqRepActionPerformed

    private void cmb_filtroBusqRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_filtroBusqRepActionPerformed

    }//GEN-LAST:event_cmb_filtroBusqRepActionPerformed

    private void btn_verNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_verNomActionPerformed
        panel.removeAll();
        panel.add(informacionNomina);
        panel.repaint();
        panel.revalidate();
        
        for(Component cp: datPersNomina.getComponents()){
            cp.setEnabled(false);
        }
        for(Component cp: datGubNomina.getComponents()){
            cp.setEnabled(false);
        }
        for(Component cp: datPlantelNomina.getComponents()){
            cp.setEnabled(false);
        }
    }//GEN-LAST:event_btn_verNomActionPerformed

    private void cmb_filtroBusqNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_filtroBusqNomActionPerformed

    }//GEN-LAST:event_cmb_filtroBusqNomActionPerformed

    private void cmb_filtroBusqEstdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_filtroBusqEstdActionPerformed

    }//GEN-LAST:event_cmb_filtroBusqEstdActionPerformed

    private void btn_verBusqEstdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_verBusqEstdActionPerformed
        panel.removeAll();
        panel.add(informacionEstudiante);
        panel.repaint();
        panel.validate();
        
        String cEstudiante = (String) tbl_busqEstd.getValueAt(tbl_busqEstd.getSelectedRow(),3);
        
        for(Component cp: datosEstd.getComponents()){
            cp.setEnabled(false);
        }
        for(Component cp: diagEstd.getComponents()){
            cp.setEnabled(false);
        }
        for(Component cp: dat_madre.getComponents()){
            cp.setEnabled(false);
        }
        for(Component cp: dat_padre.getComponents()){
            cp.setEnabled(false);
        }
        for(Component cp: dat_rep.getComponents()){
            cp.setEnabled(false);
        }
        for(Component cp: panelDocumentosEstd.getComponents()){
            cp.setEnabled(false);
        }
        
        estudiante est = connectDB.fetchEstudiante(cEstudiante);
        socioFamiliar socFam = connectDB.fetchSocioFamiliar(cEstudiante);
        representante madre = null;
        representante padre = null;
        representante repLegal = null;
        List<familia> fams = connectDB.fetchRelFamiliar(cEstudiante);
        List<representaA> reps = connectDB.fetchRelRepresentante(cEstudiante);
        if (reps != null){
            for (representaA rep : reps){
                if (rep.getParentesco().equals((String) "madre")){
                    System.out.println("Hallada madre");
                    madre = connectDB.fetchRepresentante(rep.getRepresentante_ciRepresentante());
                }
                if (rep.getParentesco().equals((String) "padre")){
                    System.out.println("Hallada madre");
                    padre = connectDB.fetchRepresentante(rep.getRepresentante_ciRepresentante());
                }
                System.out.println("Insertando representante");
                repLegal = connectDB.fetchRepresentante(rep.getRepresentante_ciRepresentante());
            }
        }
        if (reps != null){
            System.out.println("DEBUG: Entering loop for 'reps'.");
            for (representaA rep : reps){
                System.out.println("DEBUG: Current representaA object - CI: " + rep.getRepresentante_ciRepresentante() + ", Parentesco: " + rep.getParentesco());

                if (rep.getParentesco().equals((String) "madre")){
                    System.out.println("DEBUG: Found 'madre' parentesco. Fetching mother with CI: " + rep.getRepresentante_ciRepresentante());
                    madre = connectDB.fetchRepresentante(rep.getRepresentante_ciRepresentante());
                    System.out.println("DEBUG: 'madre' object fetched. Nombres: " + (madre != null ? madre.getNombres() : "null") + ", Apellidos: " + (madre != null ? madre.getApellidos() : "null"));
                }
                // NOTE: There might be a logical error here, as 'madre' is assigned again instead of 'padre'.
                // If this is intentional, it will overwrite the 'madre' variable if both 'madre' and 'padre' parentescos are found.
                // If you intended to assign to 'padre', it should be 'padre = connectDB.fetchRepresentante(...)'.
                if (rep.getParentesco().equals((String) "padre")){
                    System.out.println("DEBUG: Found 'padre' parentesco. Fetching representative for 'padre' with CI: " + rep.getRepresentante_ciRepresentante());
                    madre = connectDB.fetchRepresentante(rep.getRepresentante_ciRepresentante()); // This line currently assigns to 'madre'
                    System.out.println("DEBUG: 'madre' (assigned from 'padre' parentesco) object fetched. Nombres: " + (madre != null ? madre.getNombres() : "null") + ", Apellidos: " + (madre != null ? madre.getApellidos() : "null"));
                }
                
                // This line will assign the last representative fetched in the loop to repLegal
                System.out.println("DEBUG: Assigning repLegal with CI: " + rep.getRepresentante_ciRepresentante());
                repLegal = connectDB.fetchRepresentante(rep.getRepresentante_ciRepresentante());
                System.out.println("DEBUG: 'repLegal' object assigned. Nombres: " + (repLegal != null ? repLegal.getNombres() : "null") + ", Apellidos: " + (repLegal != null ? repLegal.getApellidos() : "null"));
            }
        } else {
            System.out.println("DEBUG: 'reps' list is null. No representatives to process.");
        }
        List<retiraA> auts = connectDB.fetchRelAutorizado(cEstudiante);
        List<autorizado> autorizados = new ArrayList<>();
        if (auts != null){
        for (retiraA aut : auts){
                autorizados.add(connectDB.fetchAutorizado(String.valueOf((aut.getAutorizadoRetiro_ciAutorizado()))));
            }
        }
        documentos docs = connectDB.getDocumentos(cEstudiante);
        diagnostico diag = connectDB.getDiagnostico(cEstudiante);
        
                // Datos del Niño/Niña
        txt_ce.setText(est.getCe());
        txt_nombreNiñ.setText(est.getNombres());
        txt_apellidoNiñ.setText(est.getApellidos());
        text_edad_niñ.setText(String.valueOf(est.getEdad()));
        diaNacNiñField.setText(String.valueOf(est.getFechaNac().getDayOfMonth()));
        mesNacNiñField.setText(String.valueOf(est.getFechaNac().getMonthValue()));
        añoNacNiñField.setText(String.valueOf(est.getFechaNac().getYear()));
        txt_lugarNacNiñ.setText(est.getLugarNac());
        txt_nacionalidadNiñ.setText(est.getNacionalidad());
        text_tall_camisa_niñ.setText(String.valueOf(est.getTallaCamisa()));
        text_tall_pantalon_niñ.setText(String.valueOf(est.getTallaPantalon()));
        text_tall_zapato_niñ.setText(String.valueOf(est.getTallaZapato()));
        text_peso_niñ.setText(String.valueOf(est.getPeso()));
        text_estatura_niñ.setText(String.valueOf(est.getEstatura()));
        fileBytes = est.getImg(); // Assuming you have a way to set the image to a UI component
        if (fileBytes != null){
            ImageIcon iconInit = new ImageIcon(fileBytes);
            ImageIcon iconFin;
            Image img = iconInit.getImage();
            Image imgScaled = img.getScaledInstance(240, 240, Image.SCALE_SMOOTH);
            iconFin = new ImageIcon(imgScaled);
            img_infEstudiante.setIcon(iconFin);
        }  
        // Otros (from diagnostico)
        cBoxHoraAc.setSelectedItem(diag.getHoraDormir().getHour());
        cBoxMinAc.setSelectedItem(diag.getHoraDormir().getMinute());
        cBoxHoraLev.setSelectedItem(diag.getHoraLevantarse().getHour());
        cBoxMinLev.setSelectedItem(diag.getHoraLevantarse().getMinute());
        txt_qnDuermeNino.setText(diag.getConQuienDuerme());
        txt_edadDejPan.setText(String.valueOf(diag.getEdadDejarPanales()));

        // Datos de Salud (from diagnostico and estudiante)
        txt_prmPartocual.setText(diag.getCualParto());
        text_motor_cual.setText(diag.getCualMotor());
        text_lenguaje_cual.setText(diag.getCualLenguaje());
        text_cognitivo_cual.setText(diag.getCualCognitivo());
        text_alergia_cual.setText(diag.getCualAlergia());
        text_alergia_cual1.setText(diag.getCualAMedicamento()); // Assuming cualAlergia1 maps to cualAMedicamento
        text_condicion_cual.setText(diag.getCualExtra());
        txt_enfPadecidas.setText(diag.getEnfermedadesPadecidas());
        txt_grpoSang.setText(est.getGrupoSanguineo());
        txt_fiebreAlta.setText(diag.getMedicamentoFiebre());
        txt_otraVac.setText(diag.getCualVacAplicada());
        txt_medicoTratante.setText(est.getMedicoTratante());
        txt_tlfMedicoTrat.setText(est.getTlfMedicoTratante());
        txt_edadCaminar.setText(String.valueOf(diag.getEdadCaminar()));


        // Madre
        if (madre != null) {
            text_apellidos_madre.setText(madre.getApellidos());
            text_nombres_madre.setText(madre.getNombres());
            text_ci_madre.setText(String.valueOf(madre.getCi()));
            diaNacMadField.setText(String.valueOf(madre.getFechaNac().getDayOfMonth()));
            mesNacMadField.setText(String.valueOf(madre.getFechaNac().getMonthValue()));
            añoNacMadField.setText(String.valueOf(madre.getFechaNac().getYear()));
            text_edad_madre.setText(String.valueOf(madre.getEdad()));
            text_lugar_nac_madre.setText(madre.getLugarNac());
            text_direc_hab_madre.setText(madre.getDireccionHab());
            text_ocupacion_madre.setText(madre.getOcupacion());
            text_direc_trabj_madre.setText(madre.getDireccionTrabj());
            text_correo_madre.setText(madre.getCorreo());
            text_tlf_madre.setText(madre.getTlf1()); // Assuming tlf1 is the primary phone
            fileBytesMad = madre.getImg(); // Assuming you have a way to set the image to a UI component
                ImageIcon iconInit = new ImageIcon(fileBytesMad);
                ImageIcon iconFin;
                Image img = iconInit.getImage();
                Image imgScaled = img.getScaledInstance(196, 196, Image.SCALE_SMOOTH);
                iconFin = new ImageIcon(imgScaled);
                img_infMad.setIcon(iconFin);

            // Check if mother is the legal representative
            if (repLegal != null && madre.getCi() == repLegal.getCi()) {
                repLegalMadreCheckbox.setSelected(true);
            } else {
                repLegalMadreCheckbox.setSelected(false);
            }
        }

        // Padre
        if (padre != null) {
            text_apellidos_padre.setText(padre.getApellidos());
            text_nombres_padre.setText(padre.getNombres());
            text_ci_padre.setText(String.valueOf(padre.getCi()));
            diaNacPadreField.setText(String.valueOf(padre.getFechaNac().getDayOfMonth()));
            mesNacPadreField.setText(String.valueOf(padre.getFechaNac().getMonthValue()));
            añoNacPadreField.setText(String.valueOf(padre.getFechaNac().getYear()));
            text_edad_padre.setText(String.valueOf(padre.getEdad()));
            text_lugar_nac_padre.setText(padre.getLugarNac());
            text_direc_hab_padre.setText(padre.getDireccionHab());
            text_ocupacion_padre.setText(padre.getOcupacion());
            text_direc_trabj_padre.setText(padre.getDireccionTrabj());
            text_correo_padre.setText(padre.getCorreo());
            text_tlf_padre.setText(padre.getTlf1()); // Assuming tlf1 is the primary phone
            fileBytesPadre = padre.getImg(); // Assuming you have a way to set the image to a UI component
                img_infPadre.setSize(196, 196);
                ImageIcon iconInit = new ImageIcon(fileBytesPadre);
                ImageIcon iconFin;
                Image img = iconInit.getImage();
                Image imgScaled = img.getScaledInstance(196, 196, Image.SCALE_SMOOTH);
                iconFin = new ImageIcon(imgScaled);
                img_infPadre.setIcon(iconFin);
            

            // Check if father is the legal representative
            if (repLegal != null && padre.getCi() == repLegal.getCi()) {
                repLegalPadreCheckbox.setSelected(true);
            } else {
                repLegalPadreCheckbox.setSelected(false);
            }
        }

        // Representante Legal (This block assumes repLegal has been correctly identified in the loop above)
        if (repLegal != null) {
            text_apellidos_rep.setText(repLegal.getApellidos());
            text_nombres_rep.setText(repLegal.getNombres());
            text_ci_rep.setText(String.valueOf(repLegal.getCi()));
            diaNacRepField.setText(String.valueOf(repLegal.getFechaNac().getDayOfMonth()));
            mesNacRepField.setText(String.valueOf(repLegal.getFechaNac().getMonthValue()));
            añoNacRepField.setText(String.valueOf(repLegal.getFechaNac().getYear()));
            text_edad_rep.setText(String.valueOf(repLegal.getEdad()));
            text_lugar_nac_rep.setText(repLegal.getLugarNac());
            text_direc_hab_rep.setText(repLegal.getDireccionHab());
            text_ocupacion_rep.setText(repLegal.getOcupacion());
            text_direc_trabj_rep.setText(repLegal.getDireccionTrabj());
            text_correo_rep.setText(repLegal.getCorreo());
            text_tlf_rep.setText(repLegal.getTlf1()); // Assuming tlf1 is the primary phone
            fileBytesRep = repLegal.getImg(); // Assuming you have a way to set the image to a UI component
            img_infRep.setSize(196, 196);
                ImageIcon iconInit = new ImageIcon(fileBytesRep);
                ImageIcon iconFin;
                Image img = iconInit.getImage();
                Image imgScaled = img.getScaledInstance(196, 196, Image.SCALE_SMOOTH);
                iconFin = new ImageIcon(imgScaled);
                img_infRep.setIcon(iconFin);

            // Find the parentesco for the legal representative
            for (representaA repA : reps) {
                if (repA.getRepresentante_ciRepresentante() == repLegal.getCi()) {
                    fieldRepresentante.setText(repA.getParentesco());
                    break;
                }
            }
        }
        
        // Populate JTable tbl_autRetiro
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Cédula");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Teléfono 1");
        model.addColumn("Teléfono 2");
        model.addColumn("Parentesco");
        
        for (autorizado aut : autorizados) {
            // Find the corresponding parentesco from the 'auts' list
            String parentesco = "";
            for (retiraA rA : auts) {
                if (String.valueOf(rA.getAutorizadoRetiro_ciAutorizado()).equals(String.valueOf(aut.getCi()))) {
                    parentesco = rA.getParentesco();
                    break;
                }
            }
            model.addRow(new Object[]{
                aut.getCi(),
                aut.getNombres(),
                aut.getApellidos(),
                aut.getTlf1(),
                aut.getTlf2(),
                parentesco
            });
        }
        tbl_autRetiro.setModel(model);
        
        // Populate JTable tbl_familiarExtra
        DefaultTableModel familiarModel = new DefaultTableModel();
        familiarModel.addColumn("Nombre");
        familiarModel.addColumn("Apellido");
        familiarModel.addColumn("Edad");
        familiarModel.addColumn("Parentesco");
        familiarModel.addColumn("Ocupación");
        
        for (familia fam : fams) {
            familiarModel.addRow(new Object[]{
                fam.getNombres(),
                fam.getApellidos(),
                fam.getEdad(), // Assuming edad in familia is already a String or can be directly converted
                fam.getParentesco(),
                fam.getOcupacion()
            });
        }
        tbl_familiarExtra.setModel(familiarModel);
        
    }//GEN-LAST:event_btn_verBusqEstdActionPerformed

    private void btn_inscribirEstdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inscribirEstdActionPerformed
        panel.removeAll();
        panel.add(informacionEstudiante);
        panel.repaint();        
        panel.validate();
    }//GEN-LAST:event_btn_inscribirEstdActionPerformed

    private void btn_verBusqAutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_verBusqAutActionPerformed

    }//GEN-LAST:event_btn_verBusqAutActionPerformed

    private void cmb_filtroBusqAutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_filtroBusqAutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_filtroBusqAutActionPerformed

    private void btn_volverBusqNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverBusqNomActionPerformed
       panel.removeAll();
       panel.add(principal);
       panel.repaint();
    }//GEN-LAST:event_btn_volverBusqNomActionPerformed

    private void btn_cerrarBusqNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarBusqNomActionPerformed
       dispose();
    }//GEN-LAST:event_btn_cerrarBusqNomActionPerformed

    private void btn_cerrarBusqEstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarBusqEstActionPerformed
       dispose();
    }//GEN-LAST:event_btn_cerrarBusqEstActionPerformed

    private void btn_volverBusqEstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverBusqEstActionPerformed
       panel.removeAll();
       panel.add(principal);
       panel.repaint();
    }//GEN-LAST:event_btn_volverBusqEstActionPerformed

    private void btn_cerrarBusqAutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarBusqAutActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btn_cerrarBusqAutActionPerformed

    private void btn_volverBusqAutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverBusqAutActionPerformed
       panel.removeAll();
       panel.add(principal);
       panel.repaint();
    }//GEN-LAST:event_btn_volverBusqAutActionPerformed

    private void btn_volverBusqRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverBusqRepActionPerformed
       panel.removeAll();
       panel.add(principal);
       panel.repaint();
    }//GEN-LAST:event_btn_volverBusqRepActionPerformed

    private void btn_cerrarBusqRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarBusqRepActionPerformed
        dispose();
    }//GEN-LAST:event_btn_cerrarBusqRepActionPerformed

    private void btn_aceptarInscripcionNiñoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptarInscripcionNiñoActionPerformed


    String ceNiñ = txt_ce.getText().trim();
    String nombreNiñ = txt_nombreNiñ.getText().trim();
    String apellidoNiñ = txt_apellidoNiñ.getText().trim();
    String edadNiñ = text_edad_niñ.getText().trim();
    String edadNumNiñ = text_edad_niñ.getText().trim();
    String diaNacNiñ = diaNacNiñField.getText().trim();
    String mesNacNiñ = mesNacNiñField.getText().trim();
    String añoNacNiñ = añoNacNiñField.getText().trim();
    String lugarNacNiñ = txt_lugarNacNiñ.getText().trim();
    String estadoNiñ = txt_estadoNiñ.getText().trim();
    String municipioNiñ = txt_municNiñ.getText().trim();
    String nacionalidadNiñ = txt_nacionalidadNiñ.getText().trim();
    String tallaCamisaNiñ = text_tall_camisa_niñ.getText().trim();
    String tallaPantalonNiñ = text_tall_pantalon_niñ.getText().trim();
    String tallaZapatoNiñ = text_tall_zapato_niñ.getText().trim();
    String pesoNiñ = text_peso_niñ.getText().trim();
    String estaturaNiñ = text_estatura_niñ.getText().trim();
    String edadCaminar = txt_edadCaminar.getText().trim();
    byte[] imgArrNiñ = fileBytes;
    
        // Otros
    
    
    String qnDuermeNino = txt_qnDuermeNino.getText().trim();
    String edadDejPan = txt_edadDejPan.getText().trim();

    // Datos de Salud
    String prmPartoCual = txt_prmPartocual.getText().trim();
    String motorCual = text_motor_cual.getText().trim();
    String lenguajeCual = text_lenguaje_cual.getText().trim();
    String cognitivoCual = text_cognitivo_cual.getText().trim();
    String alergiaCual = text_alergia_cual.getText().trim();
    String alergiaMedCual = text_alergia_cual1.getText().trim();
    String condicionCual = text_condicion_cual.getText().trim();
    String enfPadecidas = txt_enfPadecidas.getText().trim();
    String grupoSang = txt_grpoSang.getText().trim();
    String fiebreAlta = txt_fiebreAlta.getText().trim();
    String otraVac = txt_otraVac.getText().trim();
    String medicoTratante = txt_medicoTratante.getText().trim();
    String tlfMedicoTrat = txt_tlfMedicoTrat.getText().trim();

    // Madre
    String apellidosMadre = text_apellidos_madre.getText().trim();
    String nombresMadre = text_nombres_madre.getText().trim();
    String ciMadre = text_ci_madre.getText().trim();
    String diaNacMad = diaNacMadField.getText().trim();
    String mesNacMad = mesNacMadField.getText().trim();
    String añoNacMad = añoNacMadField.getText().trim();
    String edadMadre = text_edad_madre.getText().trim();
    String lugarNacMadre = text_lugar_nac_madre.getText().trim();
    String direcHabMadre = text_direc_hab_madre.getText().trim();
    String ocupacionMadre = text_ocupacion_madre.getText().trim();
    String direcTrabjMadre = text_direc_trabj_madre.getText().trim();
    String correoMadre = text_correo_madre.getText().trim();
    String tlfMadre = text_tlf_madre.getText().trim();
    byte[] imgArrMad = fileBytesMad;
    
    boolean repLegalMadre = repLegalMadreCheckbox.isSelected();

    // Padre
    String apellidosPadre = text_apellidos_padre.getText().trim();
    String nombresPadre = text_nombres_padre.getText().trim();
    String ciPadre = text_ci_padre.getText().trim();
    String diaNacPadre = diaNacPadreField.getText().trim();
    String mesNacPadre = mesNacPadreField.getText().trim();
    String añoNacPadre = añoNacPadreField.getText().trim();
    String edadPadre = text_edad_padre.getText().trim();
    String lugarNacPadre = text_lugar_nac_padre.getText().trim();
    String direcHabPadre = text_direc_hab_padre.getText().trim();
    String ocupacionPadre = text_ocupacion_padre.getText().trim();
    String direcTrabjPadre = text_direc_trabj_padre.getText().trim();
    String correoPadre = text_correo_padre.getText().trim();
    String tlfPadre = text_tlf_padre.getText().trim();
    byte[] imgArrPadre = fileBytesPadre;
    
    boolean repLegalPadre = repLegalPadreCheckbox.isSelected();

    // Representante Legal
    String apellidosRep = text_apellidos_rep.getText().trim();
    String nombresRep = text_nombres_rep.getText().trim();
    String ciRep = text_ci_rep.getText().trim();
    String diaNacRep = diaNacRepField.getText().trim();
    String mesNacRep = mesNacRepField.getText().trim();
    String añoNacRep = añoNacRepField.getText().trim();
    String edadRep = text_edad_rep.getText().trim();
    String lugarNacRep = text_lugar_nac_rep.getText().trim();
    String direcHabRep = text_direc_hab_rep.getText().trim();
    String ocupacionRep = text_ocupacion_rep.getText().trim();
    String direcTrabjRep = text_direc_trabj_rep.getText().trim();
    String correoRep = text_correo_rep.getText().trim();
    String tlfRep = text_tlf_rep.getText().trim();
    byte[] imgArrRep = fileBytesRep;
    
    String parentescoRep = fieldRepresentante.getText().trim();

    // Documentos
    String responsable = text_responsable.getText().trim();
    String persona = text_persona.getText().trim();
    LocalDate fecha = LocalDate.now();
    String observaciones = text_observaciones.getText().trim();

    // Ambiente Socio Familiar
    String cuidaNiñoHogar = txt_cuidaNiñoHogar.getText().trim();
    String otroConsult = txt_otroConsult.getText().trim();

    //Familiares
    java.util.List<familia> listaFamiliares = new java.util.ArrayList<>();
    javax.swing.table.TableModel modelFam = tbl_familiarExtra.getModel();
    for (int i = modelFam.getRowCount(); i > 0 ; i = i) {
        i--;
        Object nombreObj = modelFam.getValueAt(i, 0);
        Object apellidoObj = modelFam.getValueAt(i,1);
        Object edadObj = modelFam.getValueAt(i, 2);
        Object parentescoObj = modelFam.getValueAt(i, 3);
        Object ocupacionObj = modelFam.getValueAt(i, 4);

        String nombre = nombreObj != null ? nombreObj.toString().trim() : "";
        String apellido = apellidoObj != null ? apellidoObj.toString().trim() : "";
        String edad = edadObj != null ? edadObj.toString().trim() : "";
        String parentesco = parentescoObj != null ? parentescoObj.toString().trim() : "";
        String ocupacion = ocupacionObj != null ? ocupacionObj.toString().trim() : "";

        // Only add if at least one field is not empty
        if (!nombre.isEmpty() || !edad.isEmpty() || !parentesco.isEmpty() || !ocupacion.isEmpty()) {
            familia familiar = new familia();
            familiar.setNombres(nombre);
            familiar.setApellidos(apellido);
            familiar.setEdad(edad);
            familiar.setParentesco(parentesco);
            familiar.setOcupacion(ocupacion);
            listaFamiliares.add(familiar);
        }
    }


    //Autorizados
    java.util.List<autorizado> listaAutorizados = new java.util.ArrayList<>();
    javax.swing.table.TableModel modelAut = tbl_autRetiro.getModel();
    for (int i = 0; i < modelAut.getRowCount(); i++) {
        Object ciObj = modelAut.getValueAt(i, 0);
        Object nombreObj = modelAut.getValueAt(i, 1);
        Object apellidoObj = modelAut.getValueAt(i, 2);
        Object tlf1Obj = modelAut.getValueAt(i, 3);
        Object tlf2Obj = modelAut.getValueAt(i, 4);

        int ci = 0;
        if (ciObj != null && !ciObj.toString().trim().isEmpty()) {
            try {
                ci = Integer.parseInt(ciObj.toString().trim());
            } catch (NumberFormatException e) {
                ci = 0;
            }
        }
        String nombre = nombreObj != null ? nombreObj.toString().trim() : "";
        String apellido = apellidoObj != null ? apellidoObj.toString().trim() : "";
        String tlf1 = tlf1Obj != null ? tlf1Obj.toString().trim() : "";
        String tlf2 = tlf2Obj != null ? tlf2Obj.toString().trim() : "";

        if (ci != 0 || !nombre.isEmpty() || !apellido.isEmpty() || !tlf1.isEmpty() || !tlf2.isEmpty()) {
            autorizado aut = new autorizado();
            aut.setCi(ci);
            aut.setNombres(nombre);
            aut.setApellidos(apellido);
            aut.setTlf1(tlf1);
            aut.setTlf2(tlf2);
            listaAutorizados.add(aut);
        }
    }
    
            // Use LinkedHashMap to maintain insertion order for a consistent error message
        LinkedHashMap<String, String> fields = new LinkedHashMap<>();


        // Child's Data
        fields.put("Cédula Escolar", ceNiñ);
        fields.put("Nombre del Niño", nombreNiñ);
        fields.put("Apellido del Niño", apellidoNiñ);
        fields.put("Edad del Niño (texto)", edadNiñ);
        fields.put("Edad del Niño (número)", edadNumNiñ);
        fields.put("Lugar de Nacimiento del Niño", lugarNacNiñ);
        fields.put("Estado del Niño", estadoNiñ);
        fields.put("Municipio del Niño", municipioNiñ);
        fields.put("Nacionalidad del Niño", nacionalidadNiñ);
        fields.put("Talla de Camisa del Niño", tallaCamisaNiñ);
        fields.put("Talla de Pantalón del Niño", tallaPantalonNiñ);
        fields.put("Talla de Zapato del Niño", tallaZapatoNiñ);
        fields.put("Peso del Niño", pesoNiñ);
        fields.put("Estatura del Niño", estaturaNiñ);
        fields.put("Edad al Caminar", edadCaminar);
        fields.put("Sexo", getSelectedButtonText(sexGroup));
        fields.put("Procedencia", getSelectedButtonText(procedGroup));
        fields.put("Lateralidad", getSelectedButtonText(lateralidadGroup));
        fields.put("Duerme Tarde", getSelectedButtonText(duermeTard));
        fields.put("Chupa Dedo", getSelectedButtonText(chupaDedo));
        fields.put("Se Orina de Día", getSelectedButtonText(seOrinaDia));
        fields.put("Evacua de Día", getSelectedButtonText(evacuaDia));
        fields.put("Asea Solo", getSelectedButtonText(aseaSolo));
        fields.put("Se Orina de Noche", getSelectedButtonText(seOrinaNoche));


        // Document Data
        fields.put("Responsable", responsable);
        fields.put("Persona", persona);
        fields.put("Fecha", fecha.toString());
        fields.put("Observaciones", observaciones);

        // Socio-Family Environment
        fields.put("Quién Cuida al Niño en el Hogar", cuidaNiñoHogar);
        fields.put("Otro Consultor", otroConsult);        
        fields.put("Estado Civil Familiar", getSelectedButtonText(socFamEstdCiv));
    
    //Creamos los objetos reflejo de la DB
    
    estudiante estudiante = new estudiante();
    representante representanteLegal = new representante();
    representante madre = new representante();
    representante padre = new representante();
    diagnostico diagnostico = new diagnostico(); 
    documentos documentos = new documentos();
    
    
    //Ponemos las variables
        estudiante.setCe(ceNiñ);
        estudiante.setNombres(nombreNiñ);
        estudiante.setApellidos(apellidoNiñ);
        estudiante.setLugarNac(lugarNacNiñ);
        estudiante.setNacionalidad(nacionalidadNiñ);
        estudiante.setGrupoSanguineo(grupoSang);
        estudiante.setMedicoTratante(medicoTratante);
        estudiante.setTlfMedicoTratante(tlfMedicoTrat);
        estudiante.setProcedencia(lugarNacNiñ);
        estudiante.setImg(fileBytes);
    
            try {
            estudiante.setTallaCamisa(Integer.parseInt(tallaCamisaNiñ));
        } catch (NumberFormatException e) {
                String message = new String("Verifique que ingresó valores correctos el campo Talla Camisa (Niño)");;
                JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        try {
            estudiante.setTallaPantalon(Integer.parseInt(tallaPantalonNiñ));
        } catch (NumberFormatException e) {
            String message = new String("Verifique que ingresó valores correctos el campo Talla Pantalón (Niño)");;
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            estudiante.setTallaZapato(Integer.parseInt(tallaZapatoNiñ));
        } catch (NumberFormatException e) {
            String message = new String("Verifique que ingresó valores correctos el Talla Zapato (Niño)");;
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            // Prefer edadNumNiñ if it's meant to be numeric, otherwise fall back to edadNiñ
            estudiante.setEdad(Integer.parseInt(edadNumNiñ.isEmpty() ? edadNiñ : edadNumNiñ));
        } catch (NumberFormatException e) {
            String message = new String("Verifique que ingresó valores correctos el campo Edad (Niño)");;
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            estudiante.setEstatura(Integer.parseInt(estaturaNiñ));
        }catch (NumberFormatException e) {
            String message = new String("Verifique que ingresó valores correctos el campo Estatura (Niño)");;
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            estudiante.setPeso(Integer.parseInt(pesoNiñ));
        } catch (NumberFormatException e) {
            String message = new String("Verifique que ingresó valores correctos el campo Peso (Niño)");;
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            estudiante.setFechaNac(LocalDate.of(Integer.parseInt(añoNacNiñ),
                    Integer.parseInt(mesNacNiñ),Integer.parseInt(diaNacNiñ)));
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing fecNacRep: " + e.getMessage());
            estudiante.setFechaNac(null);
        } catch (NumberFormatException e) {
            String message = new String("Verifique que ingresó valores correctos el campo Fecha de Nacimiento (Niño)");;
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.WARNING_MESSAGE);
                }

        // --- Datos de Salud (diagnostico) ---
        try {
            diagnostico.setEdadCaminar(Integer.parseInt(edadCaminar));
        } catch (NumberFormatException e) {
            String message = new String("Verifique que ingresó valores correctos el campo Edad de Caminar (Niño)");;
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            diagnostico.setEdadDejarPanales(Integer.parseInt(edadDejPan));
        } catch (NumberFormatException e) {
            String message = new String("Verifique que ingresó valores correctos el campo Edad de Dejar el Pañal (Niño)");;
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        diagnostico.setCualParto(prmPartoCual);
        diagnostico.setProblemaParto(!prmPartoCual.isEmpty()); // Set boolean based on content

        diagnostico.setCualMotor(motorCual);
        diagnostico.setProblemaMotor(!motorCual.isEmpty());

        diagnostico.setCualLenguaje(lenguajeCual);
        diagnostico.setProblemaLenguaje(!lenguajeCual.isEmpty());

        diagnostico.setCualCoginitivo(cognitivoCual);
        diagnostico.setProblemaCognitivo(!cognitivoCual.isEmpty());

        diagnostico.setCualAlergia(alergiaCual);
        diagnostico.setAlergia(!alergiaCual.isEmpty());

        diagnostico.setCualAMedicamento(alergiaMedCual);
        diagnostico.setAlergiaMedicamento(!alergiaMedCual.isEmpty());

        diagnostico.setEnfermedadesPadecidas(enfPadecidas);
        diagnostico.setMedicamentoFiebre(fiebreAlta); // Assuming this is the medication for high fever
        diagnostico.setCualVacAplicada(otraVac);
        diagnostico.setConQuienDuerme(qnDuermeNino);

        try {
            diagnostico.setHoraDormir(LocalTime.of(cBoxHoraAc.getSelectedIndex(),cBoxMinAc.getSelectedIndex()));
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing horaAcostar: " + e.getMessage());
            diagnostico.setHoraDormir(null); // Or a default time
        }
        try {
            diagnostico.setHoraLevantarse(LocalTime.of(cBoxHoraLev.getSelectedIndex(),cBoxMinLev.getSelectedIndex()));
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing horaLevantar: " + e.getMessage());
            diagnostico.setHoraLevantarse(null); // Or a default time
        }
        // condicionCual is available but no direct boolean field for it.
        // If 'condicionExtra' is meant to be derived from 'condicionCual', uncomment below:
        // diagnostico.setCualExtra(condicionCual);
        // diagnostico.setCondicionExtra(!condicionCual.isEmpty());


        // --- Datos de la Madre (representante) ---
        madre.setNombres(nombresMadre);
        madre.setApellidos(apellidosMadre);
        try {
            madre.setCi(Integer.parseInt(ciMadre));
        } catch (NumberFormatException e) {
            System.err.println("Error parsing ciMadre: " + e.getMessage());
            madre.setCi(0);
        }
        try {
            madre.setEdad(Integer.parseInt(edadMadre));
        } catch (NumberFormatException e) {
            System.err.println("Error parsing edadMadre: " + e.getMessage());
            madre.setEdad(0);
        }
        madre.setLugarNac(lugarNacMadre);
        madre.setDireccionHab(direcHabMadre);
        madre.setOcupacion(ocupacionMadre);
        madre.setDireccionTrabj(direcTrabjMadre);
        madre.setCorreo(correoMadre);
        madre.setTlf1(tlfMadre); // Assuming tlfMadre maps to tlf1
        madre.setImg(fileBytesMad);

        // --- Datos del Padre (representante) ---
        padre.setNombres(nombresPadre);
        padre.setApellidos(apellidosPadre);
        try {
            padre.setCi(Integer.parseInt(ciPadre));
        } catch (NumberFormatException e) {
            System.err.println("Error parsing ciPadre: " + e.getMessage());
            padre.setCi(0);
        }
        try {
            padre.setEdad(Integer.parseInt(edadPadre));
        } catch (NumberFormatException e) {
            System.err.println("Error parsing edadPadre: " + e.getMessage());
            padre.setEdad(0);
        }
        padre.setLugarNac(lugarNacPadre);
        padre.setDireccionHab(direcHabPadre);
        padre.setOcupacion(ocupacionPadre);
        padre.setDireccionTrabj(direcTrabjPadre);
        padre.setCorreo(correoPadre);
        padre.setTlf1(tlfPadre); // Assuming tlfPadre maps to tlf1
        madre.setImg(fileBytesPadre);

        // --- Datos del Representante Legal (representante) ---
        representanteLegal.setNombres(nombresRep);
        representanteLegal.setApellidos(apellidosRep);
        try {
            representanteLegal.setCi(Integer.parseInt(ciRep));
        } catch (NumberFormatException e) {
            System.err.println("Error parsing ciRep: " + e.getMessage());
            representanteLegal.setCi(0);
        }
        try {
            representanteLegal.setEdad(Integer.parseInt(edadRep));
        } catch (NumberFormatException e) {
            System.err.println("Error parsing edadRep: " + e.getMessage());
            representanteLegal.setEdad(0);
        }
        representanteLegal.setLugarNac(lugarNacRep);
        representanteLegal.setDireccionHab(direcHabRep);
        representanteLegal.setOcupacion(ocupacionRep);
        representanteLegal.setDireccionTrabj(direcTrabjRep);
        representanteLegal.setCorreo(correoRep);
        representanteLegal.setTlf1(tlfRep); // Assuming tlfRep maps to tlf1
        madre.setImg(fileBytesRep);
        
        //Evaluación representantes
        
        if (padre.getCi() == 0 && madre.getCi() == 0 && representanteLegal.getCi() == 0){
        return;
        }
        
        if (repLegalPadre) {
            apellidosRep = apellidosPadre;
            nombresRep = nombresPadre;
            ciRep = ciPadre;
            diaNacRep = diaNacPadre;
            mesNacRep = mesNacPadre;
            añoNacRep = añoNacPadre;
            edadRep = edadPadre;
            lugarNacRep = lugarNacPadre;
            direcHabRep = direcHabPadre;
            ocupacionRep = ocupacionPadre;
            direcTrabjRep = direcTrabjPadre;
            correoRep = correoPadre;
            tlfRep = tlfPadre;
            fileBytesRep = fileBytesPadre;
    
            parentescoRep = fieldParentescoPadre.getText().trim();
        }
        
        if (repLegalMadre){
            apellidosRep = apellidosMadre;
            nombresRep = nombresMadre;
            ciRep = ciMadre;
            diaNacRep = diaNacMad;
            mesNacRep = mesNacMad;
            añoNacRep = añoNacMad;
            edadRep = edadMadre;
            lugarNacRep = lugarNacMadre;
            direcHabRep = direcHabMadre;
            ocupacionRep = ocupacionMadre;
            direcTrabjRep = direcTrabjMadre;
            correoRep = correoMadre;
            tlfRep = tlfMadre;
            fileBytesRep = fileBytesMad;
    
            parentescoRep = fieldParentescoMadre.getText().trim();
        }
        
        if (madre.getCi() != 0) {
                // Mother's Data
        fields.put("Apellidos de la Madre", apellidosMadre);
        fields.put("Nombres de la Madre", nombresMadre);
        fields.put("Cédula de Identidad de la Madre", ciMadre);
        fields.put("Día de Nacimiento de la Madre", diaNacMad);
        fields.put("Mes de Nacimiento de la Madre", mesNacMad);
        fields.put("Año de Nacimiento de la Madre", añoNacMad);
        fields.put("Edad de la Madre", edadMadre);
        fields.put("Lugar de Nacimiento de la Madre", lugarNacMadre);
        fields.put("Dirección de Habitación de la Madre", direcHabMadre);
        fields.put("Ocupación de la Madre", ocupacionMadre);
        fields.put("Dirección de Trabajo de la Madre", direcTrabjMadre);
        fields.put("Correo Electrónico de la Madre", correoMadre);
        fields.put("Teléfono de la Madre", tlfMadre);
        try {
            madre.setFechaNac(LocalDate.of(Integer.parseInt(añoNacMad),
                    Integer.parseInt(mesNacMad),Integer.parseInt(diaNacMad)));
        } catch (DateTimeParseException e) {
            StringBuilder message = new StringBuilder("Verifique que ingresó valores correctos en el campo Fecha de Nacimiento (Madre)");;
            JOptionPane.showMessageDialog(this, message.toString(), "Error", JOptionPane.WARNING_MESSAGE);
            System.err.println("Error parsing fecNacRep: " + e.getMessage());
            madre.setFechaNac(null);
            return;
        }
        }
        
        if (padre.getCi() != 0) {
        // Father's Data
        fields.put("Apellidos del Padre", apellidosPadre);
        fields.put("Nombres del Padre", nombresPadre);
        fields.put("Cédula de Identidad del Padre", ciPadre);
        fields.put("Día de Nacimiento de la Padre", diaNacPadre);
        fields.put("Mes de Nacimiento de la Padre", mesNacPadre);
        fields.put("Año de Nacimiento de la Padre", añoNacPadre);
        fields.put("Edad del Padre", edadPadre);
        fields.put("Lugar de Nacimiento del Padre", lugarNacPadre);
        fields.put("Dirección de Habitación del Padre", direcHabPadre);
        fields.put("Ocupación del Padre", ocupacionPadre);
        fields.put("Dirección de Trabajo del Padre", direcTrabjPadre);
        fields.put("Correo Electrónico del Padre", correoPadre);
        fields.put("Teléfono del Padre", tlfPadre);
        try {
            padre.setFechaNac(LocalDate.of(Integer.parseInt(añoNacPadre),
                    Integer.parseInt(mesNacPadre),Integer.parseInt(diaNacPadre)));
        } catch (DateTimeParseException e) {
            StringBuilder message = new StringBuilder("Verifique que ingresó valores correctos en el campo Fecha de Nacimiento (padre)");;
            JOptionPane.showMessageDialog(this, message.toString(), "Error", JOptionPane.WARNING_MESSAGE);
            System.err.println("Error parsing fecNacRep: " + e.getMessage());
            padre.setFechaNac(null);
            return;
        }
        }
        // Legal Representative's Data
        fields.put("Apellidos del Representante Legal", apellidosRep);
        fields.put("Nombres del Representante Legal", nombresRep);
        fields.put("Cédula de Identidad del Representante Legal", ciRep);
        fields.put("Día de Nacimiento del (la) Representante", diaNacRep);
        fields.put("Mes de Nacimiento del (la) Representante", mesNacRep);
        fields.put("Año de Nacimiento del (la) Representante", añoNacRep);
        fields.put("Edad del Representante Legal", edadRep);
        fields.put("Lugar de Nacimiento del Representante Legal", lugarNacRep);
        fields.put("Dirección de Habitación del Representante Legal", direcHabRep);
        fields.put("Ocupación del Representante Legal", ocupacionRep);
        fields.put("Dirección de Trabajo del Representante Legal", direcTrabjRep);
        fields.put("Correo Electrónico del Representante Legal", correoRep);
        fields.put("Teléfono del Representante Legal", tlfRep);
        fields.put("Parentesco del Representante Legal", parentescoRep);
        
        try {
            representanteLegal.setFechaNac(LocalDate.of(Integer.parseInt(añoNacRep),
                    Integer.parseInt(mesNacRep),Integer.parseInt(diaNacRep)));
        } catch (DateTimeParseException e) {
            StringBuilder message = new StringBuilder("Verifique que ingresó valores correctos en el campo Fecha de Nacimiento (Representante)");;
            JOptionPane.showMessageDialog(this, message.toString(), "Error", JOptionPane.WARNING_MESSAGE);
            System.err.println("Error parsing fecNacRep: " + e.getMessage());
            representanteLegal.setFechaNac(null);
        }
        

        // --- Documentos ---
        documentos.setResponsableInscripcion(responsable);
        documentos.setPersonaInscribe(persona);
        documentos.setFechaInscripcion(fecha);
        documentos.setObservaciones(observaciones);
        documentos.setCe(estudiante.getCe());
        documentos.setCedulaMadre(check_ci_madre.isSelected());
        documentos.setCedulaPadre(check_ci_padre.isSelected());
        documentos.setCedulaRep(check_ci_representante.isSelected());        

        documentos.setOriginales(check_originales.isSelected());
        documentos.setFotocopias(check_copias.isSelected());
        documentos.setPartidaNac(check_partida.isSelected());
        documentos.setCertificadoVacuna(check_vacunas.isSelected());

        // --- Ambiente Socio Familiar ---
        socioFamiliar socioFamiliar = new socioFamiliar();
        socioFamiliar.setCuidaNinoHogar(cuidaNiñoHogar);
        socioFamiliar.setEstdCivilPadres(getSelectedButtonText(socFamEstdCiv));
        socioFamiliar.setEstudiante_ciEstudiante(estudiante.getCe());
        socioFamiliar.setTipoVivienda(getSelectedButtonText(socFamTipoCasa));
        socioFamiliar.setVivienda(getSelectedButtonText(socFamEstiloCasa));
        socioFamiliar.setVisitNeurologo(chk_neur.isSelected());
        socioFamiliar.setVisitPsicologo(chk_psic.isSelected());
        socioFamiliar.setVisitPsicopedagogo(chk_pspg.isSelected());
        socioFamiliar.setVisitTerapLeng(chk_terpLenguaje.isSelected());
        socioFamiliar.setConsultaAtend(txt_otroConsult.getText().trim());
        // otroConsult is available but no direct field in socioFamiliar.
        // If it's meant to be part of 'relacionAmbienteFamiliar' or similar, you'd combine it.


        // --- Autorizados ---
        // Assuming tbl_autRetiro is the JTable's model
        if (tbl_autRetiro != null) {
            for (int i = 0; i < tbl_autRetiro.getRowCount(); i++) {
                Object ciObj = tbl_autRetiro.getValueAt(i, 0);
                Object nombreObj = tbl_autRetiro.getValueAt(i, 1);
                Object apellidoObj = tbl_autRetiro.getValueAt(i, 2);
                Object tlf1Obj = tbl_autRetiro.getValueAt(i, 3);
                Object tlf2Obj = tbl_autRetiro.getValueAt(i, 4);
                Object parentescoObj = tbl_autRetiro.getValueAt(i,5);

                int ci = 0;
                if (ciObj != null && !ciObj.toString().trim().isEmpty()) {
                    try {
                        ci = Integer.parseInt(ciObj.toString().trim());
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing CI for authorized person at row " + i + ": " + e.getMessage());
                        ci = 0; // Default or error handling
                    }
                }
                String nombre = nombreObj != null ? nombreObj.toString().trim() : "";
                String apellido = apellidoObj != null ? apellidoObj.toString().trim() : "";
                String tlf1 = tlf1Obj != null ? tlf1Obj.toString().trim() : "";
                String tlf2 = tlf2Obj != null ? tlf2Obj.toString().trim() : "";
                String parentesco = parentescoObj != null ? parentescoObj.toString().trim() : "";

                if (ci != 0 || !nombre.isEmpty() || !apellido.isEmpty() || !tlf1.isEmpty() || !tlf2.isEmpty() || !parentesco.isEmpty()) {
                    autorizado aut = new autorizado();
                    aut.setCi(ci);
                    aut.setNombres(nombre);
                    aut.setApellidos(apellido);
                    aut.setTlf1(tlf1);
                    aut.setTlf2(tlf2);
                    aut.setParentesco(parentesco);
                    listaAutorizados.add(aut);
                }
            }
        } else {
            System.err.println("tbl_autRetiro is null. Authorized persons list will be empty.");
        }

        if (validateAndNotify(this, fields)){
            connectDB.sendEstudiante(estudiante);
            connectDB.sendDiagnostico(diagnostico, estudiante);
            connectDB.setSocioFamiliar(socioFamiliar);
            connectDB.sendDocumentos(documentos);

            for (autorizado aut: listaAutorizados){
                connectDB.sendAutorizado(aut);
                connectDB.setRelAutorizado(aut, estudiante);
            }
            
            if (madre.getCi() == 0 && padre.getCi() == 0){
                connectDB.sendRepresentante(representanteLegal);
                connectDB.setRelRepresentado(representanteLegal, estudiante, true, parentescoRep);
            }

            if (madre.getCi() != 0){
                connectDB.sendRepresentante(madre);
                connectDB.setRelRepresentado(madre, estudiante, repLegalMadre, "madre");}
            if (padre.getCi() != 0){
                connectDB.sendRepresentante(padre);
                connectDB.setRelRepresentado(padre, estudiante, repLegalPadre, "padre");}

            for (familia fam : listaFamiliares){
                connectDB.setFamilia(fam, estudiante);
            }
            
        } else {
            
        }
        
        panel.removeAll();
        panel.add(busquedaEstudiantes);
        panel.repaint();
        panel.validate();

//    //Imprimimos en la consola
//
//    System.out.println(nombreNiñ);
//    System.out.println(apellidoNiñ);
//    System.out.println(edadNiñ);
//    System.out.println(edadNumNiñ);
//    System.out.println(lugarNacNiñ);
//    System.out.println(estadoNiñ);
//    System.out.println(municipioNiñ);
//    System.out.println(nacionalidadNiñ);
//    System.out.println(tallaCamisaNiñ);
//    System.out.println(tallaPantalonNiñ);
//    System.out.println(tallaZapatoNiñ);
//    System.out.println(pesoNiñ);
//    System.out.println(estaturaNiñ);
//    System.out.println(edadCaminar);
//
//    System.out.println(prmPartoCual);
//    System.out.println(motorCual);
//    System.out.println(lenguajeCual);
//    System.out.println(cognitivoCual);
//    System.out.println(alergiaCual);
//    System.out.println(alergiaMedCual);
//    System.out.println(condicionCual);
//    System.out.println(enfPadecidas);
//    System.out.println(grupoSang);
//    System.out.println(fiebreAlta);
//    System.out.println(otraVac);
//    System.out.println(medicoTratante);
//    System.out.println(tlfMedicoTrat);
//
//    System.out.println(horaAcostar);
//    System.out.println(horaLevantar);
//    System.out.println(qnDuermeNino);
//    System.out.println(edadDejPan);
//
//    System.out.println(apellidosMadre);
//    System.out.println(nombresMadre);
//    System.out.println(ciMadre);
//    System.out.println(edadMadre);
//    System.out.println(lugarNacMadre);
//    System.out.println(direcHabMadre);
//    System.out.println(ocupacionMadre);
//    System.out.println(direcTrabjMadre);
//    System.out.println(correoMadre);
//    System.out.println(tlfMadre);
//
//    System.out.println(apellidosPadre);
//    System.out.println(nombresPadre);
//    System.out.println(ciPadre);
//    System.out.println(edadPadre);
//    System.out.println(lugarNacPadre);
//    System.out.println(direcHabPadre);
//    System.out.println(ocupacionPadre);
//    System.out.println(direcTrabjPadre);
//    System.out.println(correoPadre);
//    System.out.println(tlfPadre);
//
//    System.out.println(apellidosRep);
//    System.out.println(nombresRep);
//    System.out.println(ciRep);
//    System.out.println(edadRep);
//    System.out.println(lugarNacRep);
//    System.out.println(direcHabRep);
//    System.out.println(ocupacionRep);
//    System.out.println(direcTrabjRep);
//    System.out.println(correoRep);
//    System.out.println(tlfRep);
//
//    System.out.println(responsable);
//    System.out.println(persona);
//    System.out.println(fecha);
//    System.out.println(observaciones);
//
//    System.out.println(cuidaNiñoHogar);
//    System.out.println(otroConsult);

//    for (autorizado aut : listaAutorizados) {
//        System.out.println("Autorizado:");
//        System.out.println("  CI: " + aut.getCi());
//        System.out.println("  Nombres: " + aut.getNombres());
//        System.out.println("  Apellidos: " + aut.getApellidos());
//        System.out.println("  Teléfono 1: " + aut.getTlf1());
//        System.out.println("  Teléfono 2: " + aut.getTlf2());
//    }
    }//GEN-LAST:event_btn_aceptarInscripcionNiñoActionPerformed

    private void text_apellidos_madreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_apellidos_madreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_apellidos_madreActionPerformed

    private void text_nombres_madreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_nombres_madreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_nombres_madreActionPerformed

    private void check_s_madreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_s_madreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_s_madreActionPerformed

    private void check_c_madreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_c_madreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_c_madreActionPerformed

    private void check_d_madreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_d_madreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_d_madreActionPerformed

    private void check_v_madreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_v_madreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_v_madreActionPerformed

    private void text_lugar_nac_madreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_lugar_nac_madreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_lugar_nac_madreActionPerformed

    private void check_ven_madreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_ven_madreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_ven_madreActionPerformed

    private void check_ext_madreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_ext_madreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_ext_madreActionPerformed

    private void text_direc_hab_madreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_direc_hab_madreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_direc_hab_madreActionPerformed

    private void check_no_bachiller_madreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_no_bachiller_madreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_no_bachiller_madreActionPerformed

    private void chec_bachiller_madreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chec_bachiller_madreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chec_bachiller_madreActionPerformed

    private void check_tsu_madreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_tsu_madreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_tsu_madreActionPerformed

    private void check_superior_madreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_superior_madreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_superior_madreActionPerformed

    private void text_correo_madreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_correo_madreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_correo_madreActionPerformed

    private void text_tlf_madreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_tlf_madreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_tlf_madreActionPerformed

    private void text_apellidos_padreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_apellidos_padreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_apellidos_padreActionPerformed

    private void text_nombres_padreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_nombres_padreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_nombres_padreActionPerformed

    private void check_s_padreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_s_padreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_s_padreActionPerformed

    private void check_c_padreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_c_padreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_c_padreActionPerformed

    private void check_d_padreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_d_padreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_d_padreActionPerformed

    private void check_v_padreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_v_padreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_v_padreActionPerformed

    private void text_lugar_nac_padreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_lugar_nac_padreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_lugar_nac_padreActionPerformed

    private void check_ven_padreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_ven_padreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_ven_padreActionPerformed

    private void check_ext_padreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_ext_padreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_ext_padreActionPerformed

    private void text_direc_hab_padreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_direc_hab_padreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_direc_hab_padreActionPerformed

    private void check_no_bachiller_padreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_no_bachiller_padreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_no_bachiller_padreActionPerformed

    private void chec_bachiller_padreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chec_bachiller_padreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chec_bachiller_padreActionPerformed

    private void check_tsu_padreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_tsu_padreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_tsu_padreActionPerformed

    private void check_superior_padreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_superior_padreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_superior_padreActionPerformed

    private void text_correo_padreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_correo_padreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_correo_padreActionPerformed

    private void text_tlf_padreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_tlf_padreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_tlf_padreActionPerformed

    private void text_apellidos_repActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_apellidos_repActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_apellidos_repActionPerformed

    private void text_nombres_repActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_nombres_repActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_nombres_repActionPerformed

    private void check_s_repActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_s_repActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_s_repActionPerformed

    private void check_c_repActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_c_repActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_c_repActionPerformed

    private void check_d_repActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_d_repActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_d_repActionPerformed

    private void check_v_repActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_v_repActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_v_repActionPerformed

    private void text_lugar_nac_repActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_lugar_nac_repActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_lugar_nac_repActionPerformed

    private void check_ven_repActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_ven_repActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_ven_repActionPerformed

    private void check_ext_repActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_ext_repActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_ext_repActionPerformed

    private void text_direc_hab_repActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_direc_hab_repActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_direc_hab_repActionPerformed

    private void check_no_bachiller_repActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_no_bachiller_repActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_no_bachiller_repActionPerformed

    private void chec_bachiller_repActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chec_bachiller_repActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chec_bachiller_repActionPerformed

    private void check_tsu_repActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_tsu_repActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_tsu_repActionPerformed

    private void check_superior_repActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_superior_repActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_superior_repActionPerformed

    private void text_ocupacion_repActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_ocupacion_repActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_ocupacion_repActionPerformed

    private void text_correo_repActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_correo_repActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_correo_repActionPerformed

    private void text_tlf_repActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_tlf_repActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_tlf_repActionPerformed

    private void txt_nombreNiñActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreNiñActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreNiñActionPerformed

    private void txt_apellidoNiñActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_apellidoNiñActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apellidoNiñActionPerformed

    private void txt_lugarNacNiñActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_lugarNacNiñActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_lugarNacNiñActionPerformed

    private void text_edad_niñActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_edad_niñActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_edad_niñActionPerformed

    private void radio_hogar_niñActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_hogar_niñActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_hogar_niñActionPerformed

    private void radio_mismo_plantel_niñActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_mismo_plantel_niñActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_mismo_plantel_niñActionPerformed

    private void radio_otro_plantel_niñActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_otro_plantel_niñActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_otro_plantel_niñActionPerformed

    private void text_tall_camisa_niñActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_tall_camisa_niñActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_tall_camisa_niñActionPerformed

    private void text_peso_niñActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_peso_niñActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_peso_niñActionPerformed

    private void text_responsableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_responsableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_responsableActionPerformed

    private void btn_aceptarInscripcionNominaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptarInscripcionNominaActionPerformed

    Integer ciNomina = Integer.parseInt(txt_ciNomina.getText().trim());
    Integer rifNomina = Integer.parseInt(txt_rifNomina.getText().trim());
    String nombreNomina = txt_nombreNomina.getText().trim();
    String apellidoNomina = txt_apellidoNomina.getText().trim();

    // Nuevo: usar ButtonGroup para sexoNomina (Booleano: true=masculino, false=femenino, null=ninguno)
    Boolean sexoNomina = null;
    if (rdb_mascNomina.isSelected()) {
        sexoNomina = true;
    } else if (rdb_femNomina.isSelected()) {
        sexoNomina = false;
    }

    //LocalDate fechNacimNomina = parse(txt_fechNacimNomina.getText().trim());

    Integer edadNomina = Integer.parseInt(txt_edadNomina.getText().trim());
    String munResNomina = txt_munResNomina.getText().trim();
    String prrqResNomina = txt_prrqResNomina.getText().trim();
    String comResNomina = txt_comResNomina.getText().trim();
    String calleResNomina = txt_calleResNomina.getText().trim();
    String tlf1Nomina = txt_tlf1Nomina.getText().trim();
    String tlf2Nomina = txt_tlf2Nomina.getText().trim();

    String munVotNomina = txt_munVotNomina.getText().trim();
    String parrqVotNomina = txt_parrqVotNomina.getText().trim();
    String centrVotNomina = txt_centrVotNomina.getText().trim();

    // Nuevo: usar ButtonGroup para votoNomina (Booleano: true=sí, false=no, null=ninguno)
    Boolean votoNomina = null;
    if (rdb_siVot.isSelected()) {
        votoNomina = true;
    } else if (rdb_noVot.isSelected()) {
        votoNomina = false;
    }

    String jefComNomina = txt_jefComNomina.getText().trim();

    Integer ciJefComNomina = Integer.parseInt(txt_ciJefComNomina.getText().trim());
    
    Boolean unoX10Nomina = null;
    if (rdb_si1x10.isSelected()) {
        unoX10Nomina = true;
    } else if (rdb_no1x10.isSelected()) {
        unoX10Nomina = false;
    }

    Integer cant1x10Nomina = Integer.parseInt(txt_cant1x10Nomina.getText().trim());

    Boolean turnoNomina = null;
    String nivelNominaMaestra = txt_nivelNominaMaestra.getText().trim();
    String seccionNominaMaestra = txt_seccionNominaMaestra.getText().trim();

    Boolean estatusNomina = null;

    //LocalDate fechaIngresoNomina = parse(txt_fechaIngresoNomina.getText().trim());

    System.out.println(ciNomina);
    System.out.println(rifNomina);
    System.out.println(nombreNomina);
    System.out.println(apellidoNomina);
    System.out.println(sexoNomina);
    /*System.out.println(fechNacimNomina);*/
    System.out.println(edadNomina);
    System.out.println(munResNomina);
    System.out.println(prrqResNomina);
    System.out.println(comResNomina);
    System.out.println(calleResNomina);
    System.out.println(tlf1Nomina);
    System.out.println(tlf2Nomina);

    System.out.println(munVotNomina);
    System.out.println(parrqVotNomina);
    System.out.println(centrVotNomina);
    System.out.println(votoNomina);
    System.out.println(jefComNomina);
    System.out.println(ciJefComNomina);
    System.out.println(unoX10Nomina);
    System.out.println(cant1x10Nomina);

    System.out.println(turnoNomina);
    System.out.println(nivelNominaMaestra);
    System.out.println(seccionNominaMaestra);
    System.out.println(estatusNomina);
    /*System.out.println(fechaIngresoNomina);*/

    trabajador trabajador = new trabajador();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/M-M/yyyy");

    // Asignar los valores al objeto trabajador
    trabajador.setNombres(nombreNomina);
    trabajador.setApellidos(apellidoNomina);
    trabajador.setCi(ciNomina);
    trabajador.setRif(rifNomina);
    trabajador.setSexo(sexoNomina);
    /*trabajador.setFechaNac(fechNacimNomina);*/
    trabajador.setMunRes(munResNomina);
    trabajador.setParrqRes(prrqResNomina);
    trabajador.setComRes(comResNomina);
    trabajador.setCalleRes(calleResNomina);
    trabajador.setTlf1(tlf1Nomina);
    trabajador.setTlf2(tlf2Nomina);

    trabajador.setMunElec(munVotNomina);
    trabajador.setParrqElec(parrqVotNomina);
    trabajador.setCenVot(centrVotNomina);
    trabajador.setHaVotado(votoNomina);
    trabajador.setNombreJefeClap(jefComNomina);
    trabajador.setCiJefeClap(ciJefComNomina);
    trabajador.setTiene1x10(unoX10Nomina);
    trabajador.setCantPers1x10(cant1x10Nomina);

    trabajador.setTurno(turnoNomina);
    trabajador.setEstatus(estatusNomina);
    /*trabajador.setFechaIngreso(fechaIngresoNomina);*/

    // Guardar en la base de datos
    connectDB.sendNomina(trabajador);

    }//GEN-LAST:event_btn_aceptarInscripcionNominaActionPerformed

    private void txt_edadNominaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_edadNominaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_edadNominaActionPerformed

    private void txt_fechNacimNominaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fechNacimNominaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fechNacimNominaActionPerformed

    private void txt_apellidoNominaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_apellidoNominaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apellidoNominaActionPerformed

    private void txt_nombreNominaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreNominaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreNominaActionPerformed

    private void txt_ciNominaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ciNominaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ciNominaActionPerformed

    private void text_direc_trabj_madreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_direc_trabj_madreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_direc_trabj_madreActionPerformed

    private void btn_buscarRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarRepActionPerformed
    DefaultTableModel model = (DefaultTableModel) tbl_busqRep.getModel();
    for (int i = model.getRowCount(); i > 0 ; i = i){
        i--;
        model.removeRow(i); }
    
    tbl_busqRep.getColumn("Foto").setCellRenderer( new customCellRenderer());
       
    String query = txt_filtroBusqRep.getText();
    System.out.println(query);
    String criterio = cmb_filtroBusqRep.getItemAt(cmb_filtroBusqRep.getSelectedIndex()); 

    criterio = switch (criterio) {
            case "Cédula" -> "ciRepresentante";
            case "Nombre" -> "nombres";
            case "Apellido" -> "apellidos";
            case "Estado Civil" -> "estdCiv";
            case "Grado de Estudios" -> "gradoEstudios";
            case "Ocupación" -> "ocupacion";
            case "Ninguno" -> null;
            default -> null; };
    System.out.println(criterio);
    // Handle cases where the input string doesn't match any known options
    List<representante> lista = connectDB.buscarRepresentante(criterio, query);
    for (representante rep : lista) {
            if (rep instanceof representante) { // Check if it's a Person object
                representante repActual = (representante) rep;
                // Create a row vector for each person
                Vector<Object> row = new Vector<>();
                ImageIcon icon = new ImageIcon(repActual.getImg()); //estActual.getImg()
                javax.swing.JLabel label = new javax.swing.JLabel(); label.setIcon(icon);
                row.add(label);
                row.add(repActual.getNombres());
                row.add(repActual.getApellidos());
                if (repActual.isNinosMenor6()) {
                    row.add("Sí"); } else { 
                    row.add("No"); }
                row.add(repActual.getCi());
                model.addRow(row);
                
            }
        }
    }//GEN-LAST:event_btn_buscarRepActionPerformed

    private void btn_buscarEstdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarEstdActionPerformed
        
    DefaultTableModel model = (DefaultTableModel) tbl_busqEstd.getModel();
    for (int i = model.getRowCount(); i > 0; i=i) {
        i--;
        model.removeRow(i); }
    
    tbl_busqEstd.getColumn("Foto").setCellRenderer( new customCellRenderer());
        
    String query = txt_filtroBusqEstd.getText();
    System.out.println(query);
    String criterio = cmb_filtroBusqEstd.getItemAt(cmb_filtroBusqEstd.getSelectedIndex()); 

    criterio = switch (criterio) {
            case "Cédula Estudiantil" -> "ciEstudiante";
            case "Nombre" -> "nombres";
            case "Apellido" -> "apellidos";
            case "Talla Camisa" -> "tallaCam";
            case "Talla Pantalón" -> "tallaPant";
            case "Talla Zapato" -> "tallaZap";
            case "Peso" -> "peso";
            case "Estatura" -> "estatura";
            case "Edad" -> "edad";
            case "Ninguno" -> null;
            default -> null; };
    System.out.println(criterio);
    // Handle cases where the input string doesn't match any known options
    List<estudiante> lista = connectDB.buscarEstudiante(criterio, query);
    for (estudiante est : lista) {
            if (est instanceof estudiante) { // Check if it's a Person object
                estudiante estActual = (estudiante) est;
                // Create a row vector for each person
                Vector<Object> row = new Vector<>();
                ImageIcon icon = new ImageIcon(estActual.getImg()); //estActual.getImg()
                javax.swing.JLabel label = new javax.swing.JLabel(); label.setIcon(icon);
                row.add(label);
                row.add(estActual.getNombres());
                row.add(estActual.getApellidos());
                row.add(estActual.getCe());
                row.add(estActual.getGrupoSanguineo());
                model.addRow(row);
            }
        }
    }//GEN-LAST:event_btn_buscarEstdActionPerformed

    private void btn_buscarAutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarAutActionPerformed
    DefaultTableModel model = (DefaultTableModel) tbl_busqAut.getModel();
    for (int i = model.getRowCount(); i > 0;){
        i--;
        model.removeRow(i); }
       
    String query = txt_filtroBusqAut.getText();
    System.out.println(query);
    String criterio = cmb_filtroBusqAut.getItemAt(cmb_filtroBusqAut.getSelectedIndex());
    criterio = switch(criterio){
        case "C.I Autorizado" -> "ciAutorizado";
        case "C.I Representante" -> "ciRepresentante";
        case "Cédula de Estudiante" -> "ciEstudiante";
        case "Nombres" -> "nombres";
        case "Apellidos" -> "apellidos";
        case "Teléfono 1" -> "Tlf1";
        case "Teléfono 2" -> "Tlf2";
        case "Ninguno" -> null;
        default -> null;
    };
    
    List<autorizado> lista = connectDB.buscarAutorizado(criterio, query);
    for (autorizado aut : lista) {
            if (aut instanceof autorizado) { // Check if it's a Person object
                autorizado autActual = (autorizado) aut;
                // Create a row vector for each person
                Vector<Object> row = new Vector<>();
                row.add(autActual.getCi());
                row.add(autActual.getNombres());
                row.add(autActual.getApellidos());
                row.add(autActual.getTlf1());
                row.add(autActual.getTlf2());
                model.addRow(row);
            }
        }
    }//GEN-LAST:event_btn_buscarAutActionPerformed

    private void btn_buscarNominaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarNominaActionPerformed
    DefaultTableModel model = (DefaultTableModel) tbl_busqNomina.getModel();
    for (int i = model.getRowCount(); i > 0;){
        i--;
        model.removeRow(i); }
    
    tbl_busqNomina.getColumn("Foto").setCellRenderer( new customCellRenderer());
       
    String query = txt_filtroBusqNom.getText();
    System.out.println(query);
    String criterio = cmb_filtroBusqNom.getItemAt(cmb_filtroBusqNom.getSelectedIndex());
    criterio = switch(criterio){
        case "Nombres" -> "nombres";
        case "Apellidos" -> "apellidos";
        case "Cédula" -> "ci_maestra";
        case "Estatus" -> "estatus";
        case "Ninguno" -> null;
        default -> null;
    };
    
    List<trabajador> lista = connectDB.buscarNomina(criterio, query);
    for (trabajador nom : lista) {
            if (nom instanceof trabajador) { // Check if it's a Person object
                trabajador nomina = (trabajador) nom;
                // Create a row vector for each person
                Vector<Object> row = new Vector<>();
                ImageIcon icon = new ImageIcon(nomina.getImg());
                javax.swing.JLabel label = new javax.swing.JLabel(); label.setIcon(icon);
                row.add(label);
                row.add(nomina.getNombres());
                row.add(nomina.getApellidos());
                row.add(nomina.getCi());
                if (nomina.isEstatus()){
                    row.add("Sí");} else{
                    row.add("No");}
                model.addRow(row);
            }
        }
    }//GEN-LAST:event_btn_buscarNominaActionPerformed
    private void rdio_casaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdio_casaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdio_casaActionPerformed

    private void rdo_siPromCogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_siPromCogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_siPromCogActionPerformed

    private void txt_grpoSangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_grpoSangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_grpoSangActionPerformed

    private void txt_fiebreAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fiebreAltaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fiebreAltaActionPerformed

    private void chk_tripleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_tripleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_tripleActionPerformed

    private void lbl_chupaDedoNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_chupaDedoNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_chupaDedoNoActionPerformed

    private void rdo_aseaSoloNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_aseaSoloNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_aseaSoloNoActionPerformed

    private void rdo_orinaRopaNocheAveceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_orinaRopaNocheAveceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_orinaRopaNocheAveceActionPerformed

    private void btn_repEstdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_repEstdActionPerformed
        panel.removeAll();
        panel.add(principal);
        panel.repaint();
        panel.validate();
    }//GEN-LAST:event_btn_repEstdActionPerformed

    private void btn_repEstd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_repEstd1ActionPerformed
       System.exit(0);
    }//GEN-LAST:event_btn_repEstd1ActionPerformed

    private void repLegalMadreCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repLegalMadreCheckboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_repLegalMadreCheckboxActionPerformed

    private void repLegalPadreCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repLegalPadreCheckboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_repLegalPadreCheckboxActionPerformed

    private void fieldParentescoMadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldParentescoMadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldParentescoMadreActionPerformed

    private void fieldParentescoPadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldParentescoPadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldParentescoPadreActionPerformed

    private void fieldRepresentanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldRepresentanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldRepresentanteActionPerformed

    private void check_s_padresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_s_padresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_s_padresActionPerformed

    private void check_c_padresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_c_padresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_c_padresActionPerformed

    private void check_d_padresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_d_padresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_d_padresActionPerformed

    private void check_v_padresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_v_padresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_v_padresActionPerformed

    private void btn_aggImgNiñActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aggImgNiñActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        int returnValue = fileChooser.showOpenDialog(this);
        
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                // Get the selected file
                File selectedFile = fileChooser.getSelectedFile();
                if (selectedFile != null) {
                    Path filePath = selectedFile.toPath();
                    txt_rutImgNiñ.setText(selectedFile.getAbsolutePath());
                    try {
                        // Read all bytes from the selected file into a byte array
                        fileBytes = Files.readAllBytes(filePath);
                        img_infEstudiante.setSize(240, 240);
                        ImageIcon iconInit = new ImageIcon(fileBytes);
                        ImageIcon iconFin;
                        Image img = iconInit.getImage();
                        Image imgScaled = img.getScaledInstance(240, 240, Image.SCALE_SMOOTH);
                        iconFin = new ImageIcon(imgScaled);
                        
                        img_infEstudiante.setIcon(iconFin);
                        
                        } catch (Exception e) {
                            // Handle potential IOException during file reading
                            JOptionPane.showMessageDialog(this,
                                    "Error reading file: " + e.getMessage(),
                                    "File Read Error",
                                    JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace(); // Print stack trace for debugging
                        }
                    }
            }
    }//GEN-LAST:event_btn_aggImgNiñActionPerformed

    private void btn_aggImgMadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aggImgMadActionPerformed
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        int returnValue = fileChooser.showOpenDialog(this);
        
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                // Get the selected file
                File selectedFile = fileChooser.getSelectedFile();
                if (selectedFile != null) {
                    Path filePath = selectedFile.toPath();
                    txt_rutImgMad.setText(selectedFile.getAbsolutePath());
                    try {
                        // Read all bytes from the selected file into a byte array
                        fileBytesMad = Files.readAllBytes(filePath);
                        img_infMad.setSize(196, 196);
                        ImageIcon iconInit = new ImageIcon(fileBytes);
                        ImageIcon iconFin;
                        Image img = iconInit.getImage();
                        Image imgScaled = img.getScaledInstance(196, 196, Image.SCALE_SMOOTH);
                        iconFin = new ImageIcon(imgScaled);
                        
                        img_infMad.setIcon(iconFin);
                        
                        } catch (Exception e) {
                            // Handle potential IOException during file reading
                            JOptionPane.showMessageDialog(this,
                                    "Error reading file: " + e.getMessage(),
                                    "File Read Error",
                                    JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace(); // Print stack trace for debugging
                        }
                    }
            }               
// TODO add your handling code here:
    }//GEN-LAST:event_btn_aggImgMadActionPerformed

    private void btn_aggImgPadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aggImgPadreActionPerformed
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        int returnValue = fileChooser.showOpenDialog(this);
        
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                // Get the selected file
                File selectedFile = fileChooser.getSelectedFile();
                if (selectedFile != null) {
                    Path filePath = selectedFile.toPath();
                    txt_rutImgPadre.setText(selectedFile.getAbsolutePath());
                    try {
                        // Read all bytes from the selected file into a byte array
                        fileBytesPadre = Files.readAllBytes(filePath);
                        img_infPadre.setSize(196, 196);
                        ImageIcon iconInit = new ImageIcon(fileBytes);
                        ImageIcon iconFin;
                        Image img = iconInit.getImage();
                        Image imgScaled = img.getScaledInstance(196, 196, Image.SCALE_SMOOTH);
                        iconFin = new ImageIcon(imgScaled);
                        
                        img_infPadre.setIcon(iconFin);
                        
                        } catch (Exception e) {
                            // Handle potential IOException during file reading
                            JOptionPane.showMessageDialog(this,
                                    "Error reading file: " + e.getMessage(),
                                    "File Read Error",
                                    JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace(); // Print stack trace for debugging
                        }
                    }
            }               
// TODO add your handling code here:
    }//GEN-LAST:event_btn_aggImgPadreActionPerformed

    private void btn_aggImgRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aggImgRepActionPerformed
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        int returnValue = fileChooser.showOpenDialog(dat_rep);
        
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                // Get the selected file
                File selectedFile = fileChooser.getSelectedFile();
                if (selectedFile != null) {
                    Path filePath = selectedFile.toPath();
                    txt_rutImgRep.setText(selectedFile.getAbsolutePath());
                    try {
                        // Read all bytes from the selected file into a byte array
                        fileBytesRep = Files.readAllBytes(filePath);
                        img_infRep.setSize(196, 196);
                        ImageIcon iconInit = new ImageIcon(fileBytesRep);
                        ImageIcon iconFin;
                        Image img = iconInit.getImage();
                        Image imgScaled = img.getScaledInstance(196, 196, Image.SCALE_SMOOTH);
                        iconFin = new ImageIcon(imgScaled);
                        
                        img_infRep.setIcon(iconFin);
                        
                        } catch (Exception e) {
                            // Handle potential IOException during file reading
                            JOptionPane.showMessageDialog(dat_rep,
                                    "Error reading file: " + e.getMessage(),
                                    "File Read Error",
                                    JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace(); // Print stack trace for debugging
                        }
                    }
            }
// TODO add your handling code here:
    }//GEN-LAST:event_btn_aggImgRepActionPerformed

    private void btn_inscribirNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inscribirNomActionPerformed

    }//GEN-LAST:event_btn_inscribirNomActionPerformed

    private void btn_aggImgNiñ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aggImgNiñ1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_aggImgNiñ1ActionPerformed

    private void btn_eliminarBusqEstdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarBusqEstdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_eliminarBusqEstdActionPerformed

    private void btn_eliminarBusqEstd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarBusqEstd1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_eliminarBusqEstd1ActionPerformed

private void MenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {

    System.exit(0); // Cierra la aplicación Java
}

private void menuItemConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {
    //ConfiguracionForm config = new ConfiguracionForm(); // Asume que creaste esta clase
    //config.setVisible(true);
    // Opcional: Si quieres cerrar el menú principal mientras la configuración está abierta
    // this.dispose(); // No siempre deseable para una ventana de configuración
}        // TODO add your handling code here:
        // GEN-LAST:event_MenuItemConfiguracionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        //java.awt.EventQueue.invokeLater(() -> new menuPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField añoNacMadField;
    private javax.swing.JTextField añoNacNiñField;
    private javax.swing.JLabel añoNacNiñLbl;
    private javax.swing.JLabel añoNacNiñLbl1;
    private javax.swing.JLabel añoNacNiñLbl2;
    private javax.swing.JLabel añoNacNiñLbl3;
    private javax.swing.JTextField añoNacPadreField;
    private javax.swing.JTextField añoNacRepField;
    private javax.swing.JButton btnAutorizados;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnEstudiantes;
    private javax.swing.JButton btnRepresentantes;
    private javax.swing.JButton btn_aceptarInscripcionNiño;
    private javax.swing.JButton btn_aceptarInscripcionNomina;
    private javax.swing.JButton btn_aggImgMad;
    private javax.swing.JButton btn_aggImgNiñ;
    private javax.swing.JButton btn_aggImgNiñ1;
    private javax.swing.JButton btn_aggImgPadre;
    private javax.swing.JButton btn_aggImgRep;
    private javax.swing.JButton btn_buscarAut;
    private javax.swing.JButton btn_buscarEstd;
    private javax.swing.JButton btn_buscarNomina;
    private javax.swing.JButton btn_buscarRep;
    private javax.swing.JButton btn_cerrarBusqAut;
    private javax.swing.JButton btn_cerrarBusqEst;
    private javax.swing.JButton btn_cerrarBusqNom;
    private javax.swing.JButton btn_cerrarBusqRep;
    private javax.swing.JButton btn_editarBusqNom;
    private javax.swing.JButton btn_eliminarBusqEstd;
    private javax.swing.JButton btn_eliminarBusqEstd1;
    private javax.swing.JButton btn_inscribirEstd;
    private javax.swing.JButton btn_inscribirNom;
    private javax.swing.JButton btn_repEstd;
    private javax.swing.JButton btn_repEstd1;
    private javax.swing.JButton btn_verBusqAut;
    private javax.swing.JButton btn_verBusqEstd;
    private javax.swing.JButton btn_verBusqRep;
    private javax.swing.JButton btn_verNom;
    private javax.swing.JButton btn_volverBusqAut;
    private javax.swing.JButton btn_volverBusqEst;
    private javax.swing.JButton btn_volverBusqNom;
    private javax.swing.JButton btn_volverBusqRep;
    private javax.swing.JPanel busquedaAutorizados;
    private javax.swing.JPanel busquedaEstudiantes;
    private javax.swing.JPanel busquedaNomina;
    private javax.swing.JPanel busquedaRepresentante;
    private javax.swing.JComboBox<String> cBoxHoraAc;
    private javax.swing.JComboBox<String> cBoxHoraLev;
    private javax.swing.JComboBox<String> cBoxMinAc;
    private javax.swing.JComboBox<String> cBoxMinLev;
    private javax.swing.JCheckBox chec_bachiller_madre;
    private javax.swing.JCheckBox chec_bachiller_padre;
    private javax.swing.JCheckBox chec_bachiller_rep;
    private javax.swing.JCheckBox check_c_madre;
    private javax.swing.JCheckBox check_c_padre;
    private javax.swing.JCheckBox check_c_padres;
    private javax.swing.JCheckBox check_c_rep;
    private javax.swing.JCheckBox check_ci_madre;
    private javax.swing.JCheckBox check_ci_padre;
    private javax.swing.JCheckBox check_ci_representante;
    private javax.swing.JCheckBox check_copias;
    private javax.swing.JCheckBox check_d_madre;
    private javax.swing.JCheckBox check_d_padre;
    private javax.swing.JCheckBox check_d_padres;
    private javax.swing.JCheckBox check_d_rep;
    private javax.swing.JCheckBox check_ext_madre;
    private javax.swing.JCheckBox check_ext_padre;
    private javax.swing.JCheckBox check_ext_rep;
    private javax.swing.JCheckBox check_no_bachiller_madre;
    private javax.swing.JCheckBox check_no_bachiller_padre;
    private javax.swing.JCheckBox check_no_bachiller_rep;
    private javax.swing.JCheckBox check_originales;
    private javax.swing.JCheckBox check_partida;
    private javax.swing.JCheckBox check_s_madre;
    private javax.swing.JCheckBox check_s_padre;
    private javax.swing.JCheckBox check_s_padres;
    private javax.swing.JCheckBox check_s_rep;
    private javax.swing.JCheckBox check_superior_madre;
    private javax.swing.JCheckBox check_superior_padre;
    private javax.swing.JCheckBox check_superior_rep;
    private javax.swing.JCheckBox check_tsu_madre;
    private javax.swing.JCheckBox check_tsu_padre;
    private javax.swing.JCheckBox check_tsu_rep;
    private javax.swing.JCheckBox check_v_madre;
    private javax.swing.JCheckBox check_v_padre;
    private javax.swing.JCheckBox check_v_padres;
    private javax.swing.JCheckBox check_v_rep;
    private javax.swing.JCheckBox check_vacunas;
    private javax.swing.JCheckBox check_ven_madre;
    private javax.swing.JCheckBox check_ven_padre;
    private javax.swing.JCheckBox check_ven_rep;
    private javax.swing.JCheckBox chk_bcg;
    private javax.swing.JCheckBox chk_neur;
    private javax.swing.JCheckBox chk_otroConsult;
    private javax.swing.JCheckBox chk_polio;
    private javax.swing.JCheckBox chk_psic;
    private javax.swing.JCheckBox chk_pspg;
    private javax.swing.JCheckBox chk_terpLenguaje;
    private javax.swing.JCheckBox chk_tifus;
    private javax.swing.JCheckBox chk_triple;
    private javax.swing.JCheckBox chk_viruela;
    private javax.swing.JComboBox<String> cmb_filtroBusqAut;
    private javax.swing.JComboBox<String> cmb_filtroBusqEstd;
    private javax.swing.JComboBox<String> cmb_filtroBusqNom;
    private javax.swing.JComboBox<String> cmb_filtroBusqRep;
    private javax.swing.JPanel datGubNomina;
    private javax.swing.JPanel datPersNomina;
    private javax.swing.JPanel datPlantelNomina;
    private javax.swing.JPanel dat_aut;
    private javax.swing.JPanel dat_madre;
    private javax.swing.JPanel dat_padre;
    private javax.swing.JPanel dat_rep;
    private javax.swing.JPanel dat_socioFamiliares;
    private javax.swing.JPanel datosEstd;
    private javax.swing.JTextField diaNacMadField;
    private javax.swing.JTextField diaNacNiñField;
    private javax.swing.JLabel diaNacNiñLbl;
    private javax.swing.JLabel diaNacNiñLbl1;
    private javax.swing.JLabel diaNacNiñLbl2;
    private javax.swing.JLabel diaNacNiñLbl3;
    private javax.swing.JTextField diaNacPadreField;
    private javax.swing.JTextField diaNacRepField;
    private javax.swing.JPanel diagEstd;
    private javax.swing.JTextField fieldParentescoMadre;
    private javax.swing.JTextField fieldParentescoPadre;
    private javax.swing.JTextField fieldRepresentante;
    private javax.swing.JPanel headderBusqAut;
    private javax.swing.JPanel headderBusqEst;
    private javax.swing.JPanel headderBusqNomina;
    private javax.swing.JPanel headderBusqRep;
    private javax.swing.JPanel headderEstudiante;
    private javax.swing.JPanel headderInfNomina;
    private javax.swing.JPanel headderMenu;
    private javax.swing.JLabel im_logoInstitucionBusqNom;
    private javax.swing.JLabel imgAutorizado;
    private javax.swing.JLabel imgEstudiante;
    private javax.swing.JLabel imgFamilia;
    private javax.swing.JLabel imgHerramienta;
    private javax.swing.JLabel imgNomina;
    private javax.swing.JLabel img_busqEstd;
    private javax.swing.JLabel img_busqNomina;
    private javax.swing.JLabel img_busqRep;
    private javax.swing.JLabel img_infEstudiante;
    private javax.swing.JLabel img_infMad;
    private javax.swing.JLabel img_infPadre;
    private javax.swing.JLabel img_infRep;
    private javax.swing.JLabel img_logoInstitucionBusqEstd;
    private javax.swing.JLabel img_logoInstitucionBusqRep;
    private javax.swing.JPanel informacionEstudiante;
    private javax.swing.JPanel informacionNomina;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel label_alergia;
    private javax.swing.JLabel label_alergiaMed;
    private javax.swing.JLabel label_alergiaMed_cual;
    private javax.swing.JLabel label_alergia_cual;
    private javax.swing.JLabel label_apellido_madre;
    private javax.swing.JLabel label_apellido_padre;
    private javax.swing.JLabel label_apellido_rep;
    private javax.swing.JLabel label_camisa_niñ;
    private javax.swing.JLabel label_cedula;
    private javax.swing.JLabel label_ci_madre;
    private javax.swing.JLabel label_ci_padre;
    private javax.swing.JLabel label_ci_rep;
    private javax.swing.JLabel label_cognitivo;
    private javax.swing.JLabel label_cognitivo_cual;
    private javax.swing.JLabel label_condicion;
    private javax.swing.JLabel label_condicion_cual;
    private javax.swing.JLabel label_copias;
    private javax.swing.JLabel label_correo_madre;
    private javax.swing.JLabel label_correo_padre;
    private javax.swing.JLabel label_correo_rep;
    private javax.swing.JLabel label_direc_hab_madre;
    private javax.swing.JLabel label_direc_hab_padre;
    private javax.swing.JLabel label_direc_hab_rep;
    private javax.swing.JLabel label_direc_trabaj_madre;
    private javax.swing.JLabel label_direc_trabaj_padre;
    private javax.swing.JLabel label_direc_trabaj_rep;
    private javax.swing.JLabel label_edad_madre;
    private javax.swing.JLabel label_edad_niñ;
    private javax.swing.JLabel label_edad_padre;
    private javax.swing.JLabel label_edad_rep;
    private javax.swing.JLabel label_estadociv_madre;
    private javax.swing.JLabel label_estadociv_padre;
    private javax.swing.JLabel label_estadociv_rep;
    private javax.swing.JLabel label_estatura_niñ;
    private javax.swing.JLabel label_fec_nac_madre;
    private javax.swing.JLabel label_fec_nac_padre;
    private javax.swing.JLabel label_fec_nac_rep;
    private javax.swing.JLabel label_fecha;
    private javax.swing.JLabel label_grado_madre;
    private javax.swing.JLabel label_grado_padre;
    private javax.swing.JLabel label_grado_rep;
    private javax.swing.JLabel label_lenguaje;
    private javax.swing.JLabel label_lenguaje_cual;
    private javax.swing.JLabel label_lugar_nac_madre;
    private javax.swing.JLabel label_lugar_nac_padre;
    private javax.swing.JLabel label_lugar_nac_rep;
    private javax.swing.JLabel label_medidas_niñ;
    private javax.swing.JLabel label_motor_cual;
    private javax.swing.JLabel label_nacionalidad_madre;
    private javax.swing.JLabel label_nacionalidad_padre;
    private javax.swing.JLabel label_nacionalidad_rep;
    private javax.swing.JLabel label_nombes_madre;
    private javax.swing.JLabel label_nombes_padre;
    private javax.swing.JLabel label_nombes_rep;
    private javax.swing.JLabel label_observaciones;
    private javax.swing.JLabel label_ocupacion_madre;
    private javax.swing.JLabel label_ocupacion_padre;
    private javax.swing.JLabel label_ocupacion_rep;
    private javax.swing.JLabel label_originales;
    private javax.swing.JLabel label_partida;
    private javax.swing.JLabel label_persona;
    private javax.swing.JLabel label_peso_niñ;
    private javax.swing.JLabel label_procedencia_niñ;
    private javax.swing.JLabel label_responsable;
    private javax.swing.JLabel label_tall_pantalon_niñ;
    private javax.swing.JLabel label_tall_zapato_niñ;
    private javax.swing.JLabel label_tallas_niñ;
    private javax.swing.JLabel label_tlf_madre;
    private javax.swing.JLabel label_tlf_padre;
    private javax.swing.JLabel label_tlf_rep;
    private javax.swing.JLabel label_vacunas;
    private javax.swing.JLabel lbl_1x10Nomina;
    private javax.swing.JLabel lbl_apellidoNiñ;
    private javax.swing.JLabel lbl_apellidoNomina;
    private javax.swing.JLabel lbl_aseaSolo;
    private javax.swing.JLabel lbl_autRetiro;
    private javax.swing.JLabel lbl_buenAptito;
    private javax.swing.JLabel lbl_busqAutorizado;
    private javax.swing.JLabel lbl_busqEstudiante;
    private javax.swing.JLabel lbl_busqNomina;
    private javax.swing.JLabel lbl_busqRepresentante;
    private javax.swing.JLabel lbl_calleResNomina;
    private javax.swing.JLabel lbl_cant1x10Nomina;
    private javax.swing.JLabel lbl_casa;
    private javax.swing.JLabel lbl_ce;
    private javax.swing.JLabel lbl_ce1;
    private javax.swing.JLabel lbl_ce2;
    private javax.swing.JLabel lbl_ce3;
    private javax.swing.JLabel lbl_ce4;
    private javax.swing.JLabel lbl_ce5;
    private javax.swing.JLabel lbl_centrVotNomina;
    private javax.swing.JLabel lbl_chupaDedo;
    private javax.swing.JRadioButton lbl_chupaDedoAvece;
    private javax.swing.JRadioButton lbl_chupaDedoNo;
    private javax.swing.JRadioButton lbl_chupaDedoSi;
    private javax.swing.JLabel lbl_ciJefComNomina;
    private javax.swing.JLabel lbl_ciNomina;
    private javax.swing.JLabel lbl_cmeAyda;
    private javax.swing.JLabel lbl_comResNomina;
    private javax.swing.JLabel lbl_consultas;
    private javax.swing.JLabel lbl_cuidaNiñoHogar;
    private javax.swing.JLabel lbl_duermTarde;
    private javax.swing.JLabel lbl_edadCaminar;
    private javax.swing.JLabel lbl_edadDejPan;
    private javax.swing.JLabel lbl_edadNiñ;
    private javax.swing.JLabel lbl_edadNomina;
    private javax.swing.JLabel lbl_embPlan;
    private javax.swing.JLabel lbl_enfPadecidas;
    private javax.swing.JLabel lbl_estadoNiñ;
    private javax.swing.JLabel lbl_estatusNomina;
    private javax.swing.JLabel lbl_evacDia;
    private javax.swing.JLabel lbl_familiarExtra;
    private javax.swing.JLabel lbl_fechIngresoNomina;
    private javax.swing.JLabel lbl_fechNacimNomina;
    private javax.swing.JLabel lbl_fiebreAlta;
    private javax.swing.JLabel lbl_grpoSang;
    private javax.swing.JLabel lbl_headderEstudiante;
    private javax.swing.JLabel lbl_headderInfNomina;
    private javax.swing.JLabel lbl_horaAcostar;
    private javax.swing.JLabel lbl_horaAcostar1;
    private javax.swing.JLabel lbl_horaAcostar2;
    private javax.swing.JLabel lbl_horaAcostar3;
    private javax.swing.JLabel lbl_horaAcostar4;
    private javax.swing.JLabel lbl_horaLevantar;
    private javax.swing.JLabel lbl_jefComNomina;
    private javax.swing.JLabel lbl_lateralidad;
    private javax.swing.JLabel lbl_lugarNacNiñ;
    private javax.swing.JLabel lbl_medicTratante;
    private javax.swing.JLabel lbl_motor;
    private javax.swing.JLabel lbl_munNacNiñ;
    private javax.swing.JLabel lbl_munResNomina;
    private javax.swing.JLabel lbl_munVotNomina;
    private javax.swing.JLabel lbl_nacionalidadNiñ;
    private javax.swing.JLabel lbl_nivelNominaMaestra;
    private javax.swing.JLabel lbl_nombreNiñ;
    private javax.swing.JLabel lbl_nombreNomina;
    private javax.swing.JLabel lbl_orinaRopaDia;
    private javax.swing.JLabel lbl_orinaRopaNoch;
    private javax.swing.JLabel lbl_otraVac;
    private javax.swing.JLabel lbl_parrqVotNomina;
    private javax.swing.JLabel lbl_prbDesarr;
    private javax.swing.JLabel lbl_prmParto;
    private javax.swing.JLabel lbl_promPartoCual;
    private javax.swing.JLabel lbl_prrqResNomina;
    private javax.swing.JLabel lbl_qnDuermeNino;
    private javax.swing.JLabel lbl_relPadres;
    private javax.swing.JLabel lbl_rifNomina;
    private javax.swing.JLabel lbl_seccionNominaMaestra;
    private javax.swing.JLabel lbl_sexoNiñ;
    private javax.swing.JLabel lbl_sexoNomina;
    private javax.swing.JLabel lbl_tipocasa;
    private javax.swing.JLabel lbl_tlf1Nomina;
    private javax.swing.JLabel lbl_tlf2Nomina;
    private javax.swing.JLabel lbl_tlfMedicTrat;
    private javax.swing.JLabel lbl_turnoNomina;
    private javax.swing.JLabel lbl_vac;
    private javax.swing.JLabel lbl_votoNomina;
    private javax.swing.JLabel logoPreescolar;
    private javax.swing.JTextField mesNacMadField;
    private javax.swing.JTextField mesNacNiñField;
    private javax.swing.JLabel mesNacNiñLbl;
    private javax.swing.JLabel mesNacNiñLbl1;
    private javax.swing.JLabel mesNacNiñLbl2;
    private javax.swing.JLabel mesNacNiñLbl3;
    private javax.swing.JTextField mesNacPadreField;
    private javax.swing.JTextField mesNacRepField;
    private javax.swing.JPanel otros;
    private javax.swing.JPanel panel;
    private javax.swing.JTabbedPane panelDatosEstd;
    private javax.swing.JPanel panelDocumentosEstd;
    private javax.swing.JTabbedPane panelRepresentanteEstd;
    private javax.swing.JLabel parentescoMadreTexto;
    private javax.swing.JLabel parentescoPadreTexto;
    private javax.swing.JLabel parentescoRepresentanteTexto;
    private javax.swing.JPanel principal;
    private javax.swing.JRadioButton radio_guarderia_niñ;
    private javax.swing.JRadioButton radio_hogar_cuidado_niñ;
    private javax.swing.JRadioButton radio_hogar_niñ;
    private javax.swing.JRadioButton radio_mismo_plantel_niñ;
    private javax.swing.JRadioButton radio_multihogar_niñ;
    private javax.swing.JRadioButton radio_otro_plantel_niñ;
    private javax.swing.JRadioButton radio_otros_niñ;
    private javax.swing.JRadioButton rdb_femNiñ;
    private javax.swing.JRadioButton rdb_femNomina;
    private javax.swing.JRadioButton rdb_mascNiñ;
    private javax.swing.JRadioButton rdb_mascNomina;
    private javax.swing.JRadioButton rdb_no1x10;
    private javax.swing.JRadioButton rdb_noVot;
    private javax.swing.JRadioButton rdb_si1x10;
    private javax.swing.JRadioButton rdb_siVot;
    private javax.swing.JRadioButton rdio_alquilada;
    private javax.swing.JRadioButton rdio_apartamento;
    private javax.swing.JRadioButton rdio_casa;
    private javax.swing.JRadioButton rdio_otro;
    private javax.swing.JRadioButton rdio_prestada;
    private javax.swing.JRadioButton rdio_propia;
    private javax.swing.JRadioButton rdio_zinc;
    private javax.swing.JRadioButton rdo_ambidiestro;
    private javax.swing.JRadioButton rdo_aseaSoloNo;
    private javax.swing.JRadioButton rdo_aseaSoloSi;
    private javax.swing.JRadioButton rdo_desconocido;
    private javax.swing.JRadioButton rdo_diestro;
    private javax.swing.JRadioButton rdo_duermTardeAveces;
    private javax.swing.JRadioButton rdo_duermTardeNo;
    private javax.swing.JRadioButton rdo_duermTardeSi;
    private javax.swing.JRadioButton rdo_evacDiaAvece;
    private javax.swing.JRadioButton rdo_evacDiaNo;
    private javax.swing.JRadioButton rdo_evacDiaSi;
    private javax.swing.JRadioButton rdo_noAlergia;
    private javax.swing.JRadioButton rdo_noAlergiaMed;
    private javax.swing.JRadioButton rdo_noBuenApetito;
    private javax.swing.JRadioButton rdo_noCmeAyda;
    private javax.swing.JRadioButton rdo_noCondExtra;
    private javax.swing.JRadioButton rdo_noEmbPlan;
    private javax.swing.JRadioButton rdo_noPrmLeng;
    private javax.swing.JRadioButton rdo_noPrmMotr;
    private javax.swing.JRadioButton rdo_noPrmParto;
    private javax.swing.JRadioButton rdo_noPromCog;
    private javax.swing.JRadioButton rdo_orinaRopaDiaAvece;
    private javax.swing.JRadioButton rdo_orinaRopaDiaNo;
    private javax.swing.JRadioButton rdo_orinaRopaDiaSi;
    private javax.swing.JRadioButton rdo_orinaRopaNocheAvece;
    private javax.swing.JRadioButton rdo_orinaRopaNocheNo;
    private javax.swing.JRadioButton rdo_orinaRopaNocheSi;
    private javax.swing.JRadioButton rdo_siAlergia;
    private javax.swing.JRadioButton rdo_siAlergiaMed;
    private javax.swing.JRadioButton rdo_siBuenApetito;
    private javax.swing.JRadioButton rdo_siCmeAyda;
    private javax.swing.JRadioButton rdo_siCondExtra;
    private javax.swing.JRadioButton rdo_siEmbPLan;
    private javax.swing.JRadioButton rdo_siPrmParto;
    private javax.swing.JRadioButton rdo_siPromCog;
    private javax.swing.JRadioButton rdo_siPromLeng;
    private javax.swing.JRadioButton rdo_siPromMotr;
    private javax.swing.JRadioButton rdo_zurdo;
    private javax.swing.JLabel repLegalLabel;
    private javax.swing.JCheckBox repLegalMadreCheckbox;
    private javax.swing.JLabel repLegalPadre;
    private javax.swing.JCheckBox repLegalPadreCheckbox;
    private javax.swing.JScrollPane scl_busqNom;
    private javax.swing.JScrollPane scrl_autRetiro;
    private javax.swing.JScrollPane scrl_busqAut;
    private javax.swing.JScrollPane scrl_busqEstd;
    private javax.swing.JScrollPane scrl_busqRep;
    private javax.swing.JScrollPane scrl_familiarExtra;
    private javax.swing.JScrollPane scroll_observaciones;
    private javax.swing.JTable tbl_autRetiro;
    private javax.swing.JTable tbl_busqAut;
    private javax.swing.JTable tbl_busqEstd;
    private javax.swing.JTable tbl_busqNomina;
    private javax.swing.JTable tbl_busqRep;
    private javax.swing.JTable tbl_familiarExtra;
    private javax.swing.JTextField text_alergia_cual;
    private javax.swing.JTextField text_alergia_cual1;
    private javax.swing.JTextField text_apellidos_madre;
    private javax.swing.JTextField text_apellidos_padre;
    private javax.swing.JTextField text_apellidos_rep;
    private javax.swing.JTextField text_ci_madre;
    private javax.swing.JTextField text_ci_padre;
    private javax.swing.JTextField text_ci_rep;
    private javax.swing.JTextField text_cognitivo_cual;
    private javax.swing.JTextField text_condicion_cual;
    private javax.swing.JTextField text_correo_madre;
    private javax.swing.JTextField text_correo_padre;
    private javax.swing.JTextField text_correo_rep;
    private javax.swing.JTextField text_direc_hab_madre;
    private javax.swing.JTextField text_direc_hab_padre;
    private javax.swing.JTextField text_direc_hab_rep;
    private javax.swing.JTextField text_direc_trabj_madre;
    private javax.swing.JTextField text_direc_trabj_padre;
    private javax.swing.JTextField text_direc_trabj_rep;
    private javax.swing.JTextField text_edad_madre;
    private javax.swing.JTextField text_edad_niñ;
    private javax.swing.JTextField text_edad_padre;
    private javax.swing.JTextField text_edad_rep;
    private javax.swing.JTextField text_estatura_niñ;
    private javax.swing.JTextField text_fecha;
    private javax.swing.JTextField text_lenguaje_cual;
    private javax.swing.JTextField text_lugar_nac_madre;
    private javax.swing.JTextField text_lugar_nac_padre;
    private javax.swing.JTextField text_lugar_nac_rep;
    private javax.swing.JTextField text_motor_cual;
    private javax.swing.JTextField text_nombres_madre;
    private javax.swing.JTextField text_nombres_padre;
    private javax.swing.JTextField text_nombres_rep;
    private javax.swing.JTextPane text_observaciones;
    private javax.swing.JTextField text_ocupacion_madre;
    private javax.swing.JTextField text_ocupacion_padre;
    private javax.swing.JTextField text_ocupacion_rep;
    private javax.swing.JTextField text_persona;
    private javax.swing.JTextField text_peso_niñ;
    private javax.swing.JTextField text_responsable;
    private javax.swing.JTextField text_tall_camisa_niñ;
    private javax.swing.JTextField text_tall_pantalon_niñ;
    private javax.swing.JTextField text_tall_zapato_niñ;
    private javax.swing.JTextField text_tlf_madre;
    private javax.swing.JTextField text_tlf_padre;
    private javax.swing.JTextField text_tlf_rep;
    private javax.swing.JLabel txtMenuPrincipal1;
    private javax.swing.JTextField txt_apellidoNiñ;
    private javax.swing.JTextField txt_apellidoNomina;
    private javax.swing.JTextField txt_calleResNomina;
    private javax.swing.JTextField txt_cant1x10Nomina;
    private javax.swing.JTextField txt_ce;
    private javax.swing.JTextField txt_centrVotNomina;
    private javax.swing.JTextField txt_ciJefComNomina;
    private javax.swing.JTextField txt_ciNomina;
    private javax.swing.JTextField txt_comResNomina;
    private javax.swing.JTextField txt_cuidaNiñoHogar;
    private javax.swing.JTextField txt_edadCaminar;
    private javax.swing.JTextField txt_edadDejPan;
    private javax.swing.JTextField txt_edadNomina;
    private javax.swing.JTextField txt_enfPadecidas;
    private javax.swing.JTextField txt_estadoNiñ;
    private javax.swing.JTextField txt_estatusNomina;
    private javax.swing.JTextField txt_fechNacimNomina;
    private javax.swing.JTextField txt_fechaIngresoNomina;
    private javax.swing.JTextField txt_fiebreAlta;
    private javax.swing.JTextField txt_filtroBusqAut;
    private javax.swing.JTextField txt_filtroBusqEstd;
    private javax.swing.JTextField txt_filtroBusqNom;
    private javax.swing.JTextField txt_filtroBusqRep;
    private javax.swing.JTextField txt_grpoSang;
    private javax.swing.JTextField txt_jefComNomina;
    private javax.swing.JTextField txt_lugarNacNiñ;
    private javax.swing.JTextField txt_medicoTratante;
    private javax.swing.JTextField txt_munResNomina;
    private javax.swing.JTextField txt_munVotNomina;
    private javax.swing.JTextField txt_municNiñ;
    private javax.swing.JTextField txt_nacionalidadNiñ;
    private javax.swing.JTextField txt_nivelNominaMaestra;
    private javax.swing.JTextField txt_nombreNiñ;
    private javax.swing.JTextField txt_nombreNomina;
    private javax.swing.JTextField txt_otraVac;
    private javax.swing.JTextField txt_otroConsult;
    private javax.swing.JTextField txt_parrqVotNomina;
    private javax.swing.JTextField txt_prmPartocual;
    private javax.swing.JTextField txt_prrqResNomina;
    private javax.swing.JTextField txt_qnDuermeNino;
    private javax.swing.JTextField txt_rifNomina;
    private javax.swing.JTextField txt_rutImgMad;
    private javax.swing.JTextField txt_rutImgNiñ;
    private javax.swing.JTextField txt_rutImgNiñ1;
    private javax.swing.JTextField txt_rutImgPadre;
    private javax.swing.JTextField txt_rutImgRep;
    private javax.swing.JTextField txt_seccionNominaMaestra;
    private javax.swing.JTextField txt_tlf1Nomina;
    private javax.swing.JTextField txt_tlf2Nomina;
    private javax.swing.JTextField txt_tlfMedicoTrat;
    private javax.swing.JTextField txt_turnoNomina;
    // End of variables declaration//GEN-END:variables
}
