package team440.models;

import team440.models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model {


    public OnlineBookStoreDatabase() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/online_bookstore", "root", "password");
        createTables();
    }

    public void createTables() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Customer (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50) NOT NULL, email VARCHAR(50) NOT NULL, address VARCHAR(50) NOT NULL)");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Cart (id INT PRIMARY KEY AUTO_INCREMENT, customer_id INT NOT NULL, book_id INT NOT NULL, quantity INT NOT NULL, FOREIGN KEY (customer_id) REFERENCES Customer(id), FOREIGN KEY (book_id) REFERENCES Book(id))");
    }
}