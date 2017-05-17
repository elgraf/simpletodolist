/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.graf.bussines;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author nich
 */
public class Item  implements Serializable{
    private String title;
    private Calendar date;
    public Item(){
        title="";
        date=new GregorianCalendar();
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String newTitle){
        this.title=newTitle;
        
    }
    public String getDate(){
        SimpleDateFormat format=new SimpleDateFormat("d/MM/yyyy 'at' HH:mm");
        return format.format(date.getTime()).toString();
    }
    public String getRawDate(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
        return format.format(date.getTime()).toString();
    }
   
    
   
    
    
}
