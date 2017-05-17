/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.graf.bussines;

import com.graf.bussines.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author nich
 */
public class UserDB {
    
    public static boolean checkUser(String email){
        ConnectionPool pool=ConnectionPool.getInstance();
        Connection connection=pool.getConnection();
        System.out.println("check user email : " + email);
        PreparedStatement ps=null;
        ResultSet set=null;
        
        String query="SELECT Email FROM USER WHERE Email=?";
        try {
            ps=connection.prepareStatement(query);
            ps.setString(1, email);
            set=ps.executeQuery();
            if(set.next()){
                System.out.println(set.getString(1));
                System.out.println("true");
                ps.close();
                pool.freeConnection(connection);
                return true;
                
            }else{
                System.out.println("false");
                ps.close();
                pool.freeConnection(connection);
                return false;
            }
        } catch (Exception e) {
            System.out.println("SEVA NE TO");
            e.printStackTrace();
            return false;
        }
        
    }
    
    public static boolean checkUser(String email,String firstName,String lastName){
        ConnectionPool pool=ConnectionPool.getInstance();
        Connection connection=pool.getConnection();
        System.out.println("checking singup user email : " + email);
        PreparedStatement ps=null;
        ResultSet set=null;
        String query="SELECT * FROM USER "
                + "WHERE Email=? "
                + "AND FirstName=? AND "
                + "LastNaME=?";
        try{
           ps=connection.prepareStatement(query);
           ps.setString(1, email);
           ps.setString(2, firstName);
           ps.setString(3, lastName);
           set=ps.executeQuery();
           if(set.next()){
               System.out.println("Succeseful finded user");
               return true;
           }
           System.out.println("Unsucceseful finded user");
           return false;
           
        }catch(SQLException e){
            System.out.println("Exceptio detected,");
            e.printStackTrace();
            return false;
        }
    }
    
    public static int insertUser(User user){
        ConnectionPool pool=ConnectionPool.getInstance();
        Connection connection=pool.getConnection();
        
        PreparedStatement ps=null;
        String query="INSERT INTO USER(Email,FirstName,LastNaME) "
                + "VALUES(?,?,?)";
        
        try {
            ps=connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            System.out.println(user.getFirstName());
            ps.close();
            pool.freeConnection(connection);
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
        
        
    }
    
    public static User getUser(String email){
        ConnectionPool pool=ConnectionPool.getInstance();
        Connection connection=pool.getConnection();
        System.out.println("check user email : " + email);
        PreparedStatement ps=null;
        ResultSet set=null;
        
        String query="SELECT Email,FirstName,LastNaME FROM USER WHERE Email=?";
        try {
            ps=connection.prepareStatement(query);
            ps.setString(1, email);
            set=ps.executeQuery();
            if(set.next()){
               User user=new User(set.getString(1),set.getString(2),set.getString(3));
               ps.close();
               pool.freeConnection(connection);
               return user;
                
            }else{
                System.out.println("false");
                ps.close();
                pool.freeConnection(connection);
                return null;
              
            }
        } catch (Exception e) {
            System.out.println("SEVA NE TO");
            
            e.printStackTrace();
            return null;
            
        }
    }
    
    public static int getUserID(String email){
        ConnectionPool pool=ConnectionPool.getInstance();
        Connection connection=pool.getConnection();
        System.out.println("getting user id : " + email);
        PreparedStatement ps=null;
        ResultSet set=null;
        
        String query="SELECT UserID FROM USER WHERE Email=?";
        try{
        ps=connection.prepareStatement(query);
        ps.setString(1, email);
        set=ps.executeQuery();
        set.next();
        int id=set.getInt(1);
        ps.close();
        pool.freeConnection(connection);
        return id;
        }catch(SQLException e){
            System.out.println("select user error");
        }
        return -1;
    }
    public static void inserTodo(Item item,int userId){
        ConnectionPool pool=ConnectionPool.getInstance();
        Connection connection=pool.getConnection();
        System.out.println("Inserting todo item" + item.getTitle());
        PreparedStatement ps=null;
        ResultSet set=null;
        String query="INSERT TODO (Text,Date,UserID)"
                + "VALUES(?,?,?)";
        try{
            ps=connection.prepareStatement(query);
            ps.setString(1, item.getTitle());
            ps.setString(2, item.getRawDate());
            ps.setString(3,String.valueOf(userId));
            int i=ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
        }catch(SQLException e){
            System.out.println("INSERT TODO EXCETPION");
            e.printStackTrace();
        }
    }
    public static void deleteTodo(Item item,int userId){
        ConnectionPool pool=ConnectionPool.getInstance();
        Connection connection=pool.getConnection();
        System.out.println("Inserting todo item" + item.getTitle());
        PreparedStatement ps=null;
        ResultSet set=null;
        String query="DELETE FROM TODO WHERE Date=?";
        
        try{
            ps=connection.prepareCall(query);
            ps.setString(1,item.getRawDate());
            ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static ToDo selectTodo(ToDo toDo,int userID){
        ConnectionPool pool=ConnectionPool.getInstance();
        Connection connection=pool.getConnection();
       
        PreparedStatement ps=null;
        ResultSet set=null;
        String query="SELECT Text,Date FROM TODO WHERE UserID=?";
        
        try{
            ps=connection.prepareStatement(query);
            ps.setString(1,String.valueOf(userID));
            set=ps.executeQuery();
            System.out.println("adding");
            while(set.next()){
                toDo.addItem(set.getString(1));
            }
            ps.close();
            pool.freeConnection(connection);
            return toDo;
        }catch(SQLException e){
            System.out.println("nothing to add");
            e.printStackTrace();
            return null;
        }
    }
}
