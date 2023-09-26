package mate.academy.repositiry;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProvider<T> {
    String getKey();

    default Specification<T> getSpecification(String[] params) {
        throw new UnsupportedOperationException("This method is not supported.");
    }

    default Specification<T> getSpecification(Double min, Double max) {
        throw new UnsupportedOperationException("This method is not supported.");
    }
}
