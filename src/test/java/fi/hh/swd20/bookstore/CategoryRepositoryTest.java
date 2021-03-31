package fi.hh.swd20.bookstore;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.hh.swd20.bookstore.domain.Category;
import fi.hh.swd20.bookstore.domain.CategoryRepository;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository repository;

    @Test
    public void findByIdShouldReturnBook() {
        Optional<Category> opt = repository.findById(1l);
        assertThat(opt.isPresent()).isTrue();

        Category cat = opt.get();
        assertThat(cat.getName()).isEqualTo("SciFi");
    }

    @Test
    public void createNewBook() {
        Category cat = repository.save(new Category("testi"));
        assertThat(cat.getId()).isGreaterThan(0l);
    }

    @Test
    public void deleteBook() {
        Optional<Category> opt = repository.findById(1l);
        assertThat(opt.isPresent()).isTrue();

        repository.deleteById(opt.get().getId());
        opt = repository.findById(1l);
        assertThat(opt.isPresent()).isFalse();
    }
    
}
