package mate.academy.repositiry.book.spec;

import java.util.Arrays;
import mate.academy.model.Book;
import mate.academy.repositiry.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class DescriptionSpecificationProvider implements SpecificationProvider<Book> {
    @Override
    public String getKey() {
        return "description";
    }

    public Specification<Book> getSpecification(String [] params) {
        return (root, query, criteriaBuilder)
                -> root.get("description").in(Arrays.stream(params).toArray());
    }
}
