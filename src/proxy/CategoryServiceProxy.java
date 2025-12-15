/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proxy;

import services.CategoryService;
import services.CategoryServiceImpl;
import models.Category;
import java.util.List;

public class CategoryServiceProxy implements CategoryService {

    private final boolean isLoggedIn;
    private final CategoryServiceImpl realService;

    public CategoryServiceProxy(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
        this.realService = new CategoryServiceImpl();
    }

    private void checkAccess() {
        if (!isLoggedIn) {
            throw new RuntimeException("Access Denied: Admin not logged in");
        }
    }

    @Override
    public List<Category> getAllCategories() {
        checkAccess();
        return realService.getAllCategories();
    }

    @Override
    public void createCategory(String code, String description) {
        checkAccess();
        realService.createCategory(code, description);
    }

    @Override
    public void editCategoryDescription(String code, String newDescription) {
        checkAccess();
        realService.editCategoryDescription(code, newDescription);
    }
}



