package com.alex.expenseapp.web;

import com.alex.expenseapp.model.entity.Expense;
import com.alex.expenseapp.repository.ExpenseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    private ExpenseRepository expenseRepository;

    public ExpenseController(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @GetMapping("/expenses")
    public List<Expense> getExpenses() {
        return this.expenseRepository.findAll();
    }

    @DeleteMapping("expenses/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id){
        this.expenseRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/expenses")
    public ResponseEntity<Expense> createExpense(@Valid @RequestBody Expense expense) throws URISyntaxException {
        Expense result = this.expenseRepository.save(expense);
        return ResponseEntity.created(new URI("api/expenses" + result.getId())).body(result);
    }
}
