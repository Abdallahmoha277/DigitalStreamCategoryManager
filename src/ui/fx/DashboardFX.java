/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.fx;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import services.CategoryService;
import models.Category;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;

public class DashboardFX {

    private final CategoryService service;
    private BorderPane root;

    public DashboardFX(CategoryService service) {
        this.service = service;
        show();
    }

    private void show() {
        Stage stage = new Stage();
        stage.setTitle("DigitalStream Dashboard");
        // 
        root = new BorderPane();

        root.setLeft(createSidebar());
        root.setCenter(createDashboardView());

        Scene scene = new Scene(root, 820, 520);
        stage.setScene(scene);
        stage.show();
    }

    
    private VBox createSidebar() {

        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(30, 20, 30, 20));
        sidebar.setPrefWidth(240);
        sidebar.setStyle("-fx-background-color: #f5f5f5;");

        String[] items = {
            "Dashboard",
            "Categories",
            "Items",
            "Reports",
            //"Settings",
            "Logout"
        };

        for (String item : items) {
            Button btn = new Button(item);

            btn.setPrefHeight(45);
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setAlignment(Pos.CENTER_LEFT);
            btn.setPadding(new Insets(0, 0, 0, 20));

            btn.setStyle("""
                -fx-background-color: white;
                -fx-font-size: 14px;
                -fx-background-radius: 8;
                -fx-cursor: hand;
            """);

            btn.setOnAction(e -> handleMenu(item));
            sidebar.getChildren().add(btn);
        }

        return sidebar;
    }

    
    private void handleMenu(String item) {

        switch (item) {
            case "Dashboard" ->
                root.setCenter(createDashboardView());
            case "Categories" ->
                root.setCenter(createCategoriesView());
            case "Items" ->
                root.setCenter(createItemsView());
            case "Reports" ->
                root.setCenter(createReportsView());
            //case "Settings" ->
            //root.setCenter(createSettingsView());
            case "Logout" ->
                System.exit(0);
        }
    }

    // ================= Views =================
    private VBox createDashboardView() {
        VBox box = new VBox();
        box.setPadding(new Insets(30));
        box.getChildren().add(new Label("Welcome to Dashboard"));
        return box;
    }

    private VBox createCategoriesView() {
        VBox box = new VBox(20); 
        box.setPadding(new Insets(30));

        Label title = new Label("Categories List");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        box.getChildren().add(title);
        
        TableView<Category> categoryTable = new TableView<>();

        
        TableColumn<Category, String> codeCol = new TableColumn<>("Code");
        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        codeCol.setPrefWidth(100);

        TableColumn<Category, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        descCol.setPrefWidth(350);

        categoryTable.getColumns().addAll(codeCol, descCol);

        
        categoryTable.setItems(
                FXCollections.observableArrayList(service.getAllCategories())
        );
        
        
        Button addBtn = new Button("Add New Category");
        addBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5; -fx-cursor: hand;");
        
        
        addBtn.setOnAction(e -> showAddCategoryDialog(categoryTable));

        box.getChildren().addAll(categoryTable, addBtn);

        return box;
    }

    private VBox createItemsView() {
        VBox box = new VBox();
        box.setPadding(new Insets(30));
        box.getChildren().add(new Label("Items Page"));
        return box;
    }

    private VBox createReportsView() {
        VBox box = new VBox();
        box.setPadding(new Insets(30));
        box.getChildren().add(new Label("Reports Page"));
        return box;
    }

    
    private void showAddCategoryDialog(TableView<Category> table) {
        
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Add Category");

        TextField codeField = new TextField();
        codeField.setPromptText("Category Code");

        TextField descField = new TextField();
        descField.setPromptText("Description");

        VBox content = new VBox(10, codeField, descField);
        content.setPadding(new Insets(20));

        dialog.getDialogPane().setContent(content);

        ButtonType saveBtn
                = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelBtn
                = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes()
                .addAll(saveBtn, cancelBtn);

        dialog.setResultConverter(btn -> {
            if (btn == saveBtn) {

                
                service.createCategory(
                        codeField.getText(),
                        descField.getText()
                );

                table.getItems().setAll(service.getAllCategories());
            }
            return null;
        });

        dialog.showAndWait();
    }
}