/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import DTOs.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                connection.close();
            }
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
    
}
