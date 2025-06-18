# 🚗 Car Dealership Management System

## 📌 Project Description

**Car Dealership Management System** is a Java console-based application integrated with MySQL, built to manage a vehicle dealership's operations. It allows users to search inventory, add/remove vehicles, and process lease/sale contracts—all backed by a relational database instead of CSV files.

---

## 🧱 Key Features

### 🔍 Search Vehicles

* By Price Range
* By Make & Model
* By Year Range
* By Color
* By Mileage Range
* By Vehicle Type

### 🚗 Vehicle Inventory Management

* View all vehicles
* Add a new vehicle
* Remove vehicle by VIN

### 📃 Contract Management

* Process **Sales Contracts**
* Process **Lease Contracts**
* Save contracts to MySQL `sales_contracts` and `lease_contracts` tables

### 💾 Data Persistence

* MySQL Database Integration
* JDBC via Apache DBCP `BasicDataSource`
* DAO Pattern (Data Access Object)
* Removed CSV file dependency

---

## 🧑‍💼 User Stories

* As a dealership staff member, I want to:

    * Search for available vehicles using filters.
    * Add a vehicle to the inventory.
    * Remove a sold vehicle.
    * Record a contract for a sale or lease.

---

## 📄 Sample Screens

### 🏠 Home Menu

```
==== Welcome to the Dealership ====
1) Search Inventory
2) Add Vehicle
3) Remove Vehicle
4) Process Sale
5) Process Lease
0) Exit
```

### 🔎 Vehicle Search Example

```
Enter minimum price:
Enter maximum price:
Results:
1. 2022 Toyota Camry | Sedan | $21,000 | VIN: A1234B56
```

### ❌ Error Handling

```
Invalid input. Please enter a valid option.
```

---

## 🧰 Technologies Used

* Java 17+
* MySQL 8+
* JDBC + Apache DBCP
* IntelliJ IDEA
* Maven
* Git & GitHub

---

## 🏗️ Project Architecture

### Packages:

```
src/
├── com.pluralsight.dealership        // UI logic (UserInterface, Program)
├── com.pluralsight.model             // POJOs: Vehicle, Dealership, SalesContract, LeaseContract
├── database                          // DAO classes: VehicleDao, SalesDao, LeaseDao
```

### DAO Classes:

* `VehicleDao.java`
* `SalesDao.java`
* `LeaseDao.java`

### Models:

* `Vehicle.java`
* `SalesContract.java`
* `LeaseContract.java`
* `Dealership.java`

---

## ✅ Setup Instructions

### Prerequisites:

* Java 17+
* MySQL Server
* Maven

### Steps:

1. **Clone the Repository**

```bash
git clone https://github.com/your-username/jdbc-dealership-project.git
```

2. **Set up the MySQL Database**

* Run `car_dealership_schema.sql` to create tables

3. **Configure Connection**

* Update `DatabaseConfig.java` with your MySQL username and password

4. **Build & Run**

```bash
mvn clean install
java -cp target/jdbc-dealership-project.jar com.pluralsight.dealership.Program
```

---

## 🧠 Interesting Code Snippet

```java
public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
    List<Vehicle> vehicles = new ArrayList<>();
    String sql = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";

    try (Connection connection = dataSource.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {

        stmt.setDouble(1, minPrice);
        stmt.setDouble(2, maxPrice);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            vehicles.add(createVehicleFromResultSet(rs));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return vehicles;
}
```

### 💡 Why It’s Interesting

* Uses `PreparedStatement` for SQL injection prevention
* Shows clean DAO structure
* Promotes reusable, maintainable code

---

## 🧪 Milestones

### ✅ Phase 1: Search Inventory

* Implemented 6 types of search filters

### ✅ Phase 2: Inventory Management

* Add and remove vehicle support via database

### ✅ Phase 3: Contract Persistence

* Sales and Lease contracts stored in SQL tables
* Removed all `.csv` file dependencies

---

## 📝 Sample Commit Messages

* `Add VehicleDao with search methods`
* `Implement contract DAOs for sales and lease`
* `Refactor CSV logic to JDBC`
* `Create database schema and populate sample data`

---

## 📷 Screenshots (Optional)

Add screenshots of:

* Home screen
* Inventory search results
* Contract processing confirmation

---

## 🧑‍💻 Author

**Guriqbal Manyani**
Sophomore @ NJIT
GitHub: \[your-github-link]
LinkedIn: \[your-link]

---

## 📜 License

This project is part of NJIT / Pluralsight's Java capstone workshop and is open-source for educational purposes.
