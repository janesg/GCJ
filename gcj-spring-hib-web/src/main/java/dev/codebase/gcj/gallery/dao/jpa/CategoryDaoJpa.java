package dev.codebase.gcj.gallery.dao.jpa;

import org.springframework.stereotype.Repository;

import dev.codebase.gcj.gallery.dao.CategoryDao;
import dev.codebase.gcj.gallery.domain.Category;

@Repository("categoryDao")
public class CategoryDaoJpa extends GenericDaoJpa<Category> implements CategoryDao {

    public CategoryDaoJpa() {
        super(Category.class);
    }
}
