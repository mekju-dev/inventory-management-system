package main;


import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;




/**
 * Main Class that runs the Inventory Management System.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //copy and paste the following 5 lines into methods to transition ex. addPart()
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/mainForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }


    /**
     * This is the first method that runs when the program is launched. Creates 3 (example) InHouse parts, 1 Outsourced parts, and 2 products and adds them to their object respective Inventory list
     * @param args
     *
     *  JAVADOC LOCATION:
     *  c482Project/src/main/java/JavaDoc ---------------------------------------
     *
     *
     *  FUTURE ENHANCEMENTS:
     *  To be updated in next rollout:
     *
     *  User validation for a need to know security access to the inventory management system.
     *  Before being granted access to the main form view, one would need to have permissions on their account
     *  and valid username and password of that account.
     *
     *  I also think the main form controller can be simplified to just have 2 buttons, one to parts from the inventory
     *  and one to products from the inventory. On click these buttons would take you to their respective views. While I
     *  can appreciate the quick access on the main screen, it's not the most secure way to be greeted as you log into
     *  something so important. I also just prefer minimalistic designs so I think it would look better as well!
     *
     */
    public static void main(String[] args) {
        Part part1 = new InHouse(Inventory.getPartId(), "Cable bundle", 42.90, 120, 100, 1000, 15);
        Inventory.addPart(part1);

        Part part2 = new InHouse(Inventory.getPartId(), "Chipset", 199.99, 35, 10, 300, 13);
        Inventory.addPart(part2);

        Part part3 = new InHouse(Inventory.getPartId(), "Case", 105.50, 200, 5, 500, 5);
        Inventory.addPart(part3);

        Part part4 = new Outsourced(Inventory.getPartId(), "GPU", 599.95, 75, 50, 500, "LidDiva");
        Inventory.addPart(part4);

        Product gamingDesktop = new Product(Inventory.getProductId(), "Obliteration", 2495.99, 220, 100, 1000);
        Inventory.addProduct(gamingDesktop);

        Product gamingLaptop = new Product(Inventory.getProductId(), "Mobile Destruction", 1999.95, 450, 100, 750);
        Inventory.addProduct(gamingLaptop);


        launch();
    }
}