package fi.hh.swd20.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fi.hh.swd20.bookstore.domain.Book;
import fi.hh.swd20.bookstore.domain.BookRepository;
import fi.hh.swd20.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;
    @Autowired
    private CategoryRepository catRepo;

    @RequestMapping("/index")
    public String bookstore() {
        return "temp";
    }

    @GetMapping("/booklist")
    public String booklist(Model model) {
        List<Book> books = (List<Book>) repository.findAll();
        model.addAttribute("books", books);
        return "booklist";
    }

    @GetMapping("/addbook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", catRepo.findAll());
        return "addbook";
    }

    @PostMapping("/savebook")
    public String saveBook(@ModelAttribute Book book) {
        repository.save(book);
        return "redirect:booklist";
    }

    @GetMapping("delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:../booklist";
    }

    @GetMapping("edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", repository.findById(id).get());
        model.addAttribute("categories", catRepo.findAll());
        return "editbook";
    }
    
}
