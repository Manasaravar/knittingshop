package com.korniushin.eshop.model.dao.repositories;
import com.korniushin.eshop.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {
    Product findProductById (Long ProductId);
    Product findProductByArticle (String article);

    // поиск

    @Query
//            ("SELECT p FROM Product p WHERE p.category.title LIKE %?1%"
//                    + " OR p.brand.title LIKE %?1%"
//                    + " OR p.color LIKE %?1%"
//                    + " OR p.article LIKE %?1%"
//                    + " OR CONCAT(p.price, '') LIKE %?1%")
            ("SELECT p FROM Product p WHERE upper(p.category.title) LIKE CONCAT('%', upper(?1), '%')"
                    + " OR upper(p.brand.title) LIKE CONCAT('%', upper(?1), '%')"
                    + " OR upper(p.composition.title) LIKE CONCAT('%', upper(?1), '%')"
                    + " OR upper(p.color) LIKE CONCAT('%', upper(?1), '%')"
                    + " OR CONCAT(p.article, '') LIKE %?1%"
                    + " OR CONCAT(p.price, '') LIKE %?1%")
    List<Product> search (String keyword);
}
