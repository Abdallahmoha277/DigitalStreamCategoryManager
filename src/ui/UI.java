
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import java.awt.*;
import services.CategoryService;
import models.Category;


public class UI extends JFrame {

    private CategoryService service;
    

    public UI(CategoryService service) {

        this.service = service;
        setTitle("DigitalStream Dashboard");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(220, 800));
        sidebar.setBackground(new Color(245, 245, 245));
        sidebar.setLayout(new GridLayout(10, 1, 5, 5));

        String[] menuItems = {
            "Dashboard",
            "Categories",
            "Items",
            "Reports",
            "Settings",
            "Logout"
        };

        for (String item : menuItems) {
            JButton btn = new JButton(item);
            btn.setFocusPainted(false);
            btn.setBackground(Color.WHITE);
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            sidebar.add(btn);
        }

        add(sidebar, BorderLayout.WEST);

        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setLayout(new BorderLayout());

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addBtn = new JButton("+ Add New Category");
        addBtn.setBackground(new Color(0, 122, 255));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFocusPainted(false);
        topBar.add(addBtn);
        addBtn.addActionListener(e -> {
            new CreateCategoryUI(service, this);  // فتح نافذة إضافة Category
        });

        content.add(topBar, BorderLayout.NORTH);

        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new GridLayout(2, 3, 20, 20));
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        cardsPanel.setBackground(Color.WHITE);
        

        for (Category cat : service.getAllCategories()) {

            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true));

            JPanel img = new JPanel();
            img.setBackground(new Color(240, 240, 240));
            img.setPreferredSize(new Dimension(200, 120));

            JLabel title = new JLabel(cat.getCode(), SwingConstants.CENTER);
            title.setFont(new Font("SansSerif", Font.BOLD, 16));

            JLabel description = new JLabel(cat.getDescription(), SwingConstants.CENTER);

            JButton manageBtn = new JButton("Manage");
            manageBtn.addActionListener(e -> new ManageCategoryUI(service, cat, this));

            JPanel textPanel = new JPanel(new GridLayout(2, 1));
            textPanel.setBackground(Color.WHITE);
            textPanel.add(title);
            textPanel.add(description);

            card.add(img, BorderLayout.NORTH);
            card.add(textPanel, BorderLayout.CENTER);
            card.add(manageBtn, BorderLayout.SOUTH);

            cardsPanel.add(card);
        }

        content.add(cardsPanel, BorderLayout.CENTER);
        add(content, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {

    }
    public void refreshUI() {
    dispose();          
    new UI(service);    
}

}
