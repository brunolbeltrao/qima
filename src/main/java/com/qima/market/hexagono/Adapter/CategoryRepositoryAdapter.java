package com.qima.market.hexagono.Adapter;

import com.qima.market.hexagono.domain.Category;
import com.qima.market.repository.CategoryEntity;
import com.qima.market.repository.CategoryRepository;
import com.qima.market.repository.CategoryRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepositoryAdapter implements CategoryRepository {

    private final CategoryRepositoryJpa categoryRepositoryJpa;

    protected CategoryRepositoryAdapter(CategoryRepositoryJpa categoryRepositoryJpa) {
        this.categoryRepositoryJpa = categoryRepositoryJpa;
    }

    public Category save(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity(category.getName());
        return build(categoryRepositoryJpa.save(categoryEntity));
    }

    public void delete(Category Category) {
        categoryRepositoryJpa.deleteById(Category.getId());
    }

    @Override
    public Category findById(Long id) {
        CategoryEntity categoryEntity = categoryRepositoryJpa.findById(id).get();
        Category category = build(categoryEntity);
        return category;
    }

    private Category build(CategoryEntity categoryEntity) {
        if (categoryEntity == null){
            return null;
        }
        Category category = Category.builder().id(categoryEntity
                        .getId())
                .name(categoryEntity.getName())
                .build();

        return category;
    }



    private List<Category> buildListCategorys(List<CategoryEntity> categoryEntities) {
        List<Category> categorys =  new ArrayList<>();

        categoryEntities.stream().forEach(categoryEntity -> {
            Category category = new Category(categoryEntity.getId(), categoryEntity.getName());
            categorys.add(category);
        });

        return categorys;
    }


    public Category findCategoryById(Long id) {
        CategoryEntity categoryEntity = categoryRepositoryJpa.findById(id).get();
        return build(categoryEntity);
    }

    public List<Category> findCategorys() {
        return buildListCategorys(categoryRepositoryJpa.findAll());
    }

    public Category findCategoryByName(String name) {
        CategoryEntity categoryEntity = categoryRepositoryJpa.findCategoryByName(name);
        return build(categoryEntity);
    }
}
