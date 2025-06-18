package database;

import com.pluralsight.dealership.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;
    int vin;
    String make;
    int year;
    String model;
    String vehicleType;
    boolean sold;
    String color;
    int odometer;
    double price;
    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        // TODO: Implement the logic to add a vehicle
        String sql = "INSERT INTO vehicles (VIN, make, model, year, SOLD, color, vehicleType, odometer, price) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicle.getVin());
            stmt.setString(2, vehicle.getMake());
            stmt.setString(3, vehicle.getModel());
            stmt.setInt(4, vehicle.getYear());
            stmt.setBoolean(5, vehicle.isSold());
            stmt.setString(6, vehicle.getColor());
            stmt.setString(7, vehicle.getVehicleType());
            stmt.setInt(8, vehicle.getOdometer());
            stmt.setDouble(9, vehicle.getPrice());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println(" Vehicle added successfully: " + vehicle.getVin());
            } else {
                System.out.println(" Vehicle not added.");
            }

        } catch (SQLException e) {
            System.err.println(" Error adding vehicle.");
            e.printStackTrace();
        }
    }

    public void removeVehicle(String VIN) {
        // TODO: Implement the logic to remove a vehicle
        String sql = "DELETE FROM vehicles WHERE VIN = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, VIN);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Vehicle with VIN " + VIN + " removed from the database.");
            } else {
                System.out.println(" No vehicle found with VIN: " + VIN);
            }

        } catch (SQLException e) {
            System.err.println(" Error removing vehicle.");
            e.printStackTrace();
        }
    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        // TODO: Implement the logic to search vehicles by price range
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT VIN, make, model, year, sold, color, vehicleType, odometer, price " +
                "FROM vehicles WHERE price BETWEEN ? AND ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDouble(1, minPrice);
            stmt.setDouble(2, maxPrice);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                do {
                    Vehicle vehicle = new Vehicle(vin, make, year,  model,vehicleType, sold,  color, odometer, price);
                    vehicle.setVin(Integer.parseInt(rs.getString("VIN")));
                    vehicle.setMake(rs.getString("make"));
                    vehicle.setModel(rs.getString("model"));
                    vehicle.setYear(rs.getInt("year"));
                    vehicle.setSold(rs.getBoolean("sold"));
                    vehicle.setColor(rs.getString("color"));
                    vehicle.setVehicleType(rs.getString("vehicleType"));
                    vehicle.setOdometer(rs.getInt("odometer"));
                    vehicle.setPrice(rs.getDouble("price"));

                    vehicles.add(vehicle);
                } while (rs.next());
            } else {
                System.out.println("No vehicles found in price range: $" + minPrice + " - $" + maxPrice);
            }

        } catch (SQLException e) {
            System.err.println(" Error fetching vehicles by price range.");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Vehicle> searchByMakeModel(String make, String model) {
        // TODO: Implement the logic to search vehicles by make and model

            List<Vehicle> vehicles = new ArrayList<>();
            String sql = "SELECT VIN, make, model, year, SOLD, color, vehicleType, odometer, price " +
                    "FROM vehicles WHERE make = ? AND model = ?";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setString(1, make);
                stmt.setString(2, model);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    do {
                        Vehicle vehicle = createVehicleFromResultSet(rs);
                        vehicles.add(vehicle);
                    } while (rs.next());
                } else {
                    System.out.println("No vehicles found for: " + make + " " + model);
                }

            } catch (SQLException e) {
                System.err.println(" Error fetching vehicles by make/model.");
                e.printStackTrace();
            }

        return vehicles;
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        // TODO: Implement the logic to search vehicles by year range
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, minYear);
            stmt.setInt(2, maxYear);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                do {
                    vehicles.add(createVehicleFromResultSet(rs));
                } while (rs.next());
            } else {
                System.out.println("No vehicles found between years " + minYear + " and " + maxYear);
            }

        } catch (SQLException e) {
            System.err.println(" Error fetching vehicles by year range.");
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByColor(String color) {
        // TODO: Implement the logic to search vehicles by color
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE color = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, color);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                do {
                    vehicles.add(createVehicleFromResultSet(rs));
                } while (rs.next());
            } else {
                System.out.println("No vehicles found with color: " + color);
            }

        } catch (SQLException e) {
            System.err.println(" Error fetching vehicles by color.");
            e.printStackTrace();
        }

        return vehicles;
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        // TODO: Implement the logic to search vehicles by mileage range
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE odometer BETWEEN ? AND ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, minMileage);
            stmt.setInt(2, maxMileage);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                do {
                    vehicles.add(createVehicleFromResultSet(rs));
                } while (rs.next());
            } else {
                System.out.println("No vehicles found in mileage range: " + minMileage + " - " + maxMileage);
            }

        } catch (SQLException e) {
            System.err.println(" Error fetching vehicles by mileage range.");
            e.printStackTrace();
        }

        return vehicles;
    }

    public List<Vehicle> searchByType(String type) {
        // TODO: Implement the logic to search vehicles by type
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE vehicleType = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, type);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                do {
                    vehicles.add(createVehicleFromResultSet(rs));
                } while (rs.next());
            } else {
                System.out.println("No vehicles found of type: " + type);
            }

        } catch (SQLException e) {
            System.err.println(" Error fetching vehicles by type.");
            e.printStackTrace();
        }

        return vehicles;
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle( vin, make, year,  model,vehicleType, sold,  color, odometer, price);
        vehicle.setVin(Integer.parseInt(resultSet.getString("VIN")));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}
