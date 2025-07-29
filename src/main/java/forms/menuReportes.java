/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forms;
import subsystems.docGen;
import subsystems.individuos.*;
import java.time.LocalDate;

public class menuReportes extends javax.swing.JDialog {
    
    public menuReportes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_vacio = new javax.swing.JPanel();
        panel_constanciaEstudio = new javax.swing.JPanel();
        lbl_direcEstdio = new javax.swing.JLabel();
        txt_direcEstdio = new javax.swing.JTextField();
        lbl_lapso = new javax.swing.JLabel();
        txt_lapso = new javax.swing.JTextField();
        lbl_finLapso = new javax.swing.JLabel();
        txt_finLapso = new javax.swing.JTextField();
        lbl_maternalEstdio = new javax.swing.JLabel();
        chk_maternalEstdio = new javax.swing.JCheckBox();
        btn_estudio = new javax.swing.JButton();
        panel_constanciaRetiro = new javax.swing.JPanel();
        lbl_direcRet = new javax.swing.JLabel();
        txt_direcRet = new javax.swing.JTextField();
        btn_retiro = new javax.swing.JButton();
        lbl_repRet = new javax.swing.JLabel();
        txt_repRet = new javax.swing.JTextField();
        panel_constanciaConducta = new javax.swing.JPanel();
        lbl_direcConducta = new javax.swing.JLabel();
        txt_direcConducta = new javax.swing.JTextField();
        lbl_maternal = new javax.swing.JLabel();
        chk_maternal = new javax.swing.JCheckBox();
        btn_conducta = new javax.swing.JButton();
        panel_licenciaMedica = new javax.swing.JPanel();
        lbl_plantel = new javax.swing.JLabel();
        txt_plantel = new javax.swing.JTextField();
        lbl_municipio = new javax.swing.JLabel();
        txt_municipio = new javax.swing.JTextField();
        lbl_estado = new javax.swing.JLabel();
        txt_estado = new javax.swing.JTextField();
        lbl_distrito = new javax.swing.JLabel();
        txt_distrito = new javax.swing.JTextField();
        lbl_motivo = new javax.swing.JLabel();
        txt_motivo = new javax.swing.JTextField();
        lbl_dias = new javax.swing.JLabel();
        txt_dias = new javax.swing.JTextField();
        lbl_inicio = new javax.swing.JLabel();
        cmb_diaInicio = new javax.swing.JComboBox<>();
        cmb_mesInicio = new javax.swing.JComboBox<>();
        txt_añoInicio = new javax.swing.JTextField();
        lbl_fin = new javax.swing.JLabel();
        cmb_diaFin = new javax.swing.JComboBox<>();
        cmb_mesFin = new javax.swing.JComboBox<>();
        txt_añoFin = new javax.swing.JTextField();
        btn_medica = new javax.swing.JButton();
        headder = new javax.swing.JPanel();
        lbl_headder = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        cmb_tipoReporte = new javax.swing.JComboBox<>();
        btn_buscarReporte = new javax.swing.JButton();
        lbl_persReporte = new javax.swing.JLabel();
        txt_persReporte = new javax.swing.JTextField();
        lbl_ciPersReporte = new javax.swing.JLabel();
        txt_ciPersReporte = new javax.swing.JTextField();

        panel_vacio.setBackground(new java.awt.Color(197, 232, 225));

