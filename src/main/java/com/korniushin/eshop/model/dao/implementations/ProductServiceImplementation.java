package com.korniushin.eshop.model.dao.implementations;

import com.korniushin.eshop.DTO.BrandDTO;
import com.korniushin.eshop.DTO.ProductDTO;
import com.korniushin.eshop.model.dao.interfaces.ProductService;
import com.korniushin.eshop.model.dao.repositories.ProductRepository;
import com.korniushin.eshop.model.entities.*;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductService {
    final private ProductRepository productRepository;

    @Override
    public List<Product> all() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
            return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        Product newProduct = productRepository.findById(product.getId()).get();
        newProduct.setArticle(product.getArticle());
        newProduct.setPrice(product.getPrice());
        newProduct.setColor(product.getColor());
        newProduct.setQuantity(product.getQuantity());
        newProduct.setUnit(product.getUnit());
        newProduct.setBrand(product.getBrand());
        newProduct.setCategory(product.getCategory());
        newProduct.setComposition(product.getComposition());
        return productRepository.save(newProduct);

    }

    @Override
    public boolean deleteById(Long id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Product findProductById(Long ProductId) {
        return productRepository.findProductById(ProductId);
    }

    @Override
    public Product findByArticle(String article) {
        return productRepository.findProductByArticle(article);
    }


    //ПОИСК
    @Override
      public List<Product> listAll(String keyword) {
        if (keyword != null) {
            return productRepository.search(keyword);
        }
        return productRepository.findAll();
    }
    // PAGINATION
    @Override
    public Page<Product> findPaginated(int pageNo, int pageSize,String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.productRepository.findAll(pageable);
    }

    @Override
    public List<ProductDTO> getProducts() {

                  List<ProductDTO> products = productRepository.findAll().stream()
                    .map(p -> ProductDTO.builder()
                            .id(p.getId())
                            .price(p.getPrice())
                            .brand(p.getBrand().getTitle())
                            .build())
                    .toList();
            return products;

    }
}
