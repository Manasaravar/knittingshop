package com.korniushin.eshop.controllers;



import com.korniushin.eshop.DTO.BrandDTO;
import com.korniushin.eshop.DTO.CategoryDTO;
import com.korniushin.eshop.DTO.ProductDTO;
import com.korniushin.eshop.model.dao.interfaces.*;
import com.korniushin.eshop.model.entities.*;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/products")

public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final UserService userService;
    private final ReviewService reviewService;


    @GetMapping
    public String getProducts(Model model) {
        return findPaginated(1, "price", "asc", model);
    }

    @GetMapping("/{id}")
    public String ProductDetailsView(@PathVariable Long id, Model model){

        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("brands", brandService.getBrands());

        final Product product = productService.findById(id).get();

        ProductDTO productDTO = new ProductDTO(product);
        model.addAttribute("product", productDTO);
        model.addAttribute("brandTitle", productDTO.getBrand());

//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = userService.findByUsername(username);

//        Reviews reviews = new Reviews(null,user,productService.findProductById(id));
//        model.addAttribute("reviewAdd", reviews);
        model.addAttribute("reviewsAboutProduct", reviewService.getReviews(id));


        return "productdetails";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {

        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("brands", brandService.getBrands());

        int pageSize = 9;

        Page<Product> page = productService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List <Product> products = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("products", products);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "products";
    }
//    @PostMapping("/{id}/send")
//    public String getReview (Reviews review, BindingResult result) {
//            if (result.hasErrors()) {
//                return "error";
//            }
//            review.setDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
//            reviewService.save(review);
//            return "redirect:/products/{id}";
//    }
@PostMapping("/{id}/send")
public String getReview (String comment, @PathVariable Long id) {

    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userService.findByUsername(username);

    Reviews review = new Reviews(comment, user, productService.findProductById(id));
    review.setDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    reviewService.save(review);
    return "redirect:/products/{id}";
}

}

