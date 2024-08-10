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
 * Controller to add product to Inventory.
 */
public class addProductFormController implements Initializable {
    @FXML
    Stage stage;
    @FXML
    Parent scene;
    @FXML
    private TextField searchBar;
    @FXML
    private TextField prodName;
    @FXML
    private TextField prodInv;
    @FXML
    private TextField prodPrice;
    @FXML
    private TextField prodMax;
    @FXML
    private TextField prodMin;

    @FXML
    private TableView<Part> partDataTableView;
    @FXML
    private TableColumn<Part, Integer> prodIdCol;
    @FXML
    private TableColumn<Part, String> prodNameCol;
    @FXML
    private TableColumn<Part, Integer> invLevelCol;
    @FXML
    private TableColumn<Part, Double> prodPriceCol;

    @FXML
    private TableView<Part> associatedPartDataTableView;
    @FXML
    private TableColumn<Part, Integer> addAssocProdId;
    @FXML
    private TableColumn<Part, String> addAssocPartName;
    @FXML
    private TableColumn<Part, Integer> addAssocInvCol;
    @FXML
    private TableColumn<Part, Double> addAssocPriceCol;

    private ObservableList<Part> assocPartsList = FXCollections.observableArrayList();

    /**
     * Search for parts.
     * When you press enter on the search bar, the value in the search bar is compared to values in Inventorys parts list
     * It then sets the items found to the parts data table (top right).
     * @param event enter button pressed
     * @throws NumberFormatException ignored
     */
    @FXML
    private void onSearchEnter(ActionEvent event) {

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

        partDataTableView.setItems(parts);
    }

    /**
     * Saves product to Inventory.
     * When the save button is clicked, exception handling is performed then if all is good a product with given values
     * is set and added to the Inventory. Alerts if exception occurs. Returns to main screen.
     * @param event save product button click
     * @throws NumberFormatException
     */
    @FXML
    private void onSaveProductClick(ActionEvent event) {

        try {
            int id = Inventory.getProductId();
            String name = prodName.getText();
            double price = Double.parseDouble(prodPrice.getText());
            int inv = Integer.parseInt(prodInv.getText());
            int min = Integer.parseInt(prodMin.getText());
            int max = Integer.parseInt(prodMax.getText());
            System.out.println("Product Added!");

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

            Product prod = new Product(id, name, price, inv, min, max);
            for (Part part: assocPartsList) {
                prod.addAssociatedProduct(part);
            }
            Inventory.addProduct(prod);

            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns to main page.
     * When cancel button clicked, loads main page of application.
     * @param event cancel button click
     * @throws IOException
     */
    @FXML
    private void onCancelClick(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Removes part from associated parts list.
     * When remove button clicked, confirms and removes selected associated part from assocPartsList.
     * @param event remove associated part click
     */
    @FXML
    private void onRemoveProductClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove association between this part and product??");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK){
            assocPartsList.remove(associatedPartDataTableView.getSelectionModel().getSelectedItem());
            }

    }

    /**
     * Adds item to associated parts list.
     * When add button clicked, adds selected item to assocPartsList list.
     * @param event add button click
     */
    @FXML
    private void onAddProductClick(ActionEvent event) {
        assocPartsList.add(partDataTableView.getSelectionModel().getSelectedItem());
    }


    /**
     * Create tables.
     * Creates two data tables for all parts as well as associated parts of a product.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Populate Parts Table
        partDataTableView.setItems(Inventory.getAllParts());

        prodIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartDataTableView.setItems(assocPartsList);

        addAssocProdId.setCellValueFactory(new PropertyValueFactory<>("id"));
        addAssocPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        addAssocInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addAssocPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}

