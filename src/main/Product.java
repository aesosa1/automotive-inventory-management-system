package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class creates allows for the creation and use of a Product object
 *
 * @author Alberto Sosa
 */

public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    public Product(int id, String name, double price, int stock, int min, int max){
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.setStock(stock);
        this.setMin(min);
        this.setMax(max);
    }


    /**
     * @param id sets the product id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the product id
     */
    public int getId() {
        return id;
    }

    /**
     * @param name sets the product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * @param price sets the product price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param stock sets the product stock level
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the product stock level
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param min sets the minimum stock level
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the minimum stock level
     */
    public int getMin() {
        return min;
    }

    /**
     * @param max sets the maximum stock level
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @return the maximum stock level
     */
    public int getMax() {
        return max;
    }

    /**
     * @param part adds the passed in part object to the associated parts list
     */
    public void addAssociatedPart(Part part) {
        this.associatedParts.add(part);
    }

    /**
     * @param selectedAssociatedPart removes the passed in part from the associated parts list
     * @return true if parts was successfully deleted; false if an error occurred
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        try {
            this.associatedParts.remove(selectedAssociatedPart);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @return a list with all associated part objects
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return this.associatedParts;
    }

}
