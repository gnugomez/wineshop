package com.group3.wineshop.controllers;

import com.group3.wineshop.entities.Winery;
import com.group3.wineshop.exceptions.NotFoundException;
import com.group3.wineshop.services.WineryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/winery")
public class WineryController {

    @Autowired
    WineryService wineryService;

    @GetMapping("")
    public List<Winery> getAll() {
        return wineryService.getAll();
    }

    @GetMapping("/{id}")
    public Winery getById(@PathVariable Integer id) throws NotFoundException {
        return wineryService.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<Winery> save(@Valid @RequestBody Winery winery) {
        return new ResponseEntity<>(wineryService.save(winery), null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Winery> update(@PathVariable Integer id ,@Valid @RequestBody Winery winery) {
        winery.setId(id);
        return new ResponseEntity<>(wineryService.update(winery), null, 201);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) throws NotFoundException {
        wineryService.delete(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    };
}
