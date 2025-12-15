package services;

import java.util.List;
import models.Category;

public interface CategoryService {

    List<Category> getAllCategories();

    void createCategory(String code, String description);

    void editCategoryDescription(String code, String newDescription);
}
