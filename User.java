package  atm_pack;

import javax.xml.transform.Result;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private Connection connection;
    private Scanner scanner;

    public User(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    public void register(){
        scanner.nextLine();
        System.out.print("Full Name: ");
        String full_name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        if(user_exists(email)) {
            System.out.println("User Already Exists for this Email Address!!");
            return;
        }
        String register_query = "INSERT INTO user_info(full_name, email, password) VALUES(?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(register_query);
            preparedStatement.setString(1, full_name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Registration Successfull!");
            } else {
                System.out.println("Registration Failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String login() {
    	 System.out.println("Email: ");
         String email = scanner.nextLine();
         System.out.println("Password: ");
         String password = scanner.nextLine();
         
         String lquery="select *from user_info where email= ? and password ?";
         try {
        	 PreparedStatement pst=connection.prepareStatement(lquery);
        	 pst.setString(1, email);
        	 pst.setString(2, password);
ResultSet rs=pst.executeQuery();
if(rs.next())
{
	//System.out.println("logged in");
return email;}
else 
	return null;
         }catch(Exception e) {
         e.printStackTrace();
         return null;}
         }
    
public boolean user_exists(String email){ 
	
	String query="select *from user_info where email=?";
	try {
PreparedStatement pstm=connection.prepareStatement(query);
pstm.setString(1, email);
ResultSet rs=pstm.executeQuery();

if(rs.next())return true;
else
return false;
	}
	catch(Exception e) {
		e.printStackTrace();
	} 
	return false;}
}
