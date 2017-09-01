package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Book;
import ch.makery.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BookOverviewController {
    @FXML
    private TableView<Book> personTable;
    @FXML
    private TableColumn<Book, String> bookTitlteColumn;

    @FXML
    private Label bookTitleLabel;
    @FXML
    private Label ISBNLabel;
    @FXML
    private Label AuthorLabel;
    @FXML
    private Label PublisherLabel;
    @FXML
    private Label bookClassificationLabel;
    @FXML
    private Label BookSinceLabel;
    
    // mainApp 
    private MainApp mainApp;

    /**
     * Constructor
     * initialize() 
     */
    
    public BookOverviewController() {
    }


    @FXML
    private void showPersonDetails(Book book) {
        if (book != null) {
            // book Fill the label with information as an object.
        	bookTitleLabel.setText(book.getBookTitle());
        	ISBNLabel.setText(book.getISBN());
        	AuthorLabel.setText(book.getAuthor());
        	bookClassificationLabel.setText(Integer.toString(book.getbookClassification()));
            PublisherLabel.setText(book.getPublisher());
            BookSinceLabel.setText(DateUtil.format(book.getBookSince()));
            
        } else {
            // If book is null, all text is erased.
        	bookTitleLabel.setText("");
        	ISBNLabel.setText("");
        	AuthorLabel.setText("");
            PublisherLabel.setText("");
            bookClassificationLabel.setText("");
            BookSinceLabel.setText("");
        }
    }

    @FXML
    private void initialize() { 
    	bookTitlteColumn.setCellValueFactory(cellData -> cellData.getValue().bookTitleProperty());
        
     // 연락처 정보를 지운다.
        showPersonDetails(null);

        // Detects the selection and displays the contact details each time.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }
    /**
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table.
        personTable.setItems(mainApp.getPersonData());
    }
    
    /**
     * 트롤러의 어느 메서드라도 @FXML 어노테이션 (또는 public)이 붙어 있으면 Scene Builder에서 이용할 수 있습니다)
     */
    /**
     * Called when the user clicks the new button.
     * Opens a dialog to insert new contact information.
     */
    @FXML
    private void handleNewPerson() {
        Book tempPerson = new Book();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }

    /**
     * called when the user clicks the edit button.
     * Opens a dialog to change the selected contact information.
     */
    @FXML
    private void handleEditPerson() {
        Book selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // did not choose anything.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Book Selected");
            alert.setContentText("Please select a Book in the table.");
            alert.showAndWait();
        }
    }
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            // did not choose anything.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Book Selected");
            alert.setContentText("Please select a book in the table.");

            alert.showAndWait();
        }
    }
}