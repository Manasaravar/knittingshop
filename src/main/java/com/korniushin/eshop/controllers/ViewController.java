package com.korniushin.eshop.controllers;

import com.korniushin.eshop.DTO.ProductDTO;
import com.korniushin.eshop.model.dao.interfaces.BrandService;
import com.korniushin.eshop.model.dao.interfaces.ProductService;
import com.korniushin.eshop.model.entities.Brand;
import com.korniushin.eshop.model.entities.Category;
import com.korniushin.eshop.model.dao.interfaces.CategoryService;

import com.korniushin.eshop.model.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ViewController {
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final ProductService productService;



    @GetMapping("/index")

    public String getIndexPage(Model model) {

        model.addAttribute("brands", getBrands());

        model.addAttribute("categories", getCategories());

        return "index";
    }

    @PostMapping("/search")
    public String search(Model model, String keyword) {

        //поиск
        if (keyword != null) {
            List<Product> listProducts = productService.listAll(keyword);
            model.addAttribute("listProducts", listProducts);
            model.addAttribute("keyword", keyword);
            return "search";
            // поиск
        } else {
            return "index";
        }
    }

    @GetMapping("/category/{id}")
    public String categoryPage(@PathVariable Long id, Model model){
        model.addAttribute("categories", getCategories());
        model.addAttribute("brands", getBrands());
        final Set<com.korniushin.eshop.model.entities.Product> productsSet = categoryService.findById(id).get().getProducts();
        List<ProductDTO> products = productsSet.stream()
                .map(ProductDTO::new)
                .toList();

        model.addAttribute("products", products);

        return "searchResults";
    }

    public List<Brand> getBrands () {
        List<Brand> brands = brandService.all().stream()
                .map(brand -> Brand.builder()
                        .title(brand.getTitle())
                        .build())
                .toList();
        return brands;
    }

    public List<Category> getCategories() {

        List <Category> categories = categoryService.all().stream()
                .map(category -> Category.builder()
                        .title(category.getTitle())
                        .build())
                .toList();
        return categories;
    }

}
