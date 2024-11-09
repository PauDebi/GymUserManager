/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import DTOs.Intent;
import DTOs.Review;
import DTOs.User;
import Frames.MainFrame;
import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Result;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

/**
 *
 * @author Pau_Clase
 */
public class Logica {
    
    public static boolean canLogin(String email, char[] password){
        DataAcces da = new DataAcces();
        User user = da.getUser(email);
        if (user.getPaswordHash() == null)
            return false;
        
        Result result = BCrypt.verifyer().verify(password, user.getPaswordHash());
        if (result.verified)
            return true;
        
        return false;
    }
    
    public static void updateTable(int idUsuari, JTable tabla){
        DataAcces da = new DataAcces();
        ArrayList<Intent> intents = da.getIntents(idUsuari);

        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);
        model.setColumnCount(5);
        model.setColumnIdentifiers(new Object[]{"Nombre", "Ejercicio", "Fecha", "Esta Revisado", "Archivo de Video"});

        User usuari = getUser(idUsuari);
        String nombreUsuario = usuari.getNom() + ":" + usuari.getId();
        
        ArrayList<Review> reviews = da.getReviews();
        
        for (Intent i : intents) { // Añadir todos los intentos a la lista
            model.addRow(new Object[]{
                nombreUsuario,
                i.getExercici(),
                i.getInici().format(DateTimeFormatter.ISO_LOCAL_DATE),
                (isReviewed( i, reviews))? "Si":"No" ,
                i.getVideoFile()});
        }
        
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getSelectionModel().setSelectionInterval(0, 0);
        
    }
    
    public static User getUser(int idUsuario){
        DataAcces da = new DataAcces();
        ArrayList<User> users = da.getUsuaris();
        
        for (User usuario : users) {
            if (usuario.getId() == idUsuario){
                return usuario;
            }
        }
        return new User();
    }
        
    public static void updateTable(JTable tabla){
        DataAcces da = new DataAcces();
        ArrayList<Intent> intents = da.getIntents();

        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);
        model.setColumnCount(4);
        model.setColumnIdentifiers(new Object[]{"Nombre", "Ejercicio", "Fecha", "Archivo de Video"});

        ArrayList<User> users = da.getUsuaris();
        ArrayList<Review> reviews = da.getReviews();
        for(Intent i : intents){
            String nombreUsuario = "No User";
            for (User usuario : users)
                if (usuario.getId() == i.getIdUsuari()){
                    nombreUsuario = usuario.getNom();
                    nombreUsuario += ":" + usuario.getId();
                    break;
                }
            
            if (!isReviewed(i, reviews))
                model.addRow(new Object[]{
                            nombreUsuario, 
                            i.getExercici(), 
                            i.getInici().format(DateTimeFormatter.ISO_LOCAL_DATE), 
                            i.getVideoFile()});
        }
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getSelectionModel().setSelectionInterval(0, 0);

    }
            
    private static boolean isReviewed(Intent intento, ArrayList<Review> reviews){
        for (Review r : reviews){
            if (r.getIdIntent() == intento.getId()){
                return true;
            }
        }
        return false;
    }
        
        
    public static void updateClientList(JList clientList){
        DataAcces da = new DataAcces();
        ArrayList<User> usuaris = da.getUsuaris();


        DefaultListModel<String> demoList = new DefaultListModel<>();
        demoList.clear();
        for (User usuari : usuaris)
            if (!usuari.isIsInstructor())
                demoList.addElement(usuari.getNom() + ":" + usuari.getId());


        clientList.setModel(demoList);
    }
    
    public static void goToLink(String url){
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (URISyntaxException|IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static HashMap<String, File> readVideos() {
        HashMap<String, File> lista = new HashMap<String, File>();
        File directorio = new File("Archivos/videos");
        
        if (directorio.exists() && directorio.isDirectory()) {
            File[] archivos = directorio.listFiles();
            
            if (archivos != null) {
                for (File archivo : archivos) {
                    // Filtra los archivos de video usando su extensión
                    if (archivo.isFile() && (archivo.getName().endsWith(".mp4"))) {
                        lista.put(archivo.getName(),archivo); // Agrega el archivo de video a la lista
                    }
                }
            }
        }
        
        return lista;
    }

    public static void addAcctionListenerTable(EmbeddedMediaPlayerComponent mediaPlayerComponente, MainFrame frame) {
        JTable tablaIntentos = frame.getTablaIntentos();
        HashMap<String, File> videos = Logica.readVideos();
        tablaIntentos.getSelectionModel().addListSelectionListener(evt -> {
                if (!evt.getValueIsAdjusting()) { // Evitar eventos duplicados
                    int selectedRow = tablaIntentos.getSelectedRow();
                    if (selectedRow != -1) {
                        manageButtonsReviews(frame);
                        Intent intento = getIntento(getIdUsuarioSeleccionado(frame), getIdExercici(frame));
                        manageReviewTable(frame, intento);
                        // Obtener el nombre del video de la última columna en la fila seleccionada
                        String videoName = (String) tablaIntentos.getValueAt(selectedRow, tablaIntentos.getColumnCount() - 1);

                        // Buscar el archivo en la lista videos
                        File videoFile = videos.get(videoName);
                        
                        if (videoFile != null && videoFile.exists()) {
                            mediaPlayerComponente.mediaPlayer().media().play(videoFile.getAbsolutePath()); // Reproduce el video
                        } else {
                            System.out.println("No se encontró el archivo de video: " + videoName);
                        }
                    }
                }
        });
    }
    
    
    public static void addAcctionListenerTableReview(EmbeddedMediaPlayerComponent mediaPlayerComponente, MainFrame frame) {
        JTable tablaReview = frame.getTablaReviews();
        tablaReview.getSelectionModel().addListSelectionListener(evt -> {
                if (!evt.getValueIsAdjusting()) { // Evitar eventos duplicados
                    int selectedRow = tablaReview.getSelectedRow();
                    if (selectedRow != -1) {
                        Intent intento = getIntento(getIdUsuarioSeleccionado(frame), getIdExercici(frame));
                        
                    }
                }
        });
    }
    
    public static void manageButtonsReviews(MainFrame frame){
        JTable tablaIntentos = frame.getTablaIntentos();
        int selectedRow = tablaIntentos.getSelectedRow();
        boolean isReviewed = false;
        if (selectedRow != -1) {
            if (tablaIntentos.getValueAt(selectedRow, 3).equals("Si")) { //Si ya esta revisado actualizamos la variable
                isReviewed = true;
            }
            frame.setReviewsButtonVisibility(isReviewed);//Se muestra el boton correspondiente dependiendo de si esta revisado o por revisar
        }
    }
    
    public static void manageReviewTable(MainFrame frame, Intent intento){
        JTable tablaIntentos = frame.getTablaIntentos();
        JTable tablaReviews = frame.getTablaReviews();
        int selectedRow = tablaIntentos.getSelectedRow();
        
         if (selectedRow != -1) {
            if (tablaIntentos.getValueAt(selectedRow, 3).equals("Si")) { //Si ya esta revisado actualizamos la variable
                updateTablaReviews(tablaReviews, intento, frame);
            }
            else{
                frame.getPanelTablaReviews().setVisible(false);
                frame.setModifyReviewButtonVisibility(false);
            }
        }
        
        
    }
    
    public static void updateTablaReviews(JTable tabla ,Intent intento, MainFrame frame){
        frame.getPanelTablaReviews().setVisible(true);
        frame.setModifyReviewButtonVisibility(true);
        DataAcces da = new DataAcces();
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);
        model.setColumnCount(4);
        model.setColumnIdentifiers(new Object[]{"Reviewer", "IdReview", "Comentario", "Nota"});
        
        ArrayList<Review> reviews = da.getReviews();
        
        for (Review r : reviews){
            if (r.getIdIntent() == intento.getId()) {
                String nombreUsuario = getUser(r.getIdReviewer()).getNom();
                model.addRow(new Object[]{
                            nombreUsuario,
                            r.getId(),
                            r.getComentari(),
                            r.getValoracion()
                });
            }
        }
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getSelectionModel().setSelectionInterval(0, 0);
        
    }
    
    
    public static void addReview(String comentario, int nota, User usuario, int idIntento){
        DataAcces da = new DataAcces();
        
        Review review = new Review();
        
        review.setComentari(comentario);
        review.setIdReviewer(usuario.getId());
        review.setValoracion(nota);
        review.setIdIntent(idIntento);
        
        da.addReview(review);
    }
    
    public static Intent getIntento(int idUsuario, int idExercici){
        DataAcces da = new DataAcces();
        return da.getIntento(idUsuario, idExercici);
    }
    
    public static int getIdUsuarioSeleccionado(MainFrame frame){
        JTable tablaIntentos = frame.getTablaIntentos();

        int selectedRow = tablaIntentos.getSelectedRow();
        if (selectedRow != -1) {
            String usuario = (String) tablaIntentos.getValueAt(selectedRow, 0);
            
            String[] linea = usuario.split(":");
            return Integer.parseInt(linea[1]);
        }
        return -1;
    }
    
    public static int getIdExercici(MainFrame frame){
        DataAcces da = new DataAcces();
        JTable tablaIntentos = frame.getTablaIntentos();
        int selectedRow = tablaIntentos.getSelectedRow();
        String nombreEjercicio = "";
        
        
        if (selectedRow != -1) 
            nombreEjercicio = (String) tablaIntentos.getValueAt(selectedRow, 1);
        
        return da.getIdExercici(nombreEjercicio);
    }

    public static void deleteReview(int idReview) {
        DataAcces da = new DataAcces();
        da.deleteReview(idReview);
    }

    public static Review getSelectedReview(MainFrame frame) {
        DataAcces da = new DataAcces();
        JTable tabla = frame.getTablaReviews();
        int selectedRow = tabla.getSelectedRow();
        int idReview = (int) tabla.getValueAt(selectedRow, 1);
        ArrayList<Review> reviews = da.getReviews();
        
        for (Review r : reviews){
            if (r.getId() == idReview){
                return r;
            }
        }
        
        return new Review();
    }

    public static void updateReview(int nota, String comentario, int idReview) {
        DataAcces da = new DataAcces();
        da.updateReview(nota, comentario, idReview);
    }

}

