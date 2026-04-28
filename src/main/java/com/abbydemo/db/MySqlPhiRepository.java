package com.abbydemo.db;

import com.abbydemo.models.PhiDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MySqlPhiRepository {
    private final DbConfig dbConfig;

    public MySqlPhiRepository(DbConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    public Set<PhiDto> fetchPhiRecords(String query) {
        Set<PhiDto> result = new HashSet<>();

        try (Connection connection = DriverManager.getConnection(
                dbConfig.getUrl(),
                dbConfig.getUsername(),
                dbConfig.getPassword()
        );
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                result.add(mapRowToPhiDto(rs));
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Failed to fetch PHI records from MySQL.", ex);
        }
        return result;
    }

    private PhiDto mapRowToPhiDto(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String description = rs.getString("description");
        Timestamp ts = rs.getTimestamp("timestamp");
        Date timestamp = ts == null ? null : new Date(ts.getTime());
        String severity = rs.getString("severity");
        return new PhiDto(id, description, timestamp, severity);
    }
}
