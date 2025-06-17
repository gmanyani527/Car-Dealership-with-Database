package database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) {
        // TODO: Implement the logic to add a vehicle to the inventory
        String sql = "INSERT INTO inventory (VIN, dealership_id) VALUES (?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vin);
            stmt.setInt(2, dealershipId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println(" Vehicle " + vin + " added to dealership " + dealershipId);
            } else {
                System.out.println(" No rows inserted.");
            }

        } catch (SQLException e) {
            System.err.println(" Failed to add vehicle to inventory.");
            e.printStackTrace();
        }
    }

    public void removeVehicleFromInventory(String vin) {
        // TODO: Implement the logic to remove a vehicle from the inventory
        String sql = "DELETE FROM inventory WHERE VIN = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vin);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Vehicle " + vin + " removed from inventory.");
            } else {
                System.out.println(" No vehicle found with VIN: " + vin);
            }

        } catch (SQLException e) {
            System.err.println(" Error removing vehicle from inventory.");
            e.printStackTrace();
        }
    }
}
