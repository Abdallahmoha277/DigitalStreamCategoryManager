/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import models.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private static final String DB_URL =
        "jdbc:mysql://localhost:3306/digitalstreamdb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "admin123";

    public CategoryServiceImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT Code, Description FROM categories";

        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Category(
                        rs.getString("Code"),
                        rs.getString("Description")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching categories", e);
        }
        return list;
    }

    @Override
    public void createCategory(String code, String description) {
        String sql = "INSERT INTO categories (Code, Description) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, code);
            ps.setString(2, description);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error creating category", e);
        }
    }

    @Override
    public void editCategoryDescription(String code, String newDescription) {
        String sql = "UPDATE categories SET Description = ? WHERE Code = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newDescription);
            ps.setString(2, code);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating category", e);
        }
    }
}

