package ch.makery.address;

import java.io.IOException;

import ch.makery.address.model.Book;
import ch.makery.address.view.BookEditDialogController;
import ch.makery.address.view.BookOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {
    private ObservableList<Book> personData = FXCollections.observableArrayList();

    //Constructor 
    public MainApp() {
        // simple data
        personData.add(new Book("Hans"));
    }
    /**
     *  observable return
     */
    public ObservableList<Book> getPersonData() {
        return personData;
    }

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showPersonOverview();
    }


    public void initRootLayout() {
        try {
            // fxml Bring top layout
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // top layout show scene
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show the book overview in the top layout.
     */
    public void showPersonOverview() {
        try {
            // book overview
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/BookOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set book overview to the middle of the top layout.
            
            rootLayout.setCenter(personOverview);
            
            BookOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Opens the dialog to change the detailed information of the book.
     * return true if the user clicked OK, false otherwise.
     * 
     */
    public boolean showPersonEditDialog(Book person) {
        try {
            // Load the fxml file and create a new stage.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/BookEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // make dialogStage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Book");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // book controller set
            BookEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // show dialogStage and wait to user close
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 
     * @return main Stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}