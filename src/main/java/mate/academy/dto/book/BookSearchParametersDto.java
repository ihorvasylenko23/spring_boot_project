package mate.academy.dto.book;

public record BookSearchParametersDto(String [] titles, String [] authors, String [] isbns,
                                      String [] prices, String [] descriptions) {
}
