package fi.hh.swd20.bookstore.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.hh.swd20.bookstore.domain.Book;
import fi.hh.swd20.bookstore.domain.BookRepository;
import fi.hh.swd20.bookstore.domain.Category;
import fi.hh.swd20.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;
    @Autowired
    private CategoryRepository catRepo;

    @RequestMapping("/")
    public String bookstore() {
        return "redirect:booklist";
    }

    @GetMapping("/booklist")
    public String booklist(Model model) {
        List<Book> books = (List<Book>) repository.findAll();
        model.addAttribute("books", books);
        return "booklist";
    }

    @GetMapping("/addbook")
    public String addBook(Model model) {
        List<Category> categories = new ArrayList();
        categories.add(new Category());
        categories.addAll((List<Category>) catRepo.findAll());
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categories);
        return "addbook";
    }

    @PostMapping("/savebook")
    public String saveBook(@ModelAttribute Book book) {
        repository.save(book);
        return "redirect:booklist";
    }

    @GetMapping("delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBook(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:../booklist";
    }

    @GetMapping("edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        List<Category> categories = new ArrayList();
        categories.add(new Category());
        categories.addAll((List<Category>) catRepo.findAll());
        model.addAttribute("book", repository.findById(id).get());
        model.addAttribute("categories", categories);
        return "editbook";
    }

    @GetMapping("getbooks")
    public @ResponseBody List<Book> booklistRest() {
        return (List<Book>) repository.findAll();
    }

    @GetMapping("getbook")
    public @ResponseBody Book bookRest(@RequestParam(value="id", defaultValue="1") Long id) {
        Optional<Book> book = repository.findById(id);
        if (book.isPresent()) {
            return book.get();
        }
        return new Book();
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }
    
}
