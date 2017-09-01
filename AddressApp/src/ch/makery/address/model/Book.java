package ch.makery.address.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * book model class
 *
 * @author Marco Jakob
 */
public class Book {

    private final StringProperty bookTitle;
    private final StringProperty ISBN;
    private final StringProperty Author;
    private final IntegerProperty bookClassification;
    private final StringProperty Publisher;
    private final ObjectProperty<LocalDate> BookSince;

    /**
     * defalu.t Constructor
     */
    public Book() {
        this(null);
    }

    /**
     * Constructor to initialize data
     * @param BookTitle
     * 
     */
    public Book(String BookTitle) {
        this.bookTitle = new SimpleStringProperty(BookTitle);
        this.ISBN = new SimpleStringProperty("");
        // initialize data
        this.Author = new SimpleStringProperty("");
        this.bookClassification = new SimpleIntegerProperty();
        this.Publisher= new SimpleStringProperty("");
        this.BookSince = new SimpleObjectProperty<LocalDate>();
    }
    
    //getter and setter
    public String getBookTitle() {
        return bookTitle.get();
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle.set(bookTitle);
    }

    public StringProperty bookTitleProperty() {
        return bookTitle;
    }

    public String getISBN() {
        return ISBN.get();
    }

    public void setISBN(String ISBN) {
        this.ISBN.set(ISBN);
    }

    public StringProperty ISBNProperty() {
        return ISBN;
    }

    public String getAuthor() {
        return Author.get();
    }

    public void setAuthor(String Author) {
        this.Author.set(Author);
    }

    public StringProperty AuthorProperty() {
        return Author;
    }

    public int getbookClassification() {
        return bookClassification.get();
    }

    public void setbookClassification(int bookClassification) {
        this.bookClassification.set(bookClassification);
    }

    public IntegerProperty bookClassification() {
        return bookClassification;
    }

    public String getPublisher() {
        return Publisher.get();
    }

    public void setPublisher(String Publisher) {
        this.Publisher.set(Publisher);
    }

    public StringProperty PublisherProperty() {
        return Publisher;
    }

    public LocalDate getBookSince() {
        return BookSince.get();
    }

    public void setBookSince(LocalDate BookSince) {
        this.BookSince.set(BookSince);
    }

    public ObjectProperty<LocalDate> BookSinceProperty() {
        return BookSince;
    }
}