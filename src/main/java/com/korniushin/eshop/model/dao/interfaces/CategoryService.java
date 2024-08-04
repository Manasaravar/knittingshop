package com.korniushin.eshop.model.dao.interfaces;


import com.korniushin.eshop.DTO.CategoryDTO;
import com.korniushin.eshop.model.dao.interfaces.baseInterface.DAO;
import com.korniushin.eshop.model.entities.Category;

import java.util.List;

public interface CategoryService extends DAO<Category> {
   List<CategoryDTO> getCategories();
}
