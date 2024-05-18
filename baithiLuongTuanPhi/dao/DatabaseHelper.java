package baithiLuongTuanPhi.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DatabaseHelper<T> {
    private static final String URL = "jdbc:mysql://localhost:3306/AddressBookDB";
    private static final String USER = "root";
    private static final String PASSWORD = "phi";

    private final String tableName;
    private final Function<ResultSet, T> mapper;
    private final String insertSql;
    private final String updateSql;

    public DatabaseHelper(String tableName, Function<ResultSet, T> mapper, String insertSql, String updateSql) {
        this.tableName = tableName;
        this.mapper = mapper;
        this.insertSql = insertSql;
        this.updateSql = updateSql;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void add(T item, Function<PreparedStatement, Void> parameterSetter) {
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            parameterSetter.apply(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public T find(String name, String query, Function<PreparedStatement, Void> parameterSetter) {
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            parameterSetter.apply(pstmt);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapper.apply(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<T> getAll() {
        String sql = "SELECT * FROM " + tableName;
        List<T> items = new ArrayList<>();
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                items.add(mapper.apply(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return items;
    }

    public void update(String oldName, T newItem, Function<PreparedStatement, Void> parameterSetter) {
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
            parameterSetter.apply(pstmt);
            pstmt.setString(5, oldName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(String name) {
        String sql = "DELETE FROM " + tableName + " WHERE name = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
