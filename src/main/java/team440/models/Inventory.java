package team440.models;


import team440.models.Book;

import java.util.List;

public class Inventory {
    private List<Book> books;

    public Inventory() {
        books = new List<Book>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void updateBookQuantity(Book book, int quantity) {
        book.setQuantity(quantity);
    }

    public List<Book> getBooks() {
        return books;
    }
}
