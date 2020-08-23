/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Daniel
 */
public class AddProductController implements Initializable {
    
    ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    ObservableList<Part> partsSearched = FXCollections.observableArrayList();
    Parent scene;
    Stage stage;
    
    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> partIDCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInvCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private Button partsSrchBtn;

    @FXML
    private TextField partsSrchBar;

    @FXML
    private TextField productIDTxt;

    @FXML
    private TextField productNameTxt;

    @FXML
    private TextField productInvTxt;

    @FXML
    private TextField productPriceTxt;

    @FXML
    private TextField productMaxTxt;

    @FXML
    private TextField productMinTxt;

    @FXML
    private TableView<Part> assPartsTable;

    @FXML
    private TableColumn<Part, Integer> assPartIDCol;

    @FXML
    private TableColumn<Part, String> assPartNameCol;

    @FXML
    private TableColumn<Part, Integer> assPartInvCol;

    @FXML
    private TableColumn<Part, Double> assPartPriceCol;

    @FXML
    private Button addPartBtn;

    @FXML
    private Button delPartBtn;

    @FXML
    private Button productCancelBtn;

    @FXML
    private Button productSaveBtn;
    
    @FXML
    private Button clrPartSrchBtn;

    @FXML
    void onActionAddPart(ActionEvent event) {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null){
            associatedParts.add(selectedPart);
        }
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
            associatedParts.remove(selectedPart);
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
    void onActionClrPartSrch(ActionEvent event) {
        partsSrchBar.setText("");
        partsTable.setItems(Inventory.getAllParts());
    }

    @FXML
    void onActionReturnHome(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Are you sure?");
        alert.setHeaderText(null);
        alert.setContentText("Return to the main screen?\n"
                + "All unsaved data will be lost");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {
        int ID = 100;
        for (int i : Inventory.getSortedProductIDs()){
            if (ID == i){
                ID+=100;
            }
        }
        if (!associatedParts.isEmpty()){
            try {
                Product newProduct = new Product(ID, productNameTxt.getText(), Double.parseDouble(productPriceTxt.getText()),
                        Integer.parseInt(productInvTxt.getText()), Integer.parseInt(productMinTxt.getText()), 
                        Integer.parseInt(productMaxTxt.getText()));
                for (Part part : associatedParts){
                    newProduct.addAssociatedPart(part);
                }
                Inventory.addProduct(newProduct);
        
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Oops!");
                alert.setHeaderText(null);
                alert.setContentText("Something went wrong, please check the information"
                        + " entered and try again");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Oops!");
                alert.setHeaderText(null);
                alert.setContentText("Please make sure product has at least one"
                    + " associated part");
                alert.showAndWait();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        partsTable.setItems(Inventory.getAllParts());
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
        
        assPartsTable.setItems(associatedParts);
        assPartIDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        assPartNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        assPartInvCol.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        assPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
    }
    
}
