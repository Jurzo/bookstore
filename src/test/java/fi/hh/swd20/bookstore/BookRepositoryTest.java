package fi.hh.swd20.bookstore;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.hh.swd20.bookstore.domain.Book;
import fi.hh.swd20.bookstore.domain.BookRepository;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository repository;

    @Test
    public void findByIdShouldReturnBook() {
        Optional<Book> opt = repository.findById(4l);
        assertThat(opt.isPresent()).isTrue();

        Book book = opt.get();
        assertThat(book.getTitle()).isEqualTo("Harry Potter");
    }

    @Test
    public void createNewBook() {
        Book book = repository.save(new Book("title", "author", "year", "isbn", 10.0));
        assertThat(book.getId()).isGreaterThan(0l);
    }

    @Test
    public void deleteBook() {
        Optional<Book> opt = repository.findById(4l);
        assertThat(opt.isPresent()).isTrue();

        repository.deleteById(opt.get().getId());
        opt = repository.findById(4l);
        assertThat(opt.isPresent()).isFalse();
    }
    
}
