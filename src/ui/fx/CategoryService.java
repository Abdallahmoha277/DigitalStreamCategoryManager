/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.fx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Category;

public class CategoryService {

   
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=DigitalStreamDB;encrypt=false;trustServerCertificate=true;";
    private static final String USER = "root";  
    private static final String PASS = "admin"; 
    

    public CategoryService() {
        try {
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            initializeDatabase(); 
        } catch (ClassNotFoundException e) {
            System.err.println("SQL Server JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database initialization failed.");
            e.printStackTrace();
        }
    }

    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    
    private void initializeDatabase() throws SQLException {
        String createTableSQL = "IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Categories') " +
                                "CREATE TABLE Categories (" +
                                "  Code NVARCHAR(50) PRIMARY KEY," +
                                "  Description NVARCHAR(255) NOT NULL" +
                                ")";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute(createTableSQL);
            System.out.println("Categories table ensured to exist.");
        }
    }

    
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String selectSQL = "SELECT Code, Description FROM Categories";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            while (rs.next()) {
                Category cat = new Category(
                    rs.getString("Code"),
                    rs.getString("Description")
                );
                categories.add(cat);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching categories from DB: " + e.getMessage());
        }
        return categories;
    }

    
    public void createCategory(String code, String description) {
        String insertSQL = "INSERT INTO Categories (Code, Description) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, code);
            pstmt.setString(2, description);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Category inserted successfully: " + code);
            }
        } catch (SQLException e) {
           
            System.err.println("Error creating category in DB: " + e.getMessage());
        }
    }

    
}