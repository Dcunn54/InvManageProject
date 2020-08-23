/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Daniel
 */
public class Inventory {
    
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ArrayList<Integer> allPartIDs = new ArrayList<Integer>();
    private static ArrayList<Integer> allProductIDs = new ArrayList<Integer>();
    
    public static void addPart(Part newPart){
        if (newPart != null){
            allParts.add(newPart);
            allPartIDs.add(newPart.getID());
        }
    }
    
    public static void addProduct(Product newProduct){
        if (newProduct != null){
            allProducts.add(newProduct);
            allProductIDs.add(newProduct.getID());
        }
    }
    
    public static Part lookupPart(int partID){
        if (!allParts.isEmpty()){
            for (Part part : allParts){
                if (part.getID() == partID){
                    return part;
                }
            }
        }
        return null;
    }
    
    public static Product lookupProduct(int productID){
        if (!allProducts.isEmpty()){
            for (Product product : allProducts){
                if (product.getID() == productID){
                    return product;
                }
            }
        }
        return null;
    }
    
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> partsSearched = FXCollections.observableArrayList();
        if (!allParts.isEmpty()){
            for (Part part : allParts){
                if (part.getName().toLowerCase().contains(partName.toLowerCase())){
                    partsSearched.add(part);
                }
            }
            if (!partsSearched.isEmpty()){
            return partsSearched;
            }
        }
        return null;
    }
    
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> productsSearched = FXCollections.observableArrayList();
        if (!allProducts.isEmpty()){
            for (Product product : allProducts){
                if (product.getName().toLowerCase().contains(productName.toLowerCase())){
                    productsSearched.add(product);
                }
            }
            if (!productsSearched.isEmpty()){
            return productsSearched;
            }
        }
        return null;
    }
    
    public static void updatePart(int index, Part newPart){
        allParts.set(index, newPart);
    }
    
    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    }
    
    public static boolean deletePart(Part selectedPart){
        for (Part part : allParts){
            if (selectedPart == part){
                allParts.remove(part);
                allPartIDs.remove((Integer)part.getID());
                return true;
            }
        }
        return false;
    }
    
    public static boolean deleteProduct(Product selectedProduct){
        for (Product product : allProducts){
            if (selectedProduct == product){
                allProducts.remove(product);
                allProductIDs.remove((Integer)product.getID());
                return true;
            }
        }
        return false;
    }
    
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
    
    public static int getIndexOfPart(int partID){
        for (Part part : allParts){
            if (part.getID() == partID){
                return allParts.indexOf(part);
            }
        }
        System.out.println("no index found");
        return -1;
    }
    
    public static int getIndexOfProduct(int productID){
        for (Product product : allProducts){
            if (product.getID() == productID){
                return allProducts.indexOf(product);
            }
        }
        System.out.println("no index found");
        return -1;
    }
    
    public static ArrayList<Integer> getSortedPartIDs(){
        Collections.sort(allPartIDs);
        return allPartIDs;
    }
    
    public static ArrayList<Integer> getSortedProductIDs(){
        Collections.sort(allProductIDs);
        return allProductIDs;
    }
    
}
