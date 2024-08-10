package Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller to modify existing parts.
 */
public class modifyPartFormController {
    @FXML
    Stage stage;
    @FXML
    Parent scene;
    @FXML
    private ToggleGroup modifyInHouseOrOutsource;
    @FXML
    private RadioButton inHouseSelected;
    @FXML
    private RadioButton outsourceSelected;
    @FXML
    private TableView<Product> productDataTableView;
    @FXML
    private TableColumn<Product, Integer> productId;
    @FXML
    private TextField modifyPartId;
    @FXML
    private TextField modifyPartName;
    @FXML
    private TextField modifyPartInv;
    @FXML
    private TextField modifyPartPrice;
    @FXML
    private TextField modifyPartMax;
    @FXML
    private TextField modifyPartMin;
    @FXML
    private TextField modifyMachineId;
    @FXML
    private Button saveModifyButton;
    @FXML
    private Button cancelModifyButton;
    private int idx;
    @FXML
    private Label machineIdLabel;

    /**
     * InHouse Select.
     * Sets machineIdLabel text to Machine ID.
     * @param event InHouse radio click
     */
    @FXML
    private void onInHouseClick(ActionEvent event) {
        machineIdLabel.setText("Machine ID");
        modifyMachineId.setLayoutX(87.0);
        modifyMachineId.setLayoutY(271.0);
    }

    /**
     * Outsource select.
     * Sets machineIdLabel text to Company Name.
     * @param event outsource radio click
     */
    @FXML
    private void onOutsourcedClick(ActionEvent event) {
        machineIdLabel.setText("Company Name");
        modifyMachineId.setLayoutX(135.0);
        modifyMachineId.setLayoutY(271.0);
    }

    /**Saves part.
     * Save button click tries to modify all part values to those inputted on the form. Returns to main screen.
     * @param event save button click
     * @throws IOException
     */
    @FXML
    private void onSaveButtonClick(ActionEvent event) throws IOException{
        try {
            int partId = Integer.parseInt(modifyPartId.getText());
            String partName = modifyPartName.getText();
            double partPrice = Double.parseDouble(modifyPartPrice.getText());
            int partMin = Integer.parseInt(modifyPartMin.getText());
            int partMax = Integer.parseInt(modifyPartMax.getText());
            int partInv = Integer.parseInt(modifyPartInv.getText());


            if (partMin > partMax){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Minimum can't be greater than Maximum");
                alert.show();
                return;
            }

            if ((partMin > partInv) || partInv > partMax){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory must be within range of minimum and maximum");
                alert.show();
                return;
            }

            if (inHouseSelected.isSelected()){
                int machineId = Integer.parseInt(modifyMachineId.getText());
                Part part = new InHouse(partId, partName, partPrice, partMin, partMax, partInv, machineId);
                Inventory.updatePart(idx, part);

                stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();}

            else if (outsourceSelected.isSelected()){
                String companyName = modifyMachineId.getText();
                Outsourced part = new Outsourced(partId, partName, partPrice, partMin, partMax, partInv, companyName);
                Inventory.updatePart(idx, part);

                stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();}
            } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            alert.show();
            throw new RuntimeException(ex);
        }
    }


    /**Loads main form.
     * When cancel button clicked, loads main form page of application.
     * @param event click cancel button
     * @throws IOException
     */
    @FXML
    private void onCancelButtonClick(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**Sends information between controllers
     * Sets text of modify part form to values given from main page selected part
     * @param indexSelected index of part
     * @param part part from inventory
     */
    @FXML
    public void sendPart(int indexSelected, Part part){
        idx = indexSelected;
        modifyPartName.setText(part.getName());
        modifyPartId.setText(String.valueOf(part.getId()));
        modifyPartPrice.setText(String.valueOf(part.getPrice()));
        modifyPartMin.setText(String.valueOf(part.getMin()));
        modifyPartMax.setText(String.valueOf(part.getMax()));
        modifyPartInv.setText(String.valueOf(part.getStock()));

        if (part instanceof InHouse) {
            modifyMachineId.setText(String.valueOf(((InHouse) part).getMachineId()));
            machineIdLabel.setText("Machine ID");
            modifyMachineId.setLayoutX(87.0);
            modifyMachineId.setLayoutY(271.0);
            inHouseSelected.setSelected(true);
        }
        else {
            assert part instanceof Outsourced;
            modifyMachineId.setText(String.valueOf(((Outsourced) part).getCompanyName()));
            machineIdLabel.setText("Company Name");
            modifyMachineId.setLayoutX(135.0);
            modifyMachineId.setLayoutY(271.0);
            outsourceSelected.setSelected(true);
        }
    }
}
