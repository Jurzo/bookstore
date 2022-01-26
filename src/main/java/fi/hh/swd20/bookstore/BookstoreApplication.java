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
import fi.hh.swd20.bookstore.domain.User;
import fi.hh.swd20.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
    public CommandLineRunner demo(BookRepository books, CategoryRepository categories, UserRepository users) {
        return (args) -> {

			log.info("Save some test categories");
			Category cat1 = new Category("SciFi");
			Category cat2 = new Category("Fantasy");
			Category cat3 = new Category("Education");
			categories.save(cat1);
			categories.save(cat2);
			categories.save(cat3);

            log.info("Save some test books");
			Book book1 = new Book("Harry Potter", "JKRowling", "2000", "1234456", 12.5, cat1);
			Book book2 = new Book("Testikirja", "Tero Testi", "2009", "1234456", 50.5);
			books.save(book1);
			books.save(book2);

			// admin:admin, user:user
			log.info("Create some users to database");
			User user = new User("user", "user@domain", "$2a$10$jpCEOlTIWzeGLfCdT9HBmeP8oz9/Z4w3vpaHbFB4JewP/o9v2G6Ji", "USER");
			User admin = new User("admin", "admin@domain", "$2a$10$BL3dOAcbj0sHZwcIiNSvoO8nyfeVwGdWSMPriJwQo5yOoXhcIPOMe", "ADMIN");
			users.save(user);
			users.save(admin);

        };
    }

}
