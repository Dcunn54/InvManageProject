/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InHouse;
import Model.Inventory;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 *
 * @author Daniel
 */
public class AddPart_IHController implements Initializable{
    
    Parent scene;
    Stage stage;
    
    @FXML
    private RadioButton outsourcedRad;

    @FXML
    private RadioButton inHouseRad;

    @FXML
    private TextField inHouseIDTxt;

    @FXML
    private TextField machIDTxt;

    @FXML
    private TextField inHouseMaxTxt;

    @FXML
    private TextField inHousePriceCostTxt;

    @FXML
    private TextField inHouseInvTxt;

    @FXML
    private TextField inHouseNameTxt;

    @FXML
    private TextField inHouseMinTxt;

    @FXML
    private Button inHouseSaveBtn;

    @FXML
    private Button inHouseCancelBtn;

    @FXML
    void onActionOutsourced(ActionEvent event) throws IOException {
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddPart_OS.fxml"));
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
    void onActionSaveInHouse(ActionEvent event) throws IOException {
        int ID = 1;
        for (int i : Inventory.getSortedPartIDs()){
            if (ID == i){
                ID++;
            }
        }
        try {
            Inventory.addPart(new InHouse(ID, inHouseNameTxt.getText(), Double.parseDouble(inHousePriceCostTxt.getText()),
                    Integer.parseInt(inHouseInvTxt.getText()), Integer.parseInt(inHouseMinTxt.getText()), 
                    Integer.parseInt(inHouseMaxTxt.getText()), Integer.parseInt(machIDTxt.getText())));
        
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.WARNING);
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
