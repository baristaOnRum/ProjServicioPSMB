package forms;
import javax.swing.JOptionPane;
import subsystems.connectDB;
import subsystems.acceso;
import subsystems.utils;


public class inicio extends javax.swing.JFrame {

    acceso accesoPresente;
    menuPrincipal menuMain;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(inicio.class.getName());

    public inicio(acceso accesoSuper, menuPrincipal main) {
        accesoPresente = accesoSuper;
        menuMain = main;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headder = new javax.swing.JPanel();
        icn_logoInstitucion = new javax.swing.JLabel();
        txt_nombreInstituto = new javax.swing.JTextPane();
        pnl_inicio = new javax.swing.JPanel();
        lbl_usr = new javax.swing.JLabel();
        txt_usr = new javax.swing.JTextField();
        lbl_contras = new javax.swing.JLabel();
        pass_contrass = new javax.swing.JPasswordField();
        btn_iniciar = new javax.swing.JButton();
        tgl_verContrass = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(139, 208, 194));
        setMinimumSize(new java.awt.Dimension(300, 180));

        headder.setBackground(new java.awt.Color(27, 120, 101));
        headder.setMinimumSize(new java.awt.Dimension(300, 100));

        icn_logoInstitucion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icn_logoInstitucion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/logo_inst_128.png"))); // NOI18N

        txt_nombreInstituto.setEditable(false);
        txt_nombreInstituto.setBackground(new java.awt.Color(27, 120, 101));
        txt_nombreInstituto.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        txt_nombreInstituto.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        txt_nombreInstituto.setForeground(new java.awt.Color(255, 255, 255));
        txt_nombreInstituto.setText("    Centro de Educacion Inicial\n           \"Arnoldo Gabaldo\"");

        javax.swing.GroupLayout headderLayout = new javax.swing.GroupLayout(headder);
        headder.setLayout(headderLayout);
        headderLayout.setHorizontalGroup(
            headderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headderLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(icn_logoInstitucion)
                .addGap(38, 38, 38)
                .addComponent(txt_nombreInstituto, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        headderLayout.setVerticalGroup(
            headderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icn_logoInstitucion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(headderLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(txt_nombreInstituto)
                .addGap(44, 44, 44))
        );

        pnl_inicio.setBackground(new java.awt.Color(197, 232, 225));
        pnl_inicio.setMinimumSize(new java.awt.Dimension(300, 100));

        lbl_usr.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_usr.setText("Usuario");

        txt_usr.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_usr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usrActionPerformed(evt);
            }
        });

        lbl_contras.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_contras.setText("Contraseña");

        pass_contrass.setEchoChar('*');
        pass_contrass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pass_contrassActionPerformed(evt);
            }
        });

        btn_iniciar.setText("Iniciar sesion");
        btn_iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_iniciarActionPerformed(evt);
            }
        });

        tgl_verContrass.setText("Ver Contraseña");
        tgl_verContrass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_verContrassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_inicioLayout = new javax.swing.GroupLayout(pnl_inicio);
        pnl_inicio.setLayout(pnl_inicioLayout);
        pnl_inicioLayout.setHorizontalGroup(
            pnl_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_inicioLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnl_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_usr)
                    .addComponent(lbl_contras))
                .addGap(12, 12, 12)
                .addGroup(pnl_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_usr)
                    .addComponent(pass_contrass))
                .addGap(18, 18, 18)
                .addGroup(pnl_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_iniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tgl_verContrass, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnl_inicioLayout.setVerticalGroup(
            pnl_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_inicioLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnl_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_usr)
                        .addComponent(txt_usr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_iniciar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_contras)
                    .addComponent(tgl_verContrass)
                    .addComponent(pass_contrass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_inicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(headder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_inicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_usrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usrActionPerformed

    private void tgl_verContrassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_verContrassActionPerformed
        if (tgl_verContrass.isSelected()){
            tgl_verContrass.setText("Ocultar contraseña");
            pass_contrass.setEchoChar((char)0);
        }else{
            tgl_verContrass.setText("Ver contraseña");
            pass_contrass.setEchoChar('*');
        }
        
    }//GEN-LAST:event_tgl_verContrassActionPerformed

    private void pass_contrassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pass_contrassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pass_contrassActionPerformed

    private void btn_iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_iniciarActionPerformed
        String usuario = txt_usr.getText();
        String passF = new String();
        
        try {
        StringBuilder pass = new StringBuilder();
        for ( char a : pass_contrass.getPassword()){
            pass.append(a);
            }
        passF = pass.toString();
        } catch (NullPointerException e){
            JOptionPane.showMessageDialog(this, "Por favor ingrese una contraseña", "Error", JOptionPane.WARNING_MESSAGE);
            return;
            }
        String hash = utils.generarHash(passF);

        // Setea la información en acceso
        accesoPresente.setNombre_usuario(usuario);
        accesoPresente.setContrasenaHash(hash);
        acceso accesoTemp;
        try {
                 connectDB.fetchUsuario(accesoTemp);
                 if (accesoTemp.getContrasenaHash() == accesoPresente.getContrasenaHash()){
                    
                    }
                        } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Usuario inexistente", "Error", JOptionPane.WARNING_MESSAGE);
}
            
        } else {
            JOptionPane.showMessageDialog(this, "Usuario inexistente", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_iniciarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_iniciar;
    private javax.swing.JPanel headder;
    private javax.swing.JLabel icn_logoInstitucion;
    private javax.swing.JLabel lbl_contras;
    private javax.swing.JLabel lbl_usr;
    private javax.swing.JPasswordField pass_contrass;
    private javax.swing.JPanel pnl_inicio;
    private javax.swing.JToggleButton tgl_verContrass;
    private javax.swing.JTextPane txt_nombreInstituto;
    private javax.swing.JTextField txt_usr;
    // End of variables declaration//GEN-END:variables
}
