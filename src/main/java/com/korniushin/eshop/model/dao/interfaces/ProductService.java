package com.korniushin.eshop.model.dao.interfaces;

import com.korniushin.eshop.DTO.ProductDTO;
import com.korniushin.eshop.model.dao.interfaces.baseInterface.DAO;
import com.korniushin.eshop.model.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;

import java.util.List;


public interface ProductService extends DAO<Product> {
    Product findProductById (Long ProductId);

    Product findByArticle (String article);

    //поиск
    List<Product> listAll(String keyword);

    //pagination
    Page<Product> findPaginated (int pageNo, int pageSize, String sortField, String sortDirection);

    List<ProductDTO> getProducts();

}


