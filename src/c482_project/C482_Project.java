/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c482_project;
import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import Model.Product;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Daniel
 */
public class C482_Project extends Application {
    
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        addTestData(inventory);
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    static void addTestData(Inventory inventory){
        //InHouse Parts
        Part a1 = new InHouse(1, "Part A1", 2.99, 10, 5, 100, 101);
        Part a2 = new InHouse(3, "Part A2", 4.99, 11, 5, 100, 103);
        Part b = new InHouse(2, "Part B", 3.99, 9, 5, 100, 102);
        inventory.addPart(a1);
        inventory.addPart(a2);
        inventory.addPart(b);
        inventory.addPart(new InHouse(4, "Part A3", 5.99, 15, 5, 100, 104));
        inventory.addPart(new InHouse(5, "Part A4", 6.99, 5, 5, 100, 105));
        
        //OutSourced Parts
        Part o1 = new Outsourced(6, "Part O1", 2.99, 10, 5, 100, "ACME Co.");
        Part p = new Outsourced(7, "Part P", 3.99, 9, 5, 100, "ACME Co.");
        Part q = new Outsourced(8, "Part Q", 2.99, 10, 5, 100, "FLORIDA Co.");
        inventory.addPart(o1);
        inventory.addPart(p);
        inventory.addPart(q);
        inventory.addPart(new Outsourced(9, "Part R", 2.99, 10, 5, 100, "FLORIDA Co."));
        inventory.addPart(new Outsourced(10, "Part O2", 2.99, 10, 5, 100, "NY Co."));
        
        //Products
        Product product1 = new Product(100, "Product A1", 9.99, 20, 5, 100);
        product1.addAssociatedPart(a1);
        product1.addAssociatedPart(o1);
        inventory.addProduct(product1);
        Product product2 = new Product(200, "Product A2", 9.99, 29, 5, 100);
        product2.addAssociatedPart(a2);
        product2.addAssociatedPart(p);
        inventory.addProduct(product2);
        Product product3 = new Product(300, "Product B1", 9.99, 30, 5, 100);
        product3.addAssociatedPart(b);
        product3.addAssociatedPart(q);
        inventory.addProduct(product3);
    }
    
}
