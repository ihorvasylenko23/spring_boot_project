package mate.academy.dto;

public record BookSearchParametersDto(String [] titles, String [] authors, String [] isbns,
                                      Double minPrice, Double maxPrice, String [] descriptions) {
}

