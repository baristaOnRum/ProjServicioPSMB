/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forms;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import subsystems.individuos.*;
/**
 *
 * @author Uzer
 */
public class informacionRepresentante extends javax.swing.JDialog {

    
    /**
     * Creates new form resultadoRepresentante
     */
    public informacionRepresentante(java.awt.Frame parent, boolean modal,representante rep) {
        super(parent, modal);
        initComponents();
        for (java.awt.Component cp: this.getComponents()){
        cp.setEnabled(false);
        }
        
        text_nombres_padre.setText(rep.getNombres() != null ? rep.getNombres() : "");
        text_apellidos_padre.setText(rep.getApellidos() != null ? rep.getApellidos() : "");
        text_ci_padre.setText(String.valueOf(rep.getCi()));
        text_edad_padre.setText(String.valueOf(rep.getEdad()));
        text_fec_nac_padre.setText(rep.getFechaNac() != null ? (rep.getFechaNac().toString()) : "");
        text_lugar_nac_padre.setText(rep.getLugarNac() != null ? rep.getLugarNac() : "");
        text_direc_hab_padre.setText(rep.getDireccionHab() != null ? rep.getDireccionHab() : "");
        text_direc_trabj_padre.setText(rep.getDireccionTrabj() != null ? rep.getDireccionTrabj() : "");
        text_ocupacion_padre.setText(rep.getOcupacion() != null ? rep.getOcupacion() : "");
        text_tlf_padre.setText(rep.getTlf1() != null ? rep.getTlf1() : "");
        text_tlf_padre1.setText(rep.getTlf2() != null ? rep.getTlf2() : "");
        text_correo_padre.setText(rep.getCorreo() != null ? rep.getCorreo() : "");
        
                // Nacionalidad
        check_ven_padre.setSelected(false);
        check_ext_padre.setSelected(false); // Deselect all in the group
        if ("Venezolana".equalsIgnoreCase(rep.getNacionalidad())) {
            check_ven_padre.setSelected(true);
        } else {
            check_ext_padre.setSelected(true);
        }

        // Estado Civil
        check_s_padre.setSelected(false);
        check_c_padre.setSelected(false);
        check_d_padre.setSelected(false);
        check_v_padre.setSelected(false);
        if ("S".equalsIgnoreCase(rep.getEstadoCivil())) {
            check_s_padre.setSelected(true);
        } else if ("C".equalsIgnoreCase(rep.getEstadoCivil())) {
            check_c_padre.setSelected(true);
        } else if ("D".equalsIgnoreCase(rep.getEstadoCivil())) {
            check_d_padre.setSelected(true);
        } else if ("V".equalsIgnoreCase(rep.getEstadoCivil())) {
            check_v_padre.setSelected(true);
        }

        // Grado de Estudios
        check_no_bachiller_padre.setSelected(false);
        chec_bachiller_padre.setSelected(false);
        check_tsu_padre.setSelected(false);
        check_superior_padre.setSelected(false);
        if ("No Bachiller".equalsIgnoreCase(rep.getGradoEstudios())) {
            check_no_bachiller_padre.setSelected(true);
        } else if ("Bachiller".equalsIgnoreCase(rep.getGradoEstudios())) {
            chec_bachiller_padre.setSelected(true);
        } else if ("TSU".equalsIgnoreCase(rep.getGradoEstudios())) {
            check_tsu_padre.setSelected(true);
        } else if ("Superior".equalsIgnoreCase(rep.getGradoEstudios())) {
            check_superior_padre.setSelected(true);
        }

        // --- Set Image on JLabel using setIcon() ---
        if (rep.getImg() != null && rep.getImg().length > 0) {
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(rep.getImg());
                BufferedImage bImage;
                bImage = ImageIO.read(bis);
                if (bImage != null) {
                    // Scale image to fit the label's current size
                    Image scaledImage = bImage.getScaledInstance(
                            240,
                            240,
                            Image.SCALE_SMOOTH);
                    jLabel6.setIcon(new javax.swing.ImageIcon(scaledImage));
                    jLabel6.setText(""); // Clear any "No Image" text
                } else {
                    jLabel6.setIcon(null); // Clear previous icon
                    jLabel6.setText("Invalid Image rep");
                }
            } catch (IOException ex) {
                System.err.println("Error loading image: " + ex.getMessage());
                jLabel6.setIcon(null);
                jLabel6.setText("Error Loading Image");
            }
        } else {
            jLabel6.setIcon(null); // Clear any existing image
            jLabel6.setText("No Image"); // Display a message when no image
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
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
        text_fec_nac_padre = new javax.swing.JTextField();
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
        label_lugar_nac_padre1 = new javax.swing.JLabel();
        text_lugar_nac_padre1 = new javax.swing.JTextField();
        label_tlf_padre1 = new javax.swing.JLabel();
        text_tlf_padre1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(27, 120, 101));
        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 32));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(250, 247, 239));
        jLabel4.setText("REPRESENTANTE");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(305, 305, 305)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4))
        );

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/set1/user-line_240.png"))); // NOI18N

        dat_padre.setBackground(new java.awt.Color(222, 255, 222));
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

        label_tlf_padre.setText("TELF 1");

        text_tlf_padre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_tlf_padreActionPerformed(evt);
            }
        });

        label_lugar_nac_padre1.setText("Fecha de Nacimiento");

        text_lugar_nac_padre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_lugar_nac_padre1ActionPerformed(evt);
            }
        });

        label_tlf_padre1.setText("TELF 2");
        label_tlf_padre1.setToolTipText("");

        text_tlf_padre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_tlf_padre1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dat_padreLayout = new javax.swing.GroupLayout(dat_padre);
        dat_padre.setLayout(dat_padreLayout);
        dat_padreLayout.setHorizontalGroup(
            dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dat_padreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dat_padreLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(dat_padreLayout.createSequentialGroup()
                                .addComponent(label_correo_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_correo_padre))
                            .addGroup(dat_padreLayout.createSequentialGroup()
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
                                .addComponent(label_nacionalidad_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_ven_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(check_ext_padre)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_lugar_nac_padre)
                            .addComponent(label_lugar_nac_padre1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(text_lugar_nac_padre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_lugar_nac_padre1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(265, 265, 265))
                    .addGroup(dat_padreLayout.createSequentialGroup()
                        .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dat_padreLayout.createSequentialGroup()
                                .addComponent(label_ocupacion_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_ocupacion_padre, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label_direc_trabaj_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_direc_trabj_padre, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(label_apellido_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_apellidos_padre, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label_nombes_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_nombres_padre, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label_ci_padre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_ci_padre, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dat_padreLayout.createSequentialGroup()
                                    .addComponent(label_direc_hab_padre)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(text_direc_hab_padre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label_tlf_padre1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(text_tlf_padre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dat_padreLayout.createSequentialGroup()
                                    .addComponent(label_fec_nac_padre)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(text_fec_nac_padre, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label_edad_padre)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(text_edad_padre, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(label_tlf_padre)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(text_tlf_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        dat_padreLayout.setVerticalGroup(
            dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dat_padreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_apellidos_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_apellido_padre)
                    .addComponent(text_nombres_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_nombes_padre)
                    .addComponent(text_ci_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_ci_padre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_tlf_padre)
                        .addComponent(text_tlf_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(text_edad_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label_edad_padre)
                        .addComponent(text_fec_nac_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label_fec_nac_padre)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_tlf_padre1)
                        .addComponent(text_tlf_padre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_direc_hab_padre)
                        .addComponent(text_direc_hab_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dat_padreLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_correo_padre)
                            .addComponent(text_correo_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_nacionalidad_padre)
                            .addComponent(check_ven_padre)
                            .addComponent(check_ext_padre))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dat_padreLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(dat_padreLayout.createSequentialGroup()
                                .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(text_lugar_nac_padre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label_lugar_nac_padre1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label_lugar_nac_padre)
                                    .addComponent(text_lugar_nac_padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(dat_padreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(label_estadociv_padre)
                                .addComponent(check_s_padre)
                                .addComponent(check_c_padre)
                                .addComponent(check_d_padre)
                                .addComponent(check_v_padre)))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dat_padre, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dat_padre, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void text_lugar_nac_padre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_lugar_nac_padre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_lugar_nac_padre1ActionPerformed

    private void text_tlf_padre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_tlf_padre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_tlf_padre1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public void main(String args[]) {
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
            java.util.logging.Logger.getLogger(informacionRepresentante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(informacionRepresentante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(informacionRepresentante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(informacionRepresentante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                informacionRepresentante dialog = new informacionRepresentante(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chec_bachiller_padre;
    private javax.swing.JCheckBox check_c_padre;
    private javax.swing.JCheckBox check_d_padre;
    private javax.swing.JCheckBox check_ext_padre;
    private javax.swing.JCheckBox check_no_bachiller_padre;
    private javax.swing.JCheckBox check_s_padre;
    private javax.swing.JCheckBox check_superior_padre;
    private javax.swing.JCheckBox check_tsu_padre;
    private javax.swing.JCheckBox check_v_padre;
    private javax.swing.JCheckBox check_ven_padre;
    private javax.swing.JPanel dat_padre;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel label_apellido_padre;
    private javax.swing.JLabel label_ci_padre;
    private javax.swing.JLabel label_correo_padre;
    private javax.swing.JLabel label_direc_hab_padre;
    private javax.swing.JLabel label_direc_trabaj_padre;
    private javax.swing.JLabel label_edad_padre;
    private javax.swing.JLabel label_estadociv_padre;
    private javax.swing.JLabel label_fec_nac_padre;
    private javax.swing.JLabel label_grado_padre;
    private javax.swing.JLabel label_lugar_nac_padre;
    private javax.swing.JLabel label_lugar_nac_padre1;
    private javax.swing.JLabel label_nacionalidad_padre;
    private javax.swing.JLabel label_nombes_padre;
    private javax.swing.JLabel label_ocupacion_padre;
    private javax.swing.JLabel label_tlf_padre;
    private javax.swing.JLabel label_tlf_padre1;
    private javax.swing.JTextField text_apellidos_padre;
    private javax.swing.JTextField text_ci_padre;
    private javax.swing.JTextField text_correo_padre;
    private javax.swing.JTextField text_direc_hab_padre;
    private javax.swing.JTextField text_direc_trabj_padre;
    private javax.swing.JTextField text_edad_padre;
    private javax.swing.JTextField text_fec_nac_padre;
    private javax.swing.JTextField text_lugar_nac_padre;
    private javax.swing.JTextField text_lugar_nac_padre1;
    private javax.swing.JTextField text_nombres_padre;
    private javax.swing.JTextField text_ocupacion_padre;
    private javax.swing.JTextField text_tlf_padre;
    private javax.swing.JTextField text_tlf_padre1;
    // End of variables declaration//GEN-END:variables
}
