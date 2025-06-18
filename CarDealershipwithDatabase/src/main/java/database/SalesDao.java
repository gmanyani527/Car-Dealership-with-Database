package database;

import models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {
        String sql = "INSERT INTO sales_contracts (VIN, sale_date, price) VALUES (?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, salesContract.getVin());
            stmt.setDate(2, Date.valueOf(salesContract.getSaleDate()));
            stmt.setDouble(3, salesContract.getPrice());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Sales contract added for VIN: " + salesContract.getVin());
            } else {
                System.out.println("Sales contract not added.");
            }

        } catch (SQLException e) {
            System.err.println(" Error adding sales contract.");
            e.printStackTrace();
        }
    }
}
