package team440.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static team440.OnlineBookStoreAPI.connection;

public class Book {
    private final int id;
    private final String title;
    private final String author;
    private final int year;
    private final double price;
    private final int quantity;

    public Book(int id, String title, String author, int year, double price, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
    }

    public static List<Book> find() throws SQLException {
        try (var statement = connection.createStatement()) {
            var books = new ArrayList<Book>();
            try (var resultSet = statement.executeQuery("SELECT * FROM books")) {
                while (resultSet.next()) {
                    books.add(new Book(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getInt("year"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("quantity")
                    ));
                }
            }
            return books;
        }

    }

    public static void delete(int id) throws SQLException {
        try (var statement = connection.prepareStatement("DELETE FROM books WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public void insert() throws SQLException {
        try (var statement = connection.prepareStatement(
                "INSERT INTO books (title, author, year, price, quantity) VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, this.title);
            statement.setString(2, this.author);
            statement.setInt(3, this.year);
            statement.setDouble(4, this.price);
            statement.setInt(5, this.quantity);
            statement.executeUpdate();
        }
    }
}
