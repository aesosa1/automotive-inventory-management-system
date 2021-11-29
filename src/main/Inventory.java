package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Creates a static inventory class which stores and manipulates data for both the product and part classes
 *
 * @author Alberto Sosa
 */

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int nextPartId = 1;
    private static int nextProductId = 1;


    //Methods for part class
    /**
     * @param newPart the new part to be added to the inventory
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * @param partId the part id to search for in the inventory
     * @return the part object with the matching id if found in the inventory
     */
    public static Part lookupPart(int partId) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getId() == partId){
                return allParts.get(i);
            }
        }
        return null;
    }

    /**
     * @param partName the part name to search for in the inventory
     * @return all part objects with names that contain the passed in search value
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> subList = FXCollections.observableArrayList();
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getName().toLowerCase().contains(partName.toLowerCase())){
                subList.add(allParts.get(i));
            }
        }
        return subList;
    }

    /**
     * @param index the location in the inventory which will be updated
     * @param selectedPart the new part object that will take the place of the original part object at the index location
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * @param selectedPart the part object to be deleted from the inventory
     * @return true if the part object was deleted from the inventory. false if the part object was not deleted from the inventory
     */
    public static boolean deletePart(Part selectedPart) {
        boolean deleted = false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete Part");
        alert.setHeaderText("Confirm Delete Part");
        alert.setContentText("Are you sure you want to delete this part?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            allParts.remove(selectedPart);
            deleted = true;
        }
        return deleted;
    }

    /**
     * @return an observable list of all part objects in the inventory
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Used to create unique part ids
     * @return the next part id to be assigned
     */
    public static int getNextPartId() {
        return nextPartId;
    }

    /**
     * increments the part id after a new part is created
     */
    public static void incrementNextPartId() {
        nextPartId++;
    }


    //Methods for product class
    /**
     * @param newProduct the new product to be added to the inventory
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * @param productId the product id to search for in the inventory
     * @return the product object with the matching id if found in the inventory
     */
    public static Product lookupProduct(int productId) {
       for (int i = 0; i < allProducts.size(); i++) {
           if (allProducts.get(i).getId() == productId){
               return allProducts.get(i);
           }
       }
       return null;
    }

    /**
     * @param productName the product name to search for in the inventory
     * @return all product objects that contain the passed in search value
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> subList = FXCollections.observableArrayList();
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getName().toLowerCase().contains(productName.toLowerCase())){
                subList.add(allProducts.get(i));
            }
        }
        return subList;
    }

    /**
     * @param index the location in the inventory which will be updated
     * @param newProduct the new product object that will take the place of the original product object at the index location
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**
     * @param selectedProduct the product object to be deleted from the inventory
     * @return true of the product object was  from the inventory. false if the product object was not deleted from the inventory
     */
    public static boolean deleteProduct(Product selectedProduct) {
        boolean deleted = false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete Product");
        alert.setHeaderText("Confirm Delete Product");
        alert.setContentText("Are you sure you want to delete this product?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            allProducts.remove(selectedProduct);
            deleted = true;
        }
        return deleted;


    }

    /**
     * @return an observable list of all product objects in the inventory
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * Used to create unique product ids
     * @return the next product id to be assigned
     */
    public static int getNextProductId() {
        return nextProductId;
    }

    /**
     * increments the product id after a new part is created
     */
    public static void incrementNextProductId() {
        nextProductId++;
    }
}

