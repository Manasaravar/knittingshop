package com.korniushin.eshop.controllers;

import com.korniushin.eshop.DTO.BrandDTO;
import com.korniushin.eshop.DTO.CategoryDTO;
import com.korniushin.eshop.DTO.LessonDTO;
import com.korniushin.eshop.DTO.ProductDTO;
import com.korniushin.eshop.model.Mail;
import com.korniushin.eshop.model.dao.interfaces.*;
import com.korniushin.eshop.model.entities.Brand;
import com.korniushin.eshop.model.entities.Category;

import com.korniushin.eshop.model.entities.Lesson;
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
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ViewController {
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final ProductService productService;
    private final MailService mailService;
    private final LessonService lessonService;

    @GetMapping("/index")

    public String getIndexPage(Model model) {

        model.addAttribute("brands", brandService.getBrands());

        model.addAttribute("categories", categoryService.getCategories());

        List <ProductDTO> productsRecommended = productService.getProducts().stream()
                .filter(p -> p.getPrice() <= 300 && Objects.equals(p.getBrand(), "DROPS"))
                .toList();

        model.addAttribute("products", productsRecommended);

        List<Lesson> lessons = lessonService.all();
        model.addAttribute("lessons", lessons);

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
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("brands", brandService.getBrands());
        final Set<com.korniushin.eshop.model.entities.Product> productsSet = categoryService.findById(id).get().getProducts();
        List<ProductDTO> products = productsSet.stream()
                .map(ProductDTO::new)
                .toList();

        model.addAttribute("products", products);

        return "searchResults";
    }

    @GetMapping("/brand/{id}")
    public String brandPage(@PathVariable Long id, Model model){
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("brands", brandService.getBrands());
        final Set<com.korniushin.eshop.model.entities.Product> productsSet = brandService.findById(id).get().getProducts();
        List<ProductDTO> products = productsSet.stream()
                .map(ProductDTO::new)
                .toList();

        model.addAttribute("products", products);

        return "searchResults";
    }

    //Обратная связь
    @PostMapping("/send/textMail")
    public String sendTextMail(String message){
        Mail mail = new Mail(null, message,null,null);
        mailService.sendTextMail(mail);
        return "redirect:/index";

    }



}
