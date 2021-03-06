/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import Model.Outsourced;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Daniel
 */
public class AddPart_OSController  implements Initializable {
    
    Parent scene;
    Stage stage;
    
    @FXML
    private RadioButton outsourcedRad;

    @FXML
    private RadioButton inHouseRad;

    @FXML
    private TextField outsourcedIDTxt;

    @FXML
    private TextField compNameTxt;

    @FXML
    private TextField outsourcedMaxTxt;

    @FXML
    private TextField outsourcedPriceCostTxt;

    @FXML
    private TextField outsourcedInvTxt;

    @FXML
    private TextField outsourcedNameTxt;

    @FXML
    private TextField outsourcedMinTxt;

    @FXML
    private Button outsourcedSaveBtn;

    @FXML
    private Button outsourcedCancelBtn;

    @FXML
    void onActionInHouse(ActionEvent event) throws IOException {
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddPart_IH.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
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
    void onActionSaveOutsourced(ActionEvent event) throws IOException {
        int ID = 1;
        for (int i : Inventory.getSortedPartIDs()){
            if (ID == i){
                ID++;
            }
        }
        try {
            Inventory.addPart(new Outsourced(ID, outsourcedNameTxt.getText(), Double.parseDouble(outsourcedPriceCostTxt.getText()),
                    Integer.parseInt(outsourcedInvTxt.getText()), Integer.parseInt(outsourcedMinTxt.getText()), 
                    Integer.parseInt(outsourcedMaxTxt.getText()), compNameTxt.getText()));
        
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
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //TO DO
    }
    
}
