package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.InHousePart;
import main.Inventory;
import main.OutsourcedPart;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The add part view controller
 * Allows users to input data and add a part to the inventory
 *
 * @author Alberto Sosa
 */

public class AddPart implements Initializable {

    // All part fields
    public TextField idTextField;
    public TextField nameTextField;
    public TextField inventoryTextField;
    public TextField priceTextField;
    public TextField minTextField;
    public TextField maxTextField;
    // InHouse part fields
    public Label machineIdLabel;
    public TextField machineIdTextField;
    // Outsourced part fields
    public Label companyNameLabel;
    public TextField companyNameTextField;
    // Radio buttons
    public RadioButton inHouseRadioButton;
    public RadioButton outsourcedRadioButton;
    public ToggleGroup partSourceToggleGroup;


    /**
     * Method to return to the main view
     */
    public void returnToMainForm(ActionEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(addPartScene);
        window.show();
    }

    /**
     * Called when the cancel button is pressed.
     * Returns the user to the main view.
     */
    public void cancelButtonPushed(ActionEvent event) throws IOException {
        returnToMainForm(event);
    }

    /**
     * Called when the save part button is pressed.
     * Ensures all input data is of the appropriate data type.
     * Creates a new part object using the given data and adds it to the inventory.
     * Returns to the main view.
     *
     * RUNTIME ERROR was encountered if a user entered an invalid data type; this was corrected by using try catch blocks
     */
    public void savePartButtonPushed(ActionEvent event) throws IOException {

        //Creates Base Variables for user input
        String inputName = nameTextField.getText();
        double inputPrice = 0;
        int inputStock = 0;
        int inputMin = 0;
        int inputMax = 0;
        int inputMachineId = 0;
        String inputCompanyName = companyNameTextField.getText();

        //Checks if all input is valid
        if (inputName.equals("")) {
            System.out.println("Invalid Input: No part name was given.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid Input in Name Field");
            alert.setContentText("Please enter a valid part ID.");
            alert.showAndWait();
            return;
        }
        try {
            inputStock = Integer.parseInt(inventoryTextField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input: \"" + inventoryTextField.getText() + "\" in inventory field. Integer expected.\"");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid Input in Inventory Field");
            alert.setContentText("Please enter a valid part inventory amount.");
            alert.showAndWait();
            return;
        }
        try {
            inputPrice = Double.parseDouble(priceTextField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input: \"" + priceTextField.getText() + "\" in price field. Double expected.\"");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid Input in Price Field");
            alert.setContentText("Please enter a valid part price.");
            alert.showAndWait();
            return;
        }
        try {
            inputMin = Integer.parseInt(minTextField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input: \"" + minTextField.getText() + "\" in min field. Integer expected.\"");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid Input in Min Field");
            alert.setContentText("Please enter a valid part minimum.");
            alert.showAndWait();
            return;
        }
        try {
            inputMax = Integer.parseInt(maxTextField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input: \"" + maxTextField.getText() + "\" in max field. Integer expected.\"");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid Input in Max Field");
            alert.setContentText("Please enter a valid part maximum.");
            alert.showAndWait();
            return;
        }
        if (partSourceToggleGroup.getSelectedToggle().equals(inHouseRadioButton)) {
            try {
                inputMachineId = Integer.parseInt(machineIdTextField.getText());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input: \"" + machineIdTextField.getText() + "\" in machine id field. Integer expected.\"");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("Invalid Input in Machine Id Field");
                alert.setContentText("Please enter a valid part machine ID.");
                alert.showAndWait();
                return;
            }
        }
        if (partSourceToggleGroup.getSelectedToggle().equals(outsourcedRadioButton)) {
            if (inputCompanyName.equals("")) {
                System.out.println("Invalid Input: No company name was given.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("Invalid Input in Company Name Field");
                alert.setContentText("Please enter a valid company name.");
                alert.showAndWait();
                return;
            }
        }
        if (inputMin > inputMax) {
            System.out.println("Invalid Input: Inventory minimum " + inputMin + " is greater than inventory maximum " + inputMax);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Minimum must be less than maximum.");
            alert.showAndWait();
            return;
        }
        if (inputStock < inputMin) {
            System.out.println("Invalid Input: Inventory stock " + inputStock + " is less than the minimum required: " + inputMin);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Inventory must be greater than minimum.");
            alert.showAndWait();
            return;
        }
        if (inputStock > inputMax) {
            System.out.println("Invalid Input: Inventory stock " + inputStock + " is greater than the maximum allowed: " + inputMin);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Inventory must be less than maximum.");
            alert.showAndWait();
            return;
        }

        //If all input is valid, part is added
        if (partSourceToggleGroup.getSelectedToggle().equals(inHouseRadioButton)) {
            Inventory.addPart(new InHousePart(
                    Inventory.getNextPartId(),
                    inputName,
                    inputPrice,
                    inputStock,
                    inputMin,
                    inputMax,
                    inputMachineId));
        } else if (partSourceToggleGroup.getSelectedToggle().equals(outsourcedRadioButton)) {
            Inventory.addPart(new OutsourcedPart(
                    Inventory.getNextPartId(),
                    inputName,
                    inputPrice,
                    inputStock,
                    inputMin,
                    inputMax,
                    inputCompanyName));
        }

        //Returns to Main Form
        Inventory.incrementNextPartId();
        returnToMainForm(event);

    }

    /**
     * Updates the view to include the machine id field and hides the company name field.
     */
    public void inHouseRadioToggleSelected() {
        inHouseRadioButton.setSelected(true);
        machineIdLabel.setVisible(true);
        machineIdTextField.setVisible(true);
        companyNameLabel.setVisible(false);
        companyNameTextField.setVisible(false);
    }

    /**
     * Updates the view to include the company name field and hides the machine id field
     */
    public void setOutsourcedRadioToggleSelected() {
        outsourcedRadioButton.setSelected(true);
        machineIdLabel.setVisible(false);
        machineIdTextField.setVisible(false);
        companyNameLabel.setVisible(true);
        companyNameTextField.setVisible(true);
    }


    /**
     * Initializes the add part view.
     * Creates the toggle group for the in house and outsourced radio buttons.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       System.out.println("Add Part Form Initialized");

        // Initializes toggle groups
        partSourceToggleGroup = new ToggleGroup();
        this.inHouseRadioButton.setToggleGroup(partSourceToggleGroup);
        this.outsourcedRadioButton.setToggleGroup(partSourceToggleGroup);
        inHouseRadioToggleSelected();

        idTextField.setText(Integer.toString(Inventory.getNextPartId()));
    }
}
