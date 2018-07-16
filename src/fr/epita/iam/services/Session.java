package fr.epita.iam.services;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import fr.epita.iam.exceptions.EntityReadException;
import fr.epita.iam.services.identity.ConsoleLogger;
import fr.epita.iam.services.user.UserJDBC;

public class Session {
	private UserJDBC userDB = new UserJDBC();
	
	/*
	 * This function is from online tutorial to hash the password
	 */
	private String getMD5(String password) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            return null;
        }
        return generatedPassword;
	}
	
	public boolean login(String username, String password) {
		password = getMD5(password);
		if (password == null) {
			System.out.println("Pasword null");
		}
		System.out.println(password);
		try {
			return userDB.checkLogin(username, password);
		} catch (final EntityReadException e){
			ConsoleLogger.log("Failed to fetch data from database");
		}
		return false;
	}
}
