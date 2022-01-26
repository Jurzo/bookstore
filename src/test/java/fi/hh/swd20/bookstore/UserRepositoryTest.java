package fi.hh.swd20.bookstore;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.hh.swd20.bookstore.domain.User;
import fi.hh.swd20.bookstore.domain.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void findByNameShouldReturnUser() {
        User user = repository.findByUsername(System.getenv("USERNAME"));
        assertNotNull(user);
    }

    @Test
    public void createNewUser() {
        User user = repository.save(new User("Juuso", "test@moi", "12345", "TEST"));
        assertThat(user.getId()).isGreaterThan(0l);
    }

    @Test
    public void deleteUser() {
        User user = repository.findByUsername(System.getenv("USERNAME"));
        assertNotNull(user);

        repository.deleteById(user.getId());
        user = repository.findByUsername(System.getenv("USERNAME"));
        assertNull(user);
    }
    
}
