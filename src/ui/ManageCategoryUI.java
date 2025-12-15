/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import java.awt.*;
import models.Category;
import services.CategoryService;




public class ManageCategoryUI extends JFrame {

    public ManageCategoryUI(CategoryService service, Category cat, UI parent) {

        setTitle("Manage Category - " + cat.getCode());
        setSize(350, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        JLabel codeLabel = new JLabel("Code: " + cat.getCode());
        JLabel descLabel = new JLabel("Description: " + cat.getDescription());
        JLabel itemsLabel = new JLabel("Items Count: " + cat.getItems().size());

        JButton editBtn = new JButton("Edit Description");
        JButton assignBtn = new JButton("Assign Item");

        
        editBtn.addActionListener(e -> {
            new EditCategoryUI(service, cat, parent);
            dispose();   
        });

        

        add(codeLabel);
        add(descLabel);
        add(itemsLabel);
        add(editBtn);
        add(assignBtn);

        setVisible(true);
    }
}

