package com.example.demo.Service;

import com.example.demo.Model.ModelCategories;
import com.example.demo.Model.ModelExpense;
import com.example.demo.Repository.CategoriesRepository;
import com.example.demo.Repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ServiceExpense {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;
    public ModelCategories getCategoryById(Long id){
        return categoriesRepository.findById(id).orElse(null);
    }
    public List<ModelExpense> getAllExpenses(){
        return expenseRepository.findAll();
    }
    public ModelExpense getExpenseById(Long id){
        return expenseRepository.findById(id).orElse(null);
    }
    public ModelExpense createNewExpense (String nome, int ammontare, Date data,ModelCategories categories){
        ModelExpense pippo=new ModelExpense(null,nome,ammontare,data,categories);
        return expenseRepository.save(pippo);
    }
    public ModelExpense createNewExpense (ModelExpense expense){
        return expenseRepository.save(expense);
    }
    public ModelExpense updateExpense (Long id, String nome, int ammontare, Date data,ModelCategories categories){
        ModelExpense updatedExpense=new ModelExpense(id,nome,ammontare,data,categories);
        return expenseRepository.save(updatedExpense);
    }
    public ModelExpense updateExpense (ModelExpense expense){
        return expenseRepository.save(expense);
    }
    public boolean deleteExpense(Long id){
        ModelExpense expense=expenseRepository.findById(id).orElse(null);
        if(expense!=null){
            expenseRepository.delete(expense);
            return true;
        }
        return false;

    }
}
