package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class for Inventory parts, products, and functionality related to them.
 */
public class Inventory {

    /**
     * list of all parts
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    /**
     * list of all products
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    /**
     * part ID value starting at 1000 to avoid clashing with product ID value
     */
    private static int partId = 1000;
    /**
     * product ID value set at 0
     */
    private static int productId = 0;

    /**Gets part ID. Returns part ID of this part object + 1.
     * @return returns int value of part ID + 1
     */
    public static int getPartId(){
        return ++partId;
    }

    /**Get Product ID. Returns product ID of this product object + 1.
     * @return returns int value of product ID + 1
     */
    public static int getProductId() {
        return ++productId;
    }

    /**Add part. Adds a part to the inventory.
     * @param newPart accepts a new part object and adds it to the end of allParts list
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**Add product. Adds a product to the inventory.
     * @param newProduct accepts a new product object and adds it to the end of allProducts list
     */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    /**Lookup part. Looks up part by part ID value.
     * @param partId accepts a given part ID number and checks if it is in allParts list
     * @return supposedId (or null if no matching ID is found)
     */
    public static Part lookupPart(int partId){
        for (Part supposedId : allParts){
            if (supposedId.getId() == partId){
                return supposedId;
            }
        }
        return null;
    }

    /**Lookup Product.
     * compares given product ID to allProducts list. If any match, returns supposedId, else return null.
     * @param productId integer value of product ID
     * @return supposedId (product object) of matching product
     */
    public static Product lookupProduct(int productId){
        for (Product supposedId : allProducts){
            if (supposedId.getId() == productId){
                return supposedId;
            }
        }
        return null;
    }

    /**Lookup part.
     * Checks if any values in allParts contain given String partName and adds to list.
     * @param partName String value to compare against in allParts list
     * @return name - list with part names matching given part name
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> name = FXCollections.observableArrayList();
        for (Part partialName : allParts){
            if (partialName.getName().contains(partName)){
                name.add(partialName);
            }
        }
        return name;
    }

    /**Lookup Product.
     * Accepts given product name and compares it against allProducts product names to check for match.
     * @param productName name of product searching for
     * @return name List of products with matching names to given name
     */
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> name = FXCollections.observableArrayList();
        for (Product partialName : allProducts){
            if (partialName.getName().contains(productName)){
                name.add(partialName);
            }
        }
        return name;
    };

    /**Update part.
     * Updates given part values in allParts list.
     * @param index index of part
     * @param selectedPart part that was passed in
     */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    /**Update product.
     * Accepts two parameters and updates given product values in allProducts list.
     * @param index index value of product
     * @param newProduct product passed in
     */
    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    };

    /**Deletes part.
     * Attempts to delete given part from allParts list.
     * @param selectedPart part which is meant for removal from allParts list.
     * @return true if given part successfully removed from list, otherwise return false
     */
    public static boolean deletePart(Part selectedPart){
        if (allParts.contains(selectedPart)){
            allParts.remove(selectedPart);
            return true;
        }
        else {
            return false;
        }
    }

    /**Delete product.
     * Attempts to delete given Product from allProducts list.
     * @param selectedProduct Product to delete
     * @return true if product removal successful, otherwise returns false
     */
    public static boolean deleteProduct(Product selectedProduct){
        if (allProducts.contains(selectedProduct)){
            allProducts.remove(selectedProduct);
            return true;
        }
        else {
            return false;
        }
    };

    /**Return all parts.
     * Returns a list of all parts in the inventory.
     * @return allParts list of all parts
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**Return all Products.
     * Returns a list of all products in the inventory.
     * @return allProducts list of all products
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    };


}