        javax.swing.GroupLayout panel_vacioLayout = new javax.swing.GroupLayout(panel_vacio);
        panel_vacio.setLayout(panel_vacioLayout);
        panel_vacioLayout.setHorizontalGroup(
            panel_vacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
        );
        panel_vacioLayout.setVerticalGroup(
            panel_vacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 282, Short.MAX_VALUE)
        );

        panel_constanciaEstudio.setBackground(new java.awt.Color(255, 255, 204));

        lbl_direcEstdio.setText("Directora");

        lbl_lapso.setText("Lapso");

        lbl_finLapso.setText("Fin Lapso");

        txt_finLapso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_finLapsoActionPerformed(evt);
            }
        });

        lbl_maternalEstdio.setText("Seleccione si el niño pertenece a maternal:");

        chk_maternalEstdio.setText("Maternal");

        btn_estudio.setText("GENERAR");
        btn_estudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_estudioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_constanciaEstudioLayout = new javax.swing.GroupLayout(panel_constanciaEstudio);
        panel_constanciaEstudio.setLayout(panel_constanciaEstudioLayout);
        panel_constanciaEstudioLayout.setHorizontalGroup(
            panel_constanciaEstudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_constanciaEstudioLayout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addGroup(panel_constanciaEstudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_constanciaEstudioLayout.createSequentialGroup()
                        .addComponent(lbl_maternalEstdio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chk_maternalEstdio))
                    .addGroup(panel_constanciaEstudioLayout.createSequentialGroup()
                        .addComponent(lbl_finLapso)
                        .addGap(18, 18, 18)
                        .addComponent(txt_finLapso, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_constanciaEstudioLayout.createSequentialGroup()
                        .addGroup(panel_constanciaEstudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_direcEstdio)
                            .addComponent(lbl_lapso))
                        .addGap(20, 20, 20)
                        .addGroup(panel_constanciaEstudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_lapso)
                            .addComponent(txt_direcEstdio)))
                    .addComponent(btn_estudio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(227, Short.MAX_VALUE))
        );
        panel_constanciaEstudioLayout.setVerticalGroup(
            panel_constanciaEstudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_constanciaEstudioLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(panel_constanciaEstudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_direcEstdio)
                    .addComponent(txt_direcEstdio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_constanciaEstudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_lapso)
                    .addComponent(txt_lapso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_constanciaEstudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_finLapso)
                    .addComponent(txt_finLapso, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(panel_constanciaEstudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_maternalEstdio)
                    .addComponent(chk_maternalEstdio))
                .addGap(18, 18, 18)
                .addComponent(btn_estudio)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        panel_constanciaRetiro.setBackground(new java.awt.Color(197, 232, 225));

        lbl_direcRet.setText("Directora:");

        txt_direcRet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_direcRetActionPerformed(evt);
            }
        });

        btn_retiro.setText("GENERAR");
        btn_retiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_retiroActionPerformed(evt);
            }
        });

        lbl_repRet.setText("Representante:");

        txt_repRet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_repRetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_constanciaRetiroLayout = new javax.swing.GroupLayout(panel_constanciaRetiro);
        panel_constanciaRetiro.setLayout(panel_constanciaRetiroLayout);
        panel_constanciaRetiroLayout.setHorizontalGroup(
            panel_constanciaRetiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_constanciaRetiroLayout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addGroup(panel_constanciaRetiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_constanciaRetiroLayout.createSequentialGroup()
                        .addComponent(lbl_repRet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_repRet))
                    .addComponent(btn_retiro, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_constanciaRetiroLayout.createSequentialGroup()
                        .addComponent(lbl_direcRet)
                        .addGap(33, 33, 33)
                        .addComponent(txt_direcRet, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(174, Short.MAX_VALUE))
        );
        panel_constanciaRetiroLayout.setVerticalGroup(
            panel_constanciaRetiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_constanciaRetiroLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(panel_constanciaRetiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbl_direcRet)
                    .addComponent(txt_direcRet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(panel_constanciaRetiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbl_repRet)
                    .addComponent(txt_repRet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_retiro)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        panel_constanciaConducta.setBackground(new java.awt.Color(204, 255, 204));

        lbl_direcConducta.setText("Directora:");

        lbl_maternal.setText("Seleccione si el niño pertenece a maternal:");

        chk_maternal.setText("Maternal");

        btn_conducta.setText("GENERAR");
        btn_conducta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_conductaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_constanciaConductaLayout = new javax.swing.GroupLayout(panel_constanciaConducta);
        panel_constanciaConducta.setLayout(panel_constanciaConductaLayout);
        panel_constanciaConductaLayout.setHorizontalGroup(
            panel_constanciaConductaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_constanciaConductaLayout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addGroup(panel_constanciaConductaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_constanciaConductaLayout.createSequentialGroup()
                        .addComponent(lbl_maternal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chk_maternal))
                    .addGroup(panel_constanciaConductaLayout.createSequentialGroup()
                        .addComponent(lbl_direcConducta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_direcConducta, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_conducta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(187, Short.MAX_VALUE))
        );
        panel_constanciaConductaLayout.setVerticalGroup(
            panel_constanciaConductaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_constanciaConductaLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(panel_constanciaConductaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_direcConducta)
                    .addComponent(txt_direcConducta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_constanciaConductaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chk_maternal)
                    .addComponent(lbl_maternal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_conducta)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        panel_licenciaMedica.setBackground(new java.awt.Color(204, 204, 255));

        lbl_plantel.setText("Plantel:");

        txt_plantel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_plantelActionPerformed(evt);
            }
        });

        lbl_municipio.setText("Municipio");

        lbl_estado.setText("Estado");

        txt_estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_estadoActionPerformed(evt);
            }
        });

        lbl_distrito.setText("Distrito");

        lbl_motivo.setText("Motivo");

        lbl_dias.setText("Cantidad de dias");

        lbl_inicio.setText("Dia inicio");

        cmb_diaInicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        cmb_mesInicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        lbl_fin.setText("Dia Fin");

        cmb_diaFin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        cmb_mesFin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        btn_medica.setText("GENERAR");
        btn_medica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_medicaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_licenciaMedicaLayout = new javax.swing.GroupLayout(panel_licenciaMedica);
        panel_licenciaMedica.setLayout(panel_licenciaMedicaLayout);
        panel_licenciaMedicaLayout.setHorizontalGroup(
            panel_licenciaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_licenciaMedicaLayout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addGroup(panel_licenciaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_licenciaMedicaLayout.createSequentialGroup()
                        .addComponent(lbl_dias)
                        .addGap(37, 37, 37)
                        .addComponent(txt_dias))
                    .addComponent(btn_medica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_licenciaMedicaLayout.createSequentialGroup()
                        .addComponent(lbl_estado)
                        .addGap(90, 90, 90)
                        .addComponent(txt_estado))
                    .addGroup(panel_licenciaMedicaLayout.createSequentialGroup()
                        .addComponent(lbl_distrito)
                        .addGap(87, 87, 87)
                        .addComponent(txt_distrito))
                    .addGroup(panel_licenciaMedicaLayout.createSequentialGroup()
                        .addGroup(panel_licenciaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_fin)
                            .addComponent(lbl_inicio)
                            .addComponent(lbl_motivo))
                        .addGap(76, 76, 76)
                        .addGroup(panel_licenciaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_motivo)
                            .addGroup(panel_licenciaMedicaLayout.createSequentialGroup()
                                .addGroup(panel_licenciaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cmb_diaFin, 0, 1, Short.MAX_VALUE)
                                    .addComponent(cmb_diaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panel_licenciaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmb_mesInicio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmb_mesFin, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_licenciaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_añoInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                    .addComponent(txt_añoFin)))))
                    .addGroup(panel_licenciaMedicaLayout.createSequentialGroup()
                        .addGroup(panel_licenciaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_municipio)
                            .addComponent(lbl_plantel))
                        .addGap(71, 71, 71)
                        .addGroup(panel_licenciaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_municipio)
                            .addComponent(txt_plantel))))
                .addContainerGap(181, Short.MAX_VALUE))
        );
        panel_licenciaMedicaLayout.setVerticalGroup(
            panel_licenciaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_licenciaMedicaLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(panel_licenciaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_plantel)
                    .addComponent(txt_plantel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_licenciaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_municipio)
                    .addComponent(txt_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_licenciaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_estado)
                    .addComponent(txt_estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_licenciaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_distrito)
                    .addComponent(txt_distrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_licenciaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_motivo)
                    .addComponent(txt_motivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_licenciaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_inicio)
                    .addComponent(cmb_diaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_mesInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_añoFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_licenciaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_fin)
                    .addComponent(cmb_diaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_mesFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_añoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_licenciaMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_dias)
                    .addComponent(txt_dias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_medica)
                .addContainerGap(147, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        headder.setBackground(new java.awt.Color(27, 120, 101));
        headder.setPreferredSize(new java.awt.Dimension(668, 48));

        lbl_headder.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbl_headder.setForeground(new java.awt.Color(250, 247, 239));
        lbl_headder.setText("Reportes");

        javax.swing.GroupLayout headderLayout = new javax.swing.GroupLayout(headder);
        headder.setLayout(headderLayout);
        headderLayout.setHorizontalGroup(
            headderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headderLayout.createSequentialGroup()
                .addContainerGap(326, Short.MAX_VALUE)
                .addComponent(lbl_headder)
                .addGap(324, 324, 324))
        );
        headderLayout.setVerticalGroup(
            headderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headderLayout.createSequentialGroup()
                .addComponent(lbl_headder)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panel.setBackground(new java.awt.Color(197, 232, 225));
        panel.setLayout(new java.awt.CardLayout());

        cmb_tipoReporte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "Licencia Medica", "Constancia de retiro", "Constancia de conducta", "Constancia de estudios", " ", " " }));
        cmb_tipoReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_tipoReporteActionPerformed(evt);
            }
        });

        btn_buscarReporte.setText("Generar");
        btn_buscarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarReporteActionPerformed(evt);
            }
        });

        lbl_persReporte.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbl_persReporte.setText("Nombre:");

        txt_persReporte.setEditable(false);
        txt_persReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_persReporteActionPerformed(evt);
            }
        });

        lbl_ciPersReporte.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbl_ciPersReporte.setText("Cédula:");

        txt_ciPersReporte.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headder, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmb_tipoReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_buscarReporte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_persReporte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_persReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_ciPersReporte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ciPersReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_ciPersReporte)
                        .addComponent(txt_ciPersReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_buscarReporte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmb_tipoReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_persReporte)
                        .addComponent(txt_persReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_tipoReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_tipoReporteActionPerformed
    }//GEN-LAST:event_cmb_tipoReporteActionPerformed

    private void btn_buscarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarReporteActionPerformed
        if (cmb_tipoReporte.getSelectedItem() == "..."){
            panel.removeAll();
            panel.add(panel_vacio);
            panel.repaint();
            panel.revalidate();
        }
        if (cmb_tipoReporte.getSelectedItem() == "Licencia Medica"){
            panel.removeAll();
            panel.add(panel_licenciaMedica);
            panel.repaint();
            panel.revalidate();
        }
        
        if (cmb_tipoReporte.getSelectedItem() == "Constancia de estudios"){
            panel.removeAll();
            panel.add(panel_constanciaEstudio);
            panel.repaint();
            panel.revalidate();
        }
        if (cmb_tipoReporte.getSelectedItem() == "Constancia de conducta"){
            panel.removeAll();
            panel.add(panel_constanciaConducta);
            panel.repaint();
            panel.revalidate();
        }
        if (cmb_tipoReporte.getSelectedItem() == "Constancia de retiro"){
            panel.removeAll();
            panel.add(panel_constanciaRetiro);
            panel.repaint();
            panel.revalidate();
        }
        
    }//GEN-LAST:event_btn_buscarReporteActionPerformed

    private void txt_persReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_persReporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_persReporteActionPerformed

    private void txt_direcRetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_direcRetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_direcRetActionPerformed

    private void txt_plantelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_plantelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_plantelActionPerformed

    private void txt_estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_estadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_estadoActionPerformed

    private void txt_finLapsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_finLapsoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_finLapsoActionPerformed

    private void btn_estudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_estudioActionPerformed
        String ce = txt_ciPersReporte.getText().trim();
        estudiante est = new estudiante();
        est.setCe(ce);
        docGen.generarConstanciaEstudios(est, txt_direcEstdio.getText().trim(), txt_lapso.getText().trim(), txt_finLapso.getText().trim(), chk_maternalEstdio.isSelected());
    }//GEN-LAST:event_btn_estudioActionPerformed

    private void btn_retiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_retiroActionPerformed
        String ce = txt_ciPersReporte.getText().trim();
        int ci = Integer.parseInt(txt_repRet.getText().trim());
        estudiante est = new estudiante();
        est.setCe(ce);
        representante rep = new representante();
        rep.setCi(ci);
        docGen.generarConstanciaRetiro(est, rep, txt_direcRet.getText().trim());
    }//GEN-LAST:event_btn_retiroActionPerformed

    private void btn_conductaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_conductaActionPerformed
        String ce = txt_ciPersReporte.getText().trim();
        estudiante est = new estudiante();
        est.setCe(ce);
        
        Boolean maternal;
        if(chk_maternal.isEnabled()){
            maternal = true;
        }
        else{
            maternal = false;
        }
         
        docGen.generarConstanciaConducta(est, txt_direcConducta.getText().trim(), maternal);
    }//GEN-LAST:event_btn_conductaActionPerformed

    private void btn_medicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_medicaActionPerformed
        String plantel = txt_plantel.getText().trim();
        String municipio = txt_municipio.getText().trim();
        String estado = txt_estado.getText().trim();
        String distrito = txt_distrito.getText().trim();
        String motivo = txt_motivo.getText().trim();
        int dias = Integer.parseInt(txt_dias.getText().trim());
        
        LocalDate DiaInicio;
        DiaInicio = LocalDate.of(Integer.parseInt(txt_añoInicio.getText()),
                                    Integer.parseInt(cmb_mesInicio.toString()),
                                        Integer.parseInt(cmb_diaInicio.toString()));
        
        LocalDate DiaFin;
        DiaFin = LocalDate.of(Integer.parseInt(txt_añoFin.getText()),
                                    Integer.parseInt(cmb_mesFin.toString()),
                                        Integer.parseInt(cmb_diaFin.toString()));
        
        trabajador nomina = new trabajador();
        nomina.setCi(Integer.parseInt(txt_ciPersReporte.getText()));
        
        docGen.generarLicenciaMedica(nomina, plantel, municipio, estado, distrito, motivo, dias, DiaInicio, DiaFin);
        
    }//GEN-LAST:event_btn_medicaActionPerformed

    private void txt_repRetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_repRetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_repRetActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menuReportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuReportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuReportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuReportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                menuReportes dialog = new menuReportes(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buscarReporte;
    private javax.swing.JButton btn_conducta;
    private javax.swing.JButton btn_estudio;
    private javax.swing.JButton btn_medica;
    private javax.swing.JButton btn_retiro;
    private javax.swing.JCheckBox chk_maternal;
    private javax.swing.JCheckBox chk_maternalEstdio;
    private javax.swing.JComboBox<String> cmb_diaFin;
    private javax.swing.JComboBox<String> cmb_diaInicio;
    private javax.swing.JComboBox<String> cmb_mesFin;
    private javax.swing.JComboBox<String> cmb_mesInicio;
    private javax.swing.JComboBox<String> cmb_tipoReporte;
    private javax.swing.JPanel headder;
    private javax.swing.JLabel lbl_ciPersReporte;
    private javax.swing.JLabel lbl_dias;
    private javax.swing.JLabel lbl_direcConducta;
    private javax.swing.JLabel lbl_direcEstdio;
    private javax.swing.JLabel lbl_direcRet;
    private javax.swing.JLabel lbl_distrito;
    private javax.swing.JLabel lbl_estado;
    private javax.swing.JLabel lbl_fin;
    private javax.swing.JLabel lbl_finLapso;
    private javax.swing.JLabel lbl_headder;
    private javax.swing.JLabel lbl_inicio;
    private javax.swing.JLabel lbl_lapso;
    private javax.swing.JLabel lbl_maternal;
    private javax.swing.JLabel lbl_maternalEstdio;
    private javax.swing.JLabel lbl_motivo;
    private javax.swing.JLabel lbl_municipio;
    private javax.swing.JLabel lbl_persReporte;
    private javax.swing.JLabel lbl_plantel;
    private javax.swing.JLabel lbl_repRet;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panel_constanciaConducta;
    private javax.swing.JPanel panel_constanciaEstudio;
    private javax.swing.JPanel panel_constanciaRetiro;
    private javax.swing.JPanel panel_licenciaMedica;
    private javax.swing.JPanel panel_vacio;
    private javax.swing.JTextField txt_añoFin;
    private javax.swing.JTextField txt_añoInicio;
    private javax.swing.JTextField txt_ciPersReporte;
    private javax.swing.JTextField txt_dias;
    private javax.swing.JTextField txt_direcConducta;
    private javax.swing.JTextField txt_direcEstdio;
    private javax.swing.JTextField txt_direcRet;
    private javax.swing.JTextField txt_distrito;
    private javax.swing.JTextField txt_estado;
    private javax.swing.JTextField txt_finLapso;
    private javax.swing.JTextField txt_lapso;
    private javax.swing.JTextField txt_motivo;
    private javax.swing.JTextField txt_municipio;
    private javax.swing.JTextField txt_persReporte;
    private javax.swing.JTextField txt_plantel;
    private javax.swing.JTextField txt_repRet;
    // End of variables declaration//GEN-END:variables
}
