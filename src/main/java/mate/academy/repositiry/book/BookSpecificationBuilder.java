package mate.academy.repositiry.book;

import lombok.RequiredArgsConstructor;
import mate.academy.dto.book.BookSearchParametersDto;
import mate.academy.model.Book;
import mate.academy.repositiry.SpecificationBuilder;
import mate.academy.repositiry.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private SpecificationProviderManager<Book> bookSpecificationProviderManager;

    public BookSpecificationBuilder(
            SpecificationProviderManager<Book> bookSpecificationProviderManager) {
        this.bookSpecificationProviderManager = bookSpecificationProviderManager;
    }

    @Override
    public Specification<Book> build(BookSearchParametersDto searchParameters) {
        Specification<Book> spec = Specification.where(null);

        addSpecificationIfPresent(searchParameters.authors(), "author", spec);
        addSpecificationIfPresent(searchParameters.descriptions(), "description", spec);
        addSpecificationIfPresent(searchParameters.isbn(), "isbn", spec);
        addSpecificationIfPresent(searchParameters.prices(), "price", spec);
        addSpecificationIfPresent(searchParameters.titles(), "title", spec);

        return spec;
    }

    private void addSpecificationIfPresent(String[] values,
                                           String providerName,
                                           Specification<Book> spec) {
        if (values != null && values.length > 0) {
            spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider(providerName)
                    .getSpecification(values));
        }
    }
}
