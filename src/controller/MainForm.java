package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The main view controller for the program
 *
 * @author Alberto Sosa
 */

public class MainForm implements Initializable {

    // Part table view objects
    public TableView partTableView;
    public TableColumn partIdColumn;
    public TableColumn partNameColumn;
    public TableColumn partInventoryLevelColumn;
    public TableColumn partCostPerUnitColumn;
    public TextField partsSearchBarTextField;
    //Product table view objects
    public TableView productTableView;
    public TableColumn productIdColumn;
    public TableColumn productNameColumn;
    public TableColumn productInventoryLevelColumn;
    public TableColumn productCostPerUnitColumn;
    public TextField productsSearchBarTextField;



    //Test Data
    public static boolean testDataIsInitialized = false;
    public void initializeTestData() {
        Inventory.addPart(new OutsourcedPart(1, "Turbo", 1999.99, 7, 0, 15, "CTS"));
        Inventory.addPart(new OutsourcedPart(2, "Cold Air Intake", 349.99, 17, 0, 40, "K&M"));
        Inventory.addPart(new InHousePart(3, "Break Pad", 24.99, 42, 0, 100, 243727));
        Inventory.addPart(new InHousePart(4, "Rim", 249.99, 32, 0, 80, 864726));
        Inventory.incrementNextPartId();
        Inventory.incrementNextPartId();
        Inventory.incrementNextPartId();
        Inventory.incrementNextPartId();

        Inventory.addProduct(new Product(1, "Volkswagen Golf", 24999.99, 4, 0, 10));
        Inventory.addProduct(new Product(2, "Honda Civic", 19999.99, 3, 0, 10));
        Inventory.addProduct(new Product(3, "Toyota Corolla", 17999.99, 7, 0, 10));
        Inventory.incrementNextProductId();
        Inventory.incrementNextProductId();
        Inventory.incrementNextProductId();
    }


    //Part Table Methods

    /**
     * Called when the enter key is pressed in the parts search bar.
     * Searches the inventory for parts with either a matching part id or part name.
     */
    public void partsSearchBarEnterPushed() {
        if (partsSearchBarTextField.getText().equals("")){
            partTableView.setItems(Inventory.getAllParts());
            return;
        }
        try {
            ObservableList<Part> partIdSearchSubList = FXCollections.observableArrayList();
            partIdSearchSubList.add(Inventory.lookupPart(Integer.parseInt(partsSearchBarTextField.getText())));
            partTableView.setItems(partIdSearchSubList);
        } catch (NumberFormatException e) {
            partTableView.setItems(Inventory.lookupPart(partsSearchBarTextField.getText()));
        }
    }

    /**
     * Called when the add part button is pressed.
     * launches the add part view.
     */
    public void addPartButtonPushed(ActionEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(addPartScene);
        window.show();
    }

    /**
     * Called when the modify part button is pressed.
     * if a part is selected, the modify part view is launched and the selected part is passed in.
     * if a part is not selected, the user is prompted to select a part.
     */
    public void modifyPartButtonPushed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ModifyPart.fxml"));
        Parent modifyPartParent = loader.load();

        ModifyPart controller = loader.getController();

        if (partTableView.getSelectionModel().getSelectedItem() instanceof InHousePart) {
            controller.initializeModifyPartDataInHousePart((InHousePart) partTableView.getSelectionModel().getSelectedItem());
        } else if (partTableView.getSelectionModel().getSelectedItem() instanceof OutsourcedPart) {
            controller.initializeModifyPartDataOutsourcedPart((OutsourcedPart) partTableView.getSelectionModel().getSelectedItem());
        } else {
            System.out.println("Error: No item selected to modify.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Part Selected");
            alert.setHeaderText("No Part Selected");
            alert.setContentText("Please select a part to modify.");
            alert.showAndWait();
            return;
        }

        Scene modifyPartScene = new Scene(modifyPartParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(modifyPartScene);
        window.show();
    }

    /**
     * Called when the delete part button is pressed.
     * if a part is selected, the user is prompted to confirm the deletion of the part.
     * if a part is not selected, the user is prompted to select a part.
     */
    public void deletePartButtonPushed() {
        for (int i = 0; i < Inventory.getAllProducts().size(); i++) {
            if (Inventory.getAllProducts().get(i).getAllAssociatedParts().contains(partTableView.getSelectionModel().getSelectedItem())) {
                System.out.println("Error Deleting Part");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Deleting Part");
                alert.setHeaderText("Error Deleting Part");
                alert.setContentText("Please remove this part from any associated products before deleting.");
                alert.showAndWait();
                return;
            }
        }
        Inventory.deletePart((Part) partTableView.getSelectionModel().getSelectedItem());
    }

    /**
     * Called when the exit button is pressed.
     * exits the application
     */
    public void exitButtonPushed() {
        System.exit(0);
    }


    //Products table methods

    /**
     * Called when the enter key is pressed for the products search bar.
     * it searches the inventory for products with either a matching product id or product name.
     */
    public void productsSearchBarEnterPushed() {
        if (productsSearchBarTextField.getText().equals("")){
            productTableView.setItems(Inventory.getAllProducts());
            return;
        }
        try {
            ObservableList<Product> productIdSearchSubList = FXCollections.observableArrayList();
            productIdSearchSubList.add(Inventory.lookupProduct(Integer.parseInt(productsSearchBarTextField.getText())));
            productTableView.setItems(productIdSearchSubList);
        } catch (NumberFormatException e) {
            productTableView.setItems(Inventory.lookupProduct(productsSearchBarTextField.getText()));
        }
    }

    /**
     * Called when the add product button is pressed.
     * launches the add product view.
     */
    public void addProductButtonPushed(ActionEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(addPartScene);
        window.show();
    }

    /**
     * Called when the modify product button is pressed.
     * if a product is selected, the modify product view is launched and the selected part is passed in.
     * if a product is not selected, the user is prompted to select a part.
     */
    public void modifyProductButtonPushed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ModifyProduct.fxml"));
        Parent modifyProductParent = loader.load();

        ModifyProduct controller = loader.getController();

        if (productTableView.getSelectionModel().getSelectedItem() instanceof Product) {
            controller.initializeModifyProductData((Product) productTableView.getSelectionModel().getSelectedItem());
        } else {
            System.out.println("Error: No item selected to modify.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Product Selected");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a product to modify.");
            alert.showAndWait();
            return;
        }

        Scene modifyProductScene = new Scene(modifyProductParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(modifyProductScene);
        window.show();
    }

    /**
     * Called when the delete product button is pressed.
     * if a product is selected, the user is prompted to confirm the deletion of the product.
     * if a product is not selected, the user is prompted to select a product.
     */
    public void deleteProductButtonPushed() {
        Product tempProduct = (Product) productTableView.getSelectionModel().getSelectedItem();
        if (tempProduct.getAllAssociatedParts().isEmpty()) {
            Inventory.deleteProduct(tempProduct);
        } else {
            System.out.println("Error Deleting Product");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Deleting Product");
            alert.setHeaderText("Error Deleting Product");
            alert.setContentText("Please remove all associated parts before attempting to delete a product.");
            alert.showAndWait();
            return;
        }
    }

    /**
     * Initializes the main view.
     * Inserts initial test data.
     * Creates table views for the parts and products table.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!testDataIsInitialized) {
            initializeTestData();
            testDataIsInitialized = true;
        }

        /**
         *  Parts Table
         */
        partTableView.setItems(Inventory.getAllParts());
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostPerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        /**
         * Products Table
         */
        productTableView.setItems(Inventory.getAllProducts());
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productCostPerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

}
