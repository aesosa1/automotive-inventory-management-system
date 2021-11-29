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
 * The modify part view controller
 * Allows the user to modify a selected part object from the inventory
 *
 * @author Alberto Sosa
 */

public class ModifyPart implements Initializable {

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
    // Passed in part data
    public InHousePart inHousePartToBeModified;
    public OutsourcedPart outsourcedPartToBeModified;
    public boolean passedInPartIsInHouse;
    public boolean passedInPartIsOutsourced;


    /**
     * Initializes the part data if the user passes in an in house part.
     * Sets all input fields to that of the passed in part.
     *
     * @param part the in house part object to be modified
     */
    public void initializeModifyPartDataInHousePart(InHousePart part) {
        inHouseRadioToggleSelected();
        idTextField.setText(Integer.toString(part.getId()));
        nameTextField.setText(part.getName());
        inventoryTextField.setText(Integer.toString(part.getStock()));
        priceTextField.setText(Double.toString(part.getPrice()));
        minTextField.setText(Integer.toString(part.getMin()));
        maxTextField.setText(Integer.toString(part.getMax()));
        machineIdTextField.setText(Integer.toString(part.getMachineId()));
        inHousePartToBeModified = part;
        passedInPartIsInHouse = true;
        passedInPartIsOutsourced = false;
    }

    /**
     * Initializes the part data if the user passes in an outsourced part.
     * Sets all input fields toe the passed in values.
     *
     * @param part the outsourced part object to be modified
     */
    public void initializeModifyPartDataOutsourcedPart(OutsourcedPart part) {
        setOutsourcedRadioToggleSelected();
        idTextField.setText(Integer.toString(part.getId()));
        nameTextField.setText(part.getName());
        inventoryTextField.setText(Integer.toString(part.getStock()));
        priceTextField.setText(Double.toString(part.getPrice()));
        minTextField.setText(Integer.toString(part.getMin()));
        maxTextField.setText(Integer.toString(part.getMax()));
        companyNameTextField.setText(part.getCompanyName());
        outsourcedPartToBeModified = part;
        passedInPartIsInHouse = false;
        passedInPartIsOutsourced = true;
    }


    /**
     * Method to return to the main view
     */
    public void returnToMainForm(ActionEvent event) throws IOException {
        Parent modifyPartParent = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        Scene modifyPartScene = new Scene(modifyPartParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(modifyPartScene);
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
     * Updates the passed in part object from the inventory with the new input data.
     * Returns to the main view.
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

        //If all input is valid, part is updated
        if (partSourceToggleGroup.getSelectedToggle().equals(inHouseRadioButton) && passedInPartIsInHouse) {
            Inventory.updatePart(Inventory.getAllParts().indexOf(inHousePartToBeModified),
                    new InHousePart(
                            inHousePartToBeModified.getId(),
                            inputName,
                            inputPrice,
                            inputStock,
                            inputMin,
                            inputMax,
                            inputMachineId));
        } else if (partSourceToggleGroup.getSelectedToggle().equals(outsourcedRadioButton) && passedInPartIsOutsourced) {
            Inventory.updatePart(Inventory.getAllParts().indexOf(outsourcedPartToBeModified),
                    new OutsourcedPart(
                            outsourcedPartToBeModified.getId(),
                            inputName,
                            inputPrice,
                            inputStock,
                            inputMin,
                            inputMax,
                            inputCompanyName));
        } else if (partSourceToggleGroup.getSelectedToggle().equals(inHouseRadioButton) && !passedInPartIsInHouse) {
            Inventory.updatePart(Inventory.getAllParts().indexOf(outsourcedPartToBeModified),
                    new InHousePart(
                            outsourcedPartToBeModified.getId(),
                            inputName,
                            inputPrice,
                            inputStock,
                            inputMin,
                            inputMax,
                            inputMachineId));
        } else if (partSourceToggleGroup.getSelectedToggle().equals(outsourcedRadioButton) && !passedInPartIsOutsourced) {
            Inventory.updatePart(Inventory.getAllParts().indexOf(inHousePartToBeModified),
                    new OutsourcedPart(
                            inHousePartToBeModified.getId(),
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
     * Updates the view to include the company name field and hides the machine id field.
     */
    public void setOutsourcedRadioToggleSelected() {
        outsourcedRadioButton.setSelected(true);
        machineIdLabel.setVisible(false);
        machineIdTextField.setVisible(false);
        companyNameLabel.setVisible(true);
        companyNameTextField.setVisible(true);
    }


    /**
     * Initializes the modify part view.
     * Creates the toggle group for the in house and outsourced radio buttons.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Modify Part Form Initialized");

        // Initializes toggle groups
        partSourceToggleGroup = new ToggleGroup();
        this.inHouseRadioButton.setToggleGroup(partSourceToggleGroup);
        this.outsourcedRadioButton.setToggleGroup(partSourceToggleGroup);
    }
}
