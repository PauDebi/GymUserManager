/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import DTOs.Intent;
import DTOs.Review;
import DTOs.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pau_Clase
 */
public class DataAcces {
    
    public ArrayList<User> getUsuaris(){
        ArrayList<User> usuaris = new ArrayList<>();
        String sql = "select * from usuaris;";
        
        Connection connection = getConnection();
        
        
        try (PreparedStatement selectStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet =  selectStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("Id"));
                user.setNom(resultSet.getString("Nom"));
                user.setEmail(resultSet.getString("Email"));
                user.setPaswordHash(resultSet.getString("PasswordHash"));
                user.setIsInstructor(resultSet.getBoolean("IsInstructor"));
                usuaris.add(user);
            }
                connection.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuaris;
    }
        
        
        
    private Connection getConnection(){
        Connection connection = null;

        String connectionString = "jdbc:sqlserver://localhost;database=simulapdb;user=sas;password=1234;trustServerCertificate=true;";
        try {
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException ex) {
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
    return connection;
    }
    
    
    
    
    public User getUser(String email){
        Connection connection = getConnection();
        String sql = "select * from Usuaris where email = '" + email + "'";
        User user = null;
        
        try (PreparedStatement selectStatement = connection.prepareStatement(sql)) {
            
            user = new User();
            ResultSet resultSet =  selectStatement.executeQuery();
            resultSet.next();
            user.setId(resultSet.getInt("Id"));
            user.setNom(resultSet.getString("Nom"));
            user.setEmail(resultSet.getString("Email"));
            user.setPaswordHash(resultSet.getString("PasswordHash"));
            user.setIsInstructor(resultSet.getBoolean("IsInstructor"));
            return user;
        }
        catch (SQLException ex) {
            
        }
        
        return user;
        
    }
    
    public ArrayList<Intent> getIntents(int idUsuari){
        ArrayList<Intent> intents = new ArrayList<>();
        Connection connection = getConnection();
        String sql = "SELECT e.id, e.Descripcio, Timestamp_inici, IdUsuari, Videofile " +
                     "FROM Intents i " +
                     "JOIN Exercicis e ON i.idExercici = e.Id " +
                     "WHERE i.idUsuari = ?;";


        try {
            PreparedStatement selectStatement = connection.prepareStatement(sql);
            selectStatement.setInt(1, idUsuari);

            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()){

                Timestamp timestampInici = rs.getTimestamp("Timestamp_Inici");

                LocalDateTime inici = (timestampInici != null) ? timestampInici.toLocalDateTime() : null;

                Intent intento = new Intent();
                intento.setId(rs.getInt("id"));
                intento.setIdUsuari(rs.getInt("IdUsuari"));
                intento.setExercici(rs.getString("Descripcio"));
                intento.setInici(inici);
                intento.setVideoFile(rs.getString("Videofile"));;

                intents.add(intento);
            }
            rs.close();
            selectStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return intents;
    }
    
    public ArrayList<Intent> getIntents(){
        ArrayList<Intent> intents = new ArrayList<>();
        Connection connection = getConnection();
        String sql = "SELECT e.id, e.Descripcio, Timestamp_inici, IdUsuari, Videofile " +
                     "FROM Intents i " +
                     "JOIN Exercicis e ON i.idExercici = e.Id ";


        try (PreparedStatement selectStatement = connection.prepareStatement(sql);){
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()){

                Timestamp timestampInici = rs.getTimestamp("Timestamp_Inici");

                LocalDateTime inici = (timestampInici != null) ? timestampInici.toLocalDateTime() : null;

                Intent intento = new Intent();
                intento.setId(rs.getInt("id"));
                intento.setIdUsuari(rs.getInt("IdUsuari"));
                intento.setExercici(rs.getString("Descripcio"));
                intento.setInici(inici);
                intento.setVideoFile(rs.getString("Videofile"));

                intents.add(intento);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return intents;
    }
    
    public ArrayList<Review> getReviews(){
        ArrayList<Review> reviews = new ArrayList<>();
        Connection connection = getConnection();
        
        String sql = "select * from review;";
        
        try (PreparedStatement selectStatement = connection.prepareStatement(sql);){
            ResultSet resultSet =  selectStatement.executeQuery();
            while (resultSet.next()){
                Review review = new Review();

                review.setId(resultSet.getInt("Id"));
                review.setIdIntent(resultSet.getInt("IdIntent"));
                review.setIdReviewer(resultSet.getInt("IdReviewer"));
                review.setValoracion(resultSet.getInt("Valoracio"));
                review.setComentari(resultSet.getString("Comentari"));
                reviews.add(review);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reviews;   
    }
}
