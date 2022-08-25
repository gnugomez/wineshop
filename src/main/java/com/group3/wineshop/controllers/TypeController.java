package com.group3.wineshop.controllers;

import com.group3.wineshop.entities.Type;
import com.group3.wineshop.exceptions.NotFoundException;
import com.group3.wineshop.services.TypeService;
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
@RequestMapping("/api/type")

public class TypeController {
    @Autowired
    private TypeService typeService;
    @GetMapping("")
    public List<Type> getAll(){
        return typeService.getAll();
    }
    @GetMapping("/{id}")
    public Type getById(@PathVariable Integer id) throws NotFoundException {
        return typeService.getById(id);
    }
    @PostMapping("")
    public ResponseEntity<Type> save(@Valid @RequestBody Type type) {
        return new ResponseEntity<>(typeService.save(type), null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Type update(@PathVariable Integer id, @Valid @RequestBody Type type){

        type.setId(id);
        return typeService.update(type);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) throws NotFoundException {
       typeService.delete(id);
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
