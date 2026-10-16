package app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import models.Farmer;
import models.Supplier;
import models.Product;
import models.Warehouse;
import models.DistributionCenter;
import models.QualityReport;
import models.Customer;
import models.Order;
import models.Shipment;
import models.Inventory;

import services.ProductService;
import services.SupplierService;
import services.InventoryService;
import services.DistributionService;
import services.SearchService;
import services.CorrectionService;
import services.DocumentService;
import services.MatchingService;
import services.ConstraintService;
import services.AnalyticsService;

public class FoodChainApp {

// =====================================================
// SCANNER
// =====================================================

private static Scanner scanner = new Scanner(System.in);

// =====================================================
// SERVICES
// =====================================================

private static ProductService productService =
        new ProductService();

private static SupplierService supplierService =
        new SupplierService();

private static InventoryService inventoryService =
        new InventoryService();

private static DistributionService distributionService =
        new DistributionService();

private static SearchService searchService =
        new SearchService();

private static CorrectionService correctionService =
        new CorrectionService();

private static DocumentService documentService =
        new DocumentService();

private static MatchingService matchingService =
        new MatchingService();

private static ConstraintService constraintService =
        new ConstraintService();

private static AnalyticsService analyticsService =
        new AnalyticsService();

// =====================================================
// OTHER DATA
// =====================================================

private static ArrayList<Farmer> farmers =
        new ArrayList<>();

private static ArrayList<Customer> customers =
        new ArrayList<>();

private static ArrayList<Order> orders =
        new ArrayList<>();

private static ArrayList<Shipment> shipments =
        new ArrayList<>();

private static ArrayList<QualityReport> qualityReports =
        new ArrayList<>();

// =====================================================
// LOGIN DATA
// =====================================================

private static ArrayList<String> usernames =
        new ArrayList<>();

private static ArrayList<String> passwords =
        new ArrayList<>();

// =====================================================
// MAIN
// =====================================================

public static void main(String[] args) {

    loadLoginData();
    loadSampleData();

    System.out.println();
    System.out.println("==========================================");
    System.out.println("              FOODCHAIN");
    System.out.println("   FOOD SUPPLY & DISTRIBUTION PLATFORM");
    System.out.println("==========================================");

    while (true) {

        System.out.println();
        System.out.println("--------------- LOGIN ----------------");
        System.out.println("1. Admin Login");
        System.out.println("2. User Login");
        System.out.println("3. Register User");
        System.out.println("4. Exit");
        System.out.println("--------------------------------------");

        int choice = readInt("Enter choice: ");

        switch (choice) {

            case 1:
                adminLogin();
                break;

            case 2:
                userLogin();
                break;

            case 3:
                registerUser();
                break;

            case 4:
                System.out.println(
                        "Thank you for using FoodChain.");
                scanner.close();
                return;

            default:
                System.out.println(
                        "Invalid choice.");
        }
    }
}

// =====================================================
// LOGIN DATA
// =====================================================

private static void loadLoginData() {

    // Default admin
    usernames.add("admin");
    passwords.add("admin123");

    // Default user
    usernames.add("user");
    passwords.add("user123");
}

// =====================================================
// SAMPLE DATA
// =====================================================

private static void loadSampleData() {

    // ---------------- FARMERS ----------------

    farmers.add(new Farmer(
            1,
            "Ravi Farmers",
            "Nandigama",
            "9876543210"));

    farmers.add(new Farmer(
            2,
            "Green Farms",
            "Vijayawada",
            "9876501234"));

    // ---------------- SUPPLIERS ----------------

    supplierService.addSupplier(
            new Supplier(
                    1,
                    "Fresh Suppliers",
                    "Vijayawada",
                    "9000011111"));

    supplierService.addSupplier(
            new Supplier(
                    2,
                    "Agro Suppliers",
                    "Guntur",
                    "9000022222"));

    // ---------------- PRODUCTS ----------------

    productService.addProduct(
            new Product(
                    101,
                    "Fresh Rice",
                    "Grains",
                    60.0,
                    500));

    productService.addProduct(
            new Product(
                    102,
                    "Brown Rice",
                    "Grains",
                    80.0,
                    300));

    productService.addProduct(
            new Product(
                    103,
                    "Fresh Apple",
                    "Fruits",
                    120.0,
                    200));

    productService.addProduct(
            new Product(
                    104,
                    "Tomato",
                    "Vegetables",
                    40.0,
                    250));

    // ---------------- WAREHOUSES ----------------

    distributionService.addWarehouse(
            new Warehouse(
                    1,
                    "Main Warehouse",
                    "Vijayawada",
                    1000));

    distributionService.addWarehouse(
            new Warehouse(
                    2,
                    "Central Warehouse",
                    "Guntur",
                    1500));

    // ---------------- DISTRIBUTION CENTERS ----------------

    distributionService.addDistributionCenter(
            new DistributionCenter(
                    1,
                    "Vijayawada DC",
                    "Vijayawada",
                    2000));

    distributionService.addDistributionCenter(
            new DistributionCenter(
                    2,
                    "Guntur DC",
                    "Guntur",
                    2500));

    // ---------------- INVENTORY ----------------

    inventoryService.addInventory(
            new Inventory(
                    1,
                    1,
                    101,
                    500,
                    100));

    inventoryService.addInventory(
            new Inventory(
                    2,
                    1,
                    102,
                    300,
                    75));

    inventoryService.addInventory(
            new Inventory(
                    3,
                    2,
                    103,
                    200,
                    50));

    inventoryService.addInventory(
            new Inventory(
                    4,
                    2,
                    104,
                    250,
                    60));

    // ---------------- CUSTOMERS ----------------

    customers.add(new Customer(
            1,
            "Demo Customer",
            "Nandigama",
            "9123456789"));

    // ---------------- QUALITY REPORTS ----------------

    qualityReports.add(
            new QualityReport(
                    1,
                    101,
                    "2026-09-20",
                    "Good",
                    "Fresh rice passed quality inspection."));

    qualityReports.add(
            new QualityReport(
                    2,
                    103,
                    "2026-09-20",
                    "Excellent",
                    "Fresh apples passed quality inspection."));

    // ---------------- DOCUMENTS ----------------

    documentService.addDocument(
            "Fresh Rice - High quality food grain.");

    documentService.addDocument(
            "Brown Rice - Healthy grain product.");

    documentService.addDocument(
            "Apple - Fresh fruit product.");

    documentService.addDocument(
            "Tomato - Fresh vegetable product.");
}

// =====================================================
// ADMIN LOGIN
// =====================================================

private static void adminLogin() {

    System.out.println();
    System.out.println("=========== ADMIN LOGIN ===========");

    String username =
            readString("Username: ");

    String password =
            readString("Password: ");

    if (username.equals("admin")
            && password.equals("admin123")) {

        System.out.println(
                "Admin login successful.");

        adminMenu();

    } else {

        System.out.println(
                "Invalid admin username or password.");
    }
}

// =====================================================
// USER LOGIN
// =====================================================

private static void userLogin() {

    System.out.println();
    System.out.println("============ USER LOGIN ============");

    String username =
            readString("Username: ");

    String password =
            readString("Password: ");

    int index = findUser(username);

    if (index != -1 &&
            passwords.get(index).equals(password)) {

        System.out.println(
                "User login successful.");

        userMenu(username);

    } else {

        System.out.println(
                "Invalid username or password.");
    }
}

// =====================================================
// REGISTER USER
// =====================================================

private static void registerUser() {

    System.out.println();
    System.out.println("========== USER REGISTRATION ==========");

    String username =
            readString("Create username: ");

    if (username.isEmpty()) {

        System.out.println(
                "Username cannot be empty.");

        return;
    }

    if (findUser(username) != -1) {

        System.out.println(
                "Username already exists.");

        return;
    }

    String password =
            readString("Create password: ");

    if (password.isEmpty()) {

        System.out.println(
                "Password cannot be empty.");

        return;
    }

    usernames.add(username);
    passwords.add(password);

    int customerId = getNextCustomerId();

    customers.add(
            new Customer(
                    customerId,
                    username,
                    "Not Provided",
                    "Not Provided"));

    System.out.println(
            "Registration successful.");

    System.out.println(
            "Your Customer ID is: "
            + customerId);
}

// =====================================================
// FIND USER
// =====================================================

private static int findUser(String username) {

    for (int i = 0;
            i < usernames.size();
            i++) {

        if (usernames.get(i)
                .equals(username)) {

            return i;
        }
    }

    return -1;
}

// =====================================================
// ADMIN MENU
// =====================================================

private static void adminMenu() {

    while (true) {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("              ADMIN DASHBOARD");
        System.out.println("==========================================");

        System.out.println("1. Farmer Management");
        System.out.println("2. Supplier Management");
        System.out.println("3. Product Management");
        System.out.println("4. Customer Management");
        System.out.println("5. Order Management");
        System.out.println("6. Marketing");
        System.out.println("7. Warehouse Management");
        System.out.println("8. Distribution Center Management");
        System.out.println("9. Inventory Management");
        System.out.println("10. Quality Reports");
        System.out.println("11. Shipment Management");
        System.out.println("12. Analytics");
        System.out.println("13. DSA Search");
        System.out.println("14. DSA Algorithms");
        System.out.println("15. Logout");

        int choice =
                readInt("Enter choice: ");

        switch (choice) {

            case 1:
                farmerMenu();
                break;

            case 2:
                supplierMenu();
                break;

            case 3:
                productMenu();
                break;

            case 4:
                customerMenu();
                break;

            case 5:
                orderMenu();
                break;

            case 6:
                marketingMenu();
                break;

            case 7:
                warehouseMenu();
                break;

            case 8:
                distributionCenterMenu();
                break;

            case 9:
                inventoryMenu();
                break;

            case 10:
                qualityReportMenu();
                break;

            case 11:
                shipmentMenu();
                break;

            case 12:
                analyticsMenu();
                break;

            case 13:
                dsaSearchMenu();
                break;

            case 14:
                dsaAlgorithmMenu();
                break;

            case 15:
                System.out.println(
                        "Admin logged out.");

                return;

            default:
                System.out.println(
                        "Invalid choice.");
        }
    }
}

// =====================================================
// USER MENU
// =====================================================

private static void userMenu(String username) {

    int customerId =
            getCustomerIdByUsername(username);

    while (true) {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("               USER DASHBOARD");
        System.out.println("==========================================");

        System.out.println("1. View Products");
        System.out.println("2. Search Products");
        System.out.println("3. View Suppliers");
        System.out.println("4. View Inventory");
        System.out.println("5. Place Order");
        System.out.println("6. My Orders");
        System.out.println("7. Cancel Order");
        System.out.println("8. Track Shipment");
        System.out.println("9. View Quality Reports");
        System.out.println("10. Marketing");
        System.out.println("11. Correct Product Name");
        System.out.println("12. Logout");

        int choice =
                readInt("Enter choice: ");

        switch (choice) {

            case 1:
                productService.displayProducts();
                break;

            case 2:
                searchProducts();
                break;

            case 3:
                supplierService.displaySuppliers();
                break;

            case 4:
                inventoryService.displayInventory();
                break;

            case 5:
                placeOrder(customerId);
                break;

            case 6:
                viewMyOrders(customerId);
                break;

            case 7:
                cancelOrder(customerId);
                break;

            case 8:
                trackShipment(customerId);
                break;

            case 9:
                displayQualityReports();
                break;

            case 10:
                marketingMenuUser();
                break;

            case 11:
                correctProductName();
                break;

            case 12:
                System.out.println(
                        "User logged out.");

                return;

            default:
                System.out.println(
                        "Invalid choice.");
        }
    }
}

// =====================================================
// FARMER MENU
// =====================================================

private static void farmerMenu() {

    while (true) {

        System.out.println();
        System.out.println("========== FARMER MANAGEMENT ==========");
        System.out.println("1. Add Farmer");
        System.out.println("2. View Farmers");
        System.out.println("3. Update Farmer");
        System.out.println("4. Delete Farmer");
        System.out.println("5. Back");

        int choice =
                readInt("Enter choice: ");

        switch (choice) {

            case 1:
                addFarmer();
                break;

            case 2:
                displayFarmers();
                break;

            case 3:
                updateFarmer();
                break;

            case 4:
                deleteFarmer();
                break;

            case 5:
                return;

            default:
                System.out.println(
                        "Invalid choice.");
        }
    }
}

// =====================================================
// ADD FARMER
// =====================================================

private static void addFarmer() {

    int id =
            readInt("Farmer ID: ");

    if (findFarmer(id) != null) {

        System.out.println(
                "Farmer ID already exists.");

        return;
    }

    String name =
            readString("Farmer Name: ");

    String location =
            readString("Location: ");

    String contact =
            readString("Contact Number: ");

    farmers.add(
            new Farmer(
                    id,
                    name,
                    location,
                    contact));

    System.out.println(
            "Farmer added successfully.");
}

// =====================================================
// VIEW FARMERS
// =====================================================

private static void displayFarmers() {

    if (farmers.isEmpty()) {

        System.out.println(
                "No farmers available.");

        return;
    }

    System.out.println();
    System.out.println(
            "----------- FARMER LIST -----------");

    for (Farmer farmer : farmers) {

        System.out.println(farmer);
    }
}

// =====================================================
// FIND FARMER
// =====================================================

private static Farmer findFarmer(int id) {

    for (Farmer farmer : farmers) {

        if (farmer.getFarmerId() == id) {
            return farmer;
        }
    }

    return null;
}

// =====================================================
// UPDATE FARMER
// =====================================================

private static void updateFarmer() {

    int id =
            readInt("Enter Farmer ID: ");

    Farmer farmer =
            findFarmer(id);

    if (farmer == null) {

        System.out.println(
                "Farmer not found.");

        return;
    }

    String name =
            readString("New Name: ");

    String location =
            readString("New Location: ");

    String contact =
            readString("New Contact: ");

    farmer.setFarmerName(name);
    farmer.setLocation(location);
    farmer.setContactNumber(contact);

    System.out.println(
            "Farmer updated successfully.");
}

// =====================================================
// DELETE FARMER
// =====================================================

private static void deleteFarmer() {

    int id =
            readInt("Enter Farmer ID: ");

    Farmer farmer =
            findFarmer(id);

    if (farmer == null) {

        System.out.println(
                "Farmer not found.");

        return;
    }

    farmers.remove(farmer);

    System.out.println(
            "Farmer deleted successfully.");
}

// =====================================================
// SUPPLIER MENU
// =====================================================

private static void supplierMenu() {

    while (true) {

        System.out.println();
        System.out.println("========= SUPPLIER MANAGEMENT =========");
        System.out.println("1. Add Supplier");
        System.out.println("2. View Suppliers");
        System.out.println("3. Update Supplier");
        System.out.println("4. Delete Supplier");
        System.out.println("5. Back");

        int choice =
                readInt("Enter choice: ");

        switch (choice) {

            case 1:
                addSupplier();
                break;

            case 2:
                supplierService.displaySuppliers();
                break;

            case 3:
                updateSupplier();
                break;

            case 4:
                deleteSupplier();
                break;

            case 5:
                return;

            default:
                System.out.println(
                        "Invalid choice.");
        }
    }
}

// =====================================================
// ADD SUPPLIER
// =====================================================

private static void addSupplier() {

    int id =
            readInt("Supplier ID: ");

    if (supplierService.supplierExists(id)) {

        System.out.println(
                "Supplier ID already exists.");

        return;
    }

    String name =
            readString("Supplier Name: ");

    String location =
            readString("Location: ");

    String contact =
            readString("Contact Number: ");

    boolean added =
            supplierService.addSupplier(
                    new Supplier(
                            id,
                            name,
                            location,
                            contact));

    if (added) {

        System.out.println(
                "Supplier added successfully.");

    } else {

        System.out.println(
                "Unable to add supplier.");
    }
}

// =====================================================
// UPDATE SUPPLIER
// =====================================================

private static void updateSupplier() {

    int id =
            readInt("Supplier ID: ");

    if (!supplierService.supplierExists(id)) {

        System.out.println(
                "Supplier not found.");

        return;
    }

    String name =
            readString("New Name: ");

    String location =
            readString("New Location: ");

    String contact =
            readString("New Contact: ");

    boolean updated =
            supplierService.updateSupplier(
                    id,
                    name,
                    location,
                    contact);

    if (updated) {

        System.out.println(
                "Supplier updated successfully.");

    } else {

        System.out.println(
                "Unable to update supplier.");
    }
}

// =====================================================
// DELETE SUPPLIER
// =====================================================

private static void deleteSupplier() {

    int id =
            readInt("Supplier ID: ");

    boolean deleted =
            supplierService.deleteSupplier(id);

    if (deleted) {

        System.out.println(
                "Supplier deleted successfully.");

    } else {

        System.out.println(
                "Supplier not found.");
    }
}

// =====================================================
// PRODUCT MENU
// =====================================================

private static void productMenu() {

    while (true) {

        System.out.println();
        System.out.println("========== PRODUCT MANAGEMENT ==========");
        System.out.println("1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Update Product");
        System.out.println("4. Delete Product");
        System.out.println("5. Back");

        int choice =
                readInt("Enter choice: ");

        switch (choice) {

            case 1:
                addProduct();
                break;

            case 2:
                productService.displayProducts();
                break;

            case 3:
                updateProduct();
                break;

            case 4:
                deleteProduct();
                break;

            case 5:
                return;

            default:
                System.out.println(
                        "Invalid choice.");
        }
    }
}

// =====================================================
// ADD PRODUCT
// =====================================================

private static void addProduct() {

    int id =
            readInt("Product ID: ");

    if (productService.productExists(id)) {

        System.out.println(
                "Product ID already exists.");

        return;
    }

    String name =
            readString("Product Name: ");

    String category =
            readString("Category: ");

    double price =
            readDouble("Price: ");

    int quantity =
            readInt("Quantity: ");

    if (price < 0 ||
            quantity < 0) {

        System.out.println(
                "Price and quantity cannot be negative.");

        return;
    }

    boolean added =
            productService.addProduct(
                    new Product(
                            id,
                            name,
                            category,
                            price,
                            quantity));

    if (added) {

        System.out.println(
                "Product added successfully.");

    } else {

        System.out.println(
                "Unable to add product.");
    }
}

// =====================================================
// UPDATE PRODUCT
// =====================================================

private static void updateProduct() {

    int id =
            readInt("Product ID: ");

    if (!productService.productExists(id)) {

        System.out.println(
                "Product not found.");

        return;
    }

    String name =
            readString("New Product Name: ");

    String category =
            readString("New Category: ");

    double price =
            readDouble("New Price: ");

    int quantity =
            readInt("New Quantity: ");

    boolean updated =
            productService.updateProduct(
                    id,
                    name,
                    category,
                    price,
                    quantity);

    if (updated) {

        System.out.println(
                "Product updated successfully.");

    } else {

        System.out.println(
                "Invalid product information.");
    }
}

// =====================================================
// DELETE PRODUCT
// =====================================================

private static void deleteProduct() {

    int id =
            readInt("Product ID: ");

    boolean deleted =
            productService.deleteProduct(id);

    if (deleted) {

        System.out.println(
                "Product deleted successfully.");

    } else {

        System.out.println(
                "Product not found.");
    }
}

// =====================================================
// CUSTOMER MENU
// =====================================================

private static void customerMenu() {

    while (true) {

        System.out.println();
        System.out.println("========= CUSTOMER MANAGEMENT =========");
        System.out.println("1. View Customers");
        System.out.println("2. Update Customer");
        System.out.println("3. Delete Customer");
        System.out.println("4. Back");

        int choice =
                readInt("Enter choice: ");

        switch (choice) {

            case 1:
                displayCustomers();
                break;

            case 2:
                updateCustomer();
                break;

            case 3:
                deleteCustomer();
                break;

            case 4:
                return;

            default:
                System.out.println(
                        "Invalid choice.");
        }
    }
}

// =====================================================
// VIEW CUSTOMERS
// =====================================================

private static void displayCustomers() {

    if (customers.isEmpty()) {

        System.out.println(
                "No customers available.");

        return;
    }

    System.out.println();
    System.out.println(
            "----------- CUSTOMER LIST -----------");

    for (Customer customer : customers) {

        System.out.println(customer);
    }
}

// =====================================================
// FIND CUSTOMER
// =====================================================

private static Customer findCustomer(
        int customerId) {

    for (Customer customer : customers) {

        if (customer.getCustomerId()
                == customerId) {

            return customer;
        }
    }

    return null;
}

// =====================================================
// UPDATE CUSTOMER
// =====================================================

private static void updateCustomer() {

    int id =
            readInt("Customer ID: ");

    Customer customer =
            findCustomer(id);

    if (customer == null) {

        System.out.println(
                "Customer not found.");

        return;
    }

    String name =
            readString("New Name: ");

    String address =
            readString("New Address: ");

    String contact =
            readString("New Contact: ");

    customer.setCustomerName(name);
    customer.setAddress(address);
    customer.setContactNumber(contact);

    System.out.println(
            "Customer updated successfully.");
}

// =====================================================
// DELETE CUSTOMER
// =====================================================

private static void deleteCustomer() {

    int id =
            readInt("Customer ID: ");

    Customer customer =
            findCustomer(id);

    if (customer == null) {

        System.out.println(
                "Customer not found.");

        return;
    }

    customers.remove(customer);

    System.out.println(
            "Customer deleted successfully.");
}

// =====================================================
// WAREHOUSE MENU
// =====================================================

private static void warehouseMenu() {

    while (true) {

        System.out.println();
        System.out.println("========= WAREHOUSE MANAGEMENT =========");
        System.out.println("1. Add Warehouse");
        System.out.println("2. View Warehouses");
        System.out.println("3. Update Warehouse");
        System.out.println("4. Delete Warehouse");
        System.out.println("5. Back");

        int choice =
                readInt("Enter choice: ");

        switch (choice) {

            case 1:
                addWarehouse();
                break;

            case 2:
                distributionService
                        .displayWarehouses();
                break;

            case 3:
                updateWarehouse();
                break;

            case 4:
                deleteWarehouse();
                break;

            case 5:
                return;

            default:
                System.out.println(
                        "Invalid choice.");
        }
    }
}

// =====================================================
// ADD WAREHOUSE
// =====================================================

private static void addWarehouse() {

    int id =
            readInt("Warehouse ID: ");

    if (distributionService
            .warehouseExists(id)) {

        System.out.println(
                "Warehouse ID already exists.");

        return;
    }

    String name =
            readString("Warehouse Name: ");

    String location =
            readString("Location: ");

    int capacity =
            readInt("Capacity: ");

    if (capacity < 0) {

        System.out.println(
                "Capacity cannot be negative.");

        return;
    }

    boolean added =
            distributionService.addWarehouse(
                    new Warehouse(
                            id,
                            name,
                            location,
                            capacity));

    if (added) {

        System.out.println(
                "Warehouse added successfully.");

    } else {

        System.out.println(
                "Unable to add warehouse.");
    }
}

// =====================================================
// UPDATE WAREHOUSE
// =====================================================

private static void updateWarehouse() {

    int id =
            readInt("Warehouse ID: ");

    String name =
            readString("New Name: ");

    String location =
            readString("New Location: ");

    int capacity =
            readInt("New Capacity: ");

    boolean updated =
            distributionService.updateWarehouse(
                    id,
                    name,
                    location,
                    capacity);

    if (updated) {

        System.out.println(
                "Warehouse updated successfully.");

    } else {

        System.out.println(
                "Warehouse not found or invalid data.");
    }
}

// =====================================================
// DELETE WAREHOUSE
// =====================================================

private static void deleteWarehouse() {

    int id =
            readInt("Warehouse ID: ");

    boolean deleted =
            distributionService.deleteWarehouse(id);

    if (deleted) {

        System.out.println(
                "Warehouse deleted successfully.");

    } else {

        System.out.println(
                "Warehouse not found.");
    }
}

// =====================================================
// DISTRIBUTION CENTER MENU
// =====================================================

private static void distributionCenterMenu() {

    while (true) {

        System.out.println();
        System.out.println(
                "===== DISTRIBUTION CENTER MANAGEMENT =====");

        System.out.println("1. Add Center");
        System.out.println("2. View Centers");
        System.out.println("3. Update Center");
        System.out.println("4. Delete Center");
        System.out.println("5. Back");

        int choice =
                readInt("Enter choice: ");

        switch (choice) {

            case 1:
                addDistributionCenter();
                break;

            case 2:
                distributionService
                        .displayDistributionCenters();
                break;

            case 3:
                updateDistributionCenter();
                break;

            case 4:
                deleteDistributionCenter();
                break;

            case 5:
                return;

            default:
                System.out.println(
                        "Invalid choice.");
        }
    }
}

// =====================================================
// ADD DISTRIBUTION CENTER
// =====================================================

private static void addDistributionCenter() {

    int id =
            readInt("Center ID: ");

    if (distributionService
            .distributionCenterExists(id)) {

        System.out.println(
                "Center ID already exists.");

        return;
    }

    String name =
            readString("Center Name: ");

    String location =
            readString("Location: ");

    int capacity =
            readInt("Capacity: ");

    if (capacity < 0) {

        System.out.println(
                "Capacity cannot be negative.");

        return;
    }

    boolean added =
            distributionService
                    .addDistributionCenter(
                            new DistributionCenter(
                                    id,
                                    name,
                                    location,
                                    capacity));

    if (added) {

        System.out.println(
                "Distribution center added successfully.");

    } else {

        System.out.println(
                "Unable to add center.");
    }
}

// =====================================================
// UPDATE DISTRIBUTION CENTER
// =====================================================

private static void updateDistributionCenter() {

    int id =
            readInt("Center ID: ");

    String name =
            readString("New Name: ");

    String location =
            readString("New Location: ");

    int capacity =
            readInt("New Capacity: ");

    boolean updated =
            distributionService
                    .updateDistributionCenter(
                            id,
                            name,
                            location,
                            capacity);

    if (updated) {

        System.out.println(
                "Distribution center updated.");

    } else {

        System.out.println(
                "Center not found or invalid data.");
    }
}

// =====================================================
// DELETE DISTRIBUTION CENTER
// =====================================================

private static void deleteDistributionCenter() {

    int id =
            readInt("Center ID: ");

    boolean deleted =
            distributionService
                    .deleteDistributionCenter(id);

    if (deleted) {

        System.out.println(
                "Distribution center deleted.");

    } else {

        System.out.println(
                "Center not found.");
    }
}

// =====================================================
// INVENTORY MENU
// =====================================================

private static void inventoryMenu() {

    while (true) {

        System.out.println();
        System.out.println("========= INVENTORY MANAGEMENT =========");
        System.out.println("1. Add Inventory");
        System.out.println("2. View Inventory");
        System.out.println("3. Update Inventory");
        System.out.println("4. Add Stock");
        System.out.println("5. Remove Stock");
        System.out.println("6. Delete Inventory");
        System.out.println("7. Back");

        int choice =
                readInt("Enter choice: ");

        switch (choice) {

            case 1:
                addInventory();
                break;

            case 2:
                inventoryService.displayInventory();
                break;

            case 3:
                updateInventory();
                break;

            case 4:
                addStock();
                break;

            case 5:
                removeStock();
                break;

            case 6:
                deleteInventory();
                break;

            case 7:
                return;

            default:
                System.out.println(
                        "Invalid choice.");
        }
    }
}

// =====================================================
// ADD INVENTORY
// =====================================================

private static void addInventory() {

    int id =
            readInt("Inventory ID: ");

    if (inventoryService.inventoryExists(id)) {

        System.out.println(
                "Inventory ID already exists.");

        return;
    }

    int warehouseId =
            readInt("Warehouse ID: ");

    if (!distributionService
            .warehouseExists(warehouseId)) {

        System.out.println(
                "Warehouse does not exist.");

        return;
    }

    int productId =
            readInt("Product ID: ");

    if (!productService
            .productExists(productId)) {

        System.out.println(
                "Product does not exist.");

        return;
    }

    int quantity =
            readInt("Quantity: ");

    int minimumStock =
            readInt("Minimum Stock: ");

    if (quantity < 0 ||
            minimumStock < 0) {

        System.out.println(
                "Quantity cannot be negative.");

        return;
    }

    boolean added =
            inventoryService.addInventory(
                    new Inventory(
                            id,
                            warehouseId,
                            productId,
                            quantity,
                            minimumStock));

    if (added) {

        System.out.println(
                "Inventory added successfully.");

    } else {

        System.out.println(
                "Unable to add inventory.");
    }
}

// =====================================================
// UPDATE INVENTORY
// =====================================================

private static void updateInventory() {

    int id =
            readInt("Inventory ID: ");

    int warehouseId =
            readInt("New Warehouse ID: ");

    if (!distributionService
            .warehouseExists(warehouseId)) {

        System.out.println(
                "Warehouse does not exist.");

        return;
    }

    int productId =
            readInt("New Product ID: ");

    if (!productService
            .productExists(productId)) {

        System.out.println(
                "Product does not exist.");

        return;
    }

    int quantity =
            readInt("New Quantity: ");

    int minimumStock =
            readInt("New Minimum Stock: ");

    boolean updated =
            inventoryService.updateInventory(
                    id,
                    warehouseId,
                    productId,
                    quantity,
                    minimumStock);

    if (updated) {

        System.out.println(
                "Inventory updated successfully.");

    } else {

        System.out.println(
                "Inventory not found or invalid data.");
    }
}

// =====================================================
// ADD STOCK
// =====================================================

private static void addStock() {

    int productId =
            readInt("Product ID: ");

    int quantity =
            readInt("Quantity to add: ");

    boolean added =
            inventoryService.addStock(
                    productId,
                    quantity);

    if (added) {

        System.out.println(
                "Stock added successfully.");

    } else {

        System.out.println(
                "Unable to add stock.");
    }
}

// =====================================================
// REMOVE STOCK
// =====================================================

private static void removeStock() {

    int productId =
            readInt("Product ID: ");

    int quantity =
            readInt("Quantity to remove: ");

    boolean removed =
            inventoryService.removeStock(
                    productId,
                    quantity);

    if (removed) {

        System.out.println(
                "Stock removed successfully.");

    } else {

        System.out.println(
                "Unable to remove stock.");
    }
}

// =====================================================
// DELETE INVENTORY
// =====================================================

private static void deleteInventory() {

    int id =
            readInt("Inventory ID: ");

    boolean deleted =
            inventoryService.deleteInventory(id);

    if (deleted) {

        System.out.println(
                "Inventory deleted successfully.");

    } else {

        System.out.println(
                "Inventory not found.");
    }
}

// =====================================================
// QUALITY REPORT MENU
// =====================================================

private static void qualityReportMenu() {

    while (true) {

        System.out.println();
        System.out.println("========== QUALITY REPORTS ==========");
        System.out.println("1. Add Report");
        System.out.println("2. View Reports");
        System.out.println("3. Update Report");
        System.out.println("4. Delete Report");
        System.out.println("5. Back");

        int choice =
                readInt("Enter choice: ");

        switch (choice) {

            case 1:
                addQualityReport();
                break;

            case 2:
                displayQualityReports();
                break;

            case 3:
                updateQualityReport();
                break;

            case 4:
                deleteQualityReport();
                break;

            case 5:
                return;

            default:
                System.out.println(
                        "Invalid choice.");
        }
    }
}

// =====================================================
// ADD QUALITY REPORT
// =====================================================

private static void addQualityReport() {

    int id =
            readInt("Report ID: ");

    if (findQualityReport(id) != null) {

        System.out.println(
                "Report ID already exists.");

        return;
    }

    int productId =
            readInt("Product ID: ");

    if (!productService
            .productExists(productId)) {

        System.out.println(
                "Product does not exist.");

        return;
    }

    String date =
            readString("Report Date: ");

    String status =
            readString("Quality Status: ");

    String description =
            readString("Description: ");

    qualityReports.add(
            new QualityReport(
                    id,
                    productId,
                    date,
                    status,
                    description));

    System.out.println(
            "Quality report added.");
}

// =====================================================
// VIEW QUALITY REPORTS
// =====================================================

private static void displayQualityReports() {

    if (qualityReports.isEmpty()) {

        System.out.println(
                "No quality reports available.");

        return;
    }

    System.out.println();
    System.out.println(
            "--------- QUALITY REPORTS ---------");

    for (QualityReport report :
            qualityReports) {

        System.out.println(report);
    }
}

// =====================================================
// FIND QUALITY REPORT
// =====================================================

private static QualityReport findQualityReport(
        int id) {

    for (QualityReport report :
            qualityReports) {

        if (report.getReportId() == id) {
            return report;
        }
    }

    return null;
}

// =====================================================
// UPDATE QUALITY REPORT
// =====================================================

private static void updateQualityReport() {

    int id =
            readInt("Report ID: ");

    QualityReport report =
            findQualityReport(id);

    if (report == null) {

        System.out.println(
                "Report not found.");

        return;
    }

    int productId =
            readInt("New Product ID: ");

    if (!productService
            .productExists(productId)) {

        System.out.println(
                "Product does not exist.");

        return;
    }

    String date =
            readString("New Date: ");

    String status =
            readString("New Quality Status: ");

    String description =
            readString("New Description: ");

    report.setProductId(productId);
    report.setReportDate(date);
    report.setQualityStatus(status);
    report.setDescription(description);

    System.out.println(
            "Quality report updated.");
}

// =====================================================
// DELETE QUALITY REPORT
// =====================================================

private static void deleteQualityReport() {

    int id =
            readInt("Report ID: ");

    QualityReport report =
            findQualityReport(id);

    if (report == null) {

        System.out.println(
                "Report not found.");

        return;
    }

    qualityReports.remove(report);

    System.out.println(
            "Quality report deleted.");
}

// =====================================================
// ORDER MENU - ADMIN
// =====================================================

private static void orderMenu() {

    while (true) {

        System.out.println();
        System.out.println("============ ORDER MANAGEMENT ============");
        System.out.println("1. View All Orders");
        System.out.println("2. Update Order Status");
        System.out.println("3. Delete / Cancel Order");
        System.out.println("4. Back");

        int choice =
                readInt("Enter choice: ");

        switch (choice) {

            case 1:
                displayAllOrders();
                break;

            case 2:
                updateOrderStatus();
                break;

            case 3:
                adminCancelOrder();
                break;

            case 4:
                return;

            default:
                System.out.println(
                        "Invalid choice.");
        }
    }
}

// =====================================================
// DISPLAY ALL ORDERS
// =====================================================

private static void displayAllOrders() {

    if (orders.isEmpty()) {

        System.out.println(
                "No orders available.");

        return;
    }

    System.out.println();
    System.out.println(
            "------------- ALL ORDERS -------------");

    for (Order order : orders) {

        System.out.println(order);
    }
}

// =====================================================
// FIND ORDER
// =====================================================

private static Order findOrder(int orderId) {

    for (Order order : orders) {

        if (order.getOrderId() == orderId) {
            return order;
        }
    }

    return null;
}

// =====================================================
// UPDATE ORDER STATUS
// =====================================================

private static void updateOrderStatus() {

    int orderId =
            readInt("Order ID: ");

    Order order =
            findOrder(orderId);

    if (order == null) {

        System.out.println(
                "Order not found.");

        return;
    }

    System.out.println(
            "Current Status: "
            + order.getStatus());

    String status =
            readString("New Status: ");

    order.setStatus(status);

    // Update shipment status also
    Shipment shipment =
            findShipmentByOrderId(orderId);

    if (shipment != null) {

        shipment.setStatus(status);
    }

    System.out.println(
            "Order status updated.");
}

// =====================================================
// ADMIN CANCEL ORDER
// =====================================================

private static void adminCancelOrder() {

    int orderId =
            readInt("Order ID: ");

    Order order =
            findOrder(orderId);

    if (order == null) {

        System.out.println(
                "Order not found.");

        return;
    }

    if (order.getStatus()
            .equalsIgnoreCase("Delivered")) {

        System.out.println(
                "Delivered order cannot be cancelled.");

        return;
    }

    order.setStatus("Cancelled");

    Shipment shipment =
            findShipmentByOrderId(orderId);

    if (shipment != null) {

        shipment.setStatus("Cancelled");
    }

    System.out.println(
            "Order cancelled.");
}

// =====================================================
// PLACE ORDER
// =====================================================

private static void placeOrder(
        int customerId) {

    if (findCustomer(customerId) == null) {

        System.out.println(
                "Customer account not found.");

        return;
    }

    productService.displayProducts();

    int productId =
            readInt("Enter Product ID: ");

    Product product =
            productService.findProductById(
                    productId);

    if (product == null) {

        System.out.println(
                "Product does not exist.");

        return;
    }

    int quantity =
            readInt("Enter Quantity: ");

    if (quantity <= 0) {

        System.out.println(
                "Quantity must be greater than zero.");

        return;
    }

    // Check inventory
    if (!inventoryService.hasEnoughStock(
            productId,
            quantity)) {

        System.out.println(
                "Insufficient inventory.");

        System.out.println(
                "Order was NOT created.");

        return;
    }

    // Check constraints
    Inventory inventory =
            inventoryService
                    .findByProductId(productId);

    if (!constraintService
            .isValidOrderQuantity(
                    product,
                    inventory,
                    quantity)) {

        System.out.println(
                "Order quantity violates constraints.");

        return;
    }

    // Check minimum stock
    if (!constraintService
            .maintainsMinimumStock(
                    inventory,
                    quantity)) {

        System.out.println(
                "Order would reduce stock below minimum level.");

        System.out.println(
                "Order was NOT created.");

        return;
    }

    // Remove stock
    boolean stockRemoved =
            inventoryService.removeStock(
                    productId,
                    quantity);

    if (!stockRemoved) {

        System.out.println(
                "Unable to update inventory.");

        return;
    }

    int orderId =
            getNextOrderId();

    String today =
            LocalDate.now().toString();

    Order order =
            new Order(
                    orderId,
                    customerId,
                    productId,
                    quantity,
                    today,
                    "Pending");

    orders.add(order);

    // Create shipment
    int warehouseId =
            inventory.getWarehouseId();

    int shipmentId =
            getNextShipmentId();

    Shipment shipment =
            new Shipment(
                    shipmentId,
                    orderId,
                    warehouseId,
                    today,
                    "Not Delivered",
                    "Pending");

    shipments.add(shipment);

    System.out.println();
    System.out.println(
            "======================================");
    System.out.println(
            "        ORDER CREATED SUCCESSFULLY");
    System.out.println(
            "======================================");

    System.out.println(
            "Order ID: " + orderId);

    System.out.println(
            "Product: "
            + product.getProductName());

    System.out.println(
            "Quantity: " + quantity);

    System.out.println(
            "Status: Pending");

    System.out.println(
            "Shipment ID: " + shipmentId);

    System.out.println(
            "Inventory updated successfully.");
}

// =====================================================
// GET NEXT ORDER ID
// =====================================================

private static int getNextOrderId() {

    int max = 0;

    for (Order order : orders) {

        if (order.getOrderId() > max) {

            max = order.getOrderId();
        }
    }

    return max + 1;
}

// =====================================================
// GET NEXT SHIPMENT ID
// =====================================================

private static int getNextShipmentId() {

    int max = 0;

    for (Shipment shipment : shipments) {

        if (shipment.getShipmentId() > max) {

            max = shipment.getShipmentId();
        }
    }

    return max + 1;
}

// =====================================================
// GET NEXT CUSTOMER ID
// =====================================================

private static int getNextCustomerId() {

    int max = 0;

    for (Customer customer : customers) {

        if (customer.getCustomerId() > max) {

            max = customer.getCustomerId();
        }
    }

    return max + 1;
}

// =====================================================
// GET CUSTOMER ID BY USERNAME
// =====================================================

private static int getCustomerIdByUsername(
        String username) {

    for (Customer customer : customers) {

        if (customer.getCustomerName()
                .equals(username)) {

            return customer.getCustomerId();
        }
    }

    // Fallback for default user
    if (username.equals("user")) {
        return 1;
    }

    return -1;
}

// =====================================================
// VIEW MY ORDERS
// =====================================================

private static void viewMyOrders(
        int customerId) {

    boolean found = false;

    System.out.println();
    System.out.println(
            "------------- MY ORDERS -------------");

    for (Order order : orders) {

        if (order.getCustomerId()
                == customerId) {

            System.out.println(order);

            found = true;
        }
    }

    if (!found) {

        System.out.println(
                "You have no orders.");
    }
}

// =====================================================
// CANCEL USER ORDER
// =====================================================

private static void cancelOrder(
        int customerId) {

    int orderId =
            readInt("Enter Order ID: ");

    Order order =
            findOrder(orderId);

    if (order == null) {

        System.out.println(
                "Order not found.");

        return;
    }

    if (order.getCustomerId()
            != customerId) {

        System.out.println(
                "You are not authorized to cancel this order.");

        return;
    }

    if (order.getStatus()
            .equalsIgnoreCase("Delivered")) {

        System.out.println(
                "Delivered order cannot be cancelled.");

        return;
    }

    if (order.getStatus()
            .equalsIgnoreCase("Cancelled")) {

        System.out.println(
                "Order is already cancelled.");

        return;
    }

    // Return stock
    inventoryService.addStock(
            order.getProductId(),
            order.getQuantity());

    order.setStatus("Cancelled");

    Shipment shipment =
            findShipmentByOrderId(orderId);

    if (shipment != null) {

        shipment.setStatus("Cancelled");
    }

    System.out.println(
            "Order cancelled successfully.");

    System.out.println(
            "Inventory has been restored.");
}

// =====================================================
// SHIPMENT MENU
// =====================================================

private static void shipmentMenu() {

    while (true) {

        System.out.println();
        System.out.println("========= SHIPMENT MANAGEMENT =========");
        System.out.println("1. View Shipments");
        System.out.println("2. Update Shipment");
        System.out.println("3. Delete Shipment");
        System.out.println("4. Back");

        int choice =
                readInt("Enter choice: ");

        switch (choice) {

            case 1:
                displayShipments();
                break;

            case 2:
                updateShipment();
                break;

            case 3:
                deleteShipment();
                break;

            case 4:
                return;

            default:
                System.out.println(
                        "Invalid choice.");
        }
    }
}

// =====================================================
// VIEW SHIPMENTS
// =====================================================

private static void displayShipments() {

    if (shipments.isEmpty()) {

        System.out.println(
                "No shipments available.");

        return;
    }

    System.out.println();
    System.out.println(
            "----------- SHIPMENT LIST -----------");

    for (Shipment shipment :
            shipments) {

        System.out.println(shipment);
    }
}

// =====================================================
// FIND SHIPMENT
// =====================================================

private static Shipment findShipment(
        int shipmentId) {

    for (Shipment shipment :
            shipments) {

        if (shipment.getShipmentId()
                == shipmentId) {

            return shipment;
        }
    }

    return null;
}

// =====================================================
// FIND SHIPMENT BY ORDER
// =====================================================

private static Shipment findShipmentByOrderId(
        int orderId) {

    for (Shipment shipment :
            shipments) {

        if (shipment.getOrderId()
                == orderId) {

            return shipment;
        }
    }

    return null;
}

// =====================================================
// UPDATE SHIPMENT
// =====================================================

private static void updateShipment() {

    int id =
            readInt("Shipment ID: ");

    Shipment shipment =
            findShipment(id);

    if (shipment == null) {

        System.out.println(
                "Shipment not found.");

        return;
    }

    int warehouseId =
            readInt("New Warehouse ID: ");

    if (!distributionService
            .warehouseExists(warehouseId)) {

        System.out.println(
                "Warehouse does not exist.");

        return;
    }

    String shipmentDate =
            readString("Shipment Date: ");

    String deliveryDate =
            readString("Delivery Date: ");

    String status =
            readString("Status: ");

    shipment.setWarehouseId(warehouseId);
    shipment.setShipmentDate(shipmentDate);
    shipment.setDeliveryDate(deliveryDate);
    shipment.setStatus(status);

    // Keep order status synchronized
    Order order =
            findOrder(shipment.getOrderId());

    if (order != null) {

        order.setStatus(status);
    }

    System.out.println(
            "Shipment updated successfully.");
}

// =====================================================
// DELETE SHIPMENT
// =====================================================

private static void deleteShipment() {

    int id =
            readInt("Shipment ID: ");

    Shipment shipment =
            findShipment(id);

    if (shipment == null) {

        System.out.println(
                "Shipment not found.");

        return;
    }

    shipments.remove(shipment);

    System.out.println(
            "Shipment deleted successfully.");
}

// =====================================================
// TRACK SHIPMENT
// =====================================================

private static void trackShipment(
        int customerId) {

    int orderId =
            readInt("Enter Order ID: ");

    Order order =
            findOrder(orderId);

    if (order == null ||
            order.getCustomerId()
            != customerId) {

        System.out.println(
                "Order not found.");

        return;
    }

    Shipment shipment =
            findShipmentByOrderId(orderId);

    if (shipment == null) {

        System.out.println(
                "Shipment not found.");

        return;
    }

    System.out.println();
    System.out.println(
            "========== SHIPMENT TRACKING ==========");

    System.out.println(
            "Order ID: "
            + orderId);

    System.out.println(
            "Shipment ID: "
            + shipment.getShipmentId());

    System.out.println(
            "Shipment Status: "
            + shipment.getStatus());

    System.out.println(
            "Warehouse ID: "
            + shipment.getWarehouseId());

    System.out.println(
            "Shipment Date: "
            + shipment.getShipmentDate());

    System.out.println(
            "Delivery Date: "
            + shipment.getDeliveryDate());
}

// =====================================================
// MARKETING - ADMIN
// =====================================================

private static void marketingMenu() {

    while (true) {

        System.out.println();
        System.out.println("============= MARKETING =============");
        System.out.println("1. View Products for Marketing");
        System.out.println("2. Add Product");
        System.out.println("3. Update Product");
        System.out.println("4. Back");

        int choice =
                readInt("Enter choice: ");

        switch (choice) {

            case 1:
                displayMarketingProducts();
                break;

            case 2:
                addProduct();
                break;

            case 3:
                updateProduct();
                break;

            case 4:
                return;

            default:
                System.out.println(
                        "Invalid choice.");
        }
    }
}

// =====================================================
// MARKETING - USER
// =====================================================

private static void marketingMenuUser() {

    displayMarketingProducts();
}

// =====================================================
// DISPLAY MARKETING PRODUCTS
// =====================================================

private static void displayMarketingProducts() {

    ArrayList<Product> products =
            productService.getProducts();

    if (products.isEmpty()) {

        System.out.println(
                "No products available.");

        return;
    }

    System.out.println();
    System.out.println(
            "========== FOODCHAIN MARKETING ==========");

    for (Product product : products) {

        System.out.println();
        System.out.println(
                "Product: "
                + product.getProductName());

        System.out.println(
                "Category: "
                + product.getCategory());

        System.out.println(
                "Price: ₹"
                + product.getPrice());

        System.out.println(
                "Available Quantity: "
                + product.getQuantity());

        System.out.println(
                "----------------------------------------");
    }
}

// =====================================================
// SEARCH PRODUCTS
// =====================================================

private static void searchProducts() {

    String keyword =
            readString("Search product name: ");

    ArrayList<Product> results =
            searchService.searchByName(
                    productService.getProducts(),
                    keyword);

    searchService.displayResults(results);
}

// =====================================================
// CORRECT PRODUCT NAME
// =====================================================

private static void correctProductName() {

    String input =
            readString(
                    "Enter product name/spelling: ");

    Product closest =
            correctionService.findClosestProduct(
                    productService.getProducts(),
                    input);

    correctionService.displayCorrection(
            input,
            closest);
}

// =====================================================
// ANALYTICS MENU
// =====================================================

private static void analyticsMenu() {

    while (true) {

        System.out.println();
        System.out.println("============== ANALYTICS ==============");
        System.out.println("1. Product Analytics");
        System.out.println("2. Inventory Analytics");
        System.out.println("3. Back");

        int choice =
                readInt("Enter choice: ");

        switch (choice) {

            case 1:
                analyticsService
                        .displayProductAnalytics(
                                productService
                                        .getProducts());
                break;

            case 2:
                analyticsService
                        .displayInventoryAnalytics(
                                inventoryService
                                        .getInventoryList());
                break;

            case 3:
                return;

            default:
                System.out.println(
                        "Invalid choice.");
        }
    }
}

// =====================================================
// DSA SEARCH MENU
// =====================================================

private static void dsaSearchMenu() {

    while (true) {

        System.out.println();
        System.out.println("============= DSA SEARCH =============");
        System.out.println("1. Product Search");
        System.out.println("2. Product Name Correction");
        System.out.println("3. Document Search");
        System.out.println("4. Back");

        int choice =
                readInt("Enter choice: ");

        switch (choice) {

            case 1:
                searchProducts();
                break;

            case 2:
                correctProductName();
                break;

            case 3:
                searchDocuments();
                break;

            case 4:
                return;

            default:
                System.out.println(
                        "Invalid choice.");
        }
    }
}

// =====================================================
// DOCUMENT SEARCH
// =====================================================

private static void searchDocuments() {

    String keyword =
            readString("Document keyword: ");

    ArrayList<String> results =
            documentService.searchDocuments(
                    keyword);

    documentService
            .displaySearchResults(results);
}

// =====================================================
// DSA ALGORITHM MENU
// =====================================================

private static void dsaAlgorithmMenu() {

    while (true) {

        System.out.println();
        System.out.println("============ DSA ALGORITHMS ============");

        System.out.println("1. M1 - String Algorithms");
        System.out.println("2. M2 - Suffix Structures");
        System.out.println("3. M3 - Dynamic Programming");
        System.out.println("4. M4 - Network Flow");
        System.out.println("5. M5 - NP-Completeness");
        System.out.println("6. M6 - Randomized & Parallel");
        System.out.println("7. Back");

        int choice =
                readInt("Enter choice: ");

        switch (choice) {

            case 1:
                displayM1();
                break;

            case 2:
                displayM2();
                break;

            case 3:
                displayM3();
                break;

            case 4:
                displayM4();
                break;

            case 5:
                displayM5();
                break;

            case 6:
                displayM6();
                break;

            case 7:
                return;

            default:
                System.out.println(
                        "Invalid choice.");
        }
    }
}

// =====================================================
// M1
// =====================================================

private static void displayM1() {

    System.out.println();
    System.out.println(
            "========== M1 STRING ALGORITHMS ==========");

    System.out.println(
            "1. KMP");

    System.out.println(
            "2. Z-Function");

    System.out.println(
            "3. Rabin-Karp");

    System.out.println(
            "4. Aho-Corasick");

    System.out.println();
    System.out.println(
            "These algorithms are used for fast");
    System.out.println(
            "pattern and product/document searching.");
}

// =====================================================
// M2
// =====================================================

private static void displayM2() {

    System.out.println();
    System.out.println(
            "========== M2 SUFFIX STRUCTURES ==========");

    System.out.println(
            "1. Suffix Array");

    System.out.println(
            "2. SA-IS");

    System.out.println(
            "3. LCP / Kasai");

    System.out.println(
            "4. Suffix Tree");

    System.out.println(
            "5. Suffix Automaton");
}

// =====================================================
// M3
// =====================================================

private static void displayM3() {

    System.out.println();
    System.out.println(
            "====== M3 DYNAMIC PROGRAMMING ======");

    System.out.println(
            "1. Levenshtein Distance");

    System.out.println(
            "2. Damerau-Levenshtein");

    System.out.println(
            "3. Bitmask DP");

    System.out.println(
            "4. Matrix Chain Multiplication");

    System.out.println(
            "5. Optimal BST");

    System.out.println();
    System.out.println(
            "Levenshtein is currently used for");
    System.out.println(
            "product-name correction.");
}

// =====================================================
// M4
// =====================================================

private static void displayM4() {

    System.out.println();
    System.out.println(
            "========== M4 NETWORK FLOW ==========");

    System.out.println(
            "1. Ford-Fulkerson");

    System.out.println(
            "2. Edmonds-Karp");

    System.out.println(
            "3. Dinic");

    System.out.println(
            "4. Bipartite Matching");

    System.out.println(
            "5. Max-Flow Min-Cut");
}

// =====================================================
// M5
// =====================================================

private static void displayM5() {

    System.out.println();
    System.out.println(
            "======= M5 NP-COMPLETENESS =======");

    System.out.println(
            "1. SAT");

    System.out.println(
            "2. 3-SAT");

    System.out.println(
            "3. 3-SAT to CLIQUE");

    System.out.println(
            "4. CLIQUE to Independent Set");

    System.out.println(
            "5. Independent Set to Vertex Cover");

    System.out.println(
            "6. Vertex Cover Approximation");
}

// =====================================================
// M6
// =====================================================

private static void displayM6() {

    System.out.println();
    System.out.println(
            "===== M6 RANDOMIZED & PARALLEL =====");

    System.out.println(
            "1. Randomized QuickSort");

    System.out.println(
            "2. Reservoir Sampling");

    System.out.println(
            "3. Miller-Rabin");

    System.out.println(
            "4. Blelloch Scan");

    System.out.println(
            "5. Parallel Reduce");

    System.out.println(
            "6. Brent's Theorem");
}

// =====================================================
// INPUT - INTEGER
// =====================================================

private static int readInt(
        String message) {

    while (true) {

        try {

            System.out.print(message);

            return Integer.parseInt(
                    scanner.nextLine().trim());

        } catch (Exception e) {

            System.out.println(
                    "Please enter a valid integer.");
        }
    }
}

// =====================================================
// INPUT - DOUBLE
// =====================================================

private static double readDouble(
        String message) {

    while (true) {

        try {

            System.out.print(message);

            return Double.parseDouble(
                    scanner.nextLine().trim());

        } catch (Exception e) {

            System.out.println(
                    "Please enter a valid number.");
        }
    }
}

// =====================================================
// INPUT - STRING
// =====================================================

private static String readString(
        String message) {

    System.out.print(message);

    return scanner.nextLine().trim();
}

}