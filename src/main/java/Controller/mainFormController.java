package Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.stage.Stage;


/**
 * Main page of Inventory Management System's methods and functionality.
 */
public class mainFormController implements Initializable {
    @FXML
    Stage stage;
    @FXML
    Parent scene;
    @FXML
    public TextField searchPart;

    @FXML
    private TableView<Part> partsTableView;
    @FXML
    private TableColumn<Part, Integer> partID;
    @FXML
    private TableColumn<Part, String> partName;
    @FXML
    private TableColumn<Part, Integer> inventoryLevelColumn;
    @FXML
    private TableColumn<Part, Double> partPricePerUnit;


    @FXML
    private TableView<Product> productsTableView;
    @FXML
    private TableColumn<Product, Integer> productId;
    @FXML
    private TableColumn<Product, String> productName;
    @FXML
    private TableColumn<Product, Integer> productInventoryLevelColumn;
    @FXML
    private TableColumn<Product, Double> productPricePerUnit;
    @FXML
    private TextField searchProduct;


    /**
     * Searches for parts in Inventory.
     * On event, searches for given value in Inventory parts list to see if any matches.
     * Sets matches to partsTableView.
     * @param event enter button press
     */
    @FXML
    private void onSearchPart(ActionEvent event) {
        String x = searchPart.getText();

        ObservableList<Part> parts = Inventory.lookupPart(x);

        try {
            if (parts.size() == 0) {
                int i = Integer.parseInt(x);
                Part part = Inventory.lookupPart(i);
                if (part != null) {
                    parts.add(part);
                }
            }
        } catch(NumberFormatException ex){
            //ignore
        }
        partsTableView.setItems(parts);
    }

    /**
     * When highlighting search product bar and enter is pressed, checks to see if value put in has a match in the products list
     * Sets matches to productsTableView
     * @param event enter button press
     * @throws NumberFormatException ex
     */
    @FXML
    private void onSearchProduct(ActionEvent event){
        String x = searchProduct.getText();

        ObservableList<Product> products = Inventory.lookupProduct(x);

        try {
            if (products.size() == 0) {
                int i = Integer.parseInt(x);
                Product product = Inventory.lookupProduct(i);
                if (product != null) {
                    products.add(product);
                }
            }
        } catch(NumberFormatException ex){
            //ignore
        }
        productsTableView.setItems(products);
    }


    /**
     * Goes to modify part page. When modify part button is clicked, checks to see if a part is selected from the parts table.
     * Sends part data relating to selected part to the modifyPartForm and loads modify part form.
     * @param event modify part button click
     * @throws IOException
     *
     * RUNTIME ERROR:
     * I used method showAndWait() and had RuntimeException from InvocationTargetException
     * The program functioned as needed, so I waited until nearly finished with the project to deal with this.
     * I was unable to pinpoint what in the block of codes with showAndWait() were causing this flurry of red error text.
     * I trial and errored and researched until I tried using the show() method.
     * The errors disappeared and the program functioned as I expected. I believe this has something to do with modality
     * of the stage but since it met my requirements, I changed all my showAndWait() methods to show to correct the error.
     */
    public void onModifyPart(ActionEvent event) throws IOException {

        if (partsTableView.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Must select a part to modify");
            alert.show();
            return;
        }

        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(getClass().getResource("/view/modifyPartForm.fxml"));
        loader1.load();


        modifyPartFormController MPFController = loader1.getController();
        MPFController.sendPart(partsTableView.getSelectionModel().getSelectedIndex(), partsTableView.getSelectionModel().getSelectedItem());

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader1.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();


    }

    /**
     * Loads add part page.
     * When add part button is clicked, loads add part form scene.
     * @param event click add part button
     * @throws IOException
     */
    public void onAddPart(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addPartForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**Deletes part.
     * When delete part is clicked, confirms and removes selected part from Inventory.
     * @param event click delete part
     */
    public void onDeletePart(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this part?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK){
        Inventory.deletePart(partsTableView.getSelectionModel().getSelectedItem());}
    }

    /**
     * Delete product.
     * When delete product clicked: confirms, validates, and removes selected product from Inventory.
     * @param event click delete product
     */
    public void onDeleteProduct(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this part?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK){
            if (productsTableView.getSelectionModel().getSelectedItem().getAllAssociatedParts().size() > 0){
                Alert alert1 = new Alert(Alert.AlertType.ERROR, "Product can not have associated parts");
                alert1.show();}
            else {Inventory.deleteProduct(productsTableView.getSelectionModel().getSelectedItem());}}
    }

    /**Modify product page loads.
     * When modify product button clicked, checks if a product is selected and if so loads modify product form
     * Passes in data from sendProduct(idx, selectedItem) to populate tables on modify product form.
     * @param event click modify product button.
     * @throws IOException
     */
    @FXML
    public void onModifyProduct(ActionEvent event) throws IOException {

            if (productsTableView.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Must select a product to modify");
                alert.show();
                return;
            }

            else {
                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(getClass().getResource("/view/modifyProductForm.fxml"));
                loader2.load();


                modifyProductFormController MPF1Controller = loader2.getController();
                MPF1Controller.sendProduct(productsTableView.getSelectionModel().getSelectedIndex(), productsTableView.getSelectionModel().getSelectedItem());
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = loader2.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();
            }
    }

    /**
     * Loads add Product form.
     * When add product button clicked, loads add product form scene.
     * @param event click add product button
     * @throws IOException
     */
    public void onAddProduct(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addProductForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * Program exits
     * When exit button clicked, program exits
     * @param event click exit button
     */
    public void onExit(ActionEvent event) {
        System.exit(0);
    }

    /**Initializes data.
     * Populates parts table and products table on launch.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partsTableView.setItems(Inventory.getAllParts());
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        //---------------------------------------------------------------------------

        productsTableView.setItems(Inventory.getAllProducts());
        productId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}