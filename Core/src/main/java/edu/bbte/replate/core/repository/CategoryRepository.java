package edu.bbte.replate.core.repository;

import edu.bbte.replate.core.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByName(String name);

    List<Category> findCategoriesByParentCategoryIsNull();
}
