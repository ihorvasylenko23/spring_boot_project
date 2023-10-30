package mate.academy.service;

import java.util.List;
import mate.academy.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.dto.category.CategoryResponseDto;
import mate.academy.dto.category.CreateCategoryRequestDto;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    List<CategoryResponseDto> findAll(Pageable pageable);

    CategoryResponseDto getByID(Long id);

    CategoryResponseDto save(CreateCategoryRequestDto requestDto);

    CategoryResponseDto update(Long id, CreateCategoryRequestDto requestDto);

    void delete(Long id);

    List<BookDtoWithoutCategoryIds> getBooksByCategoryId(Long id);
}
