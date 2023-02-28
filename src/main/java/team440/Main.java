package team440;

import io.javalin.Javalin;

import team440.models.Book;
import team440.models.Customer;

import java.sql.Connection;

public class OnlineBookStoreAPI {
    private static OnlineBookStore store = new OnlineBookStore();
    static public Connection connection;

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);
        // 添加书籍
        app.post("/books", ctx -> {
            String title = ctx.formParam("title");
            String author = ctx.formParam("author");
            int year = ctx.formParam("year", Integer.class).get();
            double price = ctx.formParam("price", Double.class).get();
            int quantity = ctx.formParam("quantity", Integer.class).get();
            Book book = new Book(id, title, author, year, price, quantity);
            store.addBook(book);
            ctx.status(201);
        });

        // 获取所有书籍
        app.get("/books", ctx -> {
            ctx.json(store.getBooks());
        });

        // 添加客户
        app.post("/customers", ctx -> {
            String name = ctx.formParam("name");
            String email = ctx.formParam("email");
            String address = ctx.formParam("address");
            Customer customer = new Customer(name, email, address);
            store.addCustomer(customer);
            ctx.status(201);
        });

        // 获取所有客户
        app.get("/customers", ctx -> {
            ctx.json(store.getCustomers());
        });

        // 添加书籍到购物车
        app.post("/customers/:customerId/cart", ctx -> {
            int customerId = ctx.pathParam("customerId", Integer.class).get();
            int bookId = ctx.formParam("bookId", Integer.class).get();
            int quantity = ctx.formParam("quantity", Integer.class).get();
            Customer customer = store.getCustomers().get(customerId);
            Book book = store.getBooks().get(bookId);
            customer.getCart().addBook(book, quantity);
            ctx.status(201);
        });

        // 获取客户购物车
        app.get("/customers/:customerId/cart", ctx -> {
            int customerId = ctx.pathParam("customerId", Integer.class).get();
            Customer customer = store.getCustomers().get(customerId);
            ctx.json(customer.getCart().getItems());
        });

        // 获取总销售额
        app.get("/sales", ctx -> {
            double totalSales = store.calculateTotalSales();
            ctx.result("Total sales: $" + totalSales);
        });
    }
}



class Test {
    public static void main(String[] args) {
        OnlineBookStore store = new OnlineBookStore();

        Book book1 = new Book(id, "Java Programming", "John Smith", 2022, 29.99, 10);
        Book book2 = new Book(id, "Python Programming", "Alice Brown", 2021, 24.99, 20);
        Book book3 = new Book(id, "C++ Programming", "Bob Johnson", 2020, 19.99, 15);

        store.addBook(book1);
        store.addBook(book2);
        store.addBook(book3);

        Customer customer1 = new Customer("Tom", "tom@example.com", "123 Main St");
        Customer customer2 = new Customer("Mary", "mary@example.com", "456 Second St");

        store.addCustomer(customer1);
        store.addCustomer(customer2);

        customer1.getCart().addBook(book1);
        customer1.getCart().addBook(book2);

        customer2.getCart().addBook(book2);
        customer2.getCart().addBook(book3);

        System.out.println("Total sales: $" + store.calculateTotalSales());
    }
}
