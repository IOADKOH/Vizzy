package com.example.demo.Controller;

import com.example.demo.Model.ModelCategories;
import com.example.demo.Model.ModelExpense;
import com.example.demo.Service.ServiceExpense;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/expenses")
@Tag(name = "Gestione spese API", description = "API di gestione spese")


public class ControllerExpense {
    @Autowired
    private ServiceExpense serviceExpense;

    @GetMapping
    public List<ModelExpense> getAllExpenses() {
        return serviceExpense.getAllExpenses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelExpense> getExpenseById(@PathVariable Long id) {
        ModelExpense spesa=serviceExpense.getExpenseById(id);
        if(spesa==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(spesa);
    }
    @PostMapping
    public ResponseEntity<ModelExpense> createExpense (@RequestParam String nome, @RequestParam int ammontare, @RequestParam Date data,@RequestParam Long category_id){
       ModelExpense expense= new ModelExpense();
       expense.setNome(nome);
       expense.setAmmontare(ammontare);
       expense.setData(data);
       ModelCategories category=serviceExpense.getCategoryById(category_id);
        if (category != null) {
            expense.setCategories(category);
            serviceExpense.createNewExpense(expense);
            return ResponseEntity.status(HttpStatus.CREATED).body(expense);
        }else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelExpense> updateExpense(@PathVariable Long id,@RequestParam String nome, @RequestParam int ammontare, @RequestParam Date data,@RequestParam Long category_id){
        ModelExpense expense=new ModelExpense();
        expense.setId(id);
        expense.setNome(nome);
        expense.setAmmontare(ammontare);
        expense.setData(data);
        ModelCategories category=serviceExpense.getCategoryById(category_id);
        if (category != null) {
            expense.setCategories(category);
            serviceExpense.updateExpense(expense);
            return ResponseEntity.status(HttpStatus.OK).body(expense);
        }else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ModelExpense> deleteExpense (@PathVariable Long id){
        ModelExpense spesa=serviceExpense.getExpenseById(id);
        if(spesa==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        serviceExpense.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

}
