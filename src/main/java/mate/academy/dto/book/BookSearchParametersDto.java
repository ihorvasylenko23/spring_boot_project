package mate.academy.dto.book;

public record BookSearchParametersDto(String [] titles, String [] authors, String [] isbn,
                                      String [] prices, String [] descriptions) {
}
