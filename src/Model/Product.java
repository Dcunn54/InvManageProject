/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Daniel
 */
public class Product {
    
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int productID;
    private String productName;
    private double productPrice;
    private int productStock;
    private int min;
    private int max;
    
    public Product(int id, String name, double price, int stock,
            int min, int max){
        this.productID = id;
        this.productName = name;
        this.productPrice = price;
        this.productStock = stock;
        this.min = min;
        this.max = max;
    }
    
    public void setID(int id){
        this.productID = id;
    }
    
    public void setName(String name){
        this.productName = name;
    }
    
    public void setPrice(double price){
        this.productPrice = price;
    }
    
    public void setStock(int stock){
        this.productStock = stock;
    }
    
    public void setMin(int min){
        this.min = min;
    }
    
    public void setMax(int max){
        this.max = max;
    }
    
    public int getID(){
        return this.productID;
    }
    
    public String getName(){
        return this.productName;
    }
    
    public double getPrice(){
        return this.productPrice;
    }
    
    public int getStock(){
        return this.productStock;
    }
    
    public int getMin(){
        return this.min;
    }
    
    public int getMax(){
        return this.max;
    }
    
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }
    
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        if (associatedParts.contains(selectedAssociatedPart)){
            associatedParts.remove(associatedParts.indexOf(selectedAssociatedPart));
            return true;
        }
        else{
            return false;
        }
    }
    
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }
    
}
