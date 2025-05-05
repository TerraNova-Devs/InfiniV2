package de.mcterranova.infini.rpg.database;

import de.mcterranova.infini.Infini;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TableHandler {

    public static void insertValue(TableID ID, UUID uuid, byte[] contents) {
        String sql = "INSERT INTO `" + ID.name().toLowerCase() + "` (`UUID`, `contents`) VALUES (?, ?);";

        try (Connection conn = Infini.hikari.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, uuid.toString());
            ps.setBytes(2, contents);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertValue(TableID ID, String id, byte[] contents) {
        String sql = "INSERT INTO `" + ID.name().toLowerCase() + "` (`id`, `contents`) VALUES (?, ?);";

        try (Connection conn = Infini.hikari.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.setBytes(2, contents);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateValue(TableID ID, UUID uuid, byte[] contents) {
        String sql = "UPDATE `" + ID.name().toLowerCase() + "` SET `contents` = ? WHERE `UUID` = ?;";

        try (Connection conn = Infini.hikari.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(2, uuid.toString());  // Set the new contents
            ps.setBytes(1, contents);  // Set the UUID for the WHERE clause
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateValue(TableID ID, String id, byte[] contents) {
        String sql = "UPDATE `" + ID.name().toLowerCase() + "` SET `contents` = ? WHERE `id` = ?;";

        try (Connection conn = Infini.hikari.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(2, id);  // Set the new contents
            ps.setBytes(1, contents);  // Set the UUID for the WHERE clause
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteValue(TableID ID, UUID uuid) {
        String sql = "DELETE FROM `" + ID.name().toLowerCase() + "` WHERE `UUID` = ?;";

        try (Connection conn = Infini.hikari.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, uuid.toString());  // Set the UUID for the WHERE clause
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteValue(TableID ID, String id) {
        String sql = "DELETE FROM `" + ID.name().toLowerCase() + "` WHERE `id` = ?;";

        try (Connection conn = Infini.hikari.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);  // Set the UUID for the WHERE clause
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean doesValueExist(TableID ID, UUID uuid) {
        String sql = "SELECT 1 FROM `" + ID.name().toLowerCase() + "` WHERE `UUID` = ? LIMIT 1;";
        boolean exists = false;

        try (Connection conn = Infini.hikari.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, uuid.toString());  // Set the UUID for the WHERE clause

            try (ResultSet rs = ps.executeQuery()) {
                exists = rs.next();  // If there's a result, the value exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;  // Return true if the value exists, false otherwise
    }

    public static boolean doesValueExist(TableID ID, String id) {
        String sql = "SELECT 1 FROM `" + ID.name().toLowerCase() + "` WHERE `id` = ? LIMIT 1;";
        boolean exists = false;

        try (Connection conn = Infini.hikari.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);  // Set the UUID for the WHERE clause

            try (ResultSet rs = ps.executeQuery()) {
                exists = rs.next();  // If there's a result, the value exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;  // Return true if the value exists, false otherwise
    }

    public static byte[] selectValue(TableID ID, UUID uuid) {
        String sql = "SELECT `contents` FROM `" + ID.name().toLowerCase() + "` WHERE `UUID` = ?;";
        byte[] contents = null;

        try (Connection conn = Infini.hikari.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, uuid.toString());  // Set the UUID for the WHERE clause

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    contents = rs.getBytes("contents");  // Get the contents from the result set
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contents;  // Return the contents or null if not found
    }

    public static byte[] selectValue(TableID ID, String id) {
        String sql = "SELECT `contents` FROM `" + ID.name().toLowerCase() + "` WHERE `id` = ?;";
        byte[] contents = null;

        try (Connection conn = Infini.hikari.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);  // Set the UUID for the WHERE clause

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    contents = rs.getBytes("contents");  // Get the contents from the result set
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contents;  // Return the contents or null if not found
    }

    public static Map<UUID, byte[]> selectValues(TableID ID) {
        String sql = "SELECT * FROM `" + ID.name().toLowerCase() + "`";
        Map<UUID, byte[]> values = new HashMap<>();

        try (Connection conn = Infini.hikari.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    values.put(UUID.fromString(new String(rs.getBytes("UUID"), StandardCharsets.UTF_8)), rs.getBytes("contents"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return values;  // Return the contents or null if not found
    }

    public static Map<UUID, byte[]> selectTemplateValues(TableID ID) {
        String sql = "SELECT * FROM `" + ID.name().toLowerCase() + "`";
        Map<UUID, byte[]> values = new HashMap<>();

        try (Connection conn = Infini.hikari.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    values.put(UUID.fromString(new String(rs.getBytes("id"), StandardCharsets.UTF_8)), rs.getBytes("contents"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return values;  // Return the contents or null if not found
    }
}
