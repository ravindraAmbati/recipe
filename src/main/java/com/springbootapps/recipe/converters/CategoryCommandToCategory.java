package com.springbootapps.recipe.converters;


import com.springbootapps.recipe.commands.CategoryCommand;
import com.springbootapps.recipe.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
        if (source == null) {
            return null;
        }

        final Category target = new Category();
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        return target;
    }
}
