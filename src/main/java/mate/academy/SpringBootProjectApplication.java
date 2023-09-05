package mate.academy;

import java.math.BigDecimal;
import mate.academy.model.Book;
import mate.academy.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootProjectApplication {
    private final BookService bookService;

    public SpringBootProjectApplication(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Book book = new Book();
                book.setAuthor("Author");
                book.setPrice(new BigDecimal(100));
                book.setIsbn("isbn");
                book.setTitle("title");
                book.setDescription("description");
                book.setCoverImage("coverImage");

                bookService.save(book);
                System.out.println(bookService.findAll());
            }
        };
    }
}
