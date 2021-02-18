package fi.hh.swd20.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fi.hh.swd20.bookstore.domain.Category;
import fi.hh.swd20.bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @GetMapping("/categorylist")
    public String categorylist(Model model) {
        model.addAttribute("categories", (List<Category>) repository.findAll());
        return "categorylist";
    }

    @GetMapping("/createcategory")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "createcategory";
    }

    @PostMapping("/createcategory")
    public String saveCategory(@ModelAttribute Category cat) {
        repository.save(cat);
        return "redirect:categorylist";
    }
    
}
