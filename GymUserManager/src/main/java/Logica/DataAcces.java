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
        String sql = "SELECT i.id, e.Descripcio, Timestamp_inici, IdUsuari, Videofile " +
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
        String sql = "SELECT i.id, e.Descripcio, Timestamp_inici, IdUsuari, Videofile " +
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
    
    public void addReview(Review review){
        Connection connection = getConnection();
        
        String sql = "insert into review (IdIntent, IdReviewer, Valoracio, Comentari) values(?, ?, ?, ?)";
        
        try (PreparedStatement insertStatement = connection.prepareStatement(sql);){
            
            insertStatement.setInt(1, review.getIdIntent());
            insertStatement.setInt(2, review.getIdReviewer());
            insertStatement.setInt(3, review.getValoracion());
            insertStatement.setString(4, review.getComentari());
            
            insertStatement.executeUpdate();
            connection.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Intent getIntento(int idUsuario, int idExercici) {
        Connection connection = getConnection();
        String sql = "SELECT i.id, e.Descripcio, Timestamp_inici, IdUsuari, Videofile " +
                     "FROM Intents i " +
                     "JOIN Exercicis e ON i.idExercici = e.Id " +
                     "where IdUsuari = ? " +
                     "and idExercici = ?";
        
        try (PreparedStatement selectStatement = connection.prepareStatement(sql);){
            selectStatement.setInt(1, idUsuario);
            selectStatement.setInt(2, idExercici);
            
            ResultSet rs =  selectStatement.executeQuery();
            rs.next();
            
            Timestamp timestampInici = rs.getTimestamp("Timestamp_Inici");
            LocalDateTime inici = (timestampInici != null) ? timestampInici.toLocalDateTime() : null;
            
            Intent intento = new Intent();
            intento.setId(rs.getInt("id"));
            intento.setIdUsuari(rs.getInt("IdUsuari"));
            intento.setExercici(rs.getString("Descripcio"));
            intento.setInici(inici);
            intento.setVideoFile(rs.getString("Videofile"));
            
            
            return intento;
            
        } catch (SQLException ex) {
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getIdExercici(String nombreEjercicio) {
        Connection connection = getConnection();
        String sql = "select Id from exercicis where descripcio = ?";
        
        try (PreparedStatement selectStatement = connection.prepareStatement(sql);){
            selectStatement.setString(1, nombreEjercicio);
            
            ResultSet rs =  selectStatement.executeQuery();
            rs.next();
            
            return rs.getInt("Id");            
        } catch (SQLException ex) {
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void deleteReview(int idIntento) {
        Connection connection = getConnection();
        String sql = "delete from review where id = ?";

        try (PreparedStatement deleteStatement = connection.prepareStatement(sql);){
            deleteStatement.setInt(1, idIntento);

            deleteStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Review getReview(int idIntento){
        Review review = new Review();
        Connection connection = getConnection();

        String sql = "select * from review where idIntent = ?";

        try (PreparedStatement selectStatement = connection.prepareStatement(sql);){
            selectStatement.setInt(1, idIntento);
            
            ResultSet resultSet =  selectStatement.executeQuery();

            review.setId(resultSet.getInt("Id"));
            review.setIdIntent(resultSet.getInt("IdIntent"));
            review.setIdReviewer(resultSet.getInt("IdReviewer"));
            review.setValoracion(resultSet.getInt("Valoracio"));
            review.setComentari(resultSet.getString("Comentari"));
                
            return review;
            
        } catch (SQLException ex) {
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
        }

        return review;   
    }

    public void updateReview(int nota, String comentario , int idReview, int idReviewer) {
        Connection connection = getConnection();
        
        String sql = "Update review set valoracio = ?, comentari = ?, idReviewer = ? where id = ?";
        
        try (PreparedStatement updateStatement = connection.prepareStatement(sql);){
            updateStatement.setInt(1, nota);
            updateStatement.setString(2, comentario);
            updateStatement.setInt(3, idReviewer);
            updateStatement.setInt(4, idReview);
            
            updateStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
