/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Daniel
 */
public class MainScreenController  implements Initializable {
    
    ObservableList<Part> partsSearched = FXCollections.observableArrayList();
    ObservableList<Product> productsSearched = FXCollections.observableArrayList();
    Stage stage;
    Parent scene;
    
    @FXML
    private AnchorPane background;
    
    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> partIDCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInvCol;

    @FXML
    private TableColumn<Part, Double> partPriceCostCol;

    @FXML
    private Button partsSrchbtn;

    @FXML
    private TextField partsSrchBar;

    @FXML
    private Button addPartBtn;

    @FXML
    private Button modPartBtn;

    @FXML
    private Button delPartBtn;
    
    @FXML
    private Button clrPartSrchBtn;

    @FXML
    private TableView<Product> productsTable;

    @FXML
    private TableColumn<Product, Integer> productIDCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Integer> productInvCol;

    @FXML
    private TableColumn<Product, Double> productPriceCostCol;

    @FXML
    private Button productsSrchBtn;

    @FXML
    private TextField productsSrchBar;

    @FXML
    private Button addProductBtn;

    @FXML
    private Button modProductBtn;

    @FXML
    private Button delProductBtn;
    
    @FXML
    private Button clrProductSrchBtn;

    @FXML
    private Button exitProgramBtn;
    
    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddPart_IH.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDelPart(ActionEvent event) {
        Part selectedPart;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Are you sure?");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this item?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                selectedPart = partsTable.getSelectionModel().getSelectedItem();
            } else {
                selectedPart = null;
            }
        if (selectedPart != null){
            Inventory.deletePart(selectedPart);
            partsSearched.remove(selectedPart);
        }
    }

    @FXML
    void onActionDelProduct(ActionEvent event) {
        Product selectedProduct;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Are you sure?");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this item?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                selectedProduct = productsTable.getSelectionModel().getSelectedItem();
            } else {
                selectedProduct = null;
            }
        if (selectedProduct != null){
            Inventory.deleteProduct(selectedProduct);
            productsSearched.remove(selectedProduct);
        }
    }

    @FXML
    void onActionExitProgram(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Are you sure?");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit the program?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void onActionModPart(ActionEvent event) throws IOException {
        if (partsTable.getSelectionModel().getSelectedItem() instanceof InHouse){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View_Controller/ModifyPart_IH.fxml"));
            loader.load();
        
            ModifyPart_IHController MPIHController = loader.getController();
            MPIHController.sendInHouse(partsTable.getSelectionModel().getSelectedItem());
            
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        else if (partsTable.getSelectionModel().getSelectedItem() instanceof Outsourced){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View_Controller/ModifyPart_OS.fxml"));
            loader.load();
        
            ModifyPart_OSController MPOSController = loader.getController();
            MPOSController.sendOutsourced(partsTable.getSelectionModel().getSelectedItem());
            
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void onActionModProduct(ActionEvent event) throws IOException {
        if (productsTable.getSelectionModel().getSelectedItem() != null){
            FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/View_Controller/ModifyProduct.fxml"));
                loader.load();
        
                ModifyProductController MPController = loader.getController();
                MPController.sendProduct(productsTable.getSelectionModel().getSelectedItem());
            
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();
        }
    }

    @FXML
    void onActionPartsSrch(ActionEvent event) {
        partsSearched.clear();
        if (Inventory.lookupPart(partsSrchBar.getText()) != null){
            partsSearched.addAll(Inventory.lookupPart(partsSrchBar.getText()));
        }
        try {
            partsSearched.add(Inventory.lookupPart(Integer.parseInt(partsSrchBar.getText())));
        } catch (Exception e) {}
        partsTable.setItems(partsSearched);
    }

    @FXML
    void onActionProductsSrch(ActionEvent event) {
        productsSearched.clear();
        if (Inventory.lookupProduct(productsSrchBar.getText()) != null){
            productsSearched.addAll(Inventory.lookupProduct(productsSrchBar.getText()));
        }
        try {
            productsSearched.add(Inventory.lookupProduct(Integer.parseInt(productsSrchBar.getText())));
        } catch (Exception e) {}
        productsTable.setItems(productsSearched);
    }
    
    
    @FXML
    void onActionClrPartSrch(ActionEvent event) {
        partsSrchBar.setText("");
        partsTable.setItems(Inventory.getAllParts());
    }

    @FXML
    void onActionClrProductSrch(ActionEvent event) {
        productsSrchBar.setText("");
        productsTable.setItems(Inventory.getAllProducts());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        partsTable.setItems(Inventory.getAllParts());
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        partPriceCostCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
        
        productsTable.setItems(Inventory.getAllProducts());
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        productInvCol.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        productPriceCostCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
    }
    
}
