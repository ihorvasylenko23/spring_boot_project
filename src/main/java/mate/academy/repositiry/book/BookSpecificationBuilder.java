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
    public Specification<Book> build(BookSearchParametersDto searchParameters) {
        Specification<Book> spec = Specification.where(null);

        addSpecificationIfPresent(searchParameters.authors(), "author", spec);
        addSpecificationIfPresent(searchParameters.descriptions(), "description", spec);
        addSpecificationIfPresent(searchParameters.isbn(), "isbn", spec);
        addPriceSpecificationIfPresent(searchParameters.minPrice(),
                searchParameters.maxPrice(), spec);
        addSpecificationIfPresent(searchParameters.titles(), "title", spec);

        return spec;
    }

    private void addSpecificationIfPresent(String[] values,
                                           String providerName,
                                           Specification<Book> spec) {
        if (values != null && values.length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider(providerName)
                    .getSpecification(values));
        }
    }

    private void addPriceSpecificationIfPresent(Double minPrice,
                                                Double maxPrice,
                                                Specification<Book> spec) {
        if (minPrice != null || maxPrice != null) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider("price")
                    .getSpecification(minPrice, maxPrice));
        }
    }
}
