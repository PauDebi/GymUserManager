/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frames;

import DTOs.User;
import Logica.Logica;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

/**
 *
 * @author Pau_Clase
 */
public class MainFrame extends javax.swing.JFrame {
    private EmbeddedMediaPlayerComponent mediaPlayerComponente = new EmbeddedMediaPlayerComponent();
    private Boolean isLoged = false;
    private StringBuilder changeColor = new StringBuilder();
    private User usuarioActivo = null;
    

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        Logica.addAcctionListenerTable(mediaPlayerComponente, this);
        setLogedState(false, null); 
        prepareVideoPlayer();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainLogo = new javax.swing.JLabel();
        urlLabel = new javax.swing.JLabel();
        loginLogo = new javax.swing.JLabel();
        videoPlayerPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaIntentos = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        userList = new javax.swing.JList<>();
        restartVideoButton = new javax.swing.JButton();
        playPauseButton = new javax.swing.JButton();
        addReviewButton = new javax.swing.JButton();
        modifyReviewButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GymUserManager");
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(null);

        mainLogo.setIcon(new javax.swing.ImageIcon("./Archivos/GymLogo2.png"));
        getContentPane().add(mainLogo);
        mainLogo.setBounds(420, 160, 370, 300);

        urlLabel.setText("User Manager");
        urlLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        urlLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                urlLabelMouseClicked(evt);
            }
        });
        getContentPane().add(urlLabel);
        urlLabel.setBounds(1090, 550, 80, 20);

        ImageIcon icono = new javax.swing.ImageIcon("./Archivos/Login.png");

        Image escalada = icono.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        loginLogo.setIcon(new ImageIcon(escalada));
        loginLogo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginLogoMouseClicked(evt);
            }
        });
        getContentPane().add(loginLogo);
        loginLogo.setBounds(1100, 0, 90, 80);

        videoPlayerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("VideoPlayer"));

        javax.swing.GroupLayout videoPlayerPanelLayout = new javax.swing.GroupLayout(videoPlayerPanel);
        videoPlayerPanel.setLayout(videoPlayerPanelLayout);
        videoPlayerPanelLayout.setHorizontalGroup(
            videoPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
        videoPlayerPanelLayout.setVerticalGroup(
            videoPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 287, Short.MAX_VALUE)
        );

        getContentPane().add(videoPlayerPanel);
        videoPlayerPanel.setBounds(50, 240, 500, 310);

        tablaIntentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Usuario", "Exercici", "Fecha", "VideoFile", "Temp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaIntentos);
        if (tablaIntentos.getColumnModel().getColumnCount() > 0) {
            tablaIntentos.getColumnModel().getColumn(0).setResizable(false);
            tablaIntentos.getColumnModel().getColumn(1).setResizable(false);
            tablaIntentos.getColumnModel().getColumn(2).setResizable(false);
            tablaIntentos.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(590, 80, 490, 470);

        userList.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuarios"));
        userList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userListKeyPressed(evt);
            }
        });
        userList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                userListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(userList);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(40, 20, 150, 200);

        restartVideoButton.setText("↺");
        restartVideoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartVideoButtonActionPerformed(evt);
            }
        });
        getContentPane().add(restartVideoButton);
        restartVideoButton.setBounds(50, 560, 30, 23);

        playPauseButton.setText("⏯");
        playPauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playPauseButtonActionPerformed(evt);
            }
        });
        getContentPane().add(playPauseButton);
        playPauseButton.setBounds(520, 560, 30, 23);

        addReviewButton.setText("Add Review");
        addReviewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addReviewButtonActionPerformed(evt);
            }
        });
        getContentPane().add(addReviewButton);
        addReviewButton.setBounds(860, 560, 93, 23);

        modifyReviewButton.setText("ModifyReview");
        modifyReviewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyReviewButtonActionPerformed(evt);
            }
        });
        getContentPane().add(modifyReviewButton);
        modifyReviewButton.setBounds(970, 560, 105, 23);

        jMenu1.setText("File");

        jMenuItem2.setText("About");
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        setBounds(0, 0, 1206, 642);
    }// </editor-fold>//GEN-END:initComponents

    
    private void prepareVideoPlayer(){
        videoPlayerPanel.setLayout(new BorderLayout());
        videoPlayerPanel.add(mediaPlayerComponente, BorderLayout.CENTER); // Agrega mediaPlayerComponente al panel
        videoPlayerPanel.revalidate(); // Refresca el panel para que muestre el reproductor
        videoPlayerPanel.repaint();
    }
    
    private void urlLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_urlLabelMouseClicked
        Logica.goToLink("https://github.com/PauDebi/GymUserManager");
    }//GEN-LAST:event_urlLabelMouseClicked

    private void loginLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginLogoMouseClicked
        if (isLoged)   
            setLogedState(false, null);
        else
            new LogingDialog(this, true);
    }//GEN-LAST:event_loginLogoMouseClicked

    private void userListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_userListValueChanged
        // Cuando seleccionas un usuario de la lista de usuarios, se muestran todos sus intentos en la tabla de intentos
        if (!evt.getValueIsAdjusting()) { // Para evitar eventos duplicados
            String selectedClient = userList.getSelectedValue();
            if (selectedClient != null) {
                String[] parts = selectedClient.split(":");
                String clientId = parts[1];

                Logica.updateTable(Integer.parseInt(clientId), tablaIntentos);
            }
        }
    }//GEN-LAST:event_userListValueChanged

    //Desceleccoionar la lista de usuarios al pulsar esc
    private void userListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userListKeyPressed
        if (evt.getKeyCode() == 27){
            userList.clearSelection();
            Logica.updateTable(tablaIntentos);
            this.setFocusable(true);
        }
    }//GEN-LAST:event_userListKeyPressed

    //Cabmiar look and feel si se escribe white || black
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // Acumula caracteres si es letra o espacio
        if (Character.isLetter(evt.getKeyChar()) || Character.isWhitespace(evt.getKeyChar())) {
            changeColor.append(evt.getKeyChar());

            // Verifica si la palabra acumulada coincide con una palabra clave
            String palabra = changeColor.toString();
            if (palabra.endsWith("white")) {
                cambiarColorWhite(); // Método que cambia el color o realiza alguna acción
                changeColor.setLength(0); // Limpia el acumulador si es necesario
            }
            if (palabra.endsWith("black")) {
                cambiarColorBlack(); // Método que cambia el color o realiza alguna acción
                changeColor.setLength(0); // Limpia el acumulador si es necesario
            }
        }
    }//GEN-LAST:event_formKeyPressed

    private void playPauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playPauseButtonActionPerformed
        if (mediaPlayerComponente.mediaPlayer().status().isPlaying()) {
            mediaPlayerComponente.mediaPlayer().controls().pause();
        } else {
            mediaPlayerComponente.mediaPlayer().controls().play();
        }
    }//GEN-LAST:event_playPauseButtonActionPerformed

    private void restartVideoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartVideoButtonActionPerformed
        mediaPlayerComponente.mediaPlayer().controls().stop(); // Detener el video si está en reproducción
        mediaPlayerComponente.mediaPlayer().controls().setTime(0); // Reiniciar al inicio (0 ms)
        mediaPlayerComponente.mediaPlayer().controls().play(); // Reproducir desde el inicio
    }//GEN-LAST:event_restartVideoButtonActionPerformed

    private void modifyReviewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyReviewButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modifyReviewButtonActionPerformed

    private void addReviewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addReviewButtonActionPerformed
        
        //new AddReview(usuarioActivo);
    }//GEN-LAST:event_addReviewButtonActionPerformed
    
    public void cambiarColorWhite(){
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void cambiarColorBlack(){
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void setLogedState(boolean loged, User usuario) {
        isLoged = loged;
        usuarioActivo = usuario;

        updateVisibility(loged);

        // Solo actualizamos la tabla y la lista de clientes si estamos logueados
        if (loged) {
            Logica.updateTable(tablaIntentos);
            Logica.updateClientList(userList);
        }
    }

    private void updateVisibility(boolean loged) {
        mainLogo.setVisible(!loged);
        urlLabel.setVisible(!loged);
        jScrollPane1.setVisible(loged);
        videoPlayerPanel.setVisible(loged);
        jScrollPane3.setVisible(loged);
        playPauseButton.setVisible(loged);
        restartVideoButton.setVisible(loged);
        modifyReviewButton.setVisible(loged);
        addReviewButton.setVisible(loged);
    }

    public JTable getTablaIntentos() {
        return tablaIntentos;
    }
    
    public void setReviewsButtonVisibility(boolean isReviewed){
        addReviewButton.setVisible(!isReviewed);
        modifyReviewButton.setVisible(isReviewed);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addReviewButton;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel loginLogo;
    private javax.swing.JLabel mainLogo;
    private javax.swing.JButton modifyReviewButton;
    private javax.swing.JButton playPauseButton;
    private javax.swing.JButton restartVideoButton;
    private javax.swing.JTable tablaIntentos;
    private javax.swing.JLabel urlLabel;
    private javax.swing.JList<String> userList;
    private javax.swing.JPanel videoPlayerPanel;
    // End of variables declaration//GEN-END:variables
}

