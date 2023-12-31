package mate.academy.repositiry.book.spec;

import java.util.Arrays;
import mate.academy.model.Book;
import mate.academy.repositiry.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class IsbnSpecificationProvider implements SpecificationProvider<Book> {
    @Override
    public String getKey() {
        return "isbn";
    }

    public Specification<Book> getSpecification(String [] params) {
        return (root, query, criteriaBuilder)
                -> root.get("isbm").in(Arrays.stream(params).toArray());
    }
}
