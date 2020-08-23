/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Daniel
 */
public abstract class Part {
    
    protected int partID;
    protected String partName;
    protected double partPrice;
    protected int partStock;
    protected int min;
    protected int max;
    
    
    
    public Part(int id, String name, double price, int stock, int min, int max){
        this.partID = id;
        this.partName = name;
        this.partPrice = price;
        this.partStock = stock;
        this.min = min;
        this.max = max;
    }
    
    public void setID(int id){
        this.partID = id;
    }
    
    
    public void setName(String name){
        this.partName = name;
    }
    
    public void setPrice(double price){
        this.partPrice = price;
    }
    
    public void setStock(int stock){
        this.partStock = stock;
    }
    
    public void setMin(int min){
        this.min = min;
    }
    
    public void setMax(int max){
        this.max = max;
    }
    
    public int getID(){
        return this.partID;
    }
    
    
    public String getName(){
        return this.partName;
    }
    
    public double getPrice(){
        return this.partPrice;
    }
    
    public int getStock(){
        return this.partStock;
    }
    
    public int getMin(){
        return this.min;
    }
    
    public int getMax(){
        return this.max;
    }
    
}
