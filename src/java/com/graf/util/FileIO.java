/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.graf.util;
import com.graf.bussines.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author nich
 */
public class FileIO {
    public static void addUser(User user,String path){
        try{
        File file=new File(path);
        PrintWriter writer=new PrintWriter(new FileWriter(file, true));
        writer.println(user.getEmail() + "|" 
                        + user.getFirstName() + "|" 
                        + user.getLastName());
        writer.flush();
        writer.close();
        }catch(Exception e){
            e.printStackTrace();
        
         }
}
    public static User getUser(String email,String path){
        try{
        Scanner scanner=new Scanner(new File(path));
        User user=new User();
        String line=scanner.nextLine();
        while(scanner.hasNextLine()){
            StringTokenizer token=new StringTokenizer(line,"|");
            //if(token.countTokens() < 3 ){
              //  user.setEmail("");
                //user.setFirstName("");
                //user.setLastName("");
                //return user;
            //}
            if(token.nextToken().equalsIgnoreCase(email)){
            user.setEmail(email);
            user.setFirstName(token.nextToken());
            user.setLastName(token.nextToken());
            return user;
            }
            line=scanner.nextLine();
            
            
        }
       
        
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}