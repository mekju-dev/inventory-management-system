package Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller to add part to Inventory.
 */
public class addPartFormController {

    @FXML
    Stage stage;
    @FXML
    Parent scene;
    @FXML
    private RadioButton inHousePartSelected;
    @FXML
    private RadioButton outsourcedPartSelected;
    @FXML
    private TextField addPartId;
    @FXML
    private TextField addPartName;
    @FXML
    private TextField addPartInv;
    @FXML
    private TextField addPartPrice;
    @FXML
    private TextField addPartMax;
    @FXML
    private TextField addMachineId;
    @FXML
    private TextField addPartMin;
    @FXML
    private Label machineIDLabel;
    @FXML
    private ToggleGroup addInHouseOrOutsource;


    /**
     * Saves part to Inventory.
     * When save button clicked, checks for exceptions as well as if the part is InHouse or Outsourced and tries to
     * add to Inventory as a part object. Returns to main screen.
     * @param event save button click
     * @throws IOException
     */
    @FXML
    private void onSaveButtonClick(ActionEvent event) throws IOException{

            try {
                int id = Inventory.getPartId();



                String name = addPartName.getText();
                int inv = Integer.parseInt(addPartInv.getText());
                double price = Double.parseDouble(addPartPrice.getText());
                int max = Integer.parseInt(addPartMax.getText());
                int min = Integer.parseInt(addPartMin.getText());
                String companyName;

                if (min > max){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Minimum can't be greater than Maximum");
                    alert.show();
                    return;
                }
                if (inv < min || inv > max){
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Inventory is out of mininum/maximum range");
                    alert.show();
                    return;
                }



                if (outsourcedPartSelected.isSelected()){
                    companyName = (String)machineIDLabel.getText();
                    Outsourced part = new Outsourced(id, name, price, inv, min, max, companyName);
                    Inventory.addPart(part);

                    stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
                else if (inHousePartSelected.isSelected()){
                    int machineId = Integer.parseInt(addMachineId.getText());
                    InHouse part = new InHouse(id, name, price, inv, min, max, machineId);
                    Inventory.addPart(part);

                    stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
            } catch (NumberFormatException ignore) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ignore.getMessage());
                alert.show();
            }

    }


    /**
     * Returns to main page.
     * On cancel button click, loads main page of application.
     * @param event cancel button click
     * @throws IOException
     */
    @FXML
    private void onCancelButtonClick(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Sets page to match outsourced part.
     * On outsourced radio click, sets machineId label to String value company name to represent an outsourced part.
     * @param event outsourced radio click
     */
    @FXML
    private void onOutsourcedClick(ActionEvent event) {
        machineIDLabel.setText("Company Name");
        addMachineId.setLayoutX(135.0);
        addMachineId.setLayoutY(271.0);
    }

    /**Sets page to match InHouse part.
     * On by default or if clicked after onOutsourcedClick runs, sets machineIdLabel to String value Machine ID to
     * represent an InHouse Part.
     * @param event InHouse radio click
     */
    @FXML
    private void onInHouseClick(ActionEvent event) {
        machineIDLabel.setText("Machine ID");
        addMachineId.setLayoutX(87.0);
        addMachineId.setLayoutY(271.0);
    }

}
