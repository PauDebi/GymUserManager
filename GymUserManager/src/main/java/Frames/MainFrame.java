/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frames;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Desktop;
import java.awt.Image;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Pau_Clase
 */
public class MainFrame extends javax.swing.JFrame {
   // private EmbeddedMediaPlayerComponent mediaPlayer
    Boolean isLoged = false;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        setLogedState(false);
        
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GymUserManager");
        setResizable(false);
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
            .addGap(0, 600, Short.MAX_VALUE)
        );
        videoPlayerPanelLayout.setVerticalGroup(
            videoPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 307, Short.MAX_VALUE)
        );

        getContentPane().add(videoPlayerPanel);
        videoPlayerPanel.setBounds(200, 250, 610, 330);

        tablaIntentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Usuario", "Exercici", "Fecha", "VideoFile"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
        jScrollPane1.setBounds(820, 110, 350, 470);

        setBounds(0, 0, 1206, 642);
    }// </editor-fold>//GEN-END:initComponents

    private void urlLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_urlLabelMouseClicked
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/PauDebi/GymUserManager"));
        } catch (URISyntaxException|IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_urlLabelMouseClicked

    private void loginLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginLogoMouseClicked
        if (!isLoged)   
            new LogingDialog(this);
        else
            setLogedState(false);
    }//GEN-LAST:event_loginLogoMouseClicked
    
    public void setLogedState(boolean loged){
        if (loged){
            isLoged = true;
            mainLogo.setVisible(false);
            urlLabel.setVisible(false);
            jScrollPane1.setVisible(true);
            videoPlayerPanel.setVisible(true);
            
        }
        else{
            isLoged = false;
            mainLogo.setVisible(true);
            urlLabel.setVisible(true);
            jScrollPane1.setVisible(false);
            videoPlayerPanel.setVisible(false);
        }
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel loginLogo;
    private javax.swing.JLabel mainLogo;
    private javax.swing.JTable tablaIntentos;
    private javax.swing.JLabel urlLabel;
    private javax.swing.JPanel videoPlayerPanel;
    // End of variables declaration//GEN-END:variables
}
