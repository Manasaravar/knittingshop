package com.korniushin.eshop.controllers;

import com.korniushin.eshop.model.dao.interfaces.ProductService;
import com.korniushin.eshop.model.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ItemsSearchController {

        @Autowired
        private ProductService productService;

        @GetMapping(path = {"/items-search"})
        public String getItems(Model model, String keyword) {
            if (keyword != null) {
                List<Product> list = productService.listAll(keyword);
                model.addAttribute("products", list);
                Product product = new Product();
                model.addAttribute("product", product);
            } else {
                List<Product> list = productService.all();
                model.addAttribute("products", list);
                Product product = new Product();
                model.addAttribute("product", product);
            }
            return "items-search";
        }
    @PostMapping("/items-search")
    public String add(Product product) {
        productService.save(product);
        return "redirect:/admin/items-search";
    }

    @GetMapping("/items-search/{id}")
    // @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
    public String getEditPage(Model model, @PathVariable Long id) {
        model.addAttribute("product", productService.findById(id).get());
        model.addAttribute("products", productService.all());
        return "items-search";
    }

    @PostMapping("/items-search/{id}")

    public String update(Product product) {
        productService.update(product);
        return "redirect:/admin/items-search";
    }

    @PostMapping("/items-search/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/admin/items-search";
    }
}
