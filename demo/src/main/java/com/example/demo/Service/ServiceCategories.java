package com.example.demo.Service;

import com.example.demo.Model.ModelCategories;
import com.example.demo.Repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCategories {
    @Autowired
    private CategoriesRepository categoriesRepository;
    public List<ModelCategories> getAllCategories(){
        return categoriesRepository.findAll();
    }
    public ModelCategories getCategoryById(Long id){
        return categoriesRepository.findById(id).orElse(null);
    }
    public ModelCategories createNewCategory(Long id,String nome){
        ModelCategories pippo=new ModelCategories(id,nome);
        return categoriesRepository.save(pippo);
    }
    public ModelCategories updateCategory(Long id,String nome){
        ModelCategories updatedCategory=new ModelCategories(id,nome);
        return categoriesRepository.save(updatedCategory);
    }
    public boolean deleteCategory(Long id){
        ModelCategories category=categoriesRepository.findById(id).orElse(null);
        if (category != null) {
            categoriesRepository.delete(category);
            return true;
        }
        return false;
    }
}
