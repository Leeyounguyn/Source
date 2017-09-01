package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ch.makery.address.model.Book;
import ch.makery.address.util.DateUtil;

/**
 * Book Dialog to change information
 *
 */
public class BookEditDialogController {

    @FXML
    private TextField bookTitleField;
    @FXML
    private TextField ISBNField;
    @FXML
    private TextField AuthorLabelField;
    @FXML
    private TextField PublisherField;
    @FXML
    private TextField bookClassificationField;
    @FXML
    private TextField BookSinceField;


    private Stage dialogStage;
    private Book book;
    private boolean okClicked = false;

    /**
     * Initialize the controller class.
     *     This method is called automatically after the fxml file is loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * dialogStage set
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Set the contact to be changed in the dialog.
     *
     * @param book
     */
    public void setPerson(Book book) {
        this.book = book;

        bookTitleField.setText(book.getBookTitle());
        ISBNField.setText(book.getISBN());
        AuthorLabelField.setText(book.getAuthor());
        bookClassificationField.setText(Integer.toString(book.getbookClassification()));
        PublisherField.setText(book.getPublisher());
        BookSinceField.setText(DateUtil.format(book.getBookSince()));
        BookSinceField.setPromptText("dd.mm.yyyy");
    }

    /**
     * if user click @Ok true else false.
     *
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    
    /**
     * 사용자가 OK를 클릭하면 호출된다.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	//person.setFirstName(bookTitleField.getText());
        	book.setBookTitle(bookTitleField.getText());
            book.setISBN(ISBNField.getText());
            book.setAuthor(AuthorLabelField.getText());
            book.setbookClassification(Integer.parseInt(bookClassificationField.getText()));
            book.setPublisher(PublisherField.getText());
            book.setBookSince(DateUtil.parse(BookSinceField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * user call cancel
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (bookTitleField.getText() == null || bookTitleField.getText().length() == 0) {
            errorMessage += "No valid book Title!\n";
        }
        if (ISBNField.getText() == null || ISBNField.getText().length() == 0) {
            errorMessage += "No valid ISBN!\n";
        }
        if (AuthorLabelField.getText() == null || AuthorLabelField.getText().length() == 0) {
            errorMessage += "No valid Author!\n";
        }

        if (bookClassificationField.getText() == null || bookClassificationField.getText().length() == 0) {
            errorMessage += "No valid book Classification!\n";
        } else {
            // 우편 번호를 int 타입으로 변환한다.
            try {
                Integer.parseInt(bookClassificationField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid bookclassification (must be an integer)!\n";
            }
        }

        if (PublisherField.getText() == null || PublisherField.getText().length() == 0) {
            errorMessage += "No valid Publisher!\n";
        }

        if (BookSinceField.getText() == null || BookSinceField.getText().length() == 0) {
            errorMessage += "No valid book Since!\n";
        } else {
            if (!DateUtil.validDate(BookSinceField.getText())) {
                errorMessage += "No valid book Since. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // 오류 메시지를 보여준다.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}