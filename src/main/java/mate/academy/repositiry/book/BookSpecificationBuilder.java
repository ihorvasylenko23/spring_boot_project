package mate.academy.repositiry.book;

import mate.academy.dto.BookSearchParametersDto;
import mate.academy.model.Book;
import mate.academy.repositiry.SpecificationBuilder;
import mate.academy.repositiry.SpecificationProviderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    @Autowired
    private SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto searchParametrers) {
        Specification<Book> spec = Specification.where(null);
        if (searchParametrers.authors() != null && searchParametrers.authors().length > 0) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("author")
                   .getSpecification(searchParametrers.authors()));
        }
        if (searchParametrers.descriptions() != null
                && searchParametrers.descriptions().length > 0) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("description")
                    .getSpecification(searchParametrers.descriptions()));
        }
        if (searchParametrers.isbns() != null && searchParametrers.isbns().length > 0) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("isbn")
                    .getSpecification(searchParametrers.isbns()));
        }
        if (searchParametrers.prices() != null && searchParametrers.prices().length > 0) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("price")
                    .getSpecification(searchParametrers.prices()));
        }
        if (searchParametrers.titles() != null && searchParametrers.titles().length > 0) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("title")
                    .getSpecification(searchParametrers.titles()));
        }
        return spec;
    }
}
