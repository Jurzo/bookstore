package fi.hh.swd20.bookstore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.hh.swd20.bookstore.web.BookController;
import fi.hh.swd20.bookstore.web.CategoryController;
import fi.hh.swd20.bookstore.web.UserDetailServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookstoreApplicationTests {

	@Autowired
	private BookController bookController;
	@Autowired
	private CategoryController categoryController;
	@Autowired
	private UserDetailServiceImpl userDetailsService;

	@Test
	public void contextLoads() {
		assertThat(bookController).isNotNull();
		assertThat(categoryController).isNotNull();
		assertThat(userDetailsService).isNotNull();
	}

}
