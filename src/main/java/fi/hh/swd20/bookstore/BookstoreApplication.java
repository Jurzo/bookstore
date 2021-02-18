package fi.hh.swd20.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.hh.swd20.bookstore.domain.Book;
import fi.hh.swd20.bookstore.domain.BookRepository;
import fi.hh.swd20.bookstore.domain.Category;
import fi.hh.swd20.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
    public CommandLineRunner demo(BookRepository repository, CategoryRepository catRep) {
        return (args) -> {

			log.info("Save some test categories");
			Category cat1 = new Category("SciFi");
			Category cat2 = new Category("Fantasy");
			catRep.save(cat1);
			catRep.save(cat2);

            log.info("Save some test books");
			Book book1 = new Book("Harry Potter", "JKRowling", "2000", "1234456", 12.5, cat1);
			Book book2 = new Book("Testikirja", "Tero Testi", "2009", "1234456", 50.5, cat2);
			repository.save(book1);
			repository.save(book2);

			log.info("fetch all books");
			for (Book b : repository.findAll()) {
				log.info(b.toString());
			}
        };
    }

}
