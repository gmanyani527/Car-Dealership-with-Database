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
    String color;
    int odometer;
    double price;
    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        // TODO: Implement the logic to add a vehicle
    }

    public void removeVehicle(String VIN) {
        // TODO: Implement the logic to remove a vehicle
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
                    Vehicle vehicle = new Vehicle(vin, make, year,  model,vehicleType,  color, odometer, price);
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
                System.err.println("❌ Error fetching vehicles by make/model.");
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
            System.err.println("❌ Error fetching vehicles by year range.");
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByColor(String color) {
        // TODO: Implement the logic to search vehicles by color
        return new ArrayList<>();
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        // TODO: Implement the logic to search vehicles by mileage range
        return new ArrayList<>();
    }

    public List<Vehicle> searchByType(String type) {
        // TODO: Implement the logic to search vehicles by type
        return new ArrayList<>();
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle( vin, make, year,  model,vehicleType,  color, odometer, price);
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
