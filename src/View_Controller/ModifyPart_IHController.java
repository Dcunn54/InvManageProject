/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Part;
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
public class ModifyPart_IHController implements Initializable {
    
    Parent scene;
    Stage stage;
    
    @FXML
    private RadioButton outsourcedRad;

    @FXML
    private RadioButton inHouseRad;

    @FXML
    private TextField modInHouseIDTxt;

    @FXML
    private TextField modMachIDTxt;

    @FXML
    private TextField modInHouseMaxTxt;

    @FXML
    private TextField modInHousePriceCostTxt;

    @FXML
    private TextField modInHouseInvTxt;

    @FXML
    private TextField modInHouseNameTxt;

    @FXML
    private TextField modInHouseMinTxt;

    @FXML
    private Button saveModInHouseBtn;

    @FXML
    private Button cancelModInHouseBtn;

    @FXML
    void onActionOutsourced(ActionEvent event) {

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
    void onActionSaveInHouse(ActionEvent event) throws IOException {
        try { 
            InHouse updatedPart = new InHouse(Integer.parseInt(modInHouseIDTxt.getText()), modInHouseNameTxt.getText(), 
                    Double.parseDouble(modInHousePriceCostTxt.getText()), Integer.parseInt(modInHouseInvTxt.getText()), 
                    Integer.parseInt(modInHouseMinTxt.getText()), Integer.parseInt(modInHouseMaxTxt.getText()), 
                    Integer.parseInt(modMachIDTxt.getText()));
            Inventory.updatePart(Inventory.getIndexOfPart(Integer.parseInt(modInHouseIDTxt.getText())), updatedPart);
            
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
    
    public void sendInHouse(Part inHouse){
        modInHouseIDTxt.setText(String.valueOf(inHouse.getID()));
        modMachIDTxt.setText(String.valueOf(((InHouse)inHouse).getMachineID()));
        modInHouseMaxTxt.setText(String.valueOf(inHouse.getMax()));
        modInHouseMinTxt.setText(String.valueOf(inHouse.getMin()));
        modInHousePriceCostTxt.setText(String.valueOf(inHouse.getPrice()));
        modInHouseInvTxt.setText(String.valueOf(inHouse.getStock()));
        modInHouseNameTxt.setText(inHouse.getName());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }
    
}
