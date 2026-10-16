package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.DatabaseConnection;
import models.Product;

public class ProductService {

    private ArrayList<Product> products;

    // ==========================================
    // CONSTRUCTOR
    // ==========================================

    public ProductService() {

        products = new ArrayList<>();

        loadProductsFromDatabase();
    }

    // ==========================================
    // LOAD PRODUCTS FROM MYSQL
    // ==========================================

    private void loadProductsFromDatabase() {

        String sql = "SELECT product_id, product_name, category, "
                   + "price, quantity FROM products";

        try (Connection connection =
                     DatabaseConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql);

             ResultSet resultSet =
                     statement.executeQuery()) {

            products.clear();

            while (resultSet.next()) {

                Product product = new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getString("category"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity")
                );

                products.add(product);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error loading products from MySQL.");

            e.printStackTrace();
        }
    }

 // ==========================================
 // ADD PRODUCT TO MYSQL
 // ==========================================

 public boolean addProduct(Product product) {

     if (product == null
             || product.getProductName() == null
             || product.getProductName().trim().isEmpty()
             || product.getCategory() == null
             || product.getCategory().trim().isEmpty()
             || product.getPrice() < 0
             || product.getQuantity() < 0) {

         return false;
     }

     String sql = "INSERT INTO products "
             + "(product_name, category, price, quantity) "
             + "VALUES (?, ?, ?, ?)";

     try (Connection connection =
                  DatabaseConnection.getConnection();

          PreparedStatement statement =
                  connection.prepareStatement(
                          sql,
                          java.sql.Statement.RETURN_GENERATED_KEYS)) {

         statement.setString(
                 1, product.getProductName());

         statement.setString(
                 2, product.getCategory());

         statement.setDouble(
                 3, product.getPrice());

         statement.setInt(
                 4, product.getQuantity());

         int rows = statement.executeUpdate();

         if (rows > 0) {

             loadProductsFromDatabase();

             System.out.println(
                     "Product added successfully to MySQL!");

             return true;
         }

     } catch (SQLException e) {

         System.out.println(
                 "Error adding product to MySQL.");

         e.printStackTrace();
     }

     return false;
 }

    // ==========================================
    // VIEW PRODUCTS
    // ==========================================

    public void displayProducts() {

        loadProductsFromDatabase();

        if (products.isEmpty()) {

            System.out.println(
                    "No products available.");

            return;
        }

        System.out.println();

        System.out.println(
                "----------- PRODUCT LIST -----------");

        for (Product product : products) {

            System.out.println(product);
        }
    }

    // ==========================================
    // FIND PRODUCT BY ID
    // ==========================================

    public Product findProductById(int productId) {

        String sql = "SELECT product_id, product_name, category, "
                   + "price, quantity FROM products "
                   + "WHERE product_id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, productId);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    return new Product(
                            resultSet.getInt("product_id"),
                            resultSet.getString("product_name"),
                            resultSet.getString("category"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("quantity")
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error searching for product.");

            e.printStackTrace();
        }

        return null;
    }

    // ==========================================
    // UPDATE COMPLETE PRODUCT
    // ==========================================

    public boolean updateProduct(
            int productId,
            String productName,
            String category,
            double price,
            int quantity) {

        if (productName == null
                || productName.trim().isEmpty()
                || category == null
                || category.trim().isEmpty()
                || price < 0
                || quantity < 0) {

            return false;
        }

        String sql = "UPDATE products SET "
                   + "product_name = ?, "
                   + "category = ?, "
                   + "price = ?, "
                   + "quantity = ? "
                   + "WHERE product_id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, productName);
            statement.setString(2, category);
            statement.setDouble(3, price);
            statement.setInt(4, quantity);
            statement.setInt(5, productId);

            int rows = statement.executeUpdate();

            if (rows > 0) {

                loadProductsFromDatabase();

                return true;
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error updating product.");

            e.printStackTrace();
        }

        return false;
    }

    // ==========================================
    // UPDATE ONLY QUANTITY
    // ==========================================

    public boolean updateQuantity(
            int productId,
            int quantity) {

        if (quantity < 0) {
            return false;
        }

        String sql = "UPDATE products SET quantity = ? "
                   + "WHERE product_id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, quantity);
            statement.setInt(2, productId);

            int rows = statement.executeUpdate();

            if (rows > 0) {

                loadProductsFromDatabase();

                return true;
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error updating product quantity.");

            e.printStackTrace();
        }

        return false;
    }

    // ==========================================
    // DELETE PRODUCT
    // ==========================================

    public boolean deleteProduct(int productId) {

        String sql = "DELETE FROM products "
                   + "WHERE product_id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, productId);

            int rows = statement.executeUpdate();

            if (rows > 0) {

                loadProductsFromDatabase();

                return true;
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error deleting product.");

            System.out.println(
                    "Check whether inventory or orders reference this product.");

            e.printStackTrace();
        }

        return false;
    }

    // ==========================================
    // CHECK PRODUCT EXISTS
    // ==========================================

    public boolean productExists(int productId) {

        return productExistsInDatabase(productId);
    }

    // ==========================================
    // CHECK PRODUCT EXISTS IN MYSQL
    // ==========================================

    private boolean productExistsInDatabase(
            int productId) {

        String sql = "SELECT 1 FROM products "
                   + "WHERE product_id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, productId);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                return resultSet.next();
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error checking product existence.");

            e.printStackTrace();
        }

        return false;
    }

    // ==========================================
    // GET ALL PRODUCTS
    // ==========================================

    public ArrayList<Product> getProducts() {

        loadProductsFromDatabase();

        return new ArrayList<>(products);
    }

    // ==========================================
    // GET PRODUCT COUNT
    // ==========================================

    public int getProductCount() {

        String sql = "SELECT COUNT(*) FROM products";

        try (Connection connection =
                     DatabaseConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql);

             ResultSet resultSet =
                     statement.executeQuery()) {

            if (resultSet.next()) {

                return resultSet.getInt(1);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error counting products.");

            e.printStackTrace();
        }

        return 0;
    }
}