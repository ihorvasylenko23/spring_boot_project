package mate.academy.repositiry;

import java.util.List;
import mate.academy.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}