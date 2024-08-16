//package com.korniushin.eshop.controllers;
//
//import com.korniushin.eshop.model.dao.implementations.OrderPositionImplementation;
//import com.korniushin.eshop.model.dao.interfaces.BrandService;
//import com.korniushin.eshop.model.dao.interfaces.ProductService;
//import com.korniushin.eshop.model.entities.Brand;
//import com.korniushin.eshop.model.entities.Product;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/admin")
//@RequiredArgsConstructor
//public class ItemsController {
//
//    private final ProductService productService;
//    private final BrandService brandService;
//
//
//    @GetMapping("/items")
//    public String items (Model model) {
//
//        List<Product> products = productService.all();
//        Product product = new Product();
//        model.addAttribute("products", products);
//        model.addAttribute("product", product);
//
//        List<Brand> dropDownBrands = brandService.all();
//        model.addAttribute("dropDownBrands",dropDownBrands);
//
//        return "items";
//    }
//
//    @PostMapping("/items")
//    public String add(Product product) {
//        productService.save(product);
//        return "redirect:/admin/items";
//    }
//
//    @GetMapping("/items/{id}")
//    // @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
//    public String getEditPage(Model model, @PathVariable Long id) {
//        model.addAttribute("product", productService.findById(id).get());
//        model.addAttribute("products", productService.all());
//        return "items";
//    }
//
//    @PostMapping("/items/{id}")
//
//    public String update(Product product) {
//        productService.update(product);
//        return "redirect:/admin/items";
//    }
//
//    @PostMapping("/items/delete/{id}")
//    public String delete(@PathVariable Long id) {
//        productService.deleteById(id);
//        return "redirect:/admin/items";
//    }
//
//}
//
//
//
//
//
