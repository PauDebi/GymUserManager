/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import DTOs.User;
import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Result;

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
    
}
