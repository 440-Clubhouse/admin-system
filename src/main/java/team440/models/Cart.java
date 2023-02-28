package team440.models;

import team440.models.Book;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Book> books;

    public Cart() {
        books = new ArrayList<Book>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Book book : books) {
            totalPrice += book.getPrice();
        }
        return totalPrice;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }
}
