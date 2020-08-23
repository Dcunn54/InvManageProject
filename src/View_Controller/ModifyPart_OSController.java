/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import Model.Outsourced;
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
public class ModifyPart_OSController implements Initializable {
    
    Parent scene;
    Stage stage;
    
    @FXML
    private RadioButton outsourcedRad;

    @FXML
    private RadioButton inHouseRad;

    @FXML
    private TextField modOutsourcedIDTxt;

    @FXML
    private TextField modCompNameTxt;

    @FXML
    private TextField modOutsourcedMaxTxt;

    @FXML
    private TextField modOutsourcedPriceCostTxt;

    @FXML
    private TextField modOutsourcedInvTxt;

    @FXML
    private TextField modOutsourcedNameTxt;

    @FXML
    private TextField modOutsourcedMinTxt;

    @FXML
    private Button saveModOutsourcedBtn;

    @FXML
    private Button cancelModOutsourcedBtn;

    @FXML
    void onActionInHouse(ActionEvent event) {

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
        try { 
            Outsourced updatedPart = new Outsourced(Integer.parseInt(modOutsourcedIDTxt.getText()), modOutsourcedNameTxt.getText(), 
                Double.parseDouble(modOutsourcedPriceCostTxt.getText()), Integer.parseInt(modOutsourcedInvTxt.getText()), 
                Integer.parseInt(modOutsourcedMinTxt.getText()), Integer.parseInt(modOutsourcedMaxTxt.getText()), modCompNameTxt.getText());
        Inventory.updatePart(Inventory.getIndexOfPart(Integer.parseInt(modOutsourcedIDTxt.getText())), updatedPart);
            
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
    
    public void sendOutsourced(Part outsourced){
        modOutsourcedIDTxt.setText(String.valueOf(outsourced.getID()));
        modCompNameTxt.setText(String.valueOf(((Outsourced)outsourced).getCompanyName()));
        modOutsourcedMaxTxt.setText(String.valueOf(outsourced.getMax()));
        modOutsourcedMinTxt.setText(String.valueOf(outsourced.getMin()));
        modOutsourcedPriceCostTxt.setText(String.valueOf(outsourced.getPrice()));
        modOutsourcedInvTxt.setText(String.valueOf(outsourced.getStock()));
        modOutsourcedNameTxt.setText(outsourced.getName());    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }
    
}
