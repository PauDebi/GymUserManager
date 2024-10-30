/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import DTOs.Intent;
import DTOs.Review;
import DTOs.User;
import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Result;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

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

        String nombreUsuario = "No User";
        ArrayList<User> users = da.getUsuaris();
        for (User usuario : users) {
            if (usuario.getId() == idUsuari){
                nombreUsuario = usuario.getNom();
                break;
            }
        }
        ArrayList<Review> reviews = da.getReviews();

        
        for (Intent i : intents) {
            String isReviewd = "No";
            for (Review review : reviews) {
                if (i.getId() == review.getIdIntent()) {
                    isReviewd = "Si";
                    break; // Salimos del bucle si encontramos una coincidencia
                }
            }
            model.addRow(new Object[]{nombreUsuario, i.getExercici(), i.getInici().format(DateTimeFormatter.ISO_LOCAL_DATE), isReviewd, i.getVideoFile()});
        }


    }
        
    public static void updateTable(JTable tabla){
        DataAcces da = new DataAcces();
        ArrayList<Intent> intents = da.getIntents();

        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);
        model.setColumnCount(4);
        model.setColumnIdentifiers(new Object[]{"Nombre", "Ejercicio", "Fecha", "Archivo de Video"});

        ArrayList<User> users = da.getUsuaris();
        for(Intent i : intents){
            String nombreUsuario = "No User";
            for (User usuario : users)
                if (usuario.getId() == i.getIdUsuari()){
                    nombreUsuario = usuario.getNom();
                    break;
                }
            
            if (!isReviewed(i))
                model.addRow(new Object[]{nombreUsuario, i.getExercici(), i.getInici().format(DateTimeFormatter.ISO_LOCAL_DATE), i.getVideoFile()});
        }
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getSelectionModel().setSelectionInterval(0, 0);

    }
    
    private static boolean isReviewed(Intent intento){
        DataAcces da = new DataAcces();
        ArrayList<Review> reviews = da.getReviews();
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
    
}
