package uz.pdp.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task2.entity.Category;
import uz.pdp.task2.payload.ApiResponse;
import uz.pdp.task2.payload.CategoryDto;
import uz.pdp.task2.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(Integer id) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        }
        return null;
    }

    public ApiResponse add(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId()!=null) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentCategoryId());

            if (optionalCategory.isPresent()) {
                Category parentCategory = optionalCategory.get();
                category.setParentCategory(parentCategory);
            }
        }else {
            category.setParentCategory(null);
        }

        categoryRepository.save(category);
        return new ApiResponse("Ok", true);


    }

    public ApiResponse edit(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            category.setName(categoryDto.getName());
            if (categoryDto.getParentCategoryId()!=null) {
                Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());

                if (optionalParentCategory.isPresent()) {
                    Category parentCategory = optionalParentCategory.get();
                    category.setParentCategory(parentCategory);
                }
            }else {
                category.setParentCategory(null);
            }
            categoryRepository.save(category);
            return new ApiResponse("Ok", true);
        }
        return new ApiResponse("Category topilmadi", false);
    }

    public ApiResponse delete(Integer id) {
        try {
            categoryRepository.deleteById(id);
            return new ApiResponse("Ok",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }

    }
}
