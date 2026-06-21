package edu.bbte.replate.core.service;

import edu.bbte.replate.core.model.Category;

import java.util.List;

public interface CategoryService {
    Category findById(Long id);

    Category findByName(String name);

    List<Category> findTopLevelCategories();

    List<Category> findSubcategories(String parentCategoryName);

    List<Category> getParentCategoryChain(Long id);
}
