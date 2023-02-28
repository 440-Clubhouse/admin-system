package team440;

import team440.models.Book;
import team440.models.Customer;
import team440.models.Inventory;

import java.util.ArrayList;

public class OnlineBookStore {
    private Inventory inventory;
    private ArrayList<Customer> customers;

    public OnlineBookStore() {
        inventory = new Inventory();
        customers = new ArrayList<Customer>();
    }

    public void addBook(Book book) {
        inventory.addBook(book);
    }

    public void removeBook(Book book) {
        inventory.removeBook(book);
    }

    public void updateBookQuantity(Book book, int quantity) {
        inventory.updateBook(book, quantity);
    }

    public ArrayList<Book> getBooks() {
        return inventory.getBooks();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public double calculateTotalSales() {
        double totalSales = 0;
        for (Customer customer : customers) {
            totalSales += customer.getCart().getTotalPrice();
        }
        return totalSales;
    }
}
