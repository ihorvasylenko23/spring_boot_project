package mate.academy.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.dto.category.CategoryResponseDto;
import mate.academy.dto.category.CreateCategoryRequestDto;
import mate.academy.service.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Get all categories", description = "Get list of all available categories")
    public List<CategoryResponseDto> getAll(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by id", description = "Get category by id")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return categoryService.getByID(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new category", description = "Create a new category")
    @PostMapping
    public CategoryResponseDto createCategory(
            @RequestBody @Valid CreateCategoryRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update a category",
            description = "Update data about an existing category")
    @PutMapping ("/{id}")
    public CategoryResponseDto updateCategory(@PathVariable Long id,
                                              @RequestBody CreateCategoryRequestDto requestDto) {
        return categoryService.update(id, requestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete category by id", description = "Delete category by id")
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @GetMapping("/{id}/books")
    @Operation(summary = "Get a list of books by id",
            description = "Getting a list of books for a given category")
    public List<BookDtoWithoutCategoryIds> getBooksByCategoryId(@PathVariable Long id) {
        return categoryService.getBooksByCategoryId(id);
    }
}
