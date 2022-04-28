package org.example.store.populator;

import org.example.domain.categories.Category;
import org.example.domain.Product;
import org.example.store.xml.XMLParser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DBPopulator implements Populator {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "root", "");
    }

    @Override
    public void generateProducts() {
        createDB();
        for (Category category : store.getCategories()) {
            for (int i = 0; i < 10; i++) {
                addProduct(generateProduct(category));
            }
        }
    }

    @Override
    public void sort() {
        StringBuilder statement = new StringBuilder("select p.id, p.name, p.rate, p.price, c.name from products as p" +
                " left join categories as c on p.category_id = c.id order by ");
        for (Map.Entry<String, String> entry : XMLParser.getConfig().entrySet()) {
            statement.append("p.").append(entry.getKey()).append(" ").append(entry.getValue()).append(", ");
        }
        statement.delete(statement.length() - 2, statement.length());
        getBy(statement.toString()).forEach(System.out::println);
    }

    @Override
    public void top() {
        String statement = """
                select p.id, p.name, p.rate, p.price, c.name from products as p
                left join categories as c on p.category_id = c.id order by p.price desc limit 5""";
        getBy(statement).forEach(System.out::println);
    }

    @Override
    public List<Product> getAllProducts() {
        String statement = """
                select p.id, p.name, p.rate, p.price, c.name from products as p
                left join categories as c on p.category_id = c.id""";
        return getBy(statement);
    }

    @Override
    public void removeProducts(List<Product> products) {
        try {
            Connection connection = getConnection();
            for (Product product : products) {
                String statement = String.format("delete from products where id = %s", product.getId());
                connection.prepareStatement(statement).executeUpdate();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void createDB() {
        try {
            Connection connection = getConnection();
            String statement = """
                    drop table if exists products; drop table if exists categories;
                    create table if not exists categories (id identity not null primary key,
                    name varchar unique not null);
                    create table if not exists products (id identity not null primary key, name varchar not null,
                    rate int not null, price double not null, category_id int not null,
                    foreign key (category_id) references categories)""";
            connection.prepareStatement(statement).executeUpdate();
            for (Category category : store.getCategories()) {
                connection.prepareStatement(String.format("insert into categories (name) values ('%s')",
                        category.getName())).executeUpdate();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getCategory(Category category) {
        int id = 0;
        try {
            Connection connection = getConnection();
            String statement = String.format("select id from categories where name = '%s'", category.getName());
            ResultSet resultSet = connection.prepareStatement(statement).executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    private void addProduct(Product product) {
        try {
            Connection connection = getConnection();
            String statement = String.format(Locale.US,
                    "insert into products (name, rate, price, category_id) values ('%s', %d, %f, %d)",
                    product.getName().replace("'", ""), product.getRate(),
                    product.getPrice(), new DBPopulator().getCategory(product.getCategory()));
            connection.prepareStatement(statement).executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Product> getBy(String statement) {
        List<Product> list = new ArrayList<>();
        try {
            Connection connection = getConnection();
            ResultSet resultSet = connection.prepareStatement(statement).executeQuery();
            while (resultSet.next()) {
                Category category = null;
                for (Category current : store.getCategories()) {
                    if (current.check(resultSet.getString(5))) {
                        category = current;
                    }
                }
                Product product = new Product(resultSet.getString(2), resultSet.getInt(3),
                        resultSet.getDouble(4), category);
                product.setId(resultSet.getInt(1));
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
