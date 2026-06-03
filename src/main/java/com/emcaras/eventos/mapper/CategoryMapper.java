package com.emcaras.eventos.mapper;

import com.emcaras.eventos.domain.Category;
import com.emcaras.eventos.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryDto categoryDto);
    CategoryDto toDto(Category category);
    List<CategoryDto> toListDto(List<Category> categories);

    void updateFromDto(CategoryDto categoryDto, @MappingTarget Category category);
}
