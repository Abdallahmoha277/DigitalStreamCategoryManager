/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import java.awt.*;

import services.CategoryService;

public class CreateCategoryUI extends JFrame {

    public CreateCategoryUI(CategoryService service, UI parent) {

        setTitle("Add New Category");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 5, 5));

        JLabel codeLabel = new JLabel("Code:");
        JTextField codeField = new JTextField();

        JLabel descLabel = new JLabel("Description:");
        JTextField descField = new JTextField();

        JButton saveBtn = new JButton("Save");

        add(codeLabel); 
        add(codeField);
        add(descLabel); 
        add(descField);
        add(new JLabel()); 
        add(saveBtn);

        saveBtn.addActionListener(e -> {
            String code = codeField.getText().trim();
            String desc = descField.getText().trim();

            if (code.isEmpty() || desc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Both fields are required!");
                return;
            }

            service.createCategory(code, desc);
            JOptionPane.showMessageDialog(this, "Category Created Successfully!");

            parent.refreshUI();  
            dispose();           
        });

        setVisible(true);
    }
}

