package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller to modify existing product(s).
 */
public class modifyProductFormController implements Initializable {
    @FXML
    Stage stage;
    @FXML
    Parent scene;
    @FXML
    private TextField searchBar;

    @FXML
    private TableView<Part> productDataTableView;
    @FXML
    private TableColumn<Part, Integer> productIdCol;
    @FXML
    private TableColumn<Part, String> prodNameCol;
    @FXML
    private TableColumn<Part, Integer> prodInvCol;
    @FXML
    private TableColumn<Part, Double> prodPriceCol;

    @FXML
    private TableView<Part> associatedPartTableView;
    @FXML
    private TableColumn<Part, Integer> associatedPartIdCol;
    @FXML
    private TableColumn<Part, String> associatedPartNameCol;
    @FXML
    private TableColumn<Part, Integer> associatedInvLevelCol;
    @FXML
    private TableColumn<Part, Double> associatedPricePerUnitCol;

    @FXML
    private TextField productId;
    @FXML
    private TextField productName;
    @FXML
    private TextField productInv;
    @FXML
    private TextField productPrice;
    @FXML
    private TextField productMax;
    @FXML
    private TextField productMin;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int idx = 0;

    /**
     * Saves product.
     * When save button is clicked, validates input is correct and creates a new product for Inventory. Returns to main screen.
     * @param event click save button
     */
    @FXML
    public void onSaveButtonClick(ActionEvent event) {
        try {
            int id = Integer.parseInt(productId.getText());
            String name = productName.getText();
            int inv = Integer.parseInt(productInv.getText());
            double price = Double.parseDouble(productPrice.getText());
            int max = Integer.parseInt(productMax.getText());
            int min = Integer.parseInt(productMin.getText());


            if (min > max){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Minimum can't be greater than Maximum");
                alert.show();
                return;
            }

            if ((min > inv) || inv > max){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory must be within range of minimum and maximum");
                alert.show();
                return;
            }

            Product product = new Product(id, name, price, inv, min, max);

            for (Part part: associatedParts) {
                product.addAssociatedProduct(part);
            }

            Inventory.updateProduct(idx, product);

            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();


        }
            catch(NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Input Error");
                alert.setContentText("Incorrect value");
                alert.show();
                return;
            } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Loads main form.
     * When cancel button clicked, returns to main form view.
     * @param event cancel button click
     * @throws IOException
     */
    @FXML
    public void onCancelButtonClick(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Removes associated part from list.
     * When remove associated part button is clicked, confirms and removes selected associated part from associated part table view.
     * @param event remove associated parts button click
     */
    @FXML
    public void onRemoveAssociatedPartClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove association between this part and product??");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK){
            associatedParts.remove(associatedPartTableView.getSelectionModel().getSelectedItem());
        }
    }

    /**Search for parts.
     * When the search bar is active and enter is pressed, compares inputted string to those from Inventory to show
     * matched results on product data table view.
     * @param event enter button
     */
    @FXML
    public void onSearchEnter(ActionEvent event) {
        String x = searchBar.getText();

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

        productDataTableView.setItems(parts);
    }


    /**Adds part to associated parts list.
     * When add button for product is clicked, adds selected associated parts to a list and adds that list to associated
     * parts view table.
     * @param event add part button clicked
     */
    @FXML
    public void onAddProductClick(ActionEvent event) {

        associatedParts.add(productDataTableView.getSelectionModel().getSelectedItem());
        associatedPartTableView.setItems(associatedParts);
    }

    /**
     * Sends information between controllers.
     * Sets values in the modify product form based on the selected product from main form controller.
     * @param idx index of product
     * @param product product
     */
    @FXML
    public void sendProduct(int idx, Product product){

        this.idx = idx;

        for (Part part: product.getAllAssociatedParts()) {
            associatedParts.add(part);
        }
        //associatedParts = product.getAllAssociatedParts();
        productId.setText(String.valueOf(product.getId()));
        productName.setText(product.getName());
        productInv.setText(String.valueOf(product.getStock()));
        productPrice.setText(String.valueOf(product.getPrice()));
        productMin.setText(String.valueOf(product.getMin()));
        productMax.setText(String.valueOf(product.getMax()));



    }


    /**Initialize method sets data.
     * Populates parts table as well as associated parts table.
     * @param url
     * @param resourceBundle
     */

    public void initialize(URL url, ResourceBundle resourceBundle) {
        productDataTableView.setItems(Inventory.getAllParts());

        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartTableView.setItems(associatedParts);

        associatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPricePerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));


    }
}
