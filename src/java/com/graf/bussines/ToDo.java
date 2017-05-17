/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.graf.bussines;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author nich
 */
public class ToDo implements Serializable{
    private ArrayList<Item> items;
    public ToDo(){
        items=new ArrayList<Item>();
    }
    public ArrayList<Item> getItems(){
        return items;
    }
    
    public void setTitles(ArrayList<Item> items){
        this.items=items;
        
    }
    public void addItem(String name){
        Item item=new Item();
        item.setTitle(name);
        items.add(item);
        
    }
    public void removeItem(String toSearch){
        for (Item item : items) {
            if(item.getRawDate().equals(toSearch)){
                items.remove(item);
                break;
            }
        }
    }
    
    public Item getItem(String toSearch){
        for(Item item:items){
            if(item.getRawDate().equals(toSearch)){
                return item;
            }
        }
        return null;
    }
    public Item getLastItem(){
        Item item=items.get(items.size()-1);
        return item;
    }
}
