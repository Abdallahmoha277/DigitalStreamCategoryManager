/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import java.awt.*;
import models.Category;
import services.CategoryService;

public class EditCategoryUI extends JFrame {

    public EditCategoryUI(CategoryService service, Category cat, UI parent) {

        setTitle("Edit Category - " + cat.getCode());
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 2, 10, 10));

        JLabel newDescLabel = new JLabel("New Description:");
        JTextField newDescField = new JTextField();

        JButton updateBtn = new JButton("Update");

        add(newDescLabel);
        add(newDescField);
        add(new JLabel());
        add(updateBtn);

        updateBtn.addActionListener(e -> {
            String newDesc = newDescField.getText().trim();

            if (newDesc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Description cannot be empty!");
                return;
            }

            service.editCategoryDescription(cat.getCode(), newDesc);
            JOptionPane.showMessageDialog(this, "Updated Successfully!");

            parent.refreshUI();  
            dispose();
        });

        setVisible(true);
    }
}

