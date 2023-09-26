package mate.academy.dto;

public record BookSearchParametersDto(String [] titles, String [] authors, String [] isbn,
                                      Double minPrice, Double maxPrice, String [] descriptions) {
}

