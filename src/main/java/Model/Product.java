package Model;

import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class for Product Object.
 */
public class Product {

    /**
     * List of a product's associated parts (ex. what parts are needed to make a product)
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * variables of a product including id, name, price, stock, min stock, and max stock
     */
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Product constructor that creates a product object using the given parameters
     * @param id id of product
     * @param name name of product
     * @param price price of product
     * @param stock stock of product
     * @param min minimum stock of product
     * @param max maximum stock of product
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**Get ID.
     * Gets ID of a product
     * @return id product ID value
     */
    public int getId() {
        return id;
    }

    /**Set ID.
     * Sets ID of a product to given ID value.
     * @param id ID to set this product to
     */
    public void setId(int id) {
        this.id = id;
    }

    /**Get name.
     * Returns the name of a product.
     * @return name Product name
     */
    public String getName() {
        return name;
    }

    /**Set Name.
     * Sets name of this product to the given String value.
     * @param name String value to set name to.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**Get price.
     * Returns the price of this product.
     * @return price Cost of product
     */
    public double getPrice() {
        return price;
    }

    /**Set price.
     * Sets the price of this product.
     * @param price Cost of price to set as
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**Get stock.
     * Returns this products current stock value.
     * @return stock Number currently in inventory.
     */
    public int getStock() {
        return stock;
    }

    /**Set stock.
     * Sets stock value of this product to given stock value.
     * @param stock sets number of products in inventory as passed in parameter
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**Get Min.
     * Returns minimum stock value of this product.
     * @return min minimum stock this product should be at.
     */
    public int getMin() {
        return min;
    }

    /**Set min.
     * Sets minimum stock value of this product to given value.
     * @param min minimum stock for this product
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**Get Max.
     * Returns max stock value of this product.
     * @return max Maximum stock value of this product
     */
    public int getMax() {
        return max;
    }

    /**Set max.
     * Sets max stock value of this product to given parameter.
     * @param max value that maximum stock will be set to
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**Add associated parts.
     * Adds a part to a product's associated parts list (at the end of the list).
     * @param part Part to be added to a product's associated parts list
     */
    public void addAssociatedProduct(Part part) {
        associatedParts.add(part);
    }

    /**Get all associated parts.
     * Returns all associated parts related to this product.
     * @return associatedParts Parts related to this product
     */
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }

    /**Delete associated part.
     * Deletes given associated parts from associatedParts list of a product.
     * @param selectedAssociatedProduct part of a product designated for deletion
     * @return true if part is in list, else false
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedProduct) {
        if (associatedParts.contains(selectedAssociatedProduct)) {
            associatedParts.remove(selectedAssociatedProduct);
            return true;
        } else {
            return false;
        }
    }

}
