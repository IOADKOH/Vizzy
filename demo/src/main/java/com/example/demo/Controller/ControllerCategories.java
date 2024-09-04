package com.example.demo.Controller;

import com.example.demo.Model.ModelCategories;
import com.example.demo.Service.ServiceCategories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class ControllerCategories {
    @Autowired
    private ServiceCategories serviceCategories;
    @GetMapping
    public List<ModelCategories> getAllCategories(){
        return serviceCategories.getAllCategories();
    }
    @GetMapping("/{id}")
    public ModelCategories getCategoriesById (@PathVariable Long id){
        return serviceCategories.getCategoryById(id);
    }
    @PostMapping
    public ResponseEntity<ModelCategories> createCategory (@RequestParam Long id,@RequestParam String nome,@RequestParam ModelCategories categories){
        ModelCategories category=serviceCategories.createNewCategory(id, nome);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
    @PutMapping("/{id}")
    public ModelCategories updateCategory (@PathVariable Long id,@RequestParam String nome){
        ModelCategories category=serviceCategories.updateCategory(id,nome);
        return category;
    }
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        serviceCategories.deleteCategory(id);
    }


}
