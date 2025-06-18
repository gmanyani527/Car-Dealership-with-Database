package database;

import models.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;


public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract) {
        String sql = "INSERT INTO lease_contracts (VIN, lease_start, lease_end, monthly_payment) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, leaseContract.getVin());
            stmt.setDate(2, Date.valueOf(leaseContract.getLeaseStart()));
            stmt.setDate(3, Date.valueOf(leaseContract.getLeaseEnd()));
            stmt.setDouble(4, leaseContract.getMonthlyPayment());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println(" Lease contract added for VIN: " + leaseContract.getVin());
            } else {
                System.out.println(" Lease contract not added.");
            }

        } catch (SQLException e) {
            System.err.println(" Error adding lease contract.");
            e.printStackTrace();
        }
    }
}
